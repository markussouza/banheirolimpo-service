/**
 * 
 */
package com.inova.banheirolimpo.converter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author Markus Souza on 13/12/2017
 *
 */
public class DateUtil {

	public LocalDate unixTimeToDateTime(final Long unixTime) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		final String formattedDtm = Instant.ofEpochSecond(unixTime).atZone(ZoneId.of("GMT-4")).format(formatter);
		return LocalDate.parse(formattedDtm, formatter);
	}

}
