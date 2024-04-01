package phoenix.partyquest.service.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import phoenix.partyquest.api.response.member.MemberMessageResponse;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
@SpringBootTest
class MemberServiceTest {
    @Autowired MemberService memberService;
    //동한
    //동한 끝
    //경진
    //경진 끝
    //현정
    //현정 끝
    //동일

    //메세지 리스트 조회
    @Test
    public void messageList(){
        //given
        String memberEmail = "dddd@dddd.com";
        //when
        List<MemberMessageResponse> messageList = memberService.messageList(memberEmail);
        System.out.println("messageList : "+messageList.toString());
        //then
        assertThat(messageList.size()).isGreaterThan(0);
    }
    //동일 끝

}