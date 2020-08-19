
package acme.features.authenticated.toolRecords;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolRecords.ToolRecord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedToolRecordListService implements AbstractListService<Authenticated, ToolRecord> {

	@Autowired
	AuthenticatedToolRecordRepository repository;


	@Override
	public boolean authorise(final Request<ToolRecord> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<ToolRecord> request, final ToolRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "activitySector", "stars");

	}

	@Override
	public Collection<ToolRecord> findMany(final Request<ToolRecord> request) {
		assert request != null;

		Collection<ToolRecord> result;

		result = this.repository.findMany();

		return result;
	}

}
