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

        process.setExpectedDuration(process.getExpectedDuration() + request.getExpectedDuration());

        SubProcess subProcess = SubProcess.builder()
                .process(process)
                .subProcessName(request.getSubProcessName())
                .progressStep(request.getProgressStep())
                .successRate(request.getSuccessRate())
                .description(request.getDescription())
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

    @Override
    public void deleteSubProcessByNo(Long no) {
        Optional<SubProcess> optionalSubProcess = subProcessRepository.findById(no);

        if(optionalSubProcess.isEmpty()){
            throw new BaseException(SUBPROCESS_NOT_EXIST);
        }

        subProcessRepository.deleteById(no);
    }

    @Override
    public void updateSubProcessByNo(Long no, SubProcessRequestDto request) {
        Optional<SubProcess> optionalSubProcess = subProcessRepository.findById(no);


        if(optionalSubProcess.isEmpty()){
            throw new BaseException(SUBPROCESS_NOT_EXIST);
        }


        SubProcess subProcess = optionalSubProcess.get();


        if(request.getProcessNo() != null){
            Optional<Process> optionalProcess = processRepository.findById(request.getProcessNo());

            if (optionalProcess.isEmpty()) {
                throw new BaseException(PROCESS_NOT_EXIST);
            }

            Process process = optionalProcess.get();

            subProcess.setProcess(process);
        }

        if(request.getSubProcessName() != null){
            subProcess.setSubProcessName(request.getSubProcessName());
        }

        if(request.getProgressStep() != null){
            subProcess.setProgressStep(request.getProgressStep());
        }

        if(request.getSuccessRate() != null){
            subProcess.setSuccessRate(request.getSuccessRate());
        }

        if(request.getDescription() != null){
            subProcess.setDescription(request.getDescription());
        }

        if(request.getExpectedDuration() != null){
            subProcess.setExpectedDuration(request.getExpectedDuration());
        }

        subProcessRepository.save(subProcess);
    }

    @Override
    public List<SubProcessResponseDto> getAllSubProcess() {
        List<SubProcess> subProcesses = subProcessRepository.findAll();

        return subProcesses.stream()
                .map(subProcess -> new SubProcessResponseDto(subProcess))
                .collect(Collectors.toList());
    }
}

