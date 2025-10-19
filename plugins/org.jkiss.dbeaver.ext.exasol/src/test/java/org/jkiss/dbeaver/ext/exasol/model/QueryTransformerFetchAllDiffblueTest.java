package org.jkiss.dbeaver.ext.exasol.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.sql.SQLQuery;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QueryTransformerFetchAllDiffblueTest {
  /**
   * Test {@link QueryTransformerFetchAll#transformQueryString(SQLQuery)}.
   *
   * <ul>
   *   <li>Then return {@code Text}.
   * </ul>
   *
   * <p>Method under test: {@link QueryTransformerFetchAll#transformQueryString(SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QueryTransformerFetchAll.transformQueryString(SQLQuery)"})
  public void testTransformQueryString_thenReturnText() throws DBCException {
    // Arrange
    QueryTransformerFetchAll queryTransformerFetchAll = new QueryTransformerFetchAll();

    // Act
    String actualTransformQueryStringResult =
        queryTransformerFetchAll.transformQueryString(
            new SQLQuery(mock(DBPDataSource.class), "Text"));

    // Assert
    assertEquals("Text", actualTransformQueryStringResult);
  }
}
