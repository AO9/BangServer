package com.gto.bang.common.net;

import com.gto.bang.common.json.JsonUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by shenjialong on 16/4/25.
 */
public final class HttpUtil {

//
	private static Logger log = Logger.getLogger(HttpUtil.class);

    public static void flushResponse(HttpServletResponse response,Object res) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.println(JsonUtil.obj2Str(res));
        writer.flush();
        writer.close();
    }


//
//    public static String doGet(String url, String queryString, String charset, boolean pretty)
//    {
//        StringBuilder response = new StringBuilder();
//        HttpClient client = new HttpClient();
//        HttpMethod method = new GetMethod(url);
//        try {
//            if (StringUtils.isNotBlank(queryString)) {
//                method.setQueryString(URIUtil.encodeQuery(queryString));
//            }
//            client.executeMethod(method);
//            if (method.getStatusCode() == HttpStatus.SC_OK) {
//                BufferedReader reader = new BufferedReader(
//                        new InputStreamReader(method.getResponseBodyAsStream(), charset));
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    if (pretty)
//                        response.append(line).append(System.getProperty("line.separator"));
//                    else
//                        response.append(line);
//                }
//                reader.close();
//            }
//        } catch (URIException e) {
////			log.error("HTTP Get request error, queryString=" + queryString, e);
//        } catch (IOException e) {
////			log.error("HTTP Get request error, url=" + url, e);
//        } finally {
//            method.releaseConnection();
//        }
//        return response.toString();
//    }
//
//    public static String doPost(String url, Map<String, String> params, String charset, boolean pretty)
//    {
//        StringBuilder response = new StringBuilder();
//        HttpClient client = new HttpClient();
//        HttpMethod method = new PostMethod(url);
//        if (params != null) {
//            List<NameValuePair> pairList = new ArrayList<NameValuePair>();
//            for (Map.Entry<String, String> entry : params.entrySet()) {
//                pairList.add(new NameValuePair(entry.getKey(), entry.getValue()));
//            }
//            NameValuePair[] pairs = pairList.toArray(new NameValuePair[pairList.size()]);
//            method.setQueryString(pairs);
//        }
//        try {
//            client.executeMethod(method);
//            if (method.getStatusCode() == HttpStatus.SC_OK) {
//                BufferedReader reader = new BufferedReader(
//                        new InputStreamReader(method.getResponseBodyAsStream(), charset));
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    if (pretty)
//                        response.append(line).append(System.getProperty("line.separator"));
//                    else
//                        response.append(line);
//                }
//                reader.close();
//            }
//        } catch (IOException e) {
////			log.error("HTTP Get request error, url=" + url, e);
//        } finally {
//            method.releaseConnection();
//        }
//        return response.toString();
//    }
//
//    public static void main(String[] args)
//    {
//        String content = doGet("http://www.baidu.com", null, "UTF-8", true);
//        System.out.println(content);
//    }
}
