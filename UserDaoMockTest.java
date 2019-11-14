package com.virtusa.banking.tests;

/*import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;*/

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.virtusa.banking.dao.interfaces.HSBCUserDao;
import com.virtusa.banking.exceptions.EmptyCredentialsException;
import com.virtusa.banking.models.HSBCUser;
import com.virtusa.banking.services.HSBCUserService;
import com.virtusa.banking.services.HSBCUserServiceInjectMock;

class UserDaoMockTest {

	    @Mock
	    private HSBCUserDao hsbcUserDao;
	    @Mock
	    private HSBCUser hsbcUser;
	    //to avoid MockitoAnnotations.initMocks(this);
	   // @Rule public MockitoRule rule = MockitoJUnit.rule();
	    		
	    //injectmock
	    @InjectMocks
	    private HSBCUserServiceInjectMock hsbcUserServiceInjectMock;
	    
	    @Captor
	    private ArgumentCaptor<HSBCUser> captor;
	    
	    
	    
	    @BeforeEach
	    public void setup() {
	    	//This initializes objects annotated with Mockito annotations for given testClass
	        MockitoAnnotations.initMocks(this);
	    }
	    //InitMock
	    @Test
	    public void testHSBCUserDAO(){
	  
	        Mockito.when(hsbcUserDao.getUserByMobileNumber(9952032862L)).thenReturn(new HSBCUser(9952032862L, "Param"));
	  
	       HSBCUser hsbcUser = hsbcUserDao.getUserByMobileNumber(9952032862L);
	  
	        assert (hsbcUser.getName().equals("Param"));
	        assert (hsbcUser.getMobileNo() == 9952032862L);
	    }
	 
	    @Test
	    public void testHSBCUserDAOMockito(){
	  
	    	HSBCUserDao hsbcUserDAO = Mockito.mock(HSBCUserDao.class);
	  
	        Mockito.when(hsbcUserDAO.getUserByMobileNumber(9952032862L)).thenReturn(new HSBCUser(9952032862L, "Param"));
	  
	        HSBCUser hsbcUser =hsbcUserDAO.getUserByMobileNumber(9952032862L);
	  	  
	        assert (hsbcUser.getName().equals("Param"));
	        assert (hsbcUser.getMobileNo() == 9952032862L);
	    }
	    
	    @Test
	    public void testHSBCUserDAOAddUserMockito(){
	  
	    	HSBCUserDao hsbcUserDAO = Mockito.mock(HSBCUserDao.class);	    	
	  
	        Mockito.when(hsbcUserDAO.addUser(hsbcUser)).thenReturn(true);
	  
	        boolean status =hsbcUserDAO.addUser(hsbcUser);
	  	  
	        assertTrue(status );
	        
	    }
	    @Test
	    public void testHSBCUserDAOAddUserMockitoException(){
	  
	    	HSBCUserDao hsbcUserDAO = Mockito.mock(HSBCUserDao.class);
	    	
	  
	        Mockito.when(hsbcUserDAO.addUser(null)).thenThrow(new NullPointerException());
	  
	        Assertions.assertThrows(NullPointerException.class, 
	        		() -> hsbcUserDAO.addUser(null));
	        
	    }
	

	    @Test
	    public void testPeopleDAOException(){
	  
	    	HSBCUserDao hsbcUserDao = Mockito.mock(HSBCUserDao.class);
	  
	        Mockito.when(hsbcUserDao.getUserByMobileNumber(1_000_000L))
	        .thenThrow(new IllegalArgumentException());
	  
	        Assertions.assertThrows(IllegalArgumentException.class, 
	        		() -> hsbcUserDao.getUserByMobileNumber(1_000_000L));
	    
	        Mockito.when(hsbcUserDao.getUserByMobileNumber(0))
	        .thenThrow(new EmptyCredentialsException("Mobile Cannot be zero"));
	  	  
	        Assertions.assertThrows(EmptyCredentialsException.class, 
	        		() -> hsbcUserDao.getUserByMobileNumber(0));
	    }
	    
	    //verify method calls
	    
