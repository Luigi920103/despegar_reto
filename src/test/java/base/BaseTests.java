package base;

import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.BasePages;
import pages.BookingPage;

public class BaseTests {
    private WebDriver driver;
    protected BasePages basePage;

    public BaseTests(String navigator) {
        setUp(navigator);
    }

    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Con este metodo nos aseguraremos de la condicion de pagina inicial la cual debera estar cargada
     * correctamente antes de cada ejecucion de pruebaactualmente este metodo cerrara pestañas adicionales
     * que queden abiertas tras la ejecucion, y limpiara cache
     */
    @Before(order = 2)
    public void goHome() {
        String originalHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(originalHandle);
        driver.manage().deleteAllCookies();
        driver.get("https://www.despegar.com.co/");
        driver.manage().window().maximize();
    }

    /**
     * Al iniciar cada clse crearemos y configuraremos nuestro correspondiente webdriver, de igual forma
     * crearemos nuestro webDriverwait el que nos permitira realizar esperas explicitas
     *
     * @param navigator con este parametro seleccionamos desde que navegador deseamos
     *                  lanzar nuestra prueba
     */
    @Before(order = 1)
    public void setUp(String navigator) {
        if (navigator.equals("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            setDriver(new FirefoxDriver());
            goHome();
            basePage = new BasePages(getDriver());
        } else {
            WebDriverManager.chromedriver().setup();
            setDriver(new ChromeDriver());
            goHome();
            basePage = new BasePages(getDriver());
        }

    }

    /**
     * Al finalizar todas las pruebas procederemos a cerrar nuestro driver junto con sus sesiones y tabs
     */
    @After
    public void tearDown() {
        getDriver().quit();
    }

    /**
     * tras realizar la carga de la pagina en navegador nos entrega el page object correspondiente
     * el cual nos permitira interactuar con la aplicacion
     *
     * @return
     */
    public BookingPage loadBookingPage() {
        return new BookingPage(getDriver());
    }

    /**
     * Metodo que nos permitira setear nuestro driver como pagina inicial y base
     *
     * @param driver
     */
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}



