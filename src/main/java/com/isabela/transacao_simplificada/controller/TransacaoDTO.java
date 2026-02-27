package com.isabela.transacao_simplificada.controller;

import java.math.BigDecimal;

public record TransacaoDTO(BigDecimal value , Long payer ,Long pay , Long payee) {
}
