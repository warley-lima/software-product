package com.gs.h2oapp.Utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;
import com.gs.h2oapp.Entity.Adress;
import com.gs.h2oapp.Entity.Company;
import com.gs.h2oapp.Entity.Liters;
import com.gs.h2oapp.Entity.Order;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Warley Lima 
 */
public class Utils {

    public static String getNameLogo(int idBreand) {
        String nameLogo;
        switch (idBreand) {
            case 1:
                nameLogo = "logo_cristal";
                break;
            case 2:
                nameLogo = "logo_ibira";
                break;
            case 3:
                nameLogo = "logo_bioleve";
                break;
            case 4:
                nameLogo = "h2o_4";
                break;
            case 5:
                nameLogo = "logo_nativa";
                break;
            case 6:
                nameLogo = "logo_ibira";
                break;
            default:
                nameLogo = "h2o_4";
                break;
        }
        return nameLogo;
    }

    public static LatLng getLocationFromAddress(Context context, String strAddress) {
        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;
        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            } else {
                Address location = address.get(0);
                p1 = new LatLng(location.getLatitude(), location.getLongitude());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return p1;
    }

    private static String completeValueDecimal(String valorCompleto){
        if(valorCompleto.indexOf(".") > -1) {
            String decimal = valorCompleto.substring(valorCompleto.indexOf(".") + 1);
            if (decimal.length() == 1) {
                return ",".concat(decimal).concat("0");
            } else {
                return ",".concat(decimal);
            }
        } else {
            return ",00";
        }

    }

    public static String formatToCurrency(BigDecimal value) {
        if (null != value && !BigDecimal.ZERO.equals(value)) {
            /*DecimalFormat myFormatter = new DecimalFormat("###.###,##");
            myFormatter.applyPattern("");*/

          //  System.out.println("-----------***TESTE VALOR PRODS-------------------->" + value.toString());

            String valorCompleto = value.toString();
            String valorDecimais = completeValueDecimal(valorCompleto);//",".concat(valorCompleto.substring(valorCompleto.indexOf(".") + 1));
            String valorRetorno;

            if( valorCompleto.indexOf(".") > -1)
            {
                switch (valorCompleto.indexOf(".")) {
                    case 4:
                        //2000.90
                        valorRetorno = valorCompleto.substring(0, 1);
                        valorRetorno += ".".concat(valorCompleto.substring(1, valorCompleto.indexOf(".")));
                        break;
                    case 5:
                        valorRetorno = valorCompleto.substring(0, 2);
                        valorRetorno += ".".concat(valorCompleto.substring(2, valorCompleto.indexOf(".")));
                        break;
                    case 6:
                        valorRetorno = valorCompleto.substring(0, 3);
                        valorRetorno += ".".concat(valorCompleto.substring(3, valorCompleto.indexOf(".")));
                        break;
                    case 7:
                        valorRetorno = valorCompleto.substring(0, 1);
                        //7.845.741.57
                        valorRetorno += ".".concat(valorCompleto.substring(1, 4));
                        valorRetorno += ".".concat(valorCompleto.substring(4, valorCompleto.indexOf(".")));
                        break;
                    case 8:
                        //78.945.687.45
                        valorRetorno = valorCompleto.substring(0, 2);
                        valorRetorno += ".".concat(valorCompleto.substring(2, 5));
                        valorRetorno += ".".concat(valorCompleto.substring(5, valorCompleto.indexOf(".")));
                        break;
                /*case 9 :
                    valorRetorno = valorCompleto.substring(0 , 2);
                    valorRetorno += ".".concat(valorCompleto.substring(2, valorCompleto.indexOf(".")));
                    break;*/
                    default:
                        if(valorCompleto.length() == 1){
                            valorRetorno = valorCompleto;
                        } else{
                            valorRetorno = valorCompleto.substring(0, valorCompleto.indexOf("."));
                        }
                        break;
                }

                valorRetorno = valorRetorno.concat(valorDecimais);
            }
             else{
                 valorRetorno = valorCompleto.concat(valorDecimais);
            }

            return  valorRetorno;


            //String valorRetorno = valorCompleto.substring(0, valorCompleto.indexOf("."));
            //DecimalFormat df = (DecimalFormat) DecimalFormat.getCurrencyInstance();
           /* DecimalFormat df = new DecimalFormat();
            DecimalFormatSymbols dfs = new DecimalFormatSymbols();
            dfs.setCurrencySymbol("pt-BR");
            dfs.setMonetaryDecimalSeparator(',');
            dfs.setGroupingSeparator('.');
            df.setDecimalFormatSymbols(dfs);
            df.setMinimumFractionDigits(2);*/


            //return df.format(value);
          //  return valorRetorno.concat(valorDecimais);
            // return valorCompleto.replace(".", ",");
            /*NumberFormat format = NumberFormat.getCurrencyInstance("pt","br");
            String currency = format.format(value);
            System.out.println("Currency in Canada : " + currency);*/
        } else {
            return "0,00";
        }
    }

