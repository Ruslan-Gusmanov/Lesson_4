import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SoftAssertions {

        @BeforeAll
        static void beforeAllSetup() {
            Configuration.browserSize = "1920x1080";
            Configuration.baseUrl = "https://github.com";
            Configuration.pageLoadStrategy = "eager";
            Configuration.timeout = 4000;
        }


        @Test
        void GithubSelenideTest() {
            open("/selenide/selenide");
            $("#wiki-tab").click();
            $("#wiki-pages-filter").setValue("SoftAssertions");
            $(".filterable-active").shouldHave(text("SoftAssertions"));
            $(".filterable-active").$(byText("SoftAssertions")).click();
            $("#wiki-body").shouldHave(text("@ExtendWith({SoftAssertsExtension.class})\n" +
                    "class Tests {\n" +
                    "  @Test\n" +
                    "  void test() {\n" +
                    "    Configuration.assertionMode = SOFT;\n" +
                    "    open(\"page.html\");\n" +
                    "\n" +
                    "    $(\"#first\").should(visible).click();\n" +
                    "    $(\"#second\").should(visible).click();\n" +
                    "  }\n" +
                    "}"));

        }

}
