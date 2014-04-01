package com.wm.github;

/**
 * User: Sunil Kumar
 */
public enum Severity {

    BACK_END_TASK("Back-end Task"),
    CRITICAL("Critical"),
    ENHANCEMENT("Enhancement"),
    FIXED("Fixed"),
    HIGH_PRIORITY("High Priority"),
    MAJOR("Major"),
    SAAS("SAAS"),
    OPEN("open"),
    BLOCKED_ANOTHER_TICKET("Blocked by another fix"),
    CHENNAI_TEAM("Chennai Team"),
    COSMETIC("Cosmetic"),
    DUPLICATE("Duplicate"),
    FUTURE_IMPLEMENTATION("Future Implementation"),
    KNOWN_ISSUE("Known Issue"),
    MINOR("Minor"),
    NOT_REPRODUCIBLE("Not Reproducible"),
    NOT_A_BUG("Not a Bug"),
    ON_HOLD("ON_HOLD"),
    REOPEN("Re-open");

    private String value;

    private Severity(String value) {
        this.value = value;
    }

    public String getValue()
    {
       return this.value;
    }

}
