package Ex6;

public class Shopping {
    private String dateOfShopping;
    private double price;
    private int howLongItTook;
    private String shopName;

    public Shopping(String dateOfShopping,double price,int howLongItTook,String shopName) {
        if (dateOfShopping.length() < 10 || dateOfShopping.charAt(2) != '/' || dateOfShopping.charAt(5) != '/') {
            throw new RuntimeException("invalid year");
        }
        int year=0;
        try
        {
            year=Integer.parseInt(dateOfShopping.substring(6,10));
        }
        catch (NumberFormatException e)
        {
            throw new RuntimeException("not valid");
        }
        if (year<2022)
        {
            throw new RuntimeException("Year is out of accepted range. Must be 2022 or later.");
        }


        this.dateOfShopping=dateOfShopping;
        this.price=price;
        this.howLongItTook=howLongItTook;
        this.shopName=shopName;
    }

    public String getDateOfShopping()
    {
        return dateOfShopping;
    }
    public double getPrice()
    {return price;
    }
    public String getShopName()
    {
        return shopName;
    }
    public int getHowLongItTook()
    {
        return howLongItTook;
    }

    public void setPrice(double price)
    {
        if (price<0)
            throw new RuntimeException("cant be under 0");
        this.price=price;
    }

    public void setDateOfShopping(String dateOfShopping)
    {
        this.dateOfShopping=dateOfShopping;
    }
    public void setHowLongItTook(int howLongItTook)
    {
        this.howLongItTook=howLongItTook;
    }
    public void setShopName(String shopName)
    {
        this.shopName=shopName;
    }
    public void display()
    {
        System.out.println("date- "+dateOfShopping);
        System.out.println("price- "+price);
        System.out.println("shop Name- "+shopName);
    }

}
