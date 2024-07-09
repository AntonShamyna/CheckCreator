package main.java.ru.clevertec.check;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Errors {
    List<String> errorMsgs;
    Integer errorCode;

    {
        errorMsgs = Arrays.asList("", "BAD REQUEST","NOT ENOUGH MONEY","INTERNAL SERVER ERROR");
        errorCode = 0;
    }

    public void printErrorAndExit(Integer errorCode) {
        this.errorCode = errorCode;
        try {
            FileWriter writer = new FileWriter("result.csv");
            writer.write("ERROR\n" + errorMsgs.get(errorCode));
            writer.close();
        } catch (IOException e) {
            System.out.println("INTERNAL SERVER ERROR");
            e.printStackTrace();
        }
        System.exit(errorCode);
    }

    public List<String> getErrorMsgs() {
        return errorMsgs;
    }

    public void setErrorMsgs(List<String> errorMsgs) {
        this.errorMsgs = errorMsgs;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorMsgCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
