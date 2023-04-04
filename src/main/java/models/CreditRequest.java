package models;

import lombok.Data;

@Data
public class CreditRequest {
    private final double percent;
    private final double amount;
    private final double period;
    private final String type;
}
