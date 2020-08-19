
package acme.features.administrator.bookkeeperRequest;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.BookkeeperRequest;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorBookkeeperRequestRepository extends AbstractRepository {

	@Query("select b from BookkeeperRequest b")
	Collection<BookkeeperRequest> findMany();

	@Query("select br from BookkeeperRequest br where br.state = 'pending'")
	Collection<BookkeeperRequest> findPendingRequest();

	@Query("select b from BookkeeperRequest b where b.id =?1")
	BookkeeperRequest findOneById(int id);

}
