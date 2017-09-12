package com.skill.framewook.code.entity;

import java.io.Serializable;


public class AjaxResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Boolean success;//是否成功
	
	private String message;//提示信息
	
	private Object content;	//数据
	
	public AjaxResponse (){
	}
	
	public AjaxResponse (boolean success,String message){
		this.success=success;
		this.message=message;
	}
	
	public AjaxResponse (boolean success,String message,Boolean autoPrompt){
		this.success=success;
		this.message=message;
	}
	
	public AjaxResponse(boolean success,Object content){
		this.success=success;
		this.content=content;
	}
	
	public AjaxResponse(boolean success,String message,Object content){
		this.success=success;
		this.message=message;
		this.content=content;
	}
	public AjaxResponse(boolean success,String message,Object content,Boolean autoPrompt){
		this.success=success;
		this.message=message;
		this.content=content;
	}
	
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

}
