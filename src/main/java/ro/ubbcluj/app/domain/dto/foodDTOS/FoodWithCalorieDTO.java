package ro.ubbcluj.app.domain.dto.foodDTOS;

public class FoodWithCalorieDTO {

    private Long id;
    private String name;
    private Double quantity;
    private Long calories;

    public FoodWithCalorieDTO(Long id, String name, Double quantity, Long calories) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.calories = calories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Long getCalories() {
        return calories;
    }

    public void setCalories(Long calories) {
        this.calories = calories;
    }
}
