package ro.ubbcluj.app.domain.user;

public enum WeightGoal {
    MAINTAIN("maintain", "Maintain weight"),
    MILD_LOSE("mildlose", "Mild weight loss"),
    WEIGHT_LOSE("weightlose", "Weight loss"),
    EXTREME_LOSE("extremelose", "Extreme weight loss"),
    MILD_GAIN("mildgain", "Mild weight gain"),
    WEIGHT_GAIN("weightgain", "Weight gain"),
    EXTREME_GAIN("extremegain", "Extreme weight gain");

    private final String key;
    private final String description;

    WeightGoal(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }
}
