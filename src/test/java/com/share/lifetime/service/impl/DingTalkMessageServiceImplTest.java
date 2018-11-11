package com.share.lifetime.service.impl;

import static org.junit.Assert.fail;

import java.net.URLEncoder;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dingtalk.chatbot.message.MarkdownMessage;
import com.share.lifetime.constant.DingTalkConsts;
import com.share.lifetime.service.DingTalkMessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring.xml"})
@ActiveProfiles("test")
public class DingTalkMessageServiceImplTest {

	@Autowired
	private DingTalkMessageService dingTalkMessageService;

	@Test
	public void testSendMessage() {
		fail("Not yet implemented");
	}

	@Test
	public void testSendMarkdownMessageMessage() throws Exception {
		MarkdownMessage message = new MarkdownMessage();
		message.setTitle("2018世界人工智能大会");
		message.add(
				"9月17-19日，由国家发展改革委、科技部、工业信息化部、国家网信办、中国科学院、中国工程院和上海市人民政府联合主办的“2018世界人工智能大会”在上海西岸举办。届时超200名来自全球人工智能领域的重量级嘉宾、1000家创新企业、2万名专业观众参与会议。");
		message.add("本次大会将结合人工智能在教育、健康、金融、零售、交通、智造、服务等7个领域的应用进行一次集中式、场景式、浸入式的AI应用体验展示，让参与的嘉宾能够身临其境感受“人工智能赋能新时代”。");
		dingTalkMessageService.sendMessage(DingTalkConsts.CHATBOT_WEBHOOK, message);
	}

	@Test
	public void testSendMarkdownMessageStringMarkdownMessage() throws Exception {
		MarkdownMessage message = new MarkdownMessage();
		message.setTitle("This is a markdown message");
		message.add(MarkdownMessage.getHeaderText(1, "header 1"));
		message.add(MarkdownMessage.getHeaderText(2, "header 2"));
		message.add(MarkdownMessage.getHeaderText(3, "header 3"));
		message.add(MarkdownMessage.getHeaderText(4, "header 4"));
		message.add(MarkdownMessage.getHeaderText(5, "header 5"));
		message.add(MarkdownMessage.getHeaderText(6, "header 6"));

		message.add(MarkdownMessage.getReferenceText("reference text"));
		message.add("\n\n");

		message.add("normal text");
		message.add("\n\n");

		message.add(MarkdownMessage.getBoldText("Bold Text"));
		message.add("\n\n");

		message.add(MarkdownMessage.getItalicText("Italic Text"));
		message.add("\n\n");

		ArrayList<String> orderList = new ArrayList<String>();
		orderList.add("order item1");
		orderList.add("order item2");
		message.add(MarkdownMessage.getOrderListText(orderList));
		message.add("\n\n");

		ArrayList<String> unorderList = new ArrayList<String>();
		unorderList.add("unorder item1");
		unorderList.add("unorder item2");
		message.add(MarkdownMessage.getUnorderListText(unorderList));
		message.add("\n\n");

		message.add(MarkdownMessage.getImageText("http://img01.taobaocdn.com/top/i1/LB1GCdYQXXXXXXtaFXXXXXXXXXX"));
		message.add(
				MarkdownMessage.getLinkText("This is a link", "dtmd://dingtalkclient/sendMessage?content=linkmessage"));
		message.add(MarkdownMessage.getLinkText("中文跳转",
				"dtmd://dingtalkclient/sendMessage?content=" + URLEncoder.encode("链接消息", "UTF-8")));
		dingTalkMessageService.sendMessage(DingTalkConsts.CHATBOT_WEBHOOK, message);
	}

}
