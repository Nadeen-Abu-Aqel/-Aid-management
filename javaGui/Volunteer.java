package project1;

import java.util.ArrayList;
import java.util.List;

public class Volunteer extends Person {
    private String assignedSector;
    private List<AidItem> deliveredItems;

    public Volunteer() {
        super();
        this.assignedSector = "";
        this.deliveredItems = new ArrayList<>();
    }

    public Volunteer(String name, String id, String location, String phone, String assignedSector) {
        super(name, id, location, phone);
        this.assignedSector = assignedSector;
        this.deliveredItems = new ArrayList<>();
    }

    public String getAssignedSector() {
        return assignedSector;
    }

    public List<AidItem> getDeliveredItems() {
        return deliveredItems;
    }

    public void addDeliveredItem(AidItem item) {
        if (item != null) deliveredItems.add(item);
    }
}
