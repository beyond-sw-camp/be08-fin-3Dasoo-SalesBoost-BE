package beyond.samdasoo.admin.service;

import beyond.samdasoo.admin.dto.ProcessRequestDto;
import beyond.samdasoo.admin.dto.ProcessResponseDto;
import beyond.samdasoo.admin.entity.Process;
import beyond.samdasoo.admin.repository.ProcessRepository;
import beyond.samdasoo.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static beyond.samdasoo.common.response.BaseResponseStatus.PROCESS_ALREADY_EXIST;

@Service
@RequiredArgsConstructor
public class ProcessServiceImpl implements ProcessService {
    private final ProcessRepository processRepository;

    @Override
    public List<ProcessResponseDto> getAllProcesses() {

        List<Process> processes = processRepository.findAll();

        return processes.stream()
                .map(process -> new ProcessResponseDto(process))
                .collect(Collectors.toList());
    }

    @Override
    public void addProcess(ProcessRequestDto request) {
        boolean exists = processRepository.existsByProcessName(request.getProcessName());

        if(exists){
            throw new BaseException(PROCESS_ALREADY_EXIST);
        }

        Process process = Process.builder()
                .processName(request.getProcessName())
                .isDefault(request.getIsDefault())
                .expectedDuration(request.getExpectedDuration())
                .description(request.getDescription())
                .build();


        processRepository.save(process);
    }
}

