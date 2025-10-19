package org.jkiss.dbeaver.model.data.aggregate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FunctionNumericDiffblueTest {
  /**
   * Test {@link FunctionNumeric#getNumeric(Object)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link FunctionNumeric#getNumeric(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number FunctionNumeric.getNumeric(Object)"})
  public void testGetNumeric_when42_thenReturnDoubleValueIsFortyTwo() {
    // Arrange, Act and Assert
    assertEquals(42.0d, FunctionNumeric.getNumeric("42").doubleValue(), 0.0);
  }

  /**
   * Test {@link FunctionNumeric#getNumeric(Object)}.
   *
   * <ul>
   *   <li>When forty-two.
   *   <li>Then return intValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link FunctionNumeric#getNumeric(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number FunctionNumeric.getNumeric(Object)"})
  public void testGetNumeric_whenFortyTwo_thenReturnIntValueIsFortyTwo() {
    // Arrange and Act
    Number actualNumeric = FunctionNumeric.getNumeric(42);

    // Assert
    assertEquals(42, actualNumeric.intValue());
  }

  /**
   * Test {@link FunctionNumeric#getNumeric(Object)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionNumeric#getNumeric(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number FunctionNumeric.getNumeric(Object)"})
  public void testGetNumeric_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(FunctionNumeric.getNumeric(null));
  }

  /**
   * Test {@link FunctionNumeric#getNumeric(Object)}.
   *
   * <ul>
   *   <li>When {@link DBPEvent#RENAME}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionNumeric#getNumeric(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number FunctionNumeric.getNumeric(Object)"})
  public void testGetNumeric_whenRename_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(FunctionNumeric.getNumeric(DBPEvent.RENAME));
  }

  /**
   * Test {@link FunctionNumeric#getComparable(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link FunctionNumeric#getComparable(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Comparable FunctionNumeric.getComparable(Object, boolean)"})
  public void testGetComparable_when42_thenReturnDoubleValueIsFortyTwo() {
    // Arrange and Act
    Comparable<?> actualComparable = FunctionNumeric.getComparable("42", false);

    // Assert
    assertEquals(42.0d, ((Double) actualComparable).doubleValue(), 0.0);
  }

  /**
   * Test {@link FunctionNumeric#getComparable(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionNumeric#getComparable(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Comparable FunctionNumeric.getComparable(Object, boolean)"})
  public void testGetComparable_whenNull_thenNull() {
    // Arrange and Act
    Comparable<?> actualComparable = FunctionNumeric.getComparable(null, false);

    // Assert
    assertNull(actualComparable);
    assertNull(null);
  }

  /**
   * Test {@link FunctionNumeric#getComparable(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@link DBPEvent#RENAME}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionNumeric#getComparable(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Comparable FunctionNumeric.getComparable(Object, boolean)"})
  public void testGetComparable_whenRename_thenReturnNull() {
    // Arrange and Act
    Comparable<?> actualComparable = FunctionNumeric.getComparable(DBPEvent.RENAME, true);

    // Assert
    assertNull(actualComparable);
  }

  /**
   * Test {@link FunctionNumeric#getComparable(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@link DBPEvent#RENAME}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionNumeric#getComparable(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Comparable FunctionNumeric.getComparable(Object, boolean)"})
  public void testGetComparable_whenRename_thenReturnNull2() {
    // Arrange and Act
    Comparable<?> actualComparable = FunctionNumeric.getComparable(DBPEvent.RENAME, false);

    // Assert
    assertNull(actualComparable);
  }

  /**
   * Test {@link FunctionNumeric#getComparable(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionNumeric#getComparable(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Comparable FunctionNumeric.getComparable(Object, boolean)"})
  public void testGetComparable_whenTrue_thenReturnFalse() {
    // Arrange and Act
    Comparable<?> actualComparable = FunctionNumeric.getComparable(false, true);

    // Assert
    assertFalse((Boolean) actualComparable);
  }

  /**
   * Test {@link FunctionNumeric#getComparable(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionNumeric#getComparable(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Comparable FunctionNumeric.getComparable(Object, boolean)"})
  public void testGetComparable_whenTrue_thenReturnTrue() {
    // Arrange and Act
    Comparable<?> actualComparable = FunctionNumeric.getComparable(true, true);

    // Assert
    assertTrue((Boolean) actualComparable);
  }
}
