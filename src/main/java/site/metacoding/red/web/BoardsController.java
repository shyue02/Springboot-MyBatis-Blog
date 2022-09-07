package site.metacoding.red.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.boards.MainDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;

@RequiredArgsConstructor
@Controller
public class BoardsController {
	
	private final HttpSession session;
	private final BoardsDao boardsDao;
//	@PostMapping("/boards{id}/delete")
//	@PostMapping("/boards{id}/update")		-> 혼자서 delete랑 update 해보고 싶으면 이렇게 연습

	@PostMapping("/boards")
	public String writeBoards(WriteDto writeDto) { // 동사 먼저 적고 명사 적는 습관을 갖자
		// 1번 세션에 접근해서 세션값을 확인한다. 그때 Users로 다운캐스팅하고 키값은 principal로 한다.
		Users principal = (Users) session.getAttribute("principal");

		// 2번 pricipal null인지 확인하고 null이면 loginForm 리다이렉션해준다.
		if(principal == null) { // 인증
			return "redirect:/loginForm";
		} // 본코드(핵심코드) / else 안 쓰는것이 더 깔끔

		// 3번 BoardsDao에 접근해서 insert 메서드를 호출한다.
		// 조건 : dto를 entity로 변환해서 인수로 담아준다.
		// 조건 : entity에는 세션의 principal에 getId가 필요하다.
		boardsDao.insert(writeDto.toEntity(principal.getId())); // post 요청은 insert(프로토콜)

		return "redirect:/";
	}

	// http://localhost:8000/ 	-> 이렇게 요청하면 쿼리스트링 없으니까 페이지 값 null -> 페이지 디폴트값을 0으로 만들어줌
	// http://localhost:8000/?page=0
	@GetMapping({"/", "/boards"}) // { }로 감싸면 두 개 쓸 수 있다
	public String getBoardList(Model model, Integer page) { // 0 -> 0, 1->10, 2->20
		if(page == null) page = 0;	
		int startNum = page * 10;	
		List<MainDto> boardsList = boardsDao.findAll(startNum);
		PagingDto paging = boardsDao.paging(page);
		model.addAttribute("boardsList", boardsList);
		model.addAttribute("paging", paging);
		return "boards/main";
	}

	@GetMapping("/boards/{id}")
	public String getBoardList(@PathVariable Integer id, Model model) {
		model.addAttribute("boards", boardsDao.findById(id));
		return "boards/detail";
	}

	@GetMapping("/boards/writeForm")
	public String writeForm() { // 모델 필요 없다 - 들고갈 데이터가 없어서. cv 패턴.
		Users principal = (Users) session.getAttribute("principal"); // 키값 - principal 로그인페이지에서 principal로 키값을 넣어서 찾을 때도
																		// 이 키값으로 찾아야 함
		if(principal == null) { // 인증 . else 안 쓰는게 좋음
			return "redirect:/loginForm"; // 내부적으로 다시 통신해주는 기술
		}
		return "boards/writeForm";	// 바로 (뷰)파일을 찾아서 응답해주는 기술
	}
}