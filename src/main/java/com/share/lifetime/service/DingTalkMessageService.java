package com.share.lifetime.service;

/**
 * https://open-doc.dingtalk.com/docs/doc.htm?spm=a219a.7629140.0.0.75f54a975DC1p4&treeId=257&articleId=105733&docType=1
 * @author liaoxiang
 *
 */
public interface DingTalkMessageService extends MessageService {


	void sendMessage(com.dingtalk.chatbot.message.Message message) throws Exception;

	void sendMessage(String webhook, com.dingtalk.chatbot.message.Message message) throws Exception;
}
