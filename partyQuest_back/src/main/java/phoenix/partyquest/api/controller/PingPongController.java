package phoenix.partyquest.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import phoenix.partyquest.api.request.study.StudyListCondRequest;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/api/ping")
@RequiredArgsConstructor
public class PingPongController {
    /**
     * 핑퐁 테스트하는 컨트롤러 에요
     * @param condRequest:검색 조건
     * @return
     */
    @GetMapping
    public ResponseEntity<String> pong(@ModelAttribute StudyListCondRequest condRequest) {
        log.info("hi there ping");
        String answer = "pong";
        if (condRequest.getSmallCateIds() !=null && condRequest.getSmallCateIds().size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (Long smallid :
                    condRequest.getSmallCateIds()) {
                sb.append(smallid);
                sb.append(" ");
            }
            answer += sb.toString();
            log.info("small id = {}", sb.toString());
        }
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)) //브라우져 단에 캐싱 처리 요청
                .body(answer);
    }
}