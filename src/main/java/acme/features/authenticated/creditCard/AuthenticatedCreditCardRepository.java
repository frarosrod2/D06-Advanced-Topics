
package acme.features.authenticated.creditCard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.Banner;
import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedCreditCardRepository extends AbstractRepository {

	@Query("select b from Banner b where b.id = ?1")
	Banner findOneById(int id);

	@Query("select b from Banner b")
	Collection<Banner> findMany();

	@Query("select c from CreditCard c where c.id = ?1")
	CreditCard findOneCCById(int id);

	@Query("select b from Banner b where b.creditCard.id = ?1")
	Banner findOneBannerByCCId(int id);

	@Query("select p from Patron p where p.creditCard.id = ?1")
	Patron findPatronByCCId(int ccId);

	@Query("select p from Patron p where p.id = ?1")
	Patron findOnePatronByActiveId(int idRole);

	@Query("select b.patron from Banner b where b.id = ?1")
	Patron findPatronByBannerId(int bannerId);

}
