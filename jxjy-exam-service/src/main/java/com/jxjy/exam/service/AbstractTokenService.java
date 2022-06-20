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
    private static final String token = "ASP.NET_SessionId=rj1w3dlktingfme52a1t2xop; JHZLUID=5e72ecf9079048017d8436caf15cbf5b";

    /**
     * User-Agent
     */
    private static List<String> uas = Lists.newArrayList();

    /**
     * cookies
     */
    private static List<String> cookies = Lists.newArrayList();

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

        /**cookies*/
        cookies.add("ASP.NET_SessionId=rj1w3dlktingfme52a1t2xop; JHZLUID=486d4dcd6712d28940d3e06ce38e48c1");
        cookies.add("ASP.NET_SessionId=rj1w3dlktingfme52a1t2xop; JHZLUID=2b52b00b1425537ab887ba65b0e36b3f");
        cookies.add("ASP.NET_SessionId=rj1w3dlktingfme52a1t2xop; JHZLUID=e49ab308d4585ff382f6be43e9f84f0e");
        cookies.add("ASP.NET_SessionId=rj1w3dlktingfme52a1t2xop; JHZLUID=bac8396d3049491bdbb58695e909fdf8");
        cookies.add("ASP.NET_SessionId=rj1w3dlktingfme52a1t2xop; JHZLUID=40ca06a4f8078b01751ab1b570faaf1b");
        cookies.add("ASP.NET_SessionId=rj1w3dlktingfme52a1t2xop; JHZLUID=a27389eaeb7ed2175c9f402380e6eccd");
        cookies.add("ASP.NET_SessionId=rj1w3dlktingfme52a1t2xop; JHZLUID=cc7a5ec4882b08a0365c7c09da44f3f9");
        cookies.add("ASP.NET_SessionId=rj1w3dlktingfme52a1t2xop; JHZLUID=12afe4d6d99585db235bb076a1a607a2");
        cookies.add("ASP.NET_SessionId=rj1w3dlktingfme52a1t2xop; JHZLUID=1b866ca3e03036c581dea6ec92fad749");
        cookies.add("ASP.NET_SessionId=rj1w3dlktingfme52a1t2xop; JHZLUID=74bcd107c5c00dac1e590bc20d00ba77");
        cookies.add("ASP.NET_SessionId=rj1w3dlktingfme52a1t2xop; JHZLUID=f85e8fc4da17a6007bf004d1c9af46f8");
        cookies.add("ASP.NET_SessionId=rj1w3dlktingfme52a1t2xop; JHZLUID=a8b584d8c6b0fc746d0ddfaa84ad1408");
        cookies.add("ASP.NET_SessionId=rj1w3dlktingfme52a1t2xop; JHZLUID=66ae7c920ed95e96f45e963ac073b558");
        cookies.add("ASP.NET_SessionId=rj1w3dlktingfme52a1t2xop; JHZLUID=47d5ebc16968343ffdd9eddf1535df18");
        cookies.add("ASP.NET_SessionId=rj1w3dlktingfme52a1t2xop; JHZLUID=34301845cb38e6763576ff9d338309cb");
        cookies.add("ASP.NET_SessionId=rj1w3dlktingfme52a1t2xop; JHZLUID=4147a94ba33bdb8cf8058004b0ea900e");
        cookies.add("ASP.NET_SessionId=rj1w3dlktingfme52a1t2xop; JHZLUID=15ad7c543f20ee876dfb71b8deacafb4");
        cookies.add("ASP.NET_SessionId=rj1w3dlktingfme52a1t2xop; JHZLUID=10f92dc910b729dcb6e1148bea8421ac");
        cookies.add("ASP.NET_SessionId=rj1w3dlktingfme52a1t2xop; JHZLUID=6f5023d56f5c4ec70c035b139abe519b");
        cookies.add("ASP.NET_SessionId=rj1w3dlktingfme52a1t2xop; JHZLUID=24df9959507f80a088900639bc341301");


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
        tokenMap.put(HTTP.USER_AGENT, getKeyByRandom(uas, 20));
        tokenMap.put("X-Requested-With", "XMLHttpRequest");
        return tokenMap;
    }

    /**
     * 随机获取关键子
     * @return
     */
    private String getKeyByRandom(List<String> keys, Integer num){
        Random random = new Random();
        int randomNum = random.nextInt(num);
        return keys.get(randomNum);
    }
}
