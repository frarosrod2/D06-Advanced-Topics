
package acme.features.patron.banner.creditCard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.Banner;
import acme.entities.creditCards.CreditCard;
import acme.entities.customisations.Customisation;
import acme.entities.roles.Patron;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronCreditCardRepository extends AbstractRepository {

	@Query("select b from Banner b where b.id = ?1")
	Banner findOneById(int id);

	@Query("select p from Patron p where p.id = ?1")
	Patron findOnePatronByUserAccountId(int id);

	@Query("select b from Banner b")
	Collection<Banner> findMany();

	@Query("select c from CreditCard c where c.id = ?1")
	CreditCard findOneCCById(int id);

	@Query("select b from Banner b where b.creditCard.id = ?1")
	Banner findOneBannerByCCId(int id);

	@Query("select c from Customisation c")
	Customisation findCustomisation();

	@Query("select b.patron from Banner b where b.id=?1")
	Patron findOnePatronByBannerId(int bannerId);

	@Query("select b.patron from Banner b where b.creditCard.id=?1")
	Patron findOnePatronByCCId(int ccId);
}
