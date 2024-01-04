package phoenix.partyquest.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import phoenix.partyquest.api.response.member.MemberMessageResponse;
import phoenix.partyquest.service.member.MemberService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
@SpringBootTest
class MemberControllerTest {
    @Autowired MemberService memberService;
    @Autowired MemberController memberController;
    //동한
    //동한 끝
    //경진
    //경진 끝
    //현정
    //현정 끝
    //동일
    @Test
    public void findMessageList(){
        //given
        String memberEmail = "cccc@cccc.ccc";
        //when
        List<MemberMessageResponse> messageList = memberController.findMessageList(memberEmail);
        System.out.println("messageList : "+messageList.toString());
        //then
        assertThat(messageList.size()).isGreaterThan(0);

    }
    //동일 끝
}