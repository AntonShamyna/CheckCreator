package main.java.ru.clevertec.check;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Check {
    private InfoBase infoBase;
    private CheckInfo checkInfo;
    private Errors errors;


    public Check(InfoBase infoBase, String[] dataString) {
        this.infoBase = infoBase;
        this.errors = new Errors();
        this.checkInfo = new CheckInfo(errors);
        this.readArgs(dataString);
        this.checkInfo.calculate();
    }

    public void readArgs(String[] args) {
        for (String s : args) {
            if (Pattern.matches("[0-9]+-[0-9]+", s)) {
                Pattern p = Pattern.compile("([0-9]+)-([0-9]+)");
                Matcher m = p.matcher(s);
                m.find();
                Integer id = Integer.parseInt(m.group(1));
                if (id < 1 || id > infoBase.getProductsInfo().size())
                    errors.printErrorAndExit(1);
                Integer quantity = Integer.parseInt(m.group(2));
                if (quantity <= 0)
                    errors.printErrorAndExit(1);

                Boolean found = false;
                for (ProductBought pb : checkInfo.getProductsBought()) {
                    if (pb.getProduct().getId() == id) {
                        pb.setQuantity(pb.getQuantity() + quantity);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    checkInfo.getProductsBought().add(new ProductBought(id, quantity, getInfoBase()));
                }
            } else if (Pattern.matches("discountCard=[0-9]{4}", s)) {
                Pattern p = Pattern.compile("discountCard=([0-9]{4})");
                Matcher m = p.matcher(s);
                m.find();
                Integer discountCardNum = Integer.parseInt(m.group(1));
                Boolean found = false;
                for (DiscountSpec d : infoBase.getDiscountsInfo()) {
                    if (d.getNumber().equals(discountCardNum)) {
                        found = true;
                        checkInfo.setDiscountCard(d);
                    }
                }
                if (!found) {
                    checkInfo.setDiscountCard(new DiscountAny(discountCardNum));
                }
            } else if (Pattern.matches("balanceDebitCard=[0-9]+[.]?[0-9]*", s)) {
                Pattern p = Pattern.compile("balanceDebitCard=([0-9]+[.]?[0-9]*)");
                Matcher m = p.matcher(s);
                m.find();
                checkInfo.setBalanceDebitCard(Float.parseFloat(m.group(1)));
            } else
                errors.printErrorAndExit(1);
        }

        if (checkInfo.getProductsBought().isEmpty() || checkInfo.getBalanceDebitCard() == null) {
            errors.printErrorAndExit(1);
        }
    }

    public String generateCheckMsg(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy;HH:mm:ss");
        String text = String.format("Date;Time\n" +
                "%s\n" +
                "\n" +
                "QTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL\n"
                , formatter.format(this.checkInfo.getDateTimeCurrent())
        );
        for (ProductBought p : checkInfo.getProductsBought()) {
            text += String.format(
                    "%d;%s;%.2f$;%.2f$;%.2f$\n",
                    p.getQuantity(), p.getProduct().getDescription(), p.getProduct().getPrice(),
                    p.getDiscount(), p.getTotal()
            );
        }
        if (checkInfo.getDiscountCard() != null) {
            text += String.format("\n" +
                    "DISCOUNT CARD;DISCOUNT PERCENTAGE\n" +
                    "%d;%d%%\n", checkInfo.getDiscountCard().getNumber(), checkInfo.getDiscountCard().discountAmount);
        }
        text += String.format("\n" +
                        "TOTAL PRICE;TOTAL DISCOUNT;TOTAL WITH DISCOUNT\n" +
                        "%.2f$;%.2f$;%.2f$",
                this.checkInfo.getTotalPrice(), this.checkInfo.getTotalDiscount(),
                this.checkInfo.getTotalWithDiscount()
        );
        return text;
    }

    public void printCheck(){
        String checkMsg = this.generateCheckMsg();
        try {
            FileWriter writer = new FileWriter("result.csv");
            writer.write(checkMsg);
            writer.close();
        } catch (IOException e) {
            System.out.println("INTERNAL SERVER ERROR");
            e.printStackTrace();
        }

        System.out.println(checkMsg);
    }

    public InfoBase getInfoBase() {
        return infoBase;
    }

    public void setInfoBase(InfoBase infoBase) {
        this.infoBase = infoBase;
    }

    public CheckInfo getCheckInfo() {
        return checkInfo;
    }

    public void setCheckInfo(CheckInfo checkInfo) {
        this.checkInfo = checkInfo;
    }
}
