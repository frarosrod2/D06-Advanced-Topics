
package acme.features.administrator.bookkeeperRequest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.BookkeeperRequest;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorBookkeeperRequestListService implements AbstractListService<Administrator, BookkeeperRequest> {

	@Autowired
	private AdministratorBookkeeperRequestRepository repository;


	@Override
	public boolean authorise(final Request<BookkeeperRequest> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "responsabilityStatement");

	}

	@Override
	public Collection<BookkeeperRequest> findMany(final Request<BookkeeperRequest> request) {
		assert request != null;
		Collection<BookkeeperRequest> result;
		result = this.repository.findPendingRequest();
		return result;
	}

}
