package mel.Tests.Admin;

import mel.AdminTestClasses.AdminAddingAuthor;
import mel.AdminTestClasses.AdminLogin;
import mel.Helper.AdditionalMethods;
import mel.Helper.GetUrl;
import mel.Helper.RunTestAgain;
import mel.Helper.SetDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AddingAuthorTest extends SetDriver {

    private AdditionalMethods methods;
    private AdminLogin adminLogin;
    private GetUrl getUrl;
    private AdminAddingAuthor author;


    @Test
    public void addingAuthor() throws InterruptedException {
        methods = new AdditionalMethods();
        getUrl = new GetUrl();
        adminLogin = new AdminLogin();
        author = new AdminAddingAuthor();

        // получение рандомной строки для двух фамилий
        int a = 0;
        int b = 10000;
        int firstrandomNumber = a + (int) (Math.random() * b);
        String firstSurname = "яяяяя" + firstrandomNumber;
        int secondrandomNumber = a + (int) (Math.random() * b);
        String secondSurname = "яяяяя" + secondrandomNumber;
        // запись в строку рандомного email
        String email = methods.generateStr();
        // два имени автора
        String firstNameOfTheAuthor = "firstName";
        String secondNameOfTheAuthor = "secondName";

        String aboutAuthor = "AboutAuthor";

        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation("test@example.com", "123qwe11");
        //попытка создания автора с незаполненным обязательным полем имени
        author.checkAddingAuthorWithIncorrectFields();
        Assert.assertTrue(author.isSaveButtonDisabled());
        // создание автора
        author.addingNewAuthor(firstNameOfTheAuthor, firstSurname, email, aboutAuthor);
        // нажатие на кнопку сортировки по фамилии и имени в обратном порядке
        author.clickInSortArrowButton();
        // запись в строку имени и фамилии автора
        String nameAndSurnameAuthor = firstSurname + firstNameOfTheAuthor;
        // запись в строку второго и третьего автора по сортированному списку
        String secondAuthor = methods.getTextFromSelector(author.secondAuthor);
        String thirdAuthor = methods.getTextFromSelector(author.thirdAuthor);
        // добавление авторов в массив
        String[] authors = {nameAndSurnameAuthor, secondAuthor, thirdAuthor};
        // проверка на правильность сортировки по фамилии и имени
        author.compareAuthorsAfterSort(authors);
        // редактирование автора с изменением имени и фамилии
        author.editAuthor(secondNameOfTheAuthor, secondSurname);
        // сравнение ожидаемого и фактического имени автора
        if (nameAndSurnameAuthor.equals(methods.getTextFromSelector(author.authorNameAndSurname))) {
            Assert.fail("Not editing works of the author");
        }

        String parentWindowId = getWebDriver().getWindowHandle();
        final Set<String> oldWindowsSet = getWebDriver().getWindowHandles();
        methods.Wait(600);
        author.clickInSortArrowButton();
        methods.Wait(600);
        author.clickInOpenInNewPageButton();
        methods.moveFocusToTheNewWindow(oldWindowsSet);
        methods.Wait(600);

        // проверка созданного автора в админке и фактического результата на сайте
        Assert.assertEquals(getWebDriver().getTitle(), secondNameOfTheAuthor + " " + secondSurname + " | Мел");
        Assert.assertEquals(methods.getTextFromSelector(author.authorNameAndSurnameInSite), secondNameOfTheAuthor + " " + secondSurname);
        Assert.assertEquals(methods.getTextFromSelector(author.aboutAuthorInSite), aboutAuthor);
        getWebDriver().switchTo().window(parentWindowId);
        // нажатие на кнопку сортировки по публикациям
        author.clickInsortingPublicationButton();
        // запись количества публикаций для сравнения
        int firstNumberPublication = author.convertSelectorToNumber(author.firstPublicationCount);
        int secondNumberPublication = author.convertSelectorToNumber(author.secondPublicationcount);
        // проверка на сортировку по публикациям
        author.compareTheNumbers(firstNumberPublication, secondNumberPublication);
        // нажатие на кнопку сортировки по подписчикам
        author.clickInsortingSubscribersButton();
        // запись количества подписчиков
        int firstNumberSubscribers = author.convertSelectorToNumber(author.firstSubscribersCount);
        int secondNumberSubscribers = author.convertSelectorToNumber(author.secondSubscribersCount);
        // проверка на сортировку по числу подписчиков
        author.compareTheNumbers(firstNumberSubscribers, secondNumberSubscribers);
        // нахождение и удаление автора
        methods.Wait(500);
        author.clickInSortArrowButton();
        methods.Wait(500);
        author.clickInSortArrowButton();
        methods.Wait(500);
        author.clickIndropdownButton();
        methods.Wait(500);
        author.clickInDeleteButtons();
        methods.Wait(500);
        author.clickInSortArrowButton();
        // проврека на наличие блогера после удаления
        if(methods.getTextFromSelector(author.authorNameAndSurname).equals(secondSurname + secondNameOfTheAuthor)){
            org.testng.Assert.fail("Sorting not working");
        }

        methods.Wait(500);
        author.clickInSortArrowButton();
        methods.Wait(500);
        author.clickIndropdownButton();
        author.clickInDeleteUserButton();
        methods.Wait(500);
        Assert.assertEquals(methods.getTextFromSelector(author.deleteUserWithPublicationMessage), "Нельзя удалить автора, у которого есть хотя бы одна статья");
        author.clickInCloseButtonInDeleteUserWindow();
    }

    @Test
    public void deleteAuthor() {
        author = new AdminAddingAuthor();
        methods = new AdditionalMethods();
        getUrl = new GetUrl();
        adminLogin = new AdminLogin();

        getUrl.driverGetAdminUrl();
        author.clickOnAuthorTab();
        author.clickInsortingPublicationButton();
        methods.Wait(500);
        author.clickInsortingPublicationButton();// для сортировки публикаций авторов у которых 0 публикаций
        methods.Wait(500);
        author.clickOnEditCreatedAuthor();
        author.clickInDeleteUserButton();
        author.clickOnConfirmDeleteButton();
    }
}
