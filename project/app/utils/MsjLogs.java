/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author reyherh
 */
public enum MsjLogs {
    
     SERVICE_STARTED("%s | Service started"),
     SERVICE_END_OK("%s | Service finished succesfully"),
     SERVICE_END_ERROR("%s | Service finished with Error"),
     
     OPEN_VIEW("Open view: %s");
     
     private String msj;
     
     private MsjLogs(String msj){
         this.msj=msj;
     }
    
     public String getMsj(){
         return this.msj;
     }
     
     public static String buildMsj(MsjLogs tipo,String nombreRest){
         return String.format(tipo.getMsj(), nombreRest);
         
     }
    
}
