package com.skill.framewook.sys.user.service;

import com.skill.framewook.code.base.AbstractService;
import com.skill.framewook.sys.user.entity.User;
import org.springframework.stereotype.Service;

/**
 * Created by CYG on 2017/9/1.
 */
@Service
public class UserService extends AbstractService<User>{

   /* @Autowired
    private UserDao userDao;*/

    /*public User getUser(String id){
       return userDao.findOne(id);
    }

    public User saveUser(User user){
        return userDao.save(user);
    }

    public Page<User> getUserPage(Pageable pageable){
        return userDao.findAll(pageable);
    }

    public Page<User> getUserPage(Example<User> example, Pageable pageable){
        return userDao.findAll(example,pageable);
    }*/
}
