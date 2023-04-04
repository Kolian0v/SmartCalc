package models;

import lombok.Data;

@Data
public class CreditResponse {
    private double overPayment;
    private double totalPayment;
    private String status;
    private String message;
    private String monthlyPayment;
}
