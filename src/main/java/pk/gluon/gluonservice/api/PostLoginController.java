package pk.gluon.gluonservice.api;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pk.gluon.gluonservice.api.request.Token;

@RestController
@RequestMapping("/api")
public class PostLoginController {

	@PostMapping("/login/cookie")
	public ResponseEntity<String> addAuthCookie(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Token token) {
		System.out.println("Inside method addAuthCookie "+token);
		Cookie authCookie = null;
		if (token != null) {
			authCookie = createCookie(token);
			response.addCookie(authCookie);
		}
		return ResponseEntity.ok().body("ADDED");
	}

	private Cookie createCookie(Token token) {
		String accessTokenAndRefreshToken = token.getAccess_token() + ":" + token.getRefresh_token();
		System.out.println("Inside method accessTokenAndRefreshToken "+accessTokenAndRefreshToken);
		Cookie authCookie = new Cookie("Gluon", "cookie");
		authCookie.setPath("/");
		authCookie.setDomain("localhost");
		authCookie.setMaxAge(60 * 60 * 5);
		return authCookie;
	}

}
