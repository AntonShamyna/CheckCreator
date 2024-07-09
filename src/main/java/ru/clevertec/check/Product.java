package main.java.ru.clevertec.check;

import java.util.List;

public class Product {
    private Integer id;
    private String description;
    private Float price;
    private Integer quantityInStock;
    private Boolean wholesale;

    public Product(List<String> str) {
        this.setId(Integer.parseInt(str.get(0)));
        this.setDescription(str.get(1));
        this.setPrice(Float.parseFloat(str.get(2)));
        this.setQuantityInStock(Integer.parseInt(str.get(3)));
        switch (str.get(4)) {
            case "+" :
                this.setWholesale(true);
                break;
            case "-" :
                this.setWholesale(false);
                break;
            default :
                System.out.printf("Invalid wholesale value for id %d", this.getId());
        }
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public boolean isWholesale() {
        return wholesale;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public void setWholesale(boolean wholesale) {
        this.wholesale = wholesale;
    }
}
