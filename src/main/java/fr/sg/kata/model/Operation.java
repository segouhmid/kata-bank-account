package fr.sg.kata.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of="operationDate")

public class Operation implements Comparable<Operation>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long amount;
    private LocalDateTime operationDate;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Account account;

    @Override
    public int compareTo(Operation o)
    {
        return this.operationDate.compareTo(o.getOperationDate());
    }
}
