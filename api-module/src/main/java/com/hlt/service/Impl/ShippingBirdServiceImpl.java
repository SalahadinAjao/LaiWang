package com.hlt.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hlt.service.ShippingBirdService;
import com.hlt.utils.MD5;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @Author: houlintao
 * @Date:2020/7/15 下午12:27
 * @email 437547058@qq.com
 * @Version 1.0
 * 快递鸟服务类，内部封装了调用快递鸟查询快递信息的接口方法，此类中的所有方法如MD5加密方法， base64加密方法等
 * 都由快递鸟提供，详情http://www.kdniao.com/documents-demo
 */
@Service
public class ShippingBirdServiceImpl implements ShippingBirdService {

    //商户ID，由快递鸟提供
    private String EBusinessID = "";
    //电商加密私钥，快递鸟提供，注意保管，不要泄漏
    private String AppKey = "";
    //获取快递轨迹信息的请求url
    private String ReqURL = "http://api.kdniao.com/Ebusiness/EbusinessOrderHandle.aspx";
    //测试地址
    private String TestURL = "http://sandboxapi.kdniao.com:8080/kdniaosandbox/gateway/exterfaceInvoke.json";

    /**
     * 通过json方式追踪订单物流信息
     * @param expCode 快递公司代码，如顺丰的代码就是SF;
     * @param expNo 快递单号
     */
    @Override
    public List getOrderTracesByJson(String expCode, String expNo) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //使用一个list对象持有结果数据，内部嵌套一个map
        List<Map<String,Object>> resultList = new ArrayList();

        /**
         * 根据官方要求，请求内容需进行URL(utf-8)编码，请求内容要设置为JSON格式，须和DataType一致
         * OrderCode:订单编号;
         * ShipperCode:快递公司编码,必填，对应京东的青龙配送编码，也叫商家编码，
         *             格式：数字＋字母＋数字，9 位数字加一个字母，共10 位,
         *             举例：001K123450；ShipperCode 为SF，且快递单号非快递鸟渠道返回时，必填，
         *             对应收件人/寄件人手机号后四位；ShipperCode 为SF，且快递单号为快递鸟渠道返回时，
         *             不填；ShipperCode 为其他快递时，不填
         * LogisticCode:物流单号;
         */
        String requestData = "{'OrderCode':'','ShipperCode':'" + expCode + "','LogisticCode':'" + expNo + "'}";
        Map<String,String> param = new HashMap<String, String>();

        /**
         * 把(请求内容(未编码)+AppKey)进行MD5加密，然后Base64编码，最后 进行URL(utf-8)编码
         */
        String urlEncoder = urlEncoder(requestData, "UTF-8");
        param.put("RequestData",urlEncoder);
        param.put("EBusinessID", EBusinessID);
        param.put("RequestType", "1002");
        //数据签名
        String dataSign = encrypt(requestData, AppKey, "UTF-8");
        param.put("DataSign",urlEncoder(dataSign,"UTF-8"));
        param.put("DataType", "2");

        String postResult = sendPost(ReqURL, param);
        //解析返回的结果
        JSONObject jsonObject = (JSONObject) JSONObject.parse(postResult);

        if (jsonObject != null){
            JSONArray jsonArray = jsonObject.getJSONArray("Traces");
            for (Object object:jsonArray){
                JSONObject jsonObj = (JSONObject) object;
                Map<String,Object> temp = new HashMap<>();
                temp.put("AcceptTime",jsonObj.getString("AcceptTime"));
                temp.put("AcceptStation",jsonObj.getString("AcceptStation"));
                resultList.add(temp);
            }
        }
        return resultList;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        ShippingBirdServiceImpl birdService = new ShippingBirdServiceImpl();

        List tracesByJson = birdService.getOrderTracesByJson("SF", "SF1023459852");

        System.out.println(tracesByJson);

