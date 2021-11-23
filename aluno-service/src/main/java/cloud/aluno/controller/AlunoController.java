package cloud.aluno.controller;

import java.util.List;
import java.util.NoSuchElementException;

import cloud.aluno.dto.AlunoDTO;
import cloud.aluno.service.AlunoService;
import cloud.aluno.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

	@Autowired
	AlunoService alunoService;
	
	@PreAuthorize("#oauth2.isUser()")
	@GetMapping("/nomes")
	public  ResponseEntity<List<String>> getAlunos() {
		return new ResponseEntity<>(alunoService.getAlunos(), HttpStatus.OK);
	}

	@GetMapping("/{id}/dto")
	@SuppressWarnings("all")
	public ResponseEntity<?> getAluno(@PathVariable Long id) throws Exception {
		AlunoDTO alunoDTO;
		try {
			alunoDTO = alunoService.getAluno(id);
		} catch(NoSuchElementException ex) {
			return new ResponseEntity<>(new CustomErrorType(ex.getMessage()), HttpStatus.OK);
		}
		return new ResponseEntity<>(alunoDTO, HttpStatus.OK);

	}

	
}