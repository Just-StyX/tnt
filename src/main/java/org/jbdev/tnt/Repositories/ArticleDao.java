package org.jbdev.tnt.Repositories;

import org.jbdev.tnt.entities.Article;

import java.util.List;

public interface ArticleDao {
    void save(Article article);
    Article read();
    Article getOne(int articleID);
    List<Article> getAll();

    void update(int articleID, Article article);

    void delete(int articleID);
}
