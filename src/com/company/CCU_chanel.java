package com.company;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

public class CCU_chanel {
    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\teuta\\Desktop\\Regression\\generateMT\\src\\test\\resources\\mtFiles\\Return.txt";
        String mtFile = readFileAsString(path);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyMMdd");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd'");
        SimpleDateFormat formatter4 = new SimpleDateFormat(" yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        SimpleDateFormat nameConventionForMT1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat nameConventionForMT2 = new SimpleDateFormat("HHmmss");


        Random rnd_1 = new Random();
        int number_1 = rnd_1.nextInt(99);
        String ref2 = String.format("%02d", number_1);

        Random rnd_2 = new Random();
        int number_2 = rnd_2.nextInt(99);
        String ref3 = String.format("%02d", number_2);

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
                System.out.println("Please input Creditor Name:");
                String creditorName;
                if(sc.hasNext("[a-zA-Z ]+")){
                    creditorName = sc.nextLine();
                    System.out.println("The creditor name to return the money to is: "+ creditorName);

                                        System.out.println("Please input Debtor Name:");
                                        String debtorName;
                                        if(sc.hasNext("[a-zA-Z0-9]+")) {
                                            debtorName = sc.nextLine();
                                            System.out.println("Debtor Name is: " + debtorName);

                                            System.out.println("Please input Debtor Address: ");
                                            String debtorAddress;
                                            if (sc.hasNext("[a-zA-Z0-9,]+")) {
                                                debtorAddress = sc.nextLine();
                                                System.out.println("Debtor address is: " + debtorAddress);

                                                System.out.println("Please input Original Payment reference: ");


                                                    String finalString = mtFile
                                                            .replaceAll("<Insert_Date>", formatter1.format(calendar.getTime()))
                                                            .replaceAll("<Insert_Time>", nameConventionForMT2.format(calendar.getTime()))
                                                            .replaceAll("<Insert_Amount>", amount)
                                                            .replaceAll("<Insert_Currency>", currency.toUpperCase())
                                                            .replaceAll("<Amount>", amount)
                                                            .replaceAll("<Creditor_Name>", creditorName)
                                                            .replaceAll("<Debtor_Name>",debtorName)
                                                            .replaceAll("<Debtor_Address>",debtorAddress);


                                                    String newFilePath = "C:\\Users\\teuta\\Desktop\\Regression\\generateMT\\src\\test\\resources\\mtFiles\\generatedPayments\\IncomingPayments\\Return\\Pacs_IBE_BBK2022.SCF-PACS008-00"+ ref2 +"_" +nameConventionForMT1 + nameConventionForMT2 + "000"+ ref3 + ".xml";
                                                    File outputFile = new File(newFilePath);
                                                    FileWriter fw = new FileWriter(outputFile);


                                                    fw.write(finalString);
                                                    fw.close();
                                                } else {
                                                    System.out.println("Please don't type letters in the TPH Reference!");
                                                }
                                            }else{
                                                System.out.println("Wrong address format!");
                                            }
                                        }else{
                                            System.out.println("Wrong Debtor Name Format!");
                                        }
                                    }else{
                                        System.out.println("Wrong Account Format!");
                                    }
                                } else {
                                    System.out.println("Wrong BIC format, should consist of 11 characters!");
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
