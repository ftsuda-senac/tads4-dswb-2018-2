/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.exemplosspring.util;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author fernando.tsuda
 */
public class WebDateFormatter implements Formatter<LocalDate> {
    
    private static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
    
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);

    @Override
    public String print(LocalDate t, Locale locale) {
        return t.format(formatter);
    }

    @Override
    public LocalDate parse(String date, Locale locale) throws ParseException {
        return LocalDate.parse(date, formatter);
    }
}
