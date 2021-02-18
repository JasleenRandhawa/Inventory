package com.wipro.flight.bean;

public class SalesReport 
{
    private String salesID;
    private java.util.Date salesDate;
    private String productID;
    private String productName;
    private int quantitySold;
    private double productUnitPrice;
    private double salesPricePerUnit;
    private double profitAmount;

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

    public String getProductName() 
    {
        return productName;
    }

    public int getQuantitySold() 
    {
        return quantitySold;
    }

    public double getProductUnitPrice() 
    {
        return productUnitPrice;
    }

    public double getSalesPricePerUnit() 
    {
        return salesPricePerUnit;
    }

    public double getProfitAmount() 
    {
        return profitAmount;
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

    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public void setQuantitySold(int quantitySold) 
    {
        this.quantitySold = quantitySold;
    }

    public void setProductUnitPrice(double productUnitPrice) 
    {
        this.productUnitPrice = productUnitPrice;
    }

    public void setSalesPricePerUnit(double salesPricePerUnit) 
    {
        this.salesPricePerUnit = salesPricePerUnit;
    }

    public void setProfitAmount(double profitAmount) 
    {
        this.profitAmount = profitAmount;
    }

}
