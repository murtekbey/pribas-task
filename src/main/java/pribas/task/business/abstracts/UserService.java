package pribas.task.business.abstracts;

import pribas.task.core.entities.User;
import pribas.task.core.utilities.results.Result;

public interface UserService {
	Result add(User user);
}
