package phoenix.partyquest.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class QueryDslConfig {
    @PersistenceContext
    public EntityManager em;
    @Bean
    public JPAQueryFactory query() {
        return new JPAQueryFactory(em);
    }
}
