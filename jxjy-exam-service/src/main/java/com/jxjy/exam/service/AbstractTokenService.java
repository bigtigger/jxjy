package com.jxjy.exam.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.http.protocol.HTTP;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author yyc
 * @version 1.0
 * @date 2022/6/15 20:33
 */
public abstract class AbstractTokenService {
    /**
     * token
     */
    private static final String token = "ASP.NET_SessionId=wastpgoqli4prihotigxu2jo; JHZLUID=d07787816a9c10ca1c0f0eec3c884819";

    private static List<String> uas = Lists.newArrayList();

    static {
        uas.add("User-Agent,Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0;");
        uas.add("User-Agent,Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0)");
        uas.add("User-Agent,Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0)");
        uas.add("User-Agent, Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
        uas.add("User-Agent, Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv,2.0.1) Gecko/20100101 Firefox/4.0.1");
        uas.add("User-Agent,Mozilla/5.0 (Windows NT 6.1; rv,2.0.1) Gecko/20100101 Firefox/4.0.1");
        uas.add("User-Agent,Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; en) Presto/2.8.131 Version/11.11");
        uas.add("User-Agent,Opera/9.80 (Windows NT 6.1; U; en) Presto/2.8.131 Version/11.11");
        uas.add("User-Agent, Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11");
        uas.add("User-Agent,Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50");
        uas.add("User-Agent,Mozilla/5.0 (Windows; U; Windows NT 6.1; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50");
        uas.add("User-Agent, Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon 2.0)");
        uas.add("User-Agent, Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; TencentTraveler 4.0)");
        uas.add("User-Agent, Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
        uas.add("User-Agent, Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; The World)");
        uas.add("User-Agent, Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; SE 2.X MetaSr 1.0; SE 2.X MetaSr 1.0; .NET CLR 2.0.50727; SE 2.X MetaSr 1.0)");
        uas.add("User-Agent, Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; 360SE)");
        uas.add("User-Agent, Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Avant Browser)");
        uas.add("User-Agent, Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
        uas.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36");
    }
    /**
     * 封装token
     * @return
     */
    protected Map<String, String> getHeaders(String url){
        Map<String, String> tokenMap = Maps.newHashMap();
        tokenMap.put("Accept", "application/json, text/javascript, */*; q=0.01");
        tokenMap.put("Accept-Encoding", "gzip, deflate, br");
        tokenMap.put("Accept-Language", "zh-CN,zh;q=0.9");
        tokenMap.put("Connection", "keep-alive");
        tokenMap.put("Cookie", token);
        tokenMap.put("Host", "www.juhaisoft.net");
        tokenMap.put("Referer", url);
        tokenMap.put("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"102\", \"Google Chrome\";v=\"102\"");
        tokenMap.put("sec-ch-ua-mobile", "?0");
        tokenMap.put("sec-ch-ua-platform", "\"macOS\"");
        tokenMap.put("Sec-Fetch-Dest", "empty");
        tokenMap.put("Sec-Fetch-Mode", "cors");
        tokenMap.put("Sec-Fetch-Site", "same-origin");
        tokenMap.put(HTTP.USER_AGENT, getUserAgent());
        tokenMap.put("X-Requested-With", "XMLHttpRequest");
        return tokenMap;
    }

    /**
     * 随机获取USER_AGENT
     * @return
     */
    private String getUserAgent(){
        Random random = new Random();
        int randomNum = random.nextInt(20);
        return uas.get(randomNum);
    }
}
