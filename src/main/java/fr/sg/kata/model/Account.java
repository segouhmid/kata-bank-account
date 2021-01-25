package fr.sg.kata.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
@Builder
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @Column(unique=true, nullable = false)
    private final String accountNumber;
    @Column
    private Long balance;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonBackReference
    private final Client client;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Operation> operations;
}
