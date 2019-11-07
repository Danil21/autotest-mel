package mel.AdminTestClasses;

import com.codeborne.selenide.Condition;
import mel.Helper.AdditionalMethods;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.util.ArrayList;
import java.util.Set;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AdminAddingTestContent extends SetDriver {

    private AdditionalMethods methods;


    private By addBlockDropdown = By.xpath("//div[text()='Добавить блок']");
    private By testInDropdown = By.xpath("//div[@class='g-dropdown__content']//span[text()='Тест']");
    private By firstQuestionInput = By.xpath("//textarea[@placeholder='Текст вопроса']");
    private By answerOneInput = By.xpath("//textarea[@placeholder='Вариант ответа']");
    private By descriptionOneInput = By.xpath("//textarea[@placeholder='Пояснение показывается при выборе варианта']");
    private By answerOneWeightInput = By.xpath("//div[contains(@class,'b-question_admin') and descendant::div[@class='b-question__index-number' and text()='1']]/descendant::input[contains(@class,'input') and parent::div[contains(@class,'weight-input')]]");
    private By addImageFirstQuestion = By.xpath("//div[text()='Добавить картинку']");

    private By answerTwoWeightInput = By.xpath("//div[contains(@class,'b-answer_admin') and descendant::div[@class='b-answer__index-number' and text()='2']]/descendant::input[contains(@class,'input') and parent::div[contains(@class,'weight-input')]]");
    private By addQuestionButton = By.xpath("//div[@class='g-button__content-container' and text()='Добавить вопрос']");
    private By answerTwoInput = By.xpath("//div[contains(@class,'b-answer_admin') and descendant::div[@class='b-answer__index-number' and text()='2']]/descendant::textarea[@placeholder='Вариант ответа']");

    private By addAnswerButton = By.cssSelector(".b-question__answer-creation-button > div");
    private By secondQuestionInput = By.xpath("//div[2]/div[1]/div[2]/textarea[@placeholder='Текст вопроса']");
    private By secondAnswerInput = By.xpath("//div[contains(@class,'b-question_admin') and descendant::div[@class='b-question__index-number' and text()='2']]/descendant::textarea[@placeholder='Вариант ответа']");
    private By secondAnswerWeightInput = By.xpath("//div[contains(@class,'b-question_admin') and descendant::div[@class='b-question__index-number' and text()='2']]/descendant::input[contains(@class,'input') and parent::div[contains(@class,'weight-input')]]");

    private By showDescriptionCheck = By.xpath("//*[text()='Показывать пояснения к ответам']");
    private By showRightAnswerCheck = By.xpath("//*[text()='Показывать правильный ответ']");

    private By firstMinResultInput = By.xpath("//div[contains(@class,'result__min-score')]/descendant::input");
    private By firstMaxResultInput = By.xpath("//div[contains(@class,'result__max-score')]/descendant::input");
    private By firstResultAddImage = By.xpath("//div[contains(@class,'b-result_admin')]/descendant::div[text()='Добавить картинку']");

    private By addResultButton = By.xpath("//div[3]/div/div[2]/div/div[3]/div[2]/div/div[2]");
    private By secondMinResultInput = By.xpath("//div[3]/div[1]/div[2]/div[2]/div[2]/div/input");
    private By secondMaxResultInput = By.xpath("//div[3]/div[1]/div[2]/div[2]/div[4]/div/input");
    private By showSecondResultDescription = By.xpath("//div[2]/div[6]/label");
    private By secondResultDescriptionInput = By.xpath("//div[2]/div[7]/div/div[2]/div/textarea");

    private By answerOneCheck = By.xpath("//*[text()='AnswerOne']");
    private By answerTwoCheck = By.xpath("//*[text()='AnswerTwo']");
    private By answerOneDescription = By.xpath("//*[text()='DescriptionOne']");
    private By nextQuestionButton = By.xpath("//*[contains(text(),'родолжить')]");
    private By secondAnswerCheck = By.xpath("//*[text()='secondAnswer']");
    private By firstResultTotalScore = By.xpath("//*[text()='Баллов набрано: 1 из 2']");
    private By firstResultImage = By.xpath("//*[@alt='result-image']");
    private By secondResultTotalScore = By.xpath("//*[text()='Баллов набрано: 2 из 2']");
    private By secondResultDescription = By.xpath("//*[text()='Description']");
    private By repeatTestButton = By.xpath("//*[contains(text(),'ройти тест ещё раз')]");
    private By openInAdminButton = By.xpath("//*[text()='Открыть в админке']");
    private By closeAuthorPopup = By.xpath("//*[@data-params='{\"type\":\"coach-mark-close\"}']");
    private By deleteTestBlockButton = By.xpath("//div[contains(@class,'command-panel') and descendant::div[contains(text(),'Тест')]]/descendant::div[contains(@data-params,'close')]");
    private By confirmDeleteTestBlock = By.xpath("//div[contains(@class,'item-modal')and not(contains(@class,'hidden'))]/descendant::div[text()='Удалить']");

    private By linkToTheCreatedTest = By.xpath("//div[text() ='Сегодня']/following-sibling::a[2]");

    public void addNewTest(String firstQuestion, String firstAnswerOne, String answerOneDescription, String answerOneWeight, String firstAnswerTwo,
                           String answerTwoWeight, String secondQuestion, String secondAnswer, String secondWeight,
                           String firstMinResult, String firstMaxResult, String secondMinResult, String secondMaxResult, String secondResultDescription) {

        methods = new AdditionalMethods();

        methods.scroll("1200");
        $(addBlockDropdown).scrollTo().click();
        $(testInDropdown).click();

        //Вопрос №1 (с картинкой) с вариантом ответа №1(с пояснением) и вариантом ответа №2
        $(firstQuestionInput).sendKeys(firstQuestion);
        $(answerOneInput).sendKeys(firstAnswerOne);
        $(descriptionOneInput).sendKeys(answerOneDescription);
        $(answerOneWeightInput).sendKeys(answerOneWeight);
        $(addImageFirstQuestion).click();
        methods.imageDownload("C:\\1.jpg");
        $(showDescriptionCheck).click(); //Чек "Показывать пояснения к ответам"
        $(showRightAnswerCheck).click(); //Чек "Показывать правильные ответы"
        $(addAnswerButton).click();
        $(answerTwoInput).sendKeys(firstAnswerTwo);
        $(answerTwoWeightInput).sendKeys(answerTwoWeight);
        methods.scroll("1500");
        $(addQuestionButton).click();

        //Вопрос №2 c одним вариантом ответа
        $(secondQuestionInput).sendKeys(secondQuestion);
        $(secondAnswerInput).sendKeys(secondAnswer);
        $(secondAnswerWeightInput).sendKeys(secondWeight);
        methods.scroll("1800");

        //Результат теста №1 (с картинкой)
        $(firstMinResultInput).sendKeys(firstMinResult);
        $(firstMaxResultInput).sendKeys(firstMaxResult);
        methods.scroll("2100");
        $(firstResultAddImage).click();
        methods.imageDownload("C:\\1.jpg");
        methods.scroll("2200");
        $(addResultButton).click();

        //Результат теста №2 (с описанием результата)
        $(secondMinResultInput).sendKeys(secondMinResult);
        $(secondMaxResultInput).sendKeys(secondMaxResult);
        methods.scroll("2500");
        $(showSecondResultDescription).click();
        $(secondResultDescriptionInput).sendKeys(secondResultDescription);
    }

    //Проверка созданного теста на сайте
    public void checkNewTest() {
        methods = new AdditionalMethods();

        $(linkToTheCreatedTest).scrollTo().click();
        ArrayList<String> tabs2 = new ArrayList<String>(getWebDriver().getWindowHandles());
        getWebDriver().switchTo().window(tabs2.get(1));

        //Проверяем получение результата теста №2(оба правильных ответа)
        //Первый вопрос теста
        methods.scroll("1200");

        if (getDisplayedAuthorPopup()) {
            $(closeAuthorPopup).click();
        }
        sleep(1000);
        $(answerOneCheck).click();
        $(answerOneDescription).shouldHave(Condition.visible);
        $(nextQuestionButton).click();
        //Второй вопрос теста
        $(secondAnswerCheck).click();
        $(nextQuestionButton).click();
        //Проверяем отображение результата №2
        $(secondResultTotalScore).shouldHave(Condition.visible);
        $(secondResultDescription).shouldHave(Condition.visible);
        $(repeatTestButton).click();
        //Проверяем получение результата теста №1(только один правильный ответ)
        //Первый вопрос теста
        $(answerTwoCheck).click();
        $(nextQuestionButton).click();
        //Второй вопрос теста
        $(secondAnswerCheck).click();
        $(nextQuestionButton).click();
        //Проверяем отображение результата №1
        $(firstResultTotalScore);
        $(firstResultImage);

    }

    private boolean getDisplayedAuthorPopup() {
        try {
            return $(closeAuthorPopup).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickOnOpenInAdmin() {
        $(openInAdminButton).click();
    }

    public void deleteTestAndOpenInAdminka() {
        methods = new AdditionalMethods();
        methods.closeNotificationCookie();

        final Set<String> oldWindowsSet = getWebDriver().getWindowHandles();
        methods.scroll("1300");
        methods.closeNotificationCookie();
        $(openInAdminButton).click();
        methods.moveFocusToTheNewWindow(oldWindowsSet);
        methods.scroll("700");
        $(deleteTestBlockButton).shouldBe(Condition.visible).click();
        sleep(2000);
        $(confirmDeleteTestBlock).click();
    }

}
