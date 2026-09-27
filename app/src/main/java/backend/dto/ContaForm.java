package backend.dto;

import backend.model.TipoConta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
public class ContaForm {

    private Long correntistaId;
    private String numero;
    private String descricao;
    private TipoConta tipo;
    private Integer diaFechamento;
}
