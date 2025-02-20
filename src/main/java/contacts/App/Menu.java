package contacts.App;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Menu {

    public static void start(PhoneBook phoneBook) {
        Scanner sc = new Scanner(System.in , StandardCharsets.UTF_8);
        while (true) {
            System.out.print("\n[menu] Enter action (add, list, search, count, exit): ");
            String action = sc.nextLine();

            switch (action) {
                case "add" -> phoneBook.addContact(sc);
                case "list" -> phoneBook.listContacts(sc);
                case "search" -> phoneBook.searchContact(sc);
                case "count" -> System.out.println("The Phone book has " + phoneBook.contactListSize() + " records");
                case "exit" -> {
                    sc.close();
                    return;
                }
            }
        }
    }
}


