package com.virtusa.banking.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Stack;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class ArgumentCaptorTest {

	 @Mock 
	 Stack<String> stack;
	 
	 @Captor ArgumentCaptor<String> argumentCaptor;
	 
	 @BeforeEach
	    public void setup() {
	    	//This initializes objects annotated with Mockito annotations for given testClass
	        MockitoAnnotations.initMocks(this);
	    }
	 
	  @Test
	  public void test() throws Exception {
	    stack.add("Mockito");
	    Mockito.verify(stack).add(argumentCaptor.capture());
	    assertEquals("Mockito", argumentCaptor.getValue());
	  }

}