    public static String formatToCurrency(String value) {
        if (null != value && !"0.00".equals(value)) {
            String valorDecimais = ",".concat(value.substring(value.indexOf(".") + 1));
            String valorRetorno;
            switch (value.indexOf(".")) {
                case 4:
                    //2000.90
                    valorRetorno = value.substring(0, 1);
                    valorRetorno += ".".concat(value.substring(1, value.indexOf(".")));
                    break;
                case 5:
                    valorRetorno = value.substring(0, 2);
                    valorRetorno += ".".concat(value.substring(2, value.indexOf(".")));
                    break;
                case 6:
                    valorRetorno = value.substring(0, 3);
                    valorRetorno += ".".concat(value.substring(3, value.indexOf(".")));
                    break;
                case 7:
                    //7.845.741.57
                    valorRetorno = value.substring(0, 1);
                    valorRetorno += ".".concat(value.substring(1, 4));
                    valorRetorno += ".".concat(value.substring(4, value.indexOf(".")));
                    break;
                case 8:
                    //78.945.687.45
                    valorRetorno = value.substring(0, 2);
                    valorRetorno += ".".concat(value.substring(2, 5));
                    valorRetorno += ".".concat(value.substring(5, value.indexOf(".")));
                    break;
                default:
                    valorRetorno = value.substring(0, value.indexOf("."));
                    break;
            }
            return valorRetorno.concat(valorDecimais);
        } else {
            return "0,00";
        }
    }

    public static Adress formatAdress(String myAdress) {
        Adress adressRet = null;
        try {
            adressRet = new Adress();
            String adress;
            String num = null;
            String bairro;
            String cidade;
            String uf;
            String temp;

            adress = myAdress.substring(0, myAdress.indexOf("-") - 1);
            if (adress.contains(",")) {
                num = adress.substring(adress.indexOf(",") + 2);
                adress = adress.substring(0, adress.indexOf(","));
            }
            temp = myAdress.substring(myAdress.indexOf("-") + 2);
            bairro = temp.substring(0, temp.indexOf(","));
            cidade = temp.substring(temp.indexOf(",") + 2, temp.indexOf("-") - 1);
            temp = temp.substring(temp.indexOf("-") - 1);
            uf = temp.substring(temp.indexOf("-") + 2, temp.indexOf(","));
            adressRet.setAdress(adress);
            adressRet.setNumber(num);
            adressRet.setBairro(bairro);
            adressRet.setCity(cidade);
            adressRet.setUf(uf);

        } catch (Exception e) {
            System.out.println("Exception on Convert Adress---->" + e);
        }
        return adressRet;
    }

