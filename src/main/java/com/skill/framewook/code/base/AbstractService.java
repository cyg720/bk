package com.skill.framewook.code.base;

import com.skill.framewook.code.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * Created by CYG on 2017/9/2.
 */
public abstract class AbstractService<M extends BaseEntity> implements BaseService<M> {


    @Autowired
    private BaseDao<M> baseDao;

    //根据Id 主键获取实体
    @Override
    public M findOneById(String id) {
        return baseDao.findOne(id);
    }

    //保存单个实体
    @Override
    public M save(M m) {
        return baseDao.save(m);
    }

    //删除单个实体
    @Override
    public void delete(String id) {
        baseDao.delete(id);
    }

    @Override
    public void delete(String[] ids) {
        Arrays.asList(ids).forEach(id ->
            this.delete(id)
        );
    }

    //修改实体
    @Override
    public void update(M m) {
        baseDao.saveAndFlush(m);
    }

    @Override
    public List<M> findAll() {
        return baseDao.findAll();
    }
}
