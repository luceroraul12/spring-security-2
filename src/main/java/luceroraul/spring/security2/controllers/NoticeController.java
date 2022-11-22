package luceroraul.spring.security2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notice")
public class NoticeController {
    @GetMapping
    public String notice(){
        return "this is a default message about notice.";
    }
}