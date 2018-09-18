package com.share.lifetime.service.impl;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dingtalk.chatbot.message.MarkdownMessage;
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
	public void testSendMarkdownMessageMessage() throws IOException {
		MarkdownMessage message = new MarkdownMessage();
		message.setTitle("2018世界人工智能大会");
		message.add(
				"9月17-19日，由国家发展改革委、科技部、工业信息化部、国家网信办、中国科学院、中国工程院和上海市人民政府联合主办的“2018世界人工智能大会”在上海西岸举办。届时超200名来自全球人工智能领域的重量级嘉宾、1000家创新企业、2万名专业观众参与会议。");
		message.add("本次大会将结合人工智能在教育、健康、金融、零售、交通、智造、服务等7个领域的应用进行一次集中式、场景式、浸入式的AI应用体验展示，让参与的嘉宾能够身临其境感受“人工智能赋能新时代”。");
		dingTalkMessageService.sendMessage(message);
	}

	@Test
	public void testSendMarkdownMessageStringMarkdownMessage() {
		fail("Not yet implemented");
	}

}
