package steps;

import base.BaseTests;
import io.cucumber.java.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.BookingPage;
import pages.ConfirmDataPage;
import pages.SearchResultsPage;

public class CucumberSteps {

    /**
     * Pageobjects que nos ayudara a interactuar entre los diferentes metodos de una forma mas rapida
     */
    private BookingPage booking;
    private SearchResultsPage resultPage;
    private ConfirmDataPage confirmData;
    private BaseTests base;

    /**
     * Condiciones Given
     */

    /**
     * @param navigator Si el string es Firefox este se lanzara desde ese navegador
     *                  de otra forma se lanzara en Chrome por defecto
     */
    @Given("the user choose the {string}")
    public void theUserChooseTheNavigator(String navigator) {
        base = new BaseTests(navigator);
    }

    /**
     * Condificiones And
     */
    /**
     * Condicion para seleccionar el boton de vuelo y el checkbox de ida y vuelta
     */
    @And("the user creates a round-trip flight")
    public void theUserCreatesARoundTripFlight() {
        booking = base.loadBookingPage();
        booking.selectFlightPlusRoundTripButtons();
    }

    /**
     * Condicion para seleccionar numero de viajantes y el tipo de o clase del vuelo
     *
     * @param adults        Numero entero entregado como strong
     * @param classOfFlight string como Economica, Premium economy, Ejecutiva/Business, Primera Clase
     */
    @And("the user selects the number of travelers {string},with the class {string}")
    public void theUserSelectsTheNumberOfTravelersAdultsWithTheClassClass(String adults, String classOfFlight) {
        booking.selectNoTravelersAndClassOfFlight(adults, classOfFlight);
    }

    /**
     * Condicion para seleccionar las ciudades de salida y destino
     *
     * @param leavingFrom ciudad de salida este campo recive un String
     * @param GoingTo     Ciudad de destino este campo recive un string
     */
    @And("the user selects the {string} with the {string}")
    public void theUserSelectsTheDeparture_cityWithTheDestination_city(String leavingFrom, String GoingTo) {
        booking.selectCitiesOfLeavingAndGoing(leavingFrom, GoingTo);
    }

    /**
     * Condicion para interactuar con el datepicker y seleccionar las fechas de salida y regreso
     * de los correspondientes vuelos
     *
     * @param months_gap        entero expresado como string mayor o igual a 1 el cual sera sumado a la
     *                          fecha del sistema
     * @param days_gap          entero expresado como string mayor o igual a 1 el cual sera sumado a la fecha del sistema
     *                          tras la suma del gap de meses
     * @param comeback_days_gap entero expresado como string mayor o igual a 1 el cual sera sumado
     *                          a la fecha resultante de el vuelo de salida
     */
    @And("the user selects the gaps: for the departure day {string}, {string},for the comeback flight {string}")
    public void theUserSelectsTheGapsForTheDepartureDayMonths_gapDays_gapForTheComebackFlightComeback_days_gap(String months_gap, String days_gap, String comeback_days_gap) {
        booking.selectDepartureAndComeBackDays(months_gap, days_gap, comeback_days_gap);
        resultPage = booking.ConfirmDataOfTrip();
    }

    /**
     *Condiciones When
     */
    /**
     * condicion para seleccionar un paquete y vuelos especificos dentro de los resultados
     * presentados por la pagina
     *
     * @param packageOfFlights      seleccion de numero de paquete entero expresado como string
     * @param optionPackageGoing    seleccion de vuelo de salida especifico dentro del paquete
     *                              previamente seleccionado entero expresado como string
     * @param optionPackageComeBack seleccion de vuelo de regreso especifico dentro del paquete
     *                              seleccionado con el primer parametro - entero expresado
     *                              como string
     */
    @When("the user selects the {string} with the {string}, {string}")
    public void theUserSelectsThePackage_optionWithTheDeparture_optionComeback_option(String packageOfFlights, String optionPackageGoing, String optionPackageComeBack) {
        confirmData = resultPage.selectSpecificResult(packageOfFlights, optionPackageGoing, optionPackageComeBack);
    }

    /**
     *Condiciones Then
     */
    /**
     * Condicion de validacion en la pagina de obtencion de datos de usuario para confirmar vuelos
     *
     * @param message mensaje a validar dentro de la pagina de confirmacion de datos
     */
    @Then("The new page shows the {string}")
    public void theNewPageShowsTheMessage(String message) {
        Assert.assertEquals(confirmData.getCompleteYourDataMessage(), message);
    }

    /**
     * Esta condicion nos ayudara a cerrar el navegador una vez finalizada la prueba correspondiente
     */
    @After
    public void closeBrowser() {
        base.tearDown();
    }
}
