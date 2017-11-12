package com.skill.framewook.code.base;

import com.skill.framewook.code.entity.BaseEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;

/**
 * Created by CYG on 2017/9/2.
 */
public interface BaseService<M extends BaseEntity> {

    void flush();

    void delete(String id);

    void delete(String[] id);

    void delete(M m);

    void deleteAll();

    void delete(List<? extends M> list);



    M save(M m);

    M saveAndFlush(M m);

    List<M> save(List<M> list);


    List<M> findAll();
    List<M> findAll(Sort sort);
    List<M> findAll(List<Serializable> list);
    List<M> findAll(Specification<M> specification);
    Page<M> findAll(Specification<M> specification, Pageable pageable);
    List<M> findAll(Specification<M> specification, Sort sort);
    Page<M> findAll(Pageable pageable);
    List<M> findAll(Example<M> example);
    List<M> findAll(Example<M> example, Sort sort);
    Page<M> findAll(Example<M> example, Pageable pageable);



    void update(M m);

}
