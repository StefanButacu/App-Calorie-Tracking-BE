package ro.ubbcluj.app.domain.user;

public enum WeightGoal {
    MAINTAIN("maintain", "Maintain weight"),
//    MILD_LOSE("mildlose", "Mild weight loss"),
    WEIGHT_LOSE("weightlose", "Weight loss"),
//    EXTREME_LOSE("extremelose", "Extreme weight loss"),
//    MILD_GAIN("mildgain", "Mild weight gain"),
    WEIGHT_GAIN("weightgain", "Weight gain");
//    EXTREME_GAIN("extremegain", "Extreme weight gain");

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
