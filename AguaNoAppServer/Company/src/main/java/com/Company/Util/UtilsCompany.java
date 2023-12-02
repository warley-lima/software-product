package com.Company.Util;

import com.Company.Model.CompanyPaymentTypes;

import java.util.ArrayList;
import java.util.List;

public class UtilsCompany {
    private static List<CompanyPaymentTypes> paymentTypeList;
    private static int lenghtTypes;

    public static List<CompanyPaymentTypes> convertTypes(String types) {
        paymentTypeList = new ArrayList<>();
        try {
            if (null != types) {
                types = types.replaceAll("}", "");
                lenghtTypes = types.length() - 1;
                switch (lenghtTypes) {
                    case 1:
                        paymentTypeList.add(new CompanyPaymentTypes(Integer.parseInt(types.substring(1))));
                        break;
                    case 3:
                        paymentTypeList.add(new CompanyPaymentTypes(Integer.parseInt(types.substring(1,2))));
                        paymentTypeList.add(new CompanyPaymentTypes(Integer.parseInt(types.substring(3,4))));
                        break;
                    case 5:
                        paymentTypeList.add(new CompanyPaymentTypes(Integer.parseInt(types.substring(1,2))));
                        paymentTypeList.add(new CompanyPaymentTypes(Integer.parseInt(types.substring(3,4))));
                        paymentTypeList.add(new CompanyPaymentTypes(Integer.parseInt(types.substring(5,6))));
                        break;
                }
            }
        } catch (Exception ex) {
            System.out.println("EXCEPTION: " + ex.getMessage());
        }
        return paymentTypeList;
    }
}
