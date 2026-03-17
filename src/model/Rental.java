package model;

import java.time.LocalDate;

public class Rental {
    private long rentId;
    private long comicId;
    private long memberId;
    private LocalDate rentDate;
    private LocalDate returnDate;

    public Rental(long rentId, long comicId, long memberId, LocalDate rentDate, LocalDate returnDate) {
        this.rentId = rentId;
        this.comicId = comicId;
        this.memberId = memberId;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    public long getRentId() {
        return rentId;
    }

    public long getComicId() {
        return comicId;
    }

    public long getMemberId() {
        return memberId;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
}