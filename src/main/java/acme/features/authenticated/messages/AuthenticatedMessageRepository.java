
package acme.features.authenticated.messages;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customisations.Customisation;
import acme.entities.forums.Forum;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.involvedUsers.InvolvedUser;
import acme.entities.messages.Message;
import acme.entities.roles.Investor;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("SELECT i.forum FROM InvolvedUser i WHERE i.authenticated.id = ?1")
	Collection<Forum> getUserInvolvedForums(int activeroleId);

	@Query("SELECT a FROM Authenticated a")
	Collection<Authenticated> getAuthenticatedUsers();

	@Query("SELECT a FROM Authenticated a WHERE a.userAccount.username IN ?1")
	Collection<Authenticated> getAuthenticatedUsersByUsernames(String[] usernames);

	@Query("SELECT t FROM Forum t WHERE t.id = ?1")
	Forum getForumById(int forumId);

	@Query("SELECT i FROM InvolvedUser i WHERE i.authenticated.id = ?1")
	InvolvedUser getInvolvedUserById(int activeRoleId);

	@Query("select m from Message m where m.forum.id = ?1")
	Collection<Message> findMessagesByForumId(int forumId);

	@Query("SELECT c FROM Customisation c")
	Customisation findCustomisation();

	@Query("select i from InvolvedUser i where i.forum.id = ?1")
	Collection<InvolvedUser> findInvolvedUserByForumId(int forumId);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findOneAuthenticatedById(int authId);

	@Query("select i from InvestmentRound i where i.forum.id = ?1")
	InvestmentRound findOneInvestmentByForumId(int forumId);

	@Query("select a.investor from Application a where a.investmentRound.id = ?1 and a.status='accepted'")
	Investor findInvestorByInvestmentId(int investId);

	@Query("select c.spamWords from Customisation c")
	String getSpamWords();

	@Query("select c.spamThreshold from Customisation c")
	Double getThreshold();

	@Query("select m from Message m where m.id = ?1")
	Message findOneMessageById(int id);
}
