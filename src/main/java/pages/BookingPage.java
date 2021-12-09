package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Calendar;
import java.util.List;

/**
 * Pagina inicial de seleccion de vuelo en la cual se podran seleccionar los diferentes parametros con los que
 * se configurara nuestro vuelo como numero de personas a viajar lugares de destino y partida etc
 */
public class BookingPage extends BasePages {

    /**
     * Buttons
     */
    @FindBy(css = ".FLIGHTS div:nth-child(1)")
    private WebElement flightButton;

    @FindBy(css = "#component-modals a  em")
    private WebElement applyTravelersConfiguration;

    @FindBy(css = ".sbox5-3-select select:nth-child(1)")
    private WebElement dropboxTypeOfFlight;

    @FindBy(xpath = "//input[@id=\"rt-sbox5\"]//following::i[1]")
    private WebElement roundTripCheckBox;

    @FindBy(css = "div.sbox5-distribution-passengers--dbiHH:nth-child(5)  input")
    private WebElement selectTravelersButton;

    @FindBy(xpath = "//input[@tabindex=\"-1\"]//following::button[2]")
    private WebElement addQuantityOfAdults;

    //Todo Boton de disminuir adultos pantalla inicial de ser necesario
    @FindBy(css = "//input[@tabindex=\"-1\"]//following::button[1]")
    private WebElement subtractQuantityOfAdults;

    @FindBy(css = "#searchbox-sbox-box-flights .sbox5-segments--lzKBc > div:nth-child(1) .sbox-places-origin--G_Rvw input")
    private WebElement leavingFromButton;

    @FindBy(css = "#searchbox-sbox-box-flights .sbox5-segments--lzKBc > div:nth-child(1) .sbox-places-toggle-destination--2Zfh0")
    private WebElement switchBetweenLeavingFromAndGoingTo;

    @FindBy(xpath = "//div[@class=\"sbox5-dates-input1-flex\"]/div")
    private WebElement datepickerOneButton;

    @FindBy(css = "#component-modals > div:nth-child(1) div:nth-child(3) > div > .sbox5-monthgrid-title-month")
    private WebElement monthOneOnView;

    @FindBy(css = "#component-modals > div:nth-child(1) div:nth-child(3) > div > .sbox5-monthgrid-title-year")
    private WebElement yearOneOnView;

    @FindBy(css = "#component-modals > div:nth-child(1) > div:nth-child(3) .btn-text")
    private WebElement confirmDateButton;

    @FindBy(css = "em.btn-text:nth-child(2)")
    private WebElement searchButton;

    @FindBy(css = "#component-modals > div:nth-child(1) .calendar-arrow-right")
    private WebElement nextMonth;

    /**
     * Variables propias de clase y necesarias para logica implementada en la misma
     */
    private int adultsNumber, adultsCounter;
    private String exactMonth, exactYear;
    private String dayToSet, monthToSet, yearToSet;

