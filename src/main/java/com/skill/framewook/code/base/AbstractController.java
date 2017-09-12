package com.skill.framewook.code.base;

import com.skill.framewook.code.entity.AbstractEntity;
import com.skill.framewook.code.entity.AbstractVo;
import com.skill.framewook.code.entity.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by CYG on 2017/9/2.
 */
public abstract class AbstractController<M extends AbstractEntity,V extends AbstractVo> extends BaseController<M,V>{

    @Autowired
    private AbstractService<M> abstractService;


    @PostMapping("/create")
    @ResponseBody
    public AjaxResponse create(M m) {
        abstractService.save(m);
        return new AjaxResponse(true,"添加成功");
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public AjaxResponse delete(@PathVariable("id") String id) {
        if(id.contains(","))
            abstractService.delete(id.split(","));
        else
            abstractService.delete(id);
        return new AjaxResponse(true,"删除成功");
    }

    @GetMapping("/findOne/{id}")
    @ResponseBody
    public AjaxResponse findOne(@PathVariable("id") String id) {
        M m = abstractService.findOneById(id);
        return new AjaxResponse(true, super.transformToVo(m));
    }

    @GetMapping("/findAll")
    @ResponseBody
    public AjaxResponse findAll() {
        List<M> list = abstractService.findAll();
        return new AjaxResponse(true, super.transformToVo(list));
    }
}
