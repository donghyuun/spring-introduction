package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller//Component 스캔
public class MemberController {

    private final MemberService memberService;

    @Autowired//생성자 주입방법, 스프링 컨테이너에서 memberService를 가져와서 본 controller에 연결(autowire)시켜 준다. 의존관계 주입(Dependency injection)
    public MemberController(MemberService memberService){//생성자 함수!!
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")//url은 같지만 method에 따라 다르게 매핑할 수 있다.
    public String create(MemberForm form){//MemberForm 객체에 본 주소로 post된 내용을 setName 해준 뒤, 해당 객체를 가져온다.
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();//모든 멤버들을 다 꺼내서
        model.addAttribute("members", members);//모델에 담아서 view template에 넘김

        return "members/memberList";
    }
}
