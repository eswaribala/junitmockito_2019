package com.virtusa.banking.tests;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.virtusa.banking.models.Utils;

import static org.powermock.api.easymock.PowerMock.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest(Utils.class)
public class UtilsTest {

	@Test
	public void test_static_method() {
		//PowerMock.mockStatic()
		mockStatic(Utils.class);
		
		expect(Utils.generateID()).andReturn(1000L);
		
		//PowerMock.replayAll()
		replayAll();
		
		assertEquals(1000L, Utils.generateID());
		//PowerMock.verifyAll()
		verifyAll();
	}
}