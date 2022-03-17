import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class SearchTests {
    private static final String GITHUB_BASE_URL = "https://github.com/";
    private static final String REPOSITORY = "Latarho/qa-guru-homework-4";

    @Test
    public void listenerTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open(GITHUB_BASE_URL);

        $(".header-search-input").click();
        $(".header-search-input").sendKeys(REPOSITORY);
        $(".header-search-input").submit();

        $(".repo-list").should(text(REPOSITORY));

        $(By.linkText(REPOSITORY)).click();

        $("#issues-tab").should(text("issues"));
    }

    @Test
    public void lambdaTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открыть главную страницу", () -> {
            open(GITHUB_BASE_URL);
        });

        step("Поиск репозитория " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });

        step("Проверка наличия репозитория " + REPOSITORY, () -> {
            $(".repo-list").should(text(REPOSITORY));
        });

        step("Открытие репозитория " + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();
        });

        step("Проверка наличия вкладки issues", () -> {
            $("#issues-tab").should(text("issues"));
        });
    }

    @Test
    public void stepTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.searchRepository(REPOSITORY);
        steps.checkRepository(REPOSITORY);
        steps.openRepository(REPOSITORY);
        steps.checkIssue();
    }
}