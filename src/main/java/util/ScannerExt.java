package util;

import model.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ScannerExt {

    private final Scanner scanner;

    public ScannerExt(Scanner scanner){
        this.scanner = scanner;
    }

    public String scanField(){
        return this.scanField(true);
    }

    public String scanField(boolean required){
        boolean invalid = true;

        String field = null;

        while(invalid){
            field = scanner.nextLine();

            if(required && field.isEmpty()){
                System.out.println("This field is required so please provide a value!");
            } else {
                invalid = false;
            }
        }

        return field;
    }

    public Integer scanNumberField(){
        return scanNumberField(true);
    }

    public Integer scanNumberField(boolean required){
        boolean invalid = true;

        Integer number = null;

        while(invalid){
            try{
                String numberString = scanner.nextLine();
                if(required && numberString.isEmpty()){
                    System.out.println("This field is required so please provide a value!");
                } else {
                    if(!numberString.isEmpty())
                        number = Integer.parseInt(numberString);

                    invalid = false;
                }
            } catch (Exception e){
                System.out.println("This field is of type number so please provide a valid value!");
            }
        }
        return number;
    }

    public LocalDate scanDateField(){
        return scanDateField(true);
    }

    public LocalDate scanDateField(boolean required){
        boolean invalid = true;

        LocalDate date = null;

        while(invalid){
            try{
                String dateString = scanner.nextLine();
                if(required && dateString.isEmpty()){
                    System.out.println("This field is required so please provide a value!");
                } else {
                    if(!dateString.isEmpty()){
                        String[] parsedDateString = dateString.split("-");
                        date = LocalDate.of(
                                Integer.parseInt(parsedDateString[2]),
                                Integer.parseInt(parsedDateString[1]),
                                Integer.parseInt(parsedDateString[0])
                        );
                    }

                    invalid = false;
                }
            } catch (Exception e){
                System.out.println("This field is of type date so please provide a valid value!");
            }
        }
        return date;
    }

    public void close(){
        this.scanner.close();
    }

    public Integer scanRestrictedFieldNumber(List<Integer> restrictedValues){
        Integer number;

        boolean invalid = true;

        do {
            number = this.scanNumberField();

            if(restrictedValues.contains(number)){
                invalid = false;
            } else {
                System.out.println("Please provide one of the listed options!");
            }
        } while(invalid);

        return number;
    }


    }
