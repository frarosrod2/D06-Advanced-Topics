
package acme.features.bookkeeper.accountingRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.customisations.Customisation;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookkeeperAccountingRecordRepository extends AbstractRepository {

	@Query("select a from AccountingRecord a where a.id=?1")
	AccountingRecord findOneById(int id);

	@Query("select i from InvestmentRound i where i.id=?1")
	InvestmentRound findInvestmentRound(int id);

	@Query("select a from AccountingRecord a where a.investmentRound.id = ?1 and a.status = 'PUBLISHED' or (a.investmentRound.id = ?1 and a.bookkeeper.userAccount.username = ?2)")
	Collection<AccountingRecord> findAccountingOfMine(int lastInvestment, String bookkeeper);

	@Query("select b from Bookkeeper b where b.id = ?1")
	Bookkeeper findBookkeeperById(int id);

	@Query("select c from Customisation c")
	Customisation findCustomisation();

}
