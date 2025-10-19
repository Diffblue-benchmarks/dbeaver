package org.jkiss.dbeaver.model.sql.parser;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.impl.sql.BasicSQLDialect;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.SQLSyntaxManager;
import org.jkiss.dbeaver.model.text.parser.TPRuleBasedScanner;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SQLParserContextDiffblueTest {
  @Mock private SQLDialect sQLDialect;

  @InjectMocks private SQLParserContext sQLParserContext;

  @Mock private TPRuleBasedScanner tPRuleBasedScanner;

  /**
   * Test {@link SQLParserContext#SQLParserContext(DBPDataSource, SQLSyntaxManager, SQLRuleManager,
   * IDocument)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceContainer}.
   *   <li>Then Document return {@link Document}.
   * </ul>
   *
   * <p>Method under test: {@link SQLParserContext#SQLParserContext(DBPDataSource, SQLSyntaxManager,
   * SQLRuleManager, IDocument)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLParserContext.<init>(DBPDataSource, SQLSyntaxManager, SQLRuleManager, IDocument)"
  })
  public void testNewSQLParserContext_givenDBPDataSourceContainer_thenDocumentReturnDocument() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(mock(DBPDataSourceContainer.class));
    Document document = new Document();

    // Act
    SQLParserContext actualSqlParserContext =
        new SQLParserContext(dataSource, null, null, document);

    // Assert
    verify(dataSource).getContainer();
    IDocument document2 = actualSqlParserContext.getDocument();
    assertTrue(document2 instanceof Document);
    assertNull(actualSqlParserContext.getPreferenceStore());
    assertNull(actualSqlParserContext.getDialect());
    assertNull(actualSqlParserContext.getSyntaxManager());
    assertNull(actualSqlParserContext.getRuleManager());
    assertSame(document, document2);
    assertSame(dataSource, actualSqlParserContext.getDataSource());
  }

  /**
   * Test {@link SQLParserContext#SQLParserContext(DBPDataSourceContainer, SQLSyntaxManager,
   * SQLRuleManager, IDocument)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource}.
   *   <li>Then Document return {@link Document}.
   * </ul>
   *
   * <p>Method under test: {@link SQLParserContext#SQLParserContext(DBPDataSourceContainer,
   * SQLSyntaxManager, SQLRuleManager, IDocument)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLParserContext.<init>(DBPDataSourceContainer, SQLSyntaxManager, SQLRuleManager, IDocument)"
  })
  public void testNewSQLParserContext_givenDBPDataSource_thenDocumentReturnDocument() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    Document document = new Document();

    // Act
    SQLParserContext actualSqlParserContext =
        new SQLParserContext(dataSourceContainer, null, null, document);

    // Assert
    verify(dataSourceContainer).getDataSource();
    IDocument document2 = actualSqlParserContext.getDocument();
    assertTrue(document2 instanceof Document);
    assertNull(actualSqlParserContext.getDialect());
    assertNull(actualSqlParserContext.getSyntaxManager());
    assertNull(actualSqlParserContext.getRuleManager());
    assertSame(document, document2);
  }

  /**
   * Test {@link SQLParserContext#getDialect()}.
   *
   * <p>Method under test: {@link SQLParserContext#getDialect()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLDialect SQLParserContext.getDialect()"})
  public void testGetDialect() {
    // Arrange and Act
    SQLDialect actualDialect = sQLParserContext.getDialect();

    // Assert
    assertSame(((BasicSQLDialect) actualDialect).INSTANCE, actualDialect);
  }
}
