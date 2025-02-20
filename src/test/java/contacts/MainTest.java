package contacts;

import contacts.App.Menu;
import contacts.App.PhoneBook;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

class MainTest {

    @Test
    void testStartApplication() {
        PhoneBook mockPhoneBook = mock(PhoneBook.class, Mockito.withSettings().useConstructor().defaultAnswer(Mockito.CALLS_REAL_METHODS));

        try (MockedStatic<Menu> mockedMenu = Mockito.mockStatic(Menu.class)) {

            mockedMenu.when(() -> Menu.start(mockPhoneBook)).thenAnswer(invocation -> null);
            Main.main(new String[]{});
            mockedMenu.verify(() -> Menu.start(any(PhoneBook.class)), times(1));

        }
    }
}