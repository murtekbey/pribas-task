package pribas.task.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pribas.task.business.abstracts.TimelineService;
import pribas.task.core.utilities.results.DataResult;
import pribas.task.core.utilities.results.ErrorDataResult;
import pribas.task.core.utilities.results.Result;
import pribas.task.entities.Timeline;

@RestController
@RequestMapping("/api/timelines")
@CrossOrigin
public class TimelinesController {
	private TimelineService timelineService;

	@Autowired
	public TimelinesController(TimelineService timelineService) {
		super();
		this.timelineService = timelineService;
	}
	
	@PostMapping("/add")
	public Result add(@Valid @RequestBody Timeline timeline) {
		return this.timelineService.add(timeline);
	}
	
	@PostMapping("/update")
	public Result update(@Valid @RequestBody Timeline timeline) {
		return this.timelineService.update(timeline);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody Timeline timeline) {
		return this.timelineService.delete(timeline);
	}
	
	@GetMapping("")
	public DataResult<List<Timeline>> getAll() {
		return this.timelineService.getAll();
	}
	
	@GetMapping("/{id}")
	public DataResult<Timeline> getById(@PathVariable String id) {
		return this.timelineService.getById(id);
	}
	
	@GetMapping("/getByTitle")
	public DataResult<Timeline> getByTitle(@RequestParam String title) {
		return this.timelineService.getByTitle(title);
	}
	
	@GetMapping("/getByMomentsIn")
	public DataResult<List<Timeline>> getByMomentsIn(@RequestParam("momentId") List<String> moments) {
		return this.timelineService.getByMomentsIn(moments);
	}
	
	@GetMapping("/getByTagsIn")
	public DataResult<List<Timeline>> getByTagsIn(@RequestParam List<String> tags) {
		return this.timelineService.getByTagsIn(tags);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
		Map<String, String> validationErrors = new HashMap<String, String>();
		for(FieldError fieldError: exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Validation errors");
		return errors;
	}
}
