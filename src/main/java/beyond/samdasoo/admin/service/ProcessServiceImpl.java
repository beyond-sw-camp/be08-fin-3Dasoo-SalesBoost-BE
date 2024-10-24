package beyond.samdasoo.admin.service;

import beyond.samdasoo.admin.dto.ProcessRequestDto;
import beyond.samdasoo.admin.dto.ProcessResponseDto;
import beyond.samdasoo.admin.entity.Process;
import beyond.samdasoo.admin.repository.ProcessRepository;
import beyond.samdasoo.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static beyond.samdasoo.common.response.BaseResponseStatus.PROCESS_ALREADY_EXIST;
import static beyond.samdasoo.common.response.BaseResponseStatus.PROCESS_NOT_EXIST;

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
                .expectedDuration(0)
                .description(request.getDescription())
                .build();


        processRepository.save(process);
    }

    @Override
    public void deleteProcessByNo(Long no) {
        Optional<Process> optionalProcess = processRepository.findById(no);

        if(optionalProcess.isEmpty()){
            throw new BaseException(PROCESS_NOT_EXIST);
        }

        processRepository.deleteById(no);
    }

    @Override
    public void updateProcessByNo(Long no, ProcessRequestDto request) {
        Optional<Process> optionalProcess = processRepository.findById(no);

        if(optionalProcess.isEmpty()){
            throw new BaseException(PROCESS_NOT_EXIST);
        }

        Process process = optionalProcess.get();

        if(request.getProcessName() != null){
            process.setProcessName(request.getProcessName());
        }

        if(request.getIsDefault() != null){
            process.setIsDefault(request.getIsDefault());
        }

        if(request.getExpectedDuration() != null){
            process.setExpectedDuration(request.getExpectedDuration());
        }

        if(request.getDescription() != null){
            process.setDescription(request.getDescription());
        }

        processRepository.save(process);
    }
}

