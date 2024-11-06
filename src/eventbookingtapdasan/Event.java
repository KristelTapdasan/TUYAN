package eventbookingtapdasan;

import java.util.Scanner;

public class Event {
    
    public void eTransaction(){
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
        Event ev = new Event ();

        switch(action){
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
        }
        System.out.println("Do you want to continue? (yes/no)");
        response = sc.next();
    }while(response.equalsIgnoreCase("yes"));
    System.out.println("Thank You, See you soonest!");
    
    }
    
    
    public void addEvents(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Event Name: ");
        String ename = sc.next();
        System.out.print("Date: ");
        String date = sc.next();
        System.out.print("Venue: ");
        String ven = sc.next();
        System.out.println("Price: ");
        double price = sc.nextDouble();
        String sql = "INSERT INTO tbl_event (e_name, e_date, e_venue, e_price) VALUES (?, ?, ?, ?)";
        conf.addRecord(sql, ename, date, ven, price);


    }

    public void viewEvents() {
        config conf = new config();
        String Query = "SELECT * FROM tbl_event";
        String[] Headers = {"Event_ID","EventName", "Date", "Venue", "Price"};
        String[] Columns = {"e_id", "e_name", "e_date", "e_venue", "e_price"};
        
        
        conf.viewRecords(Query, Headers, Columns);
    }
    private void updateEvents() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Enter the ID to update: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT e_id FROM tbl_event WHERE e_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Event ID Again: ");
        id = sc.nextInt();
        }
        
        System.out.println("New Event Name: ");
        String nename = sc.next();
        
        System.out.println("New Date: ");
        String ndate = sc.next();
        
        System.out.println("New Venue: ");
        String nven = sc.next();
        
        System.out.println("New Price: ");
        double nprice = sc.nextDouble();
                
        String qry = "UPDATE tbl_event SET e_name = ?, e_date = ?, e_venue = ?, e_price = ?, WHERE e_id = ?";
        
        
        conf.updateRecord(qry, nename, ndate, nven, nprice, id);         
        
        
    }
    
    private void deleteEvents() {
        Scanner sc = new Scanner (System.in);
        config conf = new config();
        System.out.print("Enter the ID to update: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT e_id FROM tbl_event WHERE e_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Event ID Again: ");
        id = sc.nextInt();
        }
        
        String qry = "DELETE FROM tbl_event WHERE e_id = ?";
        conf.deleteRecord(qry, id);
    }
    
}
    