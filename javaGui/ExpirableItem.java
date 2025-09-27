package project1;

import java.time.*;
import java.time.temporal.ChronoUnit;
public abstract class ExpirableItem extends AidItem {
	
	private  LocalDate expiryDate;

    public ExpirableItem(String id, String name, int quantity, int priorityLevel, LocalDate expiryDate) {
        super(id, name, quantity, priorityLevel);
        this.expiryDate = expiryDate;
    }
    public boolean isExpired() {
        return expiryDate != null && LocalDate.now().isAfter(expiryDate);
    }

    public int getRemainingDays() {
        if (expiryDate == null) return Integer.MAX_VALUE;
        if (isExpired()) return 0;
        return (int) ChronoUnit.DAYS.between(LocalDate.now(), expiryDate);
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
	

}