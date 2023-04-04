package models;

import lombok.Data;

import java.util.List;

@Data
public class DepositRequest {
    private double tax;
    private double amount;
    private double period;
    private double percent;
    private boolean capitalisation;
    private String frequency;
    private List<Double> deposits;
    private List<Double> withdrawals;
}
