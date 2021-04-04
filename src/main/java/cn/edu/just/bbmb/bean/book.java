package cn.edu.just.bbmb.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class book {
    @Id
    private Integer BookID;
    private String BookName;
    private Date LastBorrowDate;
    private String preferenceID;

    public Integer getBookID() {
        return BookID;
    }

    public void setBookID(Integer bookID) {
        BookID = bookID;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public Date getLastBorrowDate() {
        return LastBorrowDate;
    }

    public void setLastBorrowDate(Date lastBorrowDate) {
        LastBorrowDate = lastBorrowDate;
    }

    public String getPreferenceID() {
        return preferenceID;
    }

    public void setPreferenceID(String preferenceID) {
        this.preferenceID = preferenceID;
    }
}