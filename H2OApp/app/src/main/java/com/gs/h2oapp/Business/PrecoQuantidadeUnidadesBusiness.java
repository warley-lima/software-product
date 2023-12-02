package com.gs.h2oapp.Business;

import com.gs.h2oapp.Entity.PrecoQuantidadeUnidades;

import java.util.List;

/**
 * Created by Warley Lima
 */
public interface PrecoQuantidadeUnidadesBusiness {
    List<PrecoQuantidadeUnidades> getLstPrecoQuantidadeUnidades(int idPr);
    int getTotalPrecoQuantidadeUnidades(int idPro);
    PrecoQuantidadeUnidades getPrecoCasadaProduto(int id);
}
