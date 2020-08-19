package acme.features.anonymous.technologyRecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.technologyRecords.TechnologyRecord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousTechnologyRecordShowService implements AbstractShowService<Anonymous, TechnologyRecord>{

	@Autowired
	private AnonymousTechnologyRecordRepository repository;
	
	@Override
	public boolean authorise(Request<TechnologyRecord> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(Request<TechnologyRecord> request, TechnologyRecord entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "activitySector", "inventor", "description",
				"website", "email", "openSource", "stars");
		
	}

	@Override
	public TechnologyRecord findOne(Request<TechnologyRecord> request) {
		assert request != null;
		
		TechnologyRecord result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		
		return result;
	}
	

}