    public static String[] getCedulasTroco(int total) {
        int[] trocoPara = new int[10];
        String[] retornoFormatado;
        //int tot = total.intValue();
        //int notas[] = {100, 50, 20, 10, 5, 2, 1};
        //Calculando as possibilidades de troco com no máximo 6 opções, ordenado do menor para o maior
        //Começando com Cedula de R$2,00; total R$12,50
        int vlPara1 = getQuantCelula(total, Cedulas.C1.getNota());
        int vlPara2 = getQuantCelula(total, Cedulas.C2.getNota());
        int vlPara5 = getQuantCelula(total, Cedulas.C5.getNota());
        int vlPara10 = getQuantCelula(total, Cedulas.C10.getNota());
        int vlPara20 = getQuantCelula(total, Cedulas.C20.getNota());
        int vlPara50 = getQuantCelula(total, Cedulas.C50.getNota());
        int vlPara100 = getQuantCelula(total, Cedulas.C100.getNota());
        int nextPos = 0;

        if (vlPara1 > 0 ) {
            vlPara1 = vlPara1 * Cedulas.C1.getNota();
            if(vlPara1 > total) {
                trocoPara[nextPos] = vlPara1;
                nextPos++;
            }
        }


        if (vlPara2 > 0 ) {
            vlPara2 = vlPara2 * Cedulas.C2.getNota();
            if(vlPara2 > total && vlPara2 != vlPara1) {
                trocoPara[nextPos] = vlPara2;
                nextPos++;
            }
        }
        if (vlPara5 > 0) {
            vlPara5 = vlPara5 * Cedulas.C5.getNota();
            /*trocoPara[nextPos] = vlPara5;
            nextPos++;*/
            if (vlPara5 > total && vlPara5 != vlPara1 && vlPara2 != vlPara5){
                trocoPara[nextPos] = vlPara5;
                nextPos++;
            }
        }
        if (vlPara10 > 0) {
            vlPara10 = vlPara10 * Cedulas.C10.getNota();
            if (vlPara10 > total && vlPara10 != vlPara1 && vlPara10 != vlPara2 && vlPara10 != vlPara5){
                trocoPara[nextPos] = vlPara10;
                nextPos++;
            }
        }
        if (vlPara20 > 0) {
            vlPara20 = vlPara20 * Cedulas.C20.getNota();
            if (vlPara20 > total && vlPara20 != vlPara1 && vlPara20 != vlPara2 && vlPara20 != vlPara5 && vlPara20 != vlPara10){
                trocoPara[nextPos] = vlPara20;
                nextPos++;
            }
        }
        if (vlPara50 > 0) {
            vlPara50 = vlPara50 * Cedulas.C50.getNota();
            if (vlPara50 > total && vlPara50 != vlPara1 && vlPara50 != vlPara2 && vlPara50 != vlPara5 && vlPara50 != vlPara10 && vlPara50 != vlPara20&& vlPara50 != vlPara100){
                trocoPara[nextPos] = vlPara50;
                nextPos++;
            }
        }
        if (vlPara100 > 0) {
            vlPara100 = vlPara100 * Cedulas.C100.getNota();
            if (vlPara100 > total && vlPara100 != vlPara1 && vlPara100 != vlPara2 && vlPara100 != vlPara5 && vlPara100 != vlPara10 && vlPara100 != vlPara20 && vlPara100 != vlPara50){
                trocoPara[nextPos] = vlPara100;
                nextPos++;
            }
        }

        retornoFormatado = new String[nextPos];
        Arrays.sort(trocoPara);
       // int[] trocoRetorno = new int[nextPos];
        int pos = 0;
        for (int aTrocoPara : trocoPara) {
            if (0 != aTrocoPara) {
                retornoFormatado[pos] = "R$" + aTrocoPara + ",00";
                System.out.println("Troco para: " + retornoFormatado[pos]);
                //trocoRetorno[pos] = trocoPara[i];
                pos++;
            }
        }
        //int pos = 0;
       /* for (int i = 0; i < trocoPara.length; i++){

            if (0 != trocoPara[i]){
                for (int z = 0; z < trocoPega.length; z++){
                    if (0 != trocoPega[z]){
                        if (trocoPara[i] <= trocoPega[z]){
                            trocoRetorno[i] = trocoPara[i];
                           // z = trocoPara.length;
                        }else{
                            trocoRetorno[i] = trocoPega[z];
                            trocoPega[z] = 0;
                            z = trocoPega.length;
                        }
                    }
                }
            }
        }*/
       /* for (int i = 0; i < nextPos; i++){
            retornoFormatado[i] = "R$"+ trocoRetorno[i]+",00";
            System.out.println("Troco para: " + retornoFormatado[i]);
        }*/

      /*  if (vlPara2 != vlPara5){
            trocoPara[0] = vlPara2;
        }else if (vlPara5 != vlPara2 && vlPara5 != vlPara10){
            trocoPara[1] = vlPara5;
        }
        else if (vlPara5 != vlPara2 && vlPara5 != vlPara10 && vlPara5 != vlPara20 && vlPara5 != vlPara50 && vlPara5 != vlPara100){
            trocoPara[1] = vlPara5;
        }*/



        //System.out.println(Cedulas.C5.getNota());
        /*switch (total){

        }*/
        return retornoFormatado;
    }


