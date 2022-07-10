package com.studenttracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.studenttracker.model.Student;
import com.studenttracker.repository.StudentRepository;

@Service
public class StudentServiceImpl implements IStudentService {
	private final StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	@Override
	public Student findById(long id) {
		return studentRepository.findById(id).get();
	}

	@Override
	public void save(Student student) {
		studentRepository.save(student);
	}

	@Override
	public void deleteById(long id) {
		studentRepository.deleteById(id);
	}
}
