package com.ftdd2.utils;


import cn.hutool.core.date.DateTime;
import com.ftdd2.domain.entity.JobTable;
import com.ftdd2.domain.entity.Resume;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.checkerframework.checker.units.qual.Time;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.File;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XinUtils{

    // 使用id
    public static final String CLIENT_ID = "37c318f0-e283-11ee-95b3-47a2a2539ac7";
    // 密钥
    public static final String CLIENT_SECRET= "f99f9dc2-c81c-47bf-9767-7c926bf010fa";

    public static JSONObject testResumeParser(String url, String fname, String client_id, String client_secret) throws Exception {
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
//        System.out.println(res.toString(4));
//        return res.toString(4);
        return res;

    }


    public static Resume parseResume(String File) throws Exception {
        String url = "http://api.xiaoxizn.com/v1/parser/parse_base?avatar=1&handle_image=1&rawtext=1&parse_mode=fast";//支持图片解析，提取简历头像，提取简历原文本，且使用快速解析模式
//        String fname = "F:\\1365690_1550161297391_45CB5123792B25CBEEF6D52E5249D85F.png";  //替换为您的文件名

       JSONObject obj = testResumeParser(url, File, CLIENT_ID, CLIENT_SECRET);
        return transform(obj);

    }

public static Resume transform(JSONObject o) {
    JSONObject parsingResult = o.getJSONObject("parsing_result");
    JSONObject basicInfoJson = parsingResult.getJSONObject("basic_info");

    String desireJdSalaryId = basicInfoJson.optString("desired_salary", "默认值或其他处理方式");
    String gender = basicInfoJson.optString("gender", "默认值或其他处理方式");
    String age = basicInfoJson.optString("age", "未知"); // 如果age字段不存在或者不是整数，则返回-1
    String name = basicInfoJson.optString("name", "未提供");
    String phoneNumber = basicInfoJson.optString("phone_number", "未提供");
    String liveCity = basicInfoJson.optString("current_location_norm", "未提供");
    String degree = basicInfoJson.optString("degree", "未提供");
    String desireJdType = basicInfoJson.optString("desired_position", "未提供");
    String desireCity = basicInfoJson.optString("expect_location", "未提供");
    String desireJdIndustry = basicInfoJson.optString("desired_industry", "未提供");

// 工作经验
    JSONArray workExperienceArray = parsingResult.getJSONArray("work_experience");
    List<String> workDescriptions = new ArrayList<>();
    for (int i = 0; i < workExperienceArray.length(); i++) {
        JSONObject workExpJson = workExperienceArray.getJSONObject(i);
        String description = workExpJson.optString("description", "未提供");
        workDescriptions.add(description);
    }

// 获奖情况
    JSONObject othersJson = parsingResult.getJSONObject("others");
    JSONArray awardsArray = othersJson.getJSONArray("awards");
    StringBuilder awardDescriptions = new StringBuilder();
    for (int i = 0; i < awardsArray.length(); i++) {
        awardDescriptions.append(awardsArray.getString(i)).append(",");
    }
    if (awardDescriptions.length() > 0) {
        awardDescriptions.setLength(awardDescriptions.length() - 1); // 移除最后一个逗号
    }

// 开始工作年份
    String workStartYearStr = basicInfoJson.optString("work_start_year", "");
    Year startWorkDate;
    if (!workStartYearStr.isEmpty()) {
        startWorkDate = Year.parse(workStartYearStr);
    } else {
        // 提供一个默认值或者不设置该字段（根据业务需求）
        startWorkDate = null;
    }

// 其他可能存在null的字段
    String currentSalaryId = basicInfoJson.optString("current_salary", "未提供");
    String curJdType = basicInfoJson.optString("current_position", "未提供");
    String curIndustry = basicInfoJson.optString("industry", "未提供");
    // 创建并初始化Resume对象
    Resume resume = Resume.builder()
            .desireJdSalaryId(desireJdSalaryId)
            .sex(gender)
            .age(age)
            .name(name)
            .phone(phoneNumber)
            .liveCity(liveCity)
            .degree(degree)
            .desireJdType(desireJdType)
            .desireCity(desireCity)
            .desireJdIndustry(desireJdIndustry)
            .experience(String.join("\n", workDescriptions))
            .startWorkDate(startWorkDate)
            // ... 设置其他字段，如currentSalaryId, curJdType, curIndustry等
            .currentSalaryId((String) basicInfoJson.get("current_salary"))
            .curJdType((String) basicInfoJson.get("current_position"))
            .curIndustry((String) basicInfoJson.get("industry"))
            .build();

    return resume;
}
}















//    public static Resume transform(JSONObject o){
//        JSONObject object=o.getJSONObject("parsing_result");
//        // 基础信息
//
//        Map<String, Object> map = new HashMap<>(object.getJSONObject("basic_info").toMap());
//        //工作经验
//        Map<String, Object> work_experience = new HashMap<>(object.getJSONObject("work_experience").toMap());
//        //社会经验
//        Map<String, Object> social_experience = new HashMap<>(object.getJSONObject("work_experience").toMap());
//        //项目经验
//        Map<String, Object> project_experience = new HashMap<>(object.getJSONObject("work_experience").toMap());
//        //获奖情况
//        Map<String, Object> others = new HashMap<>(object.getJSONObject("awards").toMap());
//        List<String>total=new ArrayList<>();
//        total.add((String) work_experience.get("description"));
//        total.add((String)social_experience.get("description"));
//        total.add((String) project_experience.get("description"));
//        total.add((String)others.get("awards"));
//        Resume resume=new Resume();
//        resume = Resume.builder()
//                .desireJdSalaryId((String) map.get("desired_salary"))
//                .sex((String)map.get("gender"))
//                .age((Integer) map.get("age"))
//                .name((String)map.get("name"))
//                .phone((String)map.get("phone_number"))
//                .liveCity((String) map.get("current_location"))
//                .degree((String)map.get("degree") )
//                .desireJdType((String)map.get("desired_position"))
//                .desireCity((String) map.get("expect_location"))
//                .desireJdIndustry((String)map.get("desired_industry"))
//                .desireJdSalaryId((String)map.get("desired_salary"))
//                .experience(total.toString())
//                .startWorkDate(Year.parse((CharSequence) map.get("work_start_year")))
//                .currentSalaryId((String)map.get("current_salary"))
//                .curJdType((String)map.get("current_position"))
//                .curIndustry((String)map.get("industry"))
//                .build();
//        return resume;
//    }