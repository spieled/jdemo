package com.studease.jdemo.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * HTTP 请求工具类 基于 org.apache.httpcomponents.httpclient:4.5.2
 *
 * @author : liushaoping
 * @version : 1.0.0
 * @date : 2016/03/29
 */
public class HttpUtil {
    private static final int MAX_TIMEOUT = 7000;
    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;

    static {
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());
        connMgr.setValidateAfterInactivity(3000);

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        requestConfig = configBuilder.build();
    }

    /**
     * 发送 TRACE 请求（HTTP），不带输入数据
     *
     * @param url
     * @return
     */
    public static String doTrace(String url) {
        return doTrace(url, new HashMap<>());
    }

    /**
     * 发送 TRACE 请求（HTTP），K-V形式
     *
     * @param url
     * @param params
     * @return
     */
    public static String doTrace(String url, Map<String, Object> params) {
        return doRequest(HttpTrace.class, url, params);
    }

    /**
     * 发送 PATCH 请求（HTTP），不带输入数据
     *
     * @param url
     * @return
     */
    public static String doPatch(String url) {
        return doPatch(url, new HashMap<>());
    }

    /**
     * 发送 PATCH 请求（HTTP），K-V形式
     *
     * @param url
     * @param params
     * @return
     */
    public static String doPatch(String url, Map<String, Object> params) {
        return doRequest(HttpPatch.class, url, params);
    }

    /**
     * 发送 OPTIONS 请求（HTTP），不带输入数据
     *
     * @param url
     * @return
     */
    public static String doOptions(String url) {
        return doOptions(url, new HashMap<>());
    }

    /**
     * 发送 OPTIONS 请求（HTTP），K-V形式
     *
     * @param url
     * @param params
     * @return
     */
    public static String doOptions(String url, Map<String, Object> params) {
        return doRequest(HttpOptions.class, url, params);
    }

    /**
     * 发送 HEAD 请求（HTTP），不带输入数据
     *
     * @param url
     * @return
     */
    public static String doHead(String url) {
        return doHead(url, new HashMap<>());
    }

    /**
     * 发送 HEAD 请求（HTTP），K-V形式
     *
     * @param url
     * @param params
     * @return
     */
    public static String doHead(String url, Map<String, Object> params) {
        return doRequest(HttpHead.class, url, params);
    }

    /**
     * 发送 DELETE 请求（HTTP），不带输入数据
     *
     * @param url
     * @return
     */
    public static String doDelete(String url) {
        return doDelete(url, new HashMap<>());
    }

    /**
     * 发送 DELETE 请求（HTTP），K-V形式
     *
     * @param url
     * @param params
     * @return
     */
    public static String doDelete(String url, Map<String, Object> params) {
        return doRequest(HttpDelete.class, url, params);
    }

    /**
     * 发送 PUT 请求（HTTP），不带输入数据
     *
     * @param url
     * @return
     */
    public static String doPut(String url) {
        return doPut(url, new HashMap<>());
    }

    /**
     * 发送 PUT 请求（HTTP），K-V形式
     *
     * @param url
     * @param params
     * @return
     */
    public static String doPut(String url, Map<String, Object> params) {
        return doRequest(HttpPut.class, url, params);
    }

    /**
     * 发送 GET 请求（HTTP），不带输入数据
     *
     * @param url
     * @return
     */
    public static String doGet(String url) {
        return doGet(url, new HashMap<>());
    }

    /**
     * 发送 GET 请求（HTTP），K-V形式
     *
     * @param url
     * @param params
     * @return
     */
    public static String doGet(String url, Map<String, Object> params) {
        return doRequest(HttpGet.class, url, params);
    }

    /**
     * 发送 POST 请求（HTTP），不带输入数据
     *
     * @param apiUrl
     * @return
     */
    public static String doPost(String apiUrl) {
        return doPost(apiUrl, new HashMap<>());
    }

    /**
     * 发送 POST 请求（HTTP），K-V形式
     *
     * @param apiUrl API接口URL
     * @param params 参数map
     * @return
     */
    public static String doPost(String apiUrl, Map<String, Object> params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);
            List<NameValuePair> pairList = new ArrayList<>(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry
                        .getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
            response = httpClient.execute(httpPost);
            System.out.println(response.toString());
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 POST 请求（HTTP），JSON形式
     *
     * @param apiUrl
     * @param json   json对象
     * @return
     */
    public static String doPostJson(String apiUrl, Object json) {
        return doRequestJson(HttpPost.class, apiUrl, json);
    }

    /**
     * 发送 PUT 请求（HTTP），JSON形式
     *
     * @param apiUrl
     * @param json   json对象
     * @return
     */
    public static String doPutJson(String apiUrl, Object json) {
        return doRequestJson(HttpPut.class, apiUrl, json);
    }

    /**
     * 发送 PATCH 请求（HTTP），JSON形式
     *
     * @param apiUrl
     * @param json   json对象
     * @return
     */
    public static String doPatchJson(String apiUrl, Object json) {
        return doRequestJson(HttpPatch.class, apiUrl, json);
    }

    /**
     * 发送 SSL POST 请求（HTTPS），K-V形式
     *
     * @param apiUrl API接口URL
     * @param params 参数map
     * @return
     */
    public static String doPostSSL(String apiUrl, Map<String, Object> params) {
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        String httpStr = null;

        try {
            httpPost.setConfig(requestConfig);
            List<NameValuePair> pairList = new ArrayList<>(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry
                        .getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("utf-8")));
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 SSL POST 请求（HTTPS），JSON形式
     *
     * @param apiUrl API接口URL
     * @param json   JSON对象
     * @return
     */
    public static String doPostSSL(String apiUrl, Object json) {
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        String httpStr = null;

        try {
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");//解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 创建SSL安全连接
     *
     * @return
     */
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

                @Override
                public void verify(String host, SSLSocket ssl) throws IOException {
                }

                @Override
                public void verify(String host, X509Certificate cert) throws SSLException {
                }

                @Override
                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
                }
            });
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return sslsf;
    }

    public static String constructQueryString(Map<String, Object> params) {
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0)
                param.append("?");
            else
                param.append("&");
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        return param.toString();
    }

    public static String doRequest(Class<? extends HttpRequestBase> httpRequestClass, String url, Map<String, Object> params) {
        HttpRequestBase httpRequest;
        String apiUrl = url;
        String param = constructQueryString(params);
        apiUrl += param;
        String result = null;
        try {
            httpRequest = httpRequestClass.getConstructor(String.class).newInstance(apiUrl);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return "";
        }

        HttpClient httpclient = HttpClientBuilder.create().build();
        try {
            HttpResponse response = httpclient.execute(httpRequest);
            int statusCode = response.getStatusLine().getStatusCode();

            System.out.println("执行状态码 : " + statusCode);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream inputStream = entity.getContent();
                result = IOUtils.toString(inputStream, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String doRequestJson(Class<? extends HttpEntityEnclosingRequestBase> httpRequestClass, String apiUrl, Object json) {
        return doRequestJson(httpRequestClass, apiUrl, json, new HashMap<>());
    }

    public static String doRequestJson(Class<? extends HttpEntityEnclosingRequestBase> httpRequestClass, String apiUrl, Object json, Map<String, String> headers) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpEntityEnclosingRequestBase httpRequest;
        String result = null;
        try {
            httpRequest = httpRequestClass.getConstructor(Void.class).newInstance();
            httpRequest.setURI(URI.create(apiUrl));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return "";
        }


        try {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpRequest.setHeader(entry.getKey(), entry.getValue());
            }
            httpRequest.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");//解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpRequest.setEntity(stringEntity);
            response = httpClient.execute(httpRequest);
            HttpEntity entity = response.getEntity();
            System.out.println(response.getStatusLine().getStatusCode());
            result = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 测试方法
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

    }
}