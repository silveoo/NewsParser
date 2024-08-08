package com.silveo.newsparser.service;

import com.silveo.newsparser.model.News;
import com.silveo.newsparser.repository.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NewsServiceImpl implements NewsService {

    NewsRepository repository;

    @Override
    public void save(News news) {
        repository.save(news);
    }

    //checks if the new is already in db
    @Override
    public boolean isExist(String newsTitle) {
        List<News> news = repository.findAll();
        for(News n : news){
            if(n.getTitle().equals(newsTitle)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<News> getAllNews() {
        return repository.findAll();
    }
}
