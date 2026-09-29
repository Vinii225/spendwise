package backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import backend.model.Movimento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransacaoForm {

    private LocalDate data;
    private String descricao;
    private BigDecimal valor;
    private Movimento movimento;
    private Long categoriaId;
}