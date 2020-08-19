
package acme.features.administrator.overtures;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.overtures.Overture;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorOvertureRepository extends AbstractRepository {

	@Query("select o from Overture o where o.id = ?1 and o.deadline > current_date()")
	Overture findOvertureById(int id);

	@Query("select o from Overture o where o.deadline > current_date()")
	Collection<Overture> findMany();

}
