package com.skill.framewook.sys.user.service;

import com.skill.framewook.sys.user.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void getUser() throws Exception {
        User user = userService.findOneById("C1Qc9dhIJLETR83VWbJ");
        System.out.println(user);
    }

    @Test
    public void saveUser() throws Exception {

        User user = new User();
        user.setName("张三1");
        User user1 = userService.save(user);
        System.out.println("user>>>>"+user1);
    }

    @Test
    public void UserPage() throws Exception{
        Pageable pageable =  new PageRequest(0,5, Sort.Direction.ASC,"createTime");
        List<User> ls = userService.findAll();
        System.out.println(ls);

    }

}