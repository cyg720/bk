package com.skill.framewook.sys.user.service;

import com.skill.framewook.sys.user.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    //测试保存数据
    @Test
    public void deleteAll() throws Exception {
        userService.deleteAll();
    }


    //测试保存数据
    @Test
    public void saveUser() throws Exception {
        User user = new User();
        user.setName("张三2");
        User user1 = userService.save(user);
        System.out.println("user1>>>>"+user1);

        user.setName("张三111");
        User user2 = userService.saveAndFlush(user);
        System.out.println("user1>>>>"+user2);
    }

    //测试保存数据
    @Test
    public void saveUser1() throws Exception {
        List<User> users = new ArrayList<>();
        for (int i=1;i<30;i++){
            User user = new User();
            user.setName("张三"+i);
            user.setAge(i);
            users.add(user);
        }
        for (User u : userService.save(users)) {
            System.out.println(u.getId());
        }

    }


    //测试获取所有的数据
    @Test
    public void getUser() throws Exception {
        for (User user : userService.findAll()) {
            System.out.println(user.getName());
        }
    }
    //测试获取所有的数据 排序
    @Test
    public void getUser1() throws Exception {
        Sort s = new Sort(Sort.Direction.ASC,"age");
        for (User user : userService.findAll(s)) {
            System.out.println(user.getName());
        }
    }

    //测试获取所有的数据 获取指定的主键的集合
    @Test
    public void getUser2() throws Exception {
        List<Serializable> ls = new ArrayList<>();
        ls.add("DzIqZjIk2riLVxZIqom");
        ls.add("ik2Yuz2WcXS4klcnMqk");
        ls.add("LFc8QfCodCoxxmpmJNl");
        for (User user : userService.findAll(ls)) {
            System.out.println(user.getName());
        }
    }

    //测试获取所有的数据 加条件
    @Test
    public void getUser3() throws Exception {
        Specification<User> specification  = new Specification<User>(){
            @Override
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
            	
                Predicate p =  root.isNotNull();
                return null;
            }
        };
        for (User user : userService.findAll(specification)) {
            System.out.println(user.getName());
        }
    }

}