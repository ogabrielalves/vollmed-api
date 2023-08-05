package med.voll.api.dto.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull(message = "O id não pode ser nulo.")
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {

}
