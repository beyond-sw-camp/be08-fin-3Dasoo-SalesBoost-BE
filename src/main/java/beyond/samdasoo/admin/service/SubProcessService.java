package beyond.samdasoo.admin.service;

import beyond.samdasoo.admin.dto.SubProcessRequestDto;
import beyond.samdasoo.admin.dto.SubProcessResponseDto;

import java.util.List;

public interface SubProcessService {
    void addSubProcess(SubProcessRequestDto request);
    List<SubProcessResponseDto> getSubProcessesByProcessName(String processName);

    void deleteSubProcessByNo(Long no);

    void updateSubProcessByNo(Long no, SubProcessRequestDto request);
}
