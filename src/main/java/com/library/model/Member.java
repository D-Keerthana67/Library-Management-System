package com.library.model;
import java.util.*;
public class Member {
 private final String id,name;
 private final List<String> borrowedCategories=new ArrayList<>();
 public Member(String id,String name){this.id=id;this.name=name;}
 public String getId(){return id;} public String getName(){return name;}
 public void recordBorrowedCategory(String category){borrowedCategories.add(category);}
 public List<String> getBorrowedCategories(){return Collections.unmodifiableList(borrowedCategories);}
 @Override public String toString(){return id+" | "+name;}
}
