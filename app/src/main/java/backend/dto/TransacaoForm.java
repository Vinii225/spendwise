package backend.dto;

import backend.model.Movimento;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TransacaoForm {
    private LocalDate data;
    private String descricao;
    private BigDecimal valor;
    private Movimento movimento;
    private Long categoriaId;
}
