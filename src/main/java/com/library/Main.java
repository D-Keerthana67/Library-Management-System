package com.library;

import com.library.exception.LibraryException;
import com.library.model.*;
import com.library.service.*;
import java.util.*;

public class Main {
 private static final Scanner sc=new Scanner(System.in);
 private static final LibraryService library=new LibraryService();
 private static final RecommendationEngine recommender=new RecommendationEngine(library);
 public static void main(String[] args){addSamples();int choice;do{menu();choice=readInt("Enter your choice: ");try{switch(choice){case 1->addBook();case 2->addMember();case 3->viewBooks();case 4->viewMembers();case 5->searchBooks();case 6->borrowBook();case 7->returnBook();case 8->viewTransactions();case 9->recommend();case 0->System.out.println("Thank you for using the library system!");default->System.out.println("Invalid choice.");}}catch(LibraryException|IllegalArgumentException e){System.out.println("Error: "+e.getMessage());}}while(choice!=0);sc.close();}
 private static void menu(){System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");System.out.println("1.Add book  2.Register member  3.View books  4.View members  5.Search books  6.Borrow book  7.Return book  8.Member transactions  9.Recommend books  0.Exit");}
 private static void addSamples(){try{library.addBook(new Book("B101","Clean Code","Robert C. Martin","Programming",3));library.addBook(new Book("B102","Effective Java","Joshua Bloch","Programming",2));library.addBook(new Book("B103","The Alchemist","Paulo Coelho","Fiction",2));library.addBook(new Book("B104","Wings of Fire","A. P. J. Abdul Kalam","Biography",2));library.addBook(new Book("B105","Java Complete Reference","Herbert Schildt","Programming",2));library.registerMember(new Member("M101","Arun"));library.registerMember(new Member("M102","Priya"));}catch(LibraryException e){System.out.println(e.getMessage());}}
 private static void addBook()throws LibraryException{String id=text("Book ID: "),title=text("Title: "),author=text("Author: "),category=text("Category: ");int copies=readInt("Copies: ");library.addBook(new Book(id,title,author,category,copies));System.out.println("Book added.");}
 private static void addMember()throws LibraryException{library.registerMember(new Member(text("Member ID: "),text("Member name: ")));System.out.println("Member registered.");}
 private static void viewBooks(){List<Book> list=library.getBooks();if(list.isEmpty())System.out.println("No books found.");else list.forEach(System.out::println);}
 private static void viewMembers(){List<Member> list=library.getMembers();if(list.isEmpty())System.out.println("No members found.");else list.forEach(System.out::println);}
 private static void searchBooks(){List<Book> list=library.searchBooks(text("Search title, author, or category: "));if(list.isEmpty())System.out.println("No matching books.");else list.forEach(System.out::println);}
 private static void borrowBook()throws LibraryException{Transaction t=library.borrowBook(text("Book ID: "),text("Member ID: "));System.out.println("Borrowed successfully. Transaction ID: "+t.getId()+" | Due date: "+t.getDueDate());}
 private static void returnBook()throws LibraryException{double fine=library.returnBook(text("Transaction ID: "));System.out.printf("Returned successfully. Fine: Rs. %.2f%n",fine);}
 private static void viewTransactions()throws LibraryException{List<Transaction> list=library.getMemberTransactions(text("Member ID: "));if(list.isEmpty())System.out.println("No transactions found.");else list.forEach(System.out::println);}
 private static void recommend()throws LibraryException{String id=text("Member ID: ");Member m=library.getMembers().stream().filter(x->x.getId().equals(id)).findFirst().orElseThrow(()->new LibraryException("Member ID not found."));List<Book> list=recommender.recommendBooks(m);if(list.isEmpty())System.out.println("No recommendations yet. Borrow books to build your reading preferences.");else{System.out.println("Recommended for "+m.getName()+":");list.forEach(System.out::println);}}
 private static String text(String prompt){System.out.print(prompt);return sc.nextLine().trim();}
 private static int readInt(String prompt){while(true){System.out.print(prompt);try{return Integer.parseInt(sc.nextLine().trim());}catch(NumberFormatException e){System.out.println("Enter a valid whole number.");}}}
}
