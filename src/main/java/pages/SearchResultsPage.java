package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

/**
 * Este pageobject corresponde a la pagina de resultados de busqueda de vuelo
 */
public class SearchResultsPage extends BasePages {

    /**
     * Constructor de clase e inicializador de elementos (Page factory)
     */
    public SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
    }

    /**
     * Este metodo nos permite seleccionar un paquete y un vuelo tanto de ida como de
     * regreso especifico dentro del paquete seleccionado de igual forma selecciona el boton
     * correspondiente de dicho paquete para adquirirlo y continuar con el proceso
     *
     * @param packageOfFlights
     * @param optionIntoPackageGoing
     * @param optionIntoPackageBack
     * @return
     */
    public ConfirmDataPage selectSpecificResult(String packageOfFlights, String optionIntoPackageGoing, String optionIntoPackageBack) {
        click(By.xpath("//div[@data-index=\"" + packageOfFlights + "\"]//span[@class=\"sub-cluster\"]//li[" + optionIntoPackageGoing + "]//div[@class=\"itinerary-container\"]//i[@class=\"radio-circle\"]"));
        click(By.xpath("//div[@data-index=\"" + packageOfFlights + "\"]//span[@class=\"sub-cluster last\"]//li[" + optionIntoPackageBack + "]//div[@class=\"itinerary-container\"]//i[@class=\"radio-circle\"]"));
        click(By.xpath("//div[@data-index=\"" + packageOfFlights + "\"]/following-sibling::div//em[@class=\"btn-text\"]"));
        return new ConfirmDataPage(getDriver());
    }
}
