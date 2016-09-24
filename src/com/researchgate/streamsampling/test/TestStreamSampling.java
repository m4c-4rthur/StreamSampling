/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.researchgate.streamsampling.test;

import com.researchgate.streamsampling.exceptions.EmptyStreamException;
import com.researchgate.streamsampling.services.SamplingHandler;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author hesham.ibrahim
 */
public class TestStreamSampling {

    SamplingHandler handler = new SamplingHandler();
    // test the happy scenario 

    @Test
    public void testCalculateAppriasal() throws EmptyStreamException {
        String inputStream = SamplingHandler.generateRandomStream(100000);
        String result = handler.HandleStreamSampling(inputStream, 5, 100000, 1);
        assertEquals(5, result.length());

        inputStream = SamplingHandler.generateRandomStream(100000);
        result = handler.HandleStreamSampling(inputStream, 5, 100000, 2);
        assertEquals(5, result.length());

    }

    // case of the result sample size is larger the both stream size together
    @Test
    public void testCalculateYearlySalary() throws EmptyStreamException {
        String inputStream = SamplingHandler.generateRandomStream(2);
        String result = handler.HandleStreamSampling(inputStream, 5, 2, 1);
        assertTrue(5 > result.length());
        inputStream = SamplingHandler.generateRandomStream(1);
        result = handler.HandleStreamSampling(inputStream, 5, 2, 2);
        assertTrue(5 > result.length());
    }
    // case of empty empty stream from boths sources
    @Test(expected = EmptyStreamException.class)
    public void testCalculateYearlySalary2() throws EmptyStreamException {
        String inputStream = SamplingHandler.generateRandomStream(0);
        String result = handler.HandleStreamSampling(inputStream, 5, 0, 1);
        inputStream = SamplingHandler.generateRandomStream(0);
        result = handler.HandleStreamSampling(inputStream, 5, 0, 2);
    }

}
