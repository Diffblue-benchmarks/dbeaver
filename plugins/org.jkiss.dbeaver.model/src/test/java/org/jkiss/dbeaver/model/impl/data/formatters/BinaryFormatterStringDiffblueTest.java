package org.jkiss.dbeaver.model.impl.data.formatters;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BinaryFormatterStringDiffblueTest {
  /**
   * Test {@link BinaryFormatterString#toString(byte[], int, int)} with {@code byte[]}, {@code int},
   * {@code int}.
   *
   * <ul>
   *   <li>When array of {@code byte} with {@code A} and three.
   *   <li>Then return {@code A A}.
   * </ul>
   *
   * <p>Method under test: {@link BinaryFormatterString#toString(byte[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String BinaryFormatterString.toString(byte[], int, int)"})
  public void testToStringWithByteIntInt_whenArrayOfByteWithAAndThree_thenReturnAA() {
    // Arrange, Act and Assert
    assertEquals(
        "A A",
        new BinaryFormatterString()
            .toString(
                new byte[] {'A', 3, 'A', 3, 'A', 3, 'A', 3, 'A', 3, 'A', 3, 'A', 3, 'A', 3}, 2, 3));
  }

  /**
   * Test {@link BinaryFormatterString#toString(byte[], int, int)} with {@code byte[]}, {@code int},
   * {@code int}.
   *
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.
   *   <li>Then return {@code AXA}.
   * </ul>
   *
   * <p>Method under test: {@link BinaryFormatterString#toString(byte[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String BinaryFormatterString.toString(byte[], int, int)"})
  public void testToStringWithByteIntInt_whenAxaxaxaxBytesIsUtf8_thenReturnAxa()
      throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("AXA", new BinaryFormatterString().toString("AXAXAXAX".getBytes("UTF-8"), 2, 3));
  }

  /**
   * Test {@link BinaryFormatterString#toString(byte[], int, int)} with {@code byte[]}, {@code int},
   * {@code int}.
   *
   * <ul>
   *   <li>When {@code X}.
   *   <li>Then return {@code XA}.
   * </ul>
   *
   * <p>Method under test: {@link BinaryFormatterString#toString(byte[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String BinaryFormatterString.toString(byte[], int, int)"})
  public void testToStringWithByteIntInt_whenX_thenReturnXa() {
    // Arrange, Act and Assert
    assertEquals(
        " XA",
        new BinaryFormatterString()
            .toString(new byte[] {'A', 'X', Byte.MAX_VALUE, 'X', 'A', 'X', 'A', 'X'}, 2, 3));
  }

  /**
   * Test {@link BinaryFormatterString#toString(byte[], int, int)} with {@code byte[]}, {@code int},
   * {@code int}.
   *
   * <ul>
   *   <li>When {@code X}.
   *   <li>Then return {@code ÿXA}.
   * </ul>
   *
   * <p>Method under test: {@link BinaryFormatterString#toString(byte[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String BinaryFormatterString.toString(byte[], int, int)"})
  public void testToStringWithByteIntInt_whenX_thenReturnXa2() {
    // Arrange, Act and Assert
    assertEquals(
        "ÿXA",
        new BinaryFormatterString()
            .toString(new byte[] {'A', 'X', -1, 'X', 'A', 'X', 'A', 'X'}, 2, 3));
  }

  /**
   * Test {@link BinaryFormatterString#toString(byte[], int, int)} with {@code byte[]}, {@code int},
   * {@code int}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link BinaryFormatterString#toString(byte[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String BinaryFormatterString.toString(byte[], int, int)"})
  public void testToStringWithByteIntInt_whenZero_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals(
        "",
        new BinaryFormatterString()
            .toString(new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'}, 2, 0));
  }

  /**
   * Test {@link BinaryFormatterString#toBytes(String)}.
   *
   * <p>Method under test: {@link BinaryFormatterString#toBytes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] BinaryFormatterString.toBytes(String)"})
  public void testToBytes() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertArrayEquals("String".getBytes("UTF-8"), new BinaryFormatterString().toBytes("String"));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link BinaryFormatterString}
   *   <li>{@link BinaryFormatterString#getId()}
   *   <li>{@link BinaryFormatterString#getTitle()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BinaryFormatterString.<init>()",
    "String BinaryFormatterString.getId()",
    "String BinaryFormatterString.getTitle()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    BinaryFormatterString actualBinaryFormatterString = new BinaryFormatterString();
    String actualId = actualBinaryFormatterString.getId();

    // Assert
    assertEquals("String", actualBinaryFormatterString.getTitle());
    assertEquals("string", actualId);
  }
}
