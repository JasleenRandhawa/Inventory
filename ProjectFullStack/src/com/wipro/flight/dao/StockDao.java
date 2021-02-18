package com.wipro.flight.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wipro.flight.bean.Stock;
import com.wipro.flight.util.DBUtil;

public class StockDao 
{
    Connection con = DBUtil.getDBConnection();
    PreparedStatement pst = null;
    String query;

    // to insert given stock obj into TBL_STOCK table
    public int insertStock( Stock sales)
    {
        
        query = "INSERT INTO TBL_STOCK VALUES(?, ?, ?, ?, ?)";

        try
        {
             
            pst = con.prepareStatement(query);
            pst.setString(1, sales.getProductID());
            pst.setString(2, sales.getProductName());
            pst.setInt(3, sales.getQuantityOnHand());
            pst.setDouble(4, sales.getProductUnitPrice());
            pst.setInt(5, sales.getReorderLevel());

            if(pst.executeUpdate() == 1) //while executing check if it is successful or not
                {
                    pst.close();
                    return 1;
                }
            pst.close();
            return 0;
        }
        catch( SQLException e)
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
    
    // to generate Stock ID using first 2 letters of the product name + SEQ_PRODUCT_ID
    public String generateProductID(String productName) 
    {
        ResultSet res = null;
        query = "SELECT SEQ_PRODUCT_ID.NEXTVAL FROM DUAL"; //dual can be specified as a table when no table is required
        String output = "";

        try
        {
            pst = con.prepareStatement(query);
            res = pst.executeQuery();
            res.next();
            
            output += productName.substring(0, 2);
            output += res.getInt(1);

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
                if(pst != null)
                    pst.close();    
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }

            try 
            {
                if(res != null)
                    res.close();    
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
    }

    //to update TBL_STOCK table by subtracting Quantity_On_Hand by soldQty of given productID
    public int updateStock(String productID, int soldQty)
    {
        query = "UPDATE TBL_STOCK SET Quantity_On_Hand = Quantity_On_Hand - ? " + "WHERE Product_ID = ?";

        try
        {
            pst = con.prepareStatement(query);
            pst.setInt(1, soldQty);
            pst.setString(2, productID);

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

    // to fetch a specific record's details from TBL_STOCK for the given productID
    // store the info int Stock bean object and return the same
    public Stock getStock(String productID)
    {
        query = "SELECT * FROM TBL_STOCK WHERE Product_ID = ?";
        ResultSet res = null;

        try
        {
            pst = con.prepareStatement(query);
            pst.setString(1, productID);

            res = pst.executeQuery();

            res.next();
            Stock stock = new Stock();
            stock.setProductID(res.getString(1));
            stock.setProductName(res.getString(2));
            stock.setQuantityOnHand(res.getInt(3));
            stock.setProductUnitPrice(res.getDouble(4));
            stock.setReorderLevel(res.getInt(5));

            res.close();
            pst.close();

            return stock;
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
                if(pst != null)
                    pst.close();    
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }

            try 
            {
                if(res != null)
                    res.close();    
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }

        }

    }

    // to delete the record of the given Product_ID; successful return "deleted"; else "record cannot be deleted"
    public String deleteStock(String productID)
    {
        query = "DELETE TBL_STOCK WHERE Product_ID = ?";

        try
        {
            pst = con.prepareStatement(query);
            pst.setString(1, productID);

            if(pst.executeUpdate() == 1)
                {
                    pst.close();
                    return "deleted";
                }
            return "record cannot be deleted";
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return "record cannot be deleted";
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
}
