package phoenix.partyquest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.partyquest.domain.token.AccessToken;

import java.util.Optional;

public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {
    public Optional<AccessToken> findAccessTokenByUserEmail(String userEmail);
}
