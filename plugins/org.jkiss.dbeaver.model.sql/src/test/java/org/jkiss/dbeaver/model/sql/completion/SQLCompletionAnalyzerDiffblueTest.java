package org.jkiss.dbeaver.model.sql.completion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
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
import org.eclipse.jface.text.Document;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPIdentifierCase;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.DBPKeywordType;
import org.jkiss.dbeaver.model.DBPNamedObject;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.SQLQuery;
import org.jkiss.dbeaver.model.sql.SQLSyntaxManager;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLCompletionAnalyzerDiffblueTest {
  /**
   * Test {@link SQLCompletionAnalyzer#createCompletionProposal(SQLCompletionRequest, String,
   * String, boolean, DBPKeywordType, DBPImage, boolean, DBPNamedObject, Map)} with {@code request},
   * {@code replaceString}, {@code displayString}, {@code isFQName}, {@code proposalType}, {@code
   * image}, {@code isObject}, {@code object}, {@code params}.
   *
   * <p>Method under test: {@link
   * SQLCompletionAnalyzer#createCompletionProposal(SQLCompletionRequest, String, String, boolean,
   * DBPKeywordType, DBPImage, boolean, DBPNamedObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLCompletionProposalBase SQLCompletionAnalyzer.createCompletionProposal(SQLCompletionRequest, String, String, boolean, DBPKeywordType, DBPImage, boolean, DBPNamedObject, Map)"
  })
  public void
      testCreateCompletionProposalWithRequestReplaceStringDisplayStringIsFQNameProposalTypeImageIsObjectObjectParams() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStructSeparator()).thenReturn('A');
    when(sqlSyntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager sqlSyntaxManager2 = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager2.getStructSeparator()).thenReturn('a');
    when(sqlSyntaxManager2.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlSyntaxManager2.getDialect()).thenReturn(sqlDialect2);

    SQLDialect sqlDialect3 = mock(SQLDialect.class);
    when(sqlDialect3.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect3);

    SQLCompletionContext context = mock(SQLCompletionContext.class);
    when(context.isUseFQNames()).thenReturn(true);
    when(context.getDataSource()).thenReturn(dbpDataSource);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager2);
    Document document = new Document();
    SQLQuery activeQuery = new SQLQuery(mock(DBPDataSource.class), "Text");

    SQLCompletionRequest request =
        new SQLCompletionRequest(context, document, 1, activeQuery, true);
    DBPImage image = mock(DBPImage.class);
    DBPNamedObject object = mock(DBPNamedObject.class);

    SQLCompletionProposalBase sqlCompletionProposalBase =
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

    SQLCompletionContext context2 = mock(SQLCompletionContext.class);
    when(context2.getDataSource()).thenReturn(null);
    when(context2.createProposal(
            Mockito.<SQLCompletionRequest>any(),
            Mockito.<String>any(),
            Mockito.<String>any(),
            anyInt(),
            Mockito.<DBPImage>any(),
            Mockito.<DBPKeywordType>any(),
            Mockito.<String>any(),
            Mockito.<DBPNamedObject>any(),
            Mockito.<Map<String, Object>>any()))
        .thenReturn(sqlCompletionProposalBase);
    when(context2.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    Document document2 = new Document();
    SQLQuery activeQuery2 = new SQLQuery(mock(DBPDataSource.class), "Text");

    SQLCompletionRequest request2 =
        new SQLCompletionRequest(context2, document2, 1, activeQuery2, true);
    DBPImage image2 = mock(DBPImage.class);
    DBPNamedObject object2 = mock(DBPNamedObject.class);

    // Act
    SQLCompletionProposalBase actualCreateCompletionProposalResult =
        SQLCompletionAnalyzer.createCompletionProposal(
            request2,
            "Replace String",
            "Display String",
            true,
            DBPKeywordType.KEYWORD,
            image2,
            true,
            object2,
            new HashMap<>());

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(sqlDialect2).getStringQuoteStrings();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect3).getUnquotedIdentifier("replacement string");
    verify(sqlSyntaxManager2).getDialect();
    verify(sqlSyntaxManager).getDialect();
    verify(sqlSyntaxManager2).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(sqlSyntaxManager2, atLeast(1)).getStructSeparator();
    verify(context2)
        .createProposal(
            isA(SQLCompletionRequest.class),
            eq("Display String"),
            eq("Replace String"),
            eq(14),
            isA(DBPImage.class),
            eq(DBPKeywordType.KEYWORD),
            isNull(),
            isA(DBPNamedObject.class),
            isA(Map.class));
    verify(context2).getDataSource();
    verify(context, atLeast(1)).getDataSource();
    verify(context2).getSyntaxManager();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(context).isUseFQNames();
    assertEquals("42", actualCreateCompletionProposalResult.replacementFull);
    assertEquals("Display String", actualCreateCompletionProposalResult.getDisplayString());
    assertEquals("Display String", actualCreateCompletionProposalResult.toString());
    assertEquals("Replacement String", actualCreateCompletionProposalResult.getReplacementString());
    assertNull(actualCreateCompletionProposalResult.getExtraString());
    assertNull(actualCreateCompletionProposalResult.replacementAfter);
    assertNull(actualCreateCompletionProposalResult.getObjectContainer());
    assertEquals(0, actualCreateCompletionProposalResult.getProposalScore());
    assertEquals(0, actualCreateCompletionProposalResult.getReplacementOffset());
    assertEquals(1, actualCreateCompletionProposalResult.getReplacementLength());
    assertEquals(1, actualCreateCompletionProposalResult.cursorPosition);
    assertEquals(450, actualCreateCompletionProposalResult.getProposalTypeSorterScore());
    assertEquals(DBPKeywordType.KEYWORD, actualCreateCompletionProposalResult.getProposalType());
    assertFalse(actualCreateCompletionProposalResult.hasStructObject());
    assertSame(request, actualCreateCompletionProposalResult.getRequest());
  }

  /**
   * Test {@link SQLCompletionAnalyzer#createCompletionProposal(SQLCompletionRequest, String,
   * String, boolean, DBPKeywordType, DBPImage, boolean, DBPNamedObject, Map)} with {@code request},
   * {@code replaceString}, {@code displayString}, {@code isFQName}, {@code proposalType}, {@code
   * image}, {@code isObject}, {@code object}, {@code params}.
   *
   * <p>Method under test: {@link
   * SQLCompletionAnalyzer#createCompletionProposal(SQLCompletionRequest, String, String, boolean,
   * DBPKeywordType, DBPImage, boolean, DBPNamedObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLCompletionProposalBase SQLCompletionAnalyzer.createCompletionProposal(SQLCompletionRequest, String, String, boolean, DBPKeywordType, DBPImage, boolean, DBPNamedObject, Map)"
  })
  public void
      testCreateCompletionProposalWithRequestReplaceStringDisplayStringIsFQNameProposalTypeImageIsObjectObjectParams2() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStructSeparator()).thenReturn('A');
    when(sqlSyntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {null}});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager sqlSyntaxManager2 = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager2.getStructSeparator()).thenReturn('a');
    when(sqlSyntaxManager2.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlSyntaxManager2.getDialect()).thenReturn(sqlDialect2);

    SQLDialect sqlDialect3 = mock(SQLDialect.class);
    when(sqlDialect3.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect3);

    SQLCompletionContext context = mock(SQLCompletionContext.class);
    when(context.isUseFQNames()).thenReturn(true);
    when(context.getDataSource()).thenReturn(dbpDataSource);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager2);
    Document document = new Document();
    SQLQuery activeQuery = new SQLQuery(mock(DBPDataSource.class), "Text");

    SQLCompletionRequest request =
        new SQLCompletionRequest(context, document, 1, activeQuery, true);
    DBPImage image = mock(DBPImage.class);
    DBPNamedObject object = mock(DBPNamedObject.class);

    SQLCompletionProposalBase sqlCompletionProposalBase =
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

    SQLCompletionContext context2 = mock(SQLCompletionContext.class);
    when(context2.getDataSource()).thenReturn(null);
    when(context2.createProposal(
            Mockito.<SQLCompletionRequest>any(),
            Mockito.<String>any(),
            Mockito.<String>any(),
            anyInt(),
            Mockito.<DBPImage>any(),
            Mockito.<DBPKeywordType>any(),
            Mockito.<String>any(),
            Mockito.<DBPNamedObject>any(),
            Mockito.<Map<String, Object>>any()))
        .thenReturn(sqlCompletionProposalBase);
    when(context2.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    Document document2 = new Document();
    SQLQuery activeQuery2 = new SQLQuery(mock(DBPDataSource.class), "Text");

    SQLCompletionRequest request2 =
        new SQLCompletionRequest(context2, document2, 1, activeQuery2, true);
    DBPImage image2 = mock(DBPImage.class);
    DBPNamedObject object2 = mock(DBPNamedObject.class);

    // Act
    SQLCompletionProposalBase actualCreateCompletionProposalResult =
        SQLCompletionAnalyzer.createCompletionProposal(
            request2,
            "Replace String",
            "Display String",
            true,
            DBPKeywordType.KEYWORD,
            image2,
            true,
            object2,
            new HashMap<>());

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(sqlDialect2).getStringQuoteStrings();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect3).getUnquotedIdentifier("replacement string");
    verify(sqlSyntaxManager2).getDialect();
    verify(sqlSyntaxManager).getDialect();
    verify(sqlSyntaxManager2).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(sqlSyntaxManager2, atLeast(1)).getStructSeparator();
    verify(context2)
        .createProposal(
            isA(SQLCompletionRequest.class),
            eq("Display String"),
            eq("Replace String"),
            eq(14),
            isA(DBPImage.class),
            eq(DBPKeywordType.KEYWORD),
            isNull(),
            isA(DBPNamedObject.class),
            isA(Map.class));
    verify(context2).getDataSource();
    verify(context, atLeast(1)).getDataSource();
    verify(context2).getSyntaxManager();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(context).isUseFQNames();
    assertEquals("42", actualCreateCompletionProposalResult.replacementFull);
    assertEquals("Display String", actualCreateCompletionProposalResult.getDisplayString());
    assertEquals("Display String", actualCreateCompletionProposalResult.toString());
    assertEquals("Replacement String", actualCreateCompletionProposalResult.getReplacementString());
    assertNull(actualCreateCompletionProposalResult.getExtraString());
    assertNull(actualCreateCompletionProposalResult.replacementAfter);
    assertNull(actualCreateCompletionProposalResult.getObjectContainer());
    assertEquals(0, actualCreateCompletionProposalResult.getProposalScore());
    assertEquals(0, actualCreateCompletionProposalResult.getReplacementOffset());
    assertEquals(1, actualCreateCompletionProposalResult.getReplacementLength());
    assertEquals(1, actualCreateCompletionProposalResult.cursorPosition);
    assertEquals(450, actualCreateCompletionProposalResult.getProposalTypeSorterScore());
    assertEquals(DBPKeywordType.KEYWORD, actualCreateCompletionProposalResult.getProposalType());
    assertFalse(actualCreateCompletionProposalResult.hasStructObject());
    assertSame(request, actualCreateCompletionProposalResult.getRequest());
  }

  /**
   * Test {@link SQLCompletionAnalyzer#createCompletionProposal(SQLCompletionRequest, String,
   * String, boolean, DBPKeywordType, DBPImage, boolean, DBPNamedObject, Map)} with {@code request},
   * {@code replaceString}, {@code displayString}, {@code isFQName}, {@code proposalType}, {@code
   * image}, {@code isObject}, {@code object}, {@code params}.
   *
   * <p>Method under test: {@link
   * SQLCompletionAnalyzer#createCompletionProposal(SQLCompletionRequest, String, String, boolean,
   * DBPKeywordType, DBPImage, boolean, DBPNamedObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLCompletionProposalBase SQLCompletionAnalyzer.createCompletionProposal(SQLCompletionRequest, String, String, boolean, DBPKeywordType, DBPImage, boolean, DBPNamedObject, Map)"
  })
  public void
      testCreateCompletionProposalWithRequestReplaceStringDisplayStringIsFQNameProposalTypeImageIsObjectObjectParams3() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStructSeparator()).thenReturn('A');
    when(sqlSyntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {""}});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager sqlSyntaxManager2 = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager2.getStructSeparator()).thenReturn('a');
    when(sqlSyntaxManager2.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlSyntaxManager2.getDialect()).thenReturn(sqlDialect2);

    SQLDialect sqlDialect3 = mock(SQLDialect.class);
    when(sqlDialect3.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect3);

    SQLCompletionContext context = mock(SQLCompletionContext.class);
    when(context.isUseFQNames()).thenReturn(true);
    when(context.getDataSource()).thenReturn(dbpDataSource);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager2);
    Document document = new Document();
    SQLQuery activeQuery = new SQLQuery(mock(DBPDataSource.class), "Text");

    SQLCompletionRequest request =
        new SQLCompletionRequest(context, document, 1, activeQuery, true);
    DBPImage image = mock(DBPImage.class);
    DBPNamedObject object = mock(DBPNamedObject.class);

    SQLCompletionProposalBase sqlCompletionProposalBase =
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

    SQLCompletionContext context2 = mock(SQLCompletionContext.class);
    when(context2.getDataSource()).thenReturn(null);
    when(context2.createProposal(
            Mockito.<SQLCompletionRequest>any(),
            Mockito.<String>any(),
            Mockito.<String>any(),
            anyInt(),
            Mockito.<DBPImage>any(),
            Mockito.<DBPKeywordType>any(),
            Mockito.<String>any(),
            Mockito.<DBPNamedObject>any(),
            Mockito.<Map<String, Object>>any()))
        .thenReturn(sqlCompletionProposalBase);
    when(context2.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    Document document2 = new Document();
    SQLQuery activeQuery2 = new SQLQuery(mock(DBPDataSource.class), "Text");

    SQLCompletionRequest request2 =
        new SQLCompletionRequest(context2, document2, 1, activeQuery2, true);
    DBPImage image2 = mock(DBPImage.class);
    DBPNamedObject object2 = mock(DBPNamedObject.class);

    // Act
    SQLCompletionProposalBase actualCreateCompletionProposalResult =
        SQLCompletionAnalyzer.createCompletionProposal(
            request2,
            "Replace String",
            "Display String",
            true,
            DBPKeywordType.KEYWORD,
            image2,
            true,
            object2,
            new HashMap<>());

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(sqlDialect2).getStringQuoteStrings();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect3).getUnquotedIdentifier("replacement string");
    verify(sqlSyntaxManager2).getDialect();
    verify(sqlSyntaxManager).getDialect();
    verify(sqlSyntaxManager2).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(sqlSyntaxManager2, atLeast(1)).getStructSeparator();
    verify(context2)
        .createProposal(
            isA(SQLCompletionRequest.class),
            eq("Display String"),
            eq("Replace String"),
            eq(14),
            isA(DBPImage.class),
            eq(DBPKeywordType.KEYWORD),
            isNull(),
            isA(DBPNamedObject.class),
            isA(Map.class));
    verify(context2).getDataSource();
    verify(context, atLeast(1)).getDataSource();
    verify(context2).getSyntaxManager();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(context).isUseFQNames();
    assertEquals("42", actualCreateCompletionProposalResult.replacementFull);
    assertEquals("Display String", actualCreateCompletionProposalResult.getDisplayString());
    assertEquals("Display String", actualCreateCompletionProposalResult.toString());
    assertEquals("Replacement String", actualCreateCompletionProposalResult.getReplacementString());
    assertNull(actualCreateCompletionProposalResult.getExtraString());
    assertNull(actualCreateCompletionProposalResult.replacementAfter);
    assertNull(actualCreateCompletionProposalResult.getObjectContainer());
    assertEquals(0, actualCreateCompletionProposalResult.getProposalScore());
    assertEquals(0, actualCreateCompletionProposalResult.getReplacementOffset());
    assertEquals(1, actualCreateCompletionProposalResult.getReplacementLength());
    assertEquals(1, actualCreateCompletionProposalResult.cursorPosition);
    assertEquals(450, actualCreateCompletionProposalResult.getProposalTypeSorterScore());
    assertEquals(DBPKeywordType.KEYWORD, actualCreateCompletionProposalResult.getProposalType());
    assertFalse(actualCreateCompletionProposalResult.hasStructObject());
    assertSame(request, actualCreateCompletionProposalResult.getRequest());
  }

  /**
   * Test {@link SQLCompletionAnalyzer#createCompletionProposal(SQLCompletionRequest, String,
   * String, boolean, DBPKeywordType, DBPImage, boolean, DBPNamedObject, Map)} with {@code request},
   * {@code replaceString}, {@code displayString}, {@code isFQName}, {@code proposalType}, {@code
   * image}, {@code isObject}, {@code object}, {@code params}.
   *
   * <p>Method under test: {@link
   * SQLCompletionAnalyzer#createCompletionProposal(SQLCompletionRequest, String, String, boolean,
   * DBPKeywordType, DBPImage, boolean, DBPNamedObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLCompletionProposalBase SQLCompletionAnalyzer.createCompletionProposal(SQLCompletionRequest, String, String, boolean, DBPKeywordType, DBPImage, boolean, DBPNamedObject, Map)"
  })
  public void
      testCreateCompletionProposalWithRequestReplaceStringDisplayStringIsFQNameProposalTypeImageIsObjectObjectParams4() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStructSeparator()).thenReturn('A');
    when(sqlSyntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager sqlSyntaxManager2 = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager2.getStructSeparator()).thenReturn('a');
    when(sqlSyntaxManager2.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlSyntaxManager2.getDialect()).thenReturn(sqlDialect2);

    SQLDialect sqlDialect3 = mock(SQLDialect.class);
    when(sqlDialect3.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect3);

    SQLCompletionContext context = mock(SQLCompletionContext.class);
    when(context.isUseFQNames()).thenReturn(true);
    when(context.getDataSource()).thenReturn(dbpDataSource);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager2);
    Document document = new Document();
    SQLQuery activeQuery = new SQLQuery(mock(DBPDataSource.class), "Text");

    SQLCompletionRequest request =
        new SQLCompletionRequest(context, document, 1, activeQuery, true);
    DBPImage image = mock(DBPImage.class);
    DBPNamedObject object = mock(DBPNamedObject.class);

    SQLCompletionProposalBase sqlCompletionProposalBase =
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

    SQLCompletionContext context2 = mock(SQLCompletionContext.class);
    when(context2.getInsertCase()).thenReturn(1);
    when(context2.getDataSource()).thenReturn(null);
    when(context2.createProposal(
            Mockito.<SQLCompletionRequest>any(),
            Mockito.<String>any(),
            Mockito.<String>any(),
            anyInt(),
            Mockito.<DBPImage>any(),
            Mockito.<DBPKeywordType>any(),
            Mockito.<String>any(),
            Mockito.<DBPNamedObject>any(),
            Mockito.<Map<String, Object>>any()))
        .thenReturn(sqlCompletionProposalBase);
    when(context2.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    Document document2 = new Document();
    SQLQuery activeQuery2 = new SQLQuery(mock(DBPDataSource.class), "Text");

    SQLCompletionRequest request2 =
        new SQLCompletionRequest(context2, document2, 1, activeQuery2, true);
    DBPImage image2 = mock(DBPImage.class);
    DBPNamedObject object2 = mock(DBPNamedObject.class);

    // Act
    SQLCompletionProposalBase actualCreateCompletionProposalResult =
        SQLCompletionAnalyzer.createCompletionProposal(
            request2,
            "Replace String",
            "Display String",
            false,
            DBPKeywordType.KEYWORD,
            image2,
            true,
            object2,
            new HashMap<>());

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(sqlDialect2).getStringQuoteStrings();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect3).getUnquotedIdentifier("replacement string");
    verify(sqlSyntaxManager2).getDialect();
    verify(sqlSyntaxManager).getDialect();
    verify(sqlSyntaxManager2).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(sqlSyntaxManager2, atLeast(1)).getStructSeparator();
    verify(context2)
        .createProposal(
            isA(SQLCompletionRequest.class),
            eq("Display String"),
            eq("REPLACE STRING"),
            eq(14),
            isA(DBPImage.class),
            eq(DBPKeywordType.KEYWORD),
            isNull(),
            isA(DBPNamedObject.class),
            isA(Map.class));
    verify(context2).getDataSource();
    verify(context, atLeast(1)).getDataSource();
    verify(context2).getInsertCase();
    verify(context2).getSyntaxManager();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(context).isUseFQNames();
    assertEquals("42", actualCreateCompletionProposalResult.replacementFull);
    assertEquals("Display String", actualCreateCompletionProposalResult.getDisplayString());
    assertEquals("Display String", actualCreateCompletionProposalResult.toString());
    assertEquals("Replacement String", actualCreateCompletionProposalResult.getReplacementString());
    assertNull(actualCreateCompletionProposalResult.getExtraString());
    assertNull(actualCreateCompletionProposalResult.replacementAfter);
    assertNull(actualCreateCompletionProposalResult.getObjectContainer());
    assertEquals(0, actualCreateCompletionProposalResult.getProposalScore());
    assertEquals(0, actualCreateCompletionProposalResult.getReplacementOffset());
    assertEquals(1, actualCreateCompletionProposalResult.getReplacementLength());
    assertEquals(1, actualCreateCompletionProposalResult.cursorPosition);
    assertEquals(450, actualCreateCompletionProposalResult.getProposalTypeSorterScore());
    assertEquals(DBPKeywordType.KEYWORD, actualCreateCompletionProposalResult.getProposalType());
    assertFalse(actualCreateCompletionProposalResult.hasStructObject());
    assertSame(request, actualCreateCompletionProposalResult.getRequest());
  }

  /**
   * Test {@link SQLCompletionAnalyzer#createCompletionProposal(SQLCompletionRequest, String,
   * String, boolean, DBPKeywordType, DBPImage, boolean, DBPNamedObject, Map)} with {@code request},
   * {@code replaceString}, {@code displayString}, {@code isFQName}, {@code proposalType}, {@code
   * image}, {@code isObject}, {@code object}, {@code params}.
   *
   * <p>Method under test: {@link
   * SQLCompletionAnalyzer#createCompletionProposal(SQLCompletionRequest, String, String, boolean,
   * DBPKeywordType, DBPImage, boolean, DBPNamedObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLCompletionProposalBase SQLCompletionAnalyzer.createCompletionProposal(SQLCompletionRequest, String, String, boolean, DBPKeywordType, DBPImage, boolean, DBPNamedObject, Map)"
  })
  public void
      testCreateCompletionProposalWithRequestReplaceStringDisplayStringIsFQNameProposalTypeImageIsObjectObjectParams5() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStructSeparator()).thenReturn('A');
    when(sqlSyntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager sqlSyntaxManager2 = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager2.getStructSeparator()).thenReturn('a');
    when(sqlSyntaxManager2.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlSyntaxManager2.getDialect()).thenReturn(sqlDialect2);

    SQLDialect sqlDialect3 = mock(SQLDialect.class);
    when(sqlDialect3.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect3);

    SQLCompletionContext context = mock(SQLCompletionContext.class);
    when(context.isUseFQNames()).thenReturn(true);
    when(context.getDataSource()).thenReturn(dbpDataSource);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager2);
    Document document = new Document();
    SQLQuery activeQuery = new SQLQuery(mock(DBPDataSource.class), "Text");

    SQLCompletionRequest request =
        new SQLCompletionRequest(context, document, 1, activeQuery, true);
    DBPImage image = mock(DBPImage.class);
    DBPNamedObject object = mock(DBPNamedObject.class);

    SQLCompletionProposalBase sqlCompletionProposalBase =
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

    SQLCompletionContext context2 = mock(SQLCompletionContext.class);
    when(context2.getInsertCase()).thenReturn(2);
    when(context2.getDataSource()).thenReturn(null);
    when(context2.createProposal(
            Mockito.<SQLCompletionRequest>any(),
            Mockito.<String>any(),
            Mockito.<String>any(),
            anyInt(),
            Mockito.<DBPImage>any(),
            Mockito.<DBPKeywordType>any(),
            Mockito.<String>any(),
            Mockito.<DBPNamedObject>any(),
            Mockito.<Map<String, Object>>any()))
        .thenReturn(sqlCompletionProposalBase);
    when(context2.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    Document document2 = new Document();
    SQLQuery activeQuery2 = new SQLQuery(mock(DBPDataSource.class), "Text");

    SQLCompletionRequest request2 =
        new SQLCompletionRequest(context2, document2, 1, activeQuery2, true);
    DBPImage image2 = mock(DBPImage.class);
    DBPNamedObject object2 = mock(DBPNamedObject.class);

    // Act
    SQLCompletionProposalBase actualCreateCompletionProposalResult =
        SQLCompletionAnalyzer.createCompletionProposal(
            request2,
            "Replace String",
            "Display String",
            false,
            DBPKeywordType.KEYWORD,
            image2,
            true,
            object2,
            new HashMap<>());

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(sqlDialect2).getStringQuoteStrings();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect3).getUnquotedIdentifier("replacement string");
    verify(sqlSyntaxManager2).getDialect();
    verify(sqlSyntaxManager).getDialect();
    verify(sqlSyntaxManager2).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(sqlSyntaxManager2, atLeast(1)).getStructSeparator();
    verify(context2)
        .createProposal(
            isA(SQLCompletionRequest.class),
            eq("Display String"),
            eq("replace string"),
            eq(14),
            isA(DBPImage.class),
            eq(DBPKeywordType.KEYWORD),
            isNull(),
            isA(DBPNamedObject.class),
            isA(Map.class));
    verify(context2).getDataSource();
    verify(context, atLeast(1)).getDataSource();
    verify(context2).getInsertCase();
    verify(context2).getSyntaxManager();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(context).isUseFQNames();
    assertEquals("42", actualCreateCompletionProposalResult.replacementFull);
    assertEquals("Display String", actualCreateCompletionProposalResult.getDisplayString());
    assertEquals("Display String", actualCreateCompletionProposalResult.toString());
    assertEquals("Replacement String", actualCreateCompletionProposalResult.getReplacementString());
    assertNull(actualCreateCompletionProposalResult.getExtraString());
    assertNull(actualCreateCompletionProposalResult.replacementAfter);
    assertNull(actualCreateCompletionProposalResult.getObjectContainer());
    assertEquals(0, actualCreateCompletionProposalResult.getProposalScore());
    assertEquals(0, actualCreateCompletionProposalResult.getReplacementOffset());
    assertEquals(1, actualCreateCompletionProposalResult.getReplacementLength());
    assertEquals(1, actualCreateCompletionProposalResult.cursorPosition);
    assertEquals(450, actualCreateCompletionProposalResult.getProposalTypeSorterScore());
    assertEquals(DBPKeywordType.KEYWORD, actualCreateCompletionProposalResult.getProposalType());
    assertFalse(actualCreateCompletionProposalResult.hasStructObject());
    assertSame(request, actualCreateCompletionProposalResult.getRequest());
  }

  /**
   * Test {@link SQLCompletionAnalyzer#createCompletionProposal(SQLCompletionRequest, String,
   * String, boolean, DBPKeywordType, DBPImage, boolean, DBPNamedObject, Map)} with {@code request},
   * {@code replaceString}, {@code displayString}, {@code isFQName}, {@code proposalType}, {@code
   * image}, {@code isObject}, {@code object}, {@code params}.
   *
   * <p>Method under test: {@link
   * SQLCompletionAnalyzer#createCompletionProposal(SQLCompletionRequest, String, String, boolean,
   * DBPKeywordType, DBPImage, boolean, DBPNamedObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLCompletionProposalBase SQLCompletionAnalyzer.createCompletionProposal(SQLCompletionRequest, String, String, boolean, DBPKeywordType, DBPImage, boolean, DBPNamedObject, Map)"
  })
  public void
      testCreateCompletionProposalWithRequestReplaceStringDisplayStringIsFQNameProposalTypeImageIsObjectObjectParams6() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStructSeparator()).thenReturn('A');
    when(sqlSyntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager sqlSyntaxManager2 = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager2.getStructSeparator()).thenReturn('a');
    when(sqlSyntaxManager2.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlSyntaxManager2.getDialect()).thenReturn(sqlDialect2);

    SQLDialect sqlDialect3 = mock(SQLDialect.class);
    when(sqlDialect3.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect3);

    SQLCompletionContext context = mock(SQLCompletionContext.class);
    when(context.isUseFQNames()).thenReturn(true);
    when(context.getDataSource()).thenReturn(dbpDataSource);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager2);
    Document document = new Document();
    SQLQuery activeQuery = new SQLQuery(mock(DBPDataSource.class), "Text");

    SQLCompletionRequest request =
        new SQLCompletionRequest(context, document, 1, activeQuery, true);
    DBPImage image = mock(DBPImage.class);
    DBPNamedObject object = mock(DBPNamedObject.class);

    SQLCompletionProposalBase sqlCompletionProposalBase =
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

    SQLCompletionContext context2 = mock(SQLCompletionContext.class);
    when(context2.getInsertCase()).thenReturn(9);
    when(context2.getDataSource()).thenReturn(null);
    when(context2.createProposal(
            Mockito.<SQLCompletionRequest>any(),
            Mockito.<String>any(),
            Mockito.<String>any(),
            anyInt(),
            Mockito.<DBPImage>any(),
            Mockito.<DBPKeywordType>any(),
            Mockito.<String>any(),
            Mockito.<DBPNamedObject>any(),
            Mockito.<Map<String, Object>>any()))
        .thenReturn(sqlCompletionProposalBase);
    when(context2.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    Document document2 = new Document();
    SQLQuery activeQuery2 = new SQLQuery(mock(DBPDataSource.class), "Text");

    SQLCompletionRequest request2 =
        new SQLCompletionRequest(context2, document2, 1, activeQuery2, true);
    DBPImage image2 = mock(DBPImage.class);
    DBPNamedObject object2 = mock(DBPNamedObject.class);

    // Act
    SQLCompletionProposalBase actualCreateCompletionProposalResult =
        SQLCompletionAnalyzer.createCompletionProposal(
            request2,
            "Replace String",
            "Display String",
            false,
            DBPKeywordType.KEYWORD,
            image2,
            true,
            object2,
            new HashMap<>());

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(sqlDialect2).getStringQuoteStrings();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect3).getUnquotedIdentifier("replacement string");
    verify(sqlSyntaxManager2).getDialect();
    verify(sqlSyntaxManager).getDialect();
    verify(sqlSyntaxManager2).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(sqlSyntaxManager2, atLeast(1)).getStructSeparator();
    verify(context2)
        .createProposal(
            isA(SQLCompletionRequest.class),
            eq("Display String"),
            eq("Replace String"),
            eq(14),
            isA(DBPImage.class),
            eq(DBPKeywordType.KEYWORD),
            isNull(),
            isA(DBPNamedObject.class),
            isA(Map.class));
    verify(context2).getDataSource();
    verify(context, atLeast(1)).getDataSource();
    verify(context2).getInsertCase();
    verify(context2).getSyntaxManager();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(context).isUseFQNames();
    assertEquals("42", actualCreateCompletionProposalResult.replacementFull);
    assertEquals("Display String", actualCreateCompletionProposalResult.getDisplayString());
    assertEquals("Display String", actualCreateCompletionProposalResult.toString());
    assertEquals("Replacement String", actualCreateCompletionProposalResult.getReplacementString());
    assertNull(actualCreateCompletionProposalResult.getExtraString());
    assertNull(actualCreateCompletionProposalResult.replacementAfter);
    assertNull(actualCreateCompletionProposalResult.getObjectContainer());
    assertEquals(0, actualCreateCompletionProposalResult.getProposalScore());
    assertEquals(0, actualCreateCompletionProposalResult.getReplacementOffset());
    assertEquals(1, actualCreateCompletionProposalResult.getReplacementLength());
    assertEquals(1, actualCreateCompletionProposalResult.cursorPosition);
    assertEquals(450, actualCreateCompletionProposalResult.getProposalTypeSorterScore());
    assertEquals(DBPKeywordType.KEYWORD, actualCreateCompletionProposalResult.getProposalType());
    assertFalse(actualCreateCompletionProposalResult.hasStructObject());
    assertSame(request, actualCreateCompletionProposalResult.getRequest());
  }

  /**
   * Test {@link SQLCompletionAnalyzer#createCompletionProposal(SQLCompletionRequest, String,
   * String, boolean, DBPKeywordType, DBPImage, boolean, DBPNamedObject, Map)} with {@code request},
   * {@code replaceString}, {@code displayString}, {@code isFQName}, {@code proposalType}, {@code
   * image}, {@code isObject}, {@code object}, {@code params}.
   *
   * <p>Method under test: {@link
   * SQLCompletionAnalyzer#createCompletionProposal(SQLCompletionRequest, String, String, boolean,
   * DBPKeywordType, DBPImage, boolean, DBPNamedObject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLCompletionProposalBase SQLCompletionAnalyzer.createCompletionProposal(SQLCompletionRequest, String, String, boolean, DBPKeywordType, DBPImage, boolean, DBPNamedObject, Map)"
  })
  public void
      testCreateCompletionProposalWithRequestReplaceStringDisplayStringIsFQNameProposalTypeImageIsObjectObjectParams7() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStructSeparator()).thenReturn('A');
    when(sqlSyntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager sqlSyntaxManager2 = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager2.getStructSeparator()).thenReturn('a');
    when(sqlSyntaxManager2.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlSyntaxManager2.getDialect()).thenReturn(sqlDialect2);

    SQLDialect sqlDialect3 = mock(SQLDialect.class);
    when(sqlDialect3.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect3);

    SQLCompletionContext context = mock(SQLCompletionContext.class);
    when(context.isUseFQNames()).thenReturn(true);
    when(context.getDataSource()).thenReturn(dbpDataSource);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager2);
    Document document = new Document();
    SQLQuery activeQuery = new SQLQuery(mock(DBPDataSource.class), "Text");

    SQLCompletionRequest request =
        new SQLCompletionRequest(context, document, 1, activeQuery, true);
    DBPImage image = mock(DBPImage.class);
    DBPNamedObject object = mock(DBPNamedObject.class);

    SQLCompletionProposalBase sqlCompletionProposalBase =
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

    SQLCompletionContext context2 = mock(SQLCompletionContext.class);
    when(context2.getInsertCase()).thenReturn(1);
    when(context2.getDataSource()).thenReturn(null);
    when(context2.createProposal(
            Mockito.<SQLCompletionRequest>any(),
            Mockito.<String>any(),
            Mockito.<String>any(),
            anyInt(),
            Mockito.<DBPImage>any(),
            Mockito.<DBPKeywordType>any(),
            Mockito.<String>any(),
            Mockito.<DBPNamedObject>any(),
            Mockito.<Map<String, Object>>any()))
        .thenReturn(sqlCompletionProposalBase);
    when(context2.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    Document document2 = new Document();
    SQLQuery activeQuery2 = new SQLQuery(mock(DBPDataSource.class), "Text");

    SQLCompletionRequest request2 =
        new SQLCompletionRequest(context2, document2, 1, activeQuery2, true);
    DBPImage image2 = mock(DBPImage.class);
    DBPNamedObject object2 = mock(DBPNamedObject.class);

    // Act
    SQLCompletionProposalBase actualCreateCompletionProposalResult =
        SQLCompletionAnalyzer.createCompletionProposal(
            request2,
            "Replace String",
            "Display String",
            false,
            DBPKeywordType.FUNCTION,
            image2,
            true,
            object2,
            new HashMap<>());

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(sqlDialect2).getStringQuoteStrings();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect3).getUnquotedIdentifier("replacement string");
    verify(sqlSyntaxManager2).getDialect();
    verify(sqlSyntaxManager).getDialect();
    verify(sqlSyntaxManager2).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(sqlSyntaxManager2, atLeast(1)).getStructSeparator();
    verify(context2)
        .createProposal(
            isA(SQLCompletionRequest.class),
            eq("Display String"),
            eq("REPLACE STRING()"),
            eq(14),
            isA(DBPImage.class),
            eq(DBPKeywordType.FUNCTION),
            isNull(),
            isA(DBPNamedObject.class),
            isA(Map.class));
    verify(context2).getDataSource();
    verify(context, atLeast(1)).getDataSource();
    verify(context2).getInsertCase();
    verify(context2).getSyntaxManager();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(context).isUseFQNames();
    assertEquals("42", actualCreateCompletionProposalResult.replacementFull);
    assertEquals("Display String", actualCreateCompletionProposalResult.getDisplayString());
    assertEquals("Display String", actualCreateCompletionProposalResult.toString());
    assertEquals("Replacement String", actualCreateCompletionProposalResult.getReplacementString());
    assertNull(actualCreateCompletionProposalResult.getExtraString());
    assertNull(actualCreateCompletionProposalResult.replacementAfter);
    assertNull(actualCreateCompletionProposalResult.getObjectContainer());
    assertEquals(0, actualCreateCompletionProposalResult.getProposalScore());
    assertEquals(0, actualCreateCompletionProposalResult.getReplacementOffset());
    assertEquals(1, actualCreateCompletionProposalResult.getReplacementLength());
    assertEquals(1, actualCreateCompletionProposalResult.cursorPosition);
    assertEquals(450, actualCreateCompletionProposalResult.getProposalTypeSorterScore());
    assertEquals(DBPKeywordType.KEYWORD, actualCreateCompletionProposalResult.getProposalType());
    assertFalse(actualCreateCompletionProposalResult.hasStructObject());
    assertSame(request, actualCreateCompletionProposalResult.getRequest());
  }

  /**
   * Test {@link SQLCompletionAnalyzer#createCompletionProposal(SQLCompletionRequest, String,
   * String, DBPKeywordType, String)} with {@code request}, {@code replaceString}, {@code
   * displayString}, {@code proposalType}, {@code description}.
   *
   * <p>Method under test: {@link
   * SQLCompletionAnalyzer#createCompletionProposal(SQLCompletionRequest, String, String,
   * DBPKeywordType, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLCompletionProposalBase SQLCompletionAnalyzer.createCompletionProposal(SQLCompletionRequest, String, String, DBPKeywordType, String)"
  })
  public void
      testCreateCompletionProposalWithRequestReplaceStringDisplayStringProposalTypeDescription() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStructSeparator()).thenReturn('A');
    when(sqlSyntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    SQLSyntaxManager sqlSyntaxManager2 = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager2.getStructSeparator()).thenReturn('a');
    when(sqlSyntaxManager2.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlSyntaxManager2.getDialect()).thenReturn(sqlDialect2);

    SQLDialect sqlDialect3 = mock(SQLDialect.class);
    when(sqlDialect3.getUnquotedIdentifier(Mockito.<String>any())).thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect3);

    SQLCompletionContext context = mock(SQLCompletionContext.class);
    when(context.isUseFQNames()).thenReturn(true);
    when(context.getDataSource()).thenReturn(dbpDataSource);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager2);
    Document document = new Document();
    SQLQuery activeQuery = new SQLQuery(mock(DBPDataSource.class), "Text");

    SQLCompletionRequest request =
        new SQLCompletionRequest(context, document, 1, activeQuery, true);
    DBPImage image = mock(DBPImage.class);
    DBPNamedObject object = mock(DBPNamedObject.class);

    SQLCompletionProposalBase sqlCompletionProposalBase =
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

    SQLCompletionContext context2 = mock(SQLCompletionContext.class);
    when(context2.createProposal(
            Mockito.<SQLCompletionRequest>any(),
            Mockito.<String>any(),
            Mockito.<String>any(),
            anyInt(),
            Mockito.<DBPImage>any(),
            Mockito.<DBPKeywordType>any(),
            Mockito.<String>any(),
            Mockito.<DBPNamedObject>any(),
            Mockito.<Map<String, Object>>any()))
        .thenReturn(sqlCompletionProposalBase);
    when(context2.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    Document document2 = new Document();
    SQLQuery activeQuery2 = new SQLQuery(mock(DBPDataSource.class), "Text");

    SQLCompletionRequest request2 =
        new SQLCompletionRequest(context2, document2, 1, activeQuery2, true);

    // Act
    SQLCompletionProposalBase actualCreateCompletionProposalResult =
        SQLCompletionAnalyzer.createCompletionProposal(
            request2,
            "Replace String",
            "Display String",
            DBPKeywordType.KEYWORD,
            "The characteristics of someone or something");

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(sqlDialect2).getStringQuoteStrings();
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlDialect3).getUnquotedIdentifier("replacement string");
    verify(sqlSyntaxManager2).getDialect();
    verify(sqlSyntaxManager).getDialect();
    verify(sqlSyntaxManager2).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(sqlSyntaxManager2, atLeast(1)).getStructSeparator();
    verify(context2)
        .createProposal(
            isA(SQLCompletionRequest.class),
            eq("Display String"),
            eq("Replace String"),
            eq(14),
            isNull(),
            eq(DBPKeywordType.KEYWORD),
            eq("The characteristics of someone or something"),
            isNull(),
            isA(Map.class));
    verify(context, atLeast(1)).getDataSource();
    verify(context2).getSyntaxManager();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(context).isUseFQNames();
    assertEquals("42", actualCreateCompletionProposalResult.replacementFull);
    assertEquals("Display String", actualCreateCompletionProposalResult.getDisplayString());
    assertEquals("Display String", actualCreateCompletionProposalResult.toString());
    assertEquals("Replacement String", actualCreateCompletionProposalResult.getReplacementString());
    assertNull(actualCreateCompletionProposalResult.getExtraString());
    assertNull(actualCreateCompletionProposalResult.replacementAfter);
    assertNull(actualCreateCompletionProposalResult.getObjectContainer());
    assertEquals(0, actualCreateCompletionProposalResult.getProposalScore());
    assertEquals(0, actualCreateCompletionProposalResult.getReplacementOffset());
    assertEquals(1, actualCreateCompletionProposalResult.getReplacementLength());
    assertEquals(1, actualCreateCompletionProposalResult.cursorPosition);
    assertEquals(450, actualCreateCompletionProposalResult.getProposalTypeSorterScore());
    assertEquals(DBPKeywordType.KEYWORD, actualCreateCompletionProposalResult.getProposalType());
    assertFalse(actualCreateCompletionProposalResult.hasStructObject());
    assertSame(request, actualCreateCompletionProposalResult.getRequest());
  }

  /**
   * Test {@link SQLCompletionAnalyzer#convertKeywordCase(SQLCompletionRequest, String, boolean)}.
   *
   * <ul>
   *   <li>Given {@link SQLCompletionContext} {@link SQLCompletionContext#getInsertCase()} return
   *       two.
   * </ul>
   *
   * <p>Method under test: {@link SQLCompletionAnalyzer#convertKeywordCase(SQLCompletionRequest,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLCompletionAnalyzer.convertKeywordCase(SQLCompletionRequest, String, boolean)"
  })
  public void testConvertKeywordCase_givenSQLCompletionContextGetInsertCaseReturnTwo() {
    // Arrange
    SQLCompletionContext sqlCompletionContext = mock(SQLCompletionContext.class);
    when(sqlCompletionContext.getInsertCase()).thenReturn(2);

    SQLCompletionRequest request = mock(SQLCompletionRequest.class);
    when(request.getContext()).thenReturn(sqlCompletionContext);

    // Act
    String actualConvertKeywordCaseResult =
        SQLCompletionAnalyzer.convertKeywordCase(request, "Replace String", true);

    // Assert
    verify(sqlCompletionContext).getInsertCase();
    verify(request).getContext();
    assertEquals("replace string", actualConvertKeywordCaseResult);
  }

  /**
   * Test {@link SQLCompletionAnalyzer#convertKeywordCase(SQLCompletionRequest, String, boolean)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#storesUnquotedCase()} return {@code LOWER}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCompletionAnalyzer#convertKeywordCase(SQLCompletionRequest,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLCompletionAnalyzer.convertKeywordCase(SQLCompletionRequest, String, boolean)"
  })
  public void testConvertKeywordCase_givenSQLDialectStoresUnquotedCaseReturnLower() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getKeywordType(Mockito.<String>any())).thenReturn(DBPKeywordType.FUNCTION);
    when(sqlDialect.storesUnquotedCase()).thenReturn(DBPIdentifierCase.LOWER);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLCompletionContext sqlCompletionContext = mock(SQLCompletionContext.class);
    when(sqlCompletionContext.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(sqlCompletionContext.getInsertCase()).thenReturn(-1);

    SQLCompletionRequest request = mock(SQLCompletionRequest.class);
    when(request.getContext()).thenReturn(sqlCompletionContext);

    // Act
    String actualConvertKeywordCaseResult =
        SQLCompletionAnalyzer.convertKeywordCase(request, "Replace String", false);

    // Assert
    verify(sqlDialect).getKeywordType("Replace String");
    verify(sqlDialect).storesUnquotedCase();
    verify(sqlSyntaxManager).getDialect();
    verify(sqlCompletionContext).getInsertCase();
    verify(sqlCompletionContext).getSyntaxManager();
    verify(request, atLeast(1)).getContext();
    assertEquals("replace string", actualConvertKeywordCaseResult);
  }

  /**
   * Test {@link SQLCompletionAnalyzer#convertKeywordCase(SQLCompletionRequest, String, boolean)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#storesUnquotedCase()} return {@code MIXED}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCompletionAnalyzer#convertKeywordCase(SQLCompletionRequest,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLCompletionAnalyzer.convertKeywordCase(SQLCompletionRequest, String, boolean)"
  })
  public void testConvertKeywordCase_givenSQLDialectStoresUnquotedCaseReturnMixed() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getKeywordType(Mockito.<String>any())).thenReturn(DBPKeywordType.FUNCTION);
    when(sqlDialect.storesUnquotedCase()).thenReturn(DBPIdentifierCase.MIXED);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLCompletionContext sqlCompletionContext = mock(SQLCompletionContext.class);
    when(sqlCompletionContext.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(sqlCompletionContext.getInsertCase()).thenReturn(-1);

    SQLCompletionRequest request = mock(SQLCompletionRequest.class);
    when(request.getContext()).thenReturn(sqlCompletionContext);

    // Act
    String actualConvertKeywordCaseResult =
        SQLCompletionAnalyzer.convertKeywordCase(request, "Replace String", false);

    // Assert
    verify(sqlDialect).getKeywordType("Replace String");
    verify(sqlDialect).storesUnquotedCase();
    verify(sqlSyntaxManager).getDialect();
    verify(sqlCompletionContext).getInsertCase();
    verify(sqlCompletionContext).getSyntaxManager();
    verify(request, atLeast(1)).getContext();
    assertEquals("Replace String", actualConvertKeywordCaseResult);
  }

  /**
   * Test {@link SQLCompletionAnalyzer#convertKeywordCase(SQLCompletionRequest, String, boolean)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#storesUnquotedCase()} return {@code UPPER}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCompletionAnalyzer#convertKeywordCase(SQLCompletionRequest,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLCompletionAnalyzer.convertKeywordCase(SQLCompletionRequest, String, boolean)"
  })
  public void testConvertKeywordCase_givenSQLDialectStoresUnquotedCaseReturnUpper() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getKeywordType(Mockito.<String>any())).thenReturn(DBPKeywordType.FUNCTION);
    when(sqlDialect.storesUnquotedCase()).thenReturn(DBPIdentifierCase.UPPER);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLCompletionContext sqlCompletionContext = mock(SQLCompletionContext.class);
    when(sqlCompletionContext.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(sqlCompletionContext.getInsertCase()).thenReturn(-1);

    SQLCompletionRequest request = mock(SQLCompletionRequest.class);
    when(request.getContext()).thenReturn(sqlCompletionContext);

    // Act
    String actualConvertKeywordCaseResult =
        SQLCompletionAnalyzer.convertKeywordCase(request, "Replace String", false);

    // Assert
    verify(sqlDialect).getKeywordType("Replace String");
    verify(sqlDialect).storesUnquotedCase();
    verify(sqlSyntaxManager).getDialect();
    verify(sqlCompletionContext).getInsertCase();
    verify(sqlCompletionContext).getSyntaxManager();
    verify(request, atLeast(1)).getContext();
    assertEquals("REPLACE STRING", actualConvertKeywordCaseResult);
  }

  /**
   * Test {@link SQLCompletionAnalyzer#convertKeywordCase(SQLCompletionRequest, String, boolean)}.
   *
   * <ul>
   *   <li>Given {@link SQLSyntaxManager} {@link SQLSyntaxManager#getKeywordCase()} return {@code
   *       LOWER}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCompletionAnalyzer#convertKeywordCase(SQLCompletionRequest,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLCompletionAnalyzer.convertKeywordCase(SQLCompletionRequest, String, boolean)"
  })
  public void testConvertKeywordCase_givenSQLSyntaxManagerGetKeywordCaseReturnLower() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getKeywordType(Mockito.<String>any())).thenReturn(DBPKeywordType.KEYWORD);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getKeywordCase()).thenReturn(DBPIdentifierCase.LOWER);
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLCompletionContext sqlCompletionContext = mock(SQLCompletionContext.class);
    when(sqlCompletionContext.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(sqlCompletionContext.getInsertCase()).thenReturn(-1);

    SQLCompletionRequest request = mock(SQLCompletionRequest.class);
    when(request.getContext()).thenReturn(sqlCompletionContext);

    // Act
    String actualConvertKeywordCaseResult =
        SQLCompletionAnalyzer.convertKeywordCase(request, "Replace String", false);

    // Assert
    verify(sqlDialect).getKeywordType("Replace String");
    verify(sqlSyntaxManager).getDialect();
    verify(sqlSyntaxManager).getKeywordCase();
    verify(sqlCompletionContext).getInsertCase();
    verify(sqlCompletionContext, atLeast(1)).getSyntaxManager();
    verify(request, atLeast(1)).getContext();
    assertEquals("replace string", actualConvertKeywordCaseResult);
  }

  /**
   * Test {@link SQLCompletionAnalyzer#convertKeywordCase(SQLCompletionRequest, String, boolean)}.
   *
   * <ul>
   *   <li>Given {@link SQLSyntaxManager} {@link SQLSyntaxManager#getKeywordCase()} return {@code
   *       MIXED}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCompletionAnalyzer#convertKeywordCase(SQLCompletionRequest,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLCompletionAnalyzer.convertKeywordCase(SQLCompletionRequest, String, boolean)"
  })
  public void testConvertKeywordCase_givenSQLSyntaxManagerGetKeywordCaseReturnMixed() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getKeywordType(Mockito.<String>any())).thenReturn(DBPKeywordType.KEYWORD);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getKeywordCase()).thenReturn(DBPIdentifierCase.MIXED);
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLCompletionContext sqlCompletionContext = mock(SQLCompletionContext.class);
    when(sqlCompletionContext.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(sqlCompletionContext.getInsertCase()).thenReturn(-1);

    SQLCompletionRequest request = mock(SQLCompletionRequest.class);
    when(request.getContext()).thenReturn(sqlCompletionContext);

    // Act
    String actualConvertKeywordCaseResult =
        SQLCompletionAnalyzer.convertKeywordCase(request, "Replace String", false);

    // Assert
    verify(sqlDialect).getKeywordType("Replace String");
    verify(sqlSyntaxManager).getDialect();
    verify(sqlSyntaxManager).getKeywordCase();
    verify(sqlCompletionContext).getInsertCase();
    verify(sqlCompletionContext, atLeast(1)).getSyntaxManager();
    verify(request, atLeast(1)).getContext();
    assertEquals("Replace String", actualConvertKeywordCaseResult);
  }

  /**
   * Test {@link SQLCompletionAnalyzer#convertKeywordCase(SQLCompletionRequest, String, boolean)}.
   *
   * <ul>
   *   <li>Given {@link SQLSyntaxManager} {@link SQLSyntaxManager#getKeywordCase()} return {@code
   *       UPPER}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCompletionAnalyzer#convertKeywordCase(SQLCompletionRequest,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLCompletionAnalyzer.convertKeywordCase(SQLCompletionRequest, String, boolean)"
  })
  public void testConvertKeywordCase_givenSQLSyntaxManagerGetKeywordCaseReturnUpper() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getKeywordType(Mockito.<String>any())).thenReturn(DBPKeywordType.KEYWORD);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getKeywordCase()).thenReturn(DBPIdentifierCase.UPPER);
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLCompletionContext sqlCompletionContext = mock(SQLCompletionContext.class);
    when(sqlCompletionContext.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(sqlCompletionContext.getInsertCase()).thenReturn(-1);

    SQLCompletionRequest request = mock(SQLCompletionRequest.class);
    when(request.getContext()).thenReturn(sqlCompletionContext);

    // Act
    String actualConvertKeywordCaseResult =
        SQLCompletionAnalyzer.convertKeywordCase(request, "Replace String", false);

    // Assert
    verify(sqlDialect).getKeywordType("Replace String");
    verify(sqlSyntaxManager).getDialect();
    verify(sqlSyntaxManager).getKeywordCase();
    verify(sqlCompletionContext).getInsertCase();
    verify(sqlCompletionContext, atLeast(1)).getSyntaxManager();
    verify(request, atLeast(1)).getContext();
    assertEquals("REPLACE STRING", actualConvertKeywordCaseResult);
  }

  /**
   * Test {@link SQLCompletionAnalyzer#convertKeywordCase(SQLCompletionRequest, String, boolean)}.
   *
   * <ul>
   *   <li>Then calls {@link SQLDialect#getStringQuoteStrings()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCompletionAnalyzer#convertKeywordCase(SQLCompletionRequest,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLCompletionAnalyzer.convertKeywordCase(SQLCompletionRequest, String, boolean)"
  })
  public void testConvertKeywordCase_thenCallsGetStringQuoteStrings() {
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
    when(context.getInsertCase()).thenReturn(1);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    Document document = new Document();
    SQLQuery activeQuery = new SQLQuery(mock(DBPDataSource.class), "Text");

    SQLCompletionRequest request =
        new SQLCompletionRequest(context, document, 1, activeQuery, true);

    // Act
    String actualConvertKeywordCaseResult =
        SQLCompletionAnalyzer.convertKeywordCase(request, "Replace String", true);

    // Assert
    verify(sqlDialect).getStringQuoteStrings();
    verify(sqlSyntaxManager).getDialect();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(context).getInsertCase();
    verify(context).getSyntaxManager();
    assertEquals("REPLACE STRING", actualConvertKeywordCaseResult);
  }

  /**
   * Test {@link SQLCompletionAnalyzer#convertKeywordCase(SQLCompletionRequest, String, boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return {@code Replace String}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCompletionAnalyzer#convertKeywordCase(SQLCompletionRequest,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLCompletionAnalyzer.convertKeywordCase(SQLCompletionRequest, String, boolean)"
  })
  public void testConvertKeywordCase_whenTrue_thenReturnReplaceString() {
    // Arrange
    SQLCompletionContext sqlCompletionContext = mock(SQLCompletionContext.class);
    when(sqlCompletionContext.getInsertCase()).thenReturn(-1);

    SQLCompletionRequest request = mock(SQLCompletionRequest.class);
    when(request.getContext()).thenReturn(sqlCompletionContext);

    // Act
    String actualConvertKeywordCaseResult =
        SQLCompletionAnalyzer.convertKeywordCase(request, "Replace String", true);

    // Assert
    verify(sqlCompletionContext).getInsertCase();
    verify(request).getContext();
    assertEquals("Replace String", actualConvertKeywordCaseResult);
  }
}
