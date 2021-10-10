package pribas.task.dataAccess.abstracts;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import pribas.task.entities.Moment;

public interface MomentDao extends MongoRepository<Moment, String> {
	Moment getById(String id);
	Moment getByTitle(String title);
	
	@Query(value = "{ 'moment_date' : {$gte : ?0, $lte: ?1 }}")
	List<Moment> findByMomentDateBetween(Date from, Date to);
}
