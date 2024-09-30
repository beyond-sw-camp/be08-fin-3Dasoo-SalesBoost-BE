package beyond.samdasoo.admin.service;

import beyond.samdasoo.admin.dto.SubProcessRequestDto;
import beyond.samdasoo.admin.dto.SubProcessResponseDto;
import beyond.samdasoo.admin.entity.SubProcess;

import java.util.List;

public interface SubProcessService {
    void addSubProcess(SubProcessRequestDto request);
    List<SubProcessResponseDto> getSubProcessesByProcessName(String processName);
}
