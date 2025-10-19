package org.jkiss.dbeaver.model.sql.semantics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLScriptItemAtOffsetDiffblueTest {
  /**
   * Test {@link SQLScriptItemAtOffset#SQLScriptItemAtOffset(int,
   * SQLDocumentScriptItemSyntaxContext)}.
   *
   * <p>Method under test: {@link SQLScriptItemAtOffset#SQLScriptItemAtOffset(int,
   * SQLDocumentScriptItemSyntaxContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptItemAtOffset.<init>(int, SQLDocumentScriptItemSyntaxContext)"})
  public void testNewSQLScriptItemAtOffset() {
    // Arrange
    SQLDocumentScriptItemSyntaxContext item =
        new SQLDocumentScriptItemSyntaxContext(1, "Original Text", null, 3);

    // Act
    SQLScriptItemAtOffset actualSqlScriptItemAtOffset = new SQLScriptItemAtOffset(2, item);

    // Assert
    SQLDocumentScriptItemSyntaxContext sqlDocumentScriptItemSyntaxContext =
        actualSqlScriptItemAtOffset.item;
    assertEquals("Original Text", sqlDocumentScriptItemSyntaxContext.getOriginalText());
    assertNull(sqlDocumentScriptItemSyntaxContext.getProblems());
    assertNull(sqlDocumentScriptItemSyntaxContext.getQueryModel());
    assertEquals(1, sqlDocumentScriptItemSyntaxContext.getInitialPosition());
    assertEquals(3, sqlDocumentScriptItemSyntaxContext.length());
    assertFalse(sqlDocumentScriptItemSyntaxContext.isDirty());
    assertTrue(sqlDocumentScriptItemSyntaxContext.hasContextBoundaryAtLength());
  }
}
