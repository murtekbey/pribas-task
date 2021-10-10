package pribas.task.business.abstracts;

import java.util.List;

import pribas.task.core.utilities.results.DataResult;
import pribas.task.core.utilities.results.Result;
import pribas.task.entities.Timeline;

public interface TimelineService {
	Result add(Timeline timeline);
	Result update(Timeline timeline);
	Result delete(Timeline timeline);
	
	DataResult<List<Timeline>> getAll();
	DataResult<Timeline> getById(String id);
	DataResult<Timeline> getByTitle(String title);
	DataResult<List<Timeline>> getByMomentsIn(List<String> moments);
	DataResult<List<Timeline>> getByTagsIn(List<String> tags);
	
}
