package carrentalsystem.util;

import javax.swing.text.NumberFormatter;
import javax.swing.JFormattedTextField;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;

public class FormUtil {

    // Method to create a formatted text field for integers
    public static JFormattedTextField createIntegerField(int max) {
        NumberFormat integerFormat = NumberFormat.getIntegerInstance();
        NumberFormatter integerFormatter = new NumberFormatter(integerFormat) {
            @Override
            public Object stringToValue(String string) throws ParseException {
                return string.isEmpty() ? null : super.stringToValue(string);
            }
        };
        integerFormatter.setValueClass(Integer.class);
        integerFormatter.setAllowsInvalid(false);
        integerFormatter.setMinimum(0); // Set to null if no minimum
        integerFormatter.setMaximum(max); // Set to your maximum value

        JFormattedTextField integerField = new JFormattedTextField(integerFormatter) {
            @Override
            public void commitEdit() throws ParseException {
                try {
                    super.commitEdit();
                } catch (ParseException pe) {
                    if (getText().isEmpty()) {
                        setValue(null);
                    } else {
                        throw pe;
                    }
                }
            }
        };
        integerField.setColumns(20);
        return integerField;
    }

}
