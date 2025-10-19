package org.jkiss.dbeaver.ext.oracle.data;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OracleBinaryFormatterDiffblueTest {
  /**
   * Test {@link OracleBinaryFormatter#toString(byte[], int, int)} with {@code byte[]}, {@code int},
   * {@code int}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return {@code '415841'}.
   * </ul>
   *
   * <p>Method under test: {@link OracleBinaryFormatter#toString(byte[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OracleBinaryFormatter.toString(byte[], int, int)"})
  public void testToStringWithByteIntInt_whenThree_thenReturn415841()
      throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(
        "'415841'", OracleBinaryFormatter.INSTANCE.toString("AXAXAXAX".getBytes("UTF-8"), 2, 3));
  }

  /**
   * Test {@link OracleBinaryFormatter#toBytes(String)}.
   *
   * <ul>
   *   <li>When {@code ''}.
   *   <li>Then return empty array of {@code byte}.
   * </ul>
   *
   * <p>Method under test: {@link OracleBinaryFormatter#toBytes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] OracleBinaryFormatter.toBytes(String)"})
  public void testToBytes_whenApostropheApostrophe_thenReturnEmptyArrayOfByte() {
    // Arrange, Act and Assert
    assertArrayEquals(new byte[] {}, OracleBinaryFormatter.INSTANCE.toBytes("''"));
  }

  /**
   * Test {@link OracleBinaryFormatter#toBytes(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return empty array of {@code byte}.
   * </ul>
   *
   * <p>Method under test: {@link OracleBinaryFormatter#toBytes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] OracleBinaryFormatter.toBytes(String)"})
  public void testToBytes_whenEmptyString_thenReturnEmptyArrayOfByte() {
    // Arrange, Act and Assert
    assertArrayEquals(new byte[] {}, OracleBinaryFormatter.INSTANCE.toBytes(""));
  }

  /**
   * Test {@link OracleBinaryFormatter#toBytes(String)}.
   *
   * <ul>
   *   <li>When {@code String}.
   *   <li>Then return array of {@code byte} with minus seventeen and minus seventeen.
   * </ul>
   *
   * <p>Method under test: {@link OracleBinaryFormatter#toBytes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] OracleBinaryFormatter.toBytes(String)"})
  public void testToBytes_whenString_thenReturnArrayOfByteWithMinusSeventeenAndMinusSeventeen() {
    // Arrange, Act and Assert
    assertArrayEquals(new byte[] {-17, -17, -17}, OracleBinaryFormatter.INSTANCE.toBytes("String"));
  }

  /**
   * Test {@link OracleBinaryFormatter#toBytes(String)}.
   *
   * <ul>
   *   <li>When {@code 'String}.
   *   <li>Then return array of {@code byte} with minus seventeen and minus seventeen.
   * </ul>
   *
   * <p>Method under test: {@link OracleBinaryFormatter#toBytes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] OracleBinaryFormatter.toBytes(String)"})
  public void testToBytes_whenString_thenReturnArrayOfByteWithMinusSeventeenAndMinusSeventeen2() {
    // Arrange, Act and Assert
    assertArrayEquals(new byte[] {-17, -17}, OracleBinaryFormatter.INSTANCE.toBytes("'String"));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link OracleBinaryFormatter}
   *   <li>{@link OracleBinaryFormatter#getId()}
   *   <li>{@link OracleBinaryFormatter#getTitle()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OracleBinaryFormatter.<init>()",
    "String OracleBinaryFormatter.getId()",
    "String OracleBinaryFormatter.getTitle()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    OracleBinaryFormatter actualOracleBinaryFormatter = new OracleBinaryFormatter();
    String actualId = actualOracleBinaryFormatter.getId();

    // Assert
    assertEquals("Oracle Hex", actualOracleBinaryFormatter.getTitle());
    assertEquals("orahex", actualId);
  }
}
