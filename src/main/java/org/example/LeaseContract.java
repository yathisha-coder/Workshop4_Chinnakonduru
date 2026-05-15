package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LeaseContract extends Contract{
    public LeaseContract(String date,String customerName, String customerEmail,Vehicle vehicleSold){
        super(date,customerName,customerEmail,vehicleSold);
    }

    @Override
    public BigDecimal getTotalPrice() {
        return getExpectedEndingValue().add(getLeaseFee()).setScale(2,RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getMonthlyPayment() {
        BigDecimal total = getTotalPrice();
        double monthlyRate = 0.04/12;
        int months = 36;
        double payment = (total.doubleValue()*monthlyRate)/(1-Math.pow(1+monthlyRate, -months));
        return BigDecimal.valueOf(payment).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getExpectedEndingValue(){
        return BigDecimal.valueOf(getVehicleSold().getPrice()).multiply(BigDecimal.valueOf(0.5))
                .setScale(2, RoundingMode.HALF_UP);
    }
    public BigDecimal getLeaseFee(){
        return BigDecimal.valueOf(getVehicleSold().getPrice())
                .multiply(BigDecimal.valueOf(0.07)).setScale(2,RoundingMode.HALF_UP);
    }

}
