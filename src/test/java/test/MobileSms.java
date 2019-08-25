/*
 * @(#) MobileSms
 * 版权声明 没有公司的公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2019
 * <br> Company:没有公司的公司
 * <br> @author Taney
 * <br> 2019-08-25 15:13:56
 */

package test;

import java.net.URL;

import com.google.gson.JsonObject;
import com.lymava.commons.http.HttpPost;
import com.lymava.commons.util.JsonUtil;

public class MobileSms {

    //10086
    //public static final String baseUrl = "https://login.10086.cn/";
    //iqiyi
    public static final String baseUrl = "https://passport.iqiyi.com/";
    public static final String phone = "13388222901";

    public static void main(String[] args) {
        String check_account = check_account();
        System.out.println(check_account);
//        String chkNumber = chkNumberAction();
//        System.out.println("-*-*-*-*-");
//        System.out.println(chkNumber);
//        System.out.println("-*-*-*-*-");

//        String loadToken = loadToken();
//        System.out.println("-*-*-*-*-");
//        System.out.println(loadToken);
//        System.out.println("-*-*-*-*-");

//        String sendRandomCodsendRandomCodeAction();
//        System.out.println("-*-*-*-*-");
//        System.out.println(sendRandomCode);
//        System.out.println("-*-*-*-*-");
    }

    /**
     * __NEW: 1
     account: 13388222901
     agenttype: 1
     area_code: 86
     dfp: a06803fc0b476f49edba87d2936bbd467567c7356cba6565b29f4cc21315df9d7f
     fromSDK: 1
     lang:
     ptid: 01010021010000000000
     sdk_version: 1.0.0
     qd_sc: 48bf1ae199fafe70a63830191532f0e0
     * @return
     */
    public static String check_account(){
        String stringUrl = baseUrl + "apis/user/check_account.action";
        JsonObject jsonObject = new JsonObject();
        JsonUtil.addProperty(jsonObject,"__NEW","1");
        JsonUtil.addProperty(jsonObject,"account",phone);
        JsonUtil.addProperty(jsonObject,"agenttype","1");
        JsonUtil.addProperty(jsonObject,"v","86");
        JsonUtil.addProperty(jsonObject,"dfp","a06803fc0b476f49edba87d2936bbd467567c7356cba6565b29f4cc21315df9d7f");
        JsonUtil.addProperty(jsonObject,"fromSDK","1");
        JsonUtil.addProperty(jsonObject,"lang","");
        JsonUtil.addProperty(jsonObject,"ptid","01010021010000000000");
        JsonUtil.addProperty(jsonObject,"sdk_version","1.0.0");
        JsonUtil.addProperty(jsonObject,"qd_sc","48bf1ae199fafe70a63830191532f0e0");
        String request_data = request_data(stringUrl, jsonObject.toString());
        return request_data;
    }

    /**
     * chk number
     * @return
     */
    public static String loadToken() {
        String stringUrl = baseUrl + "loadToken.action";
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userName",phone);
        String request_data = request_data(stringUrl,jsonObject.toString());
        return request_data;
    }

    public static String chkNumberAction() {
        String stringUrl = baseUrl + "chkNumberAction.action";
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userName",phone);
        String request_data = request_data(stringUrl,jsonObject.toString());
        return request_data;
    }

    /**
     * send random code
     * @return
     */
    public static String sendRandomCodeAction() {
        String stringUrl = baseUrl + "sendRandomCodeAction.action";
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userName",phone);
        jsonObject.addProperty("type","01");
        jsonObject.addProperty("channelID","12003");
        String request_data = request_data(stringUrl,jsonObject.toString());
        return request_data;
    }

    public static String request_data(String url , String json) {
        HttpPost hp = null;
        try {

            hp = new HttpPost(new URL(url));

            hp.addParemeter("data", json);
            System.out.println("------");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String result = null;
        try {
            result = hp.getResult();
            System.out.println("******");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
