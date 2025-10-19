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

public class OracleQueueManagerDiffblueTest {
  /**
   * Test {@link OracleQueueManager#getMakerOptions(DBPDataSource)}.
   *
   * <p>Method under test: {@link OracleQueueManager#getMakerOptions(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long OracleQueueManager.getMakerOptions(DBPDataSource)"})
  public void testGetMakerOptions() {
    // Arrange, Act and Assert
    assertEquals(4L, new OracleQueueManager().getMakerOptions(mock(DBPDataSource.class)));
  }

  /**
   * Test {@link OracleQueueManager#canCreateObject(Object)}.
   *
   * <p>Method under test: {@link OracleQueueManager#canCreateObject(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OracleQueueManager.canCreateObject(Object)"})
  public void testCanCreateObject() {
    // Arrange, Act and Assert
    assertFalse(new OracleQueueManager().canCreateObject(DBPEvent.RENAME));
  }
}
