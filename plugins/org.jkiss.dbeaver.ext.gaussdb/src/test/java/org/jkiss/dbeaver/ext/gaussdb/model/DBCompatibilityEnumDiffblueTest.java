package org.jkiss.dbeaver.ext.gaussdb.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBCompatibilityEnumDiffblueTest {
  /**
   * Test {@link DBCompatibilityEnum#getText()}.
   *
   * <p>Method under test: {@link DBCompatibilityEnum#getText()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBCompatibilityEnum.getText()"})
  public void testGetText() {
    // Arrange, Act and Assert
    assertEquals("Oracle", DBCompatibilityEnum.valueOf("ORACLE").getText());
  }

  /**
   * Test {@link DBCompatibilityEnum#getcValue()}.
   *
   * <p>Method under test: {@link DBCompatibilityEnum#getcValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBCompatibilityEnum.getcValue()"})
  public void testGetcValue() {
    // Arrange, Act and Assert
    assertEquals("A", DBCompatibilityEnum.ORACLE.getcValue());
  }

  /**
   * Test {@link DBCompatibilityEnum#getdValue()}.
   *
   * <p>Method under test: {@link DBCompatibilityEnum#getdValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBCompatibilityEnum.getdValue()"})
  public void testGetdValue() {
    // Arrange, Act and Assert
    assertEquals("ORA", DBCompatibilityEnum.ORACLE.getdValue());
  }

  /**
   * Test {@link DBCompatibilityEnum#of(String)}.
   *
   * <ul>
   *   <li>When {@code Oracle}.
   *   <li>Then return {@code ORACLE}.
   * </ul>
   *
   * <p>Method under test: {@link DBCompatibilityEnum#of(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCompatibilityEnum DBCompatibilityEnum.of(String)"})
  public void testOf_whenOracle_thenReturnOracle() {
    // Arrange, Act and Assert
    assertEquals(DBCompatibilityEnum.ORACLE, DBCompatibilityEnum.of("Oracle"));
  }

  /**
   * Test {@link DBCompatibilityEnum#of(String)}.
   *
   * <ul>
   *   <li>When {@code Text}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBCompatibilityEnum#of(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCompatibilityEnum DBCompatibilityEnum.of(String)"})
  public void testOf_whenText_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DBCompatibilityEnum.of("Text"));
  }

  /**
   * Test {@link DBCompatibilityEnum#queryTextByValue(String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link DBCompatibilityEnum#queryTextByValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBCompatibilityEnum.queryTextByValue(String)"})
  public void testQueryTextByValue_when42_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", DBCompatibilityEnum.queryTextByValue("42"));
  }

  /**
   * Test {@link DBCompatibilityEnum#queryTextByValue(String)}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return {@code Oracle}.
   * </ul>
   *
   * <p>Method under test: {@link DBCompatibilityEnum#queryTextByValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBCompatibilityEnum.queryTextByValue(String)"})
  public void testQueryTextByValue_whenA_thenReturnOracle() {
    // Arrange, Act and Assert
    assertEquals("Oracle", DBCompatibilityEnum.queryTextByValue("A"));
  }

  /**
   * Test {@link DBCompatibilityEnum#queryTextByValue(String)}.
   *
   * <ul>
   *   <li>When {@code MYSQL}.
   *   <li>Then return {@code MySQL}.
   * </ul>
   *
   * <p>Method under test: {@link DBCompatibilityEnum#queryTextByValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBCompatibilityEnum.queryTextByValue(String)"})
  public void testQueryTextByValue_whenMysql_thenReturnMySQL() {
    // Arrange, Act and Assert
    assertEquals("MySQL", DBCompatibilityEnum.queryTextByValue("MYSQL"));
  }
}
