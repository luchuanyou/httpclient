package com.demo;

import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * 301  redirect: 301 代表永久性转移(Permanently Moved)
 * 302  redirect: 302 代表暂时性转移(Temporarily Moved )
 * 301，302 返回代码，他们代表的是重定向
 * http://m635674608.iteye.com/blog/2257006
 */
public class HttpRequest {

    public static void main(String[] args) {
        try {
            String url = "https://www.montblanc.com/products/us/en/10883.pdp.redirect.html";
            JSONObject json = new JSONObject();
            json.put("name", "name");

            System.out.println("parames:" + json);
            String receive = doPost(url, json.toString());
            System.out.println("receive:" + receive);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行请求
     *
     * @param url
     * @param postData
     * @return String
     */
    public static String doPost(String url, String postData) {
        String result = null;
        HttpPost post = null;
        try {
            HttpClient client = new DefaultHttpClient();
//            HttpParams params = client.getParams();
//            params.setParameter(AllClientPNames.HANDLE_REDIRECTS, true);
            post = new HttpPost(url);

            post.setHeader(HTTP.CONTENT_TYPE, "application/json; charset=UTF-8");
            post.setHeader("Accept", "application/json; charset=UTF-8");

            StringEntity entity = new StringEntity(postData, "UTF-8");
            post.setEntity(entity);

            HttpResponse response = client.execute(post);

            int rspCode = response.getStatusLine().getStatusCode();
            System.out.println("rspCode:" + rspCode);
            if (rspCode == HttpStatus.SC_MOVED_PERMANENTLY || rspCode == HttpStatus.SC_MOVED_TEMPORARILY) {
                Header locationHeader = response.getFirstHeader("Location");
                String location = locationHeader.getValue();
                System.out.println("=======location=====" + location);
            }
            result = EntityUtils.toString(response.getEntity(), "UTF-8");

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (post != null) {
                post.releaseConnection();
            }
        }
        return null;
    }
}