package luceroraul.spring.security2.configs;

import luceroraul.spring.security2.entities.Authority;
import luceroraul.spring.security2.entities.Customer;
import luceroraul.spring.security2.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EazyBankUsernamePwdAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        List<Customer> customer = customerRepository.findByEmail(username);

        if (customer.size() > 0){
            if (passwordEncoder.matches(pwd, customer.get(0).getPwd())){
                return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAuthorities(customer.get(0).getAuthorities()));
            } else {
                throw new BadCredentialsException("Invalid password, please try with another one.");
            }
        } else {
            throw new BadCredentialsException("Unregistred username, please try with another one.");
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities){
        return authorities.stream()
                .map(Authority::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
