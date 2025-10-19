package org.jkiss.dbeaver.model.sql.generator;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Collection;
import java.util.List;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSEntityAttribute;
import org.jkiss.dbeaver.model.struct.rdb.DBSProcedure;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLGeneratorProcedureDiffblueTest {
  /**
   * Test {@link SQLGeneratorProcedure#getAllAttributes(DBRProgressMonitor, DBSProcedure)} with
   * {@code DBRProgressMonitor}, {@code DBSProcedure}.
   *
   * <p>Method under test: {@link SQLGeneratorProcedure#getAllAttributes(DBRProgressMonitor,
   * DBSProcedure)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Collection SQLGeneratorProcedure.getAllAttributes(DBRProgressMonitor, DBSProcedure)"
  })
  public void testGetAllAttributesWithDBRProgressMonitorDBSProcedure() {
    // Arrange
    SQLGeneratorProcedureCall sqlGeneratorProcedureCall = new SQLGeneratorProcedureCall();

    // Act
    Collection<? extends DBSEntityAttribute> actualAllAttributes =
        sqlGeneratorProcedureCall.getAllAttributes(
            new LoggingProgressMonitor(), mock(DBSProcedure.class));

    // Assert
    assertTrue(actualAllAttributes instanceof List);
    assertTrue(actualAllAttributes.isEmpty());
  }

  /**
   * Test {@link SQLGeneratorProcedure#getKeyAttributes(DBRProgressMonitor, DBSProcedure)} with
   * {@code DBRProgressMonitor}, {@code DBSProcedure}.
   *
   * <p>Method under test: {@link SQLGeneratorProcedure#getKeyAttributes(DBRProgressMonitor,
   * DBSProcedure)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Collection SQLGeneratorProcedure.getKeyAttributes(DBRProgressMonitor, DBSProcedure)"
  })
  public void testGetKeyAttributesWithDBRProgressMonitorDBSProcedure() {
    // Arrange
    SQLGeneratorProcedureCall sqlGeneratorProcedureCall = new SQLGeneratorProcedureCall();

    // Act
    Collection<? extends DBSEntityAttribute> actualKeyAttributes =
        sqlGeneratorProcedureCall.getKeyAttributes(
            new LoggingProgressMonitor(), mock(DBSProcedure.class));

    // Assert
    assertTrue(actualKeyAttributes instanceof List);
    assertTrue(actualKeyAttributes.isEmpty());
  }
}
