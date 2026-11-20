package com.library.model;
import java.time.LocalDate;
public class Transaction {
 private final String id,bookId,memberId; private final LocalDate borrowDate,dueDate; private LocalDate returnDate; private double fine;
 public Transaction(String id,String bookId,String memberId,LocalDate borrowDate,LocalDate dueDate){this.id=id;this.bookId=bookId;this.memberId=memberId;this.borrowDate=borrowDate;this.dueDate=dueDate;}
 public String getId(){return id;} public String getBookId(){return bookId;} public String getMemberId(){return memberId;} public LocalDate getBorrowDate(){return borrowDate;} public LocalDate getDueDate(){return dueDate;} public LocalDate getReturnDate(){return returnDate;} public double getFine(){return fine;} public boolean isReturned(){return returnDate!=null;}
 public void completeReturn(LocalDate date,double calculatedFine){if(isReturned()) throw new IllegalStateException("Already returned.");returnDate=date;fine=calculatedFine;}
 @Override public String toString(){return "Transaction "+id+" | Book: "+bookId+" | Member: "+memberId+" | Borrowed: "+borrowDate+" | Due: "+dueDate+" | Returned: "+(returnDate==null?"Not returned":returnDate)+" | Fine: Rs. "+String.format("%.2f",fine);}
}
