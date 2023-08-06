package med.voll.api.services;

import med.voll.api.dto.medico.DadosAtualizacaoMedico;
import med.voll.api.dto.medico.DadosCadastroMedico;
import med.voll.api.dto.medico.DadosDetalhamentoMedico;
import med.voll.api.dto.medico.DadosListagemMedico;
import med.voll.api.models.Medico;
import med.voll.api.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    public ResponseEntity<?> cadastrar(DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        Medico novoMedico = new Medico(dados);
        repository.save(novoMedico);
        URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(novoMedico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(novoMedico));
    }

    public ResponseEntity<Page<DadosListagemMedico>> listar(Pageable paginacao) {
        Page<DadosListagemMedico> listagemMedicoPage = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(listagemMedicoPage);
    }

    public ResponseEntity<?> atualizar(DadosAtualizacaoMedico dados) {
        Medico medico = repository.getReferenceById(dados.id());
        medico.atulizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    public ResponseEntity<?> deletar(Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> desativar(Long id) {
        Medico medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> detalhar(Long id) {
        Medico medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
}
