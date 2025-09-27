package project1;
public class OrganizationStaff extends Person {
	private String role;
	private  String OrganizationName;
	public OrganizationStaff() {
		super();
		this.role=" ";
		this.OrganizationName="";
		
	}
	
public OrganizationStaff(String name,String id,String location,String phone,String role,String OrganizationName) {
	super(name,id,location,phone);
		this.OrganizationName=OrganizationName;
		this.role=role;
	}
public String getRole() {
	return role;
}
public String getOrganizationName() {
	return OrganizationName;
}

}
