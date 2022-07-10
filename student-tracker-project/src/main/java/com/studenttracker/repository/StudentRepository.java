package com.studenttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studenttracker.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
