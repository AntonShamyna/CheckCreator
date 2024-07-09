package main.java.ru.clevertec.check;

public class ProductBought {
    private Product product;
    private Integer quantity;
    private Float discount;
    private Float total;

    public ProductBought(Product product, Integer quantity){
        this.product = product;
        this.quantity = quantity;
        this.discount = null;
        this.total = null;
    }

    public ProductBought(Integer id, Integer quantity, InfoBase infoBase){
        for (Product p : infoBase.getProductsInfo()) {
            if (p.getId() == id) {
                setProduct(p);
            }
        }
        setQuantity(quantity);

    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductBought() {
        product = null;
        quantity = 0;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getNumber() {
        return quantity;
    }

    public void setNumber(Integer number) {
        this.quantity = number;
    }
}
