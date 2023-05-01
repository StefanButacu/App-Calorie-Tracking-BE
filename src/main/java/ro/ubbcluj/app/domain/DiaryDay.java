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
// TODO - add @OneToMany to meal
    private LocalDate day;

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
}
