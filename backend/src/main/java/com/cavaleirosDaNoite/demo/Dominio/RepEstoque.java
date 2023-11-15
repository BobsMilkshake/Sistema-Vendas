package com.cavaleirosDaNoite.demo.Dominio;
import com.cavaleirosDaNoite.demo.Persistencia.dao_interfaces.IRepEstoque;
import com.cavaleirosDaNoite.demo.Dominio.Entidades.Estoque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RepEstoque {
    private final IRepEstoque estoqueRepository;

    // Injeção de dependência do IRepEstoque

    @Autowired
    public RepEstoque(IRepEstoque estoqueRepository) {

        this.estoqueRepository = estoqueRepository;
    }


    public Optional<Estoque> findById(long id) { // Procurar pela Id

        return estoqueRepository.findById(id);
    }

    public List<Estoque> findAll() { //  Listar todos

        return (List<Estoque>) estoqueRepository.findAll();
    }

    public List<Estoque> findMinimumByQuantidadeAtualLessThan(int minQuant) { // Procurar quais estão abaixo da quantidade minima

        return estoqueRepository.findByQuantidadeAtualLessThan(minQuant);
    }

    // Adicione outras consultas conforme necessário
}