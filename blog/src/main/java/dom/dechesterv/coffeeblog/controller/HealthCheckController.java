package dom.dechesterv.coffeeblog.controller;

import dom.dechesterv.coffeemodels.agent.KeywordHealthCheck;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @PostMapping("/health/check")
    public KeywordHealthCheck healthCheck(@RequestBody KeywordHealthCheck keyword) {
        if (keyword.getKeyword().equals(KeywordHealthCheck.HEALTH_CHECK.getKeyword()))
            return KeywordHealthCheck.ENABLED;
        return KeywordHealthCheck.WARN;
    }

}
