package com.mao.springbootdemo1.controller;
import com.mao.springbootdemo1.aspect.HttpAspect;
import com.mao.springbootdemo1.domain.Girl;
import com.mao.springbootdemo1.domain.Result;
import com.mao.springbootdemo1.properties.GirlProperties;
import com.mao.springbootdemo1.repository.GirlRepository;
import com.mao.springbootdemo1.service.GilrService;
import com.mao.springbootdemo1.utils.RsultUtil;
import com.mysql.jdbc.util.ResultSetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class HelloController {
//    @Autowired
//    private GirlProperties girlProperties;
//    //@RequestMapping(value = {"/hello","/hi"},method = RequestMethod.GET)
//    @GetMapping(value = "/say")
////    public String say(@PathVariable("id") Integer id) {
//    public String say(@RequestParam(value = "id",required = false,defaultValue = "0") Integer myId){
//        return "id:" + myId;
//        //return girlProperties.getCupSize();
//    }
    private final static Logger logger=LoggerFactory.getLogger(HttpAspect.class);

    @Autowired
    private GirlRepository girlRepository;
    @Autowired
    private GilrService girlService;
    /**
     * 查询所有女生列表
     * @return
     */
    @GetMapping(value = "/girls")
    public List<Girl> girlList(){
        logger.info("girlList");
        return girlRepository.findAll();
    }

    /**
     * 添加一个女生
     * @return
     */
    @PostMapping(value ="/girls")
    public Result<Girl> girlAdd(@Valid Girl girl, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return RsultUtil.erro(1,bindingResult.getFieldError().getDefaultMessage());
        }
        girl.setCupSize(girl.getCupSize());
        girl.setAge(girl.getAge());
        return RsultUtil .success(girlRepository.save(girl));
    }

    /**
     * 查询一个女生
     */
    @GetMapping(value ="/girls/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id){
        return girlRepository.findById(id).get();
    }
    /**
     * 更新
     */
    @PutMapping(value ="/girls/{id}")
    public Girl girlUpdate(@PathVariable("id") Integer id,
                           @RequestParam("cupSize") String cupSize,
                           @RequestParam("age") Integer age){
        Girl girl = new Girl();
        girl.setId(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return girlRepository.save(girl);

    }
    /**
     * 删除
     */
    @DeleteMapping(value ="/girls/{id}")
    public void girlDelete(@PathVariable("id") Integer id){
        girlRepository.deleteById(id);
    }

    /**
     * 根据年龄查询
     * @param age
     * @return
     */
    @GetMapping(value = "/girls/age/{age}")
    public List<Girl> girlListByAge(@RequestParam("age") Integer age){

        return girlRepository.findByAge(age);
    }

    @PostMapping(value = "/girls/two")
    public void girlTwo(){
        girlService.insertTwo();
    }

    @GetMapping(value = "girls/getAge/{id}")
    public  void  getAge(@PathVariable("id") Integer id) throws Exception{
        Integer result=girlService.getAge(id);
       }
}
