package com.silveo.newsparser.controller;

import com.silveo.newsparser.model.News;
import com.silveo.newsparser.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class NewsController {

    NewsService newsService;

    @GetMapping(value = "/news")
    public List<News> getAllNews(){
        System.out.println("getAllNews");
        return newsService.getAllNews();
    }
}
