
package acme.features.authenticated.inquires;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.inquiries.Inquiry;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedInquiryRepository extends AbstractRepository {

	@Query("select i from Inquiry i where i.id = ?1 and i.deadline > current_date()")
	Inquiry findInquiryById(int id);

	@Query("select i from Inquiry i where i.deadline > current_date()")
	Collection<Inquiry> findMany();

}
