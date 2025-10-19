package org.jkiss.dbeaver.model.sql.semantics.completion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.Function;
import java.util.function.Supplier;
import org.jkiss.dbeaver.model.DBIcon;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.sql.completion.SQLCompletionRequest;
import org.jkiss.dbeaver.model.sql.semantics.completion.SQLQueryCompletionItem.SQLReservedWordCompletionItem;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SQLQueryCompletionAnalyzerDiffblueTest {
  @Mock private SQLCompletionRequest sQLCompletionRequest;

  @InjectMocks private SQLQueryCompletionAnalyzer sQLQueryCompletionAnalyzer;

  /**
   * Test {@link SQLQueryCompletionAnalyzer#SQLQueryCompletionAnalyzer(Function,
   * SQLCompletionRequest, Supplier)}.
   *
   * <p>Method under test: {@link SQLQueryCompletionAnalyzer#SQLQueryCompletionAnalyzer(Function,
   * SQLCompletionRequest, Supplier)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLQueryCompletionAnalyzer.<init>(Function, SQLCompletionRequest, Supplier)"
  })
  public void testNewSQLQueryCompletionAnalyzer() {
    // Arrange and Act
    SQLQueryCompletionAnalyzer actualSqlQueryCompletionAnalyzer =
        new SQLQueryCompletionAnalyzer(
            mock(Function.class), sQLCompletionRequest, mock(Supplier.class));

    // Assert
    assertNull(actualSqlQueryCompletionAnalyzer.getActualContextOffset());
    assertTrue(actualSqlQueryCompletionAnalyzer.getResult().isEmpty());
  }

  /**
   * Test {@link SQLQueryCompletionAnalyzer#getResult()}.
   *
   * <p>Method under test: {@link SQLQueryCompletionAnalyzer#getResult()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.List SQLQueryCompletionAnalyzer.getResult()"})
  public void testGetResult() {
    // Arrange, Act and Assert
    assertTrue(sQLQueryCompletionAnalyzer.getResult().isEmpty());
  }

  /**
   * Test {@link SQLQueryCompletionAnalyzer#getActualContextOffset()}.
   *
   * <p>Method under test: {@link SQLQueryCompletionAnalyzer#getActualContextOffset()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Integer SQLQueryCompletionAnalyzer.getActualContextOffset()"})
  public void testGetActualContextOffset() {
    // Arrange, Act and Assert
    assertNull(sQLQueryCompletionAnalyzer.getActualContextOffset());
  }

  /**
   * Test {@link SQLQueryCompletionAnalyzer#prepareProposalImage(SQLQueryCompletionItem)}.
   *
   * <ul>
   *   <li>Then return {@link DBIcon}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryCompletionAnalyzer#prepareProposalImage(SQLQueryCompletionItem)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPImage SQLQueryCompletionAnalyzer.prepareProposalImage(SQLQueryCompletionItem)"
  })
  public void testPrepareProposalImage_thenReturnDBIcon() {
    // Arrange
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");
    SQLReservedWordCompletionItem item = new SQLReservedWordCompletionItem(3, filterKey, "Text");

    // Act
    DBPImage actualPrepareProposalImageResult =
        sQLQueryCompletionAnalyzer.prepareProposalImage(item);
    String actualLocation = actualPrepareProposalImageResult.getLocation();

    // Assert
    assertTrue(actualPrepareProposalImageResult instanceof DBIcon);
    assertEquals("Text", item.text);
    assertEquals("sql/sql_text.svg", actualPrepareProposalImageResult.getLocation());
    assertEquals("sql/sql_text.svg", actualLocation);
    assertEquals("sql_text", ((DBIcon) actualPrepareProposalImageResult).getToken());
    assertNull(sQLQueryCompletionAnalyzer.getActualContextOffset());
    assertNull(item.getObject());
    assertEquals(3, item.getScore());
    assertEquals(SQLQueryCompletionItemKind.RESERVED, item.getKind());
    assertTrue(sQLQueryCompletionAnalyzer.getResult().isEmpty());
    assertSame(filterKey, item.getFilterInfo());
  }

  /**
   * Test {@link SQLQueryCompletionAnalyzer#prepareProposalImage(SQLQueryCompletionItem)}.
   *
   * <ul>
   *   <li>Then return {@link DBIcon#SQL_TEXT}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryCompletionAnalyzer#prepareProposalImage(SQLQueryCompletionItem)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPImage SQLQueryCompletionAnalyzer.prepareProposalImage(SQLQueryCompletionItem)"
  })
  public void testPrepareProposalImage_thenReturnSql_text() {
    // Arrange
    SQLQueryWordEntry filterKey = new SQLQueryWordEntry(2, "String");
    SQLReservedWordCompletionItem item = new SQLReservedWordCompletionItem(3, filterKey, "Text");

    // Act
    DBPImage actualPrepareProposalImageResult =
        sQLQueryCompletionAnalyzer.prepareProposalImage(item);

    // Assert
    assertEquals("Text", item.text);
    assertNull(sQLQueryCompletionAnalyzer.getActualContextOffset());
    assertNull(item.getObject());
    assertEquals(3, item.getScore());
    assertEquals(SQLQueryCompletionItemKind.RESERVED, item.getKind());
    assertTrue(sQLQueryCompletionAnalyzer.getResult().isEmpty());
    assertSame(filterKey, item.getFilterInfo());
    assertSame(
        ((DBIcon) actualPrepareProposalImageResult).SQL_TEXT, actualPrepareProposalImageResult);
  }
}
