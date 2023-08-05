package med.voll.api.dto.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.DadosEndereco;
import org.hibernate.validator.constraints.br.CPF;

public record DadosCadastroPaciente(
        @NotNull(message = "O nome não pode ser nulo.")
        @NotBlank(message = "O nome não pode estar em branco.")
        String nome,
        @NotBlank(message = "O telefone não pode estar em branco")
        String telefone,

        @NotBlank(message = "O email não pode estar em branco.")
        @Email(message = "Email inválido")
        String email,
        @CPF(message = "CPF inválido")
        String cpf,
        @NotNull(message = "Os dados de endereço não podem ser nulos.")
        @Valid
        DadosEndereco endereco
) {
}
