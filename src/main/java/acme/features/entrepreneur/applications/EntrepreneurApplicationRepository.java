
package acme.features.entrepreneur.applications;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.customisations.Customisation;
import acme.entities.forums.Forum;
import acme.entities.involvedUsers.InvolvedUser;
import acme.entities.roles.Investor;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id = ?1")
	Application findOneApplicationById(int id);

	@Query("select a from Application a where a.investmentRound.entrepreneur.id = ?1 order by a.ticker asc, a.creationMoment desc")
	Collection<Application> findManyByEntrepreneurId(int entrepreneurId);

	@Query("select a.investmentRound.forum from Application a where a.id = ?1")
	Forum findForumByInvestmentRound(int id);

	@Query("SELECT a FROM Authenticated a WHERE a.userAccount.id = ?1")
	Authenticated getAuthenticatedById(int accountId);

	@Query("select c from Customisation c")
	Customisation findCustomisation();

	@Query("select i from InvolvedUser i where i.forum.id = ?1")
	Collection<InvolvedUser> findInvolvedUsersByForumId(int id);

	@Query("select i from Investor i  where i.id = ?1")
	Investor findInvestorById(int id);
}
