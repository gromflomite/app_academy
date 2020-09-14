package academy.model.pojo;

public class Professor {

    private int id;
    private String name;
    private String surname;

    public Professor() {
	super();
	this.id = 0;
	this.name = "";
	this.surname = "";
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

    @Override
    public String toString() {
	return "Professor [id=" + id + ", name=" + name + ", surname=" + surname + "]";
    }

}
