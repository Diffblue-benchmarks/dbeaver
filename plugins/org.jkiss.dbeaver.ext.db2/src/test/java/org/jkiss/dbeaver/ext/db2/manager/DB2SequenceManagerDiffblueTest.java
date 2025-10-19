package org.jkiss.dbeaver.ext.db2.manager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DB2SequenceManagerDiffblueTest {
  /**
   * Test {@link DB2SequenceManager#getMakerOptions(DBPDataSource)}.
   *
   * <p>Method under test: {@link DB2SequenceManager#getMakerOptions(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long DB2SequenceManager.getMakerOptions(DBPDataSource)"})
  public void testGetMakerOptions() {
    // Arrange, Act and Assert
    assertEquals(4L, new DB2SequenceManager().getMakerOptions(mock(DBPDataSource.class)));
  }
}
