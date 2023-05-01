package ro.ubbcluj.app.domain;


import jakarta.persistence.*;


@Entity
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diaryDayId")
    private DiaryDay diaryDay;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public DiaryDay getDiaryDay() {
        return diaryDay;
    }

    public void setDiaryDay(DiaryDay diaryDay) {
        this.diaryDay = diaryDay;
    }
}
