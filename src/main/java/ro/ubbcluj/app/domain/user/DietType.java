package ro.ubbcluj.app.domain.user;

public enum DietType {
    BALANCED("balanced", "Balanced"),
    LOW_FAT("lowfat", "Low-fat"),
    LOW_CARBS("lowcarbs", "Low-carbs"),
    HIGH_PROTEIN("highprotein", "High-protein");

    private final String apiValue;
    private final String text;

    DietType(String apiValue, String text){
        this.apiValue = apiValue;
        this.text = text;
    }

    public String getApiValue(){
        return apiValue;
    }
    public String getText(){
        return text;
    }

}
