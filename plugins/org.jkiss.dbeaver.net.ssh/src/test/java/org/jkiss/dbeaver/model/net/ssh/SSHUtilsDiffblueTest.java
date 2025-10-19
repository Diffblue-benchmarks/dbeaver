package org.jkiss.dbeaver.model.net.ssh;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SSHUtilsDiffblueTest {
  /**
   * Test {@link SSHUtils#isKeyFileEncrypted(Path)} with {@code Path}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SSHUtils#isKeyFileEncrypted(Path)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SSHUtils.isKeyFileEncrypted(Path)"})
  public void testIsKeyFileEncryptedWithPath_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        SSHUtils.isKeyFileEncrypted(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")));
  }

  /**
   * Test {@link SSHUtils#isKeyFileEncrypted(String)} with {@code String}.
   *
   * <ul>
   *   <li>When {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link SSHUtils#isKeyFileEncrypted(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SSHUtils.isKeyFileEncrypted(String)"})
  public void testIsKeyFileEncryptedWithString_when42() {
    // Arrange, Act and Assert
    assertFalse(SSHUtils.isKeyFileEncrypted("42"));
  }

  /**
   * Test {@link SSHUtils#isKeyFileEncrypted(String)} with {@code String}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SSHUtils#isKeyFileEncrypted(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SSHUtils.isKeyFileEncrypted(String)"})
  public void testIsKeyFileEncryptedWithString_whenNull() {
    // Arrange, Act and Assert
    assertFalse(SSHUtils.isKeyFileEncrypted((String) null));
  }

  /**
   * Test {@link SSHUtils#isKeyFileEncrypted(String)} with {@code String}.
   *
   * <ul>
   *   <li>When {@code Priv Key Path}.
   * </ul>
   *
   * <p>Method under test: {@link SSHUtils#isKeyFileEncrypted(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SSHUtils.isKeyFileEncrypted(String)"})
  public void testIsKeyFileEncryptedWithString_whenPrivKeyPath() {
    // Arrange, Act and Assert
    assertFalse(SSHUtils.isKeyFileEncrypted("Priv Key Path"));
  }

  /**
   * Test {@link SSHUtils#isKeyEncrypted(byte[])}.
   *
   * <ul>
   *   <li>When array of {@code byte} with zero and {@code X}.
   * </ul>
   *
   * <p>Method under test: {@link SSHUtils#isKeyEncrypted(byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SSHUtils.isKeyEncrypted(byte[])"})
  public void testIsKeyEncrypted_whenArrayOfByteWithZeroAndX() {
    // Arrange, Act and Assert
    assertFalse(
        SSHUtils.isKeyEncrypted(
            new byte[] {
              0, 'X', 'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'
            }));
  }

  /**
   * Test {@link SSHUtils#isKeyEncrypted(byte[])}.
   *
   * <ul>
   *   <li>When array of {@code byte} with zero and zero.
   * </ul>
   *
   * <p>Method under test: {@link SSHUtils#isKeyEncrypted(byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SSHUtils.isKeyEncrypted(byte[])"})
  public void testIsKeyEncrypted_whenArrayOfByteWithZeroAndZero() {
    // Arrange, Act and Assert
    assertFalse(
        SSHUtils.isKeyEncrypted(
            new byte[] {
              0, 0, 'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'
            }));
  }

  /**
   * Test {@link SSHUtils#isKeyEncrypted(byte[])}.
   *
   * <ul>
   *   <li>When array of {@code byte} with zero and zero.
   * </ul>
   *
   * <p>Method under test: {@link SSHUtils#isKeyEncrypted(byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SSHUtils.isKeyEncrypted(byte[])"})
  public void testIsKeyEncrypted_whenArrayOfByteWithZeroAndZero2() {
    // Arrange, Act and Assert
    assertFalse(
        SSHUtils.isKeyEncrypted(
            new byte[] {0, 0, 0, 'X', 'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'}));
  }

  /**
   * Test {@link SSHUtils#isKeyEncrypted(byte[])}.
   *
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link SSHUtils#isKeyEncrypted(byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SSHUtils.isKeyEncrypted(byte[])"})
  public void testIsKeyEncrypted_whenAxaxaxaxBytesIsUtf8() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertFalse(SSHUtils.isKeyEncrypted("AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test {@link SSHUtils#isKeyEncrypted(byte[])}.
   *
   * <ul>
   *   <li>When {@code AXAXAXAXAXAXAXAX} Bytes is {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link SSHUtils#isKeyEncrypted(byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SSHUtils.isKeyEncrypted(byte[])"})
  public void testIsKeyEncrypted_whenAxaxaxaxaxaxaxaxBytesIsUtf8()
      throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertFalse(SSHUtils.isKeyEncrypted("AXAXAXAXAXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test {@link SSHUtils#isKeyEncrypted(byte[])}.
   *
   * <ul>
   *   <li>When empty array of {@code byte}.
   * </ul>
   *
   * <p>Method under test: {@link SSHUtils#isKeyEncrypted(byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SSHUtils.isKeyEncrypted(byte[])"})
  public void testIsKeyEncrypted_whenEmptyArrayOfByte() {
    // Arrange, Act and Assert
    assertFalse(SSHUtils.isKeyEncrypted(new byte[] {}));
  }

  /**
   * Test {@link SSHUtils#isKeyEncrypted(byte[])}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SSHUtils#isKeyEncrypted(byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SSHUtils.isKeyEncrypted(byte[])"})
  public void testIsKeyEncrypted_whenNull() {
    // Arrange, Act and Assert
    assertFalse(SSHUtils.isKeyEncrypted(null));
  }

  /**
   * Test {@link SSHUtils#trimLinesInKeyData(String)}.
   *
   * <p>Method under test: {@link SSHUtils#trimLinesInKeyData(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SSHUtils.trimLinesInKeyData(String)"})
  public void testTrimLinesInKeyData() {
    // Arrange, Act and Assert
    assertEquals("42", SSHUtils.trimLinesInKeyData("42"));
  }
}
