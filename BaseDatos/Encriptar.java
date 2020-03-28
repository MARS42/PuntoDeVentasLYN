/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDatos;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Encriptar {
String texto;
public Encriptar(String texto){
    this.texto=texto;
}

public String Encrip(){
   
    return DigestUtils.md5Hex(texto);
}
}
