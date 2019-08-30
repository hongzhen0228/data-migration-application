package com.data.migration.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hongzhen.cao on 2019/8/30.
 */
@RestController
public class IndexController {

    @GetMapping("index")
    public String hello(){
        return "Hello";
    }
}
