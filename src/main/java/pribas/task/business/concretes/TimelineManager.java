package pribas.task.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pribas.task.business.abstracts.TimelineService;
import pribas.task.core.utilities.results.DataResult;
import pribas.task.core.utilities.results.ErrorResult;
import pribas.task.core.utilities.results.Result;
import pribas.task.core.utilities.results.SuccessDataResult;
import pribas.task.core.utilities.results.SuccessResult;
import pribas.task.dataAccess.abstracts.TimelineDao;
import pribas.task.entities.Timeline;

@Service
public class TimelineManager implements TimelineService {
	
	private TimelineDao timelineDao;

	@Autowired
	public TimelineManager(TimelineDao timelineDao) {
		super();
		this.timelineDao = timelineDao;
	}

	@Override
	public Result add(Timeline timeline) {
		this.timelineDao.save(timeline);
		return new SuccessResult("Timeline is added");
	}

	@Override
	public Result update(Timeline timeline) {
		if (timeline.getId() != null) {
			this.timelineDao.save(timeline);
			return new SuccessResult("Timeline is updated");
		}
		return new ErrorResult("id not found");
	}

	@Override
	public Result delete(Timeline timeline) {
		if (timeline.getId() != null) {
			this.timelineDao.delete(timeline);
			return new SuccessResult("Timeline is deleted");
		}
		return new ErrorResult("id not found");
	}

	@Override
	public DataResult<List<Timeline>> getAll() {
		return new SuccessDataResult<List<Timeline>>(this.timelineDao.findAll(), "Timelines listed");
	}

	@Override
	public DataResult<Timeline> getById(String id) {
		return new SuccessDataResult<Timeline>(this.timelineDao.getById(id), "Timeline listed by id");
	}

	@Override
	public DataResult<Timeline> getByTitle(String title) {
		return new SuccessDataResult<Timeline>(this.timelineDao.getByTitle(title), "Timeline listed by title");
	}

	@Override
	public DataResult<List<Timeline>> getByMomentsIn(List<String> moments) {
		return new SuccessDataResult<List<Timeline>>(this.timelineDao.getByMomentsIn(moments), "Timelines listed by moments");
	}

	@Override
	public DataResult<List<Timeline>> getByTagsIn(List<String> tags) {
		return new SuccessDataResult<List<Timeline>>(this.timelineDao.getByTagsIn(tags), "Timelines listed by tags");
	}

}
