package ro.ubbcluj.app.domain.user;

public enum ActivityLevel {
    LEVEL_1(1, "Basic Metabolic Rate"),
//    LEVEL_2(2, "Sedentary: little or no exercise"),
    LEVEL_3(3, "Exercise 1-3 times/week"),
    LEVEL_4(4,  "Exercise 4-5 times/week"),
//    LEVEL_5(5,  "Intense exercise 3-4 times/week"),
    LEVEL_6(6,  "Intense exercise 6-7 times/week"),
    LEVEL_7(7, "Very intense exercise daily or physical job");

    private final int apiValue;
    private final String text;

    ActivityLevel(int apiValue, String text) {
        this.apiValue = apiValue;
        this.text = text;
    }

    public int getApiValue() {
        return apiValue;
    }
    public String getText(){
        return text;
    }

}
