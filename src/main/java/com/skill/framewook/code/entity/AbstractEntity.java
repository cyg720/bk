package com.skill.framewook.code.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by CYG on 2017/9/1.
 */
@MappedSuperclass
public abstract class AbstractEntity implements BaseEntity{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "com.skill.framewook.code.entity.UUIDSequence")
    @Column(columnDefinition = "varchar(32)")
    private String id;

    @Version
    @Column(columnDefinition = " int(11) default 0")
    private int version;

    @CreatedDate
    @Column
    private Date createTime=new Date();

    @LastModifiedDate
    @Column
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        AbstractEntity that = (AbstractEntity) obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
