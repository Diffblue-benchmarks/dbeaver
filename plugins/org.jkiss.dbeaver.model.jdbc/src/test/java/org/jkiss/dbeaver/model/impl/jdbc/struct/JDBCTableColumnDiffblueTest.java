package org.jkiss.dbeaver.model.impl.jdbc.struct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.impl.jdbc.struct.JDBCTableColumn.ColumnTypeNameListProvider;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCTableColumnDiffblueTest {
  /**
   * Test ColumnTypeNameListProvider {@link ColumnTypeNameListProvider#allowCustomValue()}.
   *
   * <p>Method under test: {@link ColumnTypeNameListProvider#allowCustomValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ColumnTypeNameListProvider.allowCustomValue()"})
  public void testColumnTypeNameListProviderAllowCustomValue() {
    // Arrange, Act and Assert
    assertTrue(new ColumnTypeNameListProvider().allowCustomValue());
  }

  /**
   * Test ColumnTypeNameListProvider {@link
   * ColumnTypeNameListProvider#getPossibleValues(JDBCTableColumn)} with {@code JDBCTableColumn}.
   *
   * <p>Method under test: {@link ColumnTypeNameListProvider#getPossibleValues(JDBCTableColumn)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object[] ColumnTypeNameListProvider.getPossibleValues(JDBCTableColumn)"})
  public void testColumnTypeNameListProviderGetPossibleValuesWithJDBCTableColumn() {
    // Arrange
    ColumnTypeNameListProvider columnTypeNameListProvider = new ColumnTypeNameListProvider();

    JDBCTableColumn<?> column = mock(JDBCTableColumn.class);
    when(column.getDataSource()).thenReturn(mock(DBPDataSource.class));

    // Act
    Object[] actualPossibleValues = columnTypeNameListProvider.getPossibleValues(column);

    // Assert
    verify(column).getDataSource();
    assertTrue(actualPossibleValues instanceof String[]);
    assertEquals(0, actualPossibleValues.length);
  }
}
