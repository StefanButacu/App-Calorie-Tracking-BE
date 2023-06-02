package ro.ubbcluj.app.domain;

public enum CalorieValue {
    PROTEIN(4),
    LIPID(9),
    CARBOHYDRATE(4);
    private final int calorie;

    CalorieValue(int calorie) {
        this.calorie = calorie;
    }

    public int getCalorie() {
        return calorie;
    }
}

