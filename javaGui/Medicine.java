package project1;

import java.time.LocalDate;

public class Medicine extends ExpirableItem implements Distributable {
    private boolean prescriptionRequired;

    public Medicine(String id, String name, int quantity, int priorityLevel, LocalDate expiryDate, boolean prescriptionRequired) {
        super(id, name, quantity, priorityLevel, expiryDate);
        this.prescriptionRequired = prescriptionRequired;
    }

    @Override
    public String getInfo() {
        return String.format("Medicine [%s] %s, Expires in %d days, Qty: %d, Prescription: %s",
                getId(), getName(), getRemainingDays(), getQuantity(),
                prescriptionRequired ? "Required" : "Not Required");
    }

    @Override
    public void assignTo(Beneficiary beneficiary) {
        if (beneficiary == null) return;
        if (isExpired()) {
            System.out.println("Cannot assign medicine — expired.");
            return;
        }
        if (!isAvailable()) {
            System.out.println("Medicine not available.");
            return;
        }
        distribute(1);
        beneficiary.addReceivedItem(this);
        System.out.println("Medicine assigned to: " + beneficiary.getName());
    }
}
