package DTO;

import java.util.Date;

public class PromotionDTO {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private double salePercent;  // Tỷ lệ giảm giá
    private Date startDate;  // Ngày bắt đầu khuyến mãi
    private Date endDate;  // Ngày kết thúc khuyến mãi

    public PromotionDTO() {
    }

    public PromotionDTO(double salePercent, Date startDate, Date endDate) {
        this.salePercent = salePercent;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public double getSalePercent() {
        return salePercent;
    }

    public void setSalePercent(double salePercent) {
        this.salePercent = salePercent;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
