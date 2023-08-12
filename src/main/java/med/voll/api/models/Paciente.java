package med.voll.api.models;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.dto.paciente.DadosAtualizacaoPaciente;
import med.voll.api.dto.paciente.DadosCadastroPaciente;

@Entity(name = "Pacientes")
@Table(name = "pacientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    @Embedded
    private Endereco endereco;

    public Paciente(DadosCadastroPaciente dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atulizarInformacoes(DadosAtualizacaoPaciente dadosAtualizados) {
        if (dadosAtualizados.nome() != null) {
            this.nome = dadosAtualizados.nome();
        }

        if (dadosAtualizados.telefone() != null) {
            this.telefone = dadosAtualizados.telefone();
        }

        if(dadosAtualizados.email() != null) {
            this.email = dadosAtualizados.email();
        }

        if(dadosAtualizados.endereco() != null) {
            this.endereco.atualizarEndereco(dadosAtualizados.endereco());
        }
    }
}
