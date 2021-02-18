package com.wipro.flight.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.wipro.flight.bean.Sales;
import com.wipro.flight.bean.Stock;
import com.wipro.flight.service.Administrator;

public class SalesApplication 
{
    static Administrator admin = new Administrator();
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) throws ParseException {
        

        int choice = 0;

        do {
            System.out.println("1. Insert Stock");
            System.out.println("2. Delete Stock");
            System.out.println("3. Insert Sales");
            System.out.println("4. View Sales Report");
            System.out.println("Any other number to exit");
            System.out.println("Enter Your Choice :");
            choice = s.nextInt();
            operateFunction(choice);
        } while (choice > 0 && choice < 5);

        s.close();
    }

    public static void operateFunction(int choice) throws ParseException
    {
        switch (choice) 
        {
            case 1:
            {
                insertStock();
                break;
            }
            case 2:
            {
                deleteStock();
                break;
            }
            case 3:
            {
                insertSales();
                break;
            }
            case 4:
            {
                admin.getSalesReport();
                break;
            }
            default:
                System.out.println("Exiting portal...");
                break;
        }
        
    }

    public static void insertStock() 
    {
        Stock stock = new Stock();
        System.out.println("Enter Product ID : ");
        stock.setProductID(s.nextLine());
        System.out.println("Enter Product Name : ");
        stock.setProductName(s.nextLine());
        System.out.println("Enter quantity on hand : ");
        stock.setQuantityOnHand(s.nextInt());
        System.out.println("Enter Product Unit Price : ");
        stock.setProductUnitPrice(s.nextDouble());
        System.out.println("Enter Product Reorder Level : ");
        stock.setReorderLevel(s.nextInt());

        admin.insertStock(stock);
    }

    public static void deleteStock() 
    {
        System.out.println("Enter ProductID to be Deleted :");
        String id = s.next();
        if (admin.deleteStock(id)) 
            System.out.println(id + " Removed Successfully");  
    }

    public static void insertSales() throws ParseException 
    {
        Sales sales = new Sales();
        System.out.println("Enter SalesID :");
        sales.setSalesID(s.next());
        System.out.println("Enter date (dd-mm-yyyy) :");
        String salesDate = s.next();
        Date date = new SimpleDateFormat("dd-mm-yyyy").parse(salesDate);
        sales.setSalesDate(date);
        System.out.println("Enter ProductID :");
        sales.setProductID(s.next());
        System.out.println("Enter Quantity Sold : ");
        sales.setQuantitySold(s.nextInt());
        System.out.println("Enter Sales Price / Unit : ");
        sales.setSalesPricePerUnit(s.nextDouble());

        admin.insertSales(sales);    
    }
}
