package ro.ubbcluj.app.domain.user;

import jakarta.persistence.*;
import ro.ubbcluj.app.domain.FoodMeal;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String username;
    private String password;
    private Double startWeight;
    private Double currentWeight;
    private Double goalWeight;
    private Double height;
    @Enumerated(EnumType.STRING)
    private ActivityLevel activityLevel;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private WeightGoal weightGoal;

    private Double proteinGoal;

    private Double carbohydrateGoal;

    private Double lipidGoal;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<FoodMeal> foodMeals;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getStartWeight() {
        return startWeight;
    }

    public void setStartWeight(Double startWeight) {
        this.startWeight = startWeight;
    }

    public Double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(Double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public Double getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(Double goalWeight) {
        this.goalWeight = goalWeight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
    public ActivityLevel getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(ActivityLevel activityLevel) {
        this.activityLevel = activityLevel;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public WeightGoal getWeightGoal() {
        return weightGoal;
    }

    public void setWeightGoal(WeightGoal weightGoal) {
        this.weightGoal = weightGoal;
    }

    public Double getProteinGoal() {
        return proteinGoal;
    }

    public void setProteinGoal(Double proteinGoal) {
        this.proteinGoal = proteinGoal;
    }

    public Double getCarbohydrateGoal() {
        return carbohydrateGoal;
    }

    public void setCarbohydrateGoal(Double carbohydrateGoal) {
        this.carbohydrateGoal = carbohydrateGoal;
    }

    public Double getLipidGoal() {
        return lipidGoal;
    }

    public void setLipidGoal(Double lipidGoal) {
        this.lipidGoal = lipidGoal;
    }
}
