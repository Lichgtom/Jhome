package com.jhome.modules.ymx.web.baseController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 控制器基础类
 */
public abstract class baseController {
    protected static final String FORWARD = "forward:";
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected static final String REDIRECT = "redirect:";

    @Value("${jhome.sysproperties.adminPath}")
    protected String adminPath;
    @Value("${jhome.sysproperties.frontPath}")
    protected String frontPath;

    public baseController() {
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception ex){
        return String.format("来自Jhome异常请求消息：%s",ex.toString()) ;
    }
}
