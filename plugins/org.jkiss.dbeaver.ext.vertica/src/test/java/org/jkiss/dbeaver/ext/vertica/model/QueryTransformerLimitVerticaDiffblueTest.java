package org.jkiss.dbeaver.ext.vertica.model;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.SQLQuery;
import org.jkiss.utils.Pair;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QueryTransformerLimitVerticaDiffblueTest {
  /**
   * Test {@link QueryTransformerLimitVertica#isApplicableTo(SQLQuery)}.
   *
   * <p>Method under test: {@link QueryTransformerLimitVertica#isApplicableTo(SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QueryTransformerLimitVertica.isApplicableTo(SQLQuery)"})
  public void testIsApplicableTo() {
    // Arrange
    QueryTransformerLimitVertica queryTransformerLimitVertica = new QueryTransformerLimitVertica();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(new Pair<>("", "Second"));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    boolean actualIsApplicableToResult =
        queryTransformerLimitVertica.isApplicableTo(new SQLQuery(dataSource, "Text"));

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    assertFalse(actualIsApplicableToResult);
  }

  /**
   * Test {@link QueryTransformerLimitVertica#isApplicableTo(SQLQuery)}.
   *
   * <ul>
   *   <li>Then calls {@link SQLDialect#getIdentifierQuoteStrings()}.
   * </ul>
   *
   * <p>Method under test: {@link QueryTransformerLimitVertica#isApplicableTo(SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QueryTransformerLimitVertica.isApplicableTo(SQLQuery)"})
  public void testIsApplicableTo_thenCallsGetIdentifierQuoteStrings() {
    // Arrange
    QueryTransformerLimitVertica queryTransformerLimitVertica = new QueryTransformerLimitVertica();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(new Pair<>("First", "Second"));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    boolean actualIsApplicableToResult =
        queryTransformerLimitVertica.isApplicableTo(new SQLQuery(dataSource, "Text"));

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    assertFalse(actualIsApplicableToResult);
  }

  /**
   * Test {@link QueryTransformerLimitVertica#isApplicableTo(SQLQuery)}.
   *
   * <ul>
   *   <li>When {@link SQLQuery#SQLQuery(DBPDataSource, String)} with dataSource is {@link
   *       DBPDataSource} and text is empty string.
   * </ul>
   *
   * <p>Method under test: {@link QueryTransformerLimitVertica#isApplicableTo(SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QueryTransformerLimitVertica.isApplicableTo(SQLQuery)"})
  public void testIsApplicableTo_whenSQLQueryWithDataSourceIsDBPDataSourceAndTextIsEmptyString() {
    // Arrange
    QueryTransformerLimitVertica queryTransformerLimitVertica = new QueryTransformerLimitVertica();

    // Act
    boolean actualIsApplicableToResult =
        queryTransformerLimitVertica.isApplicableTo(new SQLQuery(mock(DBPDataSource.class), ""));

    // Assert
    assertFalse(actualIsApplicableToResult);
  }
}
