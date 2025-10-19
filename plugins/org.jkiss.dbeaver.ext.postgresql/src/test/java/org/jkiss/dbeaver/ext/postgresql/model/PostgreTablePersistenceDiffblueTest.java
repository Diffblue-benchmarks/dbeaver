package org.jkiss.dbeaver.ext.postgresql.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostgreTablePersistenceDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PostgreTablePersistence#getCode()}
   *   <li>{@link PostgreTablePersistence#getTableTypeClause()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "char PostgreTablePersistence.getCode()",
    "String PostgreTablePersistence.getTableTypeClause()"
  })
  public void testGettersAndSetters() {
    // Arrange
    PostgreTablePersistence valueOfResult = PostgreTablePersistence.valueOf("PERMANENT");

    // Act
    char actualCode = valueOfResult.getCode();

    // Assert
    assertEquals("TABLE", valueOfResult.getTableTypeClause());
    assertEquals('p', actualCode);
  }

  /**
   * Test {@link PostgreTablePersistence#getByCode(String)} with {@code code}.
   *
   * <ul>
   *   <li>When {@code Code}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreTablePersistence#getByCode(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PostgreTablePersistence PostgreTablePersistence.getByCode(String)"})
  public void testGetByCodeWithCode_whenCode() {
    // Arrange, Act and Assert
    assertEquals(PostgreTablePersistence.UNKNOWN, PostgreTablePersistence.getByCode("Code"));
  }

  /**
   * Test {@link PostgreTablePersistence#getByCode(String)} with {@code code}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreTablePersistence#getByCode(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PostgreTablePersistence PostgreTablePersistence.getByCode(String)"})
  public void testGetByCodeWithCode_whenNull() {
    // Arrange, Act and Assert
    assertEquals(PostgreTablePersistence.UNKNOWN, PostgreTablePersistence.getByCode(null));
  }

  /**
   * Test {@link PostgreTablePersistence#getByCode(String)} with {@code code}.
   *
   * <ul>
   *   <li>When {@code ?}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreTablePersistence#getByCode(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PostgreTablePersistence PostgreTablePersistence.getByCode(String)"})
  public void testGetByCodeWithCode_whenQuestionMark() {
    // Arrange, Act and Assert
    assertEquals(PostgreTablePersistence.UNKNOWN, PostgreTablePersistence.getByCode("?"));
  }

  /**
   * Test {@link PostgreTablePersistence#getByCode(char)} with {@code pCode}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return {@code UNKNOWN}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreTablePersistence#getByCode(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PostgreTablePersistence PostgreTablePersistence.getByCode(char)"})
  public void testGetByCodeWithPCode_whenA_thenReturnUnknown() {
    // Arrange, Act and Assert
    assertEquals(PostgreTablePersistence.UNKNOWN, PostgreTablePersistence.getByCode('A'));
  }

  /**
   * Test {@link PostgreTablePersistence#getByCode(char)} with {@code pCode}.
   *
   * <ul>
   *   <li>When {@code p}.
   *   <li>Then return {@code PERMANENT}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreTablePersistence#getByCode(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PostgreTablePersistence PostgreTablePersistence.getByCode(char)"})
  public void testGetByCodeWithPCode_whenP_thenReturnPermanent() {
    // Arrange, Act and Assert
    assertEquals(PostgreTablePersistence.PERMANENT, PostgreTablePersistence.getByCode('p'));
  }
}
