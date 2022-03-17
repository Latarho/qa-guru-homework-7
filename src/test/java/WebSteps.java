import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebSteps {
    private static final String GITHUB_BASE_URL = "https://github.com/";

    @Step("Открыть главную страницу")
    public void openMainPage() {
        open(GITHUB_BASE_URL);
    }

    @Step("Поиск репозитория {repository}")
    public void searchRepository(String repository) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repository);
        $(".header-search-input").submit();
    }

    @Step("Проверка наличия репозитория {repository}")
    public void checkRepository(String repository) {
        $(".repo-list").should(text(repository));
    }

    @Step("Открытие репозитория {repository}")
    public void openRepository(String repository) {
        $(By.linkText(repository)).click();
    }

    @Step("Проверка наличия вкладки issues")
    public void checkIssue() {
        $("#issues-tab").should(text("issues"));
    }
}