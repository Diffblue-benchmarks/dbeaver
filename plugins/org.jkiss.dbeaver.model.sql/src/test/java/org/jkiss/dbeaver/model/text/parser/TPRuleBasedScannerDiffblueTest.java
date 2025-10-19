package org.jkiss.dbeaver.model.text.parser;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.jkiss.dbeaver.model.sql.parser.rules.SQLDelimiterRule;
import org.jkiss.dbeaver.model.sql.parser.tokens.SQLTokenType;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TPRuleBasedScannerDiffblueTest {
  @Mock private IDocument iDocument;

  @InjectMocks private TPRuleBasedScanner tPRuleBasedScanner;

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link TPRuleBasedScanner}
   *   <li>{@link TPRuleBasedScanner#startEval()}
   *   <li>{@link TPRuleBasedScanner#getLegalLineDelimiters()}
   *   <li>{@link TPRuleBasedScanner#getOffset()}
   *   <li>{@link TPRuleBasedScanner#getTokenOffset()}
   *   <li>{@link TPRuleBasedScanner#isEvalMode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TPRuleBasedScanner.<init>()",
    "char[][] TPRuleBasedScanner.getLegalLineDelimiters()",
    "int TPRuleBasedScanner.getOffset()",
    "int TPRuleBasedScanner.getTokenOffset()",
    "boolean TPRuleBasedScanner.isEvalMode()",
    "void TPRuleBasedScanner.startEval()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    TPRuleBasedScanner actualTpRuleBasedScanner = new TPRuleBasedScanner();
    actualTpRuleBasedScanner.startEval();
    char[][] actualLegalLineDelimiters = actualTpRuleBasedScanner.getLegalLineDelimiters();
    int actualOffset = actualTpRuleBasedScanner.getOffset();
    int actualTokenOffset = actualTpRuleBasedScanner.getTokenOffset();

    // Assert
    assertNull(actualLegalLineDelimiters);
    assertEquals(0, actualOffset);
    assertEquals(0, actualTokenOffset);
    assertTrue(actualTpRuleBasedScanner.isEvalMode());
  }

  /**
   * Test {@link TPRuleBasedScanner#setRange(IDocument, int, int)}.
   *
   * <p>Method under test: {@link TPRuleBasedScanner#setRange(IDocument, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TPRuleBasedScanner.setRange(IDocument, int, int)"})
  public void testSetRange() {
    // Arrange
    TPRuleBasedScanner tpRuleBasedScanner = new TPRuleBasedScanner();
    tpRuleBasedScanner.setDefaultReturnToken(new TPTokenDefault(SQLTokenType.T_BLOCK_BEGIN));

    // Act
    tpRuleBasedScanner.setRange(new Document(), 0, 0);

    // Assert
    assertEquals(0, tpRuleBasedScanner.getColumn());
    assertEquals(0, tpRuleBasedScanner.getOffset());
    assertEquals(0, tpRuleBasedScanner.getTokenEndOffset());
    assertEquals(0, tpRuleBasedScanner.getTokenLength());
    char[][] legalLineDelimiters = tpRuleBasedScanner.getLegalLineDelimiters();
    assertEquals(3, legalLineDelimiters.length);
    assertArrayEquals(new char[] {'\n'}, legalLineDelimiters[1]);
    assertArrayEquals(new char[] {'\r'}, legalLineDelimiters[0]);
    assertArrayEquals(new char[] {'\r', '\n'}, legalLineDelimiters[2]);
  }

  /**
   * Test {@link TPRuleBasedScanner#setRange(IDocument, int, int)}.
   *
   * <ul>
   *   <li>Given {@link IDocument} {@link IDocument#getLength()} return three.
   *   <li>Then array length is one.
   * </ul>
   *
   * <p>Method under test: {@link TPRuleBasedScanner#setRange(IDocument, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TPRuleBasedScanner.setRange(IDocument, int, int)"})
  public void testSetRange_givenIDocumentGetLengthReturnThree_thenArrayLengthIsOne() {
    // Arrange
    when(iDocument.getLegalLineDelimiters()).thenReturn(new String[] {"Legal Line Delimiters"});
    when(iDocument.getLength()).thenReturn(3);

    // Act
    tPRuleBasedScanner.setRange(iDocument, 2, 3);

    // Assert
    verify(iDocument).getLegalLineDelimiters();
    verify(iDocument).getLength();
    char[][] legalLineDelimiters = tPRuleBasedScanner.getLegalLineDelimiters();
    assertEquals(1, legalLineDelimiters.length);
    assertEquals(2, tPRuleBasedScanner.getColumn());
    assertEquals(2, tPRuleBasedScanner.getOffset());
    assertEquals(2, tPRuleBasedScanner.getTokenEndOffset());
    assertEquals(2, tPRuleBasedScanner.getTokenLength());
    assertArrayEquals("Legal Line Delimiters".toCharArray(), legalLineDelimiters[0]);
  }

  /**
   * Test {@link TPRuleBasedScanner#setRange(IDocument, int, int)}.
   *
   * <ul>
   *   <li>Given {@link TPRuleBasedScanner} (default constructor).
   *   <li>Then {@link TPRuleBasedScanner} (default constructor) Column is zero.
   * </ul>
   *
   * <p>Method under test: {@link TPRuleBasedScanner#setRange(IDocument, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TPRuleBasedScanner.setRange(IDocument, int, int)"})
  public void testSetRange_givenTPRuleBasedScanner_thenTPRuleBasedScannerColumnIsZero() {
    // Arrange
    TPRuleBasedScanner tpRuleBasedScanner = new TPRuleBasedScanner();

    // Act
    tpRuleBasedScanner.setRange(new Document(), 0, 3);

    // Assert
    assertEquals(0, tpRuleBasedScanner.getColumn());
    assertEquals(0, tpRuleBasedScanner.getOffset());
    assertEquals(0, tpRuleBasedScanner.getTokenEndOffset());
    assertEquals(0, tpRuleBasedScanner.getTokenLength());
    char[][] legalLineDelimiters = tpRuleBasedScanner.getLegalLineDelimiters();
    assertEquals(3, legalLineDelimiters.length);
    assertArrayEquals(new char[] {'\n'}, legalLineDelimiters[1]);
    assertArrayEquals(new char[] {'\r'}, legalLineDelimiters[0]);
    assertArrayEquals(new char[] {'\r', '\n'}, legalLineDelimiters[2]);
  }

  /**
   * Test {@link TPRuleBasedScanner#setRange(IDocument, int, int)}.
   *
   * <ul>
   *   <li>Then {@link TPRuleBasedScanner} (default constructor) Column is two.
   * </ul>
   *
   * <p>Method under test: {@link TPRuleBasedScanner#setRange(IDocument, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TPRuleBasedScanner.setRange(IDocument, int, int)"})
  public void testSetRange_thenTPRuleBasedScannerColumnIsTwo() {
    // Arrange
    TPRuleBasedScanner tpRuleBasedScanner = new TPRuleBasedScanner();

    // Act
    tpRuleBasedScanner.setRange(new Document("Not all who wander are lost"), 2, 3);

    // Assert
    assertEquals(2, tpRuleBasedScanner.getColumn());
    assertEquals(2, tpRuleBasedScanner.getOffset());
    assertEquals(2, tpRuleBasedScanner.getTokenEndOffset());
    assertEquals(2, tpRuleBasedScanner.getTokenLength());
    char[][] legalLineDelimiters = tpRuleBasedScanner.getLegalLineDelimiters();
    assertEquals(3, legalLineDelimiters.length);
    assertArrayEquals(new char[] {'\n'}, legalLineDelimiters[1]);
    assertArrayEquals(new char[] {'\r'}, legalLineDelimiters[0]);
    assertArrayEquals(new char[] {'\r', '\n'}, legalLineDelimiters[2]);
  }

  /**
   * Test {@link TPRuleBasedScanner#getTokenLength()}.
   *
   * <p>Method under test: {@link TPRuleBasedScanner#getTokenLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TPRuleBasedScanner.getTokenLength()"})
  public void testGetTokenLength() {
    // Arrange, Act and Assert
    assertEquals(0, new TPRuleBasedScanner().getTokenLength());
  }

  /**
   * Test {@link TPRuleBasedScanner#getTokenEndOffset()}.
   *
   * <p>Method under test: {@link TPRuleBasedScanner#getTokenEndOffset()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TPRuleBasedScanner.getTokenEndOffset()"})
  public void testGetTokenEndOffset() {
    // Arrange, Act and Assert
    assertEquals(0, new TPRuleBasedScanner().getTokenEndOffset());
  }

  /**
   * Test {@link TPRuleBasedScanner#getColumn()}.
   *
   * <p>Method under test: {@link TPRuleBasedScanner#getColumn()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TPRuleBasedScanner.getColumn()"})
  public void testGetColumn() {
    // Arrange, Act and Assert
    assertEquals(0, new TPRuleBasedScanner().getColumn());
  }

  /**
   * Test {@link TPRuleBasedScanner#nextToken()}.
   *
   * <ul>
   *   <li>Given {@link TPRuleBasedScanner} (default constructor).
   *   <li>Then {@link TPRuleBasedScanner} (default constructor) Offset is one.
   * </ul>
   *
   * <p>Method under test: {@link TPRuleBasedScanner#nextToken()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken TPRuleBasedScanner.nextToken()"})
  public void testNextToken_givenTPRuleBasedScanner_thenTPRuleBasedScannerOffsetIsOne() {
    // Arrange
    TPRuleBasedScanner tpRuleBasedScanner = new TPRuleBasedScanner();

    // Act
    TPToken actualNextTokenResult = tpRuleBasedScanner.nextToken();

    // Assert
    assertEquals(1, tpRuleBasedScanner.getOffset());
    assertSame(((TPTokenAbstract) actualNextTokenResult).EOF, actualNextTokenResult);
  }

  /**
   * Test {@link TPRuleBasedScanner#nextToken()}.
   *
   * <ul>
   *   <li>Given {@link TPRule} {@link TPRule#evaluate(TPCharacterScanner)} return {@link
   *       TPTokenAbstract#EOF}.
   *   <li>Then {@link TPRuleBasedScanner} (default constructor) Offset is zero.
   * </ul>
   *
   * <p>Method under test: {@link TPRuleBasedScanner#nextToken()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken TPRuleBasedScanner.nextToken()"})
  public void testNextToken_givenTPRuleEvaluateReturnEof_thenTPRuleBasedScannerOffsetIsZero() {
    // Arrange
    TPRule tpRule = mock(TPRule.class);
    when(tpRule.evaluate(Mockito.<TPCharacterScanner>any())).thenReturn(TPTokenAbstract.EOF);

    TPRuleBasedScanner tpRuleBasedScanner = new TPRuleBasedScanner();
    tpRuleBasedScanner.setRules(new TPRule[] {tpRule});

    // Act
    TPToken actualNextTokenResult = tpRuleBasedScanner.nextToken();

    // Assert
    verify(tpRule).evaluate(isA(TPCharacterScanner.class));
    assertEquals(0, tpRuleBasedScanner.getOffset());
    assertSame(((TPTokenAbstract) actualNextTokenResult).EOF, actualNextTokenResult);
  }

  /**
   * Test {@link TPRuleBasedScanner#nextToken()}.
   *
   * <ul>
   *   <li>Given {@link TPRule} {@link TPRule#evaluate(TPCharacterScanner)} return {@link
   *       TPTokenAbstract#UNDEFINED}.
   * </ul>
   *
   * <p>Method under test: {@link TPRuleBasedScanner#nextToken()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken TPRuleBasedScanner.nextToken()"})
  public void testNextToken_givenTPRuleEvaluateReturnUndefined() {
    // Arrange
    TPRule tpRule = mock(TPRule.class);
    when(tpRule.evaluate(Mockito.<TPCharacterScanner>any())).thenReturn(TPTokenAbstract.UNDEFINED);

    TPRuleBasedScanner tpRuleBasedScanner = new TPRuleBasedScanner();
    tpRuleBasedScanner.setRules(new TPRule[] {tpRule});

    // Act
    TPToken actualNextTokenResult = tpRuleBasedScanner.nextToken();

    // Assert
    verify(tpRule).evaluate(isA(TPCharacterScanner.class));
    assertEquals(1, tpRuleBasedScanner.getOffset());
    assertSame(((TPTokenAbstract) actualNextTokenResult).EOF, actualNextTokenResult);
  }

  /**
   * Test {@link TPRuleBasedScanner#read()}.
   *
   * <p>Method under test: {@link TPRuleBasedScanner#read()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TPRuleBasedScanner.read()"})
  public void testRead() {
    // Arrange
    TPRuleBasedScanner tpRuleBasedScanner = new TPRuleBasedScanner();

    // Act
    int actualReadResult = tpRuleBasedScanner.read();

    // Assert
    assertEquals(1, tpRuleBasedScanner.getOffset());
    assertEquals(TPCharacterScanner.EOF, actualReadResult);
  }

  /**
   * Test {@link TPRuleBasedScanner#unread()}.
   *
   * <p>Method under test: {@link TPRuleBasedScanner#unread()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TPRuleBasedScanner.unread()"})
  public void testUnread() {
    // Arrange
    TPRuleBasedScanner tpRuleBasedScanner = new TPRuleBasedScanner();

    // Act
    tpRuleBasedScanner.unread();

    // Assert
    assertEquals(TPCharacterScanner.EOF, tpRuleBasedScanner.getOffset());
    assertEquals(TPCharacterScanner.EOF, tpRuleBasedScanner.getTokenEndOffset());
    assertEquals(TPCharacterScanner.EOF, tpRuleBasedScanner.getTokenLength());
  }

  /**
   * Test {@link TPRuleBasedScanner#endEval()}.
   *
   * <ul>
   *   <li>Then calls {@link SQLDelimiterRule#changeDelimiter(String)}.
   * </ul>
   *
   * <p>Method under test: {@link TPRuleBasedScanner#endEval()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TPRuleBasedScanner.endEval()"})
  public void testEndEval_thenCallsChangeDelimiter() {
    // Arrange
    SQLDelimiterRule sqlDelimiterRule = mock(SQLDelimiterRule.class);
    doNothing().when(sqlDelimiterRule).changeDelimiter(Mockito.<String>any());

    TPRuleBasedScanner tpRuleBasedScanner = new TPRuleBasedScanner();
    tpRuleBasedScanner.setRules(new TPRule[] {sqlDelimiterRule});

    // Act
    tpRuleBasedScanner.endEval();

    // Assert
    verify(sqlDelimiterRule).changeDelimiter(null);
  }
}
