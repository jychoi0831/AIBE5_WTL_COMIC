package model;

import java.time.LocalDate;

public class Comic {
	
	private long comicId;           // 만화책ID (PK)
	private String title;           // 제목
	private String author;          // 작가
	private long volumeNumber;      // 시리즈 권수 (1권, 2권 등)
	private long rentPrice;         // 대여 가격
	private LocalDate createdAt;    // 등록일
	 
	public Comic(long comicId, String title, String author, long volumeNumber, long rentPrice, LocalDate createdAt) {
		 this.comicId = comicId;
	     this.title = title;
	     this.author = author;
	     this.volumeNumber = volumeNumber;
	     this.rentPrice = rentPrice;
	     this.createdAt = createdAt;
	}
	
	public long getComicId() {
        return comicId;
    }
	
    public void setComicId(long comicId) {
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
    
    public long getVolumeNumber() {
        return volumeNumber;
    }
    
    public void setVolumeNumber(long volumeNumber) {
        this.volumeNumber = volumeNumber;
    }
    
    public long getRentPrice() {
        return rentPrice;
    }
    
    public void setRentPrice(long rentPrice) {
        this.rentPrice = rentPrice;
    }
    
    public LocalDate getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
