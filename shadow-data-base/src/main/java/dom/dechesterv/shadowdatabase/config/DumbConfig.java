package dom.dechesterv.shadowdatabase.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DumbConfig {

    @Value("${db.dumb.count.articles:1}")
    private int countArticles;

    @Value("${db.dumb.count.consumers:1}")
    private int countConsumers;

    @Bean
    @ConditionalOnProperty(prefix = "db.dumb.", value = "enabled")
    public DumbState dumbState() {
        return new DumbState.DumbStateBuilder()
                .withCountArticles(countArticles)
                .withCountConsumers(countConsumers)
                .build();
    }
}
