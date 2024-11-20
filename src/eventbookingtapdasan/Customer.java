package eventbookingtapdasan;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Customer {
    
    public void cTransaction() {
        Scanner sc = new Scanner(System.in);
        String response;
        do {
            try {
                System.out.println("          --- [CUSTOMER PANEL] ---          ");
                System.out.println("");
                System.out.println("[1. ADD CUSTOMER]");
                System.out.println("[2. VIEW CUSTOMER]");
                System.out.println("[3. UPDATE CUSTOMER]");
                System.out.println("[4. DELETE CUSTOMER]");
                System.out.println("[5. EXIT CUSTOMER]");
                System.out.println("");
                
                System.out.print("Enter Action: ");
                System.out.println("");
                int action = sc.nextInt();
                Customer cs = new Customer();
                
                switch (action) {
                    case 1:
                        cs.addCustomers();
                        break;
                    case 2:
                        cs.viewCustomers();
                        break;
                    case 3:
                        cs.viewCustomers();
                        cs.updateCustomers();
                        cs.viewCustomers();
                        break;
                    case 4:
                        cs.viewCustomers();
                        cs.deleteCustomers();
                        cs.viewCustomers();
                        break;
                    case 5:
                        System.out.println("Exiting Customer...");
                        return; 
                    default:
                        System.out.println("Invalid action! Please choose a number between 1 and 5.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid . Please enter a valid number.");
                sc.next();
            }

            System.out.print("Do you want to continue? (yes/no): ");
            response = sc.next();
        } while (response.equalsIgnoreCase("yes"));
        
        System.out.println("Thank You, See you soonest!");
    }
    
    public void addCustomers() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("First Name: ");
        String fname = sc.nextLine();
        System.out.print("Last Name: ");
        String lname = sc.nextLine();
        System.out.print("Email: ");
        String em = sc.nextLine();
        System.out.print("Phone number: ");
        String pn = sc.nextLine();
        System.out.print("Address: ");
        String add = sc.nextLine();
        String sql = "INSERT INTO tbl_customers (c_fname, c_lname, c_email, c_phonenumber, c_address) VALUES (?, ?, ?, ?, ?)";
        conf.addRecord(sql, fname, lname, em, pn, add);
    }

    public void viewCustomers() {
        config conf = new config();
        String Query = "SELECT * FROM tbl_customers";
        String[] Headers = {"Customer_ID", "FirstName", "LastName", "Email", "PhoneNumber", "Address"};
        String[] Columns = {"c_id", "c_fname", "c_lname", "c_email", "c_phonenumber", "c_address"};
        conf.viewRecords(Query, Headers, Columns);
    }

    private void updateCustomers() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        int id;
        while (true) {
            System.out.print("Enter the ID to update: ");
            while (!sc.hasNextInt()) {
            System.out.print("Invalid input! Please enter a valid Customer ID: ");
            sc.next();
        }
            try {
                id = sc.nextInt();
                if (conf.getSingleValue("SELECT c_id FROM tbl_customers WHERE c_id = ?", id) != 0) {
                    break; 
                }
                System.out.println("Selected ID doesn't exist! Try again.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid ID! Please enter a valid ID.");
                sc.nextLine(); 
            }
        }

        sc.nextLine(); 
        System.out.print("New First Name: ");
        String nfname = sc.nextLine();
        System.out.print("New Last Name: ");
        String nlname = sc.nextLine();
        System.out.print("New Email: ");
        String nem = sc.nextLine();
        System.out.print("New Phone Number: ");
        String npn = sc.nextLine();
        System.out.print("New Address: ");
        String nadd = sc.nextLine();

        String qry = "UPDATE tbl_customers SET c_fname = ?, c_lname = ?, c_email = ?, c_phonenumber = ?, c_address = ? WHERE c_id = ?";
        conf.updateRecord(qry, nfname, nlname, nem, npn, nadd, id);
    }

    private void deleteCustomers() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Enter the ID to delete: ");
         int id;
        while (true) {
            System.out.print("Enter the ID to update: ");
            while (!sc.hasNextInt()) {
            System.out.print("Invalid input! Please enter a valid Customer ID: ");
            sc.next();
        }
            try {
                id = sc.nextInt();
                if (conf.getSingleValue("SELECT c_id FROM tbl_customers WHERE c_id = ?", id) != 0) {
                    break; 
                }
                System.out.println("Selected ID doesn't exist! Try again.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid ID! Please enter a valid ID.");
                sc.nextLine(); 
            }
        }

        String qry = "DELETE FROM tbl_customers WHERE c_id = ?";
        conf.deleteRecord(qry, id);
    }
}
