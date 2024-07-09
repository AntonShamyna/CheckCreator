package main.java.ru.clevertec.check;


public class CheckRunner {
    public static void main(String[] args) {
        InfoBase infoBase = new InfoBase();
        infoBase.printInfo();
        Check check = new Check(infoBase, args);
        check.printCheck();
    }
}