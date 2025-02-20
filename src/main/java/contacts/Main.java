package contacts;

import contacts.App.Menu;
import contacts.App.PhoneBook;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Menu.start(phoneBook);
    }
}
