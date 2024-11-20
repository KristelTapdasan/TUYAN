package eventbookingtapdasan;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Booking {

    public void bTransaction() {
        Scanner sc = new Scanner(System.in);
        String response;
        do {
    try {
        System.out.println("          --- [BOOKING PANEL] ---          ");
        System.out.println("");
        System.out.println("[1. ADD BOOKING]");
        System.out.println("[2. VIEW BOOKING]");
        System.out.println("[3. UPDATE BOOKING]");
        System.out.println("[4. DELETE BOOKING]");
        System.out.println("[5. EXIT BOOKING]");
        System.out.println("");

        System.out.print("Enter Action: ");
        int action = sc.nextInt();
        Booking bk = new Booking();

        switch (action) {
            case 1:
                bk.addBooking();
                bk.viewBooking();
                break;
            case 2:
                bk.viewBooking();
                break;
            case 3:
                bk.viewBooking();
                bk.updateBooking(); 
                bk.viewBooking();
                break;
            case 4:                
                bk.viewBooking();
                bk.deleteBooking();
                bk.viewBooking();
                break;
            case 5:
                System.out.println("Exiting Booking...");
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
    
    private void addBooking() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        Customer cs = new Customer();
        cs.viewCustomers();

        int cid;
        while (true) {
            System.out.print("Enter the ID to update: ");
            while (!sc.hasNextInt()) {
                System.out.print("Invalid ID! Please enter a valid Customer ID: ");
                sc.next();
            }
            cid = sc.nextInt();
            if (conf.getSingleValue("SELECT c_id FROM tbl_customers WHERE c_id = ?", cid) != 0) {
                break;
            }
            System.out.println("Selected ID doesn't exist! Try again.");
        }

        Event ev = new Event();
        ev.viewEvents();

        int eid;
        while (true) {
            System.out.print("Enter the ID to update: ");
            while (!sc.hasNextInt()) {
                System.out.print("Invalid ID! Please enter a valid Event ID: ");
                sc.next();
            }
            eid = sc.nextInt();
            if (conf.getSingleValue("SELECT e_id FROM tbl_event WHERE e_id = ?", eid) != 0) {
                break;
            }
            System.out.println("Selected ID doesn't exist! Try again.");
        
        }

        sc.nextLine();
        System.out.print("Booking Event Date: ");
        String date = sc.nextLine();

        
        String eventStatusQuery = "SELECT e_status FROM tbl_event WHERE e_id = ?";
        String eventStatus = conf.getStringValue(eventStatusQuery, eid);

        
        String status = eventStatus.equalsIgnoreCase("Done") ? "Done" : "Pending";

        String qry = "INSERT INTO tbl_booking (c_id, e_id, b_date, b_status) VALUES (?, ?, ?, ?)";
        conf.addRecord(qry, cid, eid, date, status);
    }

    public void viewBooking() {
        String qry = "SELECT b_id, c_fname, e_name, e_date, e_venue, e_price, b_date, b_status FROM tbl_booking "
                + "LEFT JOIN tbl_customers ON tbl_customers.c_id = tbl_booking.c_id "
                + "LEFT JOIN tbl_event ON tbl_event.e_id = tbl_booking.e_id";

        String[] headers = {"B_ID", "Customer", "Event Name", "Event Date", "Venue", "Price", "Booking Date", "Booking Status"};
        String[] columns = {"b_id", "c_fname", "e_name", "e_date", "e_venue", "e_price", "b_date", "b_status"};
        config conf = new config();
        conf.viewRecords(qry, headers, columns);
    }
    private void updateBooking() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
         int id;
        while (true) {
            System.out.print("Enter the ID to update: ");
            while (!sc.hasNextInt()) {
                System.out.print("Invalid ID! Please enter a valid Booking ID: ");
                sc.next();
            }
            id = sc.nextInt();
            if (conf.getSingleValue("SELECT b_id FROM tbl_booking WHERE b_id = ?", id) != 0) {
                break;
            }
            System.out.println("Selected ID doesn't exist! Try again.");
        }

        System.out.println("Enter New Booking Date: ");
        String date = sc.nextLine();

        String qry = "UPDATE tbl_booking SET b_date = ? WHERE b_id = ?";
        conf.updateRecord(qry, date, id);
    }

    private void deleteBooking() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        int id;

        while (true) {
            System.out.print("Enter the ID to delete: ");
            while (!sc.hasNextInt()) {
            System.out.print("Invalid input! Please enter a valid Booking ID: ");
            sc.next();
        }
            try {
                id = sc.nextInt();
                if (conf.getSingleValue("SELECT b_id FROM tbl_booking WHERE b_id = ?", id) != 0) {
                    break; 
                }
                System.out.println("Selected ID doesn't exist! Try again.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid ID.");
                sc.nextLine(); 
            }
        }

        String qry = "DELETE FROM tbl_booking WHERE b_id = ?";
        conf.deleteRecord(qry, id);
    }
}

