
package acme.features.authenticated.bookkeeperRequest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Bookkeeper;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedBookkeeperRequestRepository extends AbstractRepository {

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findOneAuthenticatedByUserAccountId(int id);

	@Query("select b from Bookkeeper b where b.userAccount.id = ?1")
	Bookkeeper findOneBookkeeperByUserAccountId(int id);

	@Query("select count(b) from BookkeeperRequest b where b.authenticated.userAccount.id = ?1 and b.state='pending'")
	int numberOfBookkeeperRequestPendingByUserAccountId(int userAccountId);
}
