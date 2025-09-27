package project1;
import java.util.ArrayList;
import java.util.List;
public class Beneficiary extends Person {
	
private int familySize;
private List<String>needs;
private List< AidItem>receiveditems;
public Beneficiary() {
	super();
	this.familySize=0;
	this.needs=new ArrayList<String>();
	this.receiveditems=new ArrayList<AidItem>();
}
	

	public Beneficiary(String name,String id,String location,String phone,int familySize,List<String>needs) {
		super(name,id,location,phone);
		this.familySize=familySize;
		this.needs=needs;
		this.receiveditems=new ArrayList<>();
		
	}
	public void addReceivedItem(AidItem item) {
	    if (item != null) {
	        receiveditems.add(item);
	    }
	}

	public List<String>getNeeds(){
		return needs;
	}
	public int getFamilySize() {
		return familySize;
		
	}
	public List<AidItem>getReceiveditems(){
		return receiveditems;
	}
}


