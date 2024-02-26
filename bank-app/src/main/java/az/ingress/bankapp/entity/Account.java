package az.ingress.bankapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "accounts")
@NamedEntityGraph(name = "account-user",
        attributeNodes = {
                @NamedAttributeNode(value = "user"),
                @NamedAttributeNode(value = "cards", subgraph = "card-benefits")
        }, subgraphs = {
        @NamedSubgraph(name = "card-benefits", attributeNodes = {
                @NamedAttributeNode("benefits")
        })
}
)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String accountNumber;
    private double balance;


    @OneToMany(mappedBy = "account")
    private List<Card> cards;
}
