package org.jbdev.tnt.services;

import org.jbdev.tnt.Repositories.ArticleDao;
import org.jbdev.tnt.entities.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleDao articleDao;

    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public void save(Article article) {
        articleDao.save(article);
    }
    public Article read() {return articleDao.read(); }
    public Article findOne(int articleID) { return articleDao.getOne(articleID); }
    public List<Article> getAll() {return articleDao.getAll();}
    public void update(int articleID, Article article) { articleDao.update(articleID, article); }
    public void delete(int articleID) { articleDao.delete(articleID); }
}
