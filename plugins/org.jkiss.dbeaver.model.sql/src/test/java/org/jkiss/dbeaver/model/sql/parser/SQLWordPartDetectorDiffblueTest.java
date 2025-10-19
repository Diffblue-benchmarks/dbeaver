package org.jkiss.dbeaver.model.sql.parser;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.SQLSyntaxManager;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLWordPartDetectorDiffblueTest {
  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int)}.
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int)"})
  public void testNewSQLWordPartDetector() throws BadLocationException {
    // Arrange
    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('￿');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(true);
    when(sqlDialect.isEntityQueryWord(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(syntaxManager.getIdentifierQuoteStrings())
        .thenReturn(
            new String[][] {
              new String[] {
                SQLParserPartitions.SQL_PARTITIONING, SQLParserPartitions.CONTENT_TYPE_SQL_QUOTED
              }
            });
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), anyInt());
    verify(document, atLeast(1)).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).isEntityQueryWord("Get");
    verify(sqlDialect, atLeast(1)).validIdentifierPart('￿', true);
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStatementDelimiters();
    verify(syntaxManager).getStructSeparator();
    assertEquals("", actualSqlWordPartDetector.getPrevDelimiter());
    assertEquals("GET", actualSqlWordPartDetector.getPrevKeyWord());
    assertEquals(0, actualSqlWordPartDetector.getLength());
    assertEquals(0, actualSqlWordPartDetector.getPrevKeyWordOffset());
    assertEquals(1, actualSqlWordPartDetector.getStartOffset());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int)}.
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int)"})
  public void testNewSQLWordPartDetector2() throws BadLocationException {
    // Arrange
    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('￿');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(true);
    when(sqlDialect.isAttributeQueryWord(Mockito.<String>any())).thenReturn(false);
    when(sqlDialect.isEntityQueryWord(Mockito.<String>any())).thenReturn(false);
    when(sqlDialect.getExecuteKeywords()).thenReturn(new String[] {});
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(syntaxManager.getIdentifierQuoteStrings()).thenReturn(null);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), anyInt());
    verify(document, atLeast(1)).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getExecuteKeywords();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).isAttributeQueryWord("Get");
    verify(sqlDialect).isEntityQueryWord("Get");
    verify(sqlDialect, atLeast(1)).validIdentifierPart('￿', true);
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStatementDelimiters();
    verify(syntaxManager).getStructSeparator();
    assertEquals("", actualSqlWordPartDetector.getPrevKeyWord());
    List<String> prevWords = actualSqlWordPartDetector.getPrevWords();
    assertEquals(1, prevWords.size());
    assertEquals("Get", prevWords.get(0));
    assertEquals(-1, actualSqlWordPartDetector.getPrevKeyWordOffset());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int, int)}.
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int, int)"})
  public void testNewSQLWordPartDetector3() throws BadLocationException {
    // Arrange
    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('￿');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(true);
    when(sqlDialect.isEntityQueryWord(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(syntaxManager.getIdentifierQuoteStrings())
        .thenReturn(
            new String[][] {
              new String[] {
                SQLParserPartitions.SQL_PARTITIONING, SQLParserPartitions.CONTENT_TYPE_SQL_QUOTED
              }
            });
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1, 1);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), anyInt());
    verify(document, atLeast(1)).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).isEntityQueryWord("Get");
    verify(sqlDialect, atLeast(1)).validIdentifierPart('￿', true);
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStatementDelimiters();
    verify(syntaxManager).getStructSeparator();
    assertEquals("", actualSqlWordPartDetector.getPrevDelimiter());
    assertEquals("GET", actualSqlWordPartDetector.getPrevKeyWord());
    assertEquals(0, actualSqlWordPartDetector.getLength());
    assertEquals(0, actualSqlWordPartDetector.getPrevKeyWordOffset());
    assertEquals(1, actualSqlWordPartDetector.getStartOffset());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int)}.
   *
   * <ul>
   *   <li>Given {@code a}.
   *   <li>When {@link IDocument} {@link IDocument#getChar(int)} return {@code a}.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int)"})
  public void testNewSQLWordPartDetector_givenA_whenIDocumentGetCharReturnA()
      throws BadLocationException {
    // Arrange
    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('a');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isEntityQueryWord(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(syntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {SQLParserPartitions.SQL_PARTITIONING}});
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), anyInt());
    verify(document, atLeast(1)).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).isEntityQueryWord("Get");
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStatementDelimiters();
    verify(syntaxManager).getStructSeparator();
    assertEquals("", actualSqlWordPartDetector.getPrevDelimiter());
    assertEquals("GET", actualSqlWordPartDetector.getPrevKeyWord());
    assertEquals(0, actualSqlWordPartDetector.getLength());
    assertEquals(0, actualSqlWordPartDetector.getPrevKeyWordOffset());
    assertEquals(1, actualSqlWordPartDetector.getStartOffset());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int, int)}.
   *
   * <ul>
   *   <li>Given {@code a}.
   *   <li>When {@link IDocument} {@link IDocument#getChar(int)} return {@code a}.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int, int)"})
  public void testNewSQLWordPartDetector_givenA_whenIDocumentGetCharReturnA2()
      throws BadLocationException {
    // Arrange
    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('a');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isEntityQueryWord(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(syntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {SQLParserPartitions.SQL_PARTITIONING}});
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1, 1);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), anyInt());
    verify(document, atLeast(1)).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).isEntityQueryWord("Get");
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStatementDelimiters();
    verify(syntaxManager).getStructSeparator();
    assertEquals("", actualSqlWordPartDetector.getPrevDelimiter());
    assertEquals("GET", actualSqlWordPartDetector.getPrevKeyWord());
    assertEquals(0, actualSqlWordPartDetector.getLength());
    assertEquals(0, actualSqlWordPartDetector.getPrevKeyWordOffset());
    assertEquals(1, actualSqlWordPartDetector.getStartOffset());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int)"})
  public void testNewSQLWordPartDetector_givenArrayOfStringWithEmptyString()
      throws BadLocationException {
    // Arrange
    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('￿');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(true);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStatementDelimiters()).thenReturn(new String[] {""});
    when(syntaxManager.getIdentifierQuoteStrings()).thenReturn(null);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1);

    // Assert
    verify(document, atLeast(1)).get(1, 0);
    verify(document).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).validIdentifierPart('￿', true);
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStatementDelimiters();
    verify(syntaxManager).getStructSeparator();
    assertEquals("", actualSqlWordPartDetector.getPrevDelimiter());
    assertEquals("", actualSqlWordPartDetector.getPrevKeyWord());
    assertEquals(-1, actualSqlWordPartDetector.getPrevKeyWordOffset());
    assertEquals(0, actualSqlWordPartDetector.getLength());
    assertEquals(1, actualSqlWordPartDetector.getStartOffset());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code Identifier Quote Strings}.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int)"})
  public void testNewSQLWordPartDetector_givenArrayOfStringWithIdentifierQuoteStrings() {
    // Arrange
    Document document = new Document();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1);

    // Assert
    verify(sqlDialect).getStringQuoteStrings();
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStructSeparator();
    assertNull(actualSqlWordPartDetector.getFullWord());
    assertNull(actualSqlWordPartDetector.getPrevDelimiter());
    assertNull(actualSqlWordPartDetector.getWordPart());
    assertEquals(0, actualSqlWordPartDetector.getStartOffset());
    assertEquals(1, actualSqlWordPartDetector.getLength());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int, int)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code Identifier Quote Strings}.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int, int)"})
  public void testNewSQLWordPartDetector_givenArrayOfStringWithIdentifierQuoteStrings2() {
    // Arrange
    Document document = new Document();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1, 1);

    // Assert
    verify(sqlDialect).getStringQuoteStrings();
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStructSeparator();
    assertNull(actualSqlWordPartDetector.getFullWord());
    assertNull(actualSqlWordPartDetector.getPrevDelimiter());
    assertNull(actualSqlWordPartDetector.getWordPart());
    assertEquals(0, actualSqlWordPartDetector.getStartOffset());
    assertEquals(1, actualSqlWordPartDetector.getLength());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int)}.
   *
   * <ul>
   *   <li>Given {@link BadLocationException#BadLocationException(String)} with message is {@code An
   *       error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int)"})
  public void testNewSQLWordPartDetector_givenBadLocationExceptionWithMessageIsAnErrorOccurred()
      throws BadLocationException {
    // Arrange
    IDocument document = mock(IDocument.class);
    when(document.getLength()).thenReturn(3);
    when(document.getContentType(anyInt()))
        .thenThrow(new BadLocationException("An error occurred"));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getIdentifierQuoteStrings()).thenReturn(null);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1);

    // Assert
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStructSeparator();
    assertNull(actualSqlWordPartDetector.getFullWord());
    assertNull(actualSqlWordPartDetector.getPrevDelimiter());
    assertNull(actualSqlWordPartDetector.getWordPart());
    assertEquals(0, actualSqlWordPartDetector.getStartOffset());
    assertEquals(1, actualSqlWordPartDetector.getLength());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int, int)}.
   *
   * <ul>
   *   <li>Given {@link BadLocationException#BadLocationException(String)} with message is {@code An
   *       error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int, int)"})
  public void testNewSQLWordPartDetector_givenBadLocationExceptionWithMessageIsAnErrorOccurred2()
      throws BadLocationException {
    // Arrange
    IDocument document = mock(IDocument.class);
    when(document.getLength()).thenReturn(3);
    when(document.getContentType(anyInt()))
        .thenThrow(new BadLocationException("An error occurred"));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getIdentifierQuoteStrings()).thenReturn(null);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1, 1);

    // Assert
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStructSeparator();
    assertNull(actualSqlWordPartDetector.getFullWord());
    assertNull(actualSqlWordPartDetector.getPrevDelimiter());
    assertNull(actualSqlWordPartDetector.getWordPart());
    assertEquals(0, actualSqlWordPartDetector.getStartOffset());
    assertEquals(1, actualSqlWordPartDetector.getLength());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int)}.
   *
   * <ul>
   *   <li>Given {@code $}.
   *   <li>When {@link IDocument} {@link IDocument#getChar(int)} return {@code $}.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int)"})
  public void testNewSQLWordPartDetector_givenDollarSign_whenIDocumentGetCharReturnDollarSign()
      throws BadLocationException {
    // Arrange
    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('$');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isEntityQueryWord(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(syntaxManager.getIdentifierQuoteStrings()).thenReturn(null);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), anyInt());
    verify(document, atLeast(1)).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).isEntityQueryWord("Get");
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStatementDelimiters();
    verify(syntaxManager).getStructSeparator();
    assertEquals("", actualSqlWordPartDetector.getPrevDelimiter());
    assertEquals("GET", actualSqlWordPartDetector.getPrevKeyWord());
    assertEquals(0, actualSqlWordPartDetector.getLength());
    assertEquals(0, actualSqlWordPartDetector.getPrevKeyWordOffset());
    assertEquals(1, actualSqlWordPartDetector.getStartOffset());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int, int)}.
   *
   * <ul>
   *   <li>Given {@code $}.
   *   <li>When {@link IDocument} {@link IDocument#getChar(int)} return {@code $}.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int, int)"})
  public void testNewSQLWordPartDetector_givenDollarSign_whenIDocumentGetCharReturnDollarSign2()
      throws BadLocationException {
    // Arrange
    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('$');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isEntityQueryWord(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(syntaxManager.getIdentifierQuoteStrings()).thenReturn(null);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1, 1);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), anyInt());
    verify(document, atLeast(1)).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).isEntityQueryWord("Get");
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStatementDelimiters();
    verify(syntaxManager).getStructSeparator();
    assertEquals("", actualSqlWordPartDetector.getPrevDelimiter());
    assertEquals("GET", actualSqlWordPartDetector.getPrevKeyWord());
    assertEquals(0, actualSqlWordPartDetector.getLength());
    assertEquals(0, actualSqlWordPartDetector.getPrevKeyWordOffset());
    assertEquals(1, actualSqlWordPartDetector.getStartOffset());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link Document#Document()}.
   *   <li>Then return FullWord is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int)"})
  public void testNewSQLWordPartDetector_givenNull_whenDocument_thenReturnFullWordIsNull() {
    // Arrange
    Document document = new Document();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getIdentifierQuoteStrings()).thenReturn(null);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1);

    // Assert
    verify(sqlDialect).getStringQuoteStrings();
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStructSeparator();
    assertNull(actualSqlWordPartDetector.getFullWord());
    assertNull(actualSqlWordPartDetector.getPrevDelimiter());
    assertNull(actualSqlWordPartDetector.getWordPart());
    assertEquals(0, actualSqlWordPartDetector.getStartOffset());
    assertEquals(1, actualSqlWordPartDetector.getLength());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int, int)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link Document#Document()}.
   *   <li>Then return FullWord is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int, int)"})
  public void testNewSQLWordPartDetector_givenNull_whenDocument_thenReturnFullWordIsNull2() {
    // Arrange
    Document document = new Document();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getIdentifierQuoteStrings()).thenReturn(null);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1, 1);

    // Assert
    verify(sqlDialect).getStringQuoteStrings();
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStructSeparator();
    assertNull(actualSqlWordPartDetector.getFullWord());
    assertNull(actualSqlWordPartDetector.getPrevDelimiter());
    assertNull(actualSqlWordPartDetector.getWordPart());
    assertEquals(0, actualSqlWordPartDetector.getStartOffset());
    assertEquals(1, actualSqlWordPartDetector.getLength());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getExecuteKeywords()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int)"})
  public void testNewSQLWordPartDetector_givenSQLDialectGetExecuteKeywordsReturnNull()
      throws BadLocationException {
    // Arrange
    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('￿');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(true);
    when(sqlDialect.isAttributeQueryWord(Mockito.<String>any())).thenReturn(false);
    when(sqlDialect.isEntityQueryWord(Mockito.<String>any())).thenReturn(false);
    when(sqlDialect.getExecuteKeywords()).thenReturn(null);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(syntaxManager.getIdentifierQuoteStrings()).thenReturn(null);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), anyInt());
    verify(document, atLeast(1)).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getExecuteKeywords();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).isAttributeQueryWord("Get");
    verify(sqlDialect).isEntityQueryWord("Get");
    verify(sqlDialect, atLeast(1)).validIdentifierPart('￿', true);
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStatementDelimiters();
    verify(syntaxManager).getStructSeparator();
    assertEquals("", actualSqlWordPartDetector.getPrevKeyWord());
    List<String> prevWords = actualSqlWordPartDetector.getPrevWords();
    assertEquals(1, prevWords.size());
    assertEquals("Get", prevWords.get(0));
    assertEquals(-1, actualSqlWordPartDetector.getPrevKeyWordOffset());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int)}.
   *
   * <ul>
   *   <li>Then calls {@link SQLDialect#isAttributeQueryWord(String)}.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int)"})
  public void testNewSQLWordPartDetector_thenCallsIsAttributeQueryWord()
      throws BadLocationException {
    // Arrange
    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('￿');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(true);
    when(sqlDialect.isAttributeQueryWord(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.isEntityQueryWord(Mockito.<String>any())).thenReturn(false);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(syntaxManager.getIdentifierQuoteStrings()).thenReturn(null);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), anyInt());
    verify(document, atLeast(1)).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).isAttributeQueryWord("Get");
    verify(sqlDialect).isEntityQueryWord("Get");
    verify(sqlDialect, atLeast(1)).validIdentifierPart('￿', true);
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStatementDelimiters();
    verify(syntaxManager).getStructSeparator();
    assertEquals("", actualSqlWordPartDetector.getPrevDelimiter());
    assertEquals("GET", actualSqlWordPartDetector.getPrevKeyWord());
    assertEquals(0, actualSqlWordPartDetector.getLength());
    assertEquals(0, actualSqlWordPartDetector.getPrevKeyWordOffset());
    assertEquals(1, actualSqlWordPartDetector.getStartOffset());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int, int)}.
   *
   * <ul>
   *   <li>Then calls {@link SQLDialect#isAttributeQueryWord(String)}.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int, int)"})
  public void testNewSQLWordPartDetector_thenCallsIsAttributeQueryWord2()
      throws BadLocationException {
    // Arrange
    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('￿');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(true);
    when(sqlDialect.isAttributeQueryWord(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.isEntityQueryWord(Mockito.<String>any())).thenReturn(false);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(syntaxManager.getIdentifierQuoteStrings()).thenReturn(null);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1, 1);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), anyInt());
    verify(document, atLeast(1)).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).isAttributeQueryWord("Get");
    verify(sqlDialect).isEntityQueryWord("Get");
    verify(sqlDialect, atLeast(1)).validIdentifierPart('￿', true);
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStatementDelimiters();
    verify(syntaxManager).getStructSeparator();
    assertEquals("", actualSqlWordPartDetector.getPrevDelimiter());
    assertEquals("GET", actualSqlWordPartDetector.getPrevKeyWord());
    assertEquals(0, actualSqlWordPartDetector.getLength());
    assertEquals(0, actualSqlWordPartDetector.getPrevKeyWordOffset());
    assertEquals(1, actualSqlWordPartDetector.getStartOffset());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int, int)}.
   *
   * <ul>
   *   <li>Then calls {@link SQLDialect#validIdentifierPart(char, boolean)}.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int, int)"})
  public void testNewSQLWordPartDetector_thenCallsValidIdentifierPart()
      throws BadLocationException {
    // Arrange
    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('￿');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(true);
    when(sqlDialect.isEntityQueryWord(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(syntaxManager.getIdentifierQuoteStrings()).thenReturn(null);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1, 1);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), anyInt());
    verify(document, atLeast(1)).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).isEntityQueryWord("Get");
    verify(sqlDialect, atLeast(1)).validIdentifierPart('￿', true);
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStatementDelimiters();
    verify(syntaxManager).getStructSeparator();
    assertEquals("", actualSqlWordPartDetector.getPrevDelimiter());
    assertEquals("GET", actualSqlWordPartDetector.getPrevKeyWord());
    assertEquals(0, actualSqlWordPartDetector.getLength());
    assertEquals(0, actualSqlWordPartDetector.getPrevKeyWordOffset());
    assertEquals(1, actualSqlWordPartDetector.getStartOffset());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int)}.
   *
   * <ul>
   *   <li>Then return NextWord is {@code Get}.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int)"})
  public void testNewSQLWordPartDetector_thenReturnNextWordIsGet() throws BadLocationException {
    // Arrange
    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('A');
    when(document.getLength()).thenReturn(3);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(
            new String[][] {
              new String[] {
                SQLParserPartitions.SQL_PARTITIONING, SQLParserPartitions.CONTENT_TYPE_SQL_QUOTED
              }
            });

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getIdentifierQuoteStrings()).thenReturn(null);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), anyInt());
    verify(document, atLeast(1)).getChar(anyInt());
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStructSeparator();
    assertEquals("Get", actualSqlWordPartDetector.getNextWord());
    assertNull(actualSqlWordPartDetector.getPrevDelimiter());
    assertEquals(0, actualSqlWordPartDetector.getStartOffset());
    assertEquals(3, actualSqlWordPartDetector.getEndOffset());
    assertEquals(3, actualSqlWordPartDetector.getLength());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int, int)}.
   *
   * <ul>
   *   <li>Then return NextWord is {@code Get}.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int, int)"})
  public void testNewSQLWordPartDetector_thenReturnNextWordIsGet2() throws BadLocationException {
    // Arrange
    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('A');
    when(document.getLength()).thenReturn(3);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(
            new String[][] {
              new String[] {
                SQLParserPartitions.SQL_PARTITIONING, SQLParserPartitions.CONTENT_TYPE_SQL_QUOTED
              }
            });

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getIdentifierQuoteStrings()).thenReturn(null);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1, 1);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), anyInt());
    verify(document, atLeast(1)).getChar(anyInt());
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStructSeparator();
    assertEquals("Get", actualSqlWordPartDetector.getNextWord());
    assertNull(actualSqlWordPartDetector.getPrevDelimiter());
    assertEquals(0, actualSqlWordPartDetector.getStartOffset());
    assertEquals(3, actualSqlWordPartDetector.getEndOffset());
    assertEquals(3, actualSqlWordPartDetector.getLength());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int)}.
   *
   * <ul>
   *   <li>Then return PrevDelimiter is empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int)"})
  public void testNewSQLWordPartDetector_thenReturnPrevDelimiterIsEmptyString()
      throws BadLocationException {
    // Arrange
    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('￿');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(true);
    when(sqlDialect.isEntityQueryWord(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(syntaxManager.getIdentifierQuoteStrings()).thenReturn(null);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), anyInt());
    verify(document, atLeast(1)).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).isEntityQueryWord("Get");
    verify(sqlDialect, atLeast(1)).validIdentifierPart('￿', true);
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStatementDelimiters();
    verify(syntaxManager).getStructSeparator();
    assertEquals("", actualSqlWordPartDetector.getPrevDelimiter());
    assertEquals("GET", actualSqlWordPartDetector.getPrevKeyWord());
    assertEquals(0, actualSqlWordPartDetector.getLength());
    assertEquals(0, actualSqlWordPartDetector.getPrevKeyWordOffset());
    assertEquals(1, actualSqlWordPartDetector.getStartOffset());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int)}.
   *
   * <ul>
   *   <li>Then return PrevDelimiter is {@code ￿}.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int)"})
  public void testNewSQLWordPartDetector_thenReturnPrevDelimiterIsUffff()
      throws BadLocationException {
    // Arrange
    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('￿');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(false);
    when(sqlDialect.isEntityQueryWord(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(syntaxManager.getIdentifierQuoteStrings()).thenReturn(null);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), eq(0));
    verify(document).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).isEntityQueryWord("Get");
    verify(sqlDialect).validIdentifierPart('￿', true);
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStatementDelimiters();
    verify(syntaxManager).getStructSeparator();
    assertEquals("GET", actualSqlWordPartDetector.getPrevKeyWord());
    assertEquals("￿", actualSqlWordPartDetector.getPrevDelimiter());
    assertEquals(0, actualSqlWordPartDetector.getLength());
    assertEquals(0, actualSqlWordPartDetector.getPrevKeyWordOffset());
    assertEquals(1, actualSqlWordPartDetector.getStartOffset());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int, int)}.
   *
   * <ul>
   *   <li>Then return PrevDelimiter is {@code ￿}.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int, int)"})
  public void testNewSQLWordPartDetector_thenReturnPrevDelimiterIsUffff2()
      throws BadLocationException {
    // Arrange
    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('￿');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(false);
    when(sqlDialect.isEntityQueryWord(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(syntaxManager.getIdentifierQuoteStrings()).thenReturn(null);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1, 1);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), eq(0));
    verify(document).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).isEntityQueryWord("Get");
    verify(sqlDialect).validIdentifierPart('￿', true);
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStatementDelimiters();
    verify(syntaxManager).getStructSeparator();
    assertEquals("GET", actualSqlWordPartDetector.getPrevKeyWord());
    assertEquals("￿", actualSqlWordPartDetector.getPrevDelimiter());
    assertEquals(0, actualSqlWordPartDetector.getLength());
    assertEquals(0, actualSqlWordPartDetector.getPrevKeyWordOffset());
    assertEquals(1, actualSqlWordPartDetector.getStartOffset());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int, int)}.
   *
   * <ul>
   *   <li>Then return PrevKeyWord is empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int, int)"})
  public void testNewSQLWordPartDetector_thenReturnPrevKeyWordIsEmptyString()
      throws BadLocationException {
    // Arrange
    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('￿');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(true);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStatementDelimiters()).thenReturn(new String[] {""});
    when(syntaxManager.getIdentifierQuoteStrings()).thenReturn(null);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1, 1);

    // Assert
    verify(document, atLeast(1)).get(1, 0);
    verify(document).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).validIdentifierPart('￿', true);
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStatementDelimiters();
    verify(syntaxManager).getStructSeparator();
    assertEquals("", actualSqlWordPartDetector.getPrevKeyWord());
    assertEquals(-1, actualSqlWordPartDetector.getPrevKeyWordOffset());
    assertEquals(0, actualSqlWordPartDetector.getLength());
    assertEquals(1, actualSqlWordPartDetector.getStartOffset());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int)}.
   *
   * <ul>
   *   <li>Then return PrevWords size is one.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int)"})
  public void testNewSQLWordPartDetector_thenReturnPrevWordsSizeIsOne()
      throws BadLocationException {
    // Arrange
    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('￿');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(true);
    when(sqlDialect.isAttributeQueryWord(Mockito.<String>any())).thenReturn(false);
    when(sqlDialect.isEntityQueryWord(Mockito.<String>any())).thenReturn(false);
    when(sqlDialect.getExecuteKeywords()).thenReturn(new String[] {"Execute Keywords"});
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(syntaxManager.getIdentifierQuoteStrings()).thenReturn(null);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), anyInt());
    verify(document, atLeast(1)).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getExecuteKeywords();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).isAttributeQueryWord("Get");
    verify(sqlDialect).isEntityQueryWord("Get");
    verify(sqlDialect, atLeast(1)).validIdentifierPart('￿', true);
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStatementDelimiters();
    verify(syntaxManager).getStructSeparator();
    assertEquals("", actualSqlWordPartDetector.getPrevKeyWord());
    List<String> prevWords = actualSqlWordPartDetector.getPrevWords();
    assertEquals(1, prevWords.size());
    assertEquals("Get", prevWords.get(0));
    assertEquals(-1, actualSqlWordPartDetector.getPrevKeyWordOffset());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int)}.
   *
   * <ul>
   *   <li>Then return StructSeparator is {@code ￿}.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int)"})
  public void testNewSQLWordPartDetector_thenReturnStructSeparatorIsUffff()
      throws BadLocationException {
    // Arrange
    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('￿');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isEntityQueryWord(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(syntaxManager.getIdentifierQuoteStrings()).thenReturn(null);
    when(syntaxManager.getStructSeparator()).thenReturn('￿');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), anyInt());
    verify(document, atLeast(1)).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).isEntityQueryWord("Get");
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStatementDelimiters();
    verify(syntaxManager).getStructSeparator();
    assertEquals("", actualSqlWordPartDetector.getPrevDelimiter());
    assertEquals("GET", actualSqlWordPartDetector.getPrevKeyWord());
    assertEquals('￿', actualSqlWordPartDetector.getStructSeparator());
    assertEquals(0, actualSqlWordPartDetector.getPrevKeyWordOffset());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int, int)}.
   *
   * <ul>
   *   <li>Then return StructSeparator is {@code ￿}.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int, int)"})
  public void testNewSQLWordPartDetector_thenReturnStructSeparatorIsUffff2()
      throws BadLocationException {
    // Arrange
    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('￿');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isEntityQueryWord(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(syntaxManager.getIdentifierQuoteStrings()).thenReturn(null);
    when(syntaxManager.getStructSeparator()).thenReturn('￿');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1, 1);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), anyInt());
    verify(document, atLeast(1)).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).isEntityQueryWord("Get");
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStatementDelimiters();
    verify(syntaxManager).getStructSeparator();
    assertEquals("", actualSqlWordPartDetector.getPrevDelimiter());
    assertEquals("GET", actualSqlWordPartDetector.getPrevKeyWord());
    assertEquals('￿', actualSqlWordPartDetector.getStructSeparator());
    assertEquals(0, actualSqlWordPartDetector.getPrevKeyWordOffset());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int)}.
   *
   * <ul>
   *   <li>When {@link IDocument} {@link IDocument#getChar(int)} return {@code A}.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int)"})
  public void testNewSQLWordPartDetector_whenIDocumentGetCharReturnA() throws BadLocationException {
    // Arrange
    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('A');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isEntityQueryWord(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(syntaxManager.getIdentifierQuoteStrings()).thenReturn(null);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), anyInt());
    verify(document, atLeast(1)).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).isEntityQueryWord("Get");
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStatementDelimiters();
    verify(syntaxManager).getStructSeparator();
    assertEquals("", actualSqlWordPartDetector.getPrevDelimiter());
    assertEquals("GET", actualSqlWordPartDetector.getPrevKeyWord());
    assertEquals(0, actualSqlWordPartDetector.getLength());
    assertEquals(0, actualSqlWordPartDetector.getPrevKeyWordOffset());
    assertEquals(1, actualSqlWordPartDetector.getStartOffset());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int, int)}.
   *
   * <ul>
   *   <li>When {@link IDocument} {@link IDocument#getChar(int)} return {@code A}.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int, int)"})
  public void testNewSQLWordPartDetector_whenIDocumentGetCharReturnA2()
      throws BadLocationException {
    // Arrange
    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('A');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isEntityQueryWord(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(syntaxManager.getIdentifierQuoteStrings()).thenReturn(null);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1, 1);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), anyInt());
    verify(document, atLeast(1)).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).isEntityQueryWord("Get");
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStatementDelimiters();
    verify(syntaxManager).getStructSeparator();
    assertEquals("", actualSqlWordPartDetector.getPrevDelimiter());
    assertEquals("GET", actualSqlWordPartDetector.getPrevKeyWord());
    assertEquals(0, actualSqlWordPartDetector.getLength());
    assertEquals(0, actualSqlWordPartDetector.getPrevKeyWordOffset());
    assertEquals(1, actualSqlWordPartDetector.getStartOffset());
  }

  /**
   * Test {@link SQLWordPartDetector#SQLWordPartDetector(IDocument, SQLSyntaxManager, int, int)}.
   *
   * <ul>
   *   <li>When thirty-six.
   *   <li>Then return PrevWords size is one.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#SQLWordPartDetector(IDocument,
   * SQLSyntaxManager, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordPartDetector.<init>(IDocument, SQLSyntaxManager, int, int)"})
  public void testNewSQLWordPartDetector_whenThirtySix_thenReturnPrevWordsSizeIsOne()
      throws BadLocationException {
    // Arrange
    IDocument document = mock(IDocument.class);
    when(document.getChar(anyInt())).thenReturn('￿');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(anyInt())).thenReturn("text/plain");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(true);
    when(sqlDialect.isEntityQueryWord(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(syntaxManager.getIdentifierQuoteStrings()).thenReturn(null);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);

    // Act
    SQLWordPartDetector actualSqlWordPartDetector =
        new SQLWordPartDetector(document, syntaxManager, 1, 36);

    // Assert
    verify(document, atLeast(1)).get(anyInt(), anyInt());
    verify(document, atLeast(1)).getChar(0);
    verify(document).getContentType(1);
    verify(document).getLength();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect).isEntityQueryWord("Get");
    verify(sqlDialect, atLeast(1)).validIdentifierPart('￿', true);
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStatementDelimiters();
    verify(syntaxManager).getStructSeparator();
    assertEquals("", actualSqlWordPartDetector.getPrevDelimiter());
    assertEquals("GET", actualSqlWordPartDetector.getPrevKeyWord());
    List<String> prevWords = actualSqlWordPartDetector.getPrevWords();
    assertEquals(1, prevWords.size());
    assertEquals("Get", prevWords.get(0));
    assertEquals(0, actualSqlWordPartDetector.getPrevKeyWordOffset());
  }

  /**
   * Test {@link SQLWordPartDetector#getLength()}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code String Quote Strings}.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#getLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLWordPartDetector.getLength()"})
  public void testGetLength_givenArrayOfStringWithStringQuoteStrings_thenReturnOne() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);
    SQLWordPartDetector sqlWordPartDetector =
        new SQLWordPartDetector(new Document(), syntaxManager, 1);

    // Act
    int actualLength = sqlWordPartDetector.getLength();

    // Assert
    verify(sqlDialect).getStringQuoteStrings();
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStructSeparator();
    assertEquals(1, actualLength);
  }

  /**
   * Test {@link SQLWordPartDetector#splitWordPart()}.
   *
   * <p>Method under test: {@link SQLWordPartDetector#splitWordPart()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLWordPartDetector.splitWordPart()"})
  public void testSplitWordPart() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getIdentifierQuoteStrings()).thenReturn(new String[][] {});
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);
    SQLWordPartDetector sqlWordPartDetector =
        new SQLWordPartDetector(new Document(), syntaxManager, 0);

    // Act
    String[] actualSplitWordPartResult = sqlWordPartDetector.splitWordPart();

    // Assert
    verify(sqlDialect).getStringQuoteStrings();
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStructSeparator();
    assertArrayEquals(new String[] {""}, actualSplitWordPartResult);
  }

  /**
   * Test {@link SQLWordPartDetector#splitWordPart()}.
   *
   * <ul>
   *   <li>Then return array of {@link String} with empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordPartDetector#splitWordPart()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLWordPartDetector.splitWordPart()"})
  public void testSplitWordPart_thenReturnArrayOfStringWithEmptyString() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(syntaxManager.getDialect()).thenReturn(sqlDialect);
    SQLWordPartDetector sqlWordPartDetector =
        new SQLWordPartDetector(new Document(), syntaxManager, 0);

    // Act
    String[] actualSplitWordPartResult = sqlWordPartDetector.splitWordPart();

    // Assert
    verify(sqlDialect).getStringQuoteStrings();
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStructSeparator();
    assertArrayEquals(new String[] {""}, actualSplitWordPartResult);
  }
}
