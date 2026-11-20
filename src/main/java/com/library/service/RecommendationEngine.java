package com.library.service;
import com.library.model.*;
import java.util.*;
import java.util.stream.Collectors;
public class RecommendationEngine {
 private final LibraryService library;
 public RecommendationEngine(LibraryService library){this.library=library;}
 public List<Book> recommendBooks(Member member){
  Set<String> categories=member.getBorrowedCategories().stream().map(String::toLowerCase).collect(Collectors.toSet());
  Set<String> borrowed=new HashSet<>(library.getBorrowedBookIds(member.getId()));
  return library.getBooks().stream().filter(Book::isAvailable).filter(b->categories.contains(b.getCategory().toLowerCase())).filter(b->!borrowed.contains(b.getId())).collect(Collectors.toList());
 }
}
