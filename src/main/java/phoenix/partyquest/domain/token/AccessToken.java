package phoenix.partyquest.domain.token;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @NoArgsConstructor
public class AccessToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userEmail;
    @Enumerated(EnumType.STRING)
    private JWTStatus status;

    @Builder
    public AccessToken(String userEmail) {
        this.userEmail = userEmail;
        this.status = JWTStatus.VALID;
    }
    public void invalidate() {
        status = JWTStatus.INVALID;
    }
    public boolean isValid() {
        return this.status == JWTStatus.VALID ? true : false;
    }

    public void reActivateAccessToken() {
        status = JWTStatus.VALID;
    }
}
