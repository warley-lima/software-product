package com.gs.h2oapp.Entity;

import java.io.Serializable;

/**
 * Created by Warley Lima 
 */
public class TipoDesconto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Short idTipoDesconto;
    private String nomeDesconto;

    public TipoDesconto() {
    }

    public TipoDesconto(Short idTipoDesconto) {
        this.idTipoDesconto = idTipoDesconto;
    }

    public TipoDesconto(Short idTipoDesconto, String nomeDesconto) {
        this.idTipoDesconto = idTipoDesconto;
        this.nomeDesconto = nomeDesconto;
    }

    public Short getIdTipoDesconto() {
        return idTipoDesconto;
    }

    public void setIdTipoDesconto(Short idTipoDesconto) {
        this.idTipoDesconto = idTipoDesconto;
    }

    public String getNomeDesconto() {
        return nomeDesconto;
    }

    public void setNomeDesconto(String nomeDesconto) {
        this.nomeDesconto = nomeDesconto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDesconto != null ? idTipoDesconto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoDesconto)) {
            return false;
        }
        TipoDesconto other = (TipoDesconto) object;
        if ((this.idTipoDesconto == null && other.idTipoDesconto != null) || (this.idTipoDesconto != null && !this.idTipoDesconto.equals(other.idTipoDesconto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.PDV.Model.Tipodesconto[ idTipoDesconto=" + idTipoDesconto + " ]";
    }
}
