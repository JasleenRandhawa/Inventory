package com.wipro.flight.service;

import java.util.List;

import com.wipro.flight.bean.Sales;
import com.wipro.flight.bean.Stock;
import com.wipro.flight.dao.SalesDao;
import com.wipro.flight.dao.StockDao;
import com.wipro.flight.bean.SalesReport;

public class Administrator 
{
    private static StockDao stockDao = new StockDao();
    private static SalesDao salesDao = new SalesDao();

    /**
     * This method is used to insert the given stock obj into the TBL_STOCK table 
	 * using StockDao class insertStock method if the below conditions are successful.
	 * 1. Stock obj should not be null
	 * 2. ProductName should be of minimum 2 letters in length
	 * 3. If above 2 are valid generate Product Id using StockDao class generateProductId 
	 * 	method and store the same in the ProductID member of the given Stock Object
	 * 
	 * If any of the above conditions fail return “Data not Valid for insertion”
	 * Else Return the generated ProductId 
     * @param stock
     * @return
     */
    public String insertStock(Stock stock)
    {
        if (stock != null && stock.getProductName().length() >= 2)
        {
            String productID = stockDao.generateProductID(stock.getProductName());
            stock.setProductID(productID);
            if(stockDao.insertStock(stock) == 1)
                return productID;
            return "Data not Valid for Insertion";
        }
        return "Data not Valid for Insertion";
    }

    /**
     * delete record using ProductID from StockDao using deleteStock()
     * @param productID
     * @return
     */
    public boolean deleteStock(String productID)
    {
        return stockDao.deleteStock(productID).equals("deleted");
    }

    /**
     *  to check various errors while inserting stocks
     * @param sales
     * @return
     */
    public String insertSales(Sales sales)
    {
        if (sales == null)
            return "Object not valid for insertiion";
        
        if (stockDao.getStock(sales.getProductID()) == null)
            return "Unknown product for sale";
            
        if (stockDao.getStock(sales.getProductID()).getQuantityOnHand() < sales.getQuantitySold())
            return "Not enough stocks to be sold";

        if (sales.getSalesDate().before(new java.util.Date()))
            return "Invalid date";

        String salesID = salesDao.generateSalesID(sales.getSalesDate());
        sales.setSalesID(salesID);

        if (salesDao.insertSales(sales) == 1)
        {
            if (stockDao.updateStock(sales.getProductID(), sales.getQuantitySold()) == 1) 
                return "Sales record inserted successfully";
            return "Error ! Updation Unsuccessful";
        }
        return "Error ! Insertion Unsuccessful";
    }

    public List<SalesReport> getSalesReport()
    {
        System.out.println(salesDao.getSalesReport());
        return salesDao.getSalesReport();
    }
    
}
