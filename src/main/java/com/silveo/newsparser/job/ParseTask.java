package com.silveo.newsparser.job;

import com.silveo.newsparser.model.News;
import com.silveo.newsparser.service.NewsService;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class ParseTask {
    NewsService newsService;

    //parses news every 10 sec and adds them to h2 db
    @Scheduled(fixedDelay = 10000)
    public void parseNewNews() throws IOException {
        System.out.println("Start parsing new news...");
        String url = "https://news.ycombinator.com/";
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla")
                .timeout(5000)
                .referrer("https://google.com")
                .get();

        //selector chooses titles and links
        Elements newsElements = doc.select(".titleline > a:not(.sitebit a)");

        for (Element element : newsElements) {
            String title = element.text();
            //separates links from titles
            String link = element.attr("href");

            if (!newsService.isExist(title)) {
                System.out.println("News not found: " + title);
                News objNew = new News();
                objNew.setTitle(title);
                objNew.setLink(link);
                newsService.save(objNew);
            }
        }
    }
}
