package ro.ubbcluj.app.domain.user;

public enum WeightGoal {
    MAINTAIN("maintain", "Maintain weight"),
    WEIGHT_LOSE("weightlose", "Weight loss"),
    WEIGHT_GAIN("weightgain", "Weight gain");

    private final String apiValue;
    private final String text;

    WeightGoal(String key, String text) {
        this.apiValue = key;
        this.text = text;
    }

    public String getApiValue() {
        return apiValue;
    }

    public String getText() {
        return text;
    }
}
