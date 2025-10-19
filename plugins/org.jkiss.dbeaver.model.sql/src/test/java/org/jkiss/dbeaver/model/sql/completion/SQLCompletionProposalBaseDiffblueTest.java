package org.jkiss.dbeaver.model.sql.completion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPartitioningException;
import org.eclipse.jface.text.Document;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.DBPKeywordType;
import org.jkiss.dbeaver.model.DBPNamedObject;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.SQLSyntaxManager;
import org.jkiss.dbeaver.model.sql.parser.SQLWordPartDetector;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLCompletionProposalBaseDiffblueTest {
  /**
   * Test {@link SQLCompletionProposalBase#SQLCompletionProposalBase(SQLCompletionRequest, String,
   * String, int, DBPImage, DBPKeywordType, String, DBPNamedObject, Map)}.
   *
   * <ul>
   *   <li>Then return {@link SQLCompletionProposalBase#replacementFull} is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLCompletionProposalBase#SQLCompletionProposalBase(SQLCompletionRequest, String, String, int,
   * DBPImage, DBPKeywordType, String, DBPNamedObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLCompletionProposalBase.<init>(SQLCompletionRequest, String, String, int, DBPImage, DBPKeywordType, String, DBPNamedObject, Map)"
  })
  public void testNewSQLCompletionProposalBase_thenReturnReplacementFullIs42()
      throws BadLocationException, BadPartitioningException {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isQuotedIdentifier(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStructSeparator()).thenReturn('A');

    SQLCompletionContext sqlCompletionContext = mock(SQLCompletionContext.class);
    when(sqlCompletionContext.isReplaceWords()).thenReturn(true);
    when(sqlCompletionContext.isUseFQNames()).thenReturn(true);
    when(sqlCompletionContext.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(sqlCompletionContext.getDataSource()).thenReturn(dbpDataSource);

    Document document = mock(Document.class);
    when(document.getChar(anyInt())).thenReturn('A');
    when(document.getLength()).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getContentType(Mockito.<String>any(), anyInt(), anyBoolean()))
        .thenReturn("text/plain");

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.isEntityQueryWord(Mockito.<String>any())).thenReturn(true);
    when(sqlDialect2.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(syntaxManager.getStructSeparator()).thenReturn('A');
    when(syntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"foo", "42"}});
    when(syntaxManager.getDialect()).thenReturn(sqlDialect2);

    SQLWordPartDetector sqlWordPartDetector = new SQLWordPartDetector(document, syntaxManager, 1);

    SQLCompletionRequest request = mock(SQLCompletionRequest.class);
    when(request.getWordDetector()).thenReturn(sqlWordPartDetector);
    when(request.getContext()).thenReturn(sqlCompletionContext);
    DBPImage image = mock(DBPImage.class);
    DBPNamedObject object = mock(DBPNamedObject.class);

    // Act
    SQLCompletionProposalBase actualSqlCompletionProposalBase =
        new SQLCompletionProposalBase(
            request,
            "Display String",
            "Replacement String",
            1,
            image,
            DBPKeywordType.KEYWORD,
            "The characteristics of someone or something",
            object,
            new HashMap<>());

    // Assert
    verify(document, atLeast(1)).get(anyInt(), anyInt());
    verify(document, atLeast(1)).getChar(0);
    verify(document).getContentType("___sql_partitioning", 1, true);
    verify(document).getLength();
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect2).getStringQuoteStrings();
    verify(sqlDialect).getUnquotedIdentifier("replacement string");
    verify(sqlDialect2).isEntityQueryWord("Get");
    verify(sqlDialect).isQuotedIdentifier("Get");
    verify(syntaxManager).getDialect();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(syntaxManager).getStatementDelimiters();
    verify(syntaxManager).getStructSeparator();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(sqlCompletionContext, atLeast(1)).getDataSource();
    verify(sqlCompletionContext).getSyntaxManager();
    verify(sqlCompletionContext).isReplaceWords();
    verify(sqlCompletionContext).isUseFQNames();
    verify(request, atLeast(1)).getContext();
    verify(request).getWordDetector();
    assertEquals("42", actualSqlCompletionProposalBase.replacementFull);
    assertEquals("Display String", actualSqlCompletionProposalBase.getDisplayString());
    assertEquals("Display String", actualSqlCompletionProposalBase.toString());
    assertEquals("Replacement String", actualSqlCompletionProposalBase.getReplacementString());
    assertNull(actualSqlCompletionProposalBase.getExtraString());
    assertNull(actualSqlCompletionProposalBase.replacementAfter);
    assertNull(actualSqlCompletionProposalBase.getObjectContainer());
    assertEquals(0, actualSqlCompletionProposalBase.getProposalScore());
    assertEquals(1, actualSqlCompletionProposalBase.getReplacementLength());
    assertEquals(1, actualSqlCompletionProposalBase.getReplacementOffset());
    assertEquals(1, actualSqlCompletionProposalBase.cursorPosition);
    assertEquals(450, actualSqlCompletionProposalBase.getProposalTypeSorterScore());
    assertEquals(DBPKeywordType.KEYWORD, actualSqlCompletionProposalBase.getProposalType());
    assertFalse(actualSqlCompletionProposalBase.hasStructObject());
    assertSame(image, actualSqlCompletionProposalBase.getObjectImage());
    assertSame(object, actualSqlCompletionProposalBase.getObject());
    assertSame(request, actualSqlCompletionProposalBase.getRequest());
  }
}
