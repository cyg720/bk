package com.skill.framewook.sys.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skill.framewook.code.base.AbstractController;
import com.skill.framewook.sys.user.entity.User;
import com.skill.framewook.sys.user.vo.UserVo;

/**
 * 
 * @author cyg
 *	后台用户
 */
@RestController
@RequestMapping("sys/user")
public class UserController extends AbstractController<User, UserVo>{

}
