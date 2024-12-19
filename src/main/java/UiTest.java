import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class UiTest {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Старт тестирования");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Старт выполнения тест-кейса");
    }


    @Test
    @DisplayName("Тест-кейс: Создание рассылки")
    public void testMailing(){
        String url = "https://preprod.otzyvypro.io/?jwt_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2VtYWlsIjoidGVzdF9zdGdfMkByb2NrZXRkYXRhLmlvIiwib3JnX2lkIjoiNzAwMDAwMDEwODAwNzQ4OTIiLCJvcmdfbmFtZSI6ItCo0LrQvtC70LAg0YLQsNC50YHQutC-0LPQviDQsdC-0LrRgdCwINC40LwuINCT0LXQvdC90LDQtNC40Y8g0KXQsNGB0LDQvdC-0LLQsCJ9.9vgITUe93cvq0-pv1bYHzOv-YtMo6mK7UC0YuE8wI7E";
        String phoneNumber = "9221593387";
        var expectedText = "Запрос отзыва Viber";

        Selenide.open(url);
        $x("//*[@class='b50b5c a1975e']").click();
        $x("//*[@value='ffb3ec72-a86f-421f-86c6-f49a01fc00e9']").click();
        $x("//button[contains(text(), 'Продолжить')]").click();

        $x("//button/span[contains(text(), 'Добавить вручную')]").click();

        $x("//input[@class='ant-input css-1redeos']").sendKeys(phoneNumber);
        $x("//input[@class='ant-input css-1redeos']").pressTab();
        $x("//button[@class='ant-btn css-1redeos ant-btn-primary']").click();


        var txtOnThePage = $x("//div[@class='b6d64c']/span[@class='ant-typography ae36d8 e636aa css-1redeos']").getText();

        System.out.println(txtOnThePage); //здесь определяю, что за текст выбрала в хpath

        if (expectedText.equals(txtOnThePage)) {
            System.out.println("Тест пройден верно");
        } else {
            System.out.println("Тест не пройден");
        }

        System.out.println("");

    }






    @Disabled
    @Test // можно прописать @Disabled для скипа теста
    @DisplayName("Сборка всех заголовков")
    public void test(){
        Selenide.open("https://www.google.com");
        $x("//*[@name='q']").sendKeys("МСБ");
        $x("//*[@name='q']").pressEnter();

        $x("//h3").shouldBe(Condition.visible);

        System.out.println("Найдены все заголовки:");

        /*
        var titles = $$x("//h3[@class='LC20lb MBeuO DKV0Md']");
        for (int i = 0; i < titles.size(); i++) {
            System.out.println(i + " = " + titles.get(i).getText());
        }
        System.out.println("");
       */


        List<String> titles = $$x("//h3[@class='LC20lb MBeuO DKV0Md']").texts();
        for (int i = 0; i < titles.size(); i++) {
            System.out.println(i + " = " + titles.get(i));
        }

        System.out.println("");

    }

        @AfterEach
        public void theEnd(){
            System.out.println("Конец теста");
        }

}
