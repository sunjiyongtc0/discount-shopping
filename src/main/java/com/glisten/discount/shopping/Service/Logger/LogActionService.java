package com.glisten.discount.shopping.Service.Logger;


import com.glisten.discount.shopping.Domain.TLoggerAction;
import com.glisten.discount.shopping.Mapper.TLoggerActionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogActionService {

    @Autowired
    private TLoggerActionMapper tLoggerActionMapper;

    public int  SaveLog(TLoggerAction loggerAction){
        return  tLoggerActionMapper.insert(loggerAction);
    }
}
