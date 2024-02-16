package com.locke.demos.web.utils;

import com.locke.demos.web.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HtmlParseUtil {

    public static void main(String[] args) throws Exception {
        new HtmlParseUtil().parseJD("java").forEach(System.out::println);
    }

    public List<Content> parseJD(String keyword) throws Exception {
        // 获取请求 https://search.jd.com/Search?keyword=java
        // 前提，需要联网，ajax 不能获取到
        String url = "https://search.jd.com/Search?keyword=" + keyword;

        // 解析网页(Jsoup 返回的 Document 就是浏览器 Document 对象)
        // Document document = Jsoup.parse(new URL(url), 30000);  // 方法失效，需要登录

        // 新方法：使用 Cookie 登录
        // 如何获取 Cookie：登录京东后，打开控制台，输入 document.cookie
        String cookies = "";
        Document document = Jsoup.connect(url)
                .cookie("Cookie", cookies)
                .get();

        // 所有在 js 中可以使用的方法，这里都能用
        Element element = document.getElementById("J_goodsList");
        // System.out.println(element.html());
        // 获取所有的 li 元素
        ArrayList<Content> goodsList = new ArrayList<>();
        if (element != null) {
            Elements elements = element.getElementsByTag("li");
            // 获取元素中的内容，这里的 el 就是每一个 li 标签了
            for (Element el : elements) {
                // 关于这种图片特别多的网站，所有的图片都是延迟加载的
                // data-lazy-img
                if (el.attr("class").equalsIgnoreCase("gl-item")) {
                    String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
                    String price = el.getElementsByClass("p-price").eq(0).text();
                    String title = el.getElementsByClass("p-name").eq(0).text();
                    goodsList.add(new Content(title, img, price));
                }
            }
        }
        return goodsList;
    }
}
