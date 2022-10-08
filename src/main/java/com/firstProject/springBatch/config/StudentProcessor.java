package com.firstProject.springBatch.config;

import com.firstProject.springBatch.domain.Student;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.time.Month;

public class StudentProcessor implements ItemProcessor<Student,Student> {
    @Override
    public Student process(Student student) throws Exception {
        final String first = student.getFirst();
        final String last = student.getLast();
        final Double GPA = student.getGPA();
        final Integer AGE = student.getAGE();

        final int year =  LocalDate.now().getYear() - AGE;
        final LocalDate DOB = LocalDate.of(year, Month.JANUARY,1);
        final Student student1 = new Student(first,last,GPA,DOB);
        return student1;
    }
}
