package pribas.task.business.concretes;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pribas.task.business.abstracts.MomentService;
import pribas.task.core.entities.File;
import pribas.task.core.utilities.helpers.abstracts.MongoFileService;
import pribas.task.core.utilities.results.DataResult;
import pribas.task.core.utilities.results.ErrorResult;
import pribas.task.core.utilities.results.Result;
import pribas.task.core.utilities.results.SuccessDataResult;
import pribas.task.core.utilities.results.SuccessResult;
import pribas.task.dataAccess.abstracts.MomentDao;
import pribas.task.entities.Moment;

@Service
public class MomentManager implements MomentService{
	
	private MomentDao momentDao;
	private MongoFileService mongoFileService;

	@Autowired
	public MomentManager(MomentDao momentDao, MongoFileService mongoFileService) {
		super();
		this.momentDao = momentDao;
		this.mongoFileService = mongoFileService;
	}

	@Override
	public Result add(MultipartFile[] attachments, Moment moment) {
		List<File> uploadedFiles = this.mongoFileService.uploadFiles(attachments);
		if (uploadedFiles != null) {
			moment.setAttachments(uploadedFiles);
		}
		this.momentDao.save(moment);
		return new SuccessResult("Moment is added");
	}

	@Override
	public Result update(Moment moment) {
		if (moment.getId() != null) {
			this.momentDao.save(moment);
			return new SuccessResult("Moment is updated");
		}
		return new ErrorResult("id not found");
	}

	@Override
	public Result delete(Moment moment) {
		if (moment.getId() != null) {
			this.mongoFileService.deleteFile(this.momentDao.getById(moment.getId()).getAttachments());
			this.momentDao.delete(moment);
			return new SuccessResult("Moment is deleted");
		}
		
		return new ErrorResult("id not found");
	}

	@Override
	public DataResult<List<Moment>> getAll() {
		return new SuccessDataResult<List<Moment>>(this.momentDao.findAll(), "Moments listed");
	}

	@Override
	public DataResult<Moment> getById(String id) {
		return new SuccessDataResult<Moment>(this.momentDao.getById(id), "Moment listed by id");
	}

	@Override
	public DataResult<Moment> getByTitle(String title) {
		return new SuccessDataResult<Moment>(this.momentDao.getByTitle(title), "Moment listed by title");
	}

	@Override
	public DataResult<List<Moment>> findByMomentDateBetween(Date from, Date to) {
		return new SuccessDataResult<List<Moment>>(this.momentDao.findByMomentDateBetween(from, to), "Moments listed by moment date");
	}
}
