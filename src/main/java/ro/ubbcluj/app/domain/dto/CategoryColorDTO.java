package ro.ubbcluj.app.domain.dto;

import java.util.List;

public class CategoryColorDTO {

    private Integer categoryId;
    private List<Integer> categoryColor;

    public CategoryColorDTO(Integer categoryId, List<Integer> categoryColor) {
        this.categoryId = categoryId;
        this.categoryColor = categoryColor;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<Integer> getCategoryColor() {
        return categoryColor;
    }

    public void setCategoryColor(List<Integer> categoryColor) {
        this.categoryColor = categoryColor;
    }
}
