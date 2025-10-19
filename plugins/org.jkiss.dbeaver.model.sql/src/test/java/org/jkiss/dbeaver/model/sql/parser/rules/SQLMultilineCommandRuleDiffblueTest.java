package org.jkiss.dbeaver.model.sql.parser.rules;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.parser.tokens.SQLControlToken;
import org.jkiss.dbeaver.model.sql.registry.SQLCommandHandlerDescriptor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SQLMultilineCommandRuleDiffblueTest {
  @Mock private SQLCommandHandlerDescriptor sQLCommandHandlerDescriptor;

  @Mock private SQLControlToken sQLControlToken;

  /**
   * Test {@link SQLMultilineCommandRule#SQLMultilineCommandRule(String,
   * SQLCommandHandlerDescriptor, SQLControlToken)}.
   *
   * <ul>
   *   <li>When {@code Command Prefix}.
   * </ul>
   *
   * <p>Method under test: {@link SQLMultilineCommandRule#SQLMultilineCommandRule(String,
   * SQLCommandHandlerDescriptor, SQLControlToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLMultilineCommandRule.<init>(String, SQLCommandHandlerDescriptor, SQLControlToken)"
  })
  public void testNewSQLMultilineCommandRule_whenCommandPrefix() {
    // Arrange
    when(sQLCommandHandlerDescriptor.getId()).thenReturn("42");

    // Act
    SQLMultilineCommandRule actualSqlMultilineCommandRule =
        new SQLMultilineCommandRule("Command Prefix", sQLCommandHandlerDescriptor, sQLControlToken);

    // Assert
    verify(sQLCommandHandlerDescriptor).getId();
    assertSame(sQLControlToken, actualSqlMultilineCommandRule.getSuccessToken());
  }

  /**
   * Test {@link SQLMultilineCommandRule#SQLMultilineCommandRule(String,
   * SQLCommandHandlerDescriptor, SQLControlToken)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLMultilineCommandRule#SQLMultilineCommandRule(String,
   * SQLCommandHandlerDescriptor, SQLControlToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLMultilineCommandRule.<init>(String, SQLCommandHandlerDescriptor, SQLControlToken)"
  })
  public void testNewSQLMultilineCommandRule_whenNull() {
    // Arrange
    when(sQLCommandHandlerDescriptor.getId()).thenReturn("42");

    // Act
    SQLMultilineCommandRule actualSqlMultilineCommandRule =
        new SQLMultilineCommandRule(null, sQLCommandHandlerDescriptor, sQLControlToken);

    // Assert
    verify(sQLCommandHandlerDescriptor).getId();
    assertSame(sQLControlToken, actualSqlMultilineCommandRule.getSuccessToken());
  }
}
