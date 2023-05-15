package ro.ubbcluj.app.domain.dto.userDTOS;

public class UserDetailsDTO {
    private Long id;
    private String username;
    private Double calorieGoal;
    private Double startWeight;
    private Double goalWeight;
    private Double currentWeight;
    private Double height;
    private Double proteinGoal;
    private Double lipidGoal;
    private Double carbohydrateGoal;

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

    public Double getCalorieGoal() {
        return calorieGoal;
    }

    public void setCalorieGoal(Double calorieGoal) {
        this.calorieGoal = calorieGoal;
    }

    public Double getStartWeight() {
        return startWeight;
    }

    public void setStartWeight(Double startWeight) {
        this.startWeight = startWeight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(Double goalWeight) {
        this.goalWeight = goalWeight;
    }

    public Double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(Double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public Double getProteinGoal() {
        return proteinGoal;
    }

    public void setProteinGoal(Double proteinGoal) {
        this.proteinGoal = proteinGoal;
    }

    public Double getLipidGoal() {
        return lipidGoal;
    }

    public void setLipidGoal(Double lipidGoal) {
        this.lipidGoal = lipidGoal;
    }

    public Double getCarbohydrateGoal() {
        return carbohydrateGoal;
    }

    public void setCarbohydrateGoal(Double carbohydrateGoal) {
        this.carbohydrateGoal = carbohydrateGoal;
    }
}
