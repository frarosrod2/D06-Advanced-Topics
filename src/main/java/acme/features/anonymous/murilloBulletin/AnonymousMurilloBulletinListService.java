
package acme.features.anonymous.murilloBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletin.MurilloBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousMurilloBulletinListService implements AbstractListService<Anonymous, MurilloBulletin> {

	@Autowired
	AnonymousMurilloBulletinRepository repository;


	@Override
	public boolean authorise(final Request<MurilloBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<MurilloBulletin> request, final MurilloBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "title", "moment", "volumen", "number", "text");

	}

	@Override
	public Collection<MurilloBulletin> findMany(final Request<MurilloBulletin> request) {
		assert request != null;

		Collection<MurilloBulletin> result;

		result = this.repository.findMany();

		return result;
	}

}
