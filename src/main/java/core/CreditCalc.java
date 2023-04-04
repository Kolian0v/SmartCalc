package core;

public class CreditCalc {
    private int months;

    private final double percent;
    private final double amount;
    private final double period;

    private double totalPay;
    private double monthlyPay;
    private double overPay;

    public CreditCalc(double amount, double period, double percent) throws Exception {
        if (period <= 0) {
            throw new Exception("The credit term should be more than 0");
        }

        if (percent < 0) {
            throw new Exception("Interest rate must be greater than or equal to 0");
        }

        this.percent = percent;
        this.amount = amount;
        this.period = period;
    }

    public void calculateDifferentiated() {
        double currentAmount = amount - (amount / period) * months;
        totalPay = monthlyPay;
        overPay = currentAmount * percent / 1200;
        monthlyPay = amount / period + currentAmount * percent / 1200;
        months++;
    }

    public void calculateAnnuity() {
        if (percent == 0.0) {
            monthlyPay = amount / period;
        } else {
            monthlyPay = (amount * (percent / 1200)) /
                                                       (1 - Math.pow(1 + percent / 1200, -period));
        }

        totalPay = monthlyPay * period;
        overPay = totalPay - amount;
    }

    public double getTotalPay() {
        return Math.round(totalPay * 100.0) / 100.0;
    }
    public double getOverPay() {
        return Math.round(overPay * 100.0) / 100.0;
    }
    public double getMonthlyPay() {
        return Math.round(monthlyPay * 100.0) / 100.0;
    }

}
