package comic;

public class Comic {
    private int comicId;
    private String title;
    private String author;
    private String publisher;
    private int price;
    private int stock;

    public Comic(int comicId, String title, String author, String publisher, int price, int stock) {
        this.comicId = comicId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.stock = stock;
    }

    public int getComicId() {
        return comicId;
    }

    public void setComicId(int comicId) {
        this.comicId = comicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "ID: " + comicId +
                ", 제목: " + title +
                ", 작가: " + author +
                ", 출판사: " + publisher +
                ", 가격: " + price +
                ", 재고: " + stock;
    }
}