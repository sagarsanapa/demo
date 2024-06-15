import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> grades = new ArrayList<>();
        boolean continueInput = true;

        System.out.println("Enter student grades. Type 'done' when finished:");

        while (continueInput) {
            String input = scanner.next();
            if (input.equalsIgnoreCase("done")) {
                continueInput = false;
            } else {
                try {
                    int grade = Integer.parseInt(input);
                    grades.add(grade);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid grade or 'done' to finish.");
                }
            }
        }

        if (grades.isEmpty()) {
            System.out.println("No grades entered.");
        } else {
            double average = calculateAverage(grades);
            int highest = findHighest(grades);
            int lowest = findLowest(grades);

            System.out.println("Grades entered: " + grades);
            System.out.println("Average grade: " + average);
            System.out.println("Highest grade: " + highest);
            System.out.println("Lowest grade: " + lowest);
        }

        scanner.close();
    }

    private static double calculateAverage(ArrayList<Integer> grades) {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }

    private static int findHighest(ArrayList<Integer> grades) {
        int highest = grades.get(0);
        for (int grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }
        return highest;
    }

    private static int findLowest(ArrayList<Integer> grades) {
        int lowest = grades.get(0);
        for (int grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }
        return lowest;
    }
}





------------------------------------------------------------------------------------------------------








import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StockTradingPlatform {
    private static Map<String, Double> marketData = new HashMap<>();
    private static Map<String, Integer> portfolio = new HashMap<>();
    private static double cashBalance = 10000.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        initializeMarketData();
        boolean continueTrading = true;

        while (continueTrading) {
            System.out.println("Welcome to the Stock Trading Platform!");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stocks");
            System.out.println("3. Sell Stocks");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewMarketData();
                    break;
                case 2:
                    buyStocks(scanner);
                    break;
                case 3:
                    sellStocks(scanner);
                    break;
                case 4:
                    viewPortfolio();
                    break;
                case 5:
                    continueTrading = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void initializeMarketData() {
        marketData.put("AAPL", 150.00);
        marketData.put("GOOGL", 2800.00);
        marketData.put("AMZN", 3500.00);
        marketData.put("MSFT", 300.00);
        marketData.put("TSLA", 700.00);
    }

    private static void viewMarketData() {
        System.out.println("Market Data:");
        for (Map.Entry<String, Double> entry : marketData.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }
    }

    private static void buyStocks(Scanner scanner) {
        System.out.print("Enter stock symbol: ");
        String symbol = scanner.next().toUpperCase();
        if (marketData.containsKey(symbol)) {
            System.out.print("Enter quantity to buy: ");
            int quantity = scanner.nextInt();
            double cost = marketData.get(symbol) * quantity;
            if (cost <= cashBalance) {
                cashBalance -= cost;
                portfolio.put(symbol, portfolio.getOrDefault(symbol, 0) + quantity);
                System.out.println("Bought " + quantity + " shares of " + symbol);
                System.out.println("Remaining cash balance: $" + cashBalance);
            } else {
                System.out.println("Insufficient funds to buy " + quantity + " shares of " + symbol);
            }
        } else {
            System.out.println("Invalid stock symbol.");
        }
    }

    private static void sellStocks(Scanner scanner) {
        System.out.print("Enter stock symbol: ");
        String symbol = scanner.next().toUpperCase();
        if (portfolio.containsKey(symbol)) {
            System.out.print("Enter quantity to sell: ");
            int quantity = scanner.nextInt();
            int currentQuantity = portfolio.get(symbol);
            if (quantity <= currentQuantity) {
                double proceeds = marketData.get(symbol) * quantity;
                cashBalance += proceeds;
                if (quantity == currentQuantity) {
                    portfolio.remove(symbol);
                } else {
                    portfolio.put(symbol, currentQuantity - quantity);
                }
                System.out.println("Sold " + quantity + " shares of " + symbol);
                System.out.println("New cash balance: $" + cashBalance);
            } else {
                System.out.println("You do not own enough shares of " + symbol);
            }
        } else {
            System.out.println("You do not own any shares of " + symbol);
        }
    }

    private static void viewPortfolio() {
        System.out.println("Portfolio:");
        for (Map.Entry<String, Integer> entry : portfolio.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " shares");
        }
        System.out.println("Cash balance: $" + cashBalance);
    }
}










------------------------------------------------------------------------------------------------------













import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Room {
    String category;
    int roomNumber;
    double pricePerNight;
    boolean isAvailable;

    public Room(String category, int roomNumber, double pricePerNight) {
        this.category = category;
        this.roomNumber = roomNumber;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "Room{" +
                "category='" + category + '\'' +
                ", roomNumber=" + roomNumber +
                ", pricePerNight=" + pricePerNight +
                ", isAvailable=" + isAvailable +
                '}';
    }
}

class Reservation {
    String customerName;
    Room room;
    int nights;
    double totalCost;

    public Reservation(String customerName, Room room, int nights) {
        this.customerName = customerName;
        this.room = room;
        this.nights = nights;
        this.totalCost = room.pricePerNight * nights;
        room.isAvailable = false;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customerName='" + customerName + '\'' +
                ", room=" + room +
                ", nights=" + nights +
                ", totalCost=" + totalCost +
                '}';
    }
}

public class HotelReservationSystem {
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeRooms();
        boolean continueSystem = true;

        while (continueSystem) {
            System.out.println("Welcome to the Hotel Reservation System!");
            System.out.println("1. Search for Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    searchAvailableRooms();
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    viewBookingDetails();
                    break;
                case 4:
                    continueSystem = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void initializeRooms() {
        rooms.add(new Room("Single", 101, 100.0));
        rooms.add(new Room("Double", 102, 150.0));
        rooms.add(new Room("Suite", 103, 250.0));
        rooms.add(new Room("Single", 104, 100.0));
        rooms.add(new Room("Double", 105, 150.0));
    }

    private static void searchAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println(room);
            }
        }
    }

    private static void makeReservation(Scanner scanner) {
        System.out.print("Enter your name: ");
        String customerName = scanner.next();
        System.out.print("Enter room number to book: ");
        int roomNumber = scanner.nextInt();
        System.out.print("Enter number of nights: ");
        int nights = scanner.nextInt();

        Room roomToBook = null;
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && room.isAvailable) {
                roomToBook = room;
                break;
            }
        }

        if (roomToBook != null) {
            Reservation reservation = new Reservation(customerName, roomToBook, nights);
            reservations.add(reservation);
            System.out.println("Reservation successful!");
            System.out.println(reservation);
        } else {
            System.out.println("Room not available or invalid room number.");
        }
    }

    private static void viewBookingDetails() {
        System.out.println("Booking Details:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }
}
