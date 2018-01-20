package funnellm.tipping.utils.http;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import static funnellm.tipping.utils.constants.Constants.*;

public class HttpUtil {

    public HttpUtil() {

    }

    public String sendPostRequest(String url, String appKey, String ssoToken, ResponseHandler<String> responseHandler,
                                  String param) {
        HttpPost post = new HttpPost(url);
        setupRequestHeadersAndConfig(post, appKey, ssoToken);

        post.setEntity(new StringEntity(param, ENCODING_UTF8));

        return makeRequest(post, responseHandler);
    }

    public String sendGetRequest(String url, String appKey, String ssoToken, ResponseHandler<String> responseHandler) {
        HttpGet get = new HttpGet(url);
        setupRequestHeadersAndConfig(get, appKey, ssoToken);

        return makeRequest(get, responseHandler);
    }

    private void setupRequestHeadersAndConfig(HttpRequestBase request, String appKey, String sessionToken) {
        request.setHeader(HTTP_HEADER_CONTENT_TYPE, APPLICATION_JSON);
        request.setHeader(HTTP_HEADER_ACCEPT, APPLICATION_JSON);
        request.setHeader(HTTP_HEADER_ACCEPT_CHARSET, ENCODING_UTF8);
        if (appKey != null) {
            request.setHeader(HTTP_HEADER_X_APPLICATION, appKey);
        }
        if (sessionToken != null) {
            request.setHeader(HTTP_HEADER_X_AUTHENTICATION, sessionToken);
        }

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(TIMEOUT)
                .setSocketTimeout(TIMEOUT)
                .build();
        request.setConfig(requestConfig);
    }

    private String makeRequest(HttpRequestBase request, ResponseHandler<String> responseHandler) {
        String response = null;
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            response = httpClient.execute(request, responseHandler);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return response;
    }
}