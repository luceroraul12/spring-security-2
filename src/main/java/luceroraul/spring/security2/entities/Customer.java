package luceroraul.spring.security2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name ="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pwd;

    private String role;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Authority> authorities;
}
