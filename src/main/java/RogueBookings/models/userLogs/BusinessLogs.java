package RogueBookings.models.userLogs;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "business_logs")
public class BusinessLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "business_logs_seq")
    @SequenceGenerator(name = "business_logs_seq")
    @Column(name = "id", nullable = false)
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}