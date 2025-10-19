package org.jkiss.dbeaver.model.sql.parser.tokens.predicates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.parser.tokens.SQLTokenType;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class CaptureTokenPredicateNodeDiffblueTest {
  /**
   * Test {@link CaptureTokenPredicateNode#CaptureTokenPredicateNode(String, SQLTokenType, String)}.
   *
   * <p>Method under test: {@link CaptureTokenPredicateNode#CaptureTokenPredicateNode(String,
   * SQLTokenType, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CaptureTokenPredicateNode.<init>(String, SQLTokenType, String)"})
  public void testNewCaptureTokenPredicateNode() {
    // Arrange and Act
    CaptureTokenPredicateNode actualCaptureTokenPredicateNode =
        new CaptureTokenPredicateNode("String", SQLTokenType.T_KEYWORD, "Key");

    // Assert
    assertEquals("String", actualCaptureTokenPredicateNode.getString());
    assertEquals(SQLTokenType.T_KEYWORD, actualCaptureTokenPredicateNode.getTokenType());
    assertFalse(actualCaptureTokenPredicateNode.isInverted());
  }

  /**
   * Test {@link CaptureTokenPredicateNode#format(StringBuilder)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code
   *       foo$<T_KEYWORD>'String'}.
   * </ul>
   *
   * <p>Method under test: {@link CaptureTokenPredicateNode#format(StringBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"StringBuilder CaptureTokenPredicateNode.format(StringBuilder)"})
  public void testFormat_thenStringBuilderWithFooToStringIsFooTKeywordString() {
    // Arrange
    CaptureTokenPredicateNode captureTokenPredicateNode =
        new CaptureTokenPredicateNode("String", SQLTokenType.T_KEYWORD, "Key");
    StringBuilder sb = new StringBuilder("foo");

    // Act
    StringBuilder actualFormatResult = captureTokenPredicateNode.format(sb);

    // Assert
    assertEquals("foo$<T_KEYWORD>'String'", sb.toString());
    assertSame(sb, actualFormatResult);
  }

  /**
   * Test {@link CaptureTokenPredicateNode#applyImpl(TokenPredicateNodeVisitor, Object)}.
   *
   * <p>Method under test: {@link CaptureTokenPredicateNode#applyImpl(TokenPredicateNodeVisitor,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object CaptureTokenPredicateNode.applyImpl(TokenPredicateNodeVisitor, Object)"
  })
  public void testApplyImpl() {
    // Arrange
    CaptureTokenPredicateNode captureTokenPredicateNode =
        new CaptureTokenPredicateNode("String", SQLTokenType.T_KEYWORD, "Key");

    TokenPredicateNodeVisitor<Object, Object> visitor = mock(TokenPredicateNodeVisitor.class);
    when(visitor.visitCapture(Mockito.<CaptureTokenPredicateNode>any(), Mockito.<Object>any()))
        .thenReturn("Visit Capture");

    // Act
    Object actualApplyImplResult = captureTokenPredicateNode.applyImpl(visitor, "Arg");

    // Assert
    verify(visitor).visitCapture(isA(CaptureTokenPredicateNode.class), isA(Object.class));
    assertEquals("Visit Capture", actualApplyImplResult);
  }
}
