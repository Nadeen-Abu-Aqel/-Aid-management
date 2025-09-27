package project1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TesterClass {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        AidManagement manager = new AidManagement();

        int choice;
        do {
            System.out.println("========= COMMUNITY RESOURCE MANAGEMENT =========");
            System.out.println("1. Register a Beneficiary");
            System.out.println("2. Register a Volunteer or Organization Staff");
            System.out.println("3. Add an Aid Item");
            System.out.println("4. Show Available Aid Items");
            System.out.println("5. Request Aid (by Beneficiary)  [shows only request]");
            System.out.println("6. Assign Aid");
            System.out.println("7. View Distribution Report");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            while (!input.hasNextInt()) {
                System.out.print("Please enter a number: ");
                input.next();
            }
            choice = input.nextInt();
            input.nextLine(); 

            switch (choice) {
                case 1:
      System.out.print("Enter beneficiary name: ");
                    String bName = input.nextLine();
                    System.out.print("Enter beneficiary id (string): ");
                    String bId = input.nextLine();
                    System.out.print("Enter location: ");
                    String location = input.nextLine();
                    System.out.print("Enter phone: ");
                    String phone = input.nextLine();
                    System.out.print("Enter family size (int): ");
                    int familySize = readIntSafe(input);
                    List<String> needs = new ArrayList<>();
                    System.out.print("How many needs to enter? ");
                    int needCount = readIntSafe(input);
                    input.nextLine();
                    for (int i = 0; i < needCount; i++) {
                        System.out.print("Enter need " + (i + 1) + ": ");
                        needs.add(input.nextLine());
                    }
                    Beneficiary b = new Beneficiary(bName, bId, location, phone, familySize, needs);
                    manager.registerBeneficiary(b);
                    break;

                case 2: 
                    System.out.print("Register type (1-Volunteer, 2-OrganizationStaff): ");
                    int x = readIntSafe(input);
                    input.nextLine();
                    System.out.print("Enter name: ");
                    String name = input.nextLine();
                    System.out.print("Enter id: ");
                    String id = input.nextLine();
                    System.out.print("Enter location: ");
                    String loc = input.nextLine();
                    System.out.print("Enter phone: ");
                    String ph = input.nextLine();
                    if (x == 1) {
                        System.out.print("Enter assigned sector: ");
                        String sector = input.nextLine();
                        Volunteer v = new Volunteer(name, id, loc, ph, sector);
                        manager.registerUser(v);
                    } else {
                        System.out.print("Enter role: ");
                        String role = input.nextLine();
                        System.out.print("Enter organization name: ");
                        String org = input.nextLine();
                        OrganizationStaff orgg = new OrganizationStaff(name, id, loc, ph, role, org);
                        manager.registerUser(orgg);
                    }
                    break;

                case 3: 
                    System.out.println("Choose aid type: 1-Book, 2-Clothing, 3-Food, 4-Medicine");
                    int type = readIntSafe(input);
                    input.nextLine();
                    System.out.print("Enter aid ID: ");
                    String aidId = input.nextLine();
                    System.out.print("Enter aid name: ");
                    String aidName = input.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = readIntSafe(input);
                    System.out.print("Enter priority level (1-high ...): ");
                    int priorityLevel = readIntSafe(input);
                    input.nextLine();

                    switch (type) {
                        case 1:
                            System.out.print("Enter book subject: ");
                            String subject = input.nextLine();
                            System.out.print("Enter book author: ");
                            String author = input.nextLine();
                            System.out.print("Enter book title: ");
                            String title = input.nextLine();
                            BookPackage bp = new BookPackage(aidId, aidName, quantity, priorityLevel, subject, author, title);
                            manager.registerAidItem(bp);
                            break;
                        case 2:
                            System.out.print("Enter size range (e.g. S-M-L): ");
                            String sizeRange = input.nextLine();
                            System.out.print("Is winter clothing? (true/false): ");
                            boolean isWinter = Boolean.parseBoolean(input.nextLine());
                            ClothingPackage cp = new ClothingPackage(aidId, aidName, quantity, priorityLevel, sizeRange, isWinter);
                            manager.registerAidItem(cp);
                            break;
                        case 3:
                            System.out.print("Enter expiry date (YYYY-MM-DD): ");
                            LocalDate expiryFood = LocalDate.parse(input.nextLine());
                            System.out.print("Contains canned goods? (true/false): ");
                            boolean canned = Boolean.parseBoolean(input.nextLine());
                            FoodPackage fp = new FoodPackage(aidId, aidName, quantity, priorityLevel, expiryFood, canned);
                            manager.registerAidItem(fp);
                            break;
                        case 4:
                            System.out.print("Enter expiry date (YYYY-MM-DD): ");
                            LocalDate expiryMed = LocalDate.parse(input.nextLine());
                            System.out.print("Prescription required? (true/false): ");
                            boolean pres = Boolean.parseBoolean(input.nextLine());
                            Medicine med = new Medicine(aidId, aidName, quantity, priorityLevel, expiryMed, pres);
                            manager.registerAidItem(med);
                            break;
                        default:
                            System.out.println("Unknown type.");
                    }
                    break;

                case 4:
                    manager.showAllAidItems();
                    break;

                case 5:
                    System.out.println("Request Aid (this only records request message).");
                    System.out.print("Enter beneficiary name: ");
                    String rbName = input.nextLine();
                    System.out.print("Enter aid name to request: ");
                    String rAidName = input.nextLine();
                    System.out.println(rbName + " requested aid: " + rAidName);
                    break;

                case 6:
                    System.out.print("Enter beneficiary name: ");
                    String abName = input.nextLine();
                    System.out.println("Available aid items:");
                    manager.listAidNames();
                    System.out.print("Enter aid name to assign: ");
                    String aAidName = input.nextLine();
                    manager.distributeAid(abName, aAidName);
                    break;

                case 7:
                    manager.viewDistributionReport();
                    break;

                case 8:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

            System.out.println();
        } while (choice != 8);

        input.close();
    }

    private static int readIntSafe(Scanner sc) {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Please enter an integer: ");
        }
        return sc.nextInt();
    }
}
