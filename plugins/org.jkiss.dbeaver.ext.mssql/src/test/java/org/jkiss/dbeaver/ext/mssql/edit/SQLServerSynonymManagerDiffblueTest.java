package org.jkiss.dbeaver.ext.mssql.edit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLServerSynonymManagerDiffblueTest {
  /**
   * Test {@link SQLServerSynonymManager#canCreateObject(Object)}.
   *
   * <p>Method under test: {@link SQLServerSynonymManager#canCreateObject(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLServerSynonymManager.canCreateObject(Object)"})
  public void testCanCreateObject() {
    // Arrange, Act and Assert
    assertFalse(new SQLServerSynonymManager().canCreateObject("Container"));
  }

  /**
   * Test {@link SQLServerSynonymManager#getMakerOptions(DBPDataSource)}.
   *
   * <p>Method under test: {@link SQLServerSynonymManager#getMakerOptions(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long SQLServerSynonymManager.getMakerOptions(DBPDataSource)"})
  public void testGetMakerOptions() {
    // Arrange, Act and Assert
    assertEquals(0L, new SQLServerSynonymManager().getMakerOptions(mock(DBPDataSource.class)));
  }
}
