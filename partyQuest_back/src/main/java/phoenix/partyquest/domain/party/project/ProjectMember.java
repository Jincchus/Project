package phoenix.partyquest.domain.party.project;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class ProjectMember {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

}
