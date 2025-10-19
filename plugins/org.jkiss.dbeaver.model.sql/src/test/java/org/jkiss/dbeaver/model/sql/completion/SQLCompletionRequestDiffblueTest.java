package org.jkiss.dbeaver.model.sql.completion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyChar;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.SQLQuery;
import org.jkiss.dbeaver.model.sql.SQLScriptElement;
import org.jkiss.dbeaver.model.sql.SQLSyntaxManager;
import org.jkiss.dbeaver.model.sql.parser.SQLWordPartDetector;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLCompletionRequestDiffblueTest {
  /**
   * Test {@link SQLCompletionRequest#SQLCompletionRequest(SQLCompletionContext, IDocument, int,
   * SQLScriptElement, boolean)}.
   *
   * <ul>
   *   <li>Given {@code a}.
   *   <li>When {@link IDocument} {@link IDocument#getChar(int)} return {@code a}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCompletionRequest#SQLCompletionRequest(SQLCompletionContext,
   * IDocument, int, SQLScriptElement, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLCompletionRequest.<init>(SQLCompletionContext, IDocument, int, SQLScriptElement, boolean)"
  })
  public void testNewSQLCompletionRequest_givenA_whenIDocumentGetCharReturnA()
      throws BadLocationException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isEntityQueryWord(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStructSeparator()).thenReturn('A');
    when(sqlSyntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"___sql_partitioning", "sql_quoted"}});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLCompletionContext context = mock(SQLCompletionContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);

    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('a');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");
    SQLQuery activeQuery = new SQLQuery(mock(DBPDataSource.class), "Text");

    // Act
    SQLCompletionRequest actualSqlCompletionRequest =
        new SQLCompletionRequest(context, document, 1, activeQuery, true);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), anyInt());
    verify(document, atLeast(1)).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).isEntityQueryWord("Get");
    verify(sqlSyntaxManager).getDialect();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(context).getSyntaxManager();
    SQLScriptElement activeQuery2 = actualSqlCompletionRequest.getActiveQuery();
    assertTrue(activeQuery2 instanceof SQLQuery);
    SQLWordPartDetector wordDetector = actualSqlCompletionRequest.getWordDetector();
    assertEquals("", wordDetector.getPrevDelimiter());
    assertEquals("GET", wordDetector.getPrevKeyWord());
    assertEquals(0, wordDetector.getPrevKeyWordOffset());
    List<SQLScriptElement> scriptElements = ((SQLQuery) activeQuery2).getScriptElements();
    assertEquals(1, scriptElements.size());
    assertEquals(1, wordDetector.getStartOffset());
    assertSame(activeQuery2, scriptElements.get(0));
  }

  /**
   * Test {@link SQLCompletionRequest#SQLCompletionRequest(SQLCompletionContext, IDocument, int,
   * SQLScriptElement, boolean)}.
   *
   * <ul>
   *   <li>Then calls {@link SQLDialect#isAttributeQueryWord(String)}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCompletionRequest#SQLCompletionRequest(SQLCompletionContext,
   * IDocument, int, SQLScriptElement, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLCompletionRequest.<init>(SQLCompletionContext, IDocument, int, SQLScriptElement, boolean)"
  })
  public void testNewSQLCompletionRequest_thenCallsIsAttributeQueryWord()
      throws BadLocationException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isAttributeQueryWord(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.isEntityQueryWord(Mockito.<String>any())).thenReturn(false);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStructSeparator()).thenReturn('A');
    when(sqlSyntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"___sql_partitioning", "sql_quoted"}});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLCompletionContext context = mock(SQLCompletionContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);

    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('A');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");
    SQLQuery activeQuery = new SQLQuery(mock(DBPDataSource.class), "Text");

    // Act
    SQLCompletionRequest actualSqlCompletionRequest =
        new SQLCompletionRequest(context, document, 1, activeQuery, true);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), anyInt());
    verify(document, atLeast(1)).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).isAttributeQueryWord("Get");
    verify(sqlDialect).isEntityQueryWord("Get");
    verify(sqlSyntaxManager).getDialect();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(context).getSyntaxManager();
    SQLScriptElement activeQuery2 = actualSqlCompletionRequest.getActiveQuery();
    assertTrue(activeQuery2 instanceof SQLQuery);
    SQLWordPartDetector wordDetector = actualSqlCompletionRequest.getWordDetector();
    assertEquals("", wordDetector.getPrevDelimiter());
    assertEquals("GET", wordDetector.getPrevKeyWord());
    assertEquals(0, wordDetector.getPrevKeyWordOffset());
    List<SQLScriptElement> scriptElements = ((SQLQuery) activeQuery2).getScriptElements();
    assertEquals(1, scriptElements.size());
    assertEquals(1, wordDetector.getStartOffset());
    assertSame(activeQuery2, scriptElements.get(0));
  }

  /**
   * Test {@link SQLCompletionRequest#SQLCompletionRequest(SQLCompletionContext, IDocument, int,
   * SQLScriptElement, boolean)}.
   *
   * <ul>
   *   <li>Then calls {@link SQLDialect#validIdentifierPart(char, boolean)}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCompletionRequest#SQLCompletionRequest(SQLCompletionContext,
   * IDocument, int, SQLScriptElement, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLCompletionRequest.<init>(SQLCompletionContext, IDocument, int, SQLScriptElement, boolean)"
  })
  public void testNewSQLCompletionRequest_thenCallsValidIdentifierPart()
      throws BadLocationException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(true);
    when(sqlDialect.isEntityQueryWord(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStructSeparator()).thenReturn('A');
    when(sqlSyntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"___sql_partitioning", "sql_quoted"}});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLCompletionContext context = mock(SQLCompletionContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);

    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('￿');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");
    SQLQuery activeQuery = new SQLQuery(mock(DBPDataSource.class), "Text");

    // Act
    SQLCompletionRequest actualSqlCompletionRequest =
        new SQLCompletionRequest(context, document, 1, activeQuery, true);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), anyInt());
    verify(document, atLeast(1)).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).isEntityQueryWord("Get");
    verify(sqlDialect, atLeast(1)).validIdentifierPart('￿', true);
    verify(sqlSyntaxManager).getDialect();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(context).getSyntaxManager();
    SQLScriptElement activeQuery2 = actualSqlCompletionRequest.getActiveQuery();
    assertTrue(activeQuery2 instanceof SQLQuery);
    SQLWordPartDetector wordDetector = actualSqlCompletionRequest.getWordDetector();
    assertEquals("", wordDetector.getPrevDelimiter());
    assertEquals("GET", wordDetector.getPrevKeyWord());
    assertEquals(0, wordDetector.getPrevKeyWordOffset());
    List<SQLScriptElement> scriptElements = ((SQLQuery) activeQuery2).getScriptElements();
    assertEquals(1, scriptElements.size());
    assertEquals(1, wordDetector.getStartOffset());
    assertSame(activeQuery2, scriptElements.get(0));
  }

  /**
   * Test {@link SQLCompletionRequest#SQLCompletionRequest(SQLCompletionContext, IDocument, int,
   * SQLScriptElement, boolean)}.
   *
   * <ul>
   *   <li>Then return WordDetector PrevDelimiter is empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLCompletionRequest#SQLCompletionRequest(SQLCompletionContext,
   * IDocument, int, SQLScriptElement, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLCompletionRequest.<init>(SQLCompletionContext, IDocument, int, SQLScriptElement, boolean)"
  })
  public void testNewSQLCompletionRequest_thenReturnWordDetectorPrevDelimiterIsEmptyString()
      throws BadLocationException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isEntityQueryWord(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStructSeparator()).thenReturn('A');
    when(sqlSyntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"___sql_partitioning", "sql_quoted"}});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLCompletionContext context = mock(SQLCompletionContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);

    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('A');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");
    SQLQuery activeQuery = new SQLQuery(mock(DBPDataSource.class), "Text");

    // Act
    SQLCompletionRequest actualSqlCompletionRequest =
        new SQLCompletionRequest(context, document, 1, activeQuery, true);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), anyInt());
    verify(document, atLeast(1)).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).isEntityQueryWord("Get");
    verify(sqlSyntaxManager).getDialect();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(context).getSyntaxManager();
    SQLScriptElement activeQuery2 = actualSqlCompletionRequest.getActiveQuery();
    assertTrue(activeQuery2 instanceof SQLQuery);
    SQLWordPartDetector wordDetector = actualSqlCompletionRequest.getWordDetector();
    assertEquals("", wordDetector.getPrevDelimiter());
    assertEquals("GET", wordDetector.getPrevKeyWord());
    assertEquals(0, wordDetector.getPrevKeyWordOffset());
    List<SQLScriptElement> scriptElements = ((SQLQuery) activeQuery2).getScriptElements();
    assertEquals(1, scriptElements.size());
    assertEquals(1, wordDetector.getStartOffset());
    assertSame(activeQuery2, scriptElements.get(0));
  }

  /**
   * Test {@link SQLCompletionRequest#SQLCompletionRequest(SQLCompletionContext, IDocument, int,
   * SQLScriptElement, boolean)}.
   *
   * <ul>
   *   <li>Then return WordDetector PrevDelimiter is {@code ￿}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCompletionRequest#SQLCompletionRequest(SQLCompletionContext,
   * IDocument, int, SQLScriptElement, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLCompletionRequest.<init>(SQLCompletionContext, IDocument, int, SQLScriptElement, boolean)"
  })
  public void testNewSQLCompletionRequest_thenReturnWordDetectorPrevDelimiterIsUffff()
      throws BadLocationException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(false);
    when(sqlDialect.isEntityQueryWord(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStructSeparator()).thenReturn('A');
    when(sqlSyntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"___sql_partitioning", "sql_quoted"}});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLCompletionContext context = mock(SQLCompletionContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);

    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('￿');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");
    SQLQuery activeQuery = new SQLQuery(mock(DBPDataSource.class), "Text");

    // Act
    SQLCompletionRequest actualSqlCompletionRequest =
        new SQLCompletionRequest(context, document, 1, activeQuery, true);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), eq(0));
    verify(document).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).isEntityQueryWord("Get");
    verify(sqlDialect).validIdentifierPart('￿', true);
    verify(sqlSyntaxManager).getDialect();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(context).getSyntaxManager();
    SQLScriptElement activeQuery2 = actualSqlCompletionRequest.getActiveQuery();
    assertTrue(activeQuery2 instanceof SQLQuery);
    SQLWordPartDetector wordDetector = actualSqlCompletionRequest.getWordDetector();
    assertEquals("GET", wordDetector.getPrevKeyWord());
    assertEquals("￿", wordDetector.getPrevDelimiter());
    assertEquals(0, wordDetector.getPrevKeyWordOffset());
    List<SQLScriptElement> scriptElements = ((SQLQuery) activeQuery2).getScriptElements();
    assertEquals(1, scriptElements.size());
    assertEquals(1, wordDetector.getStartOffset());
    assertSame(activeQuery2, scriptElements.get(0));
  }

  /**
   * Test {@link SQLCompletionRequest#SQLCompletionRequest(SQLCompletionContext, IDocument, int,
   * SQLScriptElement, boolean)}.
   *
   * <ul>
   *   <li>Then return WordDetector PrevKeyWord is empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLCompletionRequest#SQLCompletionRequest(SQLCompletionContext,
   * IDocument, int, SQLScriptElement, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLCompletionRequest.<init>(SQLCompletionContext, IDocument, int, SQLScriptElement, boolean)"
  })
  public void testNewSQLCompletionRequest_thenReturnWordDetectorPrevKeyWordIsEmptyString()
      throws BadLocationException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {""});
    when(sqlSyntaxManager.getStructSeparator()).thenReturn('A');
    when(sqlSyntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"___sql_partitioning", "sql_quoted"}});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLCompletionContext context = mock(SQLCompletionContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);

    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('A');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");
    SQLQuery activeQuery = new SQLQuery(mock(DBPDataSource.class), "Text");

    // Act
    SQLCompletionRequest actualSqlCompletionRequest =
        new SQLCompletionRequest(context, document, 1, activeQuery, true);

    // Assert
    verify(document, atLeast(1)).get(1, 0);
    verify(document).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlSyntaxManager).getDialect();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(context).getSyntaxManager();
    SQLScriptElement activeQuery2 = actualSqlCompletionRequest.getActiveQuery();
    assertTrue(activeQuery2 instanceof SQLQuery);
    SQLWordPartDetector wordDetector = actualSqlCompletionRequest.getWordDetector();
    assertEquals("", wordDetector.getPrevDelimiter());
    assertEquals("", wordDetector.getPrevKeyWord());
    assertEquals(-1, wordDetector.getPrevKeyWordOffset());
    List<SQLScriptElement> scriptElements = ((SQLQuery) activeQuery2).getScriptElements();
    assertEquals(1, scriptElements.size());
    assertEquals(1, wordDetector.getStartOffset());
    assertSame(activeQuery2, scriptElements.get(0));
  }

  /**
   * Test {@link SQLCompletionRequest#SQLCompletionRequest(SQLCompletionContext, IDocument, int,
   * SQLScriptElement, boolean)}.
   *
   * <ul>
   *   <li>Then return WordDetector StructSeparator is {@code ￿}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCompletionRequest#SQLCompletionRequest(SQLCompletionContext,
   * IDocument, int, SQLScriptElement, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLCompletionRequest.<init>(SQLCompletionContext, IDocument, int, SQLScriptElement, boolean)"
  })
  public void testNewSQLCompletionRequest_thenReturnWordDetectorStructSeparatorIsUffff()
      throws BadLocationException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isEntityQueryWord(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStructSeparator()).thenReturn('￿');
    when(sqlSyntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"___sql_partitioning", "sql_quoted"}});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLCompletionContext context = mock(SQLCompletionContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);

    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('￿');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");
    SQLQuery activeQuery = new SQLQuery(mock(DBPDataSource.class), "Text");

    // Act
    SQLCompletionRequest actualSqlCompletionRequest =
        new SQLCompletionRequest(context, document, 1, activeQuery, true);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), anyInt());
    verify(document, atLeast(1)).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).isEntityQueryWord("Get");
    verify(sqlSyntaxManager).getDialect();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(context).getSyntaxManager();
    SQLWordPartDetector wordDetector = actualSqlCompletionRequest.getWordDetector();
    assertEquals("", wordDetector.getPrevDelimiter());
    assertEquals("GET", wordDetector.getPrevKeyWord());
    assertEquals('￿', wordDetector.getStructSeparator());
    assertEquals(0, wordDetector.getPrevKeyWordOffset());
  }

  /**
   * Test {@link SQLCompletionRequest#SQLCompletionRequest(SQLCompletionContext, IDocument, int,
   * SQLScriptElement, boolean)}.
   *
   * <ul>
   *   <li>When {@link Document#Document()}.
   *   <li>Then return WordPart is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCompletionRequest#SQLCompletionRequest(SQLCompletionContext,
   * IDocument, int, SQLScriptElement, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLCompletionRequest.<init>(SQLCompletionContext, IDocument, int, SQLScriptElement, boolean)"
  })
  public void testNewSQLCompletionRequest_whenDocument_thenReturnWordPartIsNull() {
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

    // Act
    SQLCompletionRequest actualSqlCompletionRequest =
        new SQLCompletionRequest(context, document, 1, activeQuery, true);

    // Assert
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlSyntaxManager).getDialect();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(context).getSyntaxManager();
    assertNull(actualSqlCompletionRequest.getWordPart());
    SQLWordPartDetector wordDetector = actualSqlCompletionRequest.getWordDetector();
    assertNull(wordDetector.getFullWord());
    assertNull(wordDetector.getWordPart());
    assertEquals(0, wordDetector.getStartOffset());
    assertEquals(1, wordDetector.getLength());
    assertSame(document, actualSqlCompletionRequest.getDocument());
  }

  /**
   * Test {@link SQLCompletionRequest#SQLCompletionRequest(SQLCompletionContext, IDocument, int,
   * SQLScriptElement, boolean)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return DocumentOffset is minus one.
   * </ul>
   *
   * <p>Method under test: {@link SQLCompletionRequest#SQLCompletionRequest(SQLCompletionContext,
   * IDocument, int, SQLScriptElement, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLCompletionRequest.<init>(SQLCompletionContext, IDocument, int, SQLScriptElement, boolean)"
  })
  public void testNewSQLCompletionRequest_whenMinusOne_thenReturnDocumentOffsetIsMinusOne()
      throws BadLocationException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStructSeparator()).thenReturn('A');
    when(sqlSyntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"___sql_partitioning", "sql_quoted"}});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLCompletionContext context = mock(SQLCompletionContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);

    IDocument document = mock(IDocument.class);
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");
    SQLQuery activeQuery = new SQLQuery(mock(DBPDataSource.class), "Text");

    // Act
    SQLCompletionRequest actualSqlCompletionRequest =
        new SQLCompletionRequest(context, document, -1, activeQuery, true);

    // Assert
    verify(document, atLeast(1)).get(-1, 0);
    verify(document).getContentType(-1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlSyntaxManager).getDialect();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(context).getSyntaxManager();
    SQLScriptElement activeQuery2 = actualSqlCompletionRequest.getActiveQuery();
    assertTrue(activeQuery2 instanceof SQLQuery);
    assertEquals(-1, actualSqlCompletionRequest.getDocumentOffset());
    SQLWordPartDetector wordDetector = actualSqlCompletionRequest.getWordDetector();
    assertEquals(-1, wordDetector.getCursorOffset());
    assertEquals(-1, wordDetector.getEndOffset());
    assertEquals(-1, wordDetector.getStartOffset());
    List<SQLScriptElement> scriptElements = ((SQLQuery) activeQuery2).getScriptElements();
    assertEquals(1, scriptElements.size());
    assertSame(activeQuery2, scriptElements.get(0));
  }
}
