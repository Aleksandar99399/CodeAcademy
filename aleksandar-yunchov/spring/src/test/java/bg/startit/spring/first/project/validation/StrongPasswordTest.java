//package bg.startit.spring.first.project.validation;
//
//import bg.startit.validation.StrongPassword;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import javax.validation.ConstraintValidatorContext;
//
//import static org.testng.Assert.assertFalse;
//import static org.testng.Assert.assertTrue;
//
//public class StrongPasswordTest
//{
//  private StrongPassword             strongPassword = new StrongPassword();
//  @Mock
//  private ConstraintValidatorContext ctx;
//
//  @BeforeMethod
//  public void setUp()
//  {
//    MockitoAnnotations.openMocks(this);
//    Mockito.when(ctx.buildConstraintViolationWithTemplate(Mockito.anyString())).thenReturn(Mockito.mock(ConstraintValidatorContext.ConstraintViolationBuilder.class));
//  }
//
//  @AfterMethod
//  public void tearDown()
//  {
//  }
//
//  @Test
//  public void given_null_When_validating_Then_false()
//  {
//    assertFalse(strongPassword.isValid(null, ctx));
//    Mockito.verify(ctx, Mockito.times(1)).buildConstraintViolationWithTemplate(Mockito.anyString());
//  }
//
//  @Test
//  public void given_valid_password_When_validating_Then_true()
//  {
//    assertTrue(strongPassword.isValid("Admin123.", ctx));
//    Mockito.verify(ctx, Mockito.times(0)).buildConstraintViolationWithTemplate(Mockito.anyString());
//  }
//
//  @Test
//  public void given_short_password_When_validating_Then_false()
//  {
//    assertFalse(strongPassword.isValid("123Aa.", ctx));
//    assertFalse(strongPassword.isValid("", ctx));
//    assertFalse(strongPassword.isValid("1234Aa.", ctx));
//    Mockito.verify(ctx, Mockito.times(7)).buildConstraintViolationWithTemplate(Mockito.anyString());
//  }
//
//  @Test
//  public void given_long_password_When_validating_Then_false()
//  {
//    assertFalse(strongPassword.isValid("1234567890" +
//        "1234567890" +
//        "1234567890" +
//        "1234567890" +
//        "1234567890" +
//        "1234567890" +
//        "1234567Aa.", ctx));
//    Mockito.verify(ctx, Mockito.times(1)).buildConstraintViolationWithTemplate(Mockito.anyString());
//  }
//
//  @Test
//  public void given_absent_uppercase_When_validating_Then_false()
//  {
//    assertFalse(strongPassword.isValid("123456a.", ctx));
//    Mockito.verify(ctx, Mockito.times(1)).buildConstraintViolationWithTemplate(Mockito.anyString());
//  }
//
//  @Test
//  public void given_absent_lowercase_When_validating_Then_false()
//  {
//    assertFalse(strongPassword.isValid("123456A.", ctx));
//    Mockito.verify(ctx, Mockito.times(1)).buildConstraintViolationWithTemplate(Mockito.anyString());
//  }
//
//  @Test
//  public void given_absent_digit_When_validating_Then_false()
//  {
//    assertFalse(strongPassword.isValid("aaaaaAa.", ctx));
//    Mockito.verify(ctx, Mockito.times(1)).buildConstraintViolationWithTemplate(Mockito.anyString());
//  }
//
//  @Test
//  public void given_absent_special_symbol_When_validating_Then_false()
//  {
//    assertFalse(strongPassword.isValid("123456aA", ctx));
//    Mockito.verify(ctx, Mockito.times(1)).buildConstraintViolationWithTemplate(Mockito.anyString());
//  }
//
//  @Test
//  public void given_whitespace_When_validating_Then_false()
//  {
//    assertFalse(strongPassword.isValid("123456aA .", ctx));
//    Mockito.verify(ctx, Mockito.times(1)).buildConstraintViolationWithTemplate(Mockito.anyString());
//  }
//}