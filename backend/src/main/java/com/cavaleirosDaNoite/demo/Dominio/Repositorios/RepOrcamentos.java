package com.cavaleirosDaNoite.demo.Dominio.Repositorios;

import com.cavaleirosDaNoite.demo.Dominio.Entidades.Orcamento;
import com.cavaleirosDaNoite.demo.Persistencia.dao_interfaces.IRepOrcamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import java.util.List;

@Repository
public class RepOrcamentos {
    private final IRepOrcamento orcamentoRepository;

    // Injeção de dependência do IRepOrcamentos

    @Autowired
    public RepOrcamentos(IRepOrcamento orcamentoRepository) {
        this.orcamentoRepository = orcamentoRepository;
    }

    // Adicione outras consultas conforme necessário OrcamentoRepository
    public List<Orcamento> findAll() {
        return (List<Orcamento>) orcamentoRepository.findAll();
    }
    public Optional<Orcamento> findById(long id) { // Procurar pela Id

        return orcamentoRepository.findById(id);
    }

    public List<Orcamento> findByClienteId(long idCliente) {  // Procurar pela Id do cliente
        return orcamentoRepository.findByClienteId(idCliente);
    }


    private boolean isOrcamentoValido(Orcamento orcamento) {
        // Lógica para verificar a validade dos orçamentos com base na regra do enunciado
        LocalDate dataValidade = orcamento.getData().plusDays(getDiasValidade(orcamento));
        return dataValidade.isAfter(LocalDate.now());
    }

    private int getDiasValidade(Orcamento orcamento) {
        // Determinar os dias de validade com base nos períodos de baixa procura
        List<String> periodosBaixaProcura = Arrays.asList("07", "12", "01", "02");
        return periodosBaixaProcura.contains(orcamento.getData().getMonth()) ? 35 : 21;
    }

    public Orcamento save(Orcamento orcamento) {
        return orcamentoRepository.save(orcamento);
    }
}