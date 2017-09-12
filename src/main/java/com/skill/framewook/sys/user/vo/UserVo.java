package com.skill.framewook.sys.user.vo;

import com.skill.framewook.code.entity.AbstractVo;
import com.skill.framewook.code.entity.BaseEntity;

/**
 * Created by CYG on 2017/8/31.
 */
public class UserVo extends AbstractVo{

    private String name;

    @Override
    public void convert(BaseEntity entity) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
