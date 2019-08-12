package org.genanik;

import org.genanik.util.*;

import java.io.*;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
        autoOperator operater = new autoOperator();
        Scanner sc = new Scanner(System.in);

        if (!operater.verNFCDevices()) {
            System.err.println("No NFC Device(s)!");
            //return;
        }



        while (true){
            System.out.println("================================================");
            System.out.println("1. Scanner Water Card");
            System.out.println("2. Create New Water Card From Blank Card");
            System.out.println("3. About");
            System.out.println("4. Debug");
            System.out.println("5. Exit");
            System.out.println("================================================");
            System.out.print("Please Select Code:");
            String selFunc = sc.nextLine();

            switch (selFunc){
                case "1":
                    operater.autoReadFullWaterCard();
                    break;
                case "2":
                    operater.autoCreateNewWaterCardInBlankCard();
                    break;
                case "3":
                    aboutFunc();
                    break;
                case "4":
                    //No func
                    break;
                case "5":
                    return;
                default:
                    System.err.println("Error At Sel Func");
            }
        }
    }

    private static void aboutFunc(){
        System.out.println("================================================");
        System.out.println("auther:\t\t\t\tGenanik Qu");
        System.out.println("IDE:\t\t\t\tInteliJ IDEA");
        System.out.println("Test Sys:\t\t\tmacOS 10.14.6");
        System.out.println("Test Java Version:\tJava 8");
        System.out.println("Test NFC Device:\tPN532");
        System.out.println("================================================");
    }
}
