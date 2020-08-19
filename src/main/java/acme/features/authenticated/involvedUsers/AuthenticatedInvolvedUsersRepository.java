
package acme.features.authenticated.involvedUsers;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.forums.Forum;
import acme.entities.involvedUsers.InvolvedUser;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedInvolvedUsersRepository extends AbstractRepository {

	@Query("select i from InvolvedUser i where i.forum.id = ?1")
	Collection<InvolvedUser> findMany(int id);

	@Query("select i from InvolvedUser i where i.id = ?1")
	InvolvedUser findOne(int id);

	@Query("select f from Forum f where f.id = ?1")
	Forum findForumById(int forumId);

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findOneAuthenticatedById(int id);

	@Query("select a from Authenticated a where a.userAccount.username = ?1")
	Authenticated findOneAuthenticatedByUsername(String username);

	@Query("select a from Authenticated a, UserAccount ua where ua.username = ?1 and a.userAccount.id = ua.id")
	Authenticated findAuthenticatedByName(String string);

	@Query("select count(ua) from UserAccount ua where ua.username = ?1")
	int countSearchUser(String string);

	@Query("select count(i) from InvolvedUser i where i.forum.id = ?2 and i.authenticated.userAccount.username = ?1 ")
	int countSearchUserInInvolvedUser(String searchUser, int forumId);

	@Query("select i.forum.id from InvolvedUser i where i.id = ?1")
	int findForumIdByInvolvedUserId(int involvedUserId);

	@Query("select ua.id from InvolvedUser i, Authenticated a, UserAccount ua where i.id = ?1 and a.id = i.authenticated.id and a.userAccount.id = ua.id")
	int findAuthenticatedIdByInvolvedUserId(int involvedUserId);

	@Query("select i from InvolvedUser i where i.id = ?1")
	InvolvedUser findOneInvolvedById(int involvedUserId);

	@Query("select count(i)>0 from InvolvedUser i where i.forum.id = ?1 and i.authenticated.id = ?2")
	boolean findIsForumUser(int forumId, int authenticatedId);

}
