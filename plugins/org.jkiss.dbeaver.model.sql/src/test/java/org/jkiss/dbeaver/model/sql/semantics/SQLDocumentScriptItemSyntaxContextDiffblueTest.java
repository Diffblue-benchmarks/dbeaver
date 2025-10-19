package org.jkiss.dbeaver.model.sql.semantics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.model.sql.semantics.model.SQLQueryModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLDocumentScriptItemSyntaxContextDiffblueTest {
  /**
   * Test {@link SQLDocumentScriptItemSyntaxContext#SQLDocumentScriptItemSyntaxContext(int, String,
   * SQLQueryModel, int)}.
   *
   * <p>Method under test: {@link
   * SQLDocumentScriptItemSyntaxContext#SQLDocumentScriptItemSyntaxContext(int, String,
   * SQLQueryModel, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLDocumentScriptItemSyntaxContext.<init>(int, String, SQLQueryModel, int)"
  })
  public void testNewSQLDocumentScriptItemSyntaxContext() {
    // Arrange and Act
    SQLDocumentScriptItemSyntaxContext actualSqlDocumentScriptItemSyntaxContext =
        new SQLDocumentScriptItemSyntaxContext(1, "Original Text", null, 3);

    // Assert
    assertEquals("Original Text", actualSqlDocumentScriptItemSyntaxContext.getOriginalText());
    assertNull(actualSqlDocumentScriptItemSyntaxContext.getProblems());
    assertNull(actualSqlDocumentScriptItemSyntaxContext.getQueryModel());
    assertEquals(1, actualSqlDocumentScriptItemSyntaxContext.getInitialPosition());
    assertEquals(3, actualSqlDocumentScriptItemSyntaxContext.length());
    assertFalse(actualSqlDocumentScriptItemSyntaxContext.isDirty());
    assertTrue(actualSqlDocumentScriptItemSyntaxContext.hasContextBoundaryAtLength());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLDocumentScriptItemSyntaxContext#setHasContextBoundaryAtLength(boolean)}
   *   <li>{@link SQLDocumentScriptItemSyntaxContext#setProblems(List)}
   *   <li>{@link SQLDocumentScriptItemSyntaxContext#refreshCompleted()}
   *   <li>{@link SQLDocumentScriptItemSyntaxContext#getInitialPosition()}
   *   <li>{@link SQLDocumentScriptItemSyntaxContext#getOriginalText()}
   *   <li>{@link SQLDocumentScriptItemSyntaxContext#getProblems()}
   *   <li>{@link SQLDocumentScriptItemSyntaxContext#getQueryModel()}
   *   <li>{@link SQLDocumentScriptItemSyntaxContext#hasContextBoundaryAtLength()}
   *   <li>{@link SQLDocumentScriptItemSyntaxContext#isDirty()}
   *   <li>{@link SQLDocumentScriptItemSyntaxContext#length()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int SQLDocumentScriptItemSyntaxContext.getInitialPosition()",
    "String SQLDocumentScriptItemSyntaxContext.getOriginalText()",
    "List SQLDocumentScriptItemSyntaxContext.getProblems()",
    "SQLQueryModel SQLDocumentScriptItemSyntaxContext.getQueryModel()",
    "boolean SQLDocumentScriptItemSyntaxContext.hasContextBoundaryAtLength()",
    "boolean SQLDocumentScriptItemSyntaxContext.isDirty()",
    "int SQLDocumentScriptItemSyntaxContext.length()",
    "void SQLDocumentScriptItemSyntaxContext.refreshCompleted()",
    "void SQLDocumentScriptItemSyntaxContext.setHasContextBoundaryAtLength(boolean)",
    "void SQLDocumentScriptItemSyntaxContext.setProblems(List)"
  })
  public void testGettersAndSetters() {
    // Arrange
    SQLDocumentScriptItemSyntaxContext sqlDocumentScriptItemSyntaxContext =
        new SQLDocumentScriptItemSyntaxContext(1, "Original Text", null, 3);

    // Act
    sqlDocumentScriptItemSyntaxContext.setHasContextBoundaryAtLength(true);
    ArrayList<SQLQueryRecognitionProblemInfo> problems = new ArrayList<>();
    sqlDocumentScriptItemSyntaxContext.setProblems(problems);
    sqlDocumentScriptItemSyntaxContext.refreshCompleted();
    int actualInitialPosition = sqlDocumentScriptItemSyntaxContext.getInitialPosition();
    String actualOriginalText = sqlDocumentScriptItemSyntaxContext.getOriginalText();
    List<SQLQueryRecognitionProblemInfo> actualProblems =
        sqlDocumentScriptItemSyntaxContext.getProblems();
    SQLQueryModel actualQueryModel = sqlDocumentScriptItemSyntaxContext.getQueryModel();
    boolean actualHasContextBoundaryAtLengthResult =
        sqlDocumentScriptItemSyntaxContext.hasContextBoundaryAtLength();
    boolean actualIsDirtyResult = sqlDocumentScriptItemSyntaxContext.isDirty();

    // Assert
    assertEquals("Original Text", actualOriginalText);
    assertNull(actualQueryModel);
    assertEquals(1, actualInitialPosition);
    assertEquals(3, sqlDocumentScriptItemSyntaxContext.length());
    assertFalse(actualIsDirtyResult);
    assertTrue(actualProblems.isEmpty());
    assertTrue(actualHasContextBoundaryAtLengthResult);
    assertSame(problems, actualProblems);
  }

  /**
   * Test {@link SQLDocumentScriptItemSyntaxContext#findToken(int)}.
   *
   * <p>Method under test: {@link SQLDocumentScriptItemSyntaxContext#findToken(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.sql.semantics.SQLTokenEntryAtOffset SQLDocumentScriptItemSyntaxContext.findToken(int)"
  })
  public void testFindToken() {
    // Arrange
    SQLDocumentScriptItemSyntaxContext sqlDocumentScriptItemSyntaxContext =
        new SQLDocumentScriptItemSyntaxContext(1, "Original Text", null, 3);

    // Act and Assert
    assertNull(sqlDocumentScriptItemSyntaxContext.findToken(2));
  }

  /**
   * Test {@link SQLDocumentScriptItemSyntaxContext#findToken(int)}.
   *
   * <p>Method under test: {@link SQLDocumentScriptItemSyntaxContext#findToken(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.sql.semantics.SQLTokenEntryAtOffset SQLDocumentScriptItemSyntaxContext.findToken(int)"
  })
  public void testFindToken2() {
    // Arrange
    SQLDocumentScriptItemSyntaxContext sqlDocumentScriptItemSyntaxContext =
        new SQLDocumentScriptItemSyntaxContext(1, "Original Text", null, 3);
    sqlDocumentScriptItemSyntaxContext.registerToken(2, null);

    // Act and Assert
    assertNull(sqlDocumentScriptItemSyntaxContext.findToken(2));
  }

  /**
   * Test {@link SQLDocumentScriptItemSyntaxContext#findToken(int)}.
   *
   * <p>Method under test: {@link SQLDocumentScriptItemSyntaxContext#findToken(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.sql.semantics.SQLTokenEntryAtOffset SQLDocumentScriptItemSyntaxContext.findToken(int)"
  })
  public void testFindToken3() {
    // Arrange
    SQLDocumentScriptItemSyntaxContext sqlDocumentScriptItemSyntaxContext =
        new SQLDocumentScriptItemSyntaxContext(1, "Original Text", null, 3);
    sqlDocumentScriptItemSyntaxContext.registerToken(1, null);

    // Act and Assert
    assertNull(sqlDocumentScriptItemSyntaxContext.findToken(2));
  }

  /**
   * Test {@link SQLDocumentScriptItemSyntaxContext#findToken(int)}.
   *
   * <ul>
   *   <li>When one.
   * </ul>
   *
   * <p>Method under test: {@link SQLDocumentScriptItemSyntaxContext#findToken(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.sql.semantics.SQLTokenEntryAtOffset SQLDocumentScriptItemSyntaxContext.findToken(int)"
  })
  public void testFindToken_whenOne() {
    // Arrange
    SQLDocumentScriptItemSyntaxContext sqlDocumentScriptItemSyntaxContext =
        new SQLDocumentScriptItemSyntaxContext(1, "Original Text", null, 3);
    sqlDocumentScriptItemSyntaxContext.registerToken(2, null);

    // Act and Assert
    assertNull(sqlDocumentScriptItemSyntaxContext.findToken(1));
  }

  /**
   * Test {@link SQLDocumentScriptItemSyntaxContext#registerToken(int, SQLQuerySymbolEntry)}.
   *
   * <p>Method under test: {@link SQLDocumentScriptItemSyntaxContext#registerToken(int,
   * SQLQuerySymbolEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLDocumentScriptItemSyntaxContext.registerToken(int, SQLQuerySymbolEntry)"
  })
  public void testRegisterToken() {
    // Arrange
    SQLDocumentScriptItemSyntaxContext sqlDocumentScriptItemSyntaxContext =
        new SQLDocumentScriptItemSyntaxContext(1, "Original Text", null, 3);

    // Act
    sqlDocumentScriptItemSyntaxContext.registerToken(2, null);

    // Assert
    assertTrue(sqlDocumentScriptItemSyntaxContext.isDirty());
  }

  /**
   * Test {@link SQLDocumentScriptItemSyntaxContext#applyDelta(int, int, int)}.
   *
   * <p>Method under test: {@link SQLDocumentScriptItemSyntaxContext#applyDelta(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDocumentScriptItemSyntaxContext.applyDelta(int, int, int)"})
  public void testApplyDelta() {
    // Arrange
    SQLDocumentScriptItemSyntaxContext sqlDocumentScriptItemSyntaxContext =
        new SQLDocumentScriptItemSyntaxContext(1, "Original Text", null, 3);

    // Act
    sqlDocumentScriptItemSyntaxContext.applyDelta(2, 0, 3);

    // Assert
    assertEquals(6, sqlDocumentScriptItemSyntaxContext.length());
    assertTrue(sqlDocumentScriptItemSyntaxContext.isDirty());
  }

  /**
   * Test {@link SQLDocumentScriptItemSyntaxContext#applyDelta(int, int, int)}.
   *
   * <p>Method under test: {@link SQLDocumentScriptItemSyntaxContext#applyDelta(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDocumentScriptItemSyntaxContext.applyDelta(int, int, int)"})
  public void testApplyDelta2() {
    // Arrange
    SQLDocumentScriptItemSyntaxContext sqlDocumentScriptItemSyntaxContext =
        new SQLDocumentScriptItemSyntaxContext(1, "Original Text", null, 3);
    sqlDocumentScriptItemSyntaxContext.registerToken(2, null);

    // Act
    sqlDocumentScriptItemSyntaxContext.applyDelta(2, 0, 3);

    // Assert
    assertEquals(6, sqlDocumentScriptItemSyntaxContext.length());
    assertTrue(sqlDocumentScriptItemSyntaxContext.isDirty());
  }

  /**
   * Test {@link SQLDocumentScriptItemSyntaxContext#applyDelta(int, int, int)}.
   *
   * <p>Method under test: {@link SQLDocumentScriptItemSyntaxContext#applyDelta(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDocumentScriptItemSyntaxContext.applyDelta(int, int, int)"})
  public void testApplyDelta3() {
    // Arrange
    SQLDocumentScriptItemSyntaxContext sqlDocumentScriptItemSyntaxContext =
        new SQLDocumentScriptItemSyntaxContext(1, "Original Text", null, 3);

    // Act
    sqlDocumentScriptItemSyntaxContext.applyDelta(2, 0, 0);

    // Assert
    assertEquals(3, sqlDocumentScriptItemSyntaxContext.length());
    assertTrue(sqlDocumentScriptItemSyntaxContext.isDirty());
  }

  /**
   * Test {@link SQLDocumentScriptItemSyntaxContext#applyDelta(int, int, int)}.
   *
   * <p>Method under test: {@link SQLDocumentScriptItemSyntaxContext#applyDelta(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDocumentScriptItemSyntaxContext.applyDelta(int, int, int)"})
  public void testApplyDelta4() {
    // Arrange
    SQLDocumentScriptItemSyntaxContext sqlDocumentScriptItemSyntaxContext =
        new SQLDocumentScriptItemSyntaxContext(1, "Original Text", null, 3);
    sqlDocumentScriptItemSyntaxContext.registerToken(3, null);

    // Act
    sqlDocumentScriptItemSyntaxContext.applyDelta(2, 0, 3);

    // Assert
    assertEquals(6, sqlDocumentScriptItemSyntaxContext.length());
    assertTrue(sqlDocumentScriptItemSyntaxContext.isDirty());
  }

  /**
   * Test {@link SQLDocumentScriptItemSyntaxContext#applyDelta(int, int, int)}.
   *
   * <p>Method under test: {@link SQLDocumentScriptItemSyntaxContext#applyDelta(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDocumentScriptItemSyntaxContext.applyDelta(int, int, int)"})
  public void testApplyDelta5() {
    // Arrange
    SQLDocumentScriptItemSyntaxContext sqlDocumentScriptItemSyntaxContext =
        new SQLDocumentScriptItemSyntaxContext(1, "Original Text", null, 3);
    sqlDocumentScriptItemSyntaxContext.registerToken(1, null);

    // Act
    sqlDocumentScriptItemSyntaxContext.applyDelta(2, 0, 3);

    // Assert
    assertEquals(6, sqlDocumentScriptItemSyntaxContext.length());
    assertTrue(sqlDocumentScriptItemSyntaxContext.isDirty());
  }

  /**
   * Test {@link SQLDocumentScriptItemSyntaxContext#applyDelta(int, int, int)}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link SQLDocumentScriptItemSyntaxContext#applyDelta(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDocumentScriptItemSyntaxContext.applyDelta(int, int, int)"})
  public void testApplyDelta_thenThrowUnsupportedOperationException() {
    // Arrange
    SQLDocumentScriptItemSyntaxContext sqlDocumentScriptItemSyntaxContext =
        new SQLDocumentScriptItemSyntaxContext(1, "Original Text", null, 3);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> sqlDocumentScriptItemSyntaxContext.applyDelta(2, 3, 3));
  }
}
