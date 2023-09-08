package med.voll.api.services.validations.consulta;

import med.voll.api.dto.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {
    void validar(DadosAgendamentoConsulta dados);
}
