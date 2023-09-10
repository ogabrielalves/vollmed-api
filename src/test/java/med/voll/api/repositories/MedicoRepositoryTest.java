package med.voll.api.repositories;

import med.voll.api.dto.DadosEndereco;
import med.voll.api.dto.medico.DadosCadastroMedico;
import med.voll.api.dto.paciente.DadosCadastroPaciente;
import med.voll.api.enums.Especialidade;
import med.voll.api.models.Consulta;
import med.voll.api.models.Medico;
import med.voll.api.models.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Deveria null quando o médico cadastrado nao está disponivel na data")
    void escolherMedicoAleatorioLivreNaDataNull() {
        LocalDateTime segundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        Medico medico = cadastrarMedico("Luiz", "luiz@voll.med", "1234567", Especialidade.CARDIOLOGIA);
        Paciente paciente = cadastrarPaciente("Ana Beatriz", "ana.bea@email.com", "69396280020");
        cadastrarConsulta(medico, paciente, segundaAs10);

        Medico medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, segundaAs10);
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria devolver médico quando ele está disponivel na data")
    void escolherMedicoAleatorioLivreNaData() {
        LocalDateTime segundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        Medico medico = cadastrarMedico("Luiz", "luiz@voll.med", "1234567", Especialidade.CARDIOLOGIA);

        Medico medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, segundaAs10);
        assertThat(medicoLivre).isEqualTo(medico);
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        entityManager.persist(new Consulta(null, medico, paciente, data));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        Medico medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        entityManager.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        Paciente paciente = new Paciente(dadosPaciente(nome, email, cpf));
        entityManager.persist(paciente);
        return paciente;
    }

    private DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosCadastroMedico(
                nome,
                "1198906083",
                email,
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadastroPaciente dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadastroPaciente(
                nome,
                "1198907081",
                email,
                cpf,
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "Rua XPTO",
                "Bairro Novo",
                "05352425",
                "São Paulo",
                "SP",
                "Casa 5",
                "22"
        );
    }
}