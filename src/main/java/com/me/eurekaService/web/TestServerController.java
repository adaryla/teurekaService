package com.me.eurekaService.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

    /**
     * getDate
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getDate")
    @ResponseBody
    public JSONObject getDate(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String testJson = request.getParameter("testJson");
        System.out.println("id=" + id + " testJson=" + testJson);
        JSONObject obj = new JSONObject();
        obj.put("names", "getDate");
        obj.put("updateTime", DataUtils.formatDateTime(new Date()));
        return obj;
    }

    /**
     * 测试使用2
     * @param id
     * @param testJson
     * @return
     */
    @RequestMapping("/testJson")
    @ResponseBody
    public JSONObject getDate(String id, String testJson) {
        System.out.println("id=" + id + " testJson=" + testJson);
        JSONObject obj = JSON.parseObject(testJson);
        obj.put("names", "getDate");
        obj.put("branche", "branche_0517");
        obj.put("updateTime", DataUtils.formatDateTime(new Date()));
        return obj;

    }
}
