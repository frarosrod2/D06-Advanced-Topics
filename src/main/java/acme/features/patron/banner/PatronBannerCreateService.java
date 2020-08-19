
package acme.features.patron.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import acme.entities.banners.Banner;
import acme.entities.customisations.Customisation;
import acme.entities.roles.Patron;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class PatronBannerCreateService implements AbstractCreateService<Patron, Banner> {

	@Autowired
	PatronBannerRepository repository;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "targetURL");
	}

	@Override
	public Banner instantiate(final Request<Banner> request) {
		assert request != null;

		Banner result;
		Principal principal;
		int userAccountId;
		Patron patron;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		patron = this.repository.findOnePatronByUserAccountId(userAccountId);

		result = new Banner();
		result.setPatron(patron);

		return result;
	}

	@Override
	public void validate(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Customisation customisation = this.repository.findCustomisation();

		String[] spamWords = customisation.getSpamWords().split(", ");
		Double threshold = customisation.getSpamThreshold();
		Boolean isSpamPicture = false;
		Boolean isSpamSlogan = false;
		Boolean isSpamTargetURL = false;

		int numberWordsPicture;
		int numberWordsSlogan;
		int numberWordsTarget;
		int numberSpam;

		if (!errors.hasErrors("picture")) {
			String picture = entity.getPicture().toLowerCase();
			numberWordsPicture = picture.split("\\W+").length;
			numberSpam = 0;
			for (String s : spamWords) {
				numberSpam += StringUtils.countOccurrencesOf(picture, s);
			}
			isSpamPicture = numberWordsPicture > 0 && numberSpam * 100 / numberWordsPicture >= threshold;
			errors.state(request, !isSpamPicture, "picture", "patron.banner.error.pictureSpamWord");
		}

		if (!errors.hasErrors("slogan")) {
			String slogan = entity.getSlogan().toLowerCase();
			numberWordsSlogan = slogan.split("\\W+").length;
			numberSpam = 0;
			for (String s : spamWords) {
				numberSpam += StringUtils.countOccurrencesOf(slogan, s);
			}
			isSpamSlogan = numberWordsSlogan > 0 && numberSpam * 100 / numberWordsSlogan >= threshold;
			errors.state(request, !isSpamSlogan, "slogan", "patron.banner.error.sloganSpamWord");
		}

		if (!errors.hasErrors("targetURL")) {
			String targetURL = entity.getTargetURL().toLowerCase();
			numberWordsTarget = targetURL.split("\\W+").length;
			numberSpam = 0;
			for (String s : spamWords) {
				numberSpam += StringUtils.countOccurrencesOf(targetURL, s);
			}
			isSpamTargetURL = numberWordsTarget > 0 && numberSpam * 100 / numberWordsTarget >= threshold;
			errors.state(request, !isSpamTargetURL, "targetURL", "patron.banner.error.targetUrlSpamWord");
		}

	}

	@Override
	public void create(final Request<Banner> request, final Banner entity) {
		this.repository.save(entity);
	}

}
