package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService; // 스프링 컨테이너에 등록을 하고 쓰기 -> 딱 하나만 등록이 됨

    @Autowired // 스프링이 스프링 컨테이너에 있는 맴버 서비스를 가져다가 연결 시켜줌 : dependency injection(의존관계 주입)
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    // 오류발생 -> memberService가 스프링 빈으로 등록되어 있지 않음
    @GetMapping("/members/new") // 웹에 url을 치는 Get방식으로 들어옴
    public String createForm(){
        return  "members/createMemberForm"; // return하면 templates에서 찾음
    }

    @PostMapping("/members/new") // 데이터를 폼에 넣어서 전달할때, 데이터 등록할때 Post 사용 / Get은 주로 조회할때
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
