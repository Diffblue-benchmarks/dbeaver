package org.jkiss.dbeaver.ext.mysql.edit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.ext.mysql.model.MySQLSequence;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.edit.DBECommandContext;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.impl.edit.TestCommandContext;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MySQLSequenceManagerDiffblueTest {
  /**
   * Test {@link MySQLSequenceManager#createDatabaseObject(DBRProgressMonitor, DBECommandContext,
   * Object, Object, Map)}.
   *
   * <p>Method under test: {@link MySQLSequenceManager#createDatabaseObject(DBRProgressMonitor,
   * DBECommandContext, Object, Object, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MySQLSequence MySQLSequenceManager.createDatabaseObject(DBRProgressMonitor, DBECommandContext, Object, Object, Map)"
  })
  public void testCreateDatabaseObject() throws DBException {
    // Arrange
    MySQLSequenceManager mySQLSequenceManager = new MySQLSequenceManager();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    TestCommandContext context = new TestCommandContext(mock(DBCExecutionContext.class), true);

    // Act
    MySQLSequence actualCreateDatabaseObjectResult =
        mySQLSequenceManager.createDatabaseObject(
            monitor, context, null, "Copy From", new HashMap<>());

    // Assert
    assertEquals("new_sequence", actualCreateDatabaseObjectResult.getName());
    assertNull(actualCreateDatabaseObjectResult.getLastValue());
    assertNull(actualCreateDatabaseObjectResult.getDescription());
    assertNull(actualCreateDatabaseObjectResult.getCatalog());
    assertNull(actualCreateDatabaseObjectResult.getParentObject());
    assertEquals(1, actualCreateDatabaseObjectResult.getIncrementBy().intValue());
    assertEquals(1, actualCreateDatabaseObjectResult.getMinValue().intValue());
    assertEquals(9223372036854775806L, actualCreateDatabaseObjectResult.getMaxValue().longValue());
    assertFalse(actualCreateDatabaseObjectResult.isPersisted());
  }

  /**
   * Test {@link MySQLSequenceManager#getMakerOptions(DBPDataSource)}.
   *
   * <p>Method under test: {@link MySQLSequenceManager#getMakerOptions(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long MySQLSequenceManager.getMakerOptions(DBPDataSource)"})
  public void testGetMakerOptions() {
    // Arrange, Act and Assert
    assertEquals(4L, new MySQLSequenceManager().getMakerOptions(mock(DBPDataSource.class)));
  }
}
