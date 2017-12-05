package com.me.eurekaService.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.me.eurekaService.util.DataUtils;

@RestController
public class TestServerController {

    /**
     * 测试服务开启
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/index")
    @ResponseBody
    public String testIndex(HttpServletRequest request, HttpServletResponse response) {
        return "server is startup." + DataUtils.formatDateTime(new Date());
    }
}
