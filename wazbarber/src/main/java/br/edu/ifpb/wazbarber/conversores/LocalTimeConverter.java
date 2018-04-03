package br.edu.ifpb.wazbarber.conversores;

import java.sql.Time;
import java.time.LocalTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author jozimar
 */
@Converter(autoApply = false)
public class LocalTimeConverter implements AttributeConverter<LocalTime, Time> {

    @Override
    public Time convertToDatabaseColumn(LocalTime x) {
        if (x == null) {
            return null;
        }
        return Time.valueOf(x);
    }

    @Override
    public LocalTime convertToEntityAttribute(Time y) {
        if (y == null) {
            return null;
        }
        return y.toLocalTime();
    }
}
