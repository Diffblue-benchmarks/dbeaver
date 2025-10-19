package org.jkiss.dbeaver.model.sql.semantics.completion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.semantics.context.SQLQueryExprType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLQueryCompletionExtraTextProviderDiffblueTest {
  /**
   * Test {@link SQLQueryCompletionExtraTextProvider#prepareTypeNameString(SQLQueryExprType)}.
   *
   * <ul>
   *   <li>When {@link SQLQueryExprType#BOOLEAN}.
   *   <li>Then return {@code BOOLEAN}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryCompletionExtraTextProvider#prepareTypeNameString(SQLQueryExprType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String SQLQueryCompletionExtraTextProvider.prepareTypeNameString(SQLQueryExprType)"
  })
  public void testPrepareTypeNameString_whenBoolean_thenReturnBoolean() {
    // Arrange, Act and Assert
    assertEquals(
        "BOOLEAN",
        SQLQueryCompletionExtraTextProvider.prepareTypeNameString(SQLQueryExprType.BOOLEAN));
  }

  /**
   * Test {@link SQLQueryCompletionExtraTextProvider#prepareTypeNameString(SQLQueryExprType)}.
   *
   * <ul>
   *   <li>When {@link SQLQueryExprType#DUMMY}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryCompletionExtraTextProvider#prepareTypeNameString(SQLQueryExprType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String SQLQueryCompletionExtraTextProvider.prepareTypeNameString(SQLQueryExprType)"
  })
  public void testPrepareTypeNameString_whenDummy_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(SQLQueryCompletionExtraTextProvider.prepareTypeNameString(SQLQueryExprType.DUMMY));
  }

  /**
   * Test {@link SQLQueryCompletionExtraTextProvider#prepareTypeNameString(SQLQueryExprType)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryCompletionExtraTextProvider#prepareTypeNameString(SQLQueryExprType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String SQLQueryCompletionExtraTextProvider.prepareTypeNameString(SQLQueryExprType)"
  })
  public void testPrepareTypeNameString_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(SQLQueryCompletionExtraTextProvider.prepareTypeNameString(null));
  }

  /**
   * Test {@link SQLQueryCompletionExtraTextProvider#prepareTypeNameString(SQLQueryExprType)}.
   *
   * <ul>
   *   <li>When {@link SQLQueryExprType#UNKNOWN}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryCompletionExtraTextProvider#prepareTypeNameString(SQLQueryExprType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String SQLQueryCompletionExtraTextProvider.prepareTypeNameString(SQLQueryExprType)"
  })
  public void testPrepareTypeNameString_whenUnknown_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(SQLQueryCompletionExtraTextProvider.prepareTypeNameString(SQLQueryExprType.UNKNOWN));
  }
}
