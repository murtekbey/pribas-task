package pribas.task.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import pribas.task.entities.Timeline;

public interface TimelineDao extends MongoRepository<Timeline, String> {
	Timeline getById(String id);
	Timeline getByTitle(String title);
	List<Timeline> getByMomentsIn(List<String> moments);
	List<Timeline> getByTagsIn(List<String> tags);
}
