package org.jkiss.dbeaver.model.sql.parser;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.SQLSyntaxManager;
import org.jkiss.dbeaver.model.sql.parser.tokens.SQLTokenType;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SQLRuleManagerDiffblueTest {
  @InjectMocks private SQLRuleManager sQLRuleManager;

  @Mock private SQLSyntaxManager sQLSyntaxManager;

  /**
   * Test {@link SQLRuleManager#getRulesByType(SQLTokenType)}.
   *
   * <p>Method under test: {@link SQLRuleManager#getRulesByType(SQLTokenType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.text.parser.TPRule[] SQLRuleManager.getRulesByType(SQLTokenType)"
  })
  public void testGetRulesByType() {
    // Arrange, Act and Assert
    assertEquals(0, sQLRuleManager.getRulesByType(SQLTokenType.T_KEYWORD).length);
  }
}
