package efs.task.syntax;


import java.util.Scanner;
import java.util.random.RandomGenerator;

public class GuessNumberGame {

    private final Integer M;
    private final int L;
    private int numberOfTries = 1;
    private final int numberToGuess;


    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) throws IllegalArgumentException {

        try {
            this.M = Integer.parseInt(argument);
        } catch(NumberFormatException ex) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new IllegalArgumentException();
        }

        if(M > 400 || M < 1) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            System.out.println("Jest spoza zakresu <1," + UsefulConstants.MAX_UPPER_BOUND + ">");
            throw new IllegalArgumentException();
        }

        L = (int)(Math.floor(Math.log(M) / Math.log(2)) + 1);

        numberToGuess = RandomGenerator.getDefault().nextInt(M) + 1;
    }

    public void play() {

        int yourAnswer;

        System.out.println("Zagrajmy. Zgadnij liczbę z zakresu <1," + M + ">");

        while(numberOfTries <= L) {

            Scanner scanner = new Scanner(System.in);

            printStateBar(numberOfTries, L);

            System.out.println(UsefulConstants.GIVE_ME + " liczbę:");

            try {
                yourAnswer = scanner.nextInt();
            } catch (Exception e) {
                System.out.println(UsefulConstants.NOT_A_NUMBER);
                numberOfTries++;
                continue;
            }

            if(yourAnswer < numberToGuess) {
                System.out.println(UsefulConstants.TO_LESS);
            }
            else if(yourAnswer > numberToGuess) {
                System.out.println(UsefulConstants.TO_MUCH);
            }
            else {
                System.out.println(UsefulConstants.YES);
                System.out.println(UsefulConstants.CONGRATULATIONS + " " + numberOfTries + " - tyle prob zajelo Ci odgadniecie liczby " + numberToGuess);
                break;
            }
            numberOfTries++;
            scanner.close();
        }
        if(numberOfTries - 1 == L) {
            System.out.println(UsefulConstants.UNFORTUNATELY + ", wyczerpales limit prob (" + L + ")" + " do ogadniecia liczby " + numberToGuess);
        }
    }
    private void printStateBar(int numberOfTries, int limit) {
        System.out.print("Twoje proby: [");
        for(int i = 0; i < numberOfTries; i++) {
            System.out.print("*");
        }
        for(int i = 0; i < limit - numberOfTries; i++) {
            System.out.print(".");
        }
        System.out.println("] ");
    }
}
