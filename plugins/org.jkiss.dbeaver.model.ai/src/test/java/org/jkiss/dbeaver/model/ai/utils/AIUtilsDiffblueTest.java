package org.jkiss.dbeaver.model.ai.utils;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.struct.rdb.DBSProcedure;
import org.jkiss.dbeaver.model.struct.rdb.DBSTrigger;
import org.jkiss.dbeaver.model.struct.rdb.DBSView;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AIUtilsDiffblueTest {
  /**
   * Test {@link AIUtils#getObjectDDL(DBSObject, DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>When {@link DBSDocumentConstraint#DBSDocumentConstraint(DBSDocumentContainer)} with
   *       entity is {@link DBSDocumentContainer}.
   * </ul>
   *
   * <p>Method under test: {@link AIUtils#getObjectDDL(DBSObject, DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AIUtils.getObjectDDL(DBSObject, DBRProgressMonitor)"})
  public void testGetObjectDDL_whenDBSDocumentConstraintWithEntityIsDBSDocumentContainer() {
    // Arrange
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    // Act and Assert
    assertNull(AIUtils.getObjectDDL(object, new LoggingProgressMonitor()));
  }

  /**
   * Test {@link AIUtils#getObjectDDL(DBSObject, DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>When {@link DBSProcedure}.
   * </ul>
   *
   * <p>Method under test: {@link AIUtils#getObjectDDL(DBSObject, DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AIUtils.getObjectDDL(DBSObject, DBRProgressMonitor)"})
  public void testGetObjectDDL_whenDBSProcedure() {
    // Arrange
    DBSProcedure object = mock(DBSProcedure.class);

    // Act and Assert
    assertNull(AIUtils.getObjectDDL(object, new LoggingProgressMonitor()));
  }

  /**
   * Test {@link AIUtils#getObjectDDL(DBSObject, DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>When {@link DBSTrigger}.
   * </ul>
   *
   * <p>Method under test: {@link AIUtils#getObjectDDL(DBSObject, DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AIUtils.getObjectDDL(DBSObject, DBRProgressMonitor)"})
  public void testGetObjectDDL_whenDBSTrigger() {
    // Arrange
    DBSTrigger object = mock(DBSTrigger.class);

    // Act and Assert
    assertNull(AIUtils.getObjectDDL(object, new LoggingProgressMonitor()));
  }

  /**
   * Test {@link AIUtils#getObjectDDL(DBSObject, DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>When {@link DBSView}.
   * </ul>
   *
   * <p>Method under test: {@link AIUtils#getObjectDDL(DBSObject, DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AIUtils.getObjectDDL(DBSObject, DBRProgressMonitor)"})
  public void testGetObjectDDL_whenDBSView() {
    // Arrange
    DBSView object = mock(DBSView.class);

    // Act and Assert
    assertNull(AIUtils.getObjectDDL(object, new LoggingProgressMonitor()));
  }

  /**
   * Test {@link AIUtils#getObjectDDL(DBSObject, DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AIUtils#getObjectDDL(DBSObject, DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AIUtils.getObjectDDL(DBSObject, DBRProgressMonitor)"})
  public void testGetObjectDDL_whenNull() {
    // Arrange, Act and Assert
    assertNull(AIUtils.getObjectDDL(null, new LoggingProgressMonitor()));
  }
}
