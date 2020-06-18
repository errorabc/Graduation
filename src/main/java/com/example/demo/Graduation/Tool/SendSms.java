package com.example.demo.Graduation.Tool;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Graduation.entity.Result;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

//导入可选配置类
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

// 导入 SMS 模块的 client
import com.tencentcloudapi.sms.v20190711.SmsClient;

// 导入要请求接口对应的 request response 类
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;
//发送短信
@Service
public class SendSms {
    @Value("${TXSecretId}")
    private String TXSecretId;
    @Value("${TXSecretKey}")
    private String TXSecretKey;
    @Value("${AppID}")
    private String AppID;
    @Value("${ModelId}")
    private String ModelId;
    @Value("${QmContent}")
    private String QmContent;

    public Result TxSendMessage(String phone) {

        // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey，见《创建secretId和secretKey》小节
        Credential cred = new Credential(TXSecretId, TXSecretKey);
        // 实例化要请求产品(以cvm为例)的client对象
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setSignMethod(ClientProfile.SIGN_TC3_256);
        SmsClient smsClient = new SmsClient(cred, "ap-chongqing");//第二个ap-chongqing 填产品所在的区
        SendSmsRequest sendSmsRequest = new SendSmsRequest();
        sendSmsRequest.setSmsSdkAppid(AppID);//appId ,见《创建应用》小节
        String[] phones = {"+86" + phone};  //发送短信的目标手机号，可填多个。
        sendSmsRequest.setPhoneNumberSet(phones);
        sendSmsRequest.setTemplateID(ModelId);  //模版id,见《创建短信签名和模版》小节
        sendSmsRequest.setSign(QmContent); //签名内容，不是填签名id,见《创建短信签名和模版》小节
        String flag = "";
        try {
            SendSmsResponse sendSmsResponse = smsClient.SendSms(sendSmsRequest); //发送短信
            String aa = SendSmsResponse.toJsonString(sendSmsResponse);
            JSONObject dxjson = JSONObject.parseObject(SendSmsResponse.toJsonString(sendSmsResponse));
            Iterator iter = dxjson.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                if (entry.getKey().toString().equals("SendStatusSet")) {
                    JSONArray jsonArray = JSONArray.parseArray(entry.getValue().toString());
                    for (int i = 0; i < jsonArray.size(); i++) {
                        if (jsonArray.getJSONObject(i).getString("Message").equals("send success")) {
                            flag = "success";
                        } else {
                            flag = "error";
                        }
                    }
                }
            }

        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        if (flag.equals("success")) {
            return Result.success(1,"发送成功");
        } else {
            return Result.error(0,"发送失败");
        }


    }


}