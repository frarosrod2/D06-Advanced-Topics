
package acme.entities.involvedUsers;

import java.beans.Transient;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.entities.forums.Forum;
import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class InvolvedUser extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Authenticated		authenticated;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Forum				forum;

	private String				searchUser;


	@Transient
	public String getAuthenticatedUserName() {
		return this.authenticated.getUserAccount().getUsername();
	}

}
