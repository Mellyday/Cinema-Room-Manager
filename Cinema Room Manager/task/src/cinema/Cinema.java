package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);
        int FULL_PRICE = 10, BACKSEAT_PRICE = 8, seatRequestedPrice;

        System.out.println("Enter the number of rows:");
        int row = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = Integer.parseInt(scanner.nextLine());
        int totalSeats = row * seatsPerRow;

        char[][] seatsDb = new char[row + 1][seatsPerRow + 1];
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

        System.out.println("Cinema:");
        for (int i = 0; i < seatsDb.length; i++) {
            for (int j = 0; j < seatsDb[i].length; j++) {
                System.out.print(seatsDb[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Enter a row number:");
        int rowRequested = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter a seat number in that row:");
        int seatRequested = Integer.parseInt(scanner.nextLine());
        
        seatsDb[rowRequested][seatRequested] = 'B';

        int frontRow, backRow;
        if (totalSeats <= 60) {
            frontRow = row;
            backRow = 0;
            seatRequestedPrice = FULL_PRICE;
        } else {
            frontRow = row / 2;
            backRow = row - frontRow;
            seatRequestedPrice = rowRequested <= frontRow ? FULL_PRICE : BACKSEAT_PRICE;
        }

        System.out.println("Ticket price: $" + seatRequestedPrice);

        System.out.println("Cinema:");
        for (int i = 0; i < seatsDb.length; i++) {
            for (int j = 0; j < seatsDb[i].length; j++) {
                System.out.print(seatsDb[i][j] + " ");
            }
            System.out.println();
        }
    }
}