package Search;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class GooglePage {

    private Page page;

    private final Locator searchInput = page.locator("[name='q']");
    private final Locator searchBtn = page.locator("[name='btnK']");
    private final Locator googleLogo = page.locator("img[alt='Google']");
    private final Locator searchResults = page.locator("a h3");
    private final Locator pageWithResults = page.locator("td a");
    private final Locator clearBtn = page.locator("[aria-label='Очистить']");

    public GooglePage(Page page) {
        this.page = page;
    }

    public void navigate() {
        page.navigate("https://www.google.com");
    }

    public void inputText(String text) {
        searchInput.fill(text);
    }

    public void pressSearchBtn() {
        searchBtn.click();
    }

    public boolean isGoogleLogo() {
        return googleLogo.isVisible();
    }

    public boolean hasSearchResult() {
        return searchResults.count() > 0;
    }

    public boolean hasSearchPages() {
        return pageWithResults.count() != 0;
    }

    public boolean isClearBtn() {
        return clearBtn.isVisible();
    }

    public boolean IsCleanSearchText() {
        clearBtn.click();
        return searchInput.inputValue().isEmpty();
    }


}