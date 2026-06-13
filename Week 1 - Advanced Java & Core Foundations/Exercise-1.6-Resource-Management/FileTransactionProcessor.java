import java.io.*;
import java.util.*;

public class FileTransactionProcessor {
    public static void main(String[] args) {
        System.out.println("=== Exercise 1.6: Resource Management ===\n");

        String inputFile = "transactions.csv";
        String errorFile = "failed_transactions.txt";

        int processed = 0;
        int failed = 0;
        List<String> errors = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                try {
                    processLine(line);
                    processed++;
                    System.out.println(" Processed line " + lineNumber);
                } catch (Exception e) {
                    failed++;
                    String error = String.format("Line %d: %s - %s", lineNumber, e.getMessage(), line);
                    errors.add(error);
                    System.out.println("✗ Failed line " + lineNumber + ": " + e.getMessage());
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: transactions.csv not found!");
            System.out.println("Please create the file with sample transaction data.");
            return;
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        if (!errors.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(errorFile))) {
                writer.write("=== Failed Transactions Report ===\n");
                writer.write("Generated: " + new Date() + "\n\n");
                for (String error : errors) {
                    writer.write(error);
                    writer.newLine();
                }
                System.out.println("\n Errors written to: " + errorFile);
            } catch (IOException e) {
                System.out.println("Warning: Could not write error file: " + e.getMessage());
            }
        }

        System.out.println("\n=== Summary Report ===");
        System.out.println("Processed: " + processed);
        System.out.println("Failed: " + failed);
        System.out.println("Total: " + (processed + failed));
    }

    private static void processLine(String line) throws Exception {
        String[] parts = line.split(",");

        if (parts.length < 5) {
            throw new Exception("Invalid format: expected 5 fields, got " + parts.length);
        }

        String transactionId = parts[0];
        String fromAccount = parts[1];
        String toAccount = parts[2];
        double amount;

        try {
            amount = Double.parseDouble(parts[3]);
        } catch (NumberFormatException e) {
            throw new Exception("Invalid amount: '" + parts[3] + "' is not a number");
        }

        if (amount <= 0) {
            throw new Exception("Amount must be positive: " + amount);
        }

        System.out.printf("  Transaction %s: $%.2f from %s to %s%n",
                transactionId, amount, fromAccount, toAccount);
    }
}
