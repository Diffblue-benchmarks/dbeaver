package org.jkiss.dbeaver.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBPDataKindDiffblueTest {
  /**
   * Test {@link DBPDataKind#isComplex()}.
   *
   * <p>Method under test: {@link DBPDataKind#isComplex()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPDataKind.isComplex()"})
  public void testIsComplex() {
    // Arrange, Act and Assert
    assertFalse(DBPDataKind.valueOf("BOOLEAN").isComplex());
  }

  /**
   * Test {@link DBPDataKind#supportsRangeSearch()}.
   *
   * <ul>
   *   <li>Given {@code BOOLEAN}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBPDataKind#supportsRangeSearch()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPDataKind.supportsRangeSearch()"})
  public void testSupportsRangeSearch_givenBoolean_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(DBPDataKind.BOOLEAN.supportsRangeSearch());
  }

  /**
   * Test {@link DBPDataKind#supportsRangeSearch()}.
   *
   * <ul>
   *   <li>Given {@link DBPDataKind#ROWID}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBPDataKind#supportsRangeSearch()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPDataKind.supportsRangeSearch()"})
  public void testSupportsRangeSearch_givenRowid_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(DBPDataKind.ROWID.supportsRangeSearch());
  }

  /**
   * Test {@link DBPDataKind#supportsRangeSearch()}.
   *
   * <ul>
   *   <li>Given {@code STRUCT}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBPDataKind#supportsRangeSearch()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPDataKind.supportsRangeSearch()"})
  public void testSupportsRangeSearch_givenStruct_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(DBPDataKind.STRUCT.supportsRangeSearch());
  }

  /**
   * Test {@link DBPDataKind#getCommonality()}.
   *
   * <ul>
   *   <li>Given {@code BINARY}.
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link DBPDataKind#getCommonality()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBPDataKind.getCommonality()"})
  public void testGetCommonality_givenBinary_thenReturnThree() {
    // Arrange, Act and Assert
    assertEquals(3, DBPDataKind.BINARY.getCommonality());
  }

  /**
   * Test {@link DBPDataKind#getCommonality()}.
   *
   * <ul>
   *   <li>Given {@code BOOLEAN}.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link DBPDataKind#getCommonality()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBPDataKind.getCommonality()"})
  public void testGetCommonality_givenBoolean_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(1, DBPDataKind.BOOLEAN.getCommonality());
  }

  /**
   * Test {@link DBPDataKind#getCommonality()}.
   *
   * <ul>
   *   <li>Given {@code DOCUMENT}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DBPDataKind#getCommonality()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBPDataKind.getCommonality()"})
  public void testGetCommonality_givenDocument_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, DBPDataKind.DOCUMENT.getCommonality());
  }

  /**
   * Test {@link DBPDataKind#getCommonality()}.
   *
   * <ul>
   *   <li>Given {@code STRING}.
   *   <li>Then return two.
   * </ul>
   *
   * <p>Method under test: {@link DBPDataKind#getCommonality()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBPDataKind.getCommonality()"})
  public void testGetCommonality_givenString_thenReturnTwo() {
    // Arrange, Act and Assert
    assertEquals(2, DBPDataKind.STRING.getCommonality());
  }

  /**
   * Test {@link DBPDataKind#canConsume(DBPDataKind, DBPDataKind)}.
   *
   * <ul>
   *   <li>When {@link DBPDataKind#BINARY}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBPDataKind#canConsume(DBPDataKind, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPDataKind.canConsume(DBPDataKind, DBPDataKind)"})
  public void testCanConsume_whenBinary_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(DBPDataKind.canConsume(DBPDataKind.BINARY, DBPDataKind.BINARY));
  }

  /**
   * Test {@link DBPDataKind#canConsume(DBPDataKind, DBPDataKind)}.
   *
   * <ul>
   *   <li>When {@code BOOLEAN}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBPDataKind#canConsume(DBPDataKind, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPDataKind.canConsume(DBPDataKind, DBPDataKind)"})
  public void testCanConsume_whenBoolean_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(DBPDataKind.canConsume(DBPDataKind.BOOLEAN, DBPDataKind.BINARY));
  }

  /**
   * Test {@link DBPDataKind#canConsume(DBPDataKind, DBPDataKind)}.
   *
   * <ul>
   *   <li>When {@code BOOLEAN}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBPDataKind#canConsume(DBPDataKind, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPDataKind.canConsume(DBPDataKind, DBPDataKind)"})
  public void testCanConsume_whenBoolean_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(DBPDataKind.canConsume(DBPDataKind.BOOLEAN, DBPDataKind.BOOLEAN));
  }

  /**
   * Test {@link DBPDataKind#canConsume(DBPDataKind, DBPDataKind)}.
   *
   * <ul>
   *   <li>When {@link DBPDataKind#CONTENT}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBPDataKind#canConsume(DBPDataKind, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPDataKind.canConsume(DBPDataKind, DBPDataKind)"})
  public void testCanConsume_whenContent_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(DBPDataKind.canConsume(DBPDataKind.STRING, DBPDataKind.CONTENT));
  }

  /**
   * Test {@link DBPDataKind#canConsume(DBPDataKind, DBPDataKind)}.
   *
   * <ul>
   *   <li>When {@link DBPDataKind#CONTENT}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBPDataKind#canConsume(DBPDataKind, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPDataKind.canConsume(DBPDataKind, DBPDataKind)"})
  public void testCanConsume_whenContent_thenReturnTrue2() {
    // Arrange, Act and Assert
    assertTrue(DBPDataKind.canConsume(DBPDataKind.CONTENT, DBPDataKind.BINARY));
  }

  /**
   * Test {@link DBPDataKind#canConsume(DBPDataKind, DBPDataKind)}.
   *
   * <ul>
   *   <li>When {@code NUMERIC}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBPDataKind#canConsume(DBPDataKind, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPDataKind.canConsume(DBPDataKind, DBPDataKind)"})
  public void testCanConsume_whenNumeric_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(DBPDataKind.canConsume(DBPDataKind.NUMERIC, DBPDataKind.BOOLEAN));
  }

  /**
   * Test {@link DBPDataKind#canConsume(DBPDataKind, DBPDataKind)}.
   *
   * <ul>
   *   <li>When {@link DBPDataKind#STRING}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBPDataKind#canConsume(DBPDataKind, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPDataKind.canConsume(DBPDataKind, DBPDataKind)"})
  public void testCanConsume_whenString_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(DBPDataKind.canConsume(DBPDataKind.BINARY, DBPDataKind.STRING));
  }

  /**
   * Test {@link DBPDataKind#canConsume(DBPDataKind, DBPDataKind)}.
   *
   * <ul>
   *   <li>When {@link DBPDataKind#STRING}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBPDataKind#canConsume(DBPDataKind, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPDataKind.canConsume(DBPDataKind, DBPDataKind)"})
  public void testCanConsume_whenString_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(DBPDataKind.canConsume(DBPDataKind.STRING, DBPDataKind.STRING));
  }

  /**
   * Test {@link DBPDataKind#canConsume(DBPDataKind, DBPDataKind)}.
   *
   * <ul>
   *   <li>When {@link DBPDataKind#STRING}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBPDataKind#canConsume(DBPDataKind, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBPDataKind.canConsume(DBPDataKind, DBPDataKind)"})
  public void testCanConsume_whenString_thenReturnTrue2() {
    // Arrange, Act and Assert
    assertTrue(DBPDataKind.canConsume(DBPDataKind.STRING, DBPDataKind.BINARY));
  }
}
