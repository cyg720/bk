package com.skill.framewook.code.base;

import com.skill.framewook.code.entity.BaseEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by CYG on 2017/9/1.
 */
@NoRepositoryBean
public interface BaseDao<T extends BaseEntity> extends JpaRepository<T,Serializable>,
        JpaSpecificationExecutor<T>{

    @Override
    void delete(Serializable serializable);
    @Override
    void delete(T t);
    @Override
    void delete(Iterable<? extends T> iterable);
    @Override
    void deleteAll();

    @Override
    <S extends T> S save(S s);
    @Override
    <S extends T> S saveAndFlush(S s);
    @Override
    <S extends T> List<S> save(Iterable<S> iterable);


    @Override
    List<T> findAll();
    @Override
    List<T> findAll(Sort sort);
    @Override
    List<T> findAll(Iterable<Serializable> iterable);
    @Override
    List<T> findAll(Specification<T> specification);
    @Override
    Page<T> findAll(Specification<T> specification, Pageable pageable);
    @Override
    List<T> findAll(Specification<T> specification, Sort sort);
    @Override
    Page<T> findAll(Pageable pageable);
    @Override
    <S extends T> List<S> findAll(Example<S> example);
    @Override
    <S extends T> List<S> findAll(Example<S> example, Sort sort);
    @Override
    <S extends T> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    T getOne(Serializable serializable);
    @Override
    <S extends T> S findOne(Example<S> example);
    @Override
    T findOne(Specification<T> specification);
    @Override
    T findOne(Serializable serializable);



    @Override
    void flush();


    @Override
    long count(Specification<T> specification);
    @Override
    long count();
    @Override
    <S extends T> long count(Example<S> example);

    @Override
    boolean exists(Serializable serializable);
    @Override
    <S extends T> boolean exists(Example<S> example);
}
