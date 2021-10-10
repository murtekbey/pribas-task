package pribas.task.core.dataAccess;

import org.springframework.data.mongodb.repository.MongoRepository;

import pribas.task.core.entities.User;

public interface UserDao extends MongoRepository<User, String>{
	User findByUsername(String username);
	User findByEmail(String email);
}
