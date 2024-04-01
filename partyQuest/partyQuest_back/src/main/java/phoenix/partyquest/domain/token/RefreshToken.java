package phoenix.partyquest.domain.token;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * refresh token을 저장하기 위한 엔티티
 * 추후에 redis로 전환할것
 */
@Getter
@NoArgsConstructor
@Entity
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userEmail;
    private String refreshToken;
    @Enumerated(EnumType.STRING)
    private JWTStatus status;
    @Builder
    public RefreshToken(String userEmail, String refreshToken) {
        this.userEmail = userEmail;
        this.refreshToken = refreshToken;
        status = JWTStatus.VALID;
    }
    public void invalidate() {
        status = JWTStatus.INVALID;
    }

    public boolean isValid() {
        return this.status == JWTStatus.VALID ? true : false;
    }
    public void reActivateAccessToken(String refreshToken) {
        this.refreshToken = refreshToken;
        status = JWTStatus.VALID;
    }
}
