package DTO;

import java.util.Date;

public class SaleDTO {
    private double salePersent;

    private Date saleStartDate;

    private Date saleEndDate;

    public double getSalePersent() {
        return salePersent;
    }

    public void setSalePersent(double salePersent) {
        this.salePersent = salePersent;
    }

    public Date getSaleStartDate() {
        return saleStartDate;
    }

    public void setSaleStartDate(Date saleStartDate) {
        this.saleStartDate = saleStartDate;
    }

    public Date getSaleEndDate() {
        return saleEndDate;
    }

    public void setSaleEndDate(Date saleEndDate) {
        this.saleEndDate = saleEndDate;
    }

    @Override
    public String toString() {
        return "SaleDTO{" +
                "salePersent=" + salePersent +
                ", saleStartDate=" + saleStartDate +
                ", saleEndDate=" + saleEndDate +
                '}';
    }
}
