package project1;
public abstract class Person {
	private String id;
	private String name;
	private String location;
	private String phone;
	public Person() {
		this.id=" ";
		this.location=" ";
		this.name=" ";
		this.phone=" ";
	}
	public Person(String name,String id,String location,String phone) {
		this.id=id;
		this.location=location;
		this.name=name;
		this.phone=phone;
		
	}
	public String getID() {
		return id;
	}
public String getName() {
	return name;
}
public String getLocation() {
	return location;
}
public String getPhone() {
	return phone;
}
}
