package academy.model.pojo;

public class Course {

    private int id;
    private String name;
    private String identifier;
    private int hours;
    private int id_professor_course;
    private User professor;

    public Course() {
	super();
	this.id = 0;
	this.name = "";
	this.identifier = "";
	this.hours = 0;
	this.id_professor_course = 0;
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

    public User getProfessor() {
	return professor;
    }

    public void setProfessor(User professor) {
	this.professor = professor;
    }

    @Override
    public String toString() {
	return "Course [id=" + id + ", name=" + name + ", identifier=" + identifier + ", hours=" + hours + ", id_professor_course=" + id_professor_course + ", professor=" + professor + "]";
    }

}
