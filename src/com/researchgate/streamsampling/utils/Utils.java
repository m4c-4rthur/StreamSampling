/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.researchgate.streamsampling.utils;

/**
 *
 * @author hesham.ibrahim
 */
public class Utils {
    
    public static String ArrayToString(char[] x)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(x);
        //System.out.println(sb);
        return sb.toString();
    }
}
