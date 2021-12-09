package hu.unideb.inf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.unideb.inf.exception.ResourceNotFoundException;
import hu.unideb.inf.model.User;
import hu.unideb.inf.repository.UserRepository;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	//összes felhasználó lekérése
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	//felhasználó létrehozása
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	//get user by id
	
	@PostMapping("users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		User user=userRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Nincs felhasználó a következő id-val:" + id));
		
		return ResponseEntity.ok(user);
	}
	
	
	
	//felhasználó szerkesztése id alapján
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User userDetails){
		User user=userRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Nincs felhasználó a következő id-val:" + id));
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setUserName(userDetails.getUserName());
		
		User updatedUser =userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
		
	}
	
	//felhasználó törlése
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteUser(@PathVariable Long id){
		User user=userRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Nincs felhasználó a következő id-val:" + id));
		
		userRepository.delete(user);
		Map<String,Boolean> response=new HashMap<>();
		response.put("törölt",Boolean.TRUE);
		
		return ResponseEntity.ok(response);
		
	}
	
	

}
