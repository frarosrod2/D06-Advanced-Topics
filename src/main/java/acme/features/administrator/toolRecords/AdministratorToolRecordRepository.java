
package acme.features.administrator.toolRecords;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolRecords.ToolRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorToolRecordRepository extends AbstractRepository {

	@Query("select o from ToolRecord o where o.id = ?1")
	ToolRecord findToolRecordById(int id);

	@Query("select o from ToolRecord o")
	Collection<ToolRecord> findMany();

}
