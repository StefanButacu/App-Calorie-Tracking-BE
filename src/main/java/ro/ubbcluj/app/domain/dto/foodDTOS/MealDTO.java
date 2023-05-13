package ro.ubbcluj.app.domain.dto.foodDTOS;

public class MealDTO {
    private Long id;
    private String name;

    public MealDTO() {
    }

    public MealDTO(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
