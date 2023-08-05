package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.enums.Especialidade;

public record DadosCadastroMedico(
        @NotNull(message = "O nome não pode ser nulo.")
        @NotBlank(message = "O nome não pode estar em branco.")
        String nome,

        @NotBlank(message = "O telefone não pode estar em branco")
        String telefone,

        @NotBlank(message = "O email não pode estar em branco.")
        @Email(message = "Email inválido")
        String email,

        @NotBlank(message = "O CRM não pode estar em branco.")
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull(message = "A especialidade não pode ser nula.")
        Especialidade especialidade,

        @NotNull(message = "Os dados de endereço não podem ser nulos.")
        @Valid
        DadosEndereco endereco
) {
}
