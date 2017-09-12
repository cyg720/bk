package com.skill.framewook.code.entity;

import java.io.Serializable;

/**
 * Created by CYG on 2017/9/2.
 */
public interface BaseEntity extends Serializable{

    Serializable getId();

    int getVersion();

}
