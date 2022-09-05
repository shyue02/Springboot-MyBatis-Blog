package site.metacoding.red.web.dto.request.users;		//웹에서 쓰는 dto중에 요청에 대한 object

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JoinDto {
	private String username;
	private String password;
	private String email;
}