package ai.blogapp.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public String encode(String password) {
        return encoder.encode(password);
    }
	public boolean matches(String rawPassword, String encodedPassword) {
		System.out.println("Password Service       " + rawPassword + " " + encodedPassword);
        return encoder.matches(rawPassword, encodedPassword);
    }
}
