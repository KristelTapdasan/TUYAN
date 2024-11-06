/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventbookingtapdasan;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EVENTBOOKINGTAPDASAN {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);    
        boolean exit = true;
        do{
        
        System.out.println("1. CUSTOMER");
        System.out.println("2. EVENT");
        System.out.println("3. BOOKING");
        System.out.println("4. EXIT");
        
        System.out.print("Enter Action: ");
        int act = sc.nextInt();
        
        switch(act){
            case 1:
                Customer cs = new Customer ();
                cs.cTransaction();
            break;
            
            case 2:
                Event ev = new Event ();
                ev.eTransaction();
            break;
            
            case 3:
                Booking bk = new Booking ();
                bk.bTransaction();
            break;
            case 4:
                System.out.println("Are u sure?? (yes/no): ");
                String resp = sc.next();
                    if(resp.equalsIgnoreCase("yes")){
                           exit = false;
                }
            break;   
            
   
        }
        }while (exit);
        System.out.println("See you soon!!");
        
        
        }
  }
    
