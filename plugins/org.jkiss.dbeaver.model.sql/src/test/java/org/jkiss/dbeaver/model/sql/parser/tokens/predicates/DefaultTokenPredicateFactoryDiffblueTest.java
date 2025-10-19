package org.jkiss.dbeaver.model.sql.parser.tokens.predicates;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.parser.tokens.SQLTokenType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DefaultTokenPredicateFactoryDiffblueTest {
  /**
   * Test {@link DefaultTokenPredicateFactory#classifyToken(String)}.
   *
   * <p>Method under test: {@link DefaultTokenPredicateFactory#classifyToken(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLTokenType DefaultTokenPredicateFactory.classifyToken(String)"})
  public void testClassifyToken() {
    // Arrange, Act and Assert
    assertEquals(
        SQLTokenType.T_UNKNOWN, new DefaultTokenPredicateFactory().classifyToken("ABC123"));
  }
}
