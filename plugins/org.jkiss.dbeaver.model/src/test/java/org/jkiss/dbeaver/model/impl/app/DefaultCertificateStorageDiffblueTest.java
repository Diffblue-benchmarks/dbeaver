package org.jkiss.dbeaver.model.impl.app;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.security.GeneralSecurityException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DefaultCertificateStorageDiffblueTest {
  /**
   * Test {@link DefaultCertificateStorage#readEncryptedString(Reader)} with {@code reader}.
   *
   * <p>Method under test: {@link DefaultCertificateStorage#readEncryptedString(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] DefaultCertificateStorage.readEncryptedString(Reader)"})
  public void testReadEncryptedStringWithReader() throws IOException {
    // Arrange, Act and Assert
    assertArrayEquals(
        new byte[] {},
        DefaultCertificateStorage.readEncryptedString(
            new CharArrayReader("ྠ\u0004ྠ\u0004".toCharArray())));
  }

  /**
   * Test {@link DefaultCertificateStorage#readEncryptedString(Reader)} with {@code reader}.
   *
   * <p>Method under test: {@link DefaultCertificateStorage#readEncryptedString(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] DefaultCertificateStorage.readEncryptedString(Reader)"})
  public void testReadEncryptedStringWithReader2() throws IOException {
    // Arrange, Act and Assert
    assertArrayEquals(
        new byte[] {},
        DefaultCertificateStorage.readEncryptedString(
            new CharArrayReader("=\u0004ྠ\u0004".toCharArray())));
  }

  /**
   * Test {@link DefaultCertificateStorage#readEncryptedString(Reader)} with {@code reader}.
   *
   * <ul>
   *   <li>Then return array of {@code byte} with zero and zero.
   * </ul>
   *
   * <p>Method under test: {@link DefaultCertificateStorage#readEncryptedString(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] DefaultCertificateStorage.readEncryptedString(Reader)"})
  public void testReadEncryptedStringWithReader_thenReturnArrayOfByteWithZeroAndZero()
      throws IOException {
    // Arrange
    ByteArrayInputStream byteArrayInputStream =
        new ByteArrayInputStream(new byte[] {'A', -96, 'A', -96, 'A', '=', 'A', -96});
    InputStreamReader reader = new InputStreamReader(byteArrayInputStream);

    // Act
    byte[] actualReadEncryptedStringResult = DefaultCertificateStorage.readEncryptedString(reader);

    // Assert
    assertNull(reader.getEncoding());
    assertArrayEquals(new byte[] {0, 0}, actualReadEncryptedStringResult);
  }

  /**
   * Test {@link DefaultCertificateStorage#readEncryptedString(Reader)} with {@code reader}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return array of {@code byte} with zero and zero.
   * </ul>
   *
   * <p>Method under test: {@link DefaultCertificateStorage#readEncryptedString(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] DefaultCertificateStorage.readEncryptedString(Reader)"})
  public void testReadEncryptedStringWithReader_whenA_thenReturnArrayOfByteWithZeroAndZero()
      throws IOException {
    // Arrange
    ByteArrayInputStream byteArrayInputStream =
        new ByteArrayInputStream(new byte[] {'A', -96, 'A', -96, 'A', -96, 'A', -96});
    InputStreamReader reader = new InputStreamReader(byteArrayInputStream);

    // Act
    byte[] actualReadEncryptedStringResult = DefaultCertificateStorage.readEncryptedString(reader);

    // Assert
    assertNull(reader.getEncoding());
    assertArrayEquals(new byte[] {0, 0, 0}, actualReadEncryptedStringResult);
  }

  /**
   * Test {@link DefaultCertificateStorage#readEncryptedString(Reader)} with {@code reader}.
   *
   * <ul>
   *   <li>When {@code =}.
   *   <li>Then return array of {@code byte} with zero.
   * </ul>
   *
   * <p>Method under test: {@link DefaultCertificateStorage#readEncryptedString(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] DefaultCertificateStorage.readEncryptedString(Reader)"})
  public void testReadEncryptedStringWithReader_whenEqualsSign_thenReturnArrayOfByteWithZero()
      throws IOException {
    // Arrange
    ByteArrayInputStream byteArrayInputStream =
        new ByteArrayInputStream(new byte[] {'A', -96, 'A', '=', 'A', -96, 'A', -96});
    InputStreamReader reader = new InputStreamReader(byteArrayInputStream);

    // Act
    byte[] actualReadEncryptedStringResult = DefaultCertificateStorage.readEncryptedString(reader);

    // Assert
    assertNull(reader.getEncoding());
    assertArrayEquals(new byte[] {0}, actualReadEncryptedStringResult);
  }

  /**
   * Test {@link DefaultCertificateStorage#readEncryptedString(Reader)} with {@code reader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code -}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultCertificateStorage#readEncryptedString(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] DefaultCertificateStorage.readEncryptedString(Reader)"})
  public void testReadEncryptedStringWithReader_whenStringReaderWithDash() throws IOException {
    // Arrange, Act and Assert
    assertArrayEquals(
        new byte[] {}, DefaultCertificateStorage.readEncryptedString(new StringReader("-")));
  }

  /**
   * Test {@link DefaultCertificateStorage#readEncryptedString(Reader)} with {@code reader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultCertificateStorage#readEncryptedString(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] DefaultCertificateStorage.readEncryptedString(Reader)"})
  public void testReadEncryptedStringWithReader_whenStringReaderWithFoo() throws IOException {
    // Arrange, Act and Assert
    assertArrayEquals(
        new byte[] {}, DefaultCertificateStorage.readEncryptedString(new StringReader("foo")));
  }

  /**
   * Test {@link DefaultCertificateStorage#readEncryptedString(Reader)} with {@code reader}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code #}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultCertificateStorage#readEncryptedString(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] DefaultCertificateStorage.readEncryptedString(Reader)"})
  public void testReadEncryptedStringWithReader_whenStringReaderWithNumberSign()
      throws IOException {
    // Arrange, Act and Assert
    assertArrayEquals(
        new byte[] {}, DefaultCertificateStorage.readEncryptedString(new StringReader("#")));
  }

  /**
   * Test {@link DefaultCertificateStorage#readEncryptedString(InputStream)} with {@code stream}.
   *
   * <p>Method under test: {@link DefaultCertificateStorage#readEncryptedString(InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] DefaultCertificateStorage.readEncryptedString(InputStream)"})
  public void testReadEncryptedStringWithStream() throws IOException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    byte[] actualReadEncryptedStringResult = DefaultCertificateStorage.readEncryptedString(stream);

    // Assert
    int actualReadResult = stream.read(new byte[] {});
    assertEquals(-1, actualReadResult);
    assertArrayEquals(new byte[] {1, 'p', 23, 1, 'p', 23}, actualReadEncryptedStringResult);
  }

  /**
   * Test {@link DefaultCertificateStorage#readEncryptedString(InputStream)} with {@code stream}.
   *
   * <p>Method under test: {@link DefaultCertificateStorage#readEncryptedString(InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] DefaultCertificateStorage.readEncryptedString(InputStream)"})
  public void testReadEncryptedStringWithStream2() throws IOException {
    // Arrange
    ByteArrayInputStream stream =
        new ByteArrayInputStream(new byte[] {-96, 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Act
    byte[] actualReadEncryptedStringResult = DefaultCertificateStorage.readEncryptedString(stream);

    // Assert
    int actualReadResult = stream.read(new byte[] {});
    assertEquals(-1, actualReadResult);
    assertArrayEquals(new byte[] {'\\', 5, -64}, actualReadEncryptedStringResult);
  }

  /**
   * Test {@link DefaultCertificateStorage#readEncryptedString(InputStream)} with {@code stream}.
   *
   * <p>Method under test: {@link DefaultCertificateStorage#readEncryptedString(InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] DefaultCertificateStorage.readEncryptedString(InputStream)"})
  public void testReadEncryptedStringWithStream3() throws IOException {
    // Arrange
    ByteArrayInputStream stream =
        new ByteArrayInputStream(new byte[] {4, 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Act
    byte[] actualReadEncryptedStringResult = DefaultCertificateStorage.readEncryptedString(stream);

    // Assert
    int actualReadResult = stream.read(new byte[] {});
    assertEquals(-1, actualReadResult);
    assertArrayEquals(new byte[] {'\\', 5, -64}, actualReadEncryptedStringResult);
  }

  /**
   * Test {@link DefaultCertificateStorage#readEncryptedString(InputStream)} with {@code stream}.
   *
   * <p>Method under test: {@link DefaultCertificateStorage#readEncryptedString(InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] DefaultCertificateStorage.readEncryptedString(InputStream)"})
  public void testReadEncryptedStringWithStream4() throws IOException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("=XAXAXAX".getBytes("UTF-8"));

    // Act
    byte[] actualReadEncryptedStringResult = DefaultCertificateStorage.readEncryptedString(stream);

    // Assert
    int actualReadResult = stream.read(new byte[] {});
    assertEquals(-1, actualReadResult);
    assertArrayEquals(new byte[] {-3, 'p', 23, 1, 'p', 23}, actualReadEncryptedStringResult);
  }

  /**
   * Test {@link DefaultCertificateStorage#readEncryptedString(InputStream)} with {@code stream}.
   *
   * <p>Method under test: {@link DefaultCertificateStorage#readEncryptedString(InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] DefaultCertificateStorage.readEncryptedString(InputStream)"})
  public void testReadEncryptedStringWithStream5() throws IOException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AX=XAXAX".getBytes("UTF-8"));

    // Act
    byte[] actualReadEncryptedStringResult = DefaultCertificateStorage.readEncryptedString(stream);

    // Assert
    int actualReadResult = stream.read(new byte[] {});
    assertEquals(-1, actualReadResult);
    assertArrayEquals(new byte[] {1, 1, 'p', 23}, actualReadEncryptedStringResult);
  }

  /**
   * Test {@link DefaultCertificateStorage#readEncryptedString(InputStream)} with {@code stream}.
   *
   * <p>Method under test: {@link DefaultCertificateStorage#readEncryptedString(InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] DefaultCertificateStorage.readEncryptedString(InputStream)"})
  public void testReadEncryptedStringWithStream6() throws IOException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AXA=AXAX".getBytes("UTF-8"));

    // Act
    byte[] actualReadEncryptedStringResult = DefaultCertificateStorage.readEncryptedString(stream);

    // Assert
    int actualReadResult = stream.read(new byte[] {});
    assertEquals(-1, actualReadResult);
    assertArrayEquals(new byte[] {1, 'p', 1, 'p', 23}, actualReadEncryptedStringResult);
  }

  /**
   * Test {@link DefaultCertificateStorage#loadPrivateKeyFromPEM(byte[])}.
   *
   * <p>Method under test: {@link DefaultCertificateStorage#loadPrivateKeyFromPEM(byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.security.PrivateKey DefaultCertificateStorage.loadPrivateKeyFromPEM(byte[])"
  })
  public void testLoadPrivateKeyFromPEM() throws IOException, GeneralSecurityException {
    // Arrange, Act and Assert
    assertThrows(
        GeneralSecurityException.class,
        () -> DefaultCertificateStorage.loadPrivateKeyFromPEM("AXAXAXAX".getBytes("UTF-8")));
  }
}
