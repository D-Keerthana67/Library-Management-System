package com.library.model;

public class Book {
 private final String id, title, author, category;
 private final int totalCopies;
 private int availableCopies;
 public Book(String id,String title,String author,String category,int copies){
  if(copies<=0) throw new IllegalArgumentException("Copies must be greater than zero.");
  this.id=id; this.title=title; this.author=author; this.category=category; this.totalCopies=copies; this.availableCopies=copies;
 }
 public String getId(){return id;} public String getTitle(){return title;} public String getAuthor(){return author;} public String getCategory(){return category;}
 public int getTotalCopies(){return totalCopies;} public int getAvailableCopies(){return availableCopies;} public boolean isAvailable(){return availableCopies>0;}
 public void borrowCopy(){if(!isAvailable()) throw new IllegalStateException("No copies are available."); availableCopies--;}
 public void returnCopy(){if(availableCopies<totalCopies) availableCopies++;}
 @Override public String toString(){return id+" | "+title+" | "+author+" | "+category+" | Available: "+availableCopies+"/"+totalCopies;}
}
