
package acme.features.administrator.challenges;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.challenges.Challenge;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorChallengeRepository extends AbstractRepository {

	@Query("select a from Challenge a where a.id = ?1 and a.deadline > current_date()")
	Challenge findChallengeById(int id);

	@Query("select c from Challenge c where c.deadline > current_date()")
	Collection<Challenge> findMany();

}
