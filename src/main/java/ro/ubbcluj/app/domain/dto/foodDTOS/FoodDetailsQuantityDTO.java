package ro.ubbcluj.app.domain.dto.foodDTOS;

public class FoodDetailsQuantityDTO {

    Long id;
    String name;
    Double quantity;
    Double proteinPerCent;
    Double carbohydratePerCent;
    Double lipidPerCent;

    public FoodDetailsQuantityDTO() {
    }

    public FoodDetailsQuantityDTO(Long id, String name, Double quantity, Double proteinPerCent, Double carbohydratePerCent, Double lipidPerCent) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.proteinPerCent = proteinPerCent;
        this.carbohydratePerCent = carbohydratePerCent;
        this.lipidPerCent = lipidPerCent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getProteinPerCent() {
        return proteinPerCent;
    }

    public void setProteinPerCent(Double proteinPerCent) {
        this.proteinPerCent = proteinPerCent;
    }

    public Double getCarbohydratePerCent() {
        return carbohydratePerCent;
    }

    public void setCarbohydratePerCent(Double carbohydratePerCent) {
        this.carbohydratePerCent = carbohydratePerCent;
    }

    public Double getLipidPerCent() {
        return lipidPerCent;
    }

    public void setLipidPerCent(Double lipidPerCent) {
        this.lipidPerCent = lipidPerCent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
