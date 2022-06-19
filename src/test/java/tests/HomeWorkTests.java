package tests;

import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.back;
import static io.qameta.allure.Allure.step;

//!!!! Для скипа мешающих аллертов   switchTo().alert().accept();

@Tag("android")
public class HomeWorkTests extends TestBase {
    @DisplayName("Тестирование поиска")
    @Test
    void homeWorkWikiTest() {
        back();
        $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click();
        $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        $(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).shouldHave(text("Appium"));

    }

    @DisplayName("Проверка капчи при заполнении формы регистрации")
    @Test
    void homeWorkWikiCaptchaTest() {
        back();
        step("Перейти к форме регистрации", () -> {
                    $(AppiumBy.xpath("//android.widget.TextView[@text='Edits']")).click();
                    $(AppiumBy.id("org.wikipedia.alpha:id/positiveButton")).click();
                }
        );
        step("Убедиться, что открылась форма Регистрации", () -> {
            $(AppiumBy.xpath("//android.widget.TextView[@text='Create an account']"))
                    .shouldHave(text("Create an account"));
        });

        step("Заполнить форму Регистрации", () -> {
            $(AppiumBy.xpath("//android.widget.EditText[@text='Username']")).click();
            $(AppiumBy.xpath("//android.widget.EditText[@text='Username']"))
                    .sendKeys("QaGuruHello");

            $(AppiumBy.xpath("//android.widget.EditText[@text='Password']")).click();
            $(AppiumBy.xpath("//android.widget.EditText[@text='Password']"))
                    .sendKeys("12345678");

            $(AppiumBy.xpath("//android.widget.EditText[@text='Repeat password']")).click();
            $(AppiumBy.xpath("//android.widget.EditText[@text='Repeat password']"))
                    .sendKeys("12345678");

            $(AppiumBy.xpath("//android.widget.EditText[@text='Email (Optional)']")).click();
            $(AppiumBy.xpath("//android.widget.EditText[@text='Email (Optional)']"))
                    .sendKeys("qaGurur123@google.com");
        });

        step("Нажать на кнопку 'Next'", () -> {
            $(AppiumBy.xpath("//android.widget.Button[@text='NEXT']")).click();
        });
        step("Проверка, что капча и ее отрибуты отображаются корректно", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/request_account_text"))
                    .shouldHave(text("Can't see the image? Request an account"));
            $(AppiumBy.id("org.wikipedia.alpha:id/captcha_image")).shouldBe(Condition.visible);
            $(AppiumBy.id("org.wikipedia.alpha:id/captcha_submit_button")).shouldBe(Condition.visible);
        });

    }


    @Test
    @DisplayName("Проверка онбординга")
    void testGettingStarted(){
        $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                .shouldHave(text("The Free Encyclopedia …in over 300 languages"));
        $(AppiumBy.xpath("//android.widget.LinearLayout[2]")).click();
        $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                .shouldHave(text("New ways to explore"));
        $(AppiumBy.id("org.wikipedia.alpha:id/secondaryTextView"))
                .shouldHave(text("Dive down the Wikipedia rabbit hole with a constantly updating Explore feed. " +
                        "Customize the feed to your interests – whether it’s learning about historical events On this day, " +
                        "or rolling the dice with Random."));
        $(AppiumBy.xpath("//android.widget.LinearLayout[3]")).click();
        $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                .shouldHave(text("Reading lists with sync"));
        $(AppiumBy.xpath("//android.widget.LinearLayout[4]")).click();
        $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                .shouldHave(text("Send anonymous data"));


    }
}
