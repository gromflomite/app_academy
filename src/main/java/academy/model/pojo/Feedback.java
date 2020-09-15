package academy.model.pojo;

public class Feedback {

    private String type;
    private String message;

    public Feedback() {
	super();
	this.type = "";
	this.message = "";
    }

    public Feedback(String type, String message) {
	this();
	this.type = type;
	this.message = message;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    @Override
    public String toString() {
	return "Feedback [type=" + type + ", message=" + message + "]";
    }

}
