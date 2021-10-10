package pribas.task.api;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import pribas.task.business.abstracts.MomentService;
import pribas.task.core.utilities.results.DataResult;
import pribas.task.core.utilities.results.Result;
import pribas.task.entities.Moment;

@RestController
@RequestMapping("/api/moments")
@CrossOrigin
public class MomentsController {

	private MomentService momentService;

	@Autowired
	public MomentsController(MomentService momentService) {
		super();
		this.momentService = momentService;
	}
	
	@PostMapping("/add")
	public Result add(
			@Valid @RequestParam(required = false) MultipartFile[] files,
			@Valid @RequestParam String title,
			@Valid @RequestParam String description,
			@Valid @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm") Date moment_date
			) {
		Moment moment = new Moment();
		moment.setTitle(title);
		moment.setDescription(description);
		moment.setMoment_date(moment_date);
		return this.momentService.add(files, moment);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody Moment moment) {
		return this.momentService.update(moment);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody Moment moment) {
		return this.momentService.delete(moment);
	}
	
	@GetMapping("")
	public DataResult<List<Moment>> getAll() {
		return this.momentService.getAll();
	}
	
	@GetMapping("/{id}")
	public DataResult<Moment> getById(@PathVariable String id) {
		return this.momentService.getById(id);
	}
	
	@GetMapping("/getByTitle")
	public DataResult<Moment> getByTitle(@Valid @RequestParam String title) {
		return this.momentService.getByTitle(title);
	}
	
	@GetMapping("/findByMomentDateBetween")
	public DataResult<List<Moment>> findByMomentDateBetween(
			@Valid @RequestParam("From (UTC)") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm") Date from,
			@Valid @RequestParam("To (UTC)") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm") Date to
			) {
		return this.momentService.findByMomentDateBetween(from, to);
	}
	
	
	
	
}
