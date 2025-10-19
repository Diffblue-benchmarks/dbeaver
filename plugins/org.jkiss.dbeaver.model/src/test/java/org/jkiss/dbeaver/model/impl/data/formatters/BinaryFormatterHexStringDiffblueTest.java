package org.jkiss.dbeaver.model.impl.data.formatters;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BinaryFormatterHexStringDiffblueTest {
  /**
   * Test {@link BinaryFormatterHexString#toString(byte[], int, int)} with {@code byte[]}, {@code
   * int}, {@code int}.
   *
   * <ul>
   *   <li>Given {@link BinaryFormatterHexString} (default constructor).
   *   <li>Then return {@code x'415841'}.
   * </ul>
   *
   * <p>Method under test: {@link BinaryFormatterHexString#toString(byte[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String BinaryFormatterHexString.toString(byte[], int, int)"})
  public void testToStringWithByteIntInt_givenBinaryFormatterHexString_thenReturnX415841()
      throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(
        "x'415841'", new BinaryFormatterHexString().toString("AXAXAXAX".getBytes("UTF-8"), 2, 3));
  }

  /**
   * Test {@link BinaryFormatterHexString#toBytes(String)}.
   *
   * <ul>
   *   <li>When {@code 0123456789ABCDEFx'}.
   *   <li>Then return array of {@code byte} with {@code #} and {@code E}.
   * </ul>
   *
   * <p>Method under test: {@link BinaryFormatterHexString#toBytes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] BinaryFormatterHexString.toBytes(String)"})
  public void testToBytes_when0123456789ABCDEFx_thenReturnArrayOfByteWithNumberSignAndE() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new byte[] {'#', 'E', 'g', -119, -85, -51},
        new BinaryFormatterHexString().toBytes("0123456789ABCDEFx'"));
  }

  /**
   * Test {@link BinaryFormatterHexString#toBytes(String)}.
   *
   * <ul>
   *   <li>When {@code 0123456789ABCDEF}.
   *   <li>Then return array of {@code byte} with one and {@code #}.
   * </ul>
   *
   * <p>Method under test: {@link BinaryFormatterHexString#toBytes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] BinaryFormatterHexString.toBytes(String)"})
  public void testToBytes_when0123456789abcdef_thenReturnArrayOfByteWithOneAndNumberSign() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new byte[] {1, '#', 'E', 'g', -119, -85, -51, -17},
        new BinaryFormatterHexString().toBytes("0123456789ABCDEF"));
  }

  /**
   * Test {@link BinaryFormatterHexString#toBytes(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return empty array of {@code byte}.
   * </ul>
   *
   * <p>Method under test: {@link BinaryFormatterHexString#toBytes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] BinaryFormatterHexString.toBytes(String)"})
  public void testToBytes_whenEmptyString_thenReturnEmptyArrayOfByte() {
    // Arrange, Act and Assert
    assertArrayEquals(new byte[] {}, new BinaryFormatterHexString().toBytes(""));
  }

  /**
   * Test {@link BinaryFormatterHexString#toBytes(String)}.
   *
   * <ul>
   *   <li>When {@code x'0123456789ABCDEF}.
   *   <li>Then return array of {@code byte} with one and {@code #}.
   * </ul>
   *
   * <p>Method under test: {@link BinaryFormatterHexString#toBytes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] BinaryFormatterHexString.toBytes(String)"})
  public void testToBytes_whenX0123456789abcdef_thenReturnArrayOfByteWithOneAndNumberSign() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new byte[] {1, '#', 'E', 'g', -119, -85},
        new BinaryFormatterHexString().toBytes("x'0123456789ABCDEF"));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link BinaryFormatterHexString}
   *   <li>{@link BinaryFormatterHexString#getId()}
   *   <li>{@link BinaryFormatterHexString#getTitle()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BinaryFormatterHexString.<init>()",
    "String BinaryFormatterHexString.getId()",
    "String BinaryFormatterHexString.getTitle()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    BinaryFormatterHexString actualBinaryFormatterHexString = new BinaryFormatterHexString();
    String actualId = actualBinaryFormatterHexString.getId();

    // Assert
    assertEquals("Hex", actualBinaryFormatterHexString.getTitle());
    assertEquals("hex_string", actualId);
  }
}
