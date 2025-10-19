package org.jkiss.dbeaver.runtime.encode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SecuredPasswordEncrypterDiffblueTest {
  /**
   * Test {@link SecuredPasswordEncrypter#SecuredPasswordEncrypter()}.
   *
   * <ul>
   *   <li>Then return decrypt {@link SecuredPasswordEncrypter#SCHEME_DES} is empty string.
   * </ul>
   *
   * <p>Method under test: {@link SecuredPasswordEncrypter#SecuredPasswordEncrypter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SecuredPasswordEncrypter.<init>()"})
  public void testNewSecuredPasswordEncrypter_thenReturnDecryptScheme_desIsEmptyString()
      throws EncryptionException {
    // Arrange, Act and Assert
    assertEquals("", new SecuredPasswordEncrypter().decrypt(SecuredPasswordEncrypter.SCHEME_DES));
  }

  /**
   * Test {@link SecuredPasswordEncrypter#SecuredPasswordEncrypter(String)}.
   *
   * <ul>
   *   <li>Then return decrypt {@link SecuredPasswordEncrypter#SCHEME_DES} is empty string.
   * </ul>
   *
   * <p>Method under test: {@link SecuredPasswordEncrypter#SecuredPasswordEncrypter(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SecuredPasswordEncrypter.<init>(String)"})
  public void testNewSecuredPasswordEncrypter_thenReturnDecryptScheme_desIsEmptyString2()
      throws EncryptionException {
    // Arrange, Act and Assert
    assertEquals(
        "",
        new SecuredPasswordEncrypter(SecuredPasswordEncrypter.SCHEME_DESEDE)
            .decrypt(SecuredPasswordEncrypter.SCHEME_DES));
  }

  /**
   * Test {@link SecuredPasswordEncrypter#decrypt(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link SecuredPasswordEncrypter#decrypt(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SecuredPasswordEncrypter.decrypt(String)"})
  public void testDecrypt_whenEmptyString_thenThrowIllegalArgumentException()
      throws EncryptionException {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new SecuredPasswordEncrypter().decrypt(""));
  }

  /**
   * Test {@link SecuredPasswordEncrypter#decrypt(String)}.
   *
   * <ul>
   *   <li>When {@code Encrypted String}.
   *   <li>Then throw {@link EncryptionException}.
   * </ul>
   *
   * <p>Method under test: {@link SecuredPasswordEncrypter#decrypt(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SecuredPasswordEncrypter.decrypt(String)"})
  public void testDecrypt_whenEncryptedString_thenThrowEncryptionException()
      throws EncryptionException {
    // Arrange, Act and Assert
    assertThrows(
        EncryptionException.class,
        () -> new SecuredPasswordEncrypter().decrypt("Encrypted String"));
  }

  /**
   * Test {@link SecuredPasswordEncrypter#decrypt(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link SecuredPasswordEncrypter#decrypt(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SecuredPasswordEncrypter.decrypt(String)"})
  public void testDecrypt_whenNull_thenThrowIllegalArgumentException() throws EncryptionException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> new SecuredPasswordEncrypter().decrypt(null));
  }

  /**
   * Test {@link SecuredPasswordEncrypter#decrypt(String)}.
   *
   * <ul>
   *   <li>When {@link SecuredPasswordEncrypter#SCHEME_DES}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link SecuredPasswordEncrypter#decrypt(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SecuredPasswordEncrypter.decrypt(String)"})
  public void testDecrypt_whenScheme_des_thenReturnEmptyString() throws EncryptionException {
    // Arrange, Act and Assert
    assertEquals("", new SecuredPasswordEncrypter().decrypt(SecuredPasswordEncrypter.SCHEME_DES));
  }
}
