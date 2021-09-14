package kz.Prudnikov.BanksApp;

import java.util.HashMap;
import java.util.Scanner;

public class MainMenu {

    public boolean option = true, option2 = true, option3 = true, option4 = true, option5 = true, option6 = true, option7 = true;
    private int answer;
    private String error, moneyCheck;

    public String nameForRewrite;
    public int password, depositInAccount, newPassword, zeroMoney = 0;

    HashMap<String, Integer> login = (new DataBase()).getLoginP();
    HashMap<String, Integer> money = (new DataBase()).getMoneyP();


    public void ans(){
        InputErrorCheck a = new InputErrorCheck();
        answer = a.getAnswer();
    }

    public MainMenu(){

        System.out.println("Welcome to BankApp!\n");
        while (option){
            System.out.println("1.Add new customer\n" + "2.Check the balance" );
            ans();
            if(answer > 2 || answer < 1) {System.out.println("Incorrect input, try again");  ans(); }

            switch (answer) {
                case (1) -> AddNew();
                case (2) -> CheckBalance();
            }
        }
    }



    public void AddNew(){
        while (option5){
            System.out.println("Choose the Login");
            Scanner scan = new Scanner(System.in);
            String newName = scan.nextLine();

            while (option6){
                if( login.containsKey(newName) ) {
                    System.out.println("Login already exist, choose another one");                     ////////////// newPassword, newName, zeroMoney
                    newName = scan.nextLine();
                } else {
                    while (option7) {
                        System.out.println("Choose the 5-digit password");
                        ans();
                        if (String.valueOf(Math.abs(answer)).length() != 5) {
                            System.out.println("Wrong password, please choose another one");
                        } else {
                            newPassword = answer;
                            System.out.println("Repeat your password");
                            ans();
                            if (newPassword == answer) {
                                option7 = false; option6 = false; option5 = false; option = false;
                                new AddNewCustomer(newPassword, newName, zeroMoney);
                            }
                        }
                    }
                }
            }
        }

    }

    public void CheckBalance(){
        while (option2) {
            System.out.println("Enter the Login: ");
            Scanner scan = new Scanner(System.in);
            String name = scan.nextLine();
            nameForRewrite = name;

            while (option3){
                if(login.containsKey(name)){
                    depositInAccount = money.get(name);
                    System.out.println("Enter the password: ");
                    if(scan.hasNextInt()) {                                                                         //////// password, nameForRewrite, depositInAccount
                        password = scan.nextInt();
                        int check = login.get(name);
                        if(login.containsValue(password) && check == password ){
                            Balance();
                        } else System.out.println("Incorrect password, try again" );
                    }  else System.out.println("Sasay2");
                } else { System.out.println("Incorrect Login, try again"); }
                error = scan.nextLine();
            }
        }
    }


    public void Balance() {
        answer = 10;
            while (option4) {
                switch (answer) {
                    case (10) -> {
                        System.out.println("Your balance is: " + depositInAccount );
                        System.out.println("1. Deposit the money\n" + "2. Exit");
                        ans();
                    }
                    case (1) -> {
                        System.out.println("How much you want to add? ");
                        ans();
                        if (answer > 0) {
                            depositInAccount = depositInAccount + answer;
                            answer = 10;
                        }
                    }
                    case (2) -> {
                        Repository repository = new Repository("root", "Mysql4512345123");
                        repository.add(password, nameForRewrite, depositInAccount);
                        option = false; option2 = false; option3 = false; option4 = false;
                    }
                }
            }
            option4 = true;
    }
}
