package academy.model.pojo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class User {

    public static final int ROL_STUDENT = 1;
    public static final int ROL_PROFESSOR = 2;
    
    
    private int id;
    
    @NotBlank
    @Size(min = 3, max = 49, message = "The name must have between 3 and 49 characters")
    private String name;
    
    @NotBlank
    @Size(min = 3, max = 99, message = "The surname must have between 3 and 99 characters")
    private String surname;
    
    private int role;
    
    @NotBlank
    @Size(min = 4, max = 12, message = "The password must have between 4 and 12 characters")
    private String password;

    public User() {
	super();
	this.id = 0;
	this.name = "";
	this.surname = "";
	this.role = 0;
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
