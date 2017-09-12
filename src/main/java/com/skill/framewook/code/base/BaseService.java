package com.skill.framewook.code.base;

import com.skill.framewook.code.entity.BaseEntity;

import java.util.List;

/**
 * Created by CYG on 2017/9/2.
 */
public interface BaseService<M extends BaseEntity> {

    M findOneById(String id);

    List<M> findAll();

    M save(M m);

    void delete(String id);

    void delete(String[] id);

    void update(M m);

}
