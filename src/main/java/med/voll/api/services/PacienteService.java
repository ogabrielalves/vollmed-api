package med.voll.api.services;

import jakarta.validation.Valid;
import med.voll.api.dto.paciente.DadosAtualizacaoPaciente;
import med.voll.api.dto.paciente.DadosCadastroPaciente;
import med.voll.api.dto.paciente.DadosDetalhamentoPaciente;
import med.voll.api.dto.paciente.DadosListagemPaciente;
import med.voll.api.models.Paciente;
import med.voll.api.repositories.PacienteRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public ResponseEntity<?> cadastrar(DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
        Paciente novoPaciente = new Paciente(dados);
        repository.save(novoPaciente);
        URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(novoPaciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(novoPaciente));
    }

    public ResponseEntity<Page<DadosListagemPaciente>> listar(Pageable paginacao) {
        Page<DadosListagemPaciente> listagemPacientePage = repository.findAll(paginacao).map(DadosListagemPaciente::new);
        return ResponseEntity.ok(listagemPacientePage);
    }

    public ResponseEntity<?> atualizar(DadosAtualizacaoPaciente dados) {
        Paciente paciente = repository.getReferenceById(dados.id());
        paciente.atulizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    public ResponseEntity<?> deletar(Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> detalhar(Long id) {
        Paciente paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }
}
