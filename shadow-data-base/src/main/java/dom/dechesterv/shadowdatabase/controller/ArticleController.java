package dom.dechesterv.shadowdatabase.controller;

import dev.dechesterv.coffeemodels.entity.Article;
import dom.dechesterv.shadowdatabase.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("article")
public class ArticleController {

    private ArticleService articleService;

    @PostMapping("/add")
    public Long addArticle(@RequestBody Article article) {
        return article.getId() != null ? articleService.updateArticle(article) : articleService.addArticle(article);
    }

    @GetMapping("/remove/{id}")
    public void removeArticle(@PathVariable Long id) {
        articleService.removeArticle(id);
    }

    @GetMapping("/get/{id}")
    public Article getArticle(@PathVariable Long id) {
        return articleService.getArticle(id);
    }

    @GetMapping("/get/all")
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @Autowired
    private void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

}
