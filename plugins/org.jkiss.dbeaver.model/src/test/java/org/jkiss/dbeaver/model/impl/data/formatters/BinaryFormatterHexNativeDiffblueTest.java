package org.jkiss.dbeaver.model.impl.data.formatters;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BinaryFormatterHexNativeDiffblueTest {
  /**
   * Test {@link BinaryFormatterHexNative#toString(byte[], int, int)} with {@code byte[]}, {@code
   * int}, {@code int}.
   *
   * <ul>
   *   <li>Given {@link BinaryFormatterHexNative} (default constructor).
   *   <li>Then return {@code 0x415841}.
   * </ul>
   *
   * <p>Method under test: {@link BinaryFormatterHexNative#toString(byte[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String BinaryFormatterHexNative.toString(byte[], int, int)"})
  public void testToStringWithByteIntInt_givenBinaryFormatterHexNative_thenReturn0x415841()
      throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(
        "0x415841", new BinaryFormatterHexNative().toString("AXAXAXAX".getBytes("UTF-8"), 2, 3));
  }

  /**
   * Test {@link BinaryFormatterHexNative#toBytes(String)}.
   *
   * <ul>
   *   <li>Given {@link BinaryFormatterHexNative} (default constructor).
   *   <li>When {@code 0x}.
   *   <li>Then return empty array of {@code byte}.
   * </ul>
   *
   * <p>Method under test: {@link BinaryFormatterHexNative#toBytes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] BinaryFormatterHexNative.toBytes(String)"})
  public void testToBytes_givenBinaryFormatterHexNative_when0x_thenReturnEmptyArrayOfByte() {
    // Arrange, Act and Assert
    assertArrayEquals(new byte[] {}, new BinaryFormatterHexNative().toBytes("0x"));
  }

  /**
   * Test {@link BinaryFormatterHexNative#toBytes(String)}.
   *
   * <ul>
   *   <li>Given {@link BinaryFormatterHexNative} (default constructor).
   *   <li>When {@code 0X}.
   *   <li>Then return empty array of {@code byte}.
   * </ul>
   *
   * <p>Method under test: {@link BinaryFormatterHexNative#toBytes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] BinaryFormatterHexNative.toBytes(String)"})
  public void testToBytes_givenBinaryFormatterHexNative_when0x_thenReturnEmptyArrayOfByte2() {
    // Arrange, Act and Assert
    assertArrayEquals(new byte[] {}, new BinaryFormatterHexNative().toBytes("0X"));
  }

  /**
   * Test {@link BinaryFormatterHexNative#toBytes(String)}.
   *
   * <ul>
   *   <li>When {@code 0123456789ABCDEF}.
   *   <li>Then return array of {@code byte} with one and {@code #}.
   * </ul>
   *
   * <p>Method under test: {@link BinaryFormatterHexNative#toBytes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] BinaryFormatterHexNative.toBytes(String)"})
  public void testToBytes_when0123456789abcdef_thenReturnArrayOfByteWithOneAndNumberSign() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new byte[] {1, '#', 'E', 'g', -119, -85, -51, -17},
        new BinaryFormatterHexNative().toBytes("0123456789ABCDEF"));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link BinaryFormatterHexNative}
   *   <li>{@link BinaryFormatterHexNative#getId()}
   *   <li>{@link BinaryFormatterHexNative#getTitle()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BinaryFormatterHexNative.<init>()",
    "String BinaryFormatterHexNative.getId()",
    "String BinaryFormatterHexNative.getTitle()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    BinaryFormatterHexNative actualBinaryFormatterHexNative = new BinaryFormatterHexNative();
    String actualId = actualBinaryFormatterHexNative.getId();

    // Assert
    assertEquals("Hex", actualBinaryFormatterHexNative.getTitle());
    assertEquals("hex_native", actualId);
  }
}
