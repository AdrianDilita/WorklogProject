
import java.text.SimpleDateFormat;
import java.text.ParseException;


public class Validations {
    public boolean isDateFormatValid(String dateToValidate, String dateFormat) {
        String parseDateExceptionError = "Incorrect format, please use dd/MMM/yy, ex. 12/Oct/09";

        if (dateToValidate == null) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);

        try {
            sdf.parse(dateToValidate);
        } catch (ParseException e) {
            System.out.println(parseDateExceptionError);
            return false;
        }
        return true;
    }
}