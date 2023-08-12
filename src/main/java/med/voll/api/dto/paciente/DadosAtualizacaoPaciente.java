package med.voll.api.dto.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.DadosEndereco;

public record DadosAtualizacaoPaciente(
        @NotNull(message = "O id não pode ser nulo.")
        Long id,
        String nome,
        String telefone,
        String email,
        DadosEndereco endereco
) {
}
