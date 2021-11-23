package cloud.aluno.repository;

import cloud.aluno.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "/alunos", collectionResourceRel = "alunos")
public interface AlunoRepository extends JpaRepository<Aluno, Long>{
	
}