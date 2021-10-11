package pribas.task.entities;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pribas.task.core.entities.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "moment")
public class Moment {

	@Id
	private String id;
	
	@NotBlank
	@NotNull
	@Size(min = 4, max = 40)
	private String title;
	
	@NotBlank
	@NotNull
	private String description;
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "UTC")
	private Date moment_date;
	
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "UTC")
	@CreatedDate
	private Date creation_date;
	
	@JsonIgnoreProperties({"file"})
	private List<File> attachments;
	
}
