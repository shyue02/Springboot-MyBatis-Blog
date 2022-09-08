package site.metacoding.red.web.dto.request.boards;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.domain.boards.Boards;

@Setter // setter는 안 쓸 수 있으면 안 쓰는 것이 좋다. / 변화가 필요할 때 setter 사용.
@Getter
public class UpdateDto {
	private String title;
	private String content;
}
