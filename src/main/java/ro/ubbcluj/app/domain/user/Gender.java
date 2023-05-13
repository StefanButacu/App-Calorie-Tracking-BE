package ro.ubbcluj.app.domain.user;

public enum Gender {
    MALE("male"),
    FEMALE("female");

    private final String gender;

    Gender(String label) {
        this.gender = label;
    }

    public String getGender() {
        return gender;
    }


}
