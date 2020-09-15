package academy.model.pojo;

public class User {

    public static final int ROL_STUDENT = 1;
    public static final int ROL_PROFESSOR = 2;
    
    
    private int id;
    private String name;
    private String surname;
    private int role;
    private String password;

    public User() {
	super();
	this.id = 0;
	this.name = "";
	this.surname = "";
	this.role = ROL_STUDENT; // We use student as default. Just a personal option
	this.password = "123456";
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static int getRolStudent() {
        return ROL_STUDENT;
    }

    public static int getRolProfessor() {
        return ROL_PROFESSOR;
    }

    @Override
    public String toString() {
	return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", role=" + role + ", password=" + password + "]";
    }    

}
