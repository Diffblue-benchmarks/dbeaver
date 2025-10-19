package org.jkiss.dbeaver.ext.oracle.edit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OracleDataTypeManagerDiffblueTest {
  /**
   * Test {@link OracleDataTypeManager#canCreateObject(Object)}.
   *
   * <p>Method under test: {@link OracleDataTypeManager#canCreateObject(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OracleDataTypeManager.canCreateObject(Object)"})
  public void testCanCreateObject() {
    // Arrange, Act and Assert
    assertFalse(new OracleDataTypeManager().canCreateObject(DBPEvent.RENAME));
  }

  /**
   * Test {@link OracleDataTypeManager#getMakerOptions(DBPDataSource)}.
   *
   * <p>Method under test: {@link OracleDataTypeManager#getMakerOptions(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long OracleDataTypeManager.getMakerOptions(DBPDataSource)"})
  public void testGetMakerOptions() {
    // Arrange, Act and Assert
    assertEquals(4L, new OracleDataTypeManager().getMakerOptions(mock(DBPDataSource.class)));
  }
}
