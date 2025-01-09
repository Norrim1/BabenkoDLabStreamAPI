package org.example;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static java.lang.Math.round;


public class Main {
    public static void main(String[] args) throws IOException {

        String jsonBooksStr = FileUtils.readFileToString(new File("books.json"), StandardCharsets.UTF_8);
        System.out.print(jsonBooksStr);
        Gson gson = new Gson();
        libraryVisitor[] libraryVisitors = gson.fromJson(jsonBooksStr, (Type) libraryVisitor[].class);
        for(libraryVisitor visitor : libraryVisitors) {
            visitor.print();
        }
        for(libraryVisitor visitor : libraryVisitors) {
            visitor.printWithoutBooks();
        }
        System.out.println(libraryVisitors.length);
        //
        List<book> books = new ArrayList<>();
        for(libraryVisitor visitor : libraryVisitors) {
            for(book bookExVisitor : visitor.favoriteBooks)
            {

                boolean passed = true;
                for(book bookExList : books)
                {
                    if(!Objects.equals(bookExVisitor.getIsbn(), bookExList.getIsbn())){}
                    else
                    {
                        passed = false;
                        break;
                    }
                }
                if(passed)
                {
                    books.add(bookExVisitor);
                }
            }
        }
        for(book bookEx : books)
        {
            bookEx.print();
        }
        System.out.print(books.size());
        System.out.println(" unique books");
        //
        List<book> booksCopy = new ArrayList<>();
        booksCopy = books;
        booksCopy.sort(Comparator.comparingInt(book::getPublishingYear));
        System.out.println("Sorted books");
        for(book Book : booksCopy)
        {
            Book.print();
        }
        //
        for(libraryVisitor visitor : libraryVisitors) {
            boolean find = false;
            String VisitorName = visitor.getName();
            String VisitorSurname = visitor.getSurname();
            for(book bookExVisitor : visitor.favoriteBooks)
            {

                if(bookExVisitor.getAuthor().equals("Jane Austen"))
                {
                    find = true;
                    break;
                };

            }
            if(find)
            {
                System.out.println(VisitorName + " " + VisitorSurname +" has book with Jane Austen as author");
            }
        }
        //
        int maxBooksAmount = 0;
        for(libraryVisitor visitor : libraryVisitors)
        {
            if(visitor.favoriteBooks.length > maxBooksAmount) {
                maxBooksAmount = visitor.favoriteBooks.length;
            }
        }
        System.out.println("Max favorite books amount is " + maxBooksAmount);
        //
        List<libraryVisitor> libraryVisitorsSubscribed = new ArrayList<>();
        for(libraryVisitor visitor : libraryVisitors) {
            if(visitor.subscribed)
            {
                libraryVisitorsSubscribed.add(visitor);
            }
        }
        int allBooksAmount = 0;
        for(libraryVisitor visitor : libraryVisitors)
        {
            allBooksAmount += visitor.favoriteBooks.length;
        }
        int medianBooksAmount = (int) round((double) allBooksAmount /libraryVisitors.length);
        System.out.println(medianBooksAmount);
        List<libraryVisitor> AboveAverage = new ArrayList<>();
        List<libraryVisitor> BelowAverage = new ArrayList<>();
        List<libraryVisitor> Average = new ArrayList<>();
        for(libraryVisitor visitor : libraryVisitorsSubscribed) {
            if(visitor.favoriteBooks.length > medianBooksAmount)
            {
                AboveAverage.add(visitor);
            } else if (visitor.favoriteBooks.length == medianBooksAmount) {
                Average.add(visitor);
            } else {
                BelowAverage.add(visitor);
            }
        }
        for(libraryVisitor visitor : AboveAverage)
        {
            System.out.println(visitor.getName() + " you are a bookworm");
        }
        for(libraryVisitor visitor : Average)
        {
            System.out.println(visitor.getName() + " fine");
        }
        for(libraryVisitor visitor : BelowAverage)
        {
            System.out.println(visitor.getName() + " read more");
        }

    }


}
