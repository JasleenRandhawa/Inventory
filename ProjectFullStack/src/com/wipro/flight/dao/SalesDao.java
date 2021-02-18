package com.wipro.flight.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;


import com.wipro.flight.bean.Sales;
import com.wipro.flight.bean.SalesReport;
/**
 * refer to this
 * https://github.com/Cyberster/Wipro-Training-Module7---Inventory-Sales-System/blob/master/src/com/wipro/sales/dao/SalesDao.java
 */
import com.wipro.flight.util.DBUtil;
public class SalesDao 
{
    Connection con = DBUtil.getDBConnection();
    PreparedStatement pst = null;
    String query;

    /**
     * to insert sales object into TBL_SALES
     * @param sales
     * @return
     */
    public int insertSales(Sales sales)
    {
        query = "INSERT INTO TBL_SALES VALUES(?, ?, ?, ?, ?)";

        java.sql.Date sqlDate = new java.sql.Date(sales.getSalesDate().getTime());
        
        try
        {
            pst = con.prepareStatement(query);
            pst.setString(1, sales.getSalesID());
            pst.setDate(2, sqlDate);
            pst.setString(3, sales.getProductID());
            pst.setInt(4, sales.getQuantitySold());
            pst.setDouble(5, sales.getSalesPricePerUnit());
            
            if(pst.executeUpdate() == 1)
            {
                pst.close();
                return 1;
            }
            return 0;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
        finally
        {
            try 
            {
                if(pst != null)
                    pst.close();    
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * to generate SalesID = last 2 digits of Year + SEQ_SALES_ID
     * @param salesDate
     * @return
     */
    public String generateSalesID(Date salesDate)
    {
        query = "SELECT SEQ_SALES_ID.NEXTVAL FROM DUAL";
        ResultSet res = null;

        Calendar cal = Calendar.getInstance();
        cal.setTime(salesDate);
        int year = cal.get(Calendar.YEAR);
        String output = "";
        output += year;

        try
        {
            pst = con.prepareStatement(query);
            res = pst.executeQuery();

            res.next();
            output +=res.getInt(1);

            res.close();
            pst.close();

            return output;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            try 
            {
                if(res != null)
                    res.close();    
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }

            try 
            {
                if(pst != null)
                    pst.close();    
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * to store every record int SalesReport in a List
     * @return
     */
    public List<SalesReport> getSalesReport()
    {
        query = "SELECT * FROM V_SALES_REPORT";

        List<SalesReport> list = new ArrayList<> ();
        ResultSet res = null;
        
        try
        {
            pst = con.prepareStatement(query);
            res = pst.executeQuery();

            while(res.next())
            {
                SalesReport report = new SalesReport();
                report.setSalesID(res.getString(1));
                report.setSalesDate(res.getDate(2));
                report.setProductID(res.getString(3));
                report.setProductName(res.getString(4));
                report.setQuantitySold(res.getInt(5));
                report.setProductUnitPrice(res.getDouble(6));
                report.setSalesPricePerUnit(res.getDouble(7));
                report.setProfitAmount(res.getDouble(8));

                list.add(report);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return Collections.emptyList();
        }
        finally
        {
            try 
            {
                if(res != null)
                    res.close();    
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }

            try 
            {
                if(pst != null)
                    pst.close();    
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
        return list;

    }

}
