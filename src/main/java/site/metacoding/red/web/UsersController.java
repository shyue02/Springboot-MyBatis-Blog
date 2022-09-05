package site.metacoding.red.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.dto.request.users.JoinDto;
import site.metacoding.red.web.dto.request.users.LoginDto;

@RequiredArgsConstructor	//final 붙은 곳만 생성자 생성
@Controller
public class UsersController {

	private final HttpSession session;	// 스프링이 서버시작시에 IoC 컨테이너에 보관함. => DI
	private final UsersDao usersDao;		// Dao를 컴퍼지션
	
	@GetMapping("/logout")	//로그아웃은 GetMapping
	public String logout() {
		session.invalidate();	// invalidate -  해당 사용자의 키 값의 영역을 날린다는 것 = 로그아웃
		return "redirect:/";
	}
	
	@PostMapping("/login")		//로그인만 예외로 select인데 post로 함
	public String login(LoginDto loginDto) {
		Users usersPS = usersDao.login(loginDto);  // DB로 부터 select해서 들고 온 것 뒤에는 이름 적을 때 PS를 붙여주자 -> PS가 없으면 db에서 들고온 것인지, 사용자가 받은 것인지, 내가 new해서 받은건지 구별이 안 됨 
		if(usersPS != null) { //null이 아니면 로그인 인증
			session.setAttribute("principal", usersPS);		// principal : 인증된 유저, -> 키값 / 리캐스트에 principal 적지마! // http session은 object 타입이다(모든 클래스는 오브젝트를 상속)
			return "redirect:/";
		} else {	// 인증 안 됨
			return "redirect:/loginForm";
		}
	}
	
	@PostMapping("/join")		// 인증에 필요한 주소들은 앞에 테이블명을 잘 안 붙인다 - 규칙
	public String join(JoinDto joinDto) {
		usersDao.insert(joinDto);
		return "redirect:/loginForm";		//만들어져 있는 것을 리다이렉트
	}

	@GetMapping("/loginForm")
	public String loginForm() {
		return "users/loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "users/joinForm";
	}
}