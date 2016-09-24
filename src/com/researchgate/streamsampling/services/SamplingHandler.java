/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.researchgate.streamsampling.services;

import com.researchgate.streamsampling.exceptions.EmptyStreamException;
import com.researchgate.streamsampling.utils.LogUtils;
import com.researchgate.streamsampling.utils.Utils;
import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author hesham.ibrahim
 */
public class SamplingHandler {

    public String HandleStreamSampling(String inputStream, int sampleSize, int RandomUpperLimit, int mode) throws EmptyStreamException {
        StringBuilder sb = new StringBuilder();
        char[] u;
        String result;
        String randomStream = generateRandomStream(RandomUpperLimit);
        if(randomStream.length() + inputStream.length() <= 0)
        {
            throw new EmptyStreamException("Both streams are empty excpetion");
        }
        if (mode == 1) {
            LogUtils.debug("Start sampling from both streams with Reservoir Sampling Algorithm ");
            u = reservoirSamplingAlgorithm(inputStream, randomStream, sampleSize);
            result = Utils.ArrayToString(u);
            LogUtils.info("result from Reservoir Sampling Algorithm is "+ result);
        } else {
            LogUtils.debug("Start sampling from both streams with my Algorithm");
            u = myAlgorithm(inputStream, randomStream, sampleSize);
            result = Utils.ArrayToString(u);
            LogUtils.info("result from my Algorithm is "+ result);
        }
        return result;
    }

    public static String generateRandomStream(int upperLimit) {
        if (upperLimit <= 0) {
            return "";
        }
        Long time = System.currentTimeMillis();
        Random random = new Random();
        int x = random.nextInt(upperLimit);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < x; i++) {
            sb.append((char) (random.nextInt(26) + 'A'));
        }
        //System.out.println(System.currentTimeMillis() - time);
        return sb.toString();
    }
    
    //this method do the same as generateRandomStream but 
    //I noticed that it's performance is slower so I ignored it
//    public static String generateRandomStream2(int upperLimit) {
//        if (upperLimit <= 0) {
//            return "";
//        }
//        Long time = System.currentTimeMillis();
//        Random random = new Random();
//        String randomStream = random.ints(upperLimit, 0, 26).map(i -> ('A') + i).collect(StringBuilder::new,
//                StringBuilder::appendCodePoint, StringBuilder::append).toString();
//        System.out.println(System.currentTimeMillis() - time);
//        return randomStream;
//    }

    /**
     * This is a new implementation to stream sampling problem
     *
     * @param stream
     * @param stream2
     * @param K
     * @return
     */
    private char[] myAlgorithm(String stream, String stream2, int sampleSize) {
//        Long time = System.currentTimeMillis();
        HashSet<Integer> set = new HashSet();
        char[] list = new char[sampleSize];
        stream = stream.concat(stream2); // concat both streams
        if (sampleSize >= stream.length()) {
            return stream.toCharArray(); // return the stream if the sample size is greater than the stream itself
        }
        Random random = new Random();
        for (int i = 0; i < sampleSize; i++) {
            int y = random.nextInt(stream.length());
            if (set.add(y)) { //Ensure we don't have duplicate indexes
                list[i] = stream.charAt(y);
            } else {
                i--; // Skip this iteration due to repeated index
            }
        }
//        System.out.println((System.currentTimeMillis() - time));
        return list;
    }

    /**
     * This method implement stream sampling using reservoir sampling algorithm
     *
     * @param stream
     * @param stream2
     * @param K
     * @return
     */
    private char[] reservoirSamplingAlgorithm(String stream, String stream2, int sampleSize) {
//        Long time = System.currentTimeMillis();
        char[] sampleResult = new char[sampleSize];
        char[] A = stream.concat(stream2).toCharArray();
        if (sampleSize >= A.length) { // return the stream if the sample size is greater than the stream itself
            return A;
        }
        Random random = new Random();
        for (int i = 0; i < A.length; i++) {
            int y = random.nextInt(i + 1) + 1; // Pick up a random value between 0 and i
            if (y <= sampleSize) {
                sampleResult[y - 1] = A[i];   // if the rand value less than sample size then the current A[i] with sample result
            }
        }
//        System.out.println((System.currentTimeMillis() - time));
        return sampleResult;
    }

}
