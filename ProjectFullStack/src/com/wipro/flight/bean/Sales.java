package com.wipro.flight.bean;


public class Sales 
{
    private String salesID;
    private java.util.Date salesDate;
    private String productID;
    private int quantitySold;
    private double salesPricePerUnit;

    public String getSalesID()
    {
        return salesID;
    }

    public java.util.Date getSalesDate()
    {
        return salesDate;
    }

    public String getProductID()
    {
        return productID;
    }

    public int getQuantitySold() 
    {
        return quantitySold;
    }

    public double getSalesPricePerUnit() 
    {
        return salesPricePerUnit;
    }

    //setter methods

    public void setSalesID(String salesID) 
    {
        this.salesID = salesID;
    }

    public void setSalesDate(java.util.Date salesDate) 
    {
        this.salesDate = salesDate;
    }

    public void setProductID(String productID) 
    {
        this.productID = productID;
    }

    public void setQuantitySold(int quantitySold) 
    {
        this.quantitySold = quantitySold;
    }

    public void setSalesPricePerUnit(double salesPricePerUnit) 
    {
        this.salesPricePerUnit = salesPricePerUnit;
    }
    

}