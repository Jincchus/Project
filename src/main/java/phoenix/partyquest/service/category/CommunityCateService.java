package phoenix.partyquest.service.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import phoenix.partyquest.domain.community.CommunityCate;
import phoenix.partyquest.repository.category.CommunityCateRepository;
import phoenix.partyquest.repository.party.study.ExampleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityCateService {
    private final CommunityCateRepository communityCateRepository;

    public List<CommunityCate> getCommunityCate(){

        return communityCateRepository.findAll();
    }
}
