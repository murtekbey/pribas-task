package pribas.task.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pribas.task.business.abstracts.UserService;
import pribas.task.core.entities.User;
import pribas.task.core.utilities.results.Result;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UsersController {
	private UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/add")
	public Result add(User user) {
		return this.userService.add(user);
	}
}
