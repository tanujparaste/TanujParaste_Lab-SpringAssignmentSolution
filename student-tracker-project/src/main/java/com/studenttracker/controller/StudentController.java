package com.studenttracker.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.studenttracker.model.Student;
import com.studenttracker.service.IStudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
	private IStudentService studentService;

	// track what action: register or update
	private String action;

	// track if there's any empty fields in the form
	private String emptyFields;

	public StudentController(IStudentService studentService) {
		this.studentService = studentService;
	}

	private String getLoggedUsername() {
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return StringUtils.capitalize(username);
	}

	@RequestMapping("/list")
	public String showAllStudents(Model model) {
		emptyFields = "none";
		List<Student> students = studentService.findAll();
		model.addAttribute("students", students);
		model.addAttribute("username", getLoggedUsername());
		return "list-students";
	}

	@RequestMapping("/add")
	public String add(Model model) {
		action = "register";
		model.addAttribute("emptyFields", emptyFields);
		model.addAttribute("username", getLoggedUsername());
		return "add-student";
	}

	@RequestMapping("/save")
	public String saveRecord(@RequestParam(name = "id", defaultValue = "0") long id,
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("course") String course, @RequestParam("country") String country, Model model) {
		Student student;
		if (!firstName.isEmpty() && !lastName.isEmpty() && !course.isEmpty() && !country.isEmpty()) {
			if (id != 0) {
				student = studentService.findById(id);
				student.setFirstName(firstName);
				student.setLastName(lastName);
				student.setCourse(course);
				student.setCountry(country);
			} else {
				student = new Student(firstName, lastName, course, country);
			}
			studentService.save(student);
			return "redirect:./list";
		} else {
			emptyFields = "";
			if (action.equalsIgnoreCase("register")) {
				student = new Student(firstName, lastName, course, country);
				model.addAttribute("student", student);
				model.addAttribute("emptyFields", emptyFields);
				return "add-student";
			} else {
				return "redirect:./update?id=" + id;
			}
		}
	}

	@RequestMapping("/delete")
	public String deleteRecord(@RequestParam("id") long id) {
		studentService.deleteById(id);
		return "redirect:./list";
	}

	@RequestMapping("/update")
	public String updateRecord(@RequestParam("id") long id, Model model) {
		Student student = studentService.findById(id);
		action = "update";
		model.addAttribute("student", student);
		model.addAttribute("username", getLoggedUsername());
		model.addAttribute("emptyFields", emptyFields);
		return "update-student";
	}

	@RequestMapping("/403")
	public ModelAndView accesssDenied(Principal user) {
		ModelAndView model = new ModelAndView();
		if (user != null) {
			model.addObject("msg", "Hi " + StringUtils.capitalize(user.getName())
					+ ", you do not have permission to perform this operation!");
		} else {
			model.addObject("msg", "You do not have permission to perform this operation!");
		}
		model.addObject("username", StringUtils.capitalize(user.getName()));
		model.setViewName("403");
		return model;
	}
}
