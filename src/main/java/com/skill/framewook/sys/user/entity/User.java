package com.skill.framewook.sys.user.entity;


import com.skill.framewook.code.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * Created by CYG on 2017/8/31.
 */
@Entity
@Table(name = "pl_user")
public class User extends AbstractEntity {

    @Column
    private String name;

    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
