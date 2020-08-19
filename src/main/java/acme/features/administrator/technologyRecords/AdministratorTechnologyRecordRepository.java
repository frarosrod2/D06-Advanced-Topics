
package acme.features.administrator.technologyRecords;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.technologyRecords.TechnologyRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorTechnologyRecordRepository extends AbstractRepository {

	@Query("select o from TechnologyRecord o where o.id = ?1")
	TechnologyRecord findTechnologyRecordById(int id);

	@Query("select o from TechnologyRecord o")
	Collection<TechnologyRecord> findMany();

}
