package com.ftdd2.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;

import com.ftdd2.common.vo.Result;
import com.ftdd2.domain.ChatRequest;
import com.ftdd2.domain.ChatResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ChatController {
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
}
