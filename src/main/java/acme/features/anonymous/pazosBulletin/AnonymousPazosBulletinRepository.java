
package acme.features.anonymous.pazosBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletin.PazosBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousPazosBulletinRepository extends AbstractRepository {

	@Query("select p from PazosBulletin p")
	Collection<PazosBulletin> findMany();

}
