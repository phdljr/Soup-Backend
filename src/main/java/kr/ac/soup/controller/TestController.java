package kr.ac.soup.controller;

import kr.ac.soup.sercvice.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/")
    public String test() {
        return "test";
    }

    @GetMapping("/test")
    public String test1() {
        testService.addTest();
        return "add test";
    }
}
