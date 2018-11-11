package com.share.lifetime.service;

import com.share.lifetime.domain.AbstractMessage;

/**
 * https://open-doc.dingtalk.com/docs/doc.htm?spm=a219a.7629140.0.0.75f54a975DC1p4&treeId=257&articleId=105733&docType=1
 * @author liaoxiang
 *
 */
public interface DingTalkMessageService extends MessageService {


	void sendMessage(String webhook,AbstractMessage message) throws Exception;

	void sendMessage(String webhook, com.dingtalk.chatbot.message.Message message) throws Exception;
}
