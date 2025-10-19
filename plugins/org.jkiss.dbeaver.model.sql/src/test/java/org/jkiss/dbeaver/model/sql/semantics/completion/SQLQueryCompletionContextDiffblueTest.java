package org.jkiss.dbeaver.model.sql.semantics.completion;

import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashSet;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.semantics.completion.SQLQueryCompletionContext.SQLQueryDataContextInfo;
import org.jkiss.dbeaver.model.sql.semantics.context.SQLQueryConnectionDummyContext;
import org.jkiss.dbeaver.model.sql.semantics.context.SQLQueryResultColumn;
import org.jkiss.dbeaver.model.sql.semantics.context.SQLQueryRowsDataContext;
import org.jkiss.dbeaver.model.sql.semantics.context.SQLQueryRowsSourceContext;
import org.jkiss.dbeaver.model.sql.semantics.context.SQLQuerySourcesInfoCollection;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLQueryCompletionContextDiffblueTest {
  /**
   * Test SQLQueryDataContextInfo {@link SQLQueryDataContextInfo#makeFor(SQLQueryRowsDataContext)}
   * with {@code rowsDataContext}.
   *
   * <ul>
   *   <li>Then calls {@link SQLQueryRowsSourceContext#getKnownSources(boolean)}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryDataContextInfo#makeFor(SQLQueryRowsDataContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryDataContextInfo SQLQueryDataContextInfo.makeFor(SQLQueryRowsDataContext)"
  })
  public void testSQLQueryDataContextInfoMakeForWithRowsDataContext_thenCallsGetKnownSources() {
    // Arrange
    SQLQueryRowsSourceContext rowsSources = mock(SQLQueryRowsSourceContext.class);
    when(rowsSources.getKnownSources(anyBoolean()))
        .thenReturn(mock(SQLQuerySourcesInfoCollection.class));
    ArrayList<SQLQueryResultColumn> columns = new ArrayList<>();

    SQLQueryRowsDataContext rowsDataContext =
        new SQLQueryRowsDataContext(rowsSources, columns, new ArrayList<>());

    // Act
    SQLQueryDataContextInfo.makeFor(rowsDataContext);

    // Assert
    verify(rowsSources).getKnownSources(true);
  }

  /**
   * Test SQLQueryDataContextInfo {@link SQLQueryDataContextInfo#makeFor(SQLQueryRowsDataContext)}
   * with {@code rowsDataContext}.
   *
   * <ul>
   *   <li>Then calls {@link SQLQueryRowsDataContext#getRowsSources()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryDataContextInfo#makeFor(SQLQueryRowsDataContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryDataContextInfo SQLQueryDataContextInfo.makeFor(SQLQueryRowsDataContext)"
  })
  public void testSQLQueryDataContextInfoMakeForWithRowsDataContext_thenCallsGetRowsSources() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext connectionInfo =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());

    SQLQueryRowsSourceContext sqlQueryRowsSourceContext =
        new SQLQueryRowsSourceContext(connectionInfo);
    sqlQueryRowsSourceContext.appendCteSources(new ArrayList<>());

    SQLQueryRowsDataContext rowsDataContext = mock(SQLQueryRowsDataContext.class);
    when(rowsDataContext.getRowsSources()).thenReturn(sqlQueryRowsSourceContext);

    // Act
    SQLQueryDataContextInfo.makeFor(rowsDataContext);

    // Assert
    verify(rowsDataContext).getRowsSources();
  }

  /**
   * Test SQLQueryDataContextInfo {@link SQLQueryDataContextInfo#makeFor(SQLQueryRowsSourceContext)}
   * with {@code rowsSourceContext}.
   *
   * <ul>
   *   <li>Then calls {@link SQLQueryRowsSourceContext#getKnownSources(boolean)}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryDataContextInfo#makeFor(SQLQueryRowsSourceContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryDataContextInfo SQLQueryDataContextInfo.makeFor(SQLQueryRowsSourceContext)"
  })
  public void testSQLQueryDataContextInfoMakeForWithRowsSourceContext_thenCallsGetKnownSources() {
    // Arrange
    SQLQueryRowsSourceContext rowsSourceContext = mock(SQLQueryRowsSourceContext.class);
    when(rowsSourceContext.getKnownSources(anyBoolean()))
        .thenReturn(mock(SQLQuerySourcesInfoCollection.class));

    // Act
    SQLQueryDataContextInfo.makeFor(rowsSourceContext);

    // Assert
    verify(rowsSourceContext).getKnownSources(true);
  }
}
