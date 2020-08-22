
package acme.datatypes;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import acme.framework.datatypes.DomainDatatype;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Email extends DomainDatatype {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	private String				name;

	@NotBlank
	private String				email;


	// Derived attributes -----------------------------------------------------

	@Override
	public String toString() {
		StringBuilder result;

		result = new StringBuilder();
		result.append("<<");
		result.append(this.name);
		if (this.name == null) {
			result.append(this.email);
		} else {
			result.append("<");
			result.append(this.email);
			result.append(">");
		}
		result.append(">>");
		return result.toString();
	}
}
