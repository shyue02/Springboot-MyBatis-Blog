package site.metacoding.red.web.dto.response.boards;

public class PagingDto {
	private Integer startNum;
	private Integer totalCount;
	private Integer totalPage;	 	// = (totalCount / 한 페이지당 갯수) + 나머지가 있으면 +1
	private Integer currentPage;
	private boolean isLast;
	private boolean isFirst;
}