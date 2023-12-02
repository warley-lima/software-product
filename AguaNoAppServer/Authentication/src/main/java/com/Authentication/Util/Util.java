package com.Authentication.Util;

import com.Authentication.Model.Login;
import com.User.Model.User;

public class Util {

    public static Login convertUserTologin(User user){
        Login ret = null;
        try {
            String scope = "";
            ret = new Login();
           /* ret.setName(user.getName());
            ret.setUname(user.getUserName());
            ret.setPass(user.getPassword());
            ret.setClientID("Tenant_5");
            for (int i = 0; i < user.getRules().size(); i++) {
                scope += user.getRules().get(i).getIdModuleFK() + user.getRules().get(i).getName().concat(";");
            }
            ret.setScope(scope.concat(user.getPerfil()));*/
        }
        catch (Exception error){

        }
        return ret;
    }
}
