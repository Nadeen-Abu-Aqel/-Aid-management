package project1;

import java.time.LocalDate;

public class FoodPackage extends ExpirableItem implements Distributable {
    private boolean containsCannedGoods;

    public FoodPackage(String id, String name, int quantity, int priorityLevel, LocalDate expiryDate, boolean containsCannedGoods) {
        super(id, name, quantity, priorityLevel, expiryDate);
        this.containsCannedGoods = containsCannedGoods;
    }

    @Override
    public String getInfo() {
        return String.format("Food [%s] %s, Expires in %d days, Quantity: %d",
                getId(), (containsCannedGoods ? "Has canned goods" : "No canned goods"),
                getRemainingDays(), getQuantity());
    }

    @Override
    public void assignTo(Beneficiary beneficiary) {
        if (beneficiary == null) return;
        if (isExpired()) {
            System.out.println("Cannot assign food package — expired.");
            return;
        }
        if (!isAvailable()) {
            System.out.println("Food package not available.");
            return;
        }
        distribute(1);
        beneficiary.addReceivedItem(this);
        System.out.println("Food package assigned to: " + beneficiary.getName());
    }
}
