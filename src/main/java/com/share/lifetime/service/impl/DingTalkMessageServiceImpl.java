package com.share.lifetime.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dingtalk.chatbot.DingtalkChatbotClient;
import com.dingtalk.chatbot.SendResult;
import com.dingtalk.chatbot.message.MarkdownMessage;
import com.share.lifetime.constant.DingTalkConsts;
import com.share.lifetime.domain.DingTalkMarkdownMessage;
import com.share.lifetime.domain.Message;
import com.share.lifetime.service.DingTalkMessageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("dingTalkMessageService")
public class DingTalkMessageServiceImpl implements DingTalkMessageService {

	@Override
	public void sendMessage(Message message) throws IOException {
		if (message instanceof DingTalkMarkdownMessage) {
			DingTalkMarkdownMessage dingTalkMarkdownMessage = (DingTalkMarkdownMessage) message;
			String webhook = dingTalkMarkdownMessage.getWebhook();
			MarkdownMessage markdownMessage = new MarkdownMessage();
			markdownMessage.setTitle(dingTalkMarkdownMessage.getTitle());
			List<String> items = dingTalkMarkdownMessage.getItems();
			for (String item : items) {
				markdownMessage.add(item);
			}
			sendMessage(webhook, markdownMessage);
		}
	}

	@Override
	public void sendMessage(String webhook, com.dingtalk.chatbot.message.Message message) throws IOException {
		DingtalkChatbotClient chatbotClient = new DingtalkChatbotClient();
		SendResult send = chatbotClient.send(webhook, message);
		log.info("钉钉机器人推送信息！！webhook:{},message:{},errorCode:{},errorMsg:{}", message.toJsonString(),
				send.getErrorCode(), send.getErrorMsg());
	}

}
