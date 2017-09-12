package com.skill.framewook.utils;

import com.skill.framewook.code.entity.AbstractEntity;
import com.skill.framewook.code.entity.AbstractVo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VoUtil {

	public static <T> T convter(AbstractEntity form, Class<T> type) {
		try {
			T newInstance = (T) type.newInstance();
			((AbstractVo) newInstance).convert(form);
			return newInstance;
		} catch (Exception e) {
			//throw new Exception("实体对象转换错误。原因：" + e.getMessage());
			return null;
		}
	}

	public static <T> List<T> convterList(Collection<? extends AbstractEntity> src, Class<T> type) {
		ArrayList<T> arrayList = new ArrayList<T>();
		try {
			for (AbstractEntity t : src) {
				T newInstance = (T) type.newInstance();
				((AbstractVo) newInstance).convert(t);
				arrayList.add(newInstance);
			}
		} catch (Exception e) {
			//throw new BaseException("实体对象转换错误,原因：" + e.getMessage());
		}
		return arrayList;
	}


}
