package br.edu.ifpb.wazbarber.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author jozimar
 */
@Converter(autoApply = false)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime x) {
        if (x == null) {
            return null;
        }
        return Timestamp.valueOf(x);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp y) {
        if (y == null) {
            return null;
        }
        return y.toLocalDateTime();
    }
}
