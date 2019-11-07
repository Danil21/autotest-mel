package mel.TestClasses;

import com.codeborne.selenide.Condition;
import mel.Helper.AdditionalMethods;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class SubscribeNewsLetter extends SetDriver {

    private By inputEmailMain = By.xpath("//input[@type='email']");
    private By errorInSubscribeInput = By.xpath("//*[@class='b-newsletter-signup-form__email-input']//div[@class='g-input__hint']");
    private By linkPageBook = By.xpath("//a[contains(text(),'Книги')]");
    private By inputEmailInPageBook = By.xpath("//input[contains(@placeholder,'Введите свой e-mail')]");
    private By greenAirplane = By.xpath("//div[@class ='g-input__icon-right']");
    private By textErrorSubscribe = By.xpath("//div[contains(text(),'e-mail')]");

    private By greenAirplaneUnderPublication = By.xpath("//*[@id=\"root\"]/div/div[5]/div/div/div[2]/article/div[2]/div/div[1]/div[3]/div[2]/div[2]/div[1]");
    private By inputEmailUnderPublication = By.xpath("//*[@id=\"root\"]/div/div[5]/div/div/div[2]/article/div[2]/div/div[1]/div[3]/div[2]/div[2]/form/input");
    private By closeFormSubscribe = By.xpath("//div[contains(text(),'Закрыть')]");

    private By inputEmailUnderBlog = By.xpath("/html/body/div[1]/div[4]/div/div[4]/div/div[2]/div[1]/div[3]/div[3]/div/div[2]/form/input");
    private By greenAirplaneUnderBlog = By.xpath("/html/body/div[1]/div[4]/div/div[4]/div/div[2]/div[1]/div[3]/div[3]/div/div[2]/div");

    private By subscriptionMessage = By.xpath("//div[contains(@class,'newsletter-form__title_success')]");
    private By subscriptionMessageMain = By.xpath("//*[contains(@class,'i-layout__subscribe')]");
    //попап на рассылкку
    private By subscribeGreenAirplane = By.xpath("//*[contains(@class,'-icon_pablo-mel g-icon_img_green-plane')]");
    private By subscriInputEmail = By.xpath("//*[contains(@class,'newsletter-form__email-input')]//input");
    private By subscriButtoneClose = By.xpath("//div[contains(text(),'Закрыть')]");
    private By subscribeMessageError = By.xpath("//div[contains(text(),'Неверный формат e-mail') and @class='newsletter-form__email-input-hint']");
    private By subscribeMessage = By.xpath("//div[contains(@class,'b-newsletter-popup__title')]");
    //рассылка еге
    private By subscribeEgEAirplane = By.xpath("//div[contains(@class,'newsletter-form__email-input__icon-right')]");



    public boolean getDisplayedErrorInSubscribeInput() {
        AdditionalMethods methods = new AdditionalMethods();
        return methods.getDisplayedElement(errorInSubscribeInput);
    }

    public void checkSubcribeInPageBook(String email) {
        $(linkPageBook).scrollTo().click();
        $(inputEmailInPageBook).click();
        $(greenAirplane).click();
        $(textErrorSubscribe).shouldBe(Condition.visible);
        $(inputEmailInPageBook).sendKeys(email);
        $(inputEmailInPageBook).sendKeys(Keys.ENTER);
    }

    public void subscribe(String email) {
        $(inputEmailMain).click();
        $(inputEmailMain).sendKeys(email);
        $(inputEmailMain).sendKeys(Keys.ENTER);
    }

    public void checkSubscribeUnderPublication(String email) {
        $(inputEmailUnderPublication).scrollIntoView(false).click();
        $(greenAirplaneUnderPublication).click();
        $(textErrorSubscribe).shouldBe(Condition.visible);
        $(inputEmailUnderPublication).sendKeys(email);
        $(inputEmailUnderPublication).sendKeys(Keys.ENTER);
        $(subscriptionMessage).shouldBe(Condition.text("Ура, вы только что подписались!\nНе пропустите наши новые письма – проверяйте почту"));
        $(closeFormSubscribe).click();
        $(subscriptionMessage).shouldNotBe(Condition.visible);

    }

    public void checkSubscribeUnderBlog(String email) {
        $(inputEmailUnderBlog).scrollIntoView(false).click();
        $(greenAirplaneUnderBlog).click();
        //TODO как починят нужно поменять локатор для сообщения об ошибке
        $(textErrorSubscribe).shouldBe(Condition.visible);
        $(inputEmailUnderBlog).sendKeys(email);
        $(inputEmailUnderBlog).sendKeys(Keys.ENTER);
    }

    public void checkPopupSubscribe(String email) {
        boolean airplane = $(subscribeGreenAirplane).isDisplayed();
        if (!airplane) {
            $(subscribeEgEAirplane).click();
        } else {
            $(subscribeGreenAirplane).click();
        }
        $(subscribeMessageError).shouldBe(Condition.visible);
        $(subscriInputEmail).sendKeys(email);
        $(subscriInputEmail).sendKeys(Keys.ENTER);
        $(subscribeMessage).shouldBe(Condition.text("Ура, вы только что подписались!"));
        $(subscriButtoneClose).click();
        $(subscribeMessage).shouldBe(Condition.not(Condition.visible));
    }
}