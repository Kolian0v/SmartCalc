package models;

import lombok.Data;

@Data
public class DepositResponse {
    private double profit;
    private double taxes;
    private double total;
    private String status;
    private String message;
}
