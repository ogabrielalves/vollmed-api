package med.voll.api.models;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.dto.medico.DadosAtualizacaoMedico;
import med.voll.api.dto.medico.DadosCadastroMedico;
import med.voll.api.enums.Especialidade;

@Entity(name = "Medico")
@Table(name = "medicos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    private String email;

    private String crm;

    private String telefone;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public void atulizarInformacoes(DadosAtualizacaoMedico dadosAtualizados) {
        if (dadosAtualizados.nome() != null) {
            this.nome = dadosAtualizados.nome();
        }

        if (dadosAtualizados.telefone() != null) {
            this.telefone = dadosAtualizados.telefone();
        }

        if(dadosAtualizados.endereco() != null) {
            this.endereco.atualizarEndereco(dadosAtualizados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
