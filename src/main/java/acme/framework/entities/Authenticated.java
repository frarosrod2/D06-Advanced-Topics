/*
 * Authenticated.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.framework.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import acme.entities.forums.Forum;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Authenticated extends UserRole {

	// Serialisation identifier -----------------------------------------------

	private static final long serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	//	@ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
	//	@Fetch(value = FetchMode.SUBSELECT)
	//	private Collection<Forum> forums;

	@OneToMany(mappedBy = "creator", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private Collection<Forum> createdForums;

}