    /*public static int getQuantCelula(int valor, int cedula) {
        int ret;
        if (valor < cedula) {
            ret = 1;
        } else {
            Double resultadoDivisao = Math.ceil(valor / cedula);
            ret = resultadoDivisao.intValue() + 1;
        }
        return ret;
    }*/

    private static int getQuantCelula(int valor, int cedula) {
        int ret;
        if (valor < cedula) {
            ret = 1;
        } else {
            Double resultadoDivisao = Math.ceil(valor / cedula);
            if (resultadoDivisao.intValue() * cedula < valor) {
                ret = resultadoDivisao.intValue() + 1;
            } else {
                ret = resultadoDivisao.intValue();
            }
            // ret = resultadoDivisao.intValue() + 1;
            /*int restoDivisao = valor % cedula;
            if(restoDivisao == 0) {
                ret = resultadoDivisao.intValue() + 1; //14,00
            }else{
                ret = resultadoDivisao.intValue() + 1;
            }*/
        }
        return ret;
    }

    /*public static int getQuantCelulaDezenas(int valor, int cedula) {
        int ret;
        if (valor < cedula) {
            ret = 1;
        } else {
            Double resultadoDivisao = Math.ceil(valor / cedula);
            //ret = resultadoDivisao.intValue() + 1;
            if (resultadoDivisao.intValue() * cedula <= valor && cedula == 1) {
                ret = resultadoDivisao.intValue() + 1;
            } else {
                ret = resultadoDivisao.intValue();
            }
        }
        return ret;
    }

    public static int getMilCentenas(String valor) {
        int ret = 0;
        try {
            ret = Integer.parseInt(valor.substring(0, 2).concat("00"));
        } catch (Exception ex) {
            System.out.println("Erro getMilCentenas" + ex);
        }
        return ret;
    }

    public static int getCentenas(String valor) {
        //return Integer.parseInt(valor.substring(0,1).concat("00"));
        int ret = 0;
        try {
            ret = Integer.parseInt(valor.substring(0, 1).concat("00"));
        } catch (Exception ex) {
            System.out.println("Erro getCentenas" + ex);
        }
        return ret;
    }*/

    public static int calculateDivFilters(int length) {
        int b = 4;
        int d;
        Double ultimaP = Math.ceil(length / b);
        if (length % b == 0) {
            d = ultimaP.intValue();
        } else {
            d = ultimaP.intValue() + 1;
        }
        //System.out.println("Res: " + d);
        return d;
    }

    /*
    // Para ordenar por nome
    public static void orderLitersByName(List<Liters> lista) {
        Collections.sort(lista, new Comparator<Liters>() {
            @Override
            public int compare(Liters o1, Liters o2) {
                return o1.getLitersName().compareTo(o2.getLitersName());
            }
        });
    }*/

    // Para ordenar por numeros
    public static void orderLitersById(List<Liters> lista) {
        Collections.sort(lista, new Comparator<Liters>() {
            @Override
            public int compare(Liters o1, Liters o2) {
                return o1.getIdLiters().compareTo(o2.getIdLiters());
            }
        });
    }

    public static void orderOrdersById(List<Order> lista) {
        Collections.sort(lista, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return Integer.valueOf(o2.getIdOrder()).compareTo(o1.getIdOrder());
            }
        });
    }

    public static ArrayList<Company> updateToZeroAvNull(ArrayList<Company> list) {
        for (int i = 0; i < list.size(); i++) {
            if (null == list.get(i).getAv()) {
                list.get(i).setAv(BigDecimal.ZERO);
            }
        }
        return list;
    }


}
