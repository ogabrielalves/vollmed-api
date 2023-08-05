package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
        @NotBlank(message = "O logradouro não pode estar em branco.")
        String logradouro,

        @NotBlank(message = "O bairro não pode estar em branco.")
        String bairro,

        @NotBlank(message = "O CEP não pode estar em branco.")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 12345-678.")
        String cep,

        @NotBlank(message = "A cidade não pode estar em branco.")
        String cidade,

        @NotBlank(message = "O UF não pode estar em branco.")
        @Pattern(regexp = "[A-Z]{2}", message = "O UF deve conter exatamente duas letras maiúsculas.")
        String uf,

        String complemento,

        @NotBlank(message = "O número não pode estar em branco.")
        String numero
) {
}
