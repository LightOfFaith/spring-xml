package com.share.lifetime.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.dingtalk.chatbot.DingtalkChatbotClient;
import com.dingtalk.chatbot.SendResult;
import com.dingtalk.chatbot.message.MarkdownMessage;
import com.share.lifetime.constant.DingTalkConsts;
import com.share.lifetime.domain.DingTalkMessage;
import com.share.lifetime.domain.Message;
import com.share.lifetime.service.DingTalkMessageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("dingTalkMessageService")
public class DingTalkMessageServiceImpl implements DingTalkMessageService {

	@Override
	public void sendMessage(Message message) throws IOException {
		if (message instanceof DingTalkMessage) {
			DingTalkMessage dingTalkMarkdownMessage = (DingTalkMessage) message;
			MarkdownMessage markdownMessage = new MarkdownMessage();
			markdownMessage.setTitle(MarkdownMessage.getHeaderText(2, dingTalkMarkdownMessage.getSubject()));
			markdownMessage.add(MarkdownMessage.getBoldText(dingTalkMarkdownMessage.getText()));
			sendMessage(markdownMessage);
		}
	}

	@Override
	public void sendMessage(com.dingtalk.chatbot.message.Message message) throws IOException {
		sendMessage(DingTalkConsts.CHATBOT_WEBHOOK, message);
	}

	@Override
	public void sendMessage(String webhook, com.dingtalk.chatbot.message.Message message) throws IOException {
		DingtalkChatbotClient chatbotClient = new DingtalkChatbotClient();
		SendResult send = chatbotClient.send(webhook, message);
		log.info("钉钉机器人推送信息！！webhook:{},message:{},errorCode:{},errorMsg:{}", message.toJsonString(),
				send.getErrorCode(), send.getErrorMsg());
	}

}
