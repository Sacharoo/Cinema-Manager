package cinema;

import java.util.Scanner;
public class Cinema {
    public static Scanner scanner = new Scanner(System.in);
    public static int totalRows ;
    public static int totalSeats ;
    public static int rowNumber ;
    public static int seatNumber ;
    public static int ticketPrice ;
    public static char[][] cinema;
    public static int totalProfits ;
    public static int currentProfits ;
    public static  int ticketCount ;
    public static float percentage ;

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        totalRows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        totalSeats = scanner.nextInt();

        cinema = new char[totalRows][totalSeats];
        //populating the cinema array
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalSeats; j++) {
                cinema[i][j] = 'S';
            }
        }
        System.out.println();

        int total = totalRows * totalSeats;
        //Calculating ticket Prices
        if (total <= 60) {
            totalProfits = total * 10;
        }
        else {
            int expTotalRows = totalRows / 2;
            totalProfits = expTotalRows * totalSeats * 10 + Math.abs(totalRows - expTotalRows) * totalSeats * 8;
        }
        menu();
    }
    public static void menu(){
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        int option = scanner.nextInt();

        switch (option) {
            case 1 :
                printSeats();
            case 2 :
                buyTicket();
            case 3 :
                statistics(ticketCount,percentage,currentProfits,totalProfits);
            default:
                return;
        }
    }

    public static void printSeats() {
        // Print the First line
        System.out.println("Cinema:");
        // Print the Gap Before 1,2,3...
        System.out.print("  ");

        // the horizontal number line
        int[] num1 = new int[totalSeats];

        for (int i = 0; i < num1.length; i++) {
            num1[i] = i + 1;
            System.out.print(num1[i] + " " );
        }
        System.out.println();

        // the vertical number line
        int[] num2 = new int[totalRows];

        //Printing the S's
        for (int i = 0; i < totalRows; i++) {
            num2[i] = i + 1;
            System.out.print((i + 1) + " ");
            for (int j = 0; j < totalSeats; j++) {

                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        menu();
    }

    public static void buyTicket() {
        System.out.println("Enter a row number:");
        rowNumber = scanner.nextInt();

        System.out.println("Enter a seat number in that row:");
        seatNumber = scanner.nextInt();
        int total = totalRows * totalSeats;

        if (rowNumber <= 0 || rowNumber > totalRows || seatNumber <= 0 || seatNumber > totalSeats){
            System.out.println("Wrong input!");
            buyTicket();

        }
        else if(cinema[rowNumber - 1][seatNumber - 1] =='B') {
            System.out.println("That ticket has already been purchased!");
            buyTicket();

        }  else{

            System.out.println();
            if (total < 60) {
                ticketPrice = 10;
            } else {
                if (rowNumber > totalRows / 2) {
                    ticketPrice = 8;
                } else {
                    ticketPrice = 10;
                }
            }

            cinema[rowNumber - 1][seatNumber - 1] = 'B';
        }
        System.out.printf("Ticket price :$%d%n",ticketPrice);
        //Calculating Current Profits
        currentProfits = ticketPrice + currentProfits;


        float totalFloat = total;
        ticketCount = ticketCount + 1;
        percentage = (ticketCount / totalFloat)  ;

        printSeats();
        statistics(ticketCount,percentage,currentProfits,totalProfits);
}
    public static void statistics(int ticketCount, float percentage, int currentProfits, int totalProfits) {
        System.out.printf("Number of purchased tickets: %d%n", ticketCount);
        System.out.printf("Percentage: %.2f%% %n",100 * percentage);
        System.out.printf("Current Income: $%d %n",currentProfits);
        System.out.printf("Total Income: $%d %n ",totalProfits);
        System.out.println();
        menu();

    }
}
