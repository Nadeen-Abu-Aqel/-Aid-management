package project1;

public class BookPackage extends AidItem implements Distributable {
    private String subject;
    private String author;
    private String title;

    public BookPackage(String id, String name, int quantity, int priorityLevel, String subject, String author, String title) {
        super(id, name, quantity, priorityLevel);
        this.subject = subject;
        this.author = author;
        this.title = title;
    }

    @Override
    public String getInfo() {
        return String.format("Book [%s] Title: %s, Author: %s, Subject: %s, Qty: %d",
        		getId(), title, author, subject, getQuantity());
    }

    @Override
    public void assignTo(Beneficiary beneficiary) {
        if (beneficiary == null) return;
        if (!isAvailable()) {
            System.out.println("BookPackage not available: " + title);
            return;
        } 
        distribute(1);
        beneficiary.addReceivedItem(this);
        System.out.println("Book package assigned to: " + beneficiary.getName() + " -> " + title);
    }
   
}
