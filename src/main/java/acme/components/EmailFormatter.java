
package acme.components;

import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.format.Formatter;

import acme.datatypes.Email;
import acme.framework.helpers.MessageHelper;

public class EmailFormatter implements Formatter<Email> {

	// Constructors -----------------------------------------------------------

	public EmailFormatter() {
		super();
	}

	// Formatter<Email> interface ---------------------------------------------

	@Override
	public Email parse(final String text, final Locale locale) throws ParseException {
		assert text != null;
		assert locale != null;

		Email result;
		String nameRegex, emailRegex, regex;
		Pattern pattern;
		Matcher matcher;
		String errorMessage;
		String email, name;

		nameRegex = "[a-zA-Z\\s-.]+";
		emailRegex = "[a-z0-9.]+[@][a-z0-9.]+";

		regex = String.format("^((?<N1>%1$s)\\s*([<](?<E1>%2$s)[>]))$|^((?<E2>%2$s))$", nameRegex, emailRegex);
		pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

		matcher = pattern.matcher(text);
		if (!matcher.find()) {
			errorMessage = MessageHelper.getMessage("default.error.email", null, "Invalid value", locale);
			throw new ParseException(errorMessage, 0);
		} else {
			name = matcher.group("N1");
			if (name == null) {
				email = matcher.group("E2");
			} else {
				email = matcher.group("E1");
				email = "<" + email + ">";
			}
			assert email != null;

			result = new Email();
			result.setName(name);
			result.setEmail(email);
		}

		return result;
	}

	@Override
	public String print(final Email object, final Locale locale) {
		assert object != null;
		assert locale != null;

		String result;
		String name, email;

		name = object.getName();
		email = object.getEmail();

		if (name == null || name == "") {
			result = String.format("%s", email);
		} else {
			result = String.format("%s%s", name, email);
		}

		return result;
	}

}
