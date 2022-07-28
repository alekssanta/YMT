package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;

public class UISteps {

    @Когда("^открыт сайт \"([^\"]*)\"$")
    public void openUrl(String url) {
        Configuration.startMaximized = true;
        open(url);
    }

    @И("вводим в поле \"([^\"]*)\" значение \"([^\"]*)\"$")
    public void inputText(String name, String value) {
        WebDriverRunner.getWebDriver().findElement(By.id(name)).sendKeys(value);
    }

    @Если("на сайте появился текст \"([^\"]*)\", нажимаем на кнопку \"([^\"]*)\"$")
    public void ifSystemDetectedBot(String name, String checkBox) {
WebDriverRunner.getWebDriver().findElement(By.className(checkBox)).click();
    }

    @И("^ждем \"([^\"]*)\" миллисекунд$")
    public void threadSleep(String time) {
        try {
            Thread.sleep(Long.parseLong(time));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @И("^нажимаем на кнопку \"([^\"]*)\"$")
    public void clickButton(String btn) {
        WebDriverRunner.getWebDriver().findElement(By.xpath("//span[contains(text(),'"+btn+"')]/..")).click();

    }

    @И("^выбираем среди результатов пункт \"([^\"]*)\" и добавляем в корзину$")
    public void chooseSelectedItem(String number) {
        WebDriverRunner.getWebDriver().findElement(By.xpath("//*[@data-index='"+number+"']//span[contains(text(),'корзину')]")).click();
    }


    @И("^переходим в корзину$")
    public void moveToCart() {
        WebDriverRunner.getWebDriver().findElement(By.xpath("//*[@title='Корзина']/..")).click();

    }

    @И("^проверяем, что в корзине количество товара равно \"([^\"]*)\"$")
    public void checkCountItem(String count) {

    }

    @И("^нажимаем на пункте \"([^\"]*)\" на поле \"([^\"]*)\"$")
    public void pushField(String number, String field) {
        String fieldValue = null;
        switch (field){
            case ("В избранное"):
                fieldValue = "wishlist-button";
            break;
        }
        WebDriverRunner.getWebDriver().findElement(By.xpath("//*[@data-index='"+number+"']//*[@data-auto='"+fieldValue+"']")).click();

    }

    @И("^проверяем, что в избранном количество товара равно \"([^\"]*)\"$")
    public void checkCountOfWishList(String number) {
        WebDriverRunner.getWebDriver().findElement(By.linkText("в списке "+number+" товар"));
    }

    @И("^удаляем товар из избранного$")
    public void deleteWishItem() {
        WebDriverRunner.getWebDriver().findElement(By.xpath("//*[@data-auto='wishlist-button']")).click();

    }

    @И("^добавляем товар \"([^\"]*)\"$")
    public void addTo(String text) {
        WebDriverRunner.getWebDriver().findElement(By.linkText(text)).click();

    }

    @И("обновить страницу")
    public void refresh() {
        refresh();
    }

    @И("^проверить, что элементов в \"([^\"]*)\" отображается \"([^\"]*)\" штук$")
    public void checkCountElements(String element, Integer count) throws Throwable {
        try {
            WebDriverRunner.getWebDriver().findElements(By.xpath("//h1[contains(text(),'" + element + "')]/..//article"));
        } catch (Exception e) {
            throw new Exception("Количество продуктов не равно заданному");
        }
    }

    @И("^выбираем в корзине пункт \"([^\"]*)\"$")
    public void deleteCartItem(String btn) {
        WebDriverRunner.getWebDriver().findElement(By.xpath("//div[@data-auto='CartItem']//span[contains(text(),'"+btn+"')]")).click();

    }
}
