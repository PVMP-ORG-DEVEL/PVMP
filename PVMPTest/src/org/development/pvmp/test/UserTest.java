package org.development.pvmp.test;

import models.User;
import android.test.AndroidTestCase;

public class UserTest extends AndroidTestCase {
	
	private User user;

	protected void setUp() throws Exception {
		super.setUp();		
		user = new User();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testValidateNameFormat () {
		assertEquals(User.validateNameFormat("Lucas V Mattioli"), true);
		assertEquals(User.validateNameFormat("Jonathan"), true);
		assertEquals(User.validateNameFormat("Jonathan*"), false);
		assertEquals(User.validateNameFormat("    "), false);
		assertEquals(User.validateNameFormat("Joao123"), false);
		assertEquals(User.validateNameFormat("Jo&ao"), false);
	}

	public void testValidateAge () {
		assertEquals(User.validateAge(9), false);
		assertEquals(User.validateAge(100), false);
		assertEquals(User.validateAge(10), true);
		assertEquals(User.validateAge(-10), false);
		assertEquals(User.validateAge(99), true);
		assertEquals(User.validateAge(10000), false);
	}

	public void testValidatePasswordSize () {
		assertEquals(User.validatePasswordSize("12345"), false);
		assertEquals(User.validatePasswordSize("123456"), true);
		assertEquals(User.validatePasswordSize("123456a"),true);
		assertEquals(User.validatePasswordSize("qw123456afssdc1"),true);
		assertEquals(User.validatePasswordSize("12345qwert12345j"),false);
		assertEquals(User.validatePasswordSize("12345qwert123*&5j"),false);
	}
	
	public void testValidateNameSize () {
		assertEquals(User.validateNameSize(""), false);
		assertEquals(User.validateNameSize("a"), false);
		assertEquals(User.validateNameSize("ab"), false);
		assertEquals(User.validateNameSize("abc"), true);
		
		String message = new String ();
		for (int i = 0; i < 50; i++) {
			message += 'a';
		}
		assertEquals(User.validateNameSize(message), true);
		
		message +='a';
		assertEquals(User.validateNameSize(message), false);
	}
	
	public void testValidateUsernameSize () {
		assertEquals(User.validateUsernameSize("aaa"),true);
		assertEquals(User.validateUsernameSize("a1"),false);
		assertEquals(User.validateUsernameSize("123456789abcdef"),true);
		assertEquals(User.validateUsernameSize("abcdefg123456789"),false);
		assertEquals(User.validateUsernameSize("aaa12"),true);
	}
	
	public void testValidateUsernameFirstLetter () {
		assertEquals(User.validateUsernameFirstLetter("a123"),true);
		assertEquals(User.validateUsernameFirstLetter("2a1a"),false);
	}
	
	public void testValidateUsernameFormat () {
		assertEquals(User.validateUsernameFormat("Ajdj22"),true);
		assertEquals(User.validateUsernameFormat("$aja12"),false);
		assertEquals(User.validateUsernameFormat("0*"),false);
		assertEquals(User.validateUsernameFormat("aoo1234d"),true);
	}
	
	public void testValidateEmailFormat () {
		assertEquals(User.validateEmailFormat("jbs@email.com"),true);
		assertEquals(User.validateEmailFormat("asdd#kkss"),false);
		assertEquals(User.validateEmailFormat(" "),false);
		assertEquals(User.validateEmailFormat("{}sss@email.com"),false);
		assertEquals(User.validateEmailFormat("12krk@email.com"),true);
	
	}
	
	public void testValidateEmailSize () {
		assertEquals(User.validateEmailSize("fjfk@email.com"),true);
		assertEquals(User.validateEmailSize("cmfjfmfj1234567890cmdkvmsovkfv@email.com"),false);
	}
}
