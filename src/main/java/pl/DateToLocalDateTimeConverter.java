package pl;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Configuration
public class DateToLocalDateTimeConverter implements Converter<Date, LocalDateTime> {

   @Override
   public LocalDateTime convert(Date source) {
      return source == null ? null : LocalDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
   }
}
