package cloud.disciplina.client;

import java.util.ArrayList;

import cloud.disciplina.client.AlunoClient;
import cloud.disciplina.dto.AlunoDTO;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

@Component
public class AlunoClientFallback implements AlunoClient {

	@Override
	public AlunoDTO getAluno(Long id) {
		return new AlunoDTO();
	}
	
	@Override
	public Resources<AlunoDTO> getAllAlunos() {				
		return new Resources<>(new ArrayList<>());
	}

}