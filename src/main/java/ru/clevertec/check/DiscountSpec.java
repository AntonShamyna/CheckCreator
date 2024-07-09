package main.java.ru.clevertec.check;

import java.util.List;

public class DiscountSpec extends Discount {
    protected Integer id;

    public DiscountSpec(List<String> str) {
        this.id = Integer.parseInt(str.get(0));
        this.number = Integer.parseInt(str.get(1));
        this.discountAmount = Integer.parseInt(str.get(2));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
