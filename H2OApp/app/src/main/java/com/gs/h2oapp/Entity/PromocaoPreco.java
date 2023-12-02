package com.gs.h2oapp.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Warley Lima 
 */
public class PromocaoPreco implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idPromocao;
    private Date dataInicio;
    private Date dataFim;
    private BigDecimal valorDesconto;
    private String nomePromocao;
    private TipoDesconto fkTipoDesconto;

    public PromocaoPreco() {
    }

    public PromocaoPreco(Integer idPromocao) {
        this.idPromocao = idPromocao;
    }

    public PromocaoPreco(Integer idPromocao, Date dataInicio, Date dataFim, BigDecimal valorDesconto, String nomePromocao) {
        this.idPromocao = idPromocao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorDesconto = valorDesconto;
        this.nomePromocao = nomePromocao;
    }

    public Integer getIdPromocao() {
        return idPromocao;
    }

    public void setIdPromocao(Integer idPromocao) {
        this.idPromocao = idPromocao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public String getNomePromocao() {
        return nomePromocao;
    }

    public void setNomePromocao(String nomePromocao) {
        this.nomePromocao = nomePromocao;
    }

    public TipoDesconto getFkTipoDesconto() {
        return fkTipoDesconto;
    }

    public void setFkTipoDesconto(TipoDesconto fkTipoDesconto) {
        this.fkTipoDesconto = fkTipoDesconto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPromocao != null ? idPromocao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PromocaoPreco)) {
            return false;
        }
        PromocaoPreco other = (PromocaoPreco) object;
        if ((this.idPromocao == null && other.idPromocao != null) || (this.idPromocao != null && !this.idPromocao.equals(other.idPromocao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.PDV.Model.PromocaoPreco[ idPromocao=" + idPromocao + " ]";
    }
}
