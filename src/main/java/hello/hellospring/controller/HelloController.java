package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") //웹에서 /hello 로 들어오면 이 메서드를 호출해줌
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")

    //이번엔 파라미터를 외부에서 받아옴
    public String helloMvc(@RequestParam("name") String name2, Model model){
        //파라미터로 받아온 name 을 넘겨준다, key = "name"
        model.addAttribute("name", name2);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //html의 body 태그를 의미하는게 아니라 http의 body부에 (return하는)데이터를 직접넣어주겠다는 의미
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; //"hello spring"
    }


    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello{//static 클래스로 만들면 클래스안에 클래스를 사용할 수 있다
        private String name;

        public String getName(){//꺼낼 때
            return name;
        }

        public void setName(String name){//넣을 때
            this.name = name;
        }
    }
}
