package com.skill.framewook.code.entity;

import java.io.Serializable;

/**
 * Created by CYG on 2017/9/2.
 */
public interface BaseVo extends Serializable{
    String getId();

    int getVersion();

    void convert(BaseEntity entity);
}
