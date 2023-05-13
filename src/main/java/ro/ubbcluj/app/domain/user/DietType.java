package ro.ubbcluj.app.domain.user;

public enum DietType {
    BALANCED("balanced"),
    LOW_FAT("lowfat"),
    LOW_CARBS("lowcarbs"),
    HIGH_PROTEIN("highprotein");


    private final String dietType;
    DietType(String dietType){
        this.dietType = dietType;
    }

    public String getDietType(){
        return dietType;
    }

}
