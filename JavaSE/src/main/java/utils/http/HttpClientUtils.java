package utils.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author mx
 * @create  2021/04/08 14:14
 * @description http工具类
 */
public class HttpClientUtils {

    private transient static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
    // 默认字符集
    public static final String CHARSET = "UTF-8";
    private static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";
    private static final String CONTENT_TYPE_XML = "text/xml;charset=UTF-8";
    private static final String APPLICATION_JSON = "application/json";

    /**
     * 发送get请求
     * @param url
     * @return
     */
    public static String get(String url) {
        return get(url, null);
    }

    /**
     * 发送get请求
     * @param url
     * @return
     */
    public static String get(String url, Map<String, String> params) {
        String res = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet httpGet = httpGetHandler(url, params);
            res = execute(httpClient, httpGet);
        } finally {
            closeQuietly(httpClient);
        }
        return res;
    }

    /**
     * 发送post请求
     * @param url
     * @return
     */
    public static String post(String url) {
        return post(url, null);
    }

    /**
     * 发送post请求
     * @param url    post url
     * @param params post参数
     * @return
     */
    public static String post(String url, Map<String, String> params) {
        String res = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = httpPostHandler(url, params);
            res = execute(httpClient, httpPost);
        } finally {
            closeQuietly(httpClient);
        }
        return res;
    }

    /**
     * post json数据
     * @param url
     * @param jsonStr
     * @return
     */
    public static String postJson(String url, String jsonStr) {
        String res = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity stringEntity;
            try {
                stringEntity = new StringEntity(jsonStr, CHARSET);
                stringEntity.setContentEncoding(CHARSET);
                stringEntity.setContentType(APPLICATION_JSON);
            } catch (UnsupportedCharsetException e) {
                return null;
            }
            httpPost.setHeader("Content-Type", CONTENT_TYPE_JSON);
            httpPost.setEntity(stringEntity);
            res = execute(httpClient, httpPost);
        } finally {
            closeQuietly(httpClient);
        }
        return res;
    }

    /**
     * post xml数据
     * @param url
     * @param xmlStr
     * @return
     */
    public static String postXml(String url, String xmlStr) {
        String res = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
            StringEntity postEntity = new StringEntity(xmlStr, CHARSET);
            httpPost.addHeader("Content-Type", CONTENT_TYPE_XML);
            httpPost.setEntity(postEntity);
            res = execute(httpClient, httpPost);
        } finally {
            closeQuietly(httpClient);
        }
        return res;
    }

    /**
     * 配置Post请求URL,传参
     * @param url
     * @param params
     * @return
     */
    private static HttpGet httpGetHandler(String url, Map<String, String> params) {
        if(params != null && params.size()>0) {
            UrlEncodedFormEntity entity = getUrlEncodedFormEntity(params);
            if (entity != null) {
                try {
                    url += "?" + EntityUtils.toString(entity);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        HttpGet httpGet = new HttpGet(url);
        return httpGet;
    }

    /**
     * 配置Post请求URL,传参
     * @param url
     * @param params
     * @return
     */
    private static HttpPost httpPostHandler(String url, Map<String, String> params) {
        HttpPost httpPost = new HttpPost(url);
        if (params!=null && params.size()>0) {
            UrlEncodedFormEntity entity = getUrlEncodedFormEntity(params);
            if (entity != null) {
                httpPost.setEntity(entity);
            }
        }
        return httpPost;
    }

    /**
     * 拼接请求参数
     * @param params
     * @return
     */
    private static UrlEncodedFormEntity getUrlEncodedFormEntity(Map<String, String> params) {
        if (params != null ) {
            List<NameValuePair> list = new ArrayList<>(params.size());
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String value = entry.getValue();
                if (value != null) {
                    list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
            }
            if (list.size() > 0) {
                try {
                    return new UrlEncodedFormEntity(list, CHARSET);
                } catch (UnsupportedEncodingException e) {
                    logger.error("参数解析错误："+e.getMessage());
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * 执行get或者post请求
     * @param httpClient
     * @param httpGetOrPost
     * @return
     */
    private static String execute(CloseableHttpClient httpClient, HttpUriRequest httpGetOrPost) {
        String res = null;
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGetOrPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode != 200){
                httpGetOrPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            res = EntityUtils.toString(entity, CHARSET);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(response);
        }
        return res;
    }

    /**
     * 关闭连接
     * @param httpClient
     */
    private static void closeQuietly(CloseableHttpClient httpClient) {
        if (httpClient != null) {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 关闭连接
     * @param response
     */
    public static void closeQuietly(CloseableHttpResponse response) {
        if (response != null) {
            try {
                try {
                    EntityUtils.consume(response.getEntity());
                } finally {
                    response.close();
                }
            } catch (IOException var5) {
                ;
            }
        }

    }

}
