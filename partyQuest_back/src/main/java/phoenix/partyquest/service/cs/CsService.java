package phoenix.partyquest.service.cs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phoenix.partyquest.api.request.cs.BoardListRequest;
import phoenix.partyquest.api.response.cs.BoardListResponse;
import phoenix.partyquest.domain.cs.Board;
import phoenix.partyquest.repository.cs.CsRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CsService {
    private final CsRepository csRepository;

    // 공지사항 리스트 with Paging
    public Page<BoardListResponse.BoardListDetail> getNoticePagedList(BoardListRequest boardListRequest){

        Pageable pageable = boardListRequest.getPageable("id");
        Page<Board> boards = csRepository.findByCate(boardListRequest.getCateId(),pageable);
        int totalElement = (int)boards.getTotalElements();
        BoardListResponse results = new BoardListResponse(boards,totalElement);
        return new PageImpl<>(results.getBoardList(), pageable, (long) results.getTotal());

    }

}
