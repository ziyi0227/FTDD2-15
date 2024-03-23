package com.ftdd2.controller;


import com.ftdd2.common.vo.Result;
import com.ftdd2.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common")
@Slf4j
@Api(tags = "通用接口")
public class UploadController {
  @Resource
  private AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file){
        try{
            //文件名
            String originalFilename=file.getOriginalFilename();
            //
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

            String objectName= UUID.randomUUID().toString()+extension;
            String filePath=aliOssUtil.upload(file.getBytes(),objectName);
            return Result.success(filePath,"文件上传成功");
        }catch (IOException e) {
            log.error("文件上传失败");

        }
        return Result.fail("文件上传失败");
    }

}
