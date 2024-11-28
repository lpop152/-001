package org.campus.utils;

import com.alibaba.fastjson2.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.Map;


//发送短信，电话号码与验证码都是动态的
public class SendSmsUtil {

    public static boolean sendCode(String telephone, Map<String, String> param) {
        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAI5t7iaXAVGueyk8ZW8t23", "XidL6IoQ5tz9sM8sXPzy56thl2tblY");
        IAcsClient client = new DefaultAcsClient(profile);

        //设置相关固定的参数
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //设置发送相关的参数
        request.putQueryParameter("PhoneNumbers", telephone); //手机号
        request.putQueryParameter("SignName","河南邺东信息咨询有限公司"); //申请阿里云 签名名称
        request.putQueryParameter("TemplateCode","\n" +
                "SMS_475735256"); //申请阿里云 模板code
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));//验证码数据，转换json数据传递

        try {
            //最终发送
            CommonResponse response = client.getCommonResponse(request);
            return response.getHttpResponse().isSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}