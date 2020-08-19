
package acme.features.administrator.notices;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.notices.Notice;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorNoticeRepository extends AbstractRepository {

	@Query("select n from Notice n where n.id = ?1")
	Notice findOneById(int id);

	@Query("select n from Notice n")
	Collection<Notice> findMany();

}
