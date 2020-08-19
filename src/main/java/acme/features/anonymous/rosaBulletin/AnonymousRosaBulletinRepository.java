
package acme.features.anonymous.rosaBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletin.RosaBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousRosaBulletinRepository extends AbstractRepository {

	@Query("select r from RosaBulletin r")
	Collection<RosaBulletin> findMany();

}
