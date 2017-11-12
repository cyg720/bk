package com.skill.framewook.code.base;

import com.skill.framewook.code.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by CYG on 2017/9/2.
 */
public abstract class AbstractService<M extends BaseEntity> implements BaseService<M> {


    @Autowired
    private BaseDao<M> baseDao;

    @Override
    public void flush() {
        baseDao.flush();
    }
    @Override
    public void delete(String[] ids) {
        Arrays.asList(ids).forEach(id ->
                this.delete(id)
        );
    }
    @Override
    public void delete(M m) {
        baseDao.delete(m);
    }
    @Override
    public void delete(String id) {
        baseDao.delete(id);
    }
    @Override
    public void delete(List<? extends M> list){
        baseDao.delete(list);
    }
    @Override
    public void deleteAll(){
        baseDao.deleteAll();
    }

    //保存单个实体
    @Override
    public M save(M m) {
        return baseDao.save(m);
    }
    //保存立马提交
    @Override
    public M saveAndFlush(M m) {
        return baseDao.saveAndFlush(m);
    }
    //批量保存
    @Override
    public List<M> save(List<M> list) {
            return baseDao.save(list);
    }


    //获取所有的
    @Override
    public List<M> findAll() {
        return baseDao.findAll();
    }
    @Override
    public List<M> findAll(Sort sort) {
        return baseDao.findAll(sort);
    }
    @Override
    public List<M> findAll(List<Serializable> list) {
        return baseDao.findAll(list);
    }
    @Override
    public List<M> findAll(Specification<M> specification) {
        return baseDao.findAll(specification);
    }
    @Override
    public Page<M> findAll(Specification<M> specification, Pageable pageable) {
        return baseDao.findAll(specification,pageable);
    }
    @Override
    public List<M> findAll(Specification<M> specification, Sort sort) {
        return baseDao.findAll(specification,sort);
    }
    @Override
    public Page<M> findAll(Pageable pageable) {
        return baseDao.findAll(pageable);
    }
    @Override
    public List<M> findAll(Example<M> example) {
        return baseDao.findAll(example);
    }
    @Override
    public List<M> findAll(Example<M> example, Sort sort) {
        return baseDao.findAll(example,sort);
    }
    @Override
    public Page<M> findAll(Example<M> example, Pageable pageable) {
        return baseDao.findAll(example,pageable);
    }

    //修改实体
    @Override
    public void update(M m) {
        baseDao.saveAndFlush(m);
    }
}
