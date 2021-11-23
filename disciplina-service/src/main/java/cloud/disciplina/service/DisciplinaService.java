package cloud.disciplina.service;

import cloud.disciplina.client.AlunoClient;
import cloud.disciplina.dto.AlunoDTO;
import cloud.disciplina.dto.DisciplinaDTO;
import cloud.disciplina.model.Disciplina;
import cloud.disciplina.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository repository;

    @Autowired
    AlunoClient alunoClient;

    public List<String> getDisciplinas() {
        return repository.findAll()
                .stream().map(Disciplina::getNome).collect(Collectors.toList());
    }

    public DisciplinaDTO getDisciplina(@PathVariable Long id)  {

        Resources<AlunoDTO> alunos = alunoClient.getAllAlunos();
        List<String> alunosNomes = alunos.getContent().stream()
                .map(AlunoDTO::getNome).collect(Collectors.toList());

        Disciplina disciplina = repository.findOne(id);

        return DisciplinaDTO.builder()
                .id(disciplina.getId())
                .nome(disciplina.getNome())
                .cargaHoraria(disciplina.getCargaHoraria())
                .dataInicio(disciplina.getDataInicio())
                .alunosMatriculados(alunosNomes)
                .build();
    }
}
