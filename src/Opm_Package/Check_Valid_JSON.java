/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Opm_Package;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



/**
 *
 * @author john
 */
public class Check_Valid_JSON {
   
    public boolean isValidJSON(final String json) {
    boolean valid = false;
    try {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(json);
        valid = true;
   } catch (Exception e) {
     valid = false;
   }
   return valid;
}
    
    

    
}
