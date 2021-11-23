package cloud.aluno.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cloud.aluno.client.DisciplinaClient;
import cloud.aluno.dto.DisciplinaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaClient disciplinaClient;

    @HystrixCommand(fallbackMethod = "getNomesDisciplinasFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "requestCache.enabled", value = "false")
            })
    public List<String> getNomesDisciplinas() {
        Resources<DisciplinaDTO> disciplinas = disciplinaClient.getAllDisciplinas();
        return disciplinas.getContent().stream()
                .map(DisciplinaDTO::getNome).collect(Collectors.toList());
    }

    List<String> getNomesDisciplinasFallback() {
        return new ArrayList<>();
    }

}