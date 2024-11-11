package Search;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.options.LoadState.DOMCONTENTLOADED;

public class GooglePage {

    private final Page page;
    private final Locator searchInput;
    private final Locator searchBtn;
    private final Locator googleLogo;
    private final Locator searchResults;
    private final Locator pageWithResults;
    private final Locator clearBtn;


    public GooglePage(Page page) {
        this.page = page;
        this.searchInput = page.locator("[name='q']");
        this.searchBtn = page.locator("[name='btnK']").first();
        this.googleLogo = page.locator("[id='logo']>svg");
        this.searchResults = page.locator("a h3");
        this.pageWithResults = page.locator("td a");
        this.clearBtn = page.locator("[aria-label='Очистить']");
    }

    public void navigate() {
        page.navigate("https://www.google.com");
    }

    public void inputText(String text) {
        searchInput.fill(text);
    }

    public void pressSearchBtn() {
        searchBtn.click();
        page.waitForLoadState(DOMCONTENTLOADED);

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

    public boolean isCleanSearchText() {
        clearBtn.click();
        return clearBtn.isHidden();
    }


}