package com.virtusa.banking.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.virtusa.banking.models.Payment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.easymock.PowerMock.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Payment.class)
public class PaymentTest {

	@Test
	public void test_private_method() throws Exception {
		Payment mock = createPartialMock(Payment.class, "isInit", "reverse");
		
		expectPrivate(mock, "isInit").andReturn(true);
		expectPrivate(mock, "reverse", "cat").andReturn("tac");
		expectPrivate(mock, "reverse", "123").andReturn("321");
		replay(mock);
		
		assertTrue(mock.checkStatus());
		assertEquals("tac", mock.doReverse("cat"));
		assertEquals("321", mock.doReverse("123"));
		
		verify(mock);
	}
}