package eventbookingtapdasan;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ViewReports {

    private Scanner sc = new Scanner(System.in);

    
    public void viewReceipt(int bookingId) {
        config conf = new config();
        String qry = "SELECT b_id, c_fname, e_name, e_date, e_venue, e_price, b_date, b_status " +
                     "FROM tbl_booking " +
                     "LEFT JOIN tbl_customers ON tbl_customers.c_id = tbl_booking.c_id " +
                     "LEFT JOIN tbl_event ON tbl_event.e_id = tbl_booking.e_id " +
                     "WHERE b_id = ?";

        String[] headers = {"Booking ID", "Customer Name", "Event Name", "Event Date", "Venue", "Price", "Booking Date", "Status"};
        String[] columns = {"b_id", "c_fname", "e_name", "e_date", "e_venue", "e_price", "b_date", "b_status"};

        System.out.println("==========================================================================================================");
        System.out.println("                                               [RECEIPT]                                                  ");
        System.out.println("==========================================================================================================");
        conf.viewSingleRecord(qry, headers, columns, bookingId);
        System.out.println("==========================================================================================================");
    }

    
    public void viewReport() {
        config conf = new config();
       
        String qry = "SELECT b_id, c_fname, e_name, e_date, e_venue, e_price, b_date, b_status " +
                     "FROM tbl_booking " +
                     "LEFT JOIN tbl_customers ON tbl_customers.c_id = tbl_booking.c_id " +
                     "LEFT JOIN tbl_event ON tbl_event.e_id = tbl_booking.e_id";

        String[] headers = {"Booking ID", "Customer Name", "Event Name", "Event Date", "Venue", "Price", "Booking Date", "Status"};
        String[] columns = {"b_id", "c_fname", "e_name", "e_date", "e_venue", "e_price", "b_date", "b_status"};

        System.out.println("==========================================================================================================");
        System.out.println("                                            [BOOKING REPORT]                                              ");
        System.out.println("==========================================================================================================");
        conf.viewRecords(qry, headers, columns);
        System.out.println("==========================================================================================================");
    }

    
    public void reportMenu() {
        String response;
        
        do {
            try {
                System.out.println("          --- VIEW REPORTS ---          ");
                System.out.println("");
                System.out.println("[1. VIEW DETAILED REPORT]");
                System.out.println("[2. VIEW RECEIPT BY BOOKING ID]");
                System.out.println("[3. EXIT REPORTS]");
                System.out.println("");
                System.out.print("Enter Action: ");

                int action = sc.nextInt();
                switch (action) {
                    case 1:
                        viewReport();
                        break;
                    case 2:
                        System.out.print("Enter Booking ID: ");
                        if (!sc.hasNextInt()) {
                            System.out.println("Invalid input. Please enter a valid Booking ID.");
                            sc.next(); 
                            break;
                        }
                        int bookingId = sc.nextInt();
                        viewReceipt(bookingId);
                        break;
                    case 3:
                        System.out.println("Exiting Reports...");
                        return;
                    default:
                        System.out.println("Invalid action! Please choose a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a valid number.");
                sc.next();
            }

            System.out.print("\nDo you want to continue? (yes/no): ");
            response = sc.next();
        } while (response.equalsIgnoreCase("yes"));

        System.out.println("Thank you for using the report viewer!");
    }
}
