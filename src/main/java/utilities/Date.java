package utilities;

/**
 * Esta clase nos ayudara a darle formato a las fechas que nososotros ingresemos a la
 * aplicacion o que sean tomadas desde la misma aplicacion
 */
public class Date {
    private int numberMonth, currentMonth, result, desiredMonth, desiredYear, currentyear, differenceOfYears;
    private String monthFormated;

    /**
     * Este metodo nos ayuda a determinar la diferencia entre una fecha concreta a otra
     *
     * @param desiredMonthString mes deseado
     * @param desiredYearString  año deseado
     * @param currentMonthString mes actual segun el sistema en el que se ejecute
     * @param currentYearString  año actual segun el sistema en el que se ejecute
     * @return nos retorna un valor entero el cual puede ser iterado para poder avanzar dentro del mismo
     * calendario y de esta forma tener la capacidad de seleccionar el mes que realmente queremos seleccionar
     */
    public int monthDifference(String desiredMonthString, String desiredYearString, String currentMonthString, String currentYearString) {
        desiredMonth = monthStringToInt(firstLetterUppercase(desiredMonthString));
        currentMonth = monthStringToInt(firstLetterUppercase(currentMonthString));
        desiredYear = Integer.parseInt(desiredYearString);
        currentyear = Integer.parseInt(currentYearString);
        differenceOfYears = desiredYear - currentyear;
        if (currentMonth < desiredMonth) {
            result = (12 * differenceOfYears) + desiredMonth - currentMonth;
        } else if (currentMonth > desiredMonth) {
            result = (12 * differenceOfYears) - currentMonth + desiredMonth;
        } else {
            result = 0;
        }
        return result;
    }

    /**
     * Este mes basicamente lee el mes que se le entrega en un formato especifico y dentro de sus variables
     * internas almacena el mes correspondiente en diferentes formatos lo cual permitira interactuar con los
     * diferentes elementos de una forma facil
     *
     * @param month recibe un mes en forma de string
     * @return retorna el numero del mes al que corresponde el ingresado adicionalmente setea una variable con otro
     * formato de ese mismo mes dentro de esta clase
     */
    public int monthStringToInt(String month) {
        switch (month) {
            case "Enero":
                numberMonth = 1;
                monthFormated = "01";
                break;
            case "Febrero":
                numberMonth = 2;
                monthFormated = "02";
                break;
            case "Marzo":
                numberMonth = 3;
                monthFormated = "03";
                break;
            case "Abril":
                numberMonth = 4;
                monthFormated = "04";
                break;
            case "Mayo":
                numberMonth = 5;
                monthFormated = "05";
                break;
            case "Junio":
                numberMonth = 6;
                monthFormated = "06";
                break;
            case "Julio":
                numberMonth = 7;
                monthFormated = "07";
                break;
            case "Agosto":
                numberMonth = 8;
                monthFormated = "08";
                break;
            case "Septiembre":
                numberMonth = 9;
                monthFormated = "08";
                break;
            case "Octubre":
                numberMonth = 10;
                monthFormated = "10";
                break;
            case "Noviembre":
                numberMonth = 11;
                monthFormated = "11";
                break;
            case "Diciembre":
                numberMonth = 12;
                monthFormated = "12";
                break;
            default:
                numberMonth = 1;
                break;
        }
        return numberMonth;
    }

    /**
     * Este metodo regresa el mes que fue formateado en la clase anterior monthStringToInt a la
     * clase que lo necesite
     *
     * @return returna el mes formateado con tres letras y un espacio posterior como "Dec "
     */
    public String monthFormatedForDate() {
        return monthFormated;
    }

    /**
     *
     */
    public String firstLetterUppercase(String text) {
        String firstChar = text.substring(0, 1);
        String toUpperCaseChar = firstChar.toUpperCase();
        String leftText = text.substring(1, text.length());
        return toUpperCaseChar + leftText;
    }
}