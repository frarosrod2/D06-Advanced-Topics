
package acme.features.entrepreneur.activity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.activities.Activity;
import acme.entities.customisations.Customisation;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurActivityRepository extends AbstractRepository {

	@Query("select ir from InvestmentRound ir where ir.id = ?1")
	InvestmentRound findOneInvestmentRoundById(int id);

	@Query("select ir.entrepreneur from InvestmentRound ir where ir.id = ?1")
	Entrepreneur findEntrepreneurByInvestmentRoundId(int id);

	@Query("select ir from InvestmentRound ir join ir.workProgramme a where a.id= ?1")
	InvestmentRound findInvestmentRoundByActivityId(int id);

	@Query("select a from Activity a where a.id = ?1")
	Activity findOneById(int id);

	@Query("select c from Customisation c")
	Customisation findCustomisation();
}
