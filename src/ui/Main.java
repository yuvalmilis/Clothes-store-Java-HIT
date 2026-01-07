package ui;

import com.google.gson.reflect.TypeToken;
import repository.Paths;
import domain.users.*;
import domain.customers.*;
import repository.Repository;
import service.EmployeeService;
import service.CustomerService;

public class Main {

    public static void main(String[] args) {
        System.out.println("===== תחילת בדיקת מערכת ניהול רשת חנויות =====");

        // אתחול שירותים (Services)
        EmployeeService employeeService = new EmployeeService(new Repository<>(Paths.EMPLOYEES_FILE_PATH, new TypeToken<>() {}), "Employees");
        CustomerService customerService = new CustomerService(new Repository<>(Paths.CUSTOMERS_FILE_PATH, new TypeToken<>() {}), "Customers");

        // --- בדיקת עובדים ---
        System.out.println("\n--- בדיקת עובדים ---");

        // הוספת עובדים חדשים
        employeeService.addEntity(new Cashier("E001", "סניף חולון", "יוסי כהן", "050-1111111"));
        employeeService.addEntity(new Seller("E002", "BR1", "דני לוי", "050-2222222", "מחלקת בגדי גברים"));

        // בדיקת מניעת כפילות ID לעובד
        System.out.println("[בדיקת כפילות]: מנסה להוסיף עובד עם ID קיים (E001)...");
        employeeService.addEntity(new Cashier("E001", "BR1", "יוסי כהן ", "0501234567"));

        // הדפסת רשימת עובדים
        System.out.println("\nרשימת עובדים נוכחית:");
        employeeService.getAllEntities().forEach(System.out::println);

        // --- בדיקת לקוחות ---
        System.out.println("\n--- בדיקת לקוחות ---");

        // הוספת לקוחות מסוגים שונים
        customerService.addEntity(new NewCustomer("C100", "BR1", "ישראל ישראלי", "054-0000000", 0.0)); // 0% הנחה
        customerService.addEntity(new VIPCustomer("C200", "BR1", "שרה המלכה", "052-5555555", 0.2));   // 20% הנחה

        // בדיקת מניעת כפילות ID ללקוח
        System.out.println("[בדיקת כפילות]: מנסה להוסיף לקוח עם ID קיים (C100)...");
        customerService.addEntity(new ReturningCustomer("C100", "BR1", "לקוח מתחזה", "111", 0.1));

        // הצגת לקוחות ובדיקת לוגיקת הנחה
        System.out.println("\nרשימת לקוחות ובדיקת הנחות (על מוצר שעולה 200 ש\"ח):");
        for (Customer c : customerService.getAllEntities()) {
            double originalPrice = 200.0;
            double discountedPrice = c.applyDiscount(originalPrice);
            System.out.println(c + " -> מחיר אחרי הנחה: " + discountedPrice);
        }

        // --- בדיקת מחיקה ---
        System.out.println("\n--- בדיקת מחיקה ---");

        // מחיקת עובד
        System.out.println("מוחק עובד E002...");
        employeeService.removeEntity("E002", "BR1");

        // מחיקת לקוח
        System.out.println("מוחק לקוח C100...");
        customerService.removeEntity("C100", "BR1");

        // --- הצגת תמונת מצב סופית ---
        System.out.println("\n--- תמונת מצב סופית במערכת ---");

        System.out.println("עובדים שנותרו:");
        employeeService.getAllEntities().forEach(System.out::println);

        System.out.println("\nלקוחות שנותרו:");
        customerService.getAllEntities().forEach(System.out::println);

        System.out.println("\n===== הבדיקה הסתיימה בהצלחה =====");
    }
}