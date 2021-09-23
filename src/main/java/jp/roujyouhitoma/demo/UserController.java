package jp.roujyouhitoma.demo;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

class User {

	String id;

	String name;

	User(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

}

interface Dao<T> {

	Optional<T> get(String id);

}

class UserDao implements Dao {

	// Stub user data
	List<User> users = new ArrayList<>(Arrays.asList(new User("id1", "name1"), new User("id2", "name2")));

	@Override
	public Optional<User> get(String id) {
		return this.users.stream().filter(v -> v.id.equals(id)).findFirst();
	}

}

@RestController
public class UserController {

	@GetMapping("/users/{id}")
	public Optional<User> getUser(@PathVariable("id") String id) {
		Optional<User> user = new UserDao().get(id);
		if (!user.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}
		return user;
	}

}
