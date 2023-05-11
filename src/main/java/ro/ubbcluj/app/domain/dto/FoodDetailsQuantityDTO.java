package ro.ubbcluj.app.domain.dto;

public class FoodDetailsQuantityDTO {

    Long id;
    Double quantity;
    Double proteinPerCent;
    Double carbohydratePerCent;
    Double lipidPerCent;

    public FoodDetailsQuantityDTO() {
    }

    public FoodDetailsQuantityDTO(Long id, Double quantity, Double protein, Double carbohydratePerCent, Double lipidPerCent) {
        this.id = id;
        this.quantity = quantity;
        this.proteinPerCent = protein;
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
}
