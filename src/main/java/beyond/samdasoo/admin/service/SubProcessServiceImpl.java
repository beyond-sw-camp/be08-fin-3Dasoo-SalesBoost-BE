package beyond.samdasoo.admin.service;

import beyond.samdasoo.admin.dto.SubProcessRequestDto;
import beyond.samdasoo.admin.dto.SubProcessResponseDto;
import beyond.samdasoo.admin.entity.Process;
import beyond.samdasoo.admin.entity.Product;
import beyond.samdasoo.admin.entity.SubProcess;
import beyond.samdasoo.admin.repository.ProcessRepository;
import beyond.samdasoo.admin.repository.SubProcessRepository;
import beyond.samdasoo.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static beyond.samdasoo.common.response.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class SubProcessServiceImpl implements SubProcessService{
    private final SubProcessRepository subProcessRepository;
    private final ProcessRepository processRepository;


    @Override
    public void addSubProcess(SubProcessRequestDto request) {
        boolean exists = subProcessRepository.existsBySubProcessName(request.getSubProcessName());
        Optional<Process> optionalProcess = processRepository.findById(request.getProcessNo());

        if (optionalProcess.isEmpty()) {
            throw new BaseException(PROCESS_NOT_EXIST);
        }

        Process process = optionalProcess.get();

        if(exists){
            throw new BaseException(SUBPROCESS_ALREADY_EXIST);
        }

        SubProcess subProcess = SubProcess.builder()
                .process(process)
                .subProcessName(request.getSubProcessName())
                .progressStep(request.getProgressStep())
                .successRate(request.getSuccessRate())
                .action(request.getAction())
                .expectedDuration(request.getExpectedDuration())
                .build();

        subProcessRepository.save(subProcess);
    }

    @Override
    public List<SubProcessResponseDto> getSubProcessesByProcessName(String processName) {
        boolean exists = processRepository.existsByProcessName(processName);

        if(!exists){
            throw new BaseException(PROCESS_NOT_EXIST);
        }

        List<SubProcess> subProcesses = subProcessRepository.findByProcess_ProcessName(processName);

        return subProcesses.stream()
                .map(subProcess -> new SubProcessResponseDto(subProcess))
                .collect(Collectors.toList());
    }
}

