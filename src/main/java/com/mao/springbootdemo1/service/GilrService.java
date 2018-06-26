package com.mao.springbootdemo1.service;

import com.mao.springbootdemo1.domain.Girl;
import com.mao.springbootdemo1.enums.ResultEnum;
import com.mao.springbootdemo1.exception.GirlException;
import com.mao.springbootdemo1.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GilrService {

    @Autowired
    private GirlRepository girlRepository;
    @Transactional
    public void insertTwo(){
        Girl girlA=new Girl();
        girlA.setCupSize("A");
        girlA.setAge(18);
        girlRepository.save(girlA);

        Girl girlB = new Girl();
        girlB.setCupSize("B");
        girlB.setAge(19);
        girlRepository.save(girlB);
    }

    public  Integer  getAge(Integer id) throws Exception{
        Girl girl=girlRepository.findById(id).get();
        Integer age=girl.getAge();
        if (age<10){
            //返回“你还在上小学吧”
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        }else if(age > 10 && age < 16){
            //返回“你可能还在上初中”
            throw new GirlException(ResultEnum.MINDDLE_SCHOOL);
        }
        //如果大于16岁，加钱
        return 0;
    }
    public Girl findOne(Integer id){
        return girlRepository.findById(id).get();
    }
}
