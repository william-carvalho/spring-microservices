package cloud.aluno.service;

import cloud.aluno.dto.AlunoDTO;
import cloud.aluno.model.Aluno;
import cloud.aluno.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository repository;

    @Autowired
    DisciplinaService disciplinaProxy;

    public List<String> getAlunos() {
        return repository.findAll()
                .stream().map(Aluno::getNome).collect(Collectors.toList());
    }

    public AlunoDTO getAluno(Long id) {

        List<String> nomesDisciplinas = disciplinaProxy.getNomesDisciplinas();

        Aluno aluno = repository.findOne(id);

        return cloud.aluno.dto.AlunoDTO.builder().id(aluno.getId())
                .nome(aluno.getNome())
                .matricula(aluno.getMatricula())
                .email(aluno.getEmail())
                .disciplinas(nomesDisciplinas)
                .build();
    }
}
