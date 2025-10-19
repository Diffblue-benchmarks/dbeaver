package org.jkiss.dbeaver.model.sql.semantics.completion;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.jface.text.Document;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.SQLQuery;
import org.jkiss.dbeaver.model.sql.SQLSyntaxManager;
import org.jkiss.dbeaver.model.sql.completion.SQLCompletionActivityTracker;
import org.jkiss.dbeaver.model.sql.completion.SQLCompletionContext;
import org.jkiss.dbeaver.model.sql.completion.SQLCompletionRequest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLQueryCompletionProposalContextDiffblueTest {
  /**
   * Test {@link SQLQueryCompletionProposalContext#getCompletionContext()}.
   *
   * <ul>
   *   <li>Then calls {@link SQLDialect#getStringQuoteStrings()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryCompletionProposalContext#getCompletionContext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLCompletionContext SQLQueryCompletionProposalContext.getCompletionContext()"
  })
  public void testGetCompletionContext_thenCallsGetStringQuoteStrings() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStructSeparator()).thenReturn('A');
    when(sqlSyntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLCompletionContext context = mock(SQLCompletionContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    Document document = new Document();
    SQLQuery activeQuery = new SQLQuery(mock(DBPDataSource.class), "Text");

    SQLCompletionRequest completionRequest =
        new SQLCompletionRequest(context, document, 1, activeQuery, true);

    // Act
    new SQLQueryCompletionProposalContext(completionRequest, 1).getCompletionContext();

    // Assert
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlSyntaxManager).getDialect();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(context).getSyntaxManager();
  }

  /**
   * Test {@link SQLQueryCompletionProposalContext#getActivityTracker()}.
   *
   * <ul>
   *   <li>Then return AdditionalInfoExpected.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryCompletionProposalContext#getActivityTracker()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLCompletionActivityTracker SQLQueryCompletionProposalContext.getActivityTracker()"
  })
  public void testGetActivityTracker_thenReturnAdditionalInfoExpected() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStructSeparator()).thenReturn('A');
    when(sqlSyntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLCompletionContext context = mock(SQLCompletionContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    Document document = new Document();
    SQLQuery activeQuery = new SQLQuery(mock(DBPDataSource.class), "Text");

    SQLCompletionRequest completionRequest =
        new SQLCompletionRequest(context, document, 1, activeQuery, true);

    // Act
    SQLCompletionActivityTracker actualActivityTracker =
        new SQLQueryCompletionProposalContext(completionRequest, 1).getActivityTracker();

    // Assert
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlSyntaxManager).getDialect();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(context).getSyntaxManager();
    assertTrue(actualActivityTracker.isAdditionalInfoExpected());
  }
}
