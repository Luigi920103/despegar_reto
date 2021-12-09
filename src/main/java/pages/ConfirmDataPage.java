package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

/**
 * Pagina en la cual se realizaran las validaciones correspondientes de los valores seleccionados para el vuelo
 * programado
 */
public class ConfirmDataPage extends BasePages {

    /**
     * Labels textos y campos par ala validacion de elementos dentro de esta pagina
     */
    @FindBy(css = "#checkout-content h2")
    private WebElement CompleteYourDataMessage;

    /**
     * Constructor de clase e inicializador de elementos (Page factory)
     */
    public ConfirmDataPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
    }

    /**
     * @return
     */
    public String getCompleteYourDataMessage() {
        return CompleteYourDataMessage.getText();
    }
}
