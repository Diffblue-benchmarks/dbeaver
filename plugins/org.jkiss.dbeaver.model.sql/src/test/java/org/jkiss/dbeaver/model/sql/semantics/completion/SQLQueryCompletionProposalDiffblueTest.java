package org.jkiss.dbeaver.model.sql.semantics.completion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.jkiss.dbeaver.Log;
import org.jkiss.dbeaver.model.DBPContextProvider;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.SQLParametersProvider;
import org.jkiss.dbeaver.model.sql.SQLQuery;
import org.jkiss.dbeaver.model.sql.SQLScriptContext;
import org.jkiss.dbeaver.model.sql.SQLSyntaxManager;
import org.jkiss.dbeaver.model.sql.completion.SQLCompletionActivityTracker;
import org.jkiss.dbeaver.model.sql.completion.SQLCompletionContext;
import org.jkiss.dbeaver.model.sql.completion.SQLCompletionRequest;
import org.jkiss.dbeaver.model.sql.data.SQLQueryDataContainer;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLQueryCompletionProposalDiffblueTest {
  /**
   * Test {@link SQLQueryCompletionProposal#getProposalTypeSorterScore()}.
   *
   * <ul>
   *   <li>Then return {@link Integer#MAX_VALUE}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryCompletionProposal#getProposalTypeSorterScore()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryCompletionProposal.getProposalTypeSorterScore()"})
  public void testGetProposalTypeSorterScore_thenReturnMax_value() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    SQLScriptContext parentContext = mock(SQLScriptContext.class);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    SQLQueryCompletionProposalContext proposalContext =
        new SQLQueryCompletionProposalContext(null, 1);
    DBPImage image = mock(DBPImage.class);

    SQLQueryCompletionProposal sqlQueryCompletionProposal =
        new SQLQueryCompletionProposal(
            proposalContext,
            SQLQueryCompletionItemKind.UNKNOWN,
            object,
            image,
            "Display String",
            "Decoration String",
            "The characteristics of someone or something",
            "Replacement String",
            1,
            3,
            new SQLQueryWordEntry(2, "String"),
            3);

    // Act
    int actualProposalTypeSorterScore = sqlQueryCompletionProposal.getProposalTypeSorterScore();

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    assertEquals(Integer.MAX_VALUE, actualProposalTypeSorterScore);
  }

  /**
   * Test {@link SQLQueryCompletionProposal#getDisplayString()}.
   *
   * <ul>
   *   <li>Then return {@code Display String}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryCompletionProposal#getDisplayString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLQueryCompletionProposal.getDisplayString()"})
  public void testGetDisplayString_thenReturnDisplayString() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    SQLScriptContext parentContext = mock(SQLScriptContext.class);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    SQLQueryCompletionProposalContext proposalContext =
        new SQLQueryCompletionProposalContext(null, 1);
    DBPImage image = mock(DBPImage.class);

    SQLQueryCompletionProposal sqlQueryCompletionProposal =
        new SQLQueryCompletionProposal(
            proposalContext,
            SQLQueryCompletionItemKind.UNKNOWN,
            object,
            image,
            "Display String",
            "Decoration String",
            "The characteristics of someone or something",
            "Replacement String",
            1,
            3,
            new SQLQueryWordEntry(2, "String"),
            3);

    // Act
    String actualDisplayString = sqlQueryCompletionProposal.getDisplayString();

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    assertEquals("Display String", actualDisplayString);
  }

  /**
   * Test {@link SQLQueryCompletionProposal#getDisplayString()}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryCompletionProposal#getDisplayString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLQueryCompletionProposal.getDisplayString()"})
  public void testGetDisplayString_thenReturnEmptyString() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    SQLScriptContext parentContext = mock(SQLScriptContext.class);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    SQLQueryCompletionProposalContext proposalContext =
        new SQLQueryCompletionProposalContext(null, 1);
    DBPImage image = mock(DBPImage.class);

    SQLQueryCompletionProposal sqlQueryCompletionProposal =
        new SQLQueryCompletionProposal(
            proposalContext,
            SQLQueryCompletionItemKind.UNKNOWN,
            object,
            image,
            "",
            "Decoration String",
            "The characteristics of someone or something",
            "\n",
            1,
            3,
            new SQLQueryWordEntry(2, "String"),
            3);

    // Act
    String actualDisplayString = sqlQueryCompletionProposal.getDisplayString();

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    assertEquals("", actualDisplayString);
  }

  /**
   * Test {@link SQLQueryCompletionProposal#getDisplayString()}.
   *
   * <ul>
   *   <li>Then return {@code []}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryCompletionProposal#getDisplayString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLQueryCompletionProposal.getDisplayString()"})
  public void testGetDisplayString_thenReturnLeftSquareBracketRightSquareBracket() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    SQLScriptContext parentContext = mock(SQLScriptContext.class);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    SQLQueryCompletionProposalContext proposalContext =
        new SQLQueryCompletionProposalContext(null, 1);
    DBPImage image = mock(DBPImage.class);

    SQLQueryCompletionProposal sqlQueryCompletionProposal =
        new SQLQueryCompletionProposal(
            proposalContext,
            SQLQueryCompletionItemKind.UNKNOWN,
            object,
            image,
            "",
            "Decoration String",
            "The characteristics of someone or something",
            "[\r\n]",
            1,
            3,
            new SQLQueryWordEntry(2, "String"),
            3);

    // Act
    String actualDisplayString = sqlQueryCompletionProposal.getDisplayString();

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    assertEquals("[]", actualDisplayString);
  }

  /**
   * Test {@link SQLQueryCompletionProposal#getDisplayString()}.
   *
   * <ul>
   *   <li>Then return {@code Replacement String}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryCompletionProposal#getDisplayString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLQueryCompletionProposal.getDisplayString()"})
  public void testGetDisplayString_thenReturnReplacementString() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    SQLScriptContext parentContext = mock(SQLScriptContext.class);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    SQLQueryCompletionProposalContext proposalContext =
        new SQLQueryCompletionProposalContext(null, 1);
    DBPImage image = mock(DBPImage.class);

    SQLQueryCompletionProposal sqlQueryCompletionProposal =
        new SQLQueryCompletionProposal(
            proposalContext,
            SQLQueryCompletionItemKind.UNKNOWN,
            object,
            image,
            "",
            "Decoration String",
            "The characteristics of someone or something",
            "Replacement String",
            1,
            3,
            new SQLQueryWordEntry(2, "String"),
            3);

    // Act
    String actualDisplayString = sqlQueryCompletionProposal.getDisplayString();

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    assertEquals("Replacement String", actualDisplayString);
  }

  /**
   * Test {@link SQLQueryCompletionProposal#getPrefixCompletionText(IDocument, int)}.
   *
   * <ul>
   *   <li>Then return {@code Replacement String}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryCompletionProposal#getPrefixCompletionText(IDocument,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CharSequence SQLQueryCompletionProposal.getPrefixCompletionText(IDocument, int)"
  })
  public void testGetPrefixCompletionText_thenReturnReplacementString() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    SQLScriptContext parentContext = mock(SQLScriptContext.class);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    SQLQueryCompletionProposalContext proposalContext =
        new SQLQueryCompletionProposalContext(null, 1);
    DBPImage image = mock(DBPImage.class);

    SQLQueryCompletionProposal sqlQueryCompletionProposal =
        new SQLQueryCompletionProposal(
            proposalContext,
            SQLQueryCompletionItemKind.UNKNOWN,
            object,
            image,
            "Display String",
            "Decoration String",
            "The characteristics of someone or something",
            "Replacement String",
            1,
            3,
            new SQLQueryWordEntry(2, "String"),
            3);

    // Act
    CharSequence actualPrefixCompletionText =
        sqlQueryCompletionProposal.getPrefixCompletionText(new Document(), 1);

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    assertEquals("Replacement String", actualPrefixCompletionText);
  }

  /**
   * Test {@link SQLQueryCompletionProposal#getPrefixCompletionStart(IDocument, int)}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryCompletionProposal#getPrefixCompletionStart(IDocument,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryCompletionProposal.getPrefixCompletionStart(IDocument, int)"})
  public void testGetPrefixCompletionStart_thenReturnOne() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    SQLScriptContext parentContext = mock(SQLScriptContext.class);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    SQLQueryCompletionProposalContext proposalContext =
        new SQLQueryCompletionProposalContext(null, 1);
    DBPImage image = mock(DBPImage.class);

    SQLQueryCompletionProposal sqlQueryCompletionProposal =
        new SQLQueryCompletionProposal(
            proposalContext,
            SQLQueryCompletionItemKind.UNKNOWN,
            object,
            image,
            "Display String",
            "Decoration String",
            "The characteristics of someone or something",
            "Replacement String",
            1,
            3,
            new SQLQueryWordEntry(2, "String"),
            3);

    // Act
    int actualPrefixCompletionStart =
        sqlQueryCompletionProposal.getPrefixCompletionStart(new Document(), 1);

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    assertEquals(1, actualPrefixCompletionStart);
  }

  /**
   * Test {@link SQLQueryCompletionProposal#apply(IDocument)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPContextProvider#getExecutionContext()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryCompletionProposal#apply(IDocument)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLQueryCompletionProposal.apply(IDocument)"})
  public void testApply_thenCallsGetExecutionContext() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    SQLScriptContext parentContext = mock(SQLScriptContext.class);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            parentContext,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    SQLQueryCompletionProposalContext proposalContext =
        new SQLQueryCompletionProposalContext(null, 1);
    DBPImage image = mock(DBPImage.class);

    SQLQueryCompletionProposal sqlQueryCompletionProposal =
        new SQLQueryCompletionProposal(
            proposalContext,
            SQLQueryCompletionItemKind.UNKNOWN,
            object,
            image,
            "Display String",
            "Decoration String",
            "The characteristics of someone or something",
            "Replacement String",
            1,
            3,
            new SQLQueryWordEntry(2, "String"),
            3);

    // Act
    sqlQueryCompletionProposal.apply(new Document());

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
  }

  /**
   * Test {@link SQLQueryCompletionProposal#validate(IDocument, int, DocumentEvent)}.
   *
   * <p>Method under test: {@link SQLQueryCompletionProposal#validate(IDocument, int,
   * DocumentEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLQueryCompletionProposal.validate(IDocument, int, DocumentEvent)"})
  public void testValidate() {
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
    SQLQueryCompletionProposalContext proposalContext =
        new SQLQueryCompletionProposalContext(completionRequest, 1);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            null,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    DBPImage image = mock(DBPImage.class);

    SQLQueryCompletionProposal sqlQueryCompletionProposal =
        new SQLQueryCompletionProposal(
            proposalContext,
            SQLQueryCompletionItemKind.UNKNOWN,
            object,
            image,
            "Display String",
            "Decoration String",
            "The characteristics of someone or something",
            "Replacement String",
            1,
            3,
            new SQLQueryWordEntry(2, "String"),
            3);
    Document document2 = new Document();

    // Act
    boolean actualValidateResult =
        sqlQueryCompletionProposal.validate(document2, 2, new DocumentEvent());

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlSyntaxManager).getDialect();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(context).getSyntaxManager();
    assertEquals(3, sqlQueryCompletionProposal.getProposalScore());
    assertFalse(actualValidateResult);
  }

  /**
   * Test {@link SQLQueryCompletionProposal#validate(IDocument, int, DocumentEvent)}.
   *
   * <p>Method under test: {@link SQLQueryCompletionProposal#validate(IDocument, int,
   * DocumentEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLQueryCompletionProposal.validate(IDocument, int, DocumentEvent)"})
  public void testValidate2() {
    // Arrange
    SQLQueryCompletionProposalContext proposalContext =
        mock(SQLQueryCompletionProposalContext.class);
    when(proposalContext.getRequestOffset()).thenReturn(1);
    when(proposalContext.getActivityTracker()).thenReturn(new SQLCompletionActivityTracker(true));

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            null,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    DBPImage image = mock(DBPImage.class);

    SQLQueryCompletionProposal sqlQueryCompletionProposal =
        new SQLQueryCompletionProposal(
            proposalContext,
            SQLQueryCompletionItemKind.UNKNOWN,
            object,
            image,
            "Display String",
            "Decoration String",
            "The characteristics of someone or something",
            "Replacement String",
            1,
            3,
            new SQLQueryWordEntry(-1, "String"),
            3);
    Document document = new Document();

    // Act
    boolean actualValidateResult =
        sqlQueryCompletionProposal.validate(document, 2, new DocumentEvent());

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(proposalContext).getActivityTracker();
    verify(proposalContext).getRequestOffset();
    assertEquals(3, sqlQueryCompletionProposal.getProposalScore());
    assertFalse(actualValidateResult);
  }

  /**
   * Test {@link SQLQueryCompletionProposal#validate(IDocument, int, DocumentEvent)}.
   *
   * <p>Method under test: {@link SQLQueryCompletionProposal#validate(IDocument, int,
   * DocumentEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLQueryCompletionProposal.validate(IDocument, int, DocumentEvent)"})
  public void testValidate3() {
    // Arrange
    SQLQueryCompletionProposalContext proposalContext =
        mock(SQLQueryCompletionProposalContext.class);
    when(proposalContext.getActivityTracker()).thenReturn(new SQLCompletionActivityTracker(true));

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            null,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));

    SQLQueryCompletionProposal sqlQueryCompletionProposal =
        new SQLQueryCompletionProposal(
            proposalContext,
            SQLQueryCompletionItemKind.UNKNOWN,
            object,
            mock(DBPImage.class),
            "Display String",
            "Decoration String",
            "The characteristics of someone or something",
            "Replacement String",
            1,
            3,
            null,
            3);
    Document document = new Document();

    // Act
    boolean actualValidateResult =
        sqlQueryCompletionProposal.validate(document, 2, new DocumentEvent());

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(proposalContext).getActivityTracker();
    assertEquals(3, sqlQueryCompletionProposal.getProposalScore());
    assertTrue(actualValidateResult);
  }

  /**
   * Test {@link SQLQueryCompletionProposal#validate(IDocument, int, DocumentEvent)}.
   *
   * <p>Method under test: {@link SQLQueryCompletionProposal#validate(IDocument, int,
   * DocumentEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLQueryCompletionProposal.validate(IDocument, int, DocumentEvent)"})
  public void testValidate4() {
    // Arrange
    SQLQueryCompletionProposalContext proposalContext =
        mock(SQLQueryCompletionProposalContext.class);
    when(proposalContext.getActivityTracker()).thenReturn(new SQLCompletionActivityTracker(true));

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            null,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));

    SQLQueryCompletionProposal sqlQueryCompletionProposal =
        new SQLQueryCompletionProposal(
            proposalContext,
            SQLQueryCompletionItemKind.UNKNOWN,
            object,
            mock(DBPImage.class),
            "Display String",
            "Decoration String",
            "The characteristics of someone or something",
            "Replacement String",
            1,
            3,
            mock(SQLQueryWordEntry.class),
            3);
    Document document = new Document();

    // Act
    boolean actualValidateResult =
        sqlQueryCompletionProposal.validate(document, 2, new DocumentEvent());

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(proposalContext).getActivityTracker();
    assertEquals(3, sqlQueryCompletionProposal.getProposalScore());
    assertTrue(actualValidateResult);
  }

  /**
   * Test {@link SQLQueryCompletionProposal#validate(IDocument, int, DocumentEvent)}.
   *
   * <p>Method under test: {@link SQLQueryCompletionProposal#validate(IDocument, int,
   * DocumentEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLQueryCompletionProposal.validate(IDocument, int, DocumentEvent)"})
  public void testValidate5() {
    // Arrange
    SQLQueryCompletionProposalContext proposalContext =
        mock(SQLQueryCompletionProposalContext.class);
    when(proposalContext.getActivityTracker()).thenReturn(new SQLCompletionActivityTracker(true));

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            null,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    DBPImage image = mock(DBPImage.class);

    SQLQueryCompletionProposal sqlQueryCompletionProposal =
        new SQLQueryCompletionProposal(
            proposalContext,
            SQLQueryCompletionItemKind.UNKNOWN,
            object,
            image,
            "Display String",
            "Decoration String",
            "The characteristics of someone or something",
            "Replacement String",
            1,
            3,
            new SQLQueryWordEntry(2, "String"),
            3);
    Document document = new Document("Not all who wander are lost");

    // Act
    boolean actualValidateResult =
        sqlQueryCompletionProposal.validate(document, 2, new DocumentEvent());

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(proposalContext).getActivityTracker();
    assertTrue(actualValidateResult);
    assertEquals(Integer.MAX_VALUE, sqlQueryCompletionProposal.getProposalScore());
  }

  /**
   * Test {@link SQLQueryCompletionProposal#validate(IDocument, int, DocumentEvent)}.
   *
   * <p>Method under test: {@link SQLQueryCompletionProposal#validate(IDocument, int,
   * DocumentEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLQueryCompletionProposal.validate(IDocument, int, DocumentEvent)"})
  public void testValidate6() throws BadLocationException {
    // Arrange
    SQLCompletionContext sqlCompletionContext = mock(SQLCompletionContext.class);
    when(sqlCompletionContext.isSearchInsideNames()).thenReturn(true);

    SQLQueryCompletionProposalContext proposalContext =
        mock(SQLQueryCompletionProposalContext.class);
    when(proposalContext.getCompletionContext()).thenReturn(sqlCompletionContext);
    when(proposalContext.getRequestOffset()).thenReturn(1);
    when(proposalContext.getActivityTracker()).thenReturn(new SQLCompletionActivityTracker(true));

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            null,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    DBPImage image = mock(DBPImage.class);

    SQLQueryCompletionProposal sqlQueryCompletionProposal =
        new SQLQueryCompletionProposal(
            proposalContext,
            SQLQueryCompletionItemKind.UNKNOWN,
            object,
            image,
            "Display String",
            "Decoration String",
            "The characteristics of someone or something",
            "Replacement String",
            1,
            3,
            new SQLQueryWordEntry(-1, "String"),
            3);

    IDocument document = mock(IDocument.class);
    when(document.get(anyInt(), anyInt())).thenReturn(null);
    when(document.getLength()).thenReturn(3);

    // Act
    boolean actualValidateResult =
        sqlQueryCompletionProposal.validate(document, 2, new DocumentEvent());

    // Assert
    verify(document).get(1, 1);
    verify(document).getLength();
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(sqlCompletionContext).isSearchInsideNames();
    verify(proposalContext).getActivityTracker();
    verify(proposalContext).getCompletionContext();
    verify(proposalContext).getRequestOffset();
    assertTrue(actualValidateResult);
    assertEquals(Integer.MAX_VALUE, sqlQueryCompletionProposal.getProposalScore());
  }

  /**
   * Test {@link SQLQueryCompletionProposal#validate(IDocument, int, DocumentEvent)}.
   *
   * <ul>
   *   <li>Given {@link BadLocationException#BadLocationException(String)} with message is {@code An
   *       error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryCompletionProposal#validate(IDocument, int,
   * DocumentEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLQueryCompletionProposal.validate(IDocument, int, DocumentEvent)"})
  public void testValidate_givenBadLocationExceptionWithMessageIsAnErrorOccurred()
      throws BadLocationException {
    // Arrange
    SQLQueryCompletionProposalContext proposalContext =
        mock(SQLQueryCompletionProposalContext.class);
    when(proposalContext.getRequestOffset()).thenReturn(1);
    when(proposalContext.getActivityTracker()).thenReturn(new SQLCompletionActivityTracker(true));

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            null,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    DBPImage image = mock(DBPImage.class);

    SQLQueryCompletionProposal sqlQueryCompletionProposal =
        new SQLQueryCompletionProposal(
            proposalContext,
            SQLQueryCompletionItemKind.UNKNOWN,
            object,
            image,
            "Display String",
            "Decoration String",
            "The characteristics of someone or something",
            "Replacement String",
            1,
            3,
            new SQLQueryWordEntry(-1, "String"),
            3);

    IDocument document = mock(IDocument.class);
    when(document.get(anyInt(), anyInt())).thenThrow(new BadLocationException("An error occurred"));
    when(document.getLength()).thenReturn(3);

    // Act
    boolean actualValidateResult =
        sqlQueryCompletionProposal.validate(document, 2, new DocumentEvent());

    // Assert
    verify(document).get(1, 1);
    verify(document).getLength();
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(proposalContext).getActivityTracker();
    verify(proposalContext).getRequestOffset();
    assertEquals(3, sqlQueryCompletionProposal.getProposalScore());
    assertTrue(actualValidateResult);
  }

  /**
   * Test {@link SQLQueryCompletionProposal#validate(IDocument, int, DocumentEvent)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>When {@link IDocument} {@link IDocument#get(int, int)} return empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryCompletionProposal#validate(IDocument, int,
   * DocumentEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLQueryCompletionProposal.validate(IDocument, int, DocumentEvent)"})
  public void testValidate_givenEmptyString_whenIDocumentGetReturnEmptyString()
      throws BadLocationException {
    // Arrange
    SQLCompletionContext sqlCompletionContext = mock(SQLCompletionContext.class);
    when(sqlCompletionContext.isSearchInsideNames()).thenReturn(true);

    SQLQueryCompletionProposalContext proposalContext =
        mock(SQLQueryCompletionProposalContext.class);
    when(proposalContext.getCompletionContext()).thenReturn(sqlCompletionContext);
    when(proposalContext.getRequestOffset()).thenReturn(1);
    when(proposalContext.getActivityTracker()).thenReturn(new SQLCompletionActivityTracker(true));

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            null,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    DBPImage image = mock(DBPImage.class);

    SQLQueryCompletionProposal sqlQueryCompletionProposal =
        new SQLQueryCompletionProposal(
            proposalContext,
            SQLQueryCompletionItemKind.UNKNOWN,
            object,
            image,
            "Display String",
            "Decoration String",
            "The characteristics of someone or something",
            "Replacement String",
            1,
            3,
            new SQLQueryWordEntry(-1, "String"),
            3);

    IDocument document = mock(IDocument.class);
    when(document.get(anyInt(), anyInt())).thenReturn("");
    when(document.getLength()).thenReturn(3);

    // Act
    boolean actualValidateResult =
        sqlQueryCompletionProposal.validate(document, 2, new DocumentEvent());

    // Assert
    verify(document).get(1, 1);
    verify(document).getLength();
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(sqlCompletionContext).isSearchInsideNames();
    verify(proposalContext).getActivityTracker();
    verify(proposalContext).getCompletionContext();
    verify(proposalContext).getRequestOffset();
    assertEquals(0, sqlQueryCompletionProposal.getProposalScore());
    assertFalse(actualValidateResult);
  }

  /**
   * Test {@link SQLQueryCompletionProposal#validate(IDocument, int, DocumentEvent)}.
   *
   * <ul>
   *   <li>Given {@link SQLCompletionContext} {@link SQLCompletionContext#isSearchInsideNames()}
   *       return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryCompletionProposal#validate(IDocument, int,
   * DocumentEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLQueryCompletionProposal.validate(IDocument, int, DocumentEvent)"})
  public void testValidate_givenSQLCompletionContextIsSearchInsideNamesReturnFalse()
      throws BadLocationException {
    // Arrange
    SQLCompletionContext sqlCompletionContext = mock(SQLCompletionContext.class);
    when(sqlCompletionContext.isSearchInsideNames()).thenReturn(false);

    SQLQueryCompletionProposalContext proposalContext =
        mock(SQLQueryCompletionProposalContext.class);
    when(proposalContext.getCompletionContext()).thenReturn(sqlCompletionContext);
    when(proposalContext.getRequestOffset()).thenReturn(1);
    when(proposalContext.getActivityTracker()).thenReturn(new SQLCompletionActivityTracker(true));

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            null,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    DBPImage image = mock(DBPImage.class);

    SQLQueryCompletionProposal sqlQueryCompletionProposal =
        new SQLQueryCompletionProposal(
            proposalContext,
            SQLQueryCompletionItemKind.UNKNOWN,
            object,
            image,
            "Display String",
            "Decoration String",
            "The characteristics of someone or something",
            "Replacement String",
            1,
            3,
            new SQLQueryWordEntry(-1, "String"),
            3);

    IDocument document = mock(IDocument.class);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLength()).thenReturn(3);

    // Act
    boolean actualValidateResult =
        sqlQueryCompletionProposal.validate(document, 2, new DocumentEvent());

    // Assert
    verify(document).get(1, 1);
    verify(document).getLength();
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(sqlCompletionContext).isSearchInsideNames();
    verify(proposalContext).getActivityTracker();
    verify(proposalContext).getCompletionContext();
    verify(proposalContext).getRequestOffset();
    assertEquals(0, sqlQueryCompletionProposal.getProposalScore());
    assertFalse(actualValidateResult);
  }

  /**
   * Test {@link SQLQueryCompletionProposal#validate(IDocument, int, DocumentEvent)}.
   *
   * <ul>
   *   <li>Given {@link SQLQueryWordEntry#SQLQueryWordEntry(int, String)} with offset is minus one
   *       and string is empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryCompletionProposal#validate(IDocument, int,
   * DocumentEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLQueryCompletionProposal.validate(IDocument, int, DocumentEvent)"})
  public void testValidate_givenSQLQueryWordEntryWithOffsetIsMinusOneAndStringIsEmptyString() {
    // Arrange
    SQLQueryCompletionProposalContext proposalContext =
        mock(SQLQueryCompletionProposalContext.class);
    when(proposalContext.getActivityTracker()).thenReturn(new SQLCompletionActivityTracker(true));

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            null,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    DBPImage image = mock(DBPImage.class);

    SQLQueryCompletionProposal sqlQueryCompletionProposal =
        new SQLQueryCompletionProposal(
            proposalContext,
            SQLQueryCompletionItemKind.UNKNOWN,
            object,
            image,
            "Display String",
            "Decoration String",
            "The characteristics of someone or something",
            "Replacement String",
            1,
            3,
            new SQLQueryWordEntry(-1, ""),
            3);
    IDocument document = mock(IDocument.class);

    // Act
    boolean actualValidateResult =
        sqlQueryCompletionProposal.validate(document, 2, new DocumentEvent());

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(proposalContext).getActivityTracker();
    assertEquals(3, sqlQueryCompletionProposal.getProposalScore());
    assertTrue(actualValidateResult);
  }

  /**
   * Test {@link SQLQueryCompletionProposal#validate(IDocument, int, DocumentEvent)}.
   *
   * <ul>
   *   <li>Given {@link SQLQueryWordEntry#SQLQueryWordEntry(int, String)} with offset is zero and
   *       {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryCompletionProposal#validate(IDocument, int,
   * DocumentEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLQueryCompletionProposal.validate(IDocument, int, DocumentEvent)"})
  public void testValidate_givenSQLQueryWordEntryWithOffsetIsZeroAndString() {
    // Arrange
    SQLCompletionContext sqlCompletionContext = mock(SQLCompletionContext.class);
    when(sqlCompletionContext.isSearchInsideNames()).thenReturn(true);

    SQLQueryCompletionProposalContext proposalContext =
        mock(SQLQueryCompletionProposalContext.class);
    when(proposalContext.getCompletionContext()).thenReturn(sqlCompletionContext);
    when(proposalContext.getActivityTracker()).thenReturn(new SQLCompletionActivityTracker(true));

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            null,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    DBPImage image = mock(DBPImage.class);

    SQLQueryCompletionProposal sqlQueryCompletionProposal =
        new SQLQueryCompletionProposal(
            proposalContext,
            SQLQueryCompletionItemKind.UNKNOWN,
            object,
            image,
            "Display String",
            "Decoration String",
            "The characteristics of someone or something",
            "Replacement String",
            1,
            3,
            new SQLQueryWordEntry(0, "String"),
            3);
    Document document = new Document("Not all who wander are lost");

    // Act
    boolean actualValidateResult =
        sqlQueryCompletionProposal.validate(document, 2, new DocumentEvent());

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(sqlCompletionContext).isSearchInsideNames();
    verify(proposalContext).getActivityTracker();
    verify(proposalContext).getCompletionContext();
    assertEquals(0, sqlQueryCompletionProposal.getProposalScore());
    assertFalse(actualValidateResult);
  }

  /**
   * Test {@link SQLQueryCompletionProposal#validate(IDocument, int, DocumentEvent)}.
   *
   * <ul>
   *   <li>Then calls {@link SQLCompletionActivityTracker#implicitlyTriggered()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryCompletionProposal#validate(IDocument, int,
   * DocumentEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLQueryCompletionProposal.validate(IDocument, int, DocumentEvent)"})
  public void testValidate_thenCallsImplicitlyTriggered() throws BadLocationException {
    // Arrange
    SQLCompletionActivityTracker sqlCompletionActivityTracker =
        mock(SQLCompletionActivityTracker.class);
    doNothing().when(sqlCompletionActivityTracker).implicitlyTriggered();

    SQLCompletionContext sqlCompletionContext = mock(SQLCompletionContext.class);
    when(sqlCompletionContext.isSearchInsideNames()).thenReturn(true);

    SQLQueryCompletionProposalContext proposalContext =
        mock(SQLQueryCompletionProposalContext.class);
    when(proposalContext.getCompletionContext()).thenReturn(sqlCompletionContext);
    when(proposalContext.getRequestOffset()).thenReturn(1);
    when(proposalContext.getActivityTracker()).thenReturn(sqlCompletionActivityTracker);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            null,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    DBPImage image = mock(DBPImage.class);

    SQLQueryCompletionProposal sqlQueryCompletionProposal =
        new SQLQueryCompletionProposal(
            proposalContext,
            SQLQueryCompletionItemKind.UNKNOWN,
            object,
            image,
            "Display String",
            "Decoration String",
            "The characteristics of someone or something",
            "Replacement String",
            1,
            3,
            new SQLQueryWordEntry(-1, "String"),
            3);

    IDocument document = mock(IDocument.class);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLength()).thenReturn(3);

    // Act
    boolean actualValidateResult =
        sqlQueryCompletionProposal.validate(document, 2, new DocumentEvent());

    // Assert
    verify(document).get(1, 1);
    verify(document).getLength();
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(sqlCompletionActivityTracker).implicitlyTriggered();
    verify(sqlCompletionContext).isSearchInsideNames();
    verify(proposalContext).getActivityTracker();
    verify(proposalContext).getCompletionContext();
    verify(proposalContext).getRequestOffset();
    assertEquals(0, sqlQueryCompletionProposal.getProposalScore());
    assertFalse(actualValidateResult);
  }

  /**
   * Test {@link SQLQueryCompletionProposal#validate(IDocument, int, DocumentEvent)}.
   *
   * <ul>
   *   <li>When {@link Document#Document(String)} with initialContent is {@code Not all who wander
   *       are lost}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryCompletionProposal#validate(IDocument, int,
   * DocumentEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLQueryCompletionProposal.validate(IDocument, int, DocumentEvent)"})
  public void testValidate_whenDocumentWithInitialContentIsNotAllWhoWanderAreLost() {
    // Arrange
    SQLCompletionContext sqlCompletionContext = mock(SQLCompletionContext.class);
    when(sqlCompletionContext.isSearchInsideNames()).thenReturn(true);

    SQLQueryCompletionProposalContext proposalContext =
        mock(SQLQueryCompletionProposalContext.class);
    when(proposalContext.getCompletionContext()).thenReturn(sqlCompletionContext);
    when(proposalContext.getRequestOffset()).thenReturn(1);
    when(proposalContext.getActivityTracker()).thenReturn(new SQLCompletionActivityTracker(true));

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getDataSource()).thenReturn(dbpDataSource);

    DBPContextProvider contextProvider = mock(DBPContextProvider.class);
    when(contextProvider.getExecutionContext()).thenReturn(dbcExecutionContext);
    Path sourceFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    SQLScriptContext scriptContext =
        new SQLScriptContext(
            null,
            contextProvider,
            sourceFile,
            new StringWriter(),
            mock(SQLParametersProvider.class));
    DBPContextProvider contextProvider2 = mock(DBPContextProvider.class);
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    Class<Object> forClass = Object.class;

    SQLQueryDataContainer object =
        new SQLQueryDataContainer(contextProvider2, query, scriptContext, Log.getLog(forClass));
    DBPImage image = mock(DBPImage.class);

    SQLQueryCompletionProposal sqlQueryCompletionProposal =
        new SQLQueryCompletionProposal(
            proposalContext,
            SQLQueryCompletionItemKind.UNKNOWN,
            object,
            image,
            "Display String",
            "Decoration String",
            "The characteristics of someone or something",
            "Replacement String",
            1,
            3,
            new SQLQueryWordEntry(-1, "String"),
            3);
    Document document = new Document("Not all who wander are lost");

    // Act
    boolean actualValidateResult =
        sqlQueryCompletionProposal.validate(document, 2, new DocumentEvent());

    // Assert
    verify(contextProvider).getExecutionContext();
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbcExecutionContext).getDataSource();
    verify(sqlCompletionContext).isSearchInsideNames();
    verify(proposalContext).getActivityTracker();
    verify(proposalContext).getCompletionContext();
    verify(proposalContext).getRequestOffset();
    assertEquals(0, sqlQueryCompletionProposal.getProposalScore());
    assertFalse(actualValidateResult);
  }
}
