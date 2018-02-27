package com.example.tandels.issapplication;

import com.example.tandels.issapplication.util.IssAppUtil;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;



@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(IssAppUtil.class)
public class IssAppUtilTest {

    private static final String testDateTS = "02-26-2018T 12:00:00";
    private long l;
    private int seconds;
    @Before
    public void setup(){
        PowerMockito.mockStatic(IssAppUtil.class);
        l=123456789;
        seconds = 123;
    }

    @Test
    public void testformateDateTime(){
        PowerMockito.when(IssAppUtil.formateDateTime(l)).thenReturn(testDateTS);
        Assert.assertNotNull(IssAppUtil.formateDateTime(l));
        Assert.assertEquals(IssAppUtil.formateDateTime(l), testDateTS);
    }

    @Test
    public void testFormatDuration(){
        String expectedFormat="12:00";
        PowerMockito.when(IssAppUtil.formateDuration(seconds)).thenReturn(expectedFormat);
        Assert.assertNotNull(IssAppUtil.formateDuration(seconds));
        Assert.assertEquals(IssAppUtil.formateDuration(seconds),expectedFormat);
    }
}
