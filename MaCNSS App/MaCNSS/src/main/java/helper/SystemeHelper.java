package helper;

import java.util.Scanner;

public class SystemeHelper {

    public static void print(String message){ System.out.print(message); }
    public static void println(String message){
        System.out.println(message);
    }
    public static Scanner scan(){
        return new Scanner(System.in);
    }
}
