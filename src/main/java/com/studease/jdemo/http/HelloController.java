package com.studease.jdemo.http;

import com.studease.jdemo.annotation.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: liushaoping
 * Date: 2016/3/21.
 */
@RestController
public class HelloController {
    @RequestMapping("/hello.do")
    @Logger(duration = true, parameter = true, result = true)
    public @ResponseBody String hello() {
        return "hello world !!!!";
    }
}
