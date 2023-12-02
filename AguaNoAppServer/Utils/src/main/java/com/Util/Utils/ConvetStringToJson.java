package com.Util.Utils;

import com.Util.Model.UserAuthenticated;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.json.JSONObject;

public class ConvetStringToJson {

    public static JSONObject convert(String text){
        JSONObject objRet = null;
       try {
          if(null != text && !text.isEmpty()){
              JsonFactory factory = new JsonFactory();
              JsonParser parser  = factory.createParser(text);
              if (null != parser){
                  objRet = new JSONObject();
                  while(!parser.isClosed()){
                      JsonToken jsonToken = parser.nextToken();
                      if(JsonToken.FIELD_NAME.equals(jsonToken)){
                          String fieldName = parser.getCurrentName();
                          jsonToken = parser.nextToken();
                          if("access_token".equals(fieldName)){
                              objRet.put("at", parser.getValueAsString());
                          } else if ("refresh_token".equals(fieldName)){
                              objRet.put("rt",parser.getValueAsString());
                          }else if ("user".equals(fieldName)){
                              objRet.put("na",parser.getValueAsString());
                          }
                      }
                  }

              }

             /* JSONArray arrayJson = new JSONArray(text);
              if (null != arrayJson && !arrayJson.isEmpty()){
                  objRet = new JSONObject();
                 // JSO jsonParser = new JSONParser();
                  for (Iterator<Object> it = arrayJson.iterator(); it.hasNext(); ) {
                      //JSONObject ob = (JSONObject) it.next();
                      System.out.println(it.next());
                  }
              }*/
              //objRet = new JSONObject(text);
          }
       }catch (Exception e){
           System.out.println("Erro ao Converter para JSON: " + e.toString());
       }
        return  objRet;
    }

    public static UserAuthenticated toUserAuthenticated(String text){
        UserAuthenticated objRet = null;
        try {
            if(null != text && !text.isEmpty()){
                JsonFactory factory = new JsonFactory();
                JsonParser parser  = factory.createParser(text);
                if (null != parser){
                    objRet = new UserAuthenticated();
                    while(!parser.isClosed()){
                        JsonToken jsonToken = parser.nextToken();
                        if(JsonToken.FIELD_NAME.equals(jsonToken)){
                            String fieldName = parser.getCurrentName();
                            jsonToken = parser.nextToken();
                            if("access_token".equals(fieldName)){
                                objRet.setAt(parser.getValueAsString());
                            } else if ("refresh_token".equals(fieldName)){
                                objRet.setRt(parser.getValueAsString());
                            }else if ("user".equals(fieldName)){
                                objRet.setNa(parser.getValueAsString());
                            }
                        }
                    }
                 }
            }
        }catch (Exception e){
            System.out.println("Erro ao Converter para UserAuthenticated: " + e.toString());
        }
        return  objRet;
    }

    public static JSONObject convert(String text, long idTenant){
        JSONObject objRet = null;
        try {
            if(null != text && !text.isEmpty()){
                JsonFactory factory = new JsonFactory();
                JsonParser parser  = factory.createParser(text);
                if (null != parser){
                    objRet = new JSONObject();
                    while(!parser.isClosed()){
                        JsonToken jsonToken = parser.nextToken();
                        if(JsonToken.FIELD_NAME.equals(jsonToken)){
                            String fieldName = parser.getCurrentName();
                            jsonToken = parser.nextToken();
                            if("access_token".equals(fieldName)){
                                objRet.put("at", parser.getValueAsString());
                            } else if ("refresh_token".equals(fieldName)){
                                objRet.put("rt",parser.getValueAsString());
                            }else if ("user".equals(fieldName)){
                                objRet.put("na",parser.getValueAsString());
                            }
                        }
                    }
                    objRet.put("tn", idTenant);

                }
            }
        }catch (Exception e){
            System.out.println("Erro ao Converter para JSON: " + e.toString());
        }
        return  objRet;
    }
}
