package org.jkiss.dbeaver.ext.postgresql.edit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostgreProcedureManagerDiffblueTest {
  /**
   * Test {@link PostgreProcedureManager#canCreateObject(Object)}.
   *
   * <p>Method under test: {@link PostgreProcedureManager#canCreateObject(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PostgreProcedureManager.canCreateObject(Object)"})
  public void testCanCreateObject() {
    // Arrange, Act and Assert
    assertFalse(new PostgreProcedureManager().canCreateObject("Container"));
  }

  /**
   * Test {@link PostgreProcedureManager#getMakerOptions(DBPDataSource)}.
   *
   * <p>Method under test: {@link PostgreProcedureManager#getMakerOptions(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long PostgreProcedureManager.getMakerOptions(DBPDataSource)"})
  public void testGetMakerOptions() {
    // Arrange, Act and Assert
    assertEquals(4L, new PostgreProcedureManager().getMakerOptions(mock(DBPDataSource.class)));
  }
}
