package main.java.ru.clevertec.check;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class InfoBase {
    private String defaultProductsPath = "./src/main/resources/products.csv";
    private String defaultDiscountsPath = "./src/main/resources/discountCards.csv";
    private String delimiter = ";";
    private List<Product> productsInfo = new ArrayList<>();
    private List<DiscountSpec> discountsInfo = new ArrayList<>();


    public InfoBase(String productsPath, String discountsPath) {
        importProducts(productsPath);
        importDiscounts(discountsPath);
    }

    public InfoBase() {
        importProducts(defaultProductsPath);
        importDiscounts(defaultDiscountsPath);
    }

    public void importProducts(String productsPath) {
        List<List<String>> productsInfoStr;

        try (Stream<String> lines = Files.lines(Paths.get(productsPath))) {
            productsInfoStr = lines.map(line -> Arrays.asList(line.split(delimiter))).toList();
        }
        catch (IOException e) {
            System.out.println("Cannot find file products.csv");
            return;
        }

        for (int i = 1; i < productsInfoStr.size(); i++) {
            productsInfo.add(new Product(productsInfoStr.get(i)));
        }
    }

    public void importDiscounts(String discountsPath) {
        List<List<String>> discountsInfoStr;

        try (Stream<String> lines = Files.lines(Paths.get(discountsPath))) {
            discountsInfoStr = lines.map(line -> Arrays.asList(line.split(delimiter))).toList();
        }
        catch (IOException e) {
            System.out.println("Cannot find file discounts.csv");
            return;
        }
        for (int i = 1; i < discountsInfoStr.size(); i++) {
            discountsInfo.add(new DiscountSpec(discountsInfoStr.get(i)));
        }
    }

    public void printInfo() {
        for (Product p : productsInfo){
            System.out.printf("%-5d | %-30s | %-5.2f | %-5d | %-10b\n",
                    p.getId(), p.getDescription(), p.getPrice(), p.getQuantityInStock(), p.isWholesale());
        }
        System.out.println();
        for (DiscountSpec d : discountsInfo){
            System.out.printf("%-5d | %-5d | %-5d\n",
                    d.getId(), d.getNumber(), d.getDiscountAmount());
        }
        System.out.println();
    }

    public List<Product> getProductsInfo() {
        return productsInfo;
    }

    public void setProductsInfo(List<Product> productsInfo) {
        this.productsInfo = productsInfo;
    }

    public List<DiscountSpec> getDiscountsInfo() {
        return discountsInfo;
    }

    public void setDiscountsInfo(List<DiscountSpec> discountsInfo) {
        this.discountsInfo = discountsInfo;
    }
}

