package kz.Prudnikov.BanksApp;

import java.util.Scanner;

public class InputErrorCheck {

    boolean option = true;
    public int answer;

    Scanner scanner = new Scanner(System.in);

    public InputErrorCheck(){

        while (option){
            if(scanner.hasNextInt()){
                answer = Integer.parseInt(scanner.nextLine());
                break;
            } else { System.out.println("Incorrect input"); scanner.nextLine(); }
        }
    }

    public int getAnswer() {
        return answer;
    }
}
