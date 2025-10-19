package org.jkiss.dbeaver.ext.hana.model.data.wkb;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class HANAWKBParserDiffblueTest {
  /**
   * Test {@link HANAWKBParser#parse(byte[])}.
   *
   * <ul>
   *   <li>When array of {@code byte} with one and {@code X}.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBParser#parse(byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"org.locationtech.jts.geom.Geometry HANAWKBParser.parse(byte[])"})
  public void testParse_whenArrayOfByteWithOneAndX() throws HANAWKBParserException {
    // Arrange, Act and Assert
    assertThrows(
        HANAWKBParserException.class,
        () -> new HANAWKBParser().parse(new byte[] {1, 'X', 'A', 'X', 'A', 'X', 'A', 'X'}));
  }

  /**
   * Test {@link HANAWKBParser#parse(byte[])}.
   *
   * <ul>
   *   <li>When array of {@code byte} with one and {@code X}.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBParser#parse(byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"org.locationtech.jts.geom.Geometry HANAWKBParser.parse(byte[])"})
  public void testParse_whenArrayOfByteWithOneAndX2() throws HANAWKBParserException {
    // Arrange, Act and Assert
    assertThrows(
        HANAWKBParserException.class,
        () -> new HANAWKBParser().parse(new byte[] {1, 'X', 'A', 'X', -1, 'X', 'A', 'X'}));
  }

  /**
   * Test {@link HANAWKBParser#parse(byte[])}.
   *
   * <ul>
   *   <li>When array of {@code byte} with zero and {@code X}.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBParser#parse(byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"org.locationtech.jts.geom.Geometry HANAWKBParser.parse(byte[])"})
  public void testParse_whenArrayOfByteWithZeroAndX() throws HANAWKBParserException {
    // Arrange, Act and Assert
    assertThrows(
        HANAWKBParserException.class,
        () -> new HANAWKBParser().parse(new byte[] {0, 'X', 'A', 'X', 'A', 'X', 'A', 'X'}));
  }

  /**
   * Test {@link HANAWKBParser#parse(byte[])}.
   *
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBParser#parse(byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"org.locationtech.jts.geom.Geometry HANAWKBParser.parse(byte[])"})
  public void testParse_whenAxaxaxaxBytesIsUtf8()
      throws UnsupportedEncodingException, HANAWKBParserException {
    // Arrange, Act and Assert
    assertThrows(
        HANAWKBParserException.class,
        () -> new HANAWKBParser().parse("AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test {@link HANAWKBParser#parse(byte[])}.
   *
   * <ul>
   *   <li>When empty array of {@code byte}.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBParser#parse(byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"org.locationtech.jts.geom.Geometry HANAWKBParser.parse(byte[])"})
  public void testParse_whenEmptyArrayOfByte() throws HANAWKBParserException {
    // Arrange, Act and Assert
    assertThrows(HANAWKBParserException.class, () -> new HANAWKBParser().parse(new byte[] {}));
  }
}
