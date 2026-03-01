package com.isabela.transacao_simplificada.infrastructure.clients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

public interface AutorizacaoClient {

@FeignClient(url="https://util.devi.tools/api/v2/authorize" , name ="autorizacao")

    public interface AutorizaoClient {
    @GetMapping

    }

}
