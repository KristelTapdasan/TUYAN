package eventbookingtapdasan;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EVENTBOOKINGTAPDASAN {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);    
        boolean exit = true;
        
        do {
            System.out.println("==================================");
            System.out.println("     [WELCOME TO EVENT BOOKING]     ");
            System.out.println("==================================");
            System.out.println("");
            System.out.println("[1. CUSTOMER]");
            System.out.println("[2. EVENT]");
            System.out.println("[3. BOOKING]");
            System.out.println("[4. EXIT]");
            System.out.println("[5. VIEW REPORTS AND RECEIPT]");
            System.out.println("");
            
            System.out.print("Enter Action: ");
            System.out.println("");
            
            try {
                int act = sc.nextInt();
                
                switch (act) {
                    case 1:
                        Customer cs = new Customer();
                        cs.cTransaction();
                        break;
                    case 2:
                        Event ev = new Event();
                        ev.eTransaction();
                        break;
                    case 3:
                        Booking bk = new Booking();
                        bk.bTransaction();
                        break;
                    case 4:
                        System.out.print("Are you sure? (yes/no): ");
                        String resp = sc.next();
                        if (resp.equalsIgnoreCase("yes")) {
                            exit = false;
                        }
                        break;
                    case 5:
                        ViewReports vr = new ViewReports();
                        vr.reportMenu();
                        break;
    
                        
                    default:
                        System.out.println("Invalid action! Please choose a number between 1 and 4.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a valid number.");
                sc.next(); 
            }
            
        } while (exit);
        
        System.out.println("Thank you, See you soon!!");
        sc.close();
    }
}
