package org.jkiss.dbeaver.ext.postgresql.model.data;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostgreBinaryFormatterDiffblueTest {
  /**
   * Test {@link PostgreBinaryFormatter#toString(byte[], int, int)} with {@code byte[]}, {@code
   * int}, {@code int}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return {@code decode('415841','hex')}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreBinaryFormatter#toString(byte[], int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PostgreBinaryFormatter.toString(byte[], int, int)"})
  public void testToStringWithByteIntInt_whenThree_thenReturnDecode415841Hex()
      throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(
        "decode('415841','hex')",
        PostgreBinaryFormatter.INSTANCE.toString("AXAXAXAX".getBytes("UTF-8"), 2, 3));
  }

  /**
   * Test {@link PostgreBinaryFormatter#toBytes(String)}.
   *
   * <ul>
   *   <li>When {@code decode('decode('}.
   *   <li>Then return empty array of {@code byte}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreBinaryFormatter#toBytes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] PostgreBinaryFormatter.toBytes(String)"})
  public void testToBytes_whenDecodeDecode_thenReturnEmptyArrayOfByte() {
    // Arrange, Act and Assert
    assertArrayEquals(new byte[] {}, PostgreBinaryFormatter.INSTANCE.toBytes("decode('decode('"));
  }

  /**
   * Test {@link PostgreBinaryFormatter#toBytes(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return empty array of {@code byte}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreBinaryFormatter#toBytes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] PostgreBinaryFormatter.toBytes(String)"})
  public void testToBytes_whenEmptyString_thenReturnEmptyArrayOfByte() {
    // Arrange, Act and Assert
    assertArrayEquals(new byte[] {}, PostgreBinaryFormatter.INSTANCE.toBytes(""));
  }

  /**
   * Test {@link PostgreBinaryFormatter#toBytes(String)}.
   *
   * <ul>
   *   <li>When {@code String}.
   *   <li>Then return array of {@code byte} with minus seventeen and minus seventeen.
   * </ul>
   *
   * <p>Method under test: {@link PostgreBinaryFormatter#toBytes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] PostgreBinaryFormatter.toBytes(String)"})
  public void testToBytes_whenString_thenReturnArrayOfByteWithMinusSeventeenAndMinusSeventeen() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new byte[] {-17, -17, -17}, PostgreBinaryFormatter.INSTANCE.toBytes("String"));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link PostgreBinaryFormatter}
   *   <li>{@link PostgreBinaryFormatter#getId()}
   *   <li>{@link PostgreBinaryFormatter#getTitle()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PostgreBinaryFormatter.<init>()",
    "String PostgreBinaryFormatter.getId()",
    "String PostgreBinaryFormatter.getTitle()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    PostgreBinaryFormatter actualPostgreBinaryFormatter = new PostgreBinaryFormatter();
    String actualId = actualPostgreBinaryFormatter.getId();

    // Assert
    assertEquals("PostgreSQL Hex", actualPostgreBinaryFormatter.getTitle());
    assertEquals("pghex", actualId);
  }
}
