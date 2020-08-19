
package acme.features.anonymous.murilloBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletin.MurilloBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousMurilloBulletinRepository extends AbstractRepository {

	@Query("select r from MurilloBulletin r")
	Collection<MurilloBulletin> findMany();

}
