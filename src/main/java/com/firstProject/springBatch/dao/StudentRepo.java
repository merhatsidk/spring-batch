package com.firstProject.springBatch.dao;

import com.firstProject.springBatch.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Long> {


}
