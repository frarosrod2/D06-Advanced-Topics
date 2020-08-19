
package acme.features.anonymous.rosaBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletin.RosaBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousRosaBulletinListService implements AbstractListService<Anonymous, RosaBulletin> {

	@Autowired
	AnonymousRosaBulletinRepository repository;


	@Override
	public boolean authorise(final Request<RosaBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<RosaBulletin> request, final RosaBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "title", "moment", "description");

	}

	@Override
	public Collection<RosaBulletin> findMany(final Request<RosaBulletin> request) {
		assert request != null;

		Collection<RosaBulletin> result;

		result = this.repository.findMany();

		return result;
	}

}
