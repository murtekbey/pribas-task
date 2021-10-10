package pribas.task.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pribas.task.business.abstracts.UserService;
import pribas.task.core.dataAccess.UserDao;
import pribas.task.core.entities.User;
import pribas.task.core.utilities.results.ErrorResult;
import pribas.task.core.utilities.results.Result;
import pribas.task.core.utilities.results.SuccessResult;

@Service
public class UserManager implements UserService {
	
	private UserDao userDao;
	
	@Autowired
	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}


	@Override
	public Result add(User user) {
		if (this.userDao.findByEmail(user.getEmail()) == null && this.userDao.findByUsername(user.getUsername()) == null ) {
			this.userDao.save(user);
			return new SuccessResult(user.getUsername() + " is added");
		}
		return new ErrorResult("User already exists");
	}

}
