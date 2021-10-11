package pribas.task.api;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.multipart.MultipartFile;

import pribas.task.business.abstracts.MomentService;
import pribas.task.core.entities.File;
import pribas.task.core.utilities.helpers.abstracts.MongoFileService;
import pribas.task.core.utilities.results.DataResult;
import pribas.task.core.utilities.results.ErrorDataResult;
import pribas.task.core.utilities.results.Result;
import pribas.task.entities.Moment;

@RestController
@RequestMapping("/api/moments")
@CrossOrigin
public class MomentsController {

	private MomentService momentService;
	private MongoFileService mongoFileService;

	@Autowired
	public MomentsController(MomentService momentService, MongoFileService mongoFileService) {
		super();
		this.momentService = momentService;
		this.mongoFileService = mongoFileService;
	}
	
	@PostMapping("/add")
	public Result add(
			@Valid @RequestParam(required = false) MultipartFile[] attachments,
			@Valid @RequestParam String title,
			@Valid @RequestParam String description,
			@Valid @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm") Date moment_date
			) {
		Moment moment = new Moment();
		moment.setTitle(title);
		moment.setDescription(description);
		moment.setMoment_date(moment_date);
		return this.momentService.add(attachments, moment);
	}
	
	@PostMapping("/update")
	public Result update(@Valid @RequestBody Moment moment) {
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
	public DataResult<Moment> getByTitle(@RequestParam String title) {
		return this.momentService.getByTitle(title);
	}
	
	@GetMapping("/findByMomentDateBetween")
	public DataResult<List<Moment>> findByMomentDateBetween(
			@RequestParam("From (UTC)") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm") Date from,
			@RequestParam("To (UTC)") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm") Date to
			) {
		return this.momentService.findByMomentDateBetween(from, to);
	}
	
    @GetMapping("/attachment/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String id) throws IOException {
        File file = this.mongoFileService.downloadFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType() ))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(new ByteArrayResource(file.getFile()));
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
