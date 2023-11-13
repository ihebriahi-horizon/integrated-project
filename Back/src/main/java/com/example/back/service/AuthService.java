
import com.example.back.config.JwtService;
import com.example.back.payload.LoginDto;
import com.example.back.payload.SignUpDto;
import com.example.back.repository.UserRepository;
import com.example.back.entity.AuthenticationResponse;
import com.example.back.entity.Role;
import com.example.back.entity.User;
import com.example.back.repository.RoleRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class AuthService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	public AuthenticationResponse register(SignUpDto request) {
		if (!userRepository.existsByUserEmail(request.getUserEmail())) {
			User user = new User();
			user.setUserFirstname(request.getUserFirstname());
			user.setUserLastname(request.getUserLastname());
			user.setUserEmail(request.getUserEmail());
			user.setUserPassword(passwordEncoder.encode(request.getUserPassword()));
			user.setUserAddress(request.getUserAddress());
			user.setUserGender(request.getUserGender());

			log.error(roleRepository.findByRoleName("User").get());
			Role roles = roleRepository.findByRoleName("User").get();
			user.setRole(roles);
			userRepository.save(user);

			var jwtToken = jwtService.generateToken(user);
			return AuthenticationResponse.builder().token(jwtToken).status(200).message("Successfully registered")
					.build();
		} else {
			return AuthenticationResponse.builder().status(401).message("You already have an account with this email!")
					.token("").build();
		}
		// return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
	}

	public AuthenticationResponse authenticate(LoginDto request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUserEmail(), request.getUserPassword()));
		var user = userRepository.findByUserEmail(request.getUserEmail()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().status(200).message("User logged in").token(jwtToken).build();
	}
}
