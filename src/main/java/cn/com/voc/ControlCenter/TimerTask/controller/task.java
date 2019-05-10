package cn.com.voc.ControlCenter.TimerTask.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class task {
    @GetMapping("/line")
    public void printLine(){
        System.out.println("这是一条直线");
    }
}
