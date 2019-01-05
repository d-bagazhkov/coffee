package dom.dechesterv.coffeemodels.agent;

public enum KeywordHealthCheck {
    HEALTH_CHECK("HEALTH_CHECK"), ENABLED("ENABLED"), DISABLED("DISABLED");

    private String keyword;

    KeywordHealthCheck(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }
}
