package mel.AdminTestClasses;

import mel.Helper.AdditionalMethods;
import mel.Helper.SetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class AdminBlogs extends SetDriver{

    private AdditionalMethods methods;

    private By blogId = By.cssSelector("body > div.i-layout__content > div.i-layout__post-list > div:nth-child(2)");
    private By blogsButton = By.xpath("//a[contains(text(), 'Блоги')]");
    private By openAtSiteButton = By.cssSelector(".b-post__button-open-at-site-container");
    public By  blogTitleInSite = By.xpath("//*[@itemprop='headline']");
    private By dropdownMenu = By.cssSelector(".g-dropdown__opener");

    private By postFutureButton = By.cssSelector(".g-dropdown__content > div > div:nth-child(1)");
    public By  postBlockingButton = By.cssSelector(".g-dropdown__content > div > div:nth-child(2)");

    public By  flagAddToFrontPage = By.cssSelector(".b-post__status-icon-container");
    private By postBlockedModalConfirmButton = By.cssSelector(".b-post-blocked-modal__confirm-button");
    public By  iconImgHiddenBlog = By.cssSelector(".b-post__status-icon-container");

    public By  blogTitleInAdmin = By.cssSelector(".b-post__title");
    // publication page
    public By publicationTitle = By.xpath("//h1[1]");
    public By publicationSubtitle = By.xpath("//h1[1]/following-sibling:: div[1]");
    public By publicationTagOnTheCover = By.cssSelector(".cover__main-tag > a > div");

    public By publicationText = By.cssSelector("p");
    public By publicationAuthor = By.xpath("//a[@itemprop='name']");
    public By publicationImage = By.xpath("//div[contains(@class,'article__cover')]");
    // main page
    public By mainPagePublicationTitle = By.cssSelector(".b-ml-cover__title");
    public By mainPageBlogTitle = By.cssSelector(".b-ml-cover__title");
    public By mainPagePublicationSubtitle = By.cssSelector(".b-ml-cover__subtitle");
    public By mainPagePublicationCoverTag = By.cssSelector(".b-ml-cover__content > div > object > a > div");
    public By mainPageBlogCoverTag = By.cssSelector("..b-ml-cover__content > div > object > a > div");
    public By mainPagePublicationImage = By.cssSelector(".b-pb-cover__highlighting");

    public void clickInBlogsButton(){
        $(blogsButton).click();
    }

    public void comprasionTitleBlogs(String firstBlog, String secondBlog){
         if(!(firstBlog.contains(secondBlog))){
            Assert.fail("The blog isn`t displayed on the website");
        }
    }

    public void clickInOpenAtSiteButton(){
        $(openAtSiteButton).click();
    }

    public void clickInDropDownMenu(){
        $(dropdownMenu).click();
    }

    public void clickInPostFutureButton(){
        $(postFutureButton).click();
    }

    public void clickInPostBlockingButton(){
        $(postBlockingButton).click();
        sleep(3000);
        $(postBlockedModalConfirmButton).click();
    }

    public boolean checkFlagAddToFrontPage() {
        try {
            return $(flagAddToFrontPage).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getBlogId(){
        String str = $(blogId).getAttribute("data-params").substring(6,10);
        return str;
    }
}
