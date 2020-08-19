
package acme.features.authenticated.messages;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import acme.entities.customisations.Customisation;
import acme.entities.forums.Forum;
import acme.entities.messages.Message;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	@Autowired
	private AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		int forumId = request.getModel().getInteger("forumId");
		Forum forum = this.repository.getForumById(forumId);
		Collection<Forum> forums = this.repository.getUserInvolvedForums(request.getPrincipal().getActiveRoleId());

		return forums.contains(forum);
	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Forum forum = this.repository.getForumById(request.getModel().getInteger("forumId"));
		request.getModel().setAttribute("forum", forum);
		request.bind(entity, errors, "moment");

	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "tags", "body");
		Forum forum = this.repository.getForumById(request.getModel().getInteger("forumId"));
		model.setAttribute("forum", forum);
		model.setAttribute("confirmPost", false);
	}

	@Override
	public Message instantiate(final Request<Message> request) {
		Message result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		Authenticated a = this.repository.findOneAuthenticatedById(request.getPrincipal().getActiveRoleId());
		Forum f = this.repository.getForumById(request.getModel().getInteger("forumId"));

		result = new Message();
		result.setMoment(moment);
		result.setCreator(a);
		result.setForum(f);
		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		// Validacion Checkbox

		errors.state(request, request.getModel().hasAttribute("confirmPost") && request.getModel().getBoolean("confirmPost"), "confirmPost", "authenticated.message.form.errors.confirmPost");

		// Validacion Body

		errors.state(request, request.getModel().getString("body").length() < 255, "body", "authenticated.message.form.errors.body-length");

		// Validacion Etiquetas

		String tag = request.getModel().getString("tags");
		List<String> tags = Arrays.stream(tag.split(",")).map(String::trim).collect(Collectors.toList());
		if (!tag.isEmpty()) {
			if (!errors.hasErrors("tags")) {
				// Comprobamos que las palabras no están vacías
				boolean validTags = tags.stream().noneMatch(String::isEmpty);
				errors.state(request, validTags, "tags", "authenticated.message.error.tagEmpty");
			}

			if (!errors.hasErrors("tags")) {
				// Comprobamos que no repetimos palabras
				boolean repeatedTags = tags.stream().distinct().count() == tags.size();
				errors.state(request, repeatedTags, "tags", "authenticated.message.error.repeatedTag");
			}
		}

		// Validacion Spam
		Customisation customisation = this.repository.findCustomisation();
		String[] spamWords = customisation.getSpamWords().split(", ");
		Double threshold = customisation.getSpamThreshold();
		int timesAppears;
		boolean esSpam;

		if (!errors.hasErrors("title")) {
			String title = request.getModel().getString("title").toLowerCase();
			Integer titleWordsCount = title.split("\\W+").length;
			timesAppears = 0;

			for (String spamWord : spamWords) {

				timesAppears += StringUtils.countOccurrencesOf(title, spamWord);
			}

			esSpam = titleWordsCount > 0 && timesAppears * 100 / titleWordsCount >= threshold;
			errors.state(request, !esSpam, "title", "authenticated.message.error.title.spam");
		}

		if (!errors.hasErrors("tags")) {
			String tags2 = request.getModel().getString("tags").toLowerCase();
			Integer tagsWordsCount = tags2.split("\\W+").length;
			timesAppears = 0;

			for (String spamWord : spamWords) {

				timesAppears += StringUtils.countOccurrencesOf(tags2, spamWord);
			}

			esSpam = tagsWordsCount > 0 && timesAppears * 100 / tagsWordsCount >= threshold;
			errors.state(request, !esSpam, "tags", "authenticated.message.error.tags.spam");
		}

		if (!errors.hasErrors("body")) {
			String body = request.getModel().getString("body").toLowerCase();
			Integer bodyWordsCount = body.split("\\W+").length;
			timesAppears = 0;

			for (String spamWord : spamWords) {
				timesAppears += StringUtils.countOccurrencesOf(body, spamWord);
			}

			esSpam = bodyWordsCount > 0 && timesAppears * 100 / bodyWordsCount >= threshold;
			errors.state(request, !esSpam, "body", "authenticated.message.error.body.spam");
		}

	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		Message message = this.repository.save(entity);
		Forum forum = this.repository.getForumById(request.getModel().getInteger("forumId"));
		forum.getMessages().add(message);
	}

}
