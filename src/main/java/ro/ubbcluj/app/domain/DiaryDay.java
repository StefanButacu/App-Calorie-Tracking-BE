package ro.ubbcluj.app.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class DiaryDay {


    private Long id;

    List<Meal> meals;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
