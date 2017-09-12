package com.skill.framewook.code.entity;

/**
 * Created by CYG on 2017/9/2.
 */
public abstract class AbstractVo implements BaseVo{

    private String id;

    private int version;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
