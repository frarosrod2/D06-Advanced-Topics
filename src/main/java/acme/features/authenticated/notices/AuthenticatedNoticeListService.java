
package acme.features.authenticated.notices;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.notices.Notice;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedNoticeListService implements AbstractListService<Authenticated, Notice> {

	@Autowired
	AuthenticatedNoticeRepository repository;


	@Override
	public boolean authorise(final Request<Notice> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Notice> request, final Notice entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "title", "creation");

	}

	@Override
	public Collection<Notice> findMany(final Request<Notice> request) {
		assert request != null;

		Collection<Notice> result;

		result = this.repository.findMany();

		return result;
	}

}
