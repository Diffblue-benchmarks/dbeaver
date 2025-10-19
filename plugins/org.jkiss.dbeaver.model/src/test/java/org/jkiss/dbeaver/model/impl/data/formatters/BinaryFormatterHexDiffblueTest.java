package org.jkiss.dbeaver.model.impl.data.formatters;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BinaryFormatterHexDiffblueTest {
  /**
   * Test {@link BinaryFormatterHex#toString(byte[], int, int)} with {@code byte[]}, {@code int},
   * {@code int}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return {@code 415841}.
   * </ul>
   *
   * <p>Method under test: {@link BinaryFormatterHex#toString(byte[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String BinaryFormatterHex.toString(byte[], int, int)"})
  public void testToStringWithByteIntInt_whenThree_thenReturn415841()
      throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(
        "415841", BinaryFormatterHex.INSTANCE.toString("AXAXAXAX".getBytes("UTF-8"), 2, 3));
  }

  /**
   * Test {@link BinaryFormatterHex#toHexChars(byte[], int, int)}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return {@code 415841} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link BinaryFormatterHex#toHexChars(byte[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"char[] BinaryFormatterHex.toHexChars(byte[], int, int)"})
  public void testToHexChars_whenThree_thenReturn415841ToCharArray()
      throws UnsupportedEncodingException {
    // Arrange and Act
    char[] actualToHexCharsResult =
        BinaryFormatterHex.toHexChars("AXAXAXAX".getBytes("UTF-8"), 2, 3);

    // Assert
    assertArrayEquals("415841".toCharArray(), actualToHexCharsResult);
  }

  /**
   * Test {@link BinaryFormatterHex#toBytes(String)}.
   *
   * <ul>
   *   <li>When {@code 0123456789ABCDEF}.
   *   <li>Then return array of {@code byte} with one and {@code #}.
   * </ul>
   *
   * <p>Method under test: {@link BinaryFormatterHex#toBytes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] BinaryFormatterHex.toBytes(String)"})
  public void testToBytes_when0123456789abcdef_thenReturnArrayOfByteWithOneAndNumberSign() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new byte[] {1, '#', 'E', 'g', -119, -85, -51, -17},
        BinaryFormatterHex.INSTANCE.toBytes("0123456789ABCDEF"));
  }

  /**
   * Test {@link BinaryFormatterHex#toBytes(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return empty array of {@code byte}.
   * </ul>
   *
   * <p>Method under test: {@link BinaryFormatterHex#toBytes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] BinaryFormatterHex.toBytes(String)"})
  public void testToBytes_whenEmptyString_thenReturnEmptyArrayOfByte() {
    // Arrange, Act and Assert
    assertArrayEquals(new byte[] {}, BinaryFormatterHex.INSTANCE.toBytes(""));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link BinaryFormatterHex}
   *   <li>{@link BinaryFormatterHex#getId()}
   *   <li>{@link BinaryFormatterHex#getTitle()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BinaryFormatterHex.<init>()",
    "String BinaryFormatterHex.getId()",
    "String BinaryFormatterHex.getTitle()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    BinaryFormatterHex actualBinaryFormatterHex = new BinaryFormatterHex();
    String actualId = actualBinaryFormatterHex.getId();

    // Assert
    assertEquals("Hex", actualBinaryFormatterHex.getTitle());
    assertEquals("hex", actualId);
  }
}
