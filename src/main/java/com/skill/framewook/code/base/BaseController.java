package com.skill.framewook.code.base;

import com.skill.framewook.code.entity.AbstractVo;
import com.skill.framewook.code.entity.BaseEntity;
import com.skill.framewook.code.entity.BaseVo;
import com.skill.framewook.utils.ReflectUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by CYG on 2017/9/2.
 */
public abstract class BaseController<M extends BaseEntity,V extends AbstractVo> {


   // private Class<M> entityClass;
    private Class<V> valueClass;

    protected BaseController() {
       // entityClass = ReflectUtil.findParameterizedType(getClass(), 0);
        valueClass = ReflectUtil.findParameterizedType(getClass(), 1);
    }


    protected V transformToVo(M m) {
        return this.convter(m, valueClass);
    }

    /**
     * po 转vo
     *
     * @param list
     * @return
     */
    protected List<V> transformToVo(List<M> list) {
        return this.convterList(list, valueClass);
    }

    private static <T> T convter(BaseEntity form, Class<T> type) {
        try {

            T newInstance = (T) type.newInstance();
            ((BaseVo) newInstance).convert(form);
            return newInstance;
        } catch (Exception e) {
            throw new RuntimeException("实体对象转换错误。原因：" + e.getMessage());
        }
    }

    private static <T> List<T> convterList(Collection<? extends BaseEntity> src, Class<T> type) {
        ArrayList<T> arrayList = new ArrayList<T>();
        try {
            for (BaseEntity t : src) {
                T newInstance = (T) type.newInstance();
                ((BaseVo) newInstance).convert(t);
                arrayList.add(newInstance);
            }
        } catch (Exception e) {
            //throw new BaseException("实体对象转换错误,原因：" + e.getMessage());
        }
        return arrayList;
    }

}
