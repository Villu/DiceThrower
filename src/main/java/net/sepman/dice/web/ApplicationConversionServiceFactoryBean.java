package net.sepman.dice.web;

import net.sepman.dice.domain.Die;

import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.roo.addon.web.mvc.controller.converter.RooConversionService;

/**
 * A central place to register application converters and formatters. 
 */
@RooConversionService
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

    public Converter<Die, String> getDieToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<net.sepman.dice.domain.Die, java.lang.String>() {
            public String convert(Die die) {
                return new StringBuilder().append(die.getThrowResult()).toString();
            }
        };
    }
	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		// Register application converters and formatters
		registry.addConverter(getDieToStringConverter());
	}
}
