
package acme.components;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FormatterRegistrar implements WebMvcConfigurer {

	@Override
	public void addFormatters(final FormatterRegistry registry) {
		EmailFormatter emailFormatter;

		emailFormatter = new EmailFormatter();
		registry.addFormatter(emailFormatter);
	}
}
