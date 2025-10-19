package org.jkiss.dbeaver.model.sql.semantics.completion;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.semantics.completion.SQLQueryCompletionItem.SQLBuiltinFunctionCompletionItem;
import org.jkiss.dbeaver.model.sql.semantics.completion.SQLQueryCompletionItem.SQLReservedWordCompletionItem;
import org.jkiss.dbeaver.model.sql.semantics.completion.SQLQueryCompletionItem.SQLSpecialTextCompletionItem;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLQueryCompletionDescriptionProviderDiffblueTest {
  /**
   * Test {@link
   * SQLQueryCompletionDescriptionProvider#visitReservedWord(SQLReservedWordCompletionItem)}.
   *
   * <p>Method under test: {@link
   * SQLQueryCompletionDescriptionProvider#visitReservedWord(SQLReservedWordCompletionItem)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLQueryCompletionDescriptionProvider.visitReservedWord(SQLReservedWordCompletionItem)"
  })
  public void testVisitReservedWord() {
    // Arrange
    SQLReservedWordCompletionItem reservedWord =
        new SQLReservedWordCompletionItem(3, new SQLQueryWordEntry(2, "String"), "Text");

    // Act
    String actualVisitReservedWordResult =
        SQLQueryCompletionDescriptionProvider.INSTANCE.visitReservedWord(reservedWord);

    // Assert
    assertEquals("Reserved word of the query language", actualVisitReservedWordResult);
  }

  /**
   * Test {@link
   * SQLQueryCompletionDescriptionProvider#visitBuiltinFunction(SQLBuiltinFunctionCompletionItem)}.
   *
   * <p>Method under test: {@link
   * SQLQueryCompletionDescriptionProvider#visitBuiltinFunction(SQLBuiltinFunctionCompletionItem)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLQueryCompletionDescriptionProvider.visitBuiltinFunction(SQLBuiltinFunctionCompletionItem)"
  })
  public void testVisitBuiltinFunction() {
    // Arrange, Act and Assert
    assertEquals(
        "Builtin function of the database.",
        SQLQueryCompletionDescriptionProvider.INSTANCE.visitBuiltinFunction(
            mock(SQLBuiltinFunctionCompletionItem.class)));
  }

  /**
   * Test {@link
   * SQLQueryCompletionDescriptionProvider#visitSpecialText(SQLSpecialTextCompletionItem)}.
   *
   * <ul>
   *   <li>Then return {@code The characteristics of someone or something}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryCompletionDescriptionProvider#visitSpecialText(SQLSpecialTextCompletionItem)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLQueryCompletionDescriptionProvider.visitSpecialText(SQLSpecialTextCompletionItem)"
  })
  public void testVisitSpecialText_thenReturnTheCharacteristicsOfSomeoneOrSomething() {
    // Arrange
    SQLSpecialTextCompletionItem specialText =
        new SQLSpecialTextCompletionItem(
            3,
            new SQLQueryWordEntry(2, "String"),
            "Text",
            "The characteristics of someone or something");

    // Act
    String actualVisitSpecialTextResult =
        SQLQueryCompletionDescriptionProvider.INSTANCE.visitSpecialText(specialText);

    // Assert
    assertEquals("The characteristics of someone or something", actualVisitSpecialTextResult);
  }
}
