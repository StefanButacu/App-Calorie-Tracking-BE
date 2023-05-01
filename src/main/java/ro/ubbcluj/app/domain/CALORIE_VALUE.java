package ro.ubbcluj.app.domain;

public enum CALORIE_VALUE {
    PROTEIN(4),
    LIPID(9),
    CARBOHYDRATE(4);
    private final int calorie;

    CALORIE_VALUE(int calorie) {
        this.calorie = calorie;
    }

    public int getCalorie() {
        return calorie;
    }
}

