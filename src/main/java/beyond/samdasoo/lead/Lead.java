package beyond.samdasoo.lead;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_LEAD")
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;
}
