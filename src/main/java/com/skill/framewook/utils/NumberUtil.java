package com.skill.framewook.utils;

import java.math.BigDecimal;

/**
 * 关于数值工具类
 * @author cyg
 *
 */
public class NumberUtil {

	
	/**Double保留2位小数位*/
	@SuppressWarnings("finally")
	public static Double toFixed(Double double1){
		double f1=0.00;
		try {
			if(double1!= null){
				BigDecimal bg = new BigDecimal(double1);  
				f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			}
		} catch (Exception e) {
			//throw new  ServiceException("转换小数位出错");
		}finally{
			return f1;			
		}		
	}
	
	@SuppressWarnings("finally")
	public static Integer stringToInt(String string){
		Integer f1 = 0;
		try {
			f1 = Integer.parseInt(string);
		} catch (Exception e) {
			
		}finally{
			return f1;	
		}
	}
	
	@SuppressWarnings("finally")
	public static Float stringToFloat(String string){
		Float f1 = 0f;
		try {
			f1 = Float.parseFloat(string);
		} catch (Exception e) {
			
		}finally{
			return f1;	
		}
	}
	
	@SuppressWarnings("finally")
	public static Double stringToDouble(String string){
		Double f1 = 0d;
		try {
			f1 = Double.parseDouble(string);
		} catch (Exception e) {
			
		}finally{
			return f1;	
		}
	}
}
