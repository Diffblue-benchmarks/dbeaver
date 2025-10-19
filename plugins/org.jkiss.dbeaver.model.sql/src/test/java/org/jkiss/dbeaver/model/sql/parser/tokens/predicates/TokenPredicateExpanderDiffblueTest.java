package org.jkiss.dbeaver.model.sql.parser.tokens.predicates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.jkiss.dbeaver.model.sql.parser.TokenEntry;
import org.jkiss.dbeaver.model.sql.parser.tokens.SQLTokenType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TokenPredicateExpanderDiffblueTest {
  /**
   * Test {@link TokenPredicateExpander#expand(TokenPredicateNode)}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateExpander#expand(TokenPredicateNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List TokenPredicateExpander.expand(TokenPredicateNode)"})
  public void testExpand_thenReturnSizeIsOne() {
    // Arrange
    OptionalTokenPredicateNode node =
        new OptionalTokenPredicateNode(
            new CaptureTokenPredicateNode("String", SQLTokenType.T_KEYWORD, "Key"));

    // Act
    List<List<TokenEntry>> actualExpandResult = TokenPredicateExpander.expand(node);

    // Assert
    assertEquals(1, actualExpandResult.size());
    List<TokenEntry> getResult = actualExpandResult.get(0);
    assertEquals(1, getResult.size());
    assertSame(node.child, getResult.get(0));
  }

  /**
   * Test {@link TokenPredicateExpander#expand(TokenPredicateNode)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateExpander#expand(TokenPredicateNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List TokenPredicateExpander.expand(TokenPredicateNode)"})
  public void testExpand_whenNull_thenReturnEmpty() {
    // Arrange and Act
    List<List<TokenEntry>> actualExpandResult = TokenPredicateExpander.expand(null);

    // Assert
    assertTrue(actualExpandResult.isEmpty());
  }
}
