package ro.ubbcluj.app.domain.dto;

public class FoodQuantityDTO {

    private Long foodId;

    private Double quantity;
    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
