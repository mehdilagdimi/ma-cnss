package helper;

import java.util.Scanner;

public class SystemeHelper {

    public static Scanner scanner = new Scanner(System.in);
    public static void print(String message){ System.out.print(message); }
    public static void println(String message){
        System.out.println("\n" + message);
    }
    public static Scanner scan(){
        return scanner;
    }
}
