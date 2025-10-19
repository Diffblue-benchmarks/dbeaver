package org.jkiss.dbeaver.ext.mssql.edit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLServerProcedureManagerDiffblueTest {
  /**
   * Test {@link SQLServerProcedureManager#getMakerOptions(DBPDataSource)}.
   *
   * <p>Method under test: {@link SQLServerProcedureManager#getMakerOptions(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long SQLServerProcedureManager.getMakerOptions(DBPDataSource)"})
  public void testGetMakerOptions() {
    // Arrange, Act and Assert
    assertEquals(4L, new SQLServerProcedureManager().getMakerOptions(mock(DBPDataSource.class)));
  }
}
