package ro.ubbcluj.app.domain.dto;

public class FoodDetailsDTO {

    private Long id;
    private String name;
    private Double protein;
    private Double carbohydrate;
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
