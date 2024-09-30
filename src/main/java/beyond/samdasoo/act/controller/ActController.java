package beyond.samdasoo.act.controller;

import beyond.samdasoo.act.dto.ActRequestDto;
import beyond.samdasoo.act.dto.ActResponseDto;
import beyond.samdasoo.act.service.ActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/act")
public class ActController {
    private final ActService actService;

    @Autowired
    public ActController(ActService actService) { this.actService = actService; }


    @PostMapping
    public ResponseEntity<ActResponseDto> createAct(@RequestBody ActRequestDto actRequestDto) {
        ActResponseDto responseDto = actService.createAct(actRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{no}")
    public ResponseEntity<ActResponseDto> getActById(@PathVariable("no") Long no) {
        ActResponseDto responseDto = actService.getActById(no);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{no}")
    public ResponseEntity<Void> patchUpdateAct(@PathVariable("no") Long no, @RequestBody ActRequestDto actUpdateDto) {
        actService.updateAct(no, actUpdateDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{no}")
    public ResponseEntity<Void> deleteAct(@PathVariable("no") Long no) {
        actService.deleteAct(no);
        return ResponseEntity.noContent().build();
    }
}