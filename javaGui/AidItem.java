package project1;
public abstract class AidItem {
	private String idd;
	protected String name;
	private int quantity;
	private int priorityLevel;
	private boolean available;
	public AidItem( String idd, String name, int quantity, int priorityLevel) {
	this.idd=idd;
	this.name=name;
	this.quantity=quantity;
	this.priorityLevel=priorityLevel;
	
	}
	
	public void distribute(int qty) {
		if (qty <= 0) return;
        if (qty > quantity) return;

        quantity -= qty;
        available = quantity > 0;
		
	}
	public boolean isAvailable() {
		return available;
	
	}
	abstract public String getInfo();
public String getName() {
	return name;
}
public String getId() {
    return idd;
}

public int getQuantity() {
    return quantity;
}

public int getPriorityLevel() {
    return priorityLevel;
}

}


	


