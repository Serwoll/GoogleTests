package Search;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleSearchTest {
    private static Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;
    private GooglePage googlePage;


    @BeforeAll
    public static void setupClass() {
        playwright = Playwright.create();

    }

    @BeforeEach
    public void setup() {
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        googlePage = new GooglePage(page);
    }

    @Test
    @DisplayName("Тестирование поиска в Google")
    public void testGoogleMainTab() {
        googlePage.navigate();
        googlePage.inputText("Автотесты");
        googlePage.pressSearchBtn();
        assertTrue(page.url().contains("/search"));
        assertTrue(googlePage.isGoogleLogo());
        assertTrue(googlePage.hasSearchResult());
        assertTrue(googlePage.hasSearchPages());
        assertTrue(googlePage.isClearBtn());
        assertTrue(googlePage.IsCleanSearchText());
    }


}
