package ai.blogapp.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ai.blogapp.model.UserModel;
import ai.blogapp.service.UserService;

@RestController
public class ImageController {
	private final UserService uservice;
	
	ImageController(UserService uservice) {
		this.uservice = uservice;
	}
	
	@GetMapping("/photos/{id}")
	public ResponseEntity<byte[]> getPhoto(@PathVariable String id) {
		UserModel muser = uservice.findById(id);
		if(muser == null) return null;
		
		byte[] imageBytes = muser.getPhoto();
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_JPEG);
	    
	    return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
	}
	
}
