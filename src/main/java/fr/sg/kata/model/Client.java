package fr.sg.kata.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(force = true)
@EqualsAndHashCode
@Value
@Builder
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private final String firstName;
    private final String lastName;
    @OneToOne(mappedBy = "client")
    @JsonManagedReference
    private final Account account;
}
