package com.isabela.transacao_simplificada.service;

import com.isabela.transacao_simplificada.controller.TransacaoDTO;
import com.isabela.transacao_simplificada.infrastructure.entity.TipoUsuario;
import com.isabela.transacao_simplificada.infrastructure.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor

public class TransferenciaService {
    private final UsuarioService usuarioService;


    public void transferirValores(TransacaoDTO transacaoDto){
        Usuario pagador = usuarioService.buscarUsuario(transacaoDto.payer());
        Usuario recebedor = usuarioService.buscarUsuario(transacaoDto.payee());

        validarPagadorLogista(pagador);
        validarSaldoUsuario(pagador,transacaoDto.value());
    }

    private void validarPagadorLogista(Usuario usuario) {
        try {
            if (usuario.getTipoUsuario().equals(TipoUsuario.LOGISTA)) {
                throw new IllegalArgumentException("Transação nao autorizada para esse tipo de usuario");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    private void validarSaldoUsuario(Usuario usuario , BigDecimal valor) {
        try{
            if (usuario.getCarteira().getSaldo().compareTo(valor) < 0){
                throw new IllegalArgumentException("Transação nao autorizada, saldo insuficiente");
            }

        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    }

