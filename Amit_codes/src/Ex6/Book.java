package Ex6;

public class Book {

    private int price;
    private  String title;
    private String author;
    private int numOfPages;

    public Book(int price,String title,String author,int numOfPages)
    {
        if (price>300||price<10)
            throw new RuntimeException("invalid price");
        this.price=price;
        this.title=title;
        this.author=author;
        this.numOfPages=numOfPages;
    }
    public Book(Book b)
    {
         this (b.price,b.title, b.author, b.numOfPages);
    }

    public int getPrice() {
        return price;
    }

    public int getNumOfPages()
    {
        return numOfPages;
    }
    public String getAuthor()
    {
        return author;
    }
    public String getTitle()
    {
        return title;
    }
    public void setPrice(int price)
    {
        this.price=price;
    }

    public void addOnePage()
    {
        this.numOfPages++;
    }


    public static void main(String[]args)
    {
        Book book1=new Book(75,"Harry Potter","J.K Rowling",361);
        book1.setPrice(100);
        System.out.println(book1.title+book1.getAuthor());
    }

}
