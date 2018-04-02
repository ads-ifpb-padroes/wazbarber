package br.edu.ifpb.wazbarber.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jozimar
 */
@FacesConverter(value = "convert.LocalTime", forClass = LocalTime.class)
public class LocalTimeConvert implements Converter {

    DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        try {
            return LocalTime.parse(value, time);
        } catch (DateTimeParseException e) {
            throw new ConverterException(
                    "O formato da hora deve ser 12:00");
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }

        LocalTime localTime = (LocalTime) value;

        return localTime.format(time);

    }
}
