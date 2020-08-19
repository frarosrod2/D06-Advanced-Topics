
package acme.features.authenticated.forums;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.activities.Activity;
import acme.entities.applications.Application;
import acme.entities.forums.Forum;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.involvedUsers.InvolvedUser;
import acme.entities.messages.Message;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedForumRepository extends AbstractRepository {

	@Query("SELECT i.forum FROM InvolvedUser i WHERE i.authenticated.id = ?1")
	Collection<Forum> getUserInvolvedForums(int authenticatedId);

	@Query("SELECT i.forum FROM InvestmentRound i WHERE i.entrepreneur.id = ?1")
	Forum getForumByEntrepreneur(int entrepreneurId);

	@Query("select i.authenticated.userAccount.username from InvolvedUser i where i.forum.id = ?1")
	Collection<String> getInvolvedUsers(int id);

	@Query("SELECT a FROM Authenticated a WHERE a.userAccount.username IN ?1")
	Collection<Authenticated> getAuthenticatedUsersByUsernames(String[] usernames);

	@Query("SELECT a FROM Authenticated a WHERE a.userAccount.id = ?1")
	Authenticated getAuthenticatedByAccountId(int accountId);

	@Query("select t from Forum t where t.id = ?1")
	Forum getForumById(int forumId);

	@Query("select f from Forum f where f.creator.id = ?1")
	Collection<Forum> getMyForums(int activeRoleId);

	@Query("select f.messages from Forum f where f.id = ?1")
	Collection<Message> findAllMessagesByForumId(int id);

	@Query("select f from Forum f where exists (select i from InvolvedUser i where i.authenticated.id = ?1 and i.forum.id=f.id)")
	Collection<Forum> getInvolvedForums(int activeRoleId);

	@Query("select i from InvolvedUser i where exists (select f from Forum f where i.forum.id = ?1 and i.forum.id=f.id)")
	Collection<InvolvedUser> getInvolvedUsersByForum(int forumId);

	@Query("select i from InvestmentRound i where i.forum.id=?1")
	InvestmentRound getInventmentRoundByForum(int forumId);

	@Query("select ir from InvestmentRound ir join ir.accountingRecords a where a.id=?1")
	Collection<AccountingRecord> findAllAccountingRecordsByInvestmentRoundId(int id);

	@Query("select ir.applications from InvestmentRound ir where ir.id = ?1")
	Collection<Application> findAllApplicationsByInvestmentRoundId(int id);

	@Query("select ir.workProgramme from InvestmentRound ir where ir.id = ?1")
	Collection<Activity> findAllActivitiesByInvestmentRoundId(int id);
}
