package phoenix.partyquest.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import phoenix.partyquest.api.request.cs.BoardListRequest;
import phoenix.partyquest.api.response.cs.BoardListResponse;
import phoenix.partyquest.service.cs.CsService;

@Slf4j
@RestController
@RequestMapping("/api/cs")
@RequiredArgsConstructor
public class CsController {
    private final CsService csService;


    @GetMapping("/notice")
    public ResponseEntity<Page<BoardListResponse.BoardListDetail>> noticeList(@ModelAttribute BoardListRequest boardListRequest){

        log.info("[BoardListRequest] : " + boardListRequest);
        if (1 == (boardListRequest.getCateId())) {
            boardListRequest.setCateId(boardListRequest.getCateId());
            Page<BoardListResponse.BoardListDetail> boardList = csService.getNoticePagedList(boardListRequest);
            return new ResponseEntity<>(boardList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/faq")
    public ResponseEntity<Page<BoardListResponse.BoardListDetail>> faqList(@ModelAttribute BoardListRequest boardListRequest){
        if (2 == boardListRequest.getCateId()) {
            return new ResponseEntity<>(csService.getNoticePagedList(boardListRequest), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
