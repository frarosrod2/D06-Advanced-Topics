
package acme.datatypes;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import acme.framework.datatypes.DomainDatatype;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Email extends DomainDatatype {

	// Serialisation identifier -----------------------------------------------

	private static final long serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Pattern(regexp = "^[a-zA-Z\\s-.]*[<]?[a-zA-Z0-9.]+[@][a-z0-9.]+[>]?")
	private String email;


	// Derived attributes -----------------------------------------------------

	@Override
	public String toString() {
		StringBuilder result;

		result = new StringBuilder();
		result.append("<<");
		result.append(this.email);
		result.append(">>");

		return result.toString();
	}
}
