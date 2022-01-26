package framework.uiaction;

import framework.constant.FrameworkConstant;
import framework.exception.UIActionException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class UIAction {

    private WebDriver driver;

    public UIAction() {

    }

    public UIAction(WebDriver driver){
        this.driver = driver;

    }

    public String getText(By by) throws UIActionException {
        try{
            String text=null;
            visibilityOfElement(by);
            WebElement element = driver.findElement(by);
            if(element.getText()!=null){
                text = element.getText();
            } else {
                text= element.getAttribute("value");
            }
            return text;
        } catch( Exception e){
            throw new UIActionException(e.getMessage(),e);
        }

    }

    public WebElement findElement(By by) throws UIActionException {
        WebElement element;
        try{
            visibilityOfElement(by);
            element = driver.findElement(by);
        }catch(NoSuchElementException e){
            throw new UIActionException("Element not found ", e);
        }
        catch(Exception e){
            throw new UIActionException(e.getMessage(),e);
        }
        return element;
    }

    public boolean isDisplayed(By by) throws UIActionException {
        boolean status;
        try{
            presenceOfElement(by);
            status = driver.findElement(by).isDisplayed();
        }catch(NoSuchElementException e){
            throw new UIActionException("Element not found ", e);
        }
        catch(Exception e){
            throw new UIActionException(e.getMessage(),e);
        }
        return status;
    }

    public void click(By by) throws UIActionException {

        try{
            findElement(by).click();

            }catch(NoSuchElementException e){
            throw new UIActionException("No such element ", e);
        }
        catch(Exception e){
            throw new UIActionException(e.getMessage(),e);
        }

    }

    public void scrollIntoView(By by) throws UIActionException {

        try{
            JavascriptExecutor js = (JavascriptExecutor)driver;
           js.executeScript("window.scrollBy(0,800)", "");
            Thread.sleep(2000);

        }catch(ElementNotVisibleException e){
            throw new UIActionException("Element is not visible ", e);
        }
        catch(Exception e){
            throw new UIActionException(e.getMessage(),e);
        }

    }

    public boolean isSelected(By by) throws UIActionException {

        try{
           return findElement(by).isSelected();

        }catch(NoSuchElementException e){
            throw new UIActionException("No such element ", e);
        }
        catch(Exception e){
            throw new UIActionException(e.getMessage(),e);
        }

    }

    public String sendKeys(By by,String text) throws UIActionException {
        String getText=null;
        try{
            findElement(by).clear();
            findElement(by).sendKeys(text);
            getText=findElement(by).getAttribute("value");
        }
        catch(Exception e){
            throw new UIActionException(e.getMessage(),e);
        }
        return getText;
    }


    public List<WebElement> findElements(By by) throws UIActionException {
        List<WebElement> elements;
        try{
            visibilityOfElement(by);
            elements = driver.findElements(by);
        }catch(NoSuchElementException e){
            throw new UIActionException("Element not found ", e);
        }
        catch(Exception e){
            throw new UIActionException(e.getMessage(),e);
        }
        return elements;
    }

    public List<String> getListOfString(By by) throws UIActionException {
        List<WebElement> elements;
        List<String> getElement = new ArrayList<>();
        try{
          elements = findElements(by);
            for (WebElement e : elements) {
                if (e.getText() != null) {
                    getElement.add(e.getText());
                }
        }
        } catch(NoSuchElementException e){
            throw new UIActionException("Element not found ", e);
        }
        catch(Exception e){
            throw new UIActionException(e.getMessage(),e);
        }
        return getElement;
    }

//external wait
    public void visibilityOfElement(By by) throws UIActionException {
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstant.LOW_WAIT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch(Exception e){
            throw new UIActionException(e.getMessage(),e);
        }
    }

    public void presenceOfElement(By by) throws UIActionException {
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstant.LOW_WAIT));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch(Exception e){
            throw new UIActionException(e.getMessage(),e);
        }
    }

}
