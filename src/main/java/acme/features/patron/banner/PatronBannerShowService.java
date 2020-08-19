
package acme.features.patron.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.roles.Patron;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class PatronBannerShowService implements AbstractShowService<Patron, Banner> {

	@Autowired
	PatronBannerRepository repository;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;
		int bannerId = request.getModel().getInteger("id");
		int patronId = request.getPrincipal().getActiveRoleId();

		Banner banner = this.repository.findOneById(bannerId);
		Patron patron = this.repository.findOnePatronById(patronId);

		return banner.getPatron().equals(patron);
	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("hasCard", !(entity.getCreditCard() == null));
		int id = entity.getId();

		model.setAttribute("bannerId", id);
		if (entity.getCreditCard() != null) {
			model.setAttribute("creditCard", entity.getCreditCard().getId());
		}
		request.unbind(entity, model, "picture", "slogan", "targetURL");
	}

	@Override
	public Banner findOne(final Request<Banner> request) {
		assert request != null;
		Banner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
