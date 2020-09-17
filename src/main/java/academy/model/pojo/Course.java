package academy.model.pojo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Course {

    private int id;

    @NotBlank
    @Size(min = 5, max = 99, message = "The course name must have between 5 and 99 characters")
    private String name;

    @NotBlank
    private String identifier;

    @Min(value = 1, message = "The value must be positive and at least 1 hour")
    private int hours;

    private int id_professor_course;

    private int students_enrolled;

    private User professor;

    public Course() {
	super();
	this.id = 0;
	this.name = "";
	this.identifier = "";
	this.hours = 0;
	this.id_professor_course = 0;
	this.students_enrolled = 0;
	this.professor = new User();
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }

    public int getHours() {
	return hours;
    }

    public void setHours(int hours) {
	this.hours = hours;
    }

    public int getId_professor_course() {
	return id_professor_course;
    }

    public void setId_professor_course(int id_professor_course) {
	this.id_professor_course = id_professor_course;
    }

    public int getStudents_enrolled() {
	return students_enrolled;
    }

    public void setStudents_enrolled(int students_enrolled) {
	this.students_enrolled = students_enrolled;
    }

    public User getProfessor() {
	return professor;
    }

    public void setProfessor(User professor) {
	this.professor = professor;
    }

    @Override
    public String toString() {
	return "Course [id=" + id + ", name=" + name + ", identifier=" + identifier + ", hours=" + hours + ", id_professor_course=" + id_professor_course + ", students_enrolled=" + students_enrolled + ", professor=" + professor + "]";
    }

}
