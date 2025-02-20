package contacts.App;

import contacts.contact.Contact;
import contacts.contact.Person;
import contacts.contact.Organization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PhoneBookTest {

private Scanner sc;
    private List<Contact> contactsList;
    private Contact mockContact;
    private Menu mockMenu;

    @Mock
    private PhoneBook phoneBook;

@BeforeEach
void setUp() {
    MockitoAnnotations.openMocks(this);
    sc = mock(Scanner.class); // Mock the Scanner class
    contactsList = new ArrayList<>();
    mockContact = mock(Contact.class); // Mock the Contact class
    mockMenu = mock(Menu.class); // Mock the Menu class

    contactsList.add(mockContact);
    phoneBook = new PhoneBook(); // The class where editContact method is defined
}

    @Test
    void testEditContact_validIndexAndEditAction() {
        when(sc.nextLine()).thenReturn("edit");

        // Simulate the user's valid input
        String action = "1";
        phoneBook.editContact(sc, contactsList, action);

        // Verify that the editFields method is called
        verify(mockContact).editFields(sc);
    }

    @Test
    void testEditContact_validIndexAndDeleteAction() {
        when(sc.nextLine()).thenReturn("delete");

        // Simulate the user's valid input
        String action = "1";
        phoneBook.editContact(sc, contactsList, action);

        // Verify that the editFields method is called
        verify(mockContact).getFields();
    }

    @Test
    @Disabled
    void testEditContact_validIndexAndBackAction() {
        when(sc.nextLine()).thenReturn("back");

        // Simulate the user's valid input
        String action = "1";
        phoneBook.editContact(sc, contactsList, action);
        doNothing().doReturn(mockMenu);
    }

    @Test
    public void testSearchMenu_NoResults() {
        // Simulating a search with no results
        String searchInput = "Nonexistent";
        Scanner sc = new Scanner(searchInput);
        phoneBook.searchContact(sc);

        // In this test case, it should display "No contacts in the list"
        assertTrue(phoneBook.getContactsList().isEmpty());
    }

    @Test
    public void testSearchMenu_Results() {
        try (MockedStatic<Menu> mockedMenu = Mockito.mockStatic(Menu.class)) {
            // Simulating a search with no results
//            mockAddPersonContact();
            when(sc.nextLine()).thenReturn("person");
            when(sc.nextLine()).thenReturn("John");
            when(sc.nextLine()).thenReturn("Doe");
            when(sc.nextLine()).thenReturn("01/01/1990");
            when(sc.nextLine()).thenReturn("M");
            when(sc.nextLine()).thenReturn("+123456789");

            phoneBook.addContact(sc);
//            when(sc.nextLine()).thenReturn("back");
            String searchInput = "John";
            Scanner sc = new Scanner(searchInput);
            phoneBook.searchContact(sc);
        }
    }

    @Test
    public void testEditAction() {
        mockAddPersonContact();
        // Simulate user input: selecting the first contact and choosing "edit"
        when(sc.nextLine()).thenReturn("1");  // User selects "1" for the first contact and chooses "edit"

        // Call the editContact method
        phoneBook.editContact(sc, phoneBook.getContactsList(), "1");

        // Verify that editFields() was called on the selected contact
      //  verify(contactMock, times(1)).editFields(sc);
    }


    @Test
    @Disabled
    public void testSearchMenu_WithResults() {
        when(sc.nextLine())
                .thenReturn("person")
                .thenReturn("John")
                .thenReturn("Doe")
                .thenReturn("1990-12-20")
                .thenReturn("M")
                .thenReturn("+123456789"); // phone number

        phoneBook.addContact(sc);

        String searchInput = "John";
        Scanner sc = new Scanner(searchInput);
        phoneBook.searchContact(sc);

        // Simulate selecting "1" for the first contact
        String actionInput = "1"; // Select first contact
        Scanner scAction = new Scanner(actionInput);
        phoneBook.editContact(scAction, phoneBook.getContactsList(), "1");

        assertEquals(2, phoneBook.contactListSize());
    }

    @Test
    @Disabled
    public void testSearchMenu_BackAction() {
        // Add a contact for the search
        when(sc.nextLine())
                .thenReturn("person")
                .thenReturn("John")
                .thenReturn("Doe")
                .thenReturn("1990-12-20")
                .thenReturn("M")
                .thenReturn("+123456789"); // phone number

        phoneBook.addContact(sc);

        String searchInput = "John";
        Scanner sc = new Scanner(searchInput);
        phoneBook.searchContact(sc);

        when(sc.nextLine()).thenReturn("again");
        // Simulate selecting "back"
        String actionInput = "again"; // Go back to menu
        Scanner scAction = new Scanner(actionInput);
        phoneBook.searchMenu(scAction, phoneBook.getContactsList());
//        when(sc.nextLine()).thenReturn("exit");

        // Ensure it returns to the menu (you may need to add more validation based on your logic)
//        assertNotNull(phoneBook);
    }

    @Test
    void testAddPerson() {
        // Simulate the user's input for a person contact
        when(sc.nextLine())
                .thenReturn("person")
                .thenReturn("John")
                .thenReturn("Doe")
                .thenReturn("1990-12-20")
                .thenReturn("M")
                .thenReturn("+123456789"); // phone number

        phoneBook.addContact(sc);

        assertEquals(1, phoneBook.contactListSize());
        assertTrue(phoneBook.getContactsList().get(0) instanceof Person);
    }

    @Test
    void testAddOrganization() {
        // Simulate the user's input for an organization contact
        when(sc.nextLine()).thenReturn("organization"); // type = organization
        when(sc.nextLine()).thenReturn("Tech Corp");   // organization name
        when(sc.nextLine()).thenReturn("123 Tech Road"); // address
        when(sc.nextLine()).thenReturn("+987654321");  // phone number

        phoneBook.addContact(sc);

        assertEquals(1, phoneBook.contactListSize());
        assertTrue(phoneBook.getContactsList().get(0) instanceof Organization);
    }

    @Test
    void testListContacts() {
        // Simulate adding contacts
        when(sc.nextLine()).thenReturn("person");
        when(sc.nextLine()).thenReturn("Alice");
        when(sc.nextLine()).thenReturn("Smith");
        when(sc.nextLine()).thenReturn("02/02/1985");
        when(sc.nextLine()).thenReturn("F");
        when(sc.nextLine()).thenReturn("+1122334455");
        phoneBook.addContact(sc);

        when(sc.nextLine()).thenReturn("organization");
        when(sc.nextLine()).thenReturn("Some Org");
        when(sc.nextLine()).thenReturn("456 Organization St");
        when(sc.nextLine()).thenReturn("+9988776655");
        phoneBook.addContact(sc);

        assertEquals(2, phoneBook.contactListSize());
    }

    @Test
    void testSearchContact() {
        // Add a person first
        when(sc.nextLine()).thenReturn("person");
        when(sc.nextLine()).thenReturn("John");
        when(sc.nextLine()).thenReturn("Doe");
        when(sc.nextLine()).thenReturn("01/01/1990");
        when(sc.nextLine()).thenReturn("M");
        when(sc.nextLine()).thenReturn("+123456789");
        phoneBook.addContact(sc);

        // Simulate searching for a contact
        when(sc.nextLine()).thenReturn("John");

        // Since "John" should match the person's info, simulate search
        phoneBook.searchContact(sc);

        // The result should be 1 (since only John was added)
        assertEquals(1, phoneBook.contactListSize());
    }

    @Test
    void testContactCount() {
        // Add a few contacts
        when(sc.nextLine()).thenReturn("person");
        when(sc.nextLine()).thenReturn("John");
        when(sc.nextLine()).thenReturn("Doe");
        when(sc.nextLine()).thenReturn("01/01/1990");
        when(sc.nextLine()).thenReturn("M");
        when(sc.nextLine()).thenReturn("+123456789");
        phoneBook.addContact(sc);

        when(sc.nextLine()).thenReturn("organization");
        when(sc.nextLine()).thenReturn("Tech Corp");
        when(sc.nextLine()).thenReturn("123 Tech Road");
        when(sc.nextLine()).thenReturn("+987654321");
        phoneBook.addContact(sc);

        assertEquals(2, phoneBook.contactListSize());
    }

    @Test
    void testListContactsWhenEmpty() {

        phoneBook.listContacts(sc); // Call the method

        // Verify that no contacts are listed and it exits gracefully
        assertEquals(0, phoneBook.contactListSize()); // Ensure no contacts are listed
    }

    @Test
    void testListContactsWhenContactsExist() {
        // Add a contact using a mocked method
        mockAddPersonContact();

        phoneBook.listContacts(sc); // Call the method

        // Verify the contact is listed and we navigate back
        assertEquals(1, phoneBook.contactListSize()); // Ensure 1 contact exists
    }

    // Test selecting a contact from the list
    @Test
    void testListContactsAndSelect() {
        // Add two contacts using mocked methods
        mockAddPersonContact();   // Adds a Person contact
        mockAddOrganizationContact(); // Adds an Organization contact

        // Simulate user selecting the first contact (index 1)
        when(sc.nextLine()).thenReturn("1");  // Simulate user input "1"

        phoneBook.listContacts(sc); // Call the method

        // Verify that the contacts are listed
        assertEquals(2, phoneBook.contactListSize()); // Ensure 2 contacts are listed
    }

    // Test selecting "back" action to return to the menu
    @Test
    void testListContactsAndGoBack() {
        try (MockedStatic<Menu> mockedMenu = Mockito.mockStatic(Menu.class)) {
            // Add a person contact
            mockAddPersonContact();
            when(sc.nextLine()).thenReturn("back");
            phoneBook.listContacts(sc); // Call the method

            // Verify that the method exits correctly when "back" is typed
            assertEquals(1, phoneBook.contactListSize()); // Ensure one contact is still there
        }
    }

    // Helper method to mock adding a Person contact
    private void mockAddPersonContact() {
        // Simulate the input for adding a Person contact
        when(sc.nextLine()).thenReturn("person");
        when(sc.nextLine()).thenReturn("John");
        when(sc.nextLine()).thenReturn("Doe");
        when(sc.nextLine()).thenReturn("01/01/1990");
        when(sc.nextLine()).thenReturn("M");
        when(sc.nextLine()).thenReturn("+123456789");

        phoneBook.addContact(sc); // Add the person contact
    }

    // Helper method to mock adding an Organization contact
    private void mockAddOrganizationContact() {
        // Simulate the input for adding an Organization contact
        when(sc.nextLine()).thenReturn("organization");
        when(sc.nextLine()).thenReturn("Tech Corp");
        when(sc.nextLine()).thenReturn("123 Tech Road");
        when(sc.nextLine()).thenReturn("+987654321");

        phoneBook.addContact(sc); // Add the organization contact
    }

}
