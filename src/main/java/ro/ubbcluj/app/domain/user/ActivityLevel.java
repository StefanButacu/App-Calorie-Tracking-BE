package ro.ubbcluj.app.domain.user;

public enum ActivityLevel {
    LEVEL_0(1.0, "Basic Metabolic Rate"),
    LEVEL_1(1.2, "Sedentary"),
    LEVEL_2(1.55, "Exercise 1-3 times/week"),
    LEVEL_3(1.55, "Exercise 5-6 times/week"),
    LEVEL_4(1.725, "Very intense exercise daily"),
    LEVEL_5(1.725, "Very exhausting exercise for a long period");

    private final double activityFactor;
    private final String text;

    ActivityLevel(double activityFactor, String text) {
        this.activityFactor = activityFactor;
        this.text = text;
    }

    public double getActivityFactor() {
        return activityFactor;
    }

    public String getText() {
        return text;
    }

}
