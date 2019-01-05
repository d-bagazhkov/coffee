package dom.dechesterv.shadowdatabase.service;

import dom.dechesterv.coffeemodels.entity.Article;
import dom.dechesterv.shadowdatabase.dao.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private ArticleDao articleDao;

    public Long addArticle(Article article) {
        return articleDao.addArticle(article).getId();
    }

    public Long updateArticle(Article article) {
        return articleDao.updateArticle(article).getId();
    }

    public void removeArticle(Long id) {
        articleDao.removeArticle(id);
    }

    public Article getArticle(Long id) {
        return articleDao.getArticle(id);
    }


    public List<Article> getAllArticles() {
        return articleDao.getAllArticles();
    }

    @Autowired
    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

}
