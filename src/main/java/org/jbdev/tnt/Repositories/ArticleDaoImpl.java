package org.jbdev.tnt.Repositories;

import jakarta.persistence.EntityManager;
import org.jbdev.tnt.entities.Article;
import org.jbdev.tnt.entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ArticleDaoImpl implements ArticleDao{
    private final EntityManager entityManager;
    private final CommentDao commentDao;

    @Autowired
    public ArticleDaoImpl(EntityManager entityManager, CommentDao commentDao) {
        this.entityManager = entityManager;
        this.commentDao = commentDao;
    }

    @Override
    @Transactional
    public void save(Article article) {
        entityManager.persist(article);
    }

    @Override
    public Article read() {
        int x = find().size() - 1;
        Article article;
        if (x < 0) {
            article = new Article("No Article", "No Article", "No Article");
        } else {
            int size = find().get(x).getArticleID();
            article = entityManager.find(Article.class, size);
        }
        return article;
    }

    @Override
    public Article getOne(int articleID) {
        return entityManager.find(Article.class, articleID);
    }

    @Override
    public List<Article> getAll() {
        return find();
    }

    @Override
    @Transactional
    public void update(int articleID, Article article) {
        Article art = entityManager.find(Article.class, articleID);
        art.setTitle(article.getTitle());
        art.setSummary(article.getSummary());
        art.setMainArticle(article.getMainArticle());
        entityManager.merge(art);
    }

    @Override
    @Transactional
    public void delete(int articleID) {
        Article art = entityManager.find(Article.class, articleID);
        var comments = commentDao.findAllComment(articleID);
        entityManager.remove(art);
        for(Comment comment: comments) entityManager.remove(comment);
    }

    public List<Article> find() {
        String query = "from Article";
        return entityManager.createQuery(query, Article.class).getResultList();
    }
}
