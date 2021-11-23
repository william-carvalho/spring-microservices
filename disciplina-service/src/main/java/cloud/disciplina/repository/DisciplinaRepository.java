package cloud.disciplina.repository;

import cloud.disciplina.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "/disciplinas", collectionResourceRel = "disciplinas")
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

}