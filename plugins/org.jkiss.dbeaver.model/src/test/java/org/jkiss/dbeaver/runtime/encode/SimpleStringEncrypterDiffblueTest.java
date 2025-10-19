package org.jkiss.dbeaver.runtime.encode;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SimpleStringEncrypterDiffblueTest {
  /**
   * Test {@link SimpleStringEncrypter#decrypt(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleStringEncrypter#decrypt(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SimpleStringEncrypter.decrypt(String)"})
  public void testDecrypt_whenEmptyString_thenThrowIllegalArgumentException()
      throws EncryptionException {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> SimpleStringEncrypter.INSTANCE.decrypt(""));
  }

  /**
   * Test {@link SimpleStringEncrypter#decrypt(String)}.
   *
   * <ul>
   *   <li>When {@code Encrypted String}.
   *   <li>Then throw {@link EncryptionException}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleStringEncrypter#decrypt(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SimpleStringEncrypter.decrypt(String)"})
  public void testDecrypt_whenEncryptedString_thenThrowEncryptionException()
      throws EncryptionException {
    // Arrange, Act and Assert
    assertThrows(
        EncryptionException.class,
        () -> SimpleStringEncrypter.INSTANCE.decrypt("Encrypted String"));
  }

  /**
   * Test {@link SimpleStringEncrypter#decrypt(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleStringEncrypter#decrypt(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SimpleStringEncrypter.decrypt(String)"})
  public void testDecrypt_whenNull_thenThrowIllegalArgumentException() throws EncryptionException {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> SimpleStringEncrypter.INSTANCE.decrypt(null));
  }

  /**
   * Test {@link SimpleStringEncrypter#decrypt(String)}.
   *
   * <ul>
   *   <li>When {@code
   *       sdf@!#$verf^wv%6Fwe%$$#FFGwfsdefwfe135s$^H)dgsdf@!#$verf^wv%6Fwe%$$#FFGwfsdefwfe135s$^H)dg}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleStringEncrypter#decrypt(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SimpleStringEncrypter.decrypt(String)"})
  public void testDecrypt_whenSdfVerfWv6FweFFGwfsdefwfe135sHDgsdfVerfWv6FweFFGwfsdefwfe135sHDg()
      throws EncryptionException {
    // Arrange, Act and Assert
    assertThrows(
        EncryptionException.class,
        () ->
            SimpleStringEncrypter.INSTANCE.decrypt(
                "sdf@!#$verf^wv%6Fwe%$$#FFGwfsdefwfe135s$^H)dgsdf@!#$verf^wv%6Fwe%$$#FFGwfsdefwfe135s$^H)dg"));
  }
}
