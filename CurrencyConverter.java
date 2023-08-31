
import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Currency Converter!");

        // You can implement logic here to fetch real-time exchange rates

        System.out.print("Enter amount in source currency: ");
        double amount = scanner.nextDouble();

        System.out.print("Enter source currency (e.g., USD): ");
        String sourceCurrency = scanner.next().toUpperCase();

        System.out.print("Enter target currency (e.g., INR): ");
        String targetCurrency = scanner.next().toUpperCase();

        // Perform currency conversion (implement your logic here)
        double convertedAmount = convertCurrency(amount, sourceCurrency, targetCurrency);

        System.out.println("Converted amount: " + convertedAmount + " " + targetCurrency);

        scanner.close();
    }

    // Dummy method for currency conversion
    private static double convertCurrency(double amount, String sourceCurrency, String targetCurrency) {
        // Implement your currency conversion logic here
        // You might fetch real-time exchange rates and perform the calculation
        // For this example, let's assume a simple conversion
        double conversionRate = 74.5; // 1 USD to INR
        if (sourceCurrency.equals("USD") && targetCurrency.equals("INR")) {
            return amount * conversionRate;
        } else if (sourceCurrency.equals("INR") && targetCurrency.equals("USD")) {
            return amount / conversionRate;
        } else {
            System.out.println("Unsupported currency conversion.");
            return 0.0;
        }
    }
}
