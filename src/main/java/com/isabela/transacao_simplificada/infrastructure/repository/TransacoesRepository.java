package com.isabela.transacao_simplificada.infrastructure.repository;

import com.isabela.transacao_simplificada.infrastructure.entity.Transacoes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacoesRepository extends JpaRepository<Transacoes, Long> {
}
