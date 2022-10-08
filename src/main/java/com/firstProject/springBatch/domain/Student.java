package com.firstProject.springBatch.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    private String first;
    private String last;
    private Double GPA;
    @Transient
    private Integer AGE;
    private LocalDate DOB;

    public Student(String first, String last, Double GPA, LocalDate DOB) {
        this.first = first;
        this.last = last;
        this.GPA = GPA;
        this.DOB = DOB;
    }
}
