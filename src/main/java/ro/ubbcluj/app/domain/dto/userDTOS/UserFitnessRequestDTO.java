package ro.ubbcluj.app.domain.dto.userDTOS;

import ro.ubbcluj.app.domain.user.ActivityLevel;
import ro.ubbcluj.app.domain.user.DietType;
import ro.ubbcluj.app.domain.user.Gender;
import ro.ubbcluj.app.domain.user.WeightGoal;

public class UserFitnessRequestDTO {
    Integer age;
    Gender gender;
    Double height;
    Double weight;
    ActivityLevel activityLevel;
    WeightGoal weightGoal;
    DietType dietType;

    public UserFitnessRequestDTO(Integer age, Gender gender, Double height, Double weight, ActivityLevel activityLevel, WeightGoal weightGoal, DietType dietType) {
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.activityLevel = activityLevel;
        this.weightGoal = weightGoal;
        this.dietType = dietType;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public ActivityLevel getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(ActivityLevel activityLevel) {
        this.activityLevel = activityLevel;
    }

    public WeightGoal getWeightGoal() {
        return weightGoal;
    }

    public void setWeightGoal(WeightGoal weightGoal) {
        this.weightGoal = weightGoal;
    }

    public DietType getDietType() {
        return dietType;
    }

    public void setDietType(DietType dietType) {
        this.dietType = dietType;
    }
}
