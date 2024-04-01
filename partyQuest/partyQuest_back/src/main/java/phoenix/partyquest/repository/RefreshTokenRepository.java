package phoenix.partyquest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.partyquest.domain.token.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,String> {
    public Optional<RefreshToken> findRefreshTokenByUserEmail(String email);

    public Optional<RefreshToken> findRefreshTokenByRefreshToken(String token);
}
