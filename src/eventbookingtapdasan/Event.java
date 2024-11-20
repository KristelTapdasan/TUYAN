package eventbookingtapdasan;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Event {
    
    public void eTransaction() {
        Scanner sc = new Scanner(System.in);
        String response;
        do {
            try {
                System.out.println("          --- [EVENT PANEL] ---          ");
                System.out.println("");
                System.out.println("[1. ADD EVENT]");
                System.out.println("[2. VIEW EVENT]");
                System.out.println("[3. UPDATE EVENT]");
                System.out.println("[4. DELETE EVENT]");
                System.out.println("[5. EXIT EVENT]");
                System.out.println("");
                
                System.out.print("Enter Action: ");
                int action = sc.nextInt();
                Event ev = new Event();

                switch (action) {
                    case 1:
                        ev.addEvents();
                        break;
                    case 2:
                        ev.viewEvents();
                        break;
                    case 3:
                        ev.viewEvents();
                        ev.updateEvents();
                        ev.viewEvents();
                        break;
                    case 4:
                        ev.viewEvents();
                        ev.deleteEvents();
                        ev.viewEvents();
                        break;
                    case 5:
                        System.out.println("Exiting Event...");
                        return;
                    default:
                        System.out.println("Invalid action! Please choose a number between 1 and 5.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a valid number.");
                sc.next();
            }

            System.out.print("Do you want to continue? (yes/no): ");
            response = sc.next();
        } while (response.equalsIgnoreCase("yes"));

        System.out.println("Thank You, See you soonest!");
    }

    public void addEvents() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("Event Name: ");
        String ename = sc.nextLine();
        System.out.print("Date: ");
        String date = sc.nextLine();
        System.out.print("Venue: ");
        String ven = sc.nextLine();
        System.out.print("Price: ");
        double price = sc.nextDouble();
        sc.nextLine(); 
        System.out.print("Status (Done/Not Done): ");
        String status = sc.nextLine();
        
        String sql = "INSERT INTO tbl_event (e_name, e_date, e_venue, e_price, e_status) VALUES (?, ?, ?, ?, ?)";
        conf.addRecord(sql, ename, date, ven, price, status);
    }

    public void viewEvents() {
        config conf = new config();
        String query = "SELECT * FROM tbl_event";
        String[] headers = {"Event_ID", "EventName", "Date", "Venue", "Price", "Status"};
        String[] columns = {"e_id", "e_name", "e_date", "e_venue", "e_price", "e_status"};

        conf.viewRecords(query, headers, columns);
    }

    private void updateEvents() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        int id;
        while (true) {
            System.out.print("Enter the ID to update: ");
            while (!sc.hasNextInt()) {
                System.out.print("Invalid ID! Please enter a valid Event ID: ");
                sc.next();
            }
            id = sc.nextInt();
            if (conf.getSingleValue("SELECT e_id FROM tbl_event WHERE e_id = ?", id) != 0) {
                break;
            }
            System.out.println("Selected ID doesn't exist! Try again.");
        }

        sc.nextLine(); 
        System.out.print("New Event Name: ");
        String nename = sc.nextLine();
        System.out.print("New Date: ");
        String ndate = sc.nextLine();
        System.out.print("New Venue: ");
        String nven = sc.nextLine();
        System.out.print("New Price: ");
        double nprice = sc.nextDouble();
        sc.nextLine(); 
        System.out.print("New Status (Done/Not Done): ");
        String nstatus = sc.nextLine();

        String qry = "UPDATE tbl_event SET e_name = ?, e_date = ?, e_venue = ?, e_price = ?, e_status = ? WHERE e_id = ?";
        conf.updateRecord(qry, nename, ndate, nven, nprice, nstatus, id);
    }

    private void deleteEvents() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        int id;
        while (true) {
            System.out.print("Enter the ID to delete: ");
            while (!sc.hasNextInt()) {
                System.out.print("Invalid ID! Please enter a valid Event ID: ");
                sc.next();
            }
            id = sc.nextInt();
            if (conf.getSingleValue("SELECT e_id FROM tbl_event WHERE e_id = ?", id) != 0) {
                break;
            }
            System.out.println("Selected ID doesn't exist! Try again.");
        }

        String qry = "DELETE FROM tbl_event WHERE e_id = ?";
        conf.deleteRecord(qry, id);
    }
}
