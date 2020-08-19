
package acme.features.authenticated.entrepreneur;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Entrepreneur;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedEntrepreneurRepository extends AbstractRepository {

	@Query("select e from Entrepreneur e where e.userAccount.id = ?1")
	Entrepreneur findOneEntrepreneurByUserAccountId(int id);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

}
