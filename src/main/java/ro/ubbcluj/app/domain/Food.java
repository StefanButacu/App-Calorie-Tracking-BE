package ro.ubbcluj.app.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Food {
    private Long id;

    private String name;
    private Double protein;
    private Double carbohydrate;
    private Double lipid;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
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