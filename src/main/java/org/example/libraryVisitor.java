package org.example;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Getter
@Setter
@Log

public class libraryVisitor {
    String name;
    String surname;
    String phone;
    boolean subscribed;
    book[] favoriteBooks;

    public libraryVisitor(String name, String surname, String phone, boolean subscribed, book[] favoriteBooks) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.subscribed = subscribed;
        this.favoriteBooks = favoriteBooks;
    }

    public void print()
    {
        System.out.println(name);
        System.out.println(surname);
        System.out.println(phone);
        System.out.println(subscribed);
        for(book bookEx : favoriteBooks) {
            bookEx.print();
        }
        System.out.println();
    }
    public void printWithoutBooks()
    {
        System.out.println(name);
        System.out.println(surname);
        System.out.println(phone);
        System.out.println(subscribed);
        System.out.println();
    }

}
