package com.studenttracker.service;

import java.util.List;

import com.studenttracker.model.Student;

public interface IStudentService {
	public List<Student> findAll();

	public Student findById(long id);

	public void save(Student student);

	public void deleteById(long id);
}
