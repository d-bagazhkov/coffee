package dom.dechesterv.coffeemodels.entity;

public enum Permission {
    GUEST(0), DEFAULT_USER(1), MODERATOR(2), ADMIN(3), CREATOR(4);

    private int range;

    Permission(int range) {
        this.range = range;
    }
}
