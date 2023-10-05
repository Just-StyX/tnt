package org.jbdev.tnt.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "articles")
public class Article {
    @Id
    @Column(name = "article_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int articleID;

    @Column(name = "date_created")
    private LocalDate dateCreated = LocalDate.now();

    @Column(name = "summary")
    private String summary;

    @Column(name = "main_article")
    private String mainArticle;

    @Column(name = "title")
    private String title;

    public Article() {}
    public Article(String title, String summary, String mainArticle) {
        this.title = title;
        this.summary = summary;
        this.mainArticle = mainArticle;
        this.dateCreated = LocalDate.now();
    }
}
