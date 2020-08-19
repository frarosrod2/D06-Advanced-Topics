
package acme.features.administrator.customisation;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorCustomisationDisplayService implements AbstractShowService<Administrator, Customisation> {

	@Autowired
	AdministratorCustomisationRepository repository;


	@Override
	public boolean authorise(final Request<Customisation> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Customisation> request, final Customisation entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Hibernate.initialize(entity.getSpamWords());
		Hibernate.initialize(entity.getActivitySectors());

		request.unbind(entity, model, "spamWords", "spamThreshold", "activitySectors");

	}

	@Override
	public Customisation findOne(final Request<Customisation> request) {
		assert request != null;

		Customisation result = (Customisation) this.repository.findMany()[0];
		return result;
	}

}
