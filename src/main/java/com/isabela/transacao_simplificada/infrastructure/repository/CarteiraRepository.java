package com.isabela.transacao_simplificada.infrastructure.repository;

import com.isabela.transacao_simplificada.infrastructure.entity.Carteira;
import org.springframework.data.repository.CrudRepository;

public interface CarteiraRepository extends CrudRepository<Carteira, Long> {
}
