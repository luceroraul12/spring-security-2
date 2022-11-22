package luceroraul.spring.security2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cards")
public class CardsController {
    @GetMapping
    public String cards(){
        return "this is a default message about cards.";
    }
}