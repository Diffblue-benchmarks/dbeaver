package org.jkiss.dbeaver.ext.oracle.edit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OracleSequenceManagerDiffblueTest {
  /**
   * Test {@link OracleSequenceManager#getMakerOptions(DBPDataSource)}.
   *
   * <p>Method under test: {@link OracleSequenceManager#getMakerOptions(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long OracleSequenceManager.getMakerOptions(DBPDataSource)"})
  public void testGetMakerOptions() {
    // Arrange, Act and Assert
    assertEquals(4L, new OracleSequenceManager().getMakerOptions(mock(DBPDataSource.class)));
  }
}
