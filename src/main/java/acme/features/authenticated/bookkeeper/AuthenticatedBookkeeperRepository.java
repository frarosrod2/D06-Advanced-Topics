package acme.features.authenticated.bookkeeper;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Bookkeeper;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedBookkeeperRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select b from Bookkeeper b where b.userAccount.id = ?1")
	Bookkeeper findOneBookkeeperByUserAccountId(int id);

}
