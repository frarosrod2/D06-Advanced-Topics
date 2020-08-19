
package acme.features.bookkeeper.investmentRounds;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.investmentRounds.InvestmentRound;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookkeeperInvestmentRoundRepository extends AbstractRepository {

	@Query("select i from InvestmentRound i where i.id=?1")
	InvestmentRound findOneInvestmentRoundById(int investmentId);

	@Query("select i.accountingRecords from InvestmentRound i where i.id=?1")
	Collection<AccountingRecord> findAccountingRecordsByInvestmentRoundId(int investmentId);

	@Query("select distinct i from InvestmentRound i join i.accountingRecords a where a.bookkeeper.id=?1")
	Collection<InvestmentRound> findInvestmentsWrittenByBookkeeperId(int bookkeeperId);

	@Query("select i from InvestmentRound i")
	Collection<InvestmentRound> findMany();

}
