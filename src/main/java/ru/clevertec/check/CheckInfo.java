package main.java.ru.clevertec.check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CheckInfo {
    private Date dateTimeCurrent;
    private List<ProductBought> productsBought;
    private Discount discountCard;
    private Errors errors;
    private Float balanceDebitCard;
    private Float totalPrice;
    private Float totalDiscount;
    private Float totalWithDiscount;

    {
        discountCard = null;
        balanceDebitCard = null;
        totalPrice = 0F;
        totalDiscount = 0F;
    }

    public CheckInfo(Errors errors) {
        this.errors = errors;
        this.productsBought = new ArrayList<ProductBought>();
        this.dateTimeCurrent = new Date();
    }

    public void calculate() {
        for (ProductBought p : productsBought) {
            p.setTotal(p.getQuantity() * p.getProduct().getPrice());
            totalPrice += p.getTotal();
            if (p.getProduct().isWholesale() && p.getQuantity() >= 5) {
                p.setDiscount(p.getTotal() * (float) 0.1);
            } else if (discountCard != null) {
                p.setDiscount(p.getTotal() * discountCard.getDiscountAmount() / (float) 100);
            } else {
                p.setDiscount(0F);
            }
            totalDiscount += p.getDiscount();
        }
        totalWithDiscount = totalPrice - totalDiscount;
        if (balanceDebitCard < totalWithDiscount) {
            errors.printErrorAndExit(2);
        }
    }

    public Discount getDiscountCard() {
        return discountCard;
    }

    public void setDiscountCard(Discount discountCard) {
        this.discountCard = discountCard;
    }

    public Float getBalanceDebitCard() {
        return balanceDebitCard;
    }

    public void setBalanceDebitCard(Float balanceDebitCard) {
        this.balanceDebitCard = balanceDebitCard;
    }

    public Date getDateTimeCurrent() {
        return dateTimeCurrent;
    }

    public void setDateTimeCurrent(Date dateTimeCurrent) {
        this.dateTimeCurrent = dateTimeCurrent;
    }

    public List<ProductBought> getProductsBought() {
        return productsBought;
    }

    public void setProductsBought(List<ProductBought> productsBought) {
        this.productsBought = productsBought;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Float getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Float totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public Float getTotalWithDiscount() {
        return totalWithDiscount;
    }

    public void setTotalWithDiscount(Float totalWithDiscount) {
        this.totalWithDiscount = totalWithDiscount;
    }
}
