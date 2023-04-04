package core;

import java.util.List;
import java.util.Objects;

public class DepositCalc {
    private static final double NO_TAX_AMOUNT = 75000.0;

    private double amount;
    private double profit;
    private double taxes;
    private double total;
    private double percent;
    private double period;
    private double tax;
    private boolean capitalisation;
    private String frequency;
    private List<Double> deposits;
    private List<Double> withdrawals;


    public void calculate() throws Exception {
        if (withdrawals != null) {
            amount -= withdrawals.
                    stream().
                    filter(Objects::nonNull).
                    reduce(0.0, Double::sum);
        }
        if (deposits != null) {
            amount += deposits.
                    stream().
                    filter(Objects::nonNull).
                    reduce(0.0, Double::sum);
        }

        if (tax < 0 || percent < 0) {
            throw new Exception("The bet must be greater than or equal to 0");
        }
        if (period <= 0) {
            throw new Exception("Loan term should be longer 0");
        }

        if (capitalisation) {
            int counter = 0;
            switch (frequency) {
                case "yearly":
                    counter = (int) period / 12;
                    break;
                case "quarterly":
                    counter = (int) period / 3;
                    break;
                case "monthly":
                    counter = (int) period;
                    break;
            }

            double newAmount = amount;
            double profit;
            
            for (int i = 0; i < counter; i++) {
                profit = newAmount * (percent / 100) * ((period / counter) / 12);
                profit += profit;
                newAmount += profit;
            }

        } else {
            profit = amount * (percent / 100) * (period / 12);
        }

        double yearProfit = profit / (period / 12);

        if (yearProfit > NO_TAX_AMOUNT) {
            taxes = ((yearProfit - NO_TAX_AMOUNT) * tax / 100) * period / 12;
        }

        total = amount + profit - taxes;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public double getProfit() {
        return profit;
    }
    public double getTaxes() {
        return taxes;
    }
    public double getTotal() {
        return total;
    }
    public void setPercent(double percent) {
        this.percent = percent;
    }
    public void setPeriod(double period) {
        this.period = period;
    }
    public void setTax(double tax) {
        this.tax = tax;
    }
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
    public void setCapitalisation(boolean capitalisation) {
        this.capitalisation = capitalisation;
    }
    public void setDeposits(List<Double> deposits) {
        this.deposits = deposits;
    }
    public void setWithdrawals(List<Double> withdrawals) {
        this.withdrawals = withdrawals;
    }

}