        String old = "http:www.baidu.com";
        String md5 = birdService.MD5(old, "UTF-8");
        System.out.println("md5 = " + md5);
        byte[] bytes = md5.getBytes("UTF-8");
        System.out.println("bytes = " + bytes);
        for (int i=0;i<bytes.length;i++){
            System.out.println("bytes[i] = " + bytes[i]);
        }
        String encrypt = birdService.encrypt(old,"","UTF-8");
        System.out.println("encrypt = " + encrypt);
    }

    /**
     * @param content 待加密的内容
     * @param keyValue AppKey
     * @param charset 加密使用的字符集
     */
    @Override
    public String encrypt(String content, String keyValue, String charset) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (keyValue != null){
            //把(请求内容(未编码)+AppKey)进行MD5加密
            String md5Str = MD5(content + keyValue, charset);
            //然后Base64编码
            String base64Str = base64(md5Str, charset);
            return base64Str;
        }

        String md5 = MD5(content, charset);
        String base64 = base64(md5, charset);
        return base64;
    }

    /**
     * @param str 使用MD5加密的字符串
     * @param charset 加密使用的字符集
     */
    @Override
    public String MD5(String str, String charset) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        //使用指定的字节更新摘要
        md5.update(str.getBytes(charset));
        //获取密文
        byte[] bytes = md5.digest();

        StringBuffer stringBuffer = new StringBuffer(32);

        /**
         * 在这里将MD5加密后的密文再一次转换
         */
        for (int i=0;i<bytes.length;i++){
            /**
             * 在涉及到 byte 类型向 int 类型转化的时候使用 & 0xff 按未与操作
             * 保持二进制补码的一致性，防止出错。
             * 这里bytes[i]是占据8个二进制位,int占据32位，需要补位24位
             * 这里是将 bytes 数组中的每一位的 byte 数据转换为 int
             */
            int intVal = bytes[i] & 0xff;
            //0xf换算成十进制是15，即 intVal <=15
            if (intVal <= 0xf){
                //是字符的"0" 即当 intVal <= 15时就往 stringBuffer中存“0”，注意是字符串的0，不是 byte 或者 int 类型的0
                stringBuffer.append("0");
            }
            //intVal>15就将此int值转化为16进制的字符，如aa
            stringBuffer.append(Integer.toHexString(intVal));
        }
        return stringBuffer.toString().toLowerCase();
    }

    /**
     * @param str 待编码的字符串
     * @param charset 编码的字符集
     * Base64 是一种将二进制数据编码的方式，正如
     * UTF-8和UTF-16是将文本数据编码的方式一样，所以如果你需要将二进制数据编码为文本数据，那么Base64可以实现这样的需求
     */
    @Override
    public String base64(String str, String charset) throws UnsupportedEncodingException {

        String base64Encode = base64Encode(str.getBytes(charset));

        return base64Encode;
    }

    /**
     * 这里使用一个字符数组保存特定字符以供后面的移位算法取值
     */
    private static char[] base64EncodeChars = new char[]{
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '+', '/'
    };

    public static String base64Encode(byte[] data) {
        //声明一个线程安全的 StringBuffer作为字符容器
        StringBuffer sb = new StringBuffer();
        //传入的字符数组的长度
        int len = data.length;
        int i = 0;
        int b1, b2, b3;
        while (i < len) {
            /**
             * 在涉及到 byte 类型向 int 类型转化的时候使用 & 0xff 按未与操作
             * 保持二进制补码的一致性。
             */
            b1 = data[i++] & 0xff;
            if (i == len) {
                //无符号数右移忽略符号位，空位都以0补齐，只是对32位和64位的值有意义
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
                sb.append("==");
                break;
            }
            b2 = data[i++] & 0xff;
            if (i == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
                sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
                sb.append("=");
                break;
            }
            b3 = data[i++] & 0xff;
            sb.append(base64EncodeChars[b1 >>> 2]);
            sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
            sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
            sb.append(base64EncodeChars[b3 & 0x3f]);
        }
        return sb.toString();
    }

    /**
     * url解析器,通过指定的解析模式将字符串string
     * 使用 application/x-www-form-urlencoded 格式解析
     * 在接收到请求后服务器知道参数用符号&间隔，如果参数值中需要&则必须对其进行编码，编码格式就是application/x-www-form-urlencoded（
     * 将键值对的参数用&连接起来，如果有空格，将空格转换为+号；有特殊符号，将特殊符号转换为ASCII HEX值；
     * application/x-www-form-urlencoded： 窗体数据被编码为名称/值对，这里是从我们项目的后端向快递鸟的服务器发送请求的，不存在窗体表单数据，
     * 那么我们可以使用一个简单的String作为请求数据，将其解析为名/值对
     */
    @Override
    public String urlEncoder(String str, String charset) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode(str, charset);
        return encode;
    }

    /**
     * 向指定的 URL 发送 post 请求并获取返回值
     * @param url 请求的url地址
     * @param params 参数
     */
    @Override
    public String sendPost(String url, Map<String, String> params) {
        //负责发送请求数据的输出流
        OutputStreamWriter out = null;
        //负责读取返回数据的输入流
        BufferedReader in = null;
        //线程不安全，待定
        StringBuilder result = new StringBuilder();

        try {
            /**
             * 创建一个 URL 对象代表我们即将请求的远程目的url
             */
            URL realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            connection.setDoOutput(true);
            connection.setDoInput(true);

            connection.setRequestMethod("POST");
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //创建通信链接
            connection.connect();
            //获取向此 connection 写入数据的输出流对象，并使用创建的OutputStreamWriter使用utf-8字符集向其中写入数据
            out = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
            // 发送请求参数
            if (params != null){
                StringBuilder param = new StringBuilder();
                for (Map.Entry<String,String> entry:params.entrySet()){
                    if (param.length()>0){
                        param.append("&");
                    }
                    param.append(entry.getKey());
                    param.append("=");
                    param.append(entry.getValue());
                }
                //形如：https://www.baidu.com/?userId=123&password=123
                out.write(param.toString());
            }
            //清空 out 向目标url写入数据
            out.flush();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            String line;

            while ((line = in.readLine())!= null){
                result.append(line);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result.toString();
    }

}
