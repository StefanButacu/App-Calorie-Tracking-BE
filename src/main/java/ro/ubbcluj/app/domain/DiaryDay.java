package ro.ubbcluj.app.domain;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class DiaryDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "diaryDay", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Meal> meals;
    private LocalDate day;

    public DiaryDay() {
    }

    public DiaryDay(Long id, List<Meal> meals, LocalDate day) {
        this.id = id;
        this.meals = meals;
        this.day = day;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
