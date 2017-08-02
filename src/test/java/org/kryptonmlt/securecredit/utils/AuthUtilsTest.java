/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kryptonmlt.securecredit.utils;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 *
 * @author Kurt
 */
public class AuthUtilsTest {
    
    public AuthUtilsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isAdmin method, of class AuthUtils.
     */
    @Test
    public void testIsAdmin_user() {
        System.out.println("testIsAdmin_user");
        Collection<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("USER"));
        boolean expResult = false;
        boolean result = AuthUtils.isAdmin(roles);
        assertEquals(expResult, result);
    }

    /**
     * Test of isAdmin method, of class AuthUtils.
     */
    @Test
    public void testIsAdmin_admin() {
        System.out.println("testIsAdmin_admin");
        Collection<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ADMIN"));
        boolean expResult = true;
        boolean result = AuthUtils.isAdmin(roles);
        assertEquals(expResult, result);
    }

    /**
     * Test of isAdmin method, of class AuthUtils.
     */
    @Test
    public void testIsAdmin_all() {
        System.out.println("testIsAdmin_all");
        Collection<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("USER"));
        roles.add(new SimpleGrantedAuthority("ADMIN"));
        boolean expResult = true;
        boolean result = AuthUtils.isAdmin(roles);
        assertEquals(expResult, result);
    }

    /**
     * Test of isAdmin method, of class AuthUtils.
     */
    @Test
    public void testIsAdmin_nothing() {
        System.out.println("testIsAdmin_nothing");
        Collection<GrantedAuthority> roles = new ArrayList<>();
        boolean expResult = false;
        boolean result = AuthUtils.isAdmin(roles);
        assertEquals(expResult, result);
    }
    
}
