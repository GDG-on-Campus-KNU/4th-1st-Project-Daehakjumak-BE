package gdg.daejuju.daehakjumak.jumak.ui;

import gdg.daejuju.daehakjumak.common.ui.Response;
import gdg.daejuju.daehakjumak.jumak.application.interfaces.JumakRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jumak")
public class JumakController {

    private final JumakRepository jumakRepository;

    @PatchMapping("/{jumakId}")
    public Response<Void> updateJumakName(@PathVariable Long jumakId, @RequestParam String jumakName) {
        jumakRepository.updateJumakName(jumakId, jumakName);
        return Response.ok(null);
    }

}
