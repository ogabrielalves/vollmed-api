package med.voll.api.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAutenticacao(
        @NotBlank(message = "O login deve ser preenchido.")
        String login,
        @NotBlank(message = "A senha deve ser preenchida.")
        String senha
) {
}
