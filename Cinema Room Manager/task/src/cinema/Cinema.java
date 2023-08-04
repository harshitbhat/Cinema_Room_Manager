package cinema;

import java.util.Scanner;

public class Cinema {
    public static void printSeats(char [][] seats, int rows, int columns) {
        System.out.println("Cinema:");
        for (int r = 0; r <= rows; r++) {
            for (int c = 0; c <= columns; c++) {
                if(r == 0 && c == 0) {
                    System.out.print("  ");
                } else if (r == 0) {
                    System.out.print(c + " ");
                } else if (c == 0) {
                    System.out.print(r + " ");
                } else {
                    System.out.print(seats[r - 1][c - 1] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void initializeSeats(char[][] seats, int rows, int cols) {
        for (int r = 0; r < rows; r++) {
            for(int c = 0; c < cols; c++) {
                seats[r][c] = 'S';
            }
        }
    }

    public static void bookTicket(char[][] seats, int rows, int columns, Scanner sc) {

        int r, c;

        boolean validInput = true;

        do {
            System.out.println("\nEnter a row number:");
            r = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            c = sc.nextInt();

            if(r >= 1 && r <= rows && c >= 1 && c <= columns) {
                if (seats[r - 1][c - 1] == 'B') {
                    System.out.println("That ticket has already been purchased!");
                    validInput = false;
                } else {
                    seats[r - 1][c - 1] = 'B';
                    int cost = getSeatCost(rows, columns, r);
                    System.out.printf("Ticket price: $%d\n", cost);
                    validInput = true;
                }
            } else {
                System.out.println("Wrong input!");
                validInput = false;
            }
        } while (validInput != true);
    }

    public static int getTotalCost(int rows, int cols) {

        if (rows * cols < 60) {
            return rows * cols * 10;
        } else {
            if (rows % 2 == 0) {
                return rows * cols * 9;
            } else {
                return (rows / 2 * cols * 10) + ((rows / 2 + 1) * cols * 8);
            }
        }
    }

    public static int getSeatCost(int rows, int cols, int r) {
        if (rows * cols <= 60) {
            return 10;
        } else {
            if (r <= rows / 2) {
                return 10;
            } else {
                return 8;
            }
        }
    }

    public static void showStatistics(char[][] seats, int rows, int cols) {
        int count = 0;
        int currentIncome = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (seats[i][j] == 'B') {
                    count++;
                    currentIncome += getSeatCost(rows, cols, i + 1);
                }
            }
        }

        double occupancyPercentage = count / (rows * cols * 1.0);

        System.out.printf("Number of purchased tickets: %d\n", count);
        System.out.printf("Percentage: %.2f%c\n", occupancyPercentage * 100, '%');
        System.out.printf("Current income: $%d\n", currentIncome);
        System.out.printf("Total income: $%d\n", getTotalCost(rows, cols));

    }

    public static void showChoices() {
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        final int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        final int cols = scanner.nextInt();

        char[][] seats = new char[rows][cols];
        initializeSeats(seats, rows, cols);

        int input;

        do {
            showChoices();
            input = scanner.nextInt();

            switch (input) {
                case 0:
                    break;
                case 1:
                    printSeats(seats, rows, cols);
                    break;
                case 2:
                    bookTicket(seats, rows, cols, scanner);
                    break;
                case 3:
                    showStatistics(seats, rows, cols);
                    break;
                default:
                    System.out.println("Wrong Input!");
                    break;
            }

        } while (input != 0);

    }
}