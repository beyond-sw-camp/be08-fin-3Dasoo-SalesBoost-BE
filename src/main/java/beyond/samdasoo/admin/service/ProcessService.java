package beyond.samdasoo.admin.service;

import beyond.samdasoo.admin.dto.ProcessRequestDto;
import beyond.samdasoo.admin.dto.ProcessResponseDto;

import java.util.List;

public interface ProcessService {

    List<ProcessResponseDto> getAllProcesses();

    void addProcess(ProcessRequestDto request);

    void deleteProcessByNo(Long no);

    void updateProcessByNo(Long no, ProcessRequestDto request);
}
