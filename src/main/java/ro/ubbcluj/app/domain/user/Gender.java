package ro.ubbcluj.app.domain.user;

public enum Gender {
    MALE("male", "Male"),
    FEMALE("female", "Female");

    private final String apiValue;
    private final String text;


    Gender(String apiValue, String text) {
        this.apiValue = apiValue;
        this.text = text;
    }

    public String getApiValue() {
        return apiValue;
    }
    public String getText() {
        return text;
    }


}
