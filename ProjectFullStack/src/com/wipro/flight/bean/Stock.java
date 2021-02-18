package com.wipro.flight.bean;

public class Stock 
{
    private String productID;
    private String productName;
    private int quantityOnHand;
    private double productUnitPrice;
    private int reorderLevel;
    
    public String getProductID()
    {
        return productID;
    }

    public String getProductName()
    {
        return productName;
    }

    public int getQuantityOnHand()
    {
        return quantityOnHand;
    }

    public double getProductUnitPrice()
    {
        return productUnitPrice;
    }

    public int getReorderLevel()
    {
        return reorderLevel;
    }

// setter methods

    public void setProductID(String s)
    {
        productID = s;
    }

    public void setProductName(String s)
    {
        productName = s;
    }

    public void setQuantityOnHand(int s)
    {
        quantityOnHand = s;
    }

    public void setProductUnitPrice(double s)
    {
        productUnitPrice = s;
    }
    
    public void setReorderLevel(int s)
    {
        reorderLevel = s;
    }
}
