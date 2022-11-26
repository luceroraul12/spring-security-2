package luceroraul.spring.security2.controllers;

import luceroraul.spring.security2.entities.Customer;
import luceroraul.spring.security2.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogginController {

    @Autowired
    CustomerRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        ResponseEntity<String> response =  null;
        try{
            //hash encrypt password
            String hashPsw = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPsw);

            Customer userCreated = repository.save(customer);

            if (userCreated.getId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("the user has been created");
            }
        } catch(Exception e) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("the user has not been created "+ e.getMessage());
        }
        return response;
    }
}