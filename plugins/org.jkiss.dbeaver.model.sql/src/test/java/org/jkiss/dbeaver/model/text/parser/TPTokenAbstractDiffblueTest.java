package org.jkiss.dbeaver.model.text.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TPTokenAbstractDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link TPTokenAbstract#TPTokenAbstract(int, Object)}
   *   <li>{@link TPTokenAbstract#getData()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TPTokenAbstract.<init>(int, Object)",
    "Object TPTokenAbstract.getData()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    TPTokenAbstract<Object> actualTpTokenAbstract = new TPTokenAbstract<>(1, "Data");

    // Assert
    assertEquals("Data", actualTpTokenAbstract.getData());
  }

  /**
   * Test {@link TPTokenAbstract#isOther()}.
   *
   * <ul>
   *   <li>Given {@link TPTokenAbstract#TPTokenAbstract(int, Object)} with type is one and {@code
   *       Data}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link TPTokenAbstract#isOther()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TPTokenAbstract.isOther()"})
  public void testIsOther_givenTPTokenAbstractWithTypeIsOneAndData_thenReturnFalse() {
    // Arrange
    TPTokenAbstract<Object> tpTokenAbstract = new TPTokenAbstract<>(1, "Data");

    // Act and Assert
    assertFalse(tpTokenAbstract.isOther());
  }

  /**
   * Test {@link TPTokenAbstract#isOther()}.
   *
   * <ul>
   *   <li>Given {@link TPTokenAbstract#TPTokenAbstract(int, Object)} with type is three and {@code
   *       Data}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TPTokenAbstract#isOther()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TPTokenAbstract.isOther()"})
  public void testIsOther_givenTPTokenAbstractWithTypeIsThreeAndData_thenReturnTrue() {
    // Arrange
    TPTokenAbstract<Object> tpTokenAbstract = new TPTokenAbstract<>(3, "Data");

    // Act and Assert
    assertTrue(tpTokenAbstract.isOther());
  }

  /**
   * Test {@link TPTokenAbstract#isEOF()}.
   *
   * <ul>
   *   <li>Given {@link TPTokenAbstract#TPTokenAbstract(int, Object)} with type is one and {@code
   *       Data}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TPTokenAbstract#isEOF()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TPTokenAbstract.isEOF()"})
  public void testIsEOF_givenTPTokenAbstractWithTypeIsOneAndData_thenReturnTrue() {
    // Arrange
    TPTokenAbstract<Object> tpTokenAbstract = new TPTokenAbstract<>(1, "Data");

    // Act and Assert
    assertTrue(tpTokenAbstract.isEOF());
  }

  /**
   * Test {@link TPTokenAbstract#isEOF()}.
   *
   * <ul>
   *   <li>Given {@link TPTokenAbstract#TPTokenAbstract(int, Object)} with type is zero and {@code
   *       Data}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link TPTokenAbstract#isEOF()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TPTokenAbstract.isEOF()"})
  public void testIsEOF_givenTPTokenAbstractWithTypeIsZeroAndData_thenReturnFalse() {
    // Arrange
    TPTokenAbstract<Object> tpTokenAbstract = new TPTokenAbstract<>(0, "Data");

    // Act and Assert
    assertFalse(tpTokenAbstract.isEOF());
  }

  /**
   * Test {@link TPTokenAbstract#isWhitespace()}.
   *
   * <ul>
   *   <li>Given {@link TPTokenAbstract#TPTokenAbstract(int, Object)} with type is one and {@code
   *       Data}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link TPTokenAbstract#isWhitespace()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TPTokenAbstract.isWhitespace()"})
  public void testIsWhitespace_givenTPTokenAbstractWithTypeIsOneAndData_thenReturnFalse() {
    // Arrange
    TPTokenAbstract<Object> tpTokenAbstract = new TPTokenAbstract<>(1, "Data");

    // Act and Assert
    assertFalse(tpTokenAbstract.isWhitespace());
  }

  /**
   * Test {@link TPTokenAbstract#isWhitespace()}.
   *
   * <ul>
   *   <li>Given {@link TPTokenAbstract#TPTokenAbstract(int, Object)} with type is two and {@code
   *       Data}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TPTokenAbstract#isWhitespace()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TPTokenAbstract.isWhitespace()"})
  public void testIsWhitespace_givenTPTokenAbstractWithTypeIsTwoAndData_thenReturnTrue() {
    // Arrange
    TPTokenAbstract<Object> tpTokenAbstract = new TPTokenAbstract<>(2, "Data");

    // Act and Assert
    assertTrue(tpTokenAbstract.isWhitespace());
  }

  /**
   * Test {@link TPTokenAbstract#isUndefined()}.
   *
   * <ul>
   *   <li>Given {@link TPTokenAbstract#TPTokenAbstract(int, Object)} with type is one and {@code
   *       Data}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link TPTokenAbstract#isUndefined()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TPTokenAbstract.isUndefined()"})
  public void testIsUndefined_givenTPTokenAbstractWithTypeIsOneAndData_thenReturnFalse() {
    // Arrange
    TPTokenAbstract<Object> tpTokenAbstract = new TPTokenAbstract<>(1, "Data");

    // Act and Assert
    assertFalse(tpTokenAbstract.isUndefined());
  }

  /**
   * Test {@link TPTokenAbstract#isUndefined()}.
   *
   * <ul>
   *   <li>Given {@link TPTokenAbstract#TPTokenAbstract(int, Object)} with type is zero and {@code
   *       Data}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TPTokenAbstract#isUndefined()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TPTokenAbstract.isUndefined()"})
  public void testIsUndefined_givenTPTokenAbstractWithTypeIsZeroAndData_thenReturnTrue() {
    // Arrange
    TPTokenAbstract<Object> tpTokenAbstract = new TPTokenAbstract<>(0, "Data");

    // Act and Assert
    assertTrue(tpTokenAbstract.isUndefined());
  }
}
