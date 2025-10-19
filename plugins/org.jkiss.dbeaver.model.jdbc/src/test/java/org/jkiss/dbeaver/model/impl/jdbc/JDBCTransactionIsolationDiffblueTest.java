package org.jkiss.dbeaver.model.impl.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCTransactionIsolationDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JDBCTransactionIsolation#getCode()}
   *   <li>{@link JDBCTransactionIsolation#getTitle()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int JDBCTransactionIsolation.getCode()",
    "java.lang.String JDBCTransactionIsolation.getTitle()"
  })
  public void testGettersAndSetters() {
    // Arrange
    JDBCTransactionIsolation valueOfResult = JDBCTransactionIsolation.valueOf("NONE");

    // Act
    int actualCode = valueOfResult.getCode();

    // Assert
    assertEquals("None", valueOfResult.getTitle());
    assertEquals(0, actualCode);
  }

  /**
   * Test {@link JDBCTransactionIsolation#isEnabled()}.
   *
   * <ul>
   *   <li>Given {@code NONE}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCTransactionIsolation#isEnabled()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCTransactionIsolation.isEnabled()"})
  public void testIsEnabled_givenNone_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(JDBCTransactionIsolation.NONE.isEnabled());
  }

  /**
   * Test {@link JDBCTransactionIsolation#isEnabled()}.
   *
   * <ul>
   *   <li>Given {@code READ_UNCOMMITTED}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCTransactionIsolation#isEnabled()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCTransactionIsolation.isEnabled()"})
  public void testIsEnabled_givenReadUncommitted_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(JDBCTransactionIsolation.READ_UNCOMMITTED.isEnabled());
  }

  /**
   * Test {@link JDBCTransactionIsolation#getByCode(int)}.
   *
   * <ul>
   *   <li>When five.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCTransactionIsolation#getByCode(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCTransactionIsolation JDBCTransactionIsolation.getByCode(int)"})
  public void testGetByCode_whenFive_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(JDBCTransactionIsolation.getByCode(5));
  }

  /**
   * Test {@link JDBCTransactionIsolation#getByCode(int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code READ_UNCOMMITTED}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCTransactionIsolation#getByCode(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCTransactionIsolation JDBCTransactionIsolation.getByCode(int)"})
  public void testGetByCode_whenOne_thenReturnReadUncommitted() {
    // Arrange, Act and Assert
    assertEquals(JDBCTransactionIsolation.READ_UNCOMMITTED, JDBCTransactionIsolation.getByCode(1));
  }
}
