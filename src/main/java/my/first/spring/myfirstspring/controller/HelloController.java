package my.first.spring.myfirstspring.controller;


import my.first.spring.myfirstspring.type.HelloRequestParams;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    @GetMapping("hello")
    public  String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello";
    }

    @GetMapping("hello-api")
    @ResponseBody
    public String helloGetAPi(
            @RequestParam("name") String name,
            @RequestParam("age") int age)
    {
        return "hello " + name + " age " + age;
    }

    @GetMapping("hello-two-api")
    @ResponseBody
    public String helloGetTwoApi(
            // required옵션 처리
            @RequestParam(value = "name", required = false) String name,
            @RequestParam("age") int age)
    {
        return "hello " + name + " age " + age;
    }


    @GetMapping("hello-model-api")
    @ResponseBody
    public String helloGetModelApi(@ModelAttribute HelloRequestParams params)
    {
        return "hello " + params.getName() + " before Age " + params.getAge();
    }


    @GetMapping("hello-api/{address}")
    @ResponseBody
    public String helloUrlGetApi(
            @PathVariable("address") String address)
    {
        return "path  " + address;
    }
}
