package cloud.aluno.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DisciplinaDTO {

	Long id;
	String nome;
	Integer cargaHoraria;
	Date dataInicio;
	List<String> alunosMatriculados;

}
