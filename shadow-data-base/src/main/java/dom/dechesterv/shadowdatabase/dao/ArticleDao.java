package dom.dechesterv.shadowdatabase.dao;

import dom.dechesterv.coffeemodels.entity.Article;
import dom.dechesterv.shadowdatabase.config.DumbState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Repository
public class ArticleDao {

    private DumbState dumbState;

    public Article addArticle(Article article) {
        if (dumbState != null) {
            article.setId(new Random().nextLong());
            dumbState.getDumbCacheArticles().add(article);
        } else {
            //TODO:add connection to db
        }
        return null;
    }

    public Article updateArticle(Article article) {
        if (dumbState != null) {
            dumbState.setDumbCacheArticles(dumbState.getDumbCacheArticles().stream()
                    .filter(a -> !a.getId().equals(article.getId()))
                    .collect(Collectors.toList()));
            dumbState.getDumbCacheArticles().add(article);
            return article;
        } else {
            //TODO:add connection to db
        }
        return null;
    }

    @Autowired
    public void setDumbState(DumbState dumbState) {
        this.dumbState = dumbState;
    }

    public void removeArticle(Long id) {
        if (dumbState != null) {
            dumbState.setDumbCacheArticles(dumbState.getDumbCacheArticles().stream()
                    .filter(a -> !a.getId().equals(id))
                    .collect(Collectors.toList()));
        } else {
            //TODO:add connection to db
        }
    }

    public Article getArticle(Long id) {
        if (dumbState != null) {
            return dumbState.getDumbCacheArticles().stream()
                    .filter(a -> a.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        } else {
            //TODO:add connection to db
        }
        return null;
    }

    public List<Article> getAllArticles() {
        if (dumbState != null) {
            return dumbState.getDumbCacheArticles();
        } else {
            //TODO:add connection to db
        }
        return null;
    }
}
