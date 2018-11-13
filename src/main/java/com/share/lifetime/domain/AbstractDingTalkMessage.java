package com.share.lifetime.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author liaoxiang
 * @date 2018/11/11
 */
@Getter
@Setter
public abstract class AbstractDingTalkMessage extends AbstractMessage {

    private String webhook;

}