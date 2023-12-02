package com.gs.h2oapp.Utils;

/**
 * Created by Warley Lima 
 */
public enum Cedulas {

    C1(1),
    C2(2),
    C5(5),
    C10(10),
    C20(20),
    C50(50),
    C100(100);

    private final int nota;

    Cedulas(int nota) {
        this.nota = nota;
    }

    public int getNota() {
        return nota;
    }


}
