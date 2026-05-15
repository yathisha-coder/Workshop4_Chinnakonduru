package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalesContract extends Contract{
    private boolean finance;
    public SalesContract(String date, String customerName,String customerEmail,
                         Vehicle vehicleSold, boolean finance){
        super(date,customerName,customerEmail,vehicleSold);
        this.finance = finance;
    }
    public boolean getFinance(){
        return finance;
    }
    public void setFinance(boolean finance){
        this.finance = finance;
    }
    public BigDecimal getSalesTax(){
        return BigDecimal.valueOf(getVehicleSold().getPrice())
                .multiply(BigDecimal.valueOf(0.05)).setScale(2, RoundingMode.HALF_UP);
    }
    public BigDecimal getRecordingFee(){
        return BigDecimal.valueOf(100);
    }
    public BigDecimal getProcessingFee(){
        if(getVehicleSold().getPrice() < 10000){
            return BigDecimal.valueOf(295);
        }
        return BigDecimal.valueOf(495);
    }

    @Override
    public BigDecimal getTotalPrice() {
        return BigDecimal.valueOf(getVehicleSold().getPrice())
                .add(getSalesTax()).add(getRecordingFee()).add(getProcessingFee().setScale(2,RoundingMode.HALF_UP));
    }

    @Override
    public BigDecimal getMonthlyPayment() {
       if(!finance){
           return BigDecimal.ZERO;
       }
       BigDecimal totalPrice = getTotalPrice();
       BigDecimal annualRate;
       int months;
       if(totalPrice.compareTo(BigDecimal.valueOf(10000)) >= 0){
           annualRate = new BigDecimal("4.25");
           months= 48;
       }else {
           annualRate = new BigDecimal("5.25");
           months= 24;
       }
       BigDecimal monthlyRateBigDecimal = annualRate.divide(BigDecimal.valueOf(100),
               10,RoundingMode.HALF_UP).divide(BigDecimal.valueOf(12),10,
               RoundingMode.HALF_UP);
       double monthlyRate = monthlyRateBigDecimal.doubleValue();
       double payment = (totalPrice.doubleValue()*monthlyRate)/(1-Math.pow(1+monthlyRate,-months));

        return BigDecimal.valueOf(payment).setScale(2,RoundingMode.HALF_UP);
    }
}
