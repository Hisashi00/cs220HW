import java.util.*;

class FoodEntry {
    String foodName;
    String timeEaten;
    int bloodSugarLevel;

    public FoodEntry(String foodName, String timeEaten, int bloodSugarLevel) {
        this.foodName = foodName;
        this.timeEaten = timeEaten;
        this.bloodSugarLevel = bloodSugarLevel;
    }

    @Override
    public String toString() {
        return "Time: " + timeEaten + ", Blood Sugar: " + bloodSugarLevel;
    }
    }

//Once you run this file, 
//the prompts of main method and other methods guide you through the app 
//to add/reference the food consunpution record.

public class BloodSugarTracker {
    
    static Map<String, List<FoodEntry>> foodLog = new HashMap<>();
    static Scanner scanner = new Scanner(System.in); 

    public static void main(String[] args) {
        System.out.println("Welcome to the Prediabetes Blood Sugar Tracker");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add new food entry");
            System.out.println("2. Look up food history");
            System.out.println("3. Exit");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addFoodEntry();   
                    break;
                case "2":
                    lookupFood();     
                    break;
                case "3":
                    System.out.println("Goodbye and stay healthy!");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    public static void addFoodEntry() {
        System.out.println("=== Add New Food Entry ===");
    
        System.out.print("Enter food name: ");
        String foodName = scanner.nextLine().trim().toLowerCase();
    
        System.out.print("Enter time eaten (e.g. 2025-03-27 08:30): ");
        String timeEaten = scanner.nextLine().trim();
    
        System.out.print("Enter blood sugar level after eating (e.g. 120): ");
        int bloodSugarLevel;
        try {
            bloodSugarLevel = Integer.parseInt(scanner.nextLine().trim());
            if (bloodSugarLevel > 300) {
                System.out.println("Invalid blood Sugar level");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Blood sugar level must be a number.");
            return;
        }
    
        FoodEntry entry = new FoodEntry(foodName, timeEaten, bloodSugarLevel);
        foodLog.putIfAbsent(foodName, new ArrayList<>());
        foodLog.get(foodName).add(entry);
    
        System.out.println("Entry added successfully!");
    }

    public static void lookupFood() {
        System.out.println("=== Look Up Food History ===");
        System.out.print("Enter food name to look up: ");
        String foodName = scanner.nextLine().trim().toLowerCase();
    
        List<FoodEntry> history = foodLog.get(foodName);
    
        if (history == null || history.isEmpty()) {
            System.out.println("No records found for \"" + foodName + "\".");
            return;
        }
    
        System.out.println("Past records for \"" + foodName + "\":");
        for (FoodEntry entry : history) {
            System.out.println(" - " + entry); 
        }
    }
    

}
