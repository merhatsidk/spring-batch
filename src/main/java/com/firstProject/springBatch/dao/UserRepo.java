package com.firstProject.springBatch.dao;

import com.firstProject.springBatch.domain.AppUser;
import com.firstProject.springBatch.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
}
