package dom.dechesterv.shadowdatabase.config;

import dom.dechesterv.coffeemodels.entity.Article;
import dom.dechesterv.coffeemodels.entity.Consumer;
import dom.dechesterv.coffeemodels.entity.Permission;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class DumbState {

    private List<Article> dumbCacheArticles;
    private List<Consumer> dumbCacheConsumers;

    static class DumbStateBuilder {

        private int countArticles = 1;
        private int countConsumers = 1;

        private Random random = new Random();

        public DumbStateBuilder withCountArticles(int countArticles) {
            this.countArticles = countArticles;
            return this;
        }

        public DumbStateBuilder withCountConsumers(int countConsumers) {
            this.countConsumers = countConsumers;
            return this;
        }

        public DumbState build() {
            DumbState state = new DumbState();
            state.setDumbCacheArticles(new ArrayList<>());
            for (int i = 0; i < countArticles; i++)
                state.getDumbCacheArticles().add(createDumbArticle());
            state.setDumbCacheConsumers(new ArrayList<>());
            for (int i = 0; i < countConsumers; i++)
                state.getDumbCacheConsumers().add(createDumbConsumer());
            return state;
        }

        private Article createDumbArticle() {
            Article article = new Article();
            article.setId((long) random.nextInt(10000));
            StringBuilder sb = new StringBuilder();
            int countWords = random.nextInt(2) + 3;
            int countLatter;
            sb.append((char) (random.nextInt(26) + 'A'));
            for (int i = 0; i < countWords; i++) {
                sb.append(randomString(random.nextInt(4) + 3)).append(" ");
            }
            article.setTitle(sb.toString());
            sb = new StringBuilder();
            countWords = random.nextInt(30) + 15;
            sb.append((char) (random.nextInt(26) + 'A'));
            for (int i = 0; i < countWords; i++) {
                countLatter = random.nextInt(7) + 1;
                for (int j = 0; j < countLatter; j++)
                    sb.append((char) (random.nextInt(26) + 'a'));
                if (i != countWords / 2)
                    sb.append(" ");
                else
                    sb.append(". ").append((char) (random.nextInt(26) + 'A'));
            }
            article.setContent(sb.toString());
            article.setDate(new Date());
            return article;
        }

        private Consumer createDumbConsumer() {
            Consumer consumer = new Consumer();
            consumer.setId((long) random.nextInt(10000));
            consumer.setUsername(randomString(random.nextInt(4) + 5));
            consumer.setPassword(randomString(random.nextInt(4) + 6));
            consumer.setEmail(randomString(random.nextInt(5) + 5) +
                    "@" +
                    randomString(random.nextInt(2) + 3) +
                    "." +
                    randomString(random.nextInt(3) + 2));
            consumer.setPermission(Permission.values()[random.nextInt(5)]);
            consumer.setDate(new Date());
            return consumer;
        }

        private String randomString(int countLatter) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < countLatter; i++)
                sb.append((char) (random.nextInt(26) + 'a'));
            return sb.toString();
        }

    }
}
