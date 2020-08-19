
package acme.features.entrepreneur.investmentRounds;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.activities.Activity;
import acme.entities.customisations.Customisation;
import acme.entities.forums.Forum;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.involvedUsers.InvolvedUser;
import acme.entities.roles.Entrepreneur;
import acme.entities.roles.Investor;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurInvestmentRoundRepository extends AbstractRepository {

	@Query("select i from InvestmentRound i where i.id = ?1")
	InvestmentRound findOneInvestmentRoundById(int id);

	@Query("select i from InvestmentRound i where i.entrepreneur.id = ?1")
	Collection<InvestmentRound> findManyByEntrepreneurId(int entrepreneurId);

	@Query("Select e from Entrepreneur e where e.id = ?1")
	Entrepreneur findOneEntrepreneurById(int id);

	@Query("select a.investor from Application a where a.investmentRound.entrepreneur.id = ?1 and a.status='accepted'")
	Collection<Investor> findInvestorAcceptedApplication(int entrepreneurId);

	@Query("Select a from Authenticated a where a.id = ?1")
	Authenticated findAuthenticatedUserById(int id);

	@Query("select ir.workProgramme from InvestmentRound ir where ir.id = ?1")
	Collection<Activity> findAllActivitiesByInvestmentRoundId(int id);

	@Query("select ir.forum from InvestmentRound ir where ir.id = ?1")
	Forum findForumByInvestmentRoundId(int id);

	@Query("select count(a) from InvestmentRound ir join ir.applications a where a.investmentRound.id=?1")
	Integer findAllApplicationsByInvestmentRoundId(int id);

	@Query("select c from Customisation c")
	Customisation findCustomisation();

	@Query("SELECT a FROM Authenticated a WHERE a.userAccount.id = ?1")
	Authenticated getAuthenticatedByAccountId(int accountId);

	@Query("select ir from InvestmentRound ir where ir.ticker = ?1")
	InvestmentRound findOneByTicker(String ticker);

	@Query("select ir from InvestmentRound ir join ir.accountingRecords a where a.id=?1")
	Collection<AccountingRecord> findAllAccountingRecordsByInvestmentRoundId(int id);

	@Query("select i from InvolvedUser i where i.forum.id = ?1")
	Collection<InvolvedUser> findById(int id);

}
