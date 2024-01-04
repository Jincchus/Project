package phoenix.partyquest.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import phoenix.partyquest.domain.community.CommunityCate;
import phoenix.partyquest.service.category.CommunityCateService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/community")
@RequiredArgsConstructor
public class CommunityCateController {
    private final CommunityCateService communityCateService;

    @GetMapping("/communityCate")
    public List<CommunityCate> communityCate() { return communityCateService.getCommunityCate(); }
}
