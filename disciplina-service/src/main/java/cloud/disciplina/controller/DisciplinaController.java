package cloud.disciplina.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import cloud.disciplina.client.AlunoClient;
import cloud.disciplina.dto.AlunoDTO;
import cloud.disciplina.dto.DisciplinaDTO;
import cloud.disciplina.model.Disciplina;
import cloud.disciplina.repository.DisciplinaRepository;
import cloud.disciplina.service.DisciplinaService;
import cloud.disciplina.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

	@Autowired
	DisciplinaService repository;
	
	@Autowired
	AlunoClient alunoClient;
	
	@PreAuthorize("hasRole('MANAGER')")
	@GetMapping("/nomes")
	public ResponseEntity<List<String>> getDisciplinas() {
		return new ResponseEntity<>(repository.getDisciplinas(), HttpStatus.OK);

	}
	
	@GetMapping("/{id}/dto")
	@SuppressWarnings("all")
	public ResponseEntity<?> getDisciplina(@PathVariable Long id) throws Exception {

		DisciplinaDTO disciplinaDTO;
		try {
			disciplinaDTO = repository.getDisciplina(id);
		} catch(NoSuchElementException ex) {
			return new ResponseEntity<>(new CustomErrorType(ex.getMessage()), HttpStatus.OK);
		}
		return new ResponseEntity<>(disciplinaDTO, HttpStatus.OK);
	}		
	
}