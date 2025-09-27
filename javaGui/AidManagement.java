package project1;
import project1.AidItem;
import java.util.ArrayList;
import java.util.List;

public class AidManagement {
    private List<AidItem> aidItems;
    private List<Beneficiary> beneficiaries;
    private List<Person> users; // volunteers + staff
    private List<String> distributionLog;

    public AidManagement() {
        aidItems = new ArrayList<>();
        beneficiaries = new ArrayList<>();
        users = new ArrayList<>();
        distributionLog = new ArrayList<>();
    }

    public void registerAidItem(AidItem item) {
        if (item != null) {
            aidItems.add(item);
            System.out.println("Aid added successfully: " + item.getInfo());
        } else {
            System.out.println("Failed to add aid: Item is null.");
        }
    }

    public void showAllAidItems() {
        if (aidItems.isEmpty()) {
            System.out.println("No registered aid items.");
        } else {
            System.out.println("List of aid items:");
            for (AidItem item : aidItems) {
                System.out.println(" - " + item.getInfo());
            }
        }
    }

    public void registerBeneficiary(Beneficiary beneficiary) {
        if (beneficiary != null) {
            beneficiaries.add(beneficiary);
            System.out.println("Beneficiary registered: " + beneficiary.getName());
        } else {
            System.out.println("Failed to register: Beneficiary is null.");
        }
    }

    public void registerUser(Person user) {
        if (user != null) {
            users.add(user);
            System.out.println("User registered: " + user.getName());
        } else {
            System.out.println("Failed to register user: null.");
        }
    }

    public AidItem searchAid(String aidName) {
        for (AidItem item : aidItems) {
            if (item.getName().equalsIgnoreCase(aidName)) {
                return item;
            }
        }
        return null;
    }

    public Beneficiary findBeneficiary(String beneficiaryName) {
        for (Beneficiary b : beneficiaries) {
            if (b.getName().equalsIgnoreCase(beneficiaryName)) return b;
        }
        return null;
    }

    public void distributeAid(String beneficiaryName, String aidName) {
        Beneficiary b = findBeneficiary(beneficiaryName);
        if (b == null) {
            System.out.println("Beneficiary not found: " + beneficiaryName);
            return;
        }
        AidItem item = searchAid(aidName);
        if (item == null) {
            System.out.println("Aid item not found: " + aidName);
            return;
        }
        if (!(item instanceof Distributable)) {
            System.out.println("This aid item cannot be distributed programmatically.");
            return;
        }
        
        ((Distributable) item).assignTo(b);
       
        distributionLog.add(String.format("%s <- %s (remaining: %d)", b.getName(), item.getName(), item.getQuantity()));
    }

    public void viewDistributionReport() {
        System.out.println("=== Distribution Report ===");
        if (distributionLog.isEmpty()) {
            System.out.println("No distributions yet.");
        } else {
            distributionLog.forEach(System.out::println);
        }
        System.out.println("\nBeneficiaries and received items:");
        for (Beneficiary b : beneficiaries) {
            System.out.println("- " + b.getName() + " received:");
            if (b.getReceiveditems().isEmpty()) {
                System.out.println("   (none)");
            } else {
                for (AidItem it : b.getReceiveditems()) {
                    System.out.println("   * " + it.getInfo());
                }
            }
        }
    }

    public void listBeneficiaries() {
        if (beneficiaries.isEmpty()) {
            System.out.println("No beneficiaries registered.");
            return;
        }
        for (Beneficiary b : beneficiaries) {
            System.out.println(" - " + b.getName());
        }
    }

    public void listAidNames() {
        if (aidItems.isEmpty()) {
            System.out.println("No aid items registered.");
            return;
        }
        for (AidItem a : aidItems) {
            System.out.println(" - " + a.getName() + " (Qty: " + a.getQuantity() + ")");
        }
    }
}
