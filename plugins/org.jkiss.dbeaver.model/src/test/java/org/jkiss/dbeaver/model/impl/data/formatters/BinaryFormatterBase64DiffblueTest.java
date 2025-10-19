package org.jkiss.dbeaver.model.impl.data.formatters;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BinaryFormatterBase64DiffblueTest {
  /**
   * Test {@link BinaryFormatterBase64#toString(byte[], int, int)} with {@code byte[]}, {@code int},
   * {@code int}.
   *
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link BinaryFormatterBase64#toString(byte[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String BinaryFormatterBase64.toString(byte[], int, int)"})
  public void testToStringWithByteIntInt_whenAxaxaxaxBytesIsUtf8_thenReturnEmptyString()
      throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("", new BinaryFormatterBase64().toString("AXAXAXAX".getBytes("UTF-8"), 3, 3));
  }

  /**
   * Test {@link BinaryFormatterBase64#toString(byte[], int, int)} with {@code byte[]}, {@code int},
   * {@code int}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code WEE=}.
   * </ul>
   *
   * <p>Method under test: {@link BinaryFormatterBase64#toString(byte[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String BinaryFormatterBase64.toString(byte[], int, int)"})
  public void testToStringWithByteIntInt_whenOne_thenReturnWee()
      throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("WEE=", new BinaryFormatterBase64().toString("AXAXAXAX".getBytes("UTF-8"), 1, 3));
  }

  /**
   * Test {@link BinaryFormatterBase64#toString(byte[], int, int)} with {@code byte[]}, {@code int},
   * {@code int}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code QQ==}.
   * </ul>
   *
   * <p>Method under test: {@link BinaryFormatterBase64#toString(byte[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String BinaryFormatterBase64.toString(byte[], int, int)"})
  public void testToStringWithByteIntInt_whenTwo_thenReturnQq()
      throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("QQ==", new BinaryFormatterBase64().toString("AXAXAXAX".getBytes("UTF-8"), 2, 3));
  }

  /**
   * Test {@link BinaryFormatterBase64#toString(byte[], int, int)} with {@code byte[]}, {@code int},
   * {@code int}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link BinaryFormatterBase64#toString(byte[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String BinaryFormatterBase64.toString(byte[], int, int)"})
  public void testToStringWithByteIntInt_whenZero_thenReturnEmptyString()
      throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("", new BinaryFormatterBase64().toString("AXAXAXAX".getBytes("UTF-8"), 2, 0));
  }

  /**
   * Test {@link BinaryFormatterBase64#toString(byte[], int, int)} with {@code byte[]}, {@code int},
   * {@code int}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@code QVhB}.
   * </ul>
   *
   * <p>Method under test: {@link BinaryFormatterBase64#toString(byte[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String BinaryFormatterBase64.toString(byte[], int, int)"})
  public void testToStringWithByteIntInt_whenZero_thenReturnQVhB()
      throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("QVhB", new BinaryFormatterBase64().toString("AXAXAXAX".getBytes("UTF-8"), 0, 3));
  }

  /**
   * Test {@link BinaryFormatterBase64#toBytes(String)}.
   *
   * <p>Method under test: {@link BinaryFormatterBase64#toBytes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] BinaryFormatterBase64.toBytes(String)"})
  public void testToBytes() {
    // Arrange, Act and Assert
    assertArrayEquals(new byte[] {'J', -38, -30}, new BinaryFormatterBase64().toBytes("String"));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link BinaryFormatterBase64}
   *   <li>{@link BinaryFormatterBase64#getId()}
   *   <li>{@link BinaryFormatterBase64#getTitle()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BinaryFormatterBase64.<init>()",
    "String BinaryFormatterBase64.getId()",
    "String BinaryFormatterBase64.getTitle()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    BinaryFormatterBase64 actualBinaryFormatterBase64 = new BinaryFormatterBase64();
    String actualId = actualBinaryFormatterBase64.getId();

    // Assert
    assertEquals("Base64", actualBinaryFormatterBase64.getTitle());
    assertEquals("base64", actualId);
  }
}
