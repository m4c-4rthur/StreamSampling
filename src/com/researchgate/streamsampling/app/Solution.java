/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.researchgate.streamsampling.app;

import com.researchgate.streamsampling.constants.Defines;
import com.researchgate.streamsampling.services.SamplingHandler;
import com.researchgate.streamsampling.utils.LogUtils;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

/**
 *
 *
 * @author hesham.ibrahim
 */
public class Solution {

    static int randomUpperLimit = 10000000;

    public static void main(String arg[]) {
        String result = "";
        try {
            LogUtils.initialize(Defines.APP.logPropertiesFileName);
            Scanner sc = new Scanner(System.in);
            LogUtils.info("Please enter the sample size");
            int sampleSize = sc.nextInt();
            LogUtils.info("Please enter the first stream");
            String inputStream = (sc.next());
            SamplingHandler handler = new SamplingHandler();
            handler.HandleStreamSampling(inputStream, sampleSize, randomUpperLimit, 1);
            handler.HandleStreamSampling(inputStream, sampleSize, randomUpperLimit, 2);
        } catch (Exception e) {
            LogUtils.error("Exception occured " + e.getMessage(), e);
        }
    }

}
