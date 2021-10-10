package pribas.task.core.entities;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class User {
	
	@Id
	private String id;
	
	@NotBlank
	@NotNull
	@Size(min = 4, max = 40)
	private String username;
	
	@Email
	@NotBlank
	@NotNull
	private String email;
	
	@NotBlank
	@NotNull
	@Size(min = 7)
	private String password;
}