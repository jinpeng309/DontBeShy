package com.capslock.dontbeshy;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * Created by capslock.
 */
public class DontBeShyPageProcessor implements PageProcessor {
    private final Site site = Site.me().setRetryTimes(3).setSleepTime(100).setUserAgent("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1 )");
    private static final String URL_LIST = "https://www\\.douban\\.com/group/haixiuzu/discussion\\?start=(\\d+)";
    private static final String URL_TOPIC = "https://www\\.douban\\.com/group/topic/(\\d+)/";
    private static final String URL_LIST_TEMPLATE = "https://www.douban.com/group/haixiuzu/discussion?start=%s";

    private static final String TITLE = "title";
    private static final String IMAGE_URL_LIST = "imageUrlList";

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(final Page page) {
        final Selectable pageUrl = page.getUrl();
        if (pageUrl.regex(URL_LIST).match()) {
            processListPage(page);
        }
        if (pageUrl.regex(URL_TOPIC).match()) {
            processTopicPage(page);
        }
    }

    private void processTopicPage(final Page page) {
        final String title = page.getHtml().xpath("//div[@id='content']/h1/text()").toString();
        final List<String> imageUrlList = page.getHtml().xpath("//div[@class='topic-figure cc']").$("img", "src").all();
        page.putField(TITLE, title);
        page.putField(IMAGE_URL_LIST, imageUrlList);
    }

    private void processListPage(final Page page) {
        final Selectable pageUrl = page.getUrl();
        final long prePageNumber = Long.parseLong(pageUrl.regex(URL_LIST).get());
        page.addTargetRequest(String.format(URL_LIST_TEMPLATE, prePageNumber + 25));
        final List<String> topicUrlList = page.getHtml().xpath("//td[@class='title']/a").links().all();
        if (!topicUrlList.isEmpty()) {
            page.addTargetRequests(topicUrlList);
        }
    }


    public static void main(String[] args) {
        Spider.create(new DontBeShyPageProcessor())
                .addUrl("https://www.douban.com/group/haixiuzu/discussion?start=0")
                .thread(5)
                .run();
    }
}
