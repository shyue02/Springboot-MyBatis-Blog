package site.metacoding.red.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;

@RequiredArgsConstructor
@Controller
public class BoardsController {

	private final HttpSession session;
//	@PostMapping("/boards{id}/delete")
//	@PostMapping("/boards{id}/update")		-> 혼자서 delete랑 update 해보고 싶으면 이렇게 연습

	@GetMapping({"/", "/boards"})		// { }로 감싸면 두 개 쓸 수 있다
	public String getBoardList() {
		return "boards/main";
	}
	
	@GetMapping("/boards/{id}")	
	public String getBoardList(@PathVariable Integer id) {
		return "boards/detail";
	}
	
	@GetMapping("/boards/writeForm")
	public String writeForm() {
		Users principal = (Users) session.getAttribute("principal");	// 키값 - principal	로그인페이지에서 principal로 키값을 넣어서 찾을 때도 이 키값으로 찾아야 함
		if(principal == null){
			return "redirect:/loginForm";	//	내부적으로 다시 통신해주는 기술
		}else {
			return "boards/writeForm";	// 바로 (뷰)파일을 찾아서 응답해주는 기술
		}
	}
}