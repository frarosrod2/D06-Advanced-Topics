
package acme.features.administrator.customisation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorCustomisationUpdateService implements AbstractUpdateService<Administrator, Customisation> {

	@Autowired
	AdministratorCustomisationRepository repository;


	@Override
	public void bind(final Request<Customisation> request, final Customisation entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public Customisation findOne(final Request<Customisation> request) {
		assert request != null;

		Customisation result = (Customisation) this.repository.findMany()[0];
		return result;
	}

	@Override
	public void validate(final Request<Customisation> request, final Customisation entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String spamWords = request.getModel().getString("spamWords");
		List<String> words = Arrays.stream(spamWords.split(",")).map(String::trim).collect(Collectors.toList());

		if (!errors.hasErrors("spamWords")) {
			// Comprobamos que las palabras no están vacías
			boolean validWords = words.stream().noneMatch(String::isEmpty);
			errors.state(request, validWords, "spamWords", "administrator.customisation.error.wordEmpty");
		}

		if (!errors.hasErrors("spamWords")) {
			// Comprobamos que no repetimos palabras
			boolean repeatedWord = words.stream().distinct().count() == words.size();
			errors.state(request, repeatedWord, "spamWords", "administrator.customisation.error.repeatedWord");
		}

		String activitySectors = request.getModel().getString("activitySectors");
		List<String> sectors = Arrays.stream(activitySectors.split(",")).map(String::trim).collect(Collectors.toList());

		if (!errors.hasErrors("activitySectors")) {
			// Comprobamos que las palabras no están vacías
			boolean validSectors = sectors.stream().noneMatch(String::isEmpty);
			errors.state(request, validSectors, "activitySectors", "administrator.customisation.error.wordEmpty");
		}

		if (!errors.hasErrors("activitySectors")) {
			// Comprobamos que no repetimos palabras
			boolean repeatedSector = sectors.stream().distinct().count() == sectors.size();
			errors.state(request, repeatedSector, "activitySectors", "administrator.customisation.error.repeatedSector");
		}

	}

	@Override
	public void update(final Request<Customisation> request, final Customisation entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

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

		request.unbind(entity, model, "spamWords", "spamThreshold", "activitySectors");

	}

}
