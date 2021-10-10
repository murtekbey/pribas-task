package pribas.task.business.abstracts;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import pribas.task.core.utilities.results.DataResult;
import pribas.task.core.utilities.results.Result;
import pribas.task.entities.Moment;

public interface MomentService {
	Result add(MultipartFile[] attachments, Moment moment);
	Result update(Moment moment);
	Result delete(Moment moment);
	
	DataResult<List<Moment>> getAll();
	DataResult<Moment> getById(String id);
	DataResult<Moment> getByTitle(String title);
	DataResult<List<Moment>> findByMomentDateBetween(Date from,Date to);
}
