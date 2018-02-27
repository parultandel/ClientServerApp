package com.example.tandels.issapplication;

import com.example.tandels.issapplication.viewmodel.Utility;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;



@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(Utility.class)
public class UtilityTest {

    private static final String testDateTS = "02-26-2018T 12:00:00";
    private long l;
    private int seconds;
    @Before
    public void setup(){
        PowerMockito.mockStatic(Utility.class);
        l=123456789;
        seconds = 123;
    }

    @Test
    public void testformateDateTime(){
        PowerMockito.when(Utility.formateDateTime(l)).thenReturn(testDateTS);
        Assert.assertNotNull(Utility.formateDateTime(l));
        Assert.assertEquals(Utility.formateDateTime(l), testDateTS);
    }

    @Test
    public void testFormatDuration(){
        String expectedFormat="12:00";
        PowerMockito.when(Utility.formateDuration(seconds)).thenReturn(expectedFormat);
        Assert.assertNotNull(Utility.formateDuration(seconds));
        Assert.assertEquals(Utility.formateDuration(seconds),expectedFormat);
    }
}
