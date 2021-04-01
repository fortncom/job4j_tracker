package ru.job4j.pojo;

public class Library {

    public static void main(String[] args) {
        Book fahrenheit = new Book("451 градус по Фаренгейту", 176);
        Book dune = new Book("Дюна", 736);
        Book newWorld = new Book("О дивный новый мир", 320);
        Book cleanCode = new Book("Clean code", 556);
        Book[] books = new Book[4];
        books[0] = fahrenheit;
        books[1] = dune;
        books[2] = newWorld;
        books[3] = cleanCode;
        for (int i = 0; i < books.length; i++) {
            Book book = books[i];
            System.out.println(book.getName() + " - "
                    + book.getPages() + " стр.");
        }
        Book tempBook = books[0];
        books[0] = books[3];
        books[3] = tempBook;
        for (int i = 0; i < books.length; i++) {
            Book book = books[i];
            System.out.println(book.getName() + " - "
                    + book.getPages() + " стр.");
        }
    }
}
