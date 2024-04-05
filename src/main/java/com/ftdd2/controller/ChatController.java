package com.ftdd2.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import com.ftdd2.common.vo.Result;
import com.ftdd2.domain.ChatRequest;
import com.ftdd2.domain.ChatResponse;
import com.ftdd2.domain.entity.Resume;
import com.ftdd2.service.IUsersService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ChatController {
    @Resource
    private IUsersService usersService;
    @PostMapping("/chat")//文档要求使用post请求
    public Result<ChatResponse> chat(String q){
        String url = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";
        String ApiKey = "sk-0ce6f90bbbfb4006b98582ef8bc3438d";

        ChatRequest chatRequest = new ChatRequest(q);
        String json = JSONUtil.toJsonStr(chatRequest);
        //System.out.println(json);//正式发送给api前,查看请求的主要数据情况
        String result = HttpRequest.post(url)
                .header("Authorization","Bearer "+ ApiKey)
                .header("Content-Type","application/json")
                .body(json)
                .execute().body();
        System.out.println(result);
        return Result.success(JSONUtil.toBean(result, ChatResponse.class));
    }

    @PostMapping("/getAssess")//获取能力评估
    public Result<ChatResponse> getAssess(String q){
        Resume resume=usersService.getMyResume();
        String url = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";
        String ApiKey = "sk-0ce6f90bbbfb4006b98582ef8bc3438d";
        StringBuffer quest = new StringBuffer(resume.toString()+"这个是简历\n");
        StringBuffer Job =new StringBuffer(q+"这个是招聘信息");
        quest.append(",你能给这个简历信息以及招聘信息做出一个评估吗，分点打分，满分十分");
        StringBuffer total=new StringBuffer();
        total.append(quest).append(Job);
        ChatRequest chatRequest = new ChatRequest(total.toString());
        String json = JSONUtil.toJsonStr(chatRequest);
        //System.out.println(json);//正式发送给api前,查看请求的主要数据情况
        String result = HttpRequest.post(url)
                .header("Authorization","Bearer "+ ApiKey)
                .header("Content-Type","application/json")
                .body(json)
                .execute().body();
        System.out.println(result);
        return Result.success(JSONUtil.toBean(result, ChatResponse.class));
    }
    @PostMapping("/getSuggestion")
    public Result<ChatResponse> getSuggestion(){
        Resume resume=usersService.getMyResume();
        String url = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";
        String ApiKey = "sk-0ce6f90bbbfb4006b98582ef8bc3438d";
        StringBuffer quest = new StringBuffer(resume.toString());
        quest.append(",你根据着简历给点求职建议呗");
        ChatRequest chatRequest = new ChatRequest(quest.toString());
        String json = JSONUtil.toJsonStr(chatRequest);
        //System.out.println(json);//正式发送给api前,查看请求的主要数据情况
        String result = HttpRequest.post(url)
                .header("Authorization","Bearer "+ ApiKey)
                .header("Content-Type","application/json")
                .body(json)
                .execute().body();
        System.out.println(result);
        return Result.success(JSONUtil.toBean(result, ChatResponse.class));
    }


}
