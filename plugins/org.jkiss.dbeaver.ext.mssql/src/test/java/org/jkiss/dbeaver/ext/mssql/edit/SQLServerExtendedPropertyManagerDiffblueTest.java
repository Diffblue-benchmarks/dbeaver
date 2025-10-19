package org.jkiss.dbeaver.ext.mssql.edit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLServerExtendedPropertyManagerDiffblueTest {
  /**
   * Test {@link SQLServerExtendedPropertyManager#getMakerOptions(DBPDataSource)}.
   *
   * <p>Method under test: {@link SQLServerExtendedPropertyManager#getMakerOptions(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long SQLServerExtendedPropertyManager.getMakerOptions(DBPDataSource)"})
  public void testGetMakerOptions() {
    // Arrange, Act and Assert
    assertEquals(
        0L, new SQLServerExtendedPropertyManager().getMakerOptions(mock(DBPDataSource.class)));
  }
}
