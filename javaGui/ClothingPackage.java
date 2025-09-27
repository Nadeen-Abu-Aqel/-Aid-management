package project1;

public class ClothingPackage extends AidItem implements Distributable {
    private String sizeRange;
    private boolean isWinterClothing;

    public ClothingPackage(String id, String name, int quantity, int priorityLevel, String sizeRange, boolean isWinterClothing) {
        super(id, name, quantity, priorityLevel);
        this.sizeRange = sizeRange;
        this.isWinterClothing = isWinterClothing;
    }

    @Override
    public String getInfo() {
        return String.format("Clothing [%s] Size Range: %s, Winter: %s, Qty: %d",
                getId(), sizeRange, isWinterClothing ? "Yes" : "No", getQuantity());
    }

    @Override
    public void assignTo(Beneficiary beneficiary) {
        if (beneficiary == null) return;
        if (!isAvailable()) {
            System.out.println("Clothing package not available.");
            return;
        }
        distribute(1);
        beneficiary.addReceivedItem(this);
        System.out.println("Clothing package assigned to: " + beneficiary.getName());
    }
}
