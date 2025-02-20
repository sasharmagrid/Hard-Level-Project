package contacts.App;

import contacts.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class MenuTest {

    @Mock
    private PhoneBook phoneBook;
    @Mock
    private Scanner scanner;

    @BeforeEach
    void setUp() {
        phoneBook = mock(PhoneBook.class); // Mock the PhoneBook class
        scanner = mock(Scanner.class); // Mock the Scanner class
    }

    @Test
    public void testAddContact() {
        // Simulate user input: user chooses to add a contact and then exits
        String input = "add\nexit";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);


//        phoneBook.when(()-> phoneBook.addContact(scanner)).thenAnswer(invocation->null);
        doNothing().when(phoneBook).addContact(scanner);


        Menu.start(phoneBook);
    }

    @Test
    public void testListContacts() {
        // Simulate user input: user chooses to list contacts and then exits
        String input = "list\nexit";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);


//        phoneBook.when(()-> phoneBook.addContact(scanner)).thenAnswer(invocation->null);
        doNothing().when(phoneBook).listContacts(scanner);


        Menu.start(phoneBook);
    }

    @Test
    public void testSearchContacts() {
        // Simulate user input: user chooses to search contacts and then exits
        String input = "search\nexit";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);


//        phoneBook.when(()-> phoneBook.addContact(scanner)).thenAnswer(invocation->null);
        doNothing().when(phoneBook).searchContact(scanner);


        Menu.start(phoneBook);
    }

    @Test
    public void testCountContacts() {
        // Simulate user input: user chooses to count the contacts and then exits
        String input = "count\nexit";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Menu.start(phoneBook);
    }

}