package gdg.daejuju.daehakjumak.waiting.ui;

import gdg.daejuju.daehakjumak.common.ui.Response;
import gdg.daejuju.daehakjumak.waiting.application.WaitingService;
import gdg.daejuju.daehakjumak.waiting.application.dto.CreateWaitingRequestDto;
import gdg.daejuju.daehakjumak.waiting.application.dto.GetWaitingResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/waiting")
public class WaitingController {

    private final WaitingService waitingService;

    @PatchMapping("/{waitingId}/nop")
    public Response<Void> updateNop(@PathVariable("waitingId") Long waitingId, @RequestParam("nop") int nop) {
        waitingService.updateNop(waitingId,nop);
        return Response.ok(null);
    }

    @PatchMapping("/{waitingId}/phoneNum")
    public Response<Void> updatePhoneNum(@PathVariable("waitingId") Long waitingId, @RequestParam("phoneNum") String phoneNum) {
        waitingService.updatePhoneNum(waitingId,phoneNum);
        return Response.ok(null);
    }

    @PostMapping("/create")
    public Response<Long> createWaiting(@RequestBody CreateWaitingRequestDto dto) {
        Long id = waitingService.createWaiting(dto);
        return Response.ok(id);
    }

    @DeleteMapping("/{waitingId}")
    public Response<Void> deleteWaiting(@PathVariable("waitingId") Long waitingId) {
        waitingService.deleteWaiting(waitingId);
        return Response.ok(null);
    }

    @GetMapping
    public Response<List<GetWaitingResponseDto>> getWaitingList(@RequestParam("jumakId") Long jumakId) {
        List<GetWaitingResponseDto> waitingList = waitingService.getWaitingList(jumakId);
        return Response.ok(waitingList);
    }



}
