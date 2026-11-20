package com.library.service;

import com.library.exception.*;
import com.library.model.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class LibraryService {
 private static final int LOAN_DAYS=14; private static final double FINE_PER_DAY=5.0;
 private final Map<String,Book> books=new HashMap<>(); private final Map<String,Member> members=new HashMap<>(); private final List<Transaction> transactions=new ArrayList<>();
 public void addBook(Book book)throws LibraryException{if(books.containsKey(book.getId()))throw new LibraryException("A book with this ID already exists.");books.put(book.getId(),book);}
 public void registerMember(Member member)throws LibraryException{if(members.containsKey(member.getId()))throw new LibraryException("A member with this ID already exists.");members.put(member.getId(),member);}
 public List<Book> getBooks(){return new ArrayList<>(books.values());} public List<Member> getMembers(){return new ArrayList<>(members.values());}
 public List<Book> searchBooks(String keyword){String s=keyword.toLowerCase();return books.values().stream().filter(b->b.getTitle().toLowerCase().contains(s)||b.getAuthor().toLowerCase().contains(s)||b.getCategory().toLowerCase().contains(s)).collect(Collectors.toList());}
 public Transaction borrowBook(String bookId,String memberId)throws LibraryException{
  Book b=books.get(bookId);Member m=members.get(memberId);if(b==null)throw new LibraryException("Book ID not found.");if(m==null)throw new LibraryException("Member ID not found.");if(!b.isAvailable())throw new BookNotAvailableException("This book is currently unavailable.");
  b.borrowCopy();LocalDate today=LocalDate.now();Transaction t=new Transaction(UUID.randomUUID().toString().substring(0,8).toUpperCase(),bookId,memberId,today,today.plusDays(LOAN_DAYS));transactions.add(t);m.recordBorrowedCategory(b.getCategory());return t;
 }
 public double returnBook(String transactionId)throws LibraryException{
  Transaction t=transactions.stream().filter(x->x.getId().equalsIgnoreCase(transactionId)).findFirst().orElseThrow(()->new LibraryException("Transaction ID not found."));if(t.isReturned())throw new LibraryException("This book has already been returned.");
  LocalDate today=LocalDate.now();long lateDays=Math.max(0,ChronoUnit.DAYS.between(t.getDueDate(),today));double fine=lateDays*FINE_PER_DAY;books.get(t.getBookId()).returnCopy();t.completeReturn(today,fine);return fine;
 }
 public List<Transaction> getMemberTransactions(String memberId)throws LibraryException{if(!members.containsKey(memberId))throw new LibraryException("Member ID not found.");return transactions.stream().filter(t->t.getMemberId().equals(memberId)).collect(Collectors.toList());}
 public List<String> getBorrowedBookIds(String memberId){return transactions.stream().filter(t->t.getMemberId().equals(memberId)).map(Transaction::getBookId).collect(Collectors.toList());}
}
