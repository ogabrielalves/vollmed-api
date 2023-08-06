package med.voll.api.services;

import jakarta.validation.Valid;
import med.voll.api.dto.medico.DadosAtualizacaoMedico;
import med.voll.api.dto.medico.DadosCadastroMedico;
import med.voll.api.dto.medico.DadosDetalhamentoMedico;
import med.voll.api.dto.medico.DadosListagemMedico;
import med.voll.api.models.Medico;
import med.voll.api.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        try {
            repository.save(new Medico(dados));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        try {
            Page<DadosListagemMedico> listagemMedicoPage = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
            return ResponseEntity.ok(listagemMedicoPage);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<?> atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        try {
            Medico medico = repository.getReferenceById(dados.id());
            medico.atulizarInformacoes(dados);
            return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    public ResponseEntity<?> desativar(@PathVariable Long id) {
        try {
            Medico medico = repository.getReferenceById(id);
            medico.excluir();
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }
}
