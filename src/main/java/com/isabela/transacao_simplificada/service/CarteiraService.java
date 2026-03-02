package com.isabela.transacao_simplificada.service;


import com.isabela.transacao_simplificada.infrastructure.entity.Carteira;

import com.isabela.transacao_simplificada.infrastructure.repository.CarteiraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarteiraService {

    private final CarteiraRepository repository;

    public void salvar(Carteira carteira ){
        repository.save(carteira);


    }


}
