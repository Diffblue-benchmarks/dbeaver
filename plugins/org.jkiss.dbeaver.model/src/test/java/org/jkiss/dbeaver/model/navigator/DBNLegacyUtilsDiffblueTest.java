package org.jkiss.dbeaver.model.navigator;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.app.DBPPlatform;
import org.jkiss.dbeaver.model.navigator.DBNModel.NodePath;
import org.jkiss.dbeaver.model.navigator.DBNNode.NodePathType;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBNLegacyUtilsDiffblueTest {
  /**
   * Test {@link DBNLegacyUtils#legacyGetNodeByPath(DBRProgressMonitor, DBNModel, NodePath)} with
   * {@code monitor}, {@code model}, {@code nodePath}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNLegacyUtils#legacyGetNodeByPath(DBRProgressMonitor, DBNModel,
   * NodePath)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBNNode DBNLegacyUtils.legacyGetNodeByPath(DBRProgressMonitor, DBNModel, NodePath)"
  })
  public void testLegacyGetNodeByPathWithMonitorModelNodePath_thenReturnNull() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel model = new DBNModel(platform, new ArrayList<>());
    NodePath nodePath = new NodePath(NodePathType.other, new ArrayList<>());

    // Act
    DBNNode actualLegacyGetNodeByPathResult =
        DBNLegacyUtils.legacyGetNodeByPath(monitor, model, nodePath);

    // Assert
    assertNull(actualLegacyGetNodeByPathResult);
  }

  /**
   * Test {@link DBNLegacyUtils#legacyGetNodeByPath(DBRProgressMonitor, DBNProject, NodePath)} with
   * {@code monitor}, {@code projectNode}, {@code nodePath}.
   *
   * <p>Method under test: {@link DBNLegacyUtils#legacyGetNodeByPath(DBRProgressMonitor, DBNProject,
   * NodePath)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBNNode DBNLegacyUtils.legacyGetNodeByPath(DBRProgressMonitor, DBNProject, NodePath)"
  })
  public void testLegacyGetNodeByPathWithMonitorProjectNodeNodePath() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    DBNNode actualLegacyGetNodeByPathResult =
        DBNLegacyUtils.legacyGetNodeByPath(
            monitor, (DBNProject) null, new NodePath(NodePathType.dbvfs, new ArrayList<>()));

    // Assert
    assertNull(actualLegacyGetNodeByPathResult);
  }

  /**
   * Test {@link DBNLegacyUtils#legacyGetNodeByPath(DBRProgressMonitor, DBNProject, NodePath)} with
   * {@code monitor}, {@code projectNode}, {@code nodePath}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNLegacyUtils#legacyGetNodeByPath(DBRProgressMonitor, DBNProject,
   * NodePath)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBNNode DBNLegacyUtils.legacyGetNodeByPath(DBRProgressMonitor, DBNProject, NodePath)"
  })
  public void testLegacyGetNodeByPathWithMonitorProjectNodeNodePath_thenReturnNull()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    DBNNode actualLegacyGetNodeByPathResult =
        DBNLegacyUtils.legacyGetNodeByPath(
            monitor, (DBNProject) null, new NodePath(NodePathType.resource, new ArrayList<>()));

    // Assert
    assertNull(actualLegacyGetNodeByPathResult);
  }
}
