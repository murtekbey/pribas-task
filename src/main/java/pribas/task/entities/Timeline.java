package pribas.task.entities;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pribas.task.core.entities.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "timeline")
public class Timeline {

	@Id
	private String id;
	
	@NotBlank
	@NotNull
	@Size(min = 4, max = 40)
	private String title;
	
	@NotBlank
	@NotNull
	private String description;
	
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "UTC")
	@CreatedDate
	private Date creation_date;
	
	private List<String> tags;
	
	@DBRef
	@JsonIncludeProperties({"id"})
	private User user;
	
	@DBRef
	private List<Moment> moments;
}