    /**
     * Constructor de clase e inicializador de elementos (Page factory)
     */
    public BookingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
    }

    /**
     * Metodo que nos simplificara la accion de sleeccionar el boton de flight y el checkbox de
     * ida y vuelta
     */
    public void selectFlightPlusRoundTripButtons() {
        flightButton.click();
        roundTripCheckBox.click();
    }

    /**
     * Este metodo seleccionara la opcion de pasajeros y clase y nos permitira asignarle un valor
     * a sus parametros de acuerdo a las necesidades planteadas
     *
     * @param noTravelers   Esta variable asignara el valor de los adultos
     * @param classOfFlight Esta variable tomara el texto y lo buscara en la lista desplegable
     */
    //Todo Integrar logica para la seleccion de niños
    public void selectNoTravelersAndClassOfFlight(String noTravelers, String classOfFlight) {
        selectTravelersButton.click();
        selectAdultTravelers(Integer.parseInt(noTravelers), classOfFlight);
    }

    /**
     * Este metodo nos ayudara a seleccionar las ciudades tanto de origen como la ciudad de
     * destino
     * <p>
     * Nota: actualmente se selecciona siempre el primer item en lista pero aun asi este puede
     * ser configurado mas adelante
     *
     * @param leavingFrom ciudad de origen o de salida la cual sera digitada en el textbox de la pagina
     * @param goingTo     ciudad de destino o llegada la cual sera digitada en el textvox de la pagina
     */
    //Todo definir si puede existir el caso de seleccionar un item diferente al primero en la lista desplegada
    public void selectCitiesOfLeavingAndGoing(String leavingFrom, String goingTo) {
        selectPlace(leavingFromButton, goingTo, "1");
        switchBetweenLeavingFromAndGoingTo.click();
        selectPlace(leavingFromButton, leavingFrom, "1");
    }

    /**
     * Metodo que nos ayudara a interactuar con el date picker recive tres parametros
     * los cuales deben ser enteros entregados por un string y corresponden a un gap
     * de tiempo el cual se sumara a partir de la fecha actual del sistema
     *
     * @param monthGapLeaving gap de meses que sumara a partir de el mes actual
     * @param dayGapLeaving   gap de dias que sumara a partir del dia actual el numero puede
     *                        ser incluso superior a un mes y este metodo calculara la fecha correspondiente
     * @param dayGapGoinTo    gap de dias entre el vuelo de ida y el de regreso
     */
    public void selectDepartureAndComeBackDays(String monthGapLeaving, String dayGapLeaving, String dayGapGoinTo) {
        datepickerOneButton.click();
        selectDateWithMonthGap(Integer.parseInt(monthGapLeaving), Integer.parseInt(dayGapLeaving));
        selectDateWithDaysGap(Integer.parseInt(dayGapGoinTo));
        confirmDateButton.click();
    }

    /**
     * Este metodo basicamente nos sirve para confirmar los datos suministrados y nos retorna un
     * objeto de la pagina de resultados de la busqueda
     *
     * @return objeto de tipo SearchResultsPage el cual nos permitira seguir interactuando con la
     * siguiente pagina ya que este elemento le entrega el driver necesario
     */
    public SearchResultsPage ConfirmDataOfTrip() {
        searchButton.click();
        return new SearchResultsPage(getDriver());
    }


    /**
     * Este metodo nos permitira seleccionar cualquier fecha superior a la actual en el date picker
     *
     * @param day          dia entregado en forma 1-31 como cadena de texto tener presente que los dias de un solo
     *                     digito deben ser entregados asi con un solo digito
     * @param desiredMonth Mes deseado para la seleccion de fecha este debe estar en formaro 1-12 como un
     *                     String y los meses de un solo digito deben ser escritos con un solo digito e.g 1
     * @param desiredYear  Año que se desea seleccionar se entrega como cadena de texto o String formato yyyy
     *                     e.g 2020 o 2021
     */
    public void selectDay(String day, String desiredMonth, String desiredYear) {
        exactMonth = monthOneOnView.getText();
        exactYear = yearOneOnView.getText();

        int difference = utilDate.monthDifference(desiredMonth, desiredYear, exactMonth, exactYear);
        for (int count = 1; count <= difference; count++) {
            nextMonth.click();
        }
        utilDate.monthStringToInt(utilDate.firstLetterUppercase(desiredMonth));
        String monthOnDate = utilDate.monthFormatedForDate();
        click(By.xpath("//div[@data-month=\"" + desiredYear + "-" + monthOnDate + "\"]/div[3]//div[text()=\"" + day + "\"]"));
    }

    /**
     * Metodo que nos ayudara a seleccionar el numero de adultos deseados para el viaje
     *
     * @param noTravelers entero >1< lo que determine la aplicacion
     */
    public void selectAdultTravelers(int noTravelers, String coincidence) {
        adultsNumber = 1;//todo Pendiente definicion de donde tomar el dato dado que este valor no se puede tomar de la pagina
        adultsCounter = noTravelers;
        while (adultsNumber != adultsCounter) {
            if (adultsNumber < adultsCounter) {
                wait.until(ExpectedConditions.elementToBeClickable(addQuantityOfAdults));
                addQuantityOfAdults.click();
                adultsNumber++;
            } else {
                wait.until(ExpectedConditions.elementToBeClickable(subtractQuantityOfAdults));
                subtractQuantityOfAdults.click();
                adultsNumber--;
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable(dropboxTypeOfFlight));
        dropboxTypeOfFlight.click();
        List<WebElement> options = getoptions(dropboxTypeOfFlight).getOptions();
        for (WebElement option : options) {
            if (option.getText().equals(coincidence)) {
                option.click();
                dropboxTypeOfFlight.click();
                break;
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable(applyTravelersConfiguration));
        applyTravelersConfiguration.click();
    }

    /**
     * Este metodo nos servira para seleccionar un elemento de la lista mostrada por la aplicacion
     * tras realizar una busqueda de ciudad, permite seleccionar cualquier elemento listado por medio
     * de su posicion --- por defecto es llamado con el valor 1 de esta forma se garantiza una seleccion
     */
    public void selectPlace(WebElement object, String place, String selection) {
        object.click();
        object.sendKeys(place);
        click(By.xpath("/html/body/div[4]/div/div[1]/ul/li[" + selection + "]/span"));
    }

    /**
     * Metodo que tomara el gap de meses y el gap de dias para la fecha de partida y la convertira en una fecha
     * con el formato indicado para ser usado por el metodo selectDay
     *
     * @param months meses de gap seleccionados desde el test principal para la fecha de partida
     * @param days   dias de gap seleccionados desde el test principal para la fecha de partida
     */
    public void selectDateWithMonthGap(int months, int days) {
        wait.until(ExpectedConditions.elementToBeClickable(nextMonth));
        dateNow.add(Calendar.MONTH, months);
        dateNow.add(Calendar.DAY_OF_YEAR, days);
        dayToSet = extractDay(formatCalendar(dateNow));
        monthToSet = extractMonth(formatCalendar(dateNow));
        yearToSet = extractYear(formatCalendar(dateNow));
        selectDay(dayToSet, monthToSet, yearToSet);
    }

    /**
     * Metodo que tomara el gap de dias para determinar la fecha de regreso y la convertira en una fecha
     * con el formato indicado para ser usado por el metodo selectDay
     *
     * @param days dias de gap seleccionados desde el test principal para la fecha de destino determinara
     *             el periodo de duracion del viaje
     */
    public void selectDateWithDaysGap(int days) {
        wait.until(ExpectedConditions.elementToBeClickable(nextMonth));
        dateNow.add(Calendar.DAY_OF_YEAR, days);
        dayToSet = extractDay(formatCalendar(dateNow));
        monthToSet = extractMonth(formatCalendar(dateNow));
        yearToSet = extractYear(formatCalendar(dateNow));
        selectDay(dayToSet, monthToSet, yearToSet);
    }
}