	    @Test
	    public void testHsbcUserDAO(){
	  
	    	HSBCUserDao hsbcUserDao = Mockito.mock(HSBCUserDao.class);
	  	  
	        Mockito.when(hsbcUserDao.getUserByMobileNumber(Mockito.anyLong())).thenReturn(new HSBCUser(9952032862L, "Param"));
	        HSBCUser hsbcUser = hsbcUserDao.getUserByMobileNumber(9952032862L);
		  	
	        // check whether this method is called twice.
	        Mockito.verify(hsbcUserDao , Mockito.times(1)).getUserByMobileNumber(Mockito.anyLong());
	        //Mockito.verify(hsbcUserDao , Mockito.atLeast(3)).getUserByMobileNumber(9952032862L);
	        Mockito.verify(hsbcUserDao , Mockito.atLeastOnce()).getUserByMobileNumber(Mockito.anyLong());
	        Mockito.verify(hsbcUserDao , Mockito.atMost(1)).getUserByMobileNumber(Mockito.anyLong());
	        Mockito.verify(hsbcUserDao, Mockito.timeout(100)).getUserByMobileNumber(Mockito.anyLong());
	    
	    
	    }
	    
	    
	    @Test
	    public void testHsbcUserFindAllUsersDAO(){
	  
	    	HSBCUserDao hsbcUserDao = Mockito.mock(HSBCUserDao.class);
	  	  
	    	List<HSBCUser> hsbcUsers=new ArrayList<HSBCUser>();
	        Mockito.when(hsbcUserDao.getAllUsers()).thenReturn(hsbcUsers);
	        //hsbcUserDao.getAllUsers();
	       // hsbcUserDao.getAllUsers();
	        // check whether this method is called twice.
	        Mockito.verify(hsbcUserDao , Mockito.never()).getAllUsers();
	       // Mockito.verify(hsbcUserDao , Mockito.atLeast(3)).getAllUsers();
	        //Mockito.verify(hsbcUserDao , Mockito.atLeastOnce()).getAllUsers();
	        //Mockito.verify(hsbcUserDao , Mockito.atMost(1)).getAllUsers();
	       // Mockito.verify(hsbcUserDao, Mockito.timeout(100)).getAllUsers();
	    
	    
	    }
	    
	    //partial mocking
	    @Spy
	    List<String> spiedList = new ArrayList<String>();
	     
	    @Test
	    public void whenUseSpyAnnotation_thenSpyIsInjectedCorrectly() {
	        spiedList.add("one");
	        spiedList.add("two");
	     
	        Mockito.verify(spiedList).add("one");
	        Mockito.verify(spiedList).add("two");
	     
	        assertEquals(2, spiedList.size());
	     
	        Mockito.doReturn(100).when(spiedList).size();
	        assertEquals(100, spiedList.size());
	    }
	    
	    @Spy
	    List<HSBCUser> spiedUsers=new ArrayList<HSBCUser>();
	    @Test
	    public void whenUseHSBCUserSpyAnnotation_thenSpyIsInjectedCorrectly() {
	    	spiedUsers.add(hsbcUser);
	    	//spiedUsers.add(hsbcUser);
	     
	        Mockito.verify(spiedUsers).add(hsbcUser);
	        
	     
	        assertEquals(1, spiedUsers.size());
	     
	        Mockito.doReturn(20).when(spiedUsers).size();
	        assertEquals(20, spiedUsers.size());
	    }
	    
	    //@injectmock
	    
	    @Test
	    public void hsbcUserServiceInjectMockTest()
	    {
	   
	    	// when
	        Mockito.when(hsbcUserDao.getUserByMobileNumber(9952032862L))
	        .thenReturn(new HSBCUser(9952032862L, "Param"));

	        
	        assertEquals("Param", hsbcUserServiceInjectMock.getUserByMobileNo(9952032862L).getName());
	       
	    }
	    
	    @Test
	    public void shouldCapture() {
		/*
		 * HSBCUser hsbcUser = new HSBCUser(); hsbcUser.setMobileNo(9952032862L);
		 * hsbcUser.setName("Param");
		 */
	    	HSBCUser hsbcUser = new HSBCUser(); 
	    	hsbcUser.setMobileNo(9952032862L);
			hsbcUser.setName("Param");
	        hsbcUserServiceInjectMock.addUser(hsbcUser);
	        Mockito.verify(hsbcUserDao).addUser(captor.capture());
	        HSBCUser captured = captor.getValue();
	        assertEquals(captured.getName(), "Param");
	    }
}
