package com.share.lifetime.entity;

import java.util.Date;

import com.share.lifetime.util.JsonUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author liaoxiang
 * @date 2018/11/11
 */
@Getter
@Setter
public abstract class AbstractEntity {

    protected String id;
    protected Date gmtCreate;
    protected Date gmtModified;
    protected String creator;
    protected String modifier;
    protected String isDeleted;
    protected String code;

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }

}
