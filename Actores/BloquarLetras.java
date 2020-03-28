/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actores;

import java.util.ArrayList;

/**
 *
 * @author omara
 */
public class BloquarLetras {
    ArrayList<Object>numeros= new ArrayList<>();

    public BloquarLetras() {
        for(int i=0; i<9; i++){
            numeros.add(i);
        }
        numeros.add(".");
    }
    
}
