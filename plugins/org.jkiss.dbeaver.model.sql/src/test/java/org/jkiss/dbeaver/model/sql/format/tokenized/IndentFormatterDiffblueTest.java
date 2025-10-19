package org.jkiss.dbeaver.model.sql.format.tokenized;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.SQLSyntaxManager;
import org.jkiss.dbeaver.model.sql.format.SQLFormatterConfiguration;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class IndentFormatterDiffblueTest {
  /**
   * Test {@link IndentFormatter#IndentFormatter(SQLFormatterConfiguration, boolean)}.
   *
   * <p>Method under test: {@link IndentFormatter#IndentFormatter(SQLFormatterConfiguration,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void IndentFormatter.<init>(SQLFormatterConfiguration, boolean)"})
  public void testNewIndentFormatter() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getBlockHeaderStrings()).thenReturn(new String[] {"Block Header Strings"});
    when(sqlDialect.getScriptDelimiterRedefiner()).thenReturn("Script Delimiter Redefiner");

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLFormatterConfiguration formatterCfg = mock(SQLFormatterConfiguration.class);
    when(formatterCfg.getSyntaxManager()).thenReturn(sqlSyntaxManager);

    // Act
    new IndentFormatter(formatterCfg, true);

    // Assert
    verify(sqlDialect).getBlockHeaderStrings();
    verify(sqlDialect).getScriptDelimiterRedefiner();
    verify(sqlSyntaxManager, atLeast(1)).getDialect();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(formatterCfg, atLeast(1)).getSyntaxManager();
  }

  /**
   * Test {@link IndentFormatter#IndentFormatter(SQLFormatterConfiguration, boolean)}.
   *
   * <p>Method under test: {@link IndentFormatter#IndentFormatter(SQLFormatterConfiguration,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void IndentFormatter.<init>(SQLFormatterConfiguration, boolean)"})
  public void testNewIndentFormatter2() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getBlockHeaderStrings()).thenReturn(new String[] {"Block Header Strings"});
    when(sqlDialect.getScriptDelimiterRedefiner()).thenReturn("Script Delimiter Redefiner");

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"foo", null});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLFormatterConfiguration formatterCfg = mock(SQLFormatterConfiguration.class);
    when(formatterCfg.getSyntaxManager()).thenReturn(sqlSyntaxManager);

    // Act
    new IndentFormatter(formatterCfg, true);

    // Assert
    verify(sqlDialect).getBlockHeaderStrings();
    verify(sqlDialect).getScriptDelimiterRedefiner();
    verify(sqlSyntaxManager, atLeast(1)).getDialect();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(formatterCfg, atLeast(1)).getSyntaxManager();
  }

  /**
   * Test {@link IndentFormatter#IndentFormatter(SQLFormatterConfiguration, boolean)}.
   *
   * <p>Method under test: {@link IndentFormatter#IndentFormatter(SQLFormatterConfiguration,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void IndentFormatter.<init>(SQLFormatterConfiguration, boolean)"})
  public void testNewIndentFormatter3() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getBlockHeaderStrings()).thenReturn(new String[] {"Block Header Strings"});
    when(sqlDialect.getScriptDelimiterRedefiner()).thenReturn("Script Delimiter Redefiner");

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {""});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLFormatterConfiguration formatterCfg = mock(SQLFormatterConfiguration.class);
    when(formatterCfg.getSyntaxManager()).thenReturn(sqlSyntaxManager);

    // Act
    new IndentFormatter(formatterCfg, true);

    // Assert
    verify(sqlDialect).getBlockHeaderStrings();
    verify(sqlDialect).getScriptDelimiterRedefiner();
    verify(sqlSyntaxManager, atLeast(1)).getDialect();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(formatterCfg, atLeast(1)).getSyntaxManager();
  }

  /**
   * Test {@link IndentFormatter#IndentFormatter(SQLFormatterConfiguration, boolean)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getScriptDelimiterRedefiner()} return {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link IndentFormatter#IndentFormatter(SQLFormatterConfiguration,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void IndentFormatter.<init>(SQLFormatterConfiguration, boolean)"})
  public void testNewIndentFormatter_givenSQLDialectGetScriptDelimiterRedefinerReturnNull() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getBlockHeaderStrings()).thenReturn(new String[] {"Block Header Strings"});
    when(sqlDialect.getScriptDelimiterRedefiner()).thenReturn(null);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLFormatterConfiguration formatterCfg = mock(SQLFormatterConfiguration.class);
    when(formatterCfg.getSyntaxManager()).thenReturn(sqlSyntaxManager);

    // Act
    new IndentFormatter(formatterCfg, true);

    // Assert
    verify(sqlDialect).getBlockHeaderStrings();
    verify(sqlDialect).getScriptDelimiterRedefiner();
    verify(sqlSyntaxManager, atLeast(1)).getDialect();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(formatterCfg, atLeast(1)).getSyntaxManager();
  }
}
