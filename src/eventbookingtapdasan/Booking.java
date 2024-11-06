package eventbookingtapdasan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Booking {
    
    public void bTransaction(){
        Scanner sc = new Scanner (System.in);
        String response;
        do{
        
        System.out.println("1. ADD");
        System.out.println("2. VIEW");
        System.out.println("3. UPDATE");
        System.out.println("4. DELETE");
        System.out.println("5. EXIT");
        
        System.out.println("Enter Action: ");
        int action = sc.nextInt();
        Booking bk = new Booking ();

        switch(action){
            case 1:
                addBooking();
                viewBooking();
                break;
            case 2:       
                viewBooking();
                break;
            case 3:
                
                break;
            case 4:
                   
                break;
        }
        System.out.println("Do you want to continue? (yes/no)");
        response = sc.next();
    }while(response.equalsIgnoreCase("yes"));
    System.out.println("Thank You, See you soonest!");
    
    }
     private void addBooking(){
        Scanner sc = new Scanner (System.in);   
        config conf = new config(); 
        
        Customer cs = new Customer();
        cs.viewCustomers();         
        
        System.out.print("Enter the ID of the Customer: ");
        int cid = sc.nextInt();
        
        String csql = "SELECT c_id FROM tbl_customers WHERE c_id = ?";
        while(conf.getSingleValue(csql, cid) == 0){
            System.out.print("Customer does not exist, Select Again: ");
            cid = sc.nextInt();
            
        }
        Event ev = new Event ();
        ev.viewEvents();
        
        System.out.print("Enter the ID of the Event: ");
        int eid = sc.nextInt();
        
        String esql = "SELECT e_id FROM tbl_event WHERE e_id = ?";
        while(conf.getSingleValue(esql, eid) == 0){
            System.out.print("Event does not exist, Select Again: ");
            eid = sc.nextInt();
        }
     
        LocalDate currdate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = currdate. format(format);

        String status = "Pending";
             
        String qry = "INSERT INTO tbl_booking (c_id, e_id, b_date, b_status)"
                + "VALUES (?, ?, ?, ?)";
        conf.addRecord(qry, cid, eid, date, status);
}
    
     public void viewBooking() {
        
        String qry = "SELECT b_id, c_fname, e_name, e_date, e_venue, e_price, b_date, b_status FROM tbl_booking "
        + "LEFT JOIN tbl_customers ON tbl_customers.c_id = tbl_booking.c_id "
        + "LEFT JOIN tbl_event ON tbl_event.e_id = tbl_booking.e_id ";
       
        String[] hrds = {"B_ID", "Customer", "Event Name", "Venue", "Price", "Booking Date" ,"Booking Status"};
        String[] clms = {"b_id", "c_fname", "e_name", "e_venue", "e_price" ,"b_date", "b_status"};
        config conf = new config();
        conf.viewRecords(qry, hrds, clms);
     
}  
    
}