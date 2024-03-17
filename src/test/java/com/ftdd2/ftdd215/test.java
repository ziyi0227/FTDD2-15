package com.ftdd2.ftdd215;


import java.io.File;

import com.ftdd2.domain.entity.Resume;
import com.ftdd2.utils.XinUtils;
import lombok.Data;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

/**
 * 小忻智能工具类
 */
@Data
public class test {
    public static final String CLIENT_ID = "37c318f0-e283-11ee-95b3-47a2a2539ac7";
    public static final String CLIENT_SECRET= "f99f9dc2-c81c-47bf-9767-7c926bf010fa";

    public static void testResumeParser(String url, String fname, String client_id, String client_secret) throws Exception {
        byte[] bytes = org.apache.commons.io.FileUtils.readFileToByteArray(new File(fname));
        String data = new String(Base64.encodeBase64(bytes), Consts.UTF_8);

        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(data, Consts.UTF_8));

        // 设置头字段
        httpPost.setHeader("id", client_id);
        httpPost.setHeader("secret", client_secret);
        httpPost.addHeader("content-type", "application/json");

        // 设置内容信息
        JSONObject json = new JSONObject();
        json.put("file_name", fname);   // 文件名
        json.put("resume_base", data); // 经base64编码过的文件内容
        StringEntity params = new StringEntity(json.toString());
        httpPost.setEntity(params);

        // 发送请求
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(httpPost);

        // 处理返回结果
        String resCont = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
        //System.out.println(resCont);

        JSONObject res = new JSONObject(resCont);
        System.out.println(res.toString(4));
    }

    @Test
    public void test1() throws Exception {
        String file="F:\\1365690_1550161297391_45CB5123792B25CBEEF6D52E5249D85F.png";
        Resume resume = XinUtils.parseResume(file);
//        String url = "http://api.xiaoxizn.com/v1/parser/parse_base?avatar=1&handle_image=1&rawtext=1&parse_mode=fast";
//        testResumeParser(url,file,XinUtils.CLIENT_ID,XinUtils.CLIENT_SECRET);
        System.out.println(resume);
    }
}

