package com.company;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

public class MTAInstructions_MT202 {
    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\teuta\\Desktop\\Regression\\generateMT\\src\\test\\resources\\mtFiles\\MTAInstructions_MT202_Template.txt";;
        String mtFile = readFileAsString(path);
        //System.out.println(mtFile);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmyyMMdd");
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyMMdd");
        SimpleDateFormat nameConventionForMT1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat nameConventionForMT2 = new SimpleDateFormat("HHmmss");

        Random rnd = new Random();
        int number = rnd.nextInt(999);
        // this will convert any number sequence into 6 character.
        String ref = String.format("ALTB202%03d", number);



        Scanner sc = new Scanner(System.in);
        System.out.println("Please input amount: ");
        String amount;
        if(sc.hasNext("^[0-9,]+$")) {
            amount = sc.nextLine();
            System.out.println("Your specified amount: " + amount);

            System.out.println("Please input currency: ");
            String currency;

            if(sc.hasNext("[a-zA-Z]{3}")) {
                currency = sc.nextLine();
                System.out.println("Your specified currency: " + currency);

                System.out.println("Please input Recipient BIC:");
                String recipient;

                if(sc.hasNext("[a-zA-Z0-9]{11}")) {
                    recipient = sc.nextLine();
                    System.out.println("Your recipient BIC is: " + recipient.toUpperCase());
                    String regularRecipient = addChar(recipient.toUpperCase(), 'A', 8);
                    System.out.println("Please input Beneficiary BIC:");
                    String beneficiary = sc.nextLine();
                    System.out.println("Your beneficiary BIC is: " + beneficiary.toUpperCase());


                    String finalString = mtFile
                            .replaceAll("<Insert_Time_Date>", formatter.format(calendar.getTime()))
                            .replaceAll("<Insert_Date>", formatter1.format(calendar.getTime()))
                            .replaceAll("<Insert_Ref>", ref)
                            .replaceAll("<Insert_Currency>", currency.toUpperCase())
                            .replaceAll("<Insert_Amount>", amount)
                            .replaceAll("<Receiver_BIC>", regularRecipient)
                            .replaceAll("<Beneficiary_BIC>", beneficiary.toUpperCase());


                    String newFilePath = "C:\\Users\\teuta\\Desktop\\Regression\\generateMT\\src\\test\\resources\\mtFiles\\generatedPayments\\IncomingPayments\\Alternative Instructions\\MT202\\MT200_FBE_" + nameConventionForMT1.format(calendar.getTime()) + "." + nameConventionForMT2.format(calendar.getTime()) + ".000.mt";
                    File outputFile = new File(newFilePath);
                    FileWriter fw = new FileWriter(outputFile);


                    fw.write(finalString);
                    fw.close();
                }else{
                    System.out.println("Wrong BIC format, should consist of 11 characters!");
                }
            }else{
                System.out.println("Please input only three letters for Currency Code!");
            }
        }else{
            System.out.println("Your input is a wrong amount format, please use the 'comma' as a separator if using decimal values for your amount!");
        }
    }

    public static String readFileAsString(String file) throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    public static String addChar(String str, char ch, int position) {
        StringBuilder sb = new StringBuilder(str);
        sb.insert(position, ch);
        return sb.toString();
    }
}
