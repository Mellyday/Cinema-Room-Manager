package cinema;

import java.util.Scanner;

public class Cinema {
    Scanner scanner;
    char[][] seatsDb;
    final int FULL_PRICE = 10, BACKSEAT_PRICE = 8, row, seatsPerRow, totalSeats;
    int purchasedTickets, currentIncome, fullSeatIncome;

    public Cinema(int row, int seatsPerRow) {
        this.scanner = new Scanner(System.in);
        this.row = row;
        this.seatsPerRow = seatsPerRow;
        this.totalSeats = this.row * this.seatsPerRow;

        this.purchasedTickets = 0;
        this.currentIncome = 0;
        this.fullSeatIncome = 0;

        if (totalSeats <= 60) {
            this.fullSeatIncome = FULL_PRICE * this.totalSeats;
        } else {
            this.fullSeatIncome = FULL_PRICE * (row / 2) * this.seatsPerRow +
            BACKSEAT_PRICE * (row - row / 2) * this.seatsPerRow;
        }

        this.seatsDb = new char[row + 1][seatsPerRow + 1];
        //name the row
        for (int i = 1; i < seatsDb.length; i++) {
            seatsDb[i][0] = Character.forDigit(i, 10);
        }
        //name the col
        for (int i = 1; i < seatsDb[0].length; i++) {
            seatsDb[0][i] = Character.forDigit(i, 10);
        }
        //fill the array with seats
        for (int i = 1; i < seatsDb.length; i++) {
            for (int j = 1; j < seatsDb[i].length; j++) {
                seatsDb[i][j] = 'S';
            }
        }
    }

    private int price(int rowRequested) {
        if (row * seatsPerRow <= 60) {
            return FULL_PRICE;
        } else {
            return rowRequested <= row / 2 ? FULL_PRICE : BACKSEAT_PRICE;
        }
    }

    public void print() {
        System.out.println("Cinema:");
        for (int i = 0; i < seatsDb.length; i++) {
            for (int j = 0; j < seatsDb[i].length; j++) {
                System.out.print(seatsDb[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printMenu() {
        System.out.println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");
    }

    public void printStatistics() {
        double percentage = (double) purchasedTickets / totalSeats * 100;
        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + fullSeatIncome);
    }

    public void bookSeat() {
        System.out.println("Enter a row number:");
        int row = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter a seat number in that row:");
        int seat = Integer.parseInt(scanner.nextLine());

        if (row > this.row || seat > this.seatsPerRow) {
            System.out.println("Wrong input!");
            bookSeat();
            return;
        }

        if (seatsDb[row][seat] == 'B') {
            System.out.println("That ticket has already been purchased!");
            bookSeat();
            return;
        }

        seatsDb[row][seat] = 'B';
        System.out.println("Ticket price: $" + price(row));
        this.purchasedTickets++;
        this.currentIncome += price(row);
    }

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int row = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = Integer.parseInt(scanner.nextLine());
        Cinema cinema = new Cinema(row, seatsPerRow);

        while (true) {
            cinema.printMenu();

            String action = scanner.nextLine();
            if ("0".equals(action)) {
                break;
            }

            if ("1".equals(action)) {
                cinema.print();
            }

            if ("2".equals(action)) {
                cinema.bookSeat();
            }

            if ("3".equals(action)) {
                cinema.printStatistics();
            }
        }
    }
}