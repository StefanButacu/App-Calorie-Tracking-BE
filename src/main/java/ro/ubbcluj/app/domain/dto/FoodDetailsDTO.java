package ro.ubbcluj.app.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class FoodDetailsDTO {

    private Long id;
    @NotEmpty(message = "Name can't be empty")
    private String name;
    @NotNull(message = "Proteins are required")
    @Min(value = 1, message = "The number of proteins must be greater than 0")
    private Double protein;
    @NotNull(message = "Carbohydrates are required")
    @Min(value = 1, message = "The number of carbohydrates must be greater than 0")
    private Double carbohydrate;
    @NotNull(message = "Lipids are required")
    @Min(value = 1, message = "The number of lipids must be greater than 0")
    private Double lipid;

    public FoodDetailsDTO() {
    }

    public FoodDetailsDTO(Long id, String name, Double protein, Double carbohydrate, Double lipid) {
        this.id = id;
        this.name = name;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.lipid = lipid;
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

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(Double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public Double getLipid() {
        return lipid;
    }

    public void setLipid(Double lipid) {
        this.lipid = lipid;
    }
}
