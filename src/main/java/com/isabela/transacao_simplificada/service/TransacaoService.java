package com.isabela.transacao_simplificada.service;
import com.isabela.transacao_simplificada.controller.TransacaoDTO;
import com.isabela.transacao_simplificada.infrastructure.entity.Carteira;
import com.isabela.transacao_simplificada.infrastructure.entity.TipoUsuario;
import com.isabela.transacao_simplificada.infrastructure.entity.Transacoes;
import com.isabela.transacao_simplificada.infrastructure.entity.Usuario;
import com.isabela.transacao_simplificada.infrastructure.exceptions.BadRequestException;
import com.isabela.transacao_simplificada.infrastructure.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor

public class TransacaoService {
    private final UsuarioService usuarioService;
    private final AutorizacaoService autorizacaoService;
    private final CarteiraService carteiraService;
    private final TransacaoRepository repository;
    private final NotificacaoService notificacaoService;

    @Transactional
    public void transferirValores(TransacaoDTO transacaoDto){
        Usuario pagador = usuarioService.buscarUsuario(transacaoDto.payer());
        Usuario recebedor = usuarioService.buscarUsuario(transacaoDto.payee());

        validarPagadorLogista(pagador);
        validarSaldoUsuario(pagador,transacaoDto.value());
        validarTransferencia();

        pagador.getCarteira().setSaldo(pagador.getCarteira().getSaldo().subtract(transacaoDto.value()));
        atualizarSaldoCarteira(pagador.getCarteira());
        recebedor.getCarteira().setSaldo(pagador.getCarteira().getSaldo().add(transacaoDto.value()));
        atualizarSaldoCarteira(recebedor.getCarteira());

        Transacoes transacoes = Transacoes.builder()
                .valor(transacaoDto.value())
                .pagador(pagador)
                .recebedor(recebedor)
                .build();
        repository.save(transacoes);
        enviarNotificacao();

    }

    private void validarPagadorLogista(Usuario usuario) {
        try {
            if (usuario.getTipoUsuario().equals(TipoUsuario.LOJISTA)) {
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
    private void validarTransferencia() {
        try{
            if (!autorizacaoService.validarTransferencia()){
                throw new IllegalArgumentException("Transação nao autorizada pela api ");
            }

        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    private void atualizarSaldoCarteira(Carteira carteira) {
        carteiraService.salvar(carteira);
    }
    private void enviarNotificacao() {
        try {
            notificacaoService.enviarNotificacao();
        } catch (HttpClientErrorException e) {
            throw new BadRequestException("Erro ao enviar notificacao");
        }
    }

}

