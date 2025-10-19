package org.jkiss.dbeaver.model.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCFeatureNotSupportedException;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.rdb.DBSCatalog;
import org.jkiss.dbeaver.model.struct.rdb.DBSSchema;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class VoidExecutionContextDefaultsDiffblueTest {
  /**
   * Test {@link VoidExecutionContextDefaults#supportsCatalogChange()}.
   *
   * <p>Method under test: {@link VoidExecutionContextDefaults#supportsCatalogChange()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean VoidExecutionContextDefaults.supportsCatalogChange()"})
  public void testSupportsCatalogChange() {
    // Arrange, Act and Assert
    assertFalse(new VoidExecutionContextDefaults().supportsCatalogChange());
  }

  /**
   * Test {@link VoidExecutionContextDefaults#supportsSchemaChange()}.
   *
   * <p>Method under test: {@link VoidExecutionContextDefaults#supportsSchemaChange()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean VoidExecutionContextDefaults.supportsSchemaChange()"})
  public void testSupportsSchemaChange() {
    // Arrange, Act and Assert
    assertFalse(new VoidExecutionContextDefaults().supportsSchemaChange());
  }

  /**
   * Test {@link VoidExecutionContextDefaults#setDefaultCatalog(DBRProgressMonitor, DBSCatalog,
   * DBSSchema)} with {@code DBRProgressMonitor}, {@code DBSCatalog}, {@code DBSSchema}.
   *
   * <p>Method under test: {@link VoidExecutionContextDefaults#setDefaultCatalog(DBRProgressMonitor,
   * DBSCatalog, DBSSchema)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void VoidExecutionContextDefaults.setDefaultCatalog(DBRProgressMonitor, DBSCatalog, DBSSchema)"
  })
  public void testSetDefaultCatalogWithDBRProgressMonitorDBSCatalogDBSSchema() throws DBCException {
    // Arrange
    VoidExecutionContextDefaults voidExecutionContextDefaults = new VoidExecutionContextDefaults();

    // Act and Assert
    assertThrows(
        DBCFeatureNotSupportedException.class,
        () ->
            voidExecutionContextDefaults.setDefaultCatalog(
                new LoggingProgressMonitor(), mock(DBSCatalog.class), mock(DBSSchema.class)));
  }

  /**
   * Test {@link VoidExecutionContextDefaults#setDefaultSchema(DBRProgressMonitor, DBSSchema)} with
   * {@code DBRProgressMonitor}, {@code DBSSchema}.
   *
   * <p>Method under test: {@link VoidExecutionContextDefaults#setDefaultSchema(DBRProgressMonitor,
   * DBSSchema)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void VoidExecutionContextDefaults.setDefaultSchema(DBRProgressMonitor, DBSSchema)"
  })
  public void testSetDefaultSchemaWithDBRProgressMonitorDBSSchema() throws DBCException {
    // Arrange
    VoidExecutionContextDefaults voidExecutionContextDefaults = new VoidExecutionContextDefaults();

    // Act and Assert
    assertThrows(
        DBCFeatureNotSupportedException.class,
        () ->
            voidExecutionContextDefaults.setDefaultSchema(
                new LoggingProgressMonitor(), mock(DBSSchema.class)));
  }

  /**
   * Test {@link VoidExecutionContextDefaults#refreshDefaults(DBRProgressMonitor, boolean)}.
   *
   * <p>Method under test: {@link VoidExecutionContextDefaults#refreshDefaults(DBRProgressMonitor,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean VoidExecutionContextDefaults.refreshDefaults(DBRProgressMonitor, boolean)"
  })
  public void testRefreshDefaults() throws DBException {
    // Arrange
    VoidExecutionContextDefaults voidExecutionContextDefaults = new VoidExecutionContextDefaults();

    // Act and Assert
    assertThrows(
        DBCFeatureNotSupportedException.class,
        () -> voidExecutionContextDefaults.refreshDefaults(new LoggingProgressMonitor(), true));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link VoidExecutionContextDefaults}
   *   <li>{@link VoidExecutionContextDefaults#getDefaultCatalog()}
   *   <li>{@link VoidExecutionContextDefaults#getDefaultSchema()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void VoidExecutionContextDefaults.<init>()",
    "DBSCatalog VoidExecutionContextDefaults.getDefaultCatalog()",
    "DBSSchema VoidExecutionContextDefaults.getDefaultSchema()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    VoidExecutionContextDefaults actualVoidExecutionContextDefaults =
        new VoidExecutionContextDefaults();
    DBSCatalog actualDefaultCatalog = actualVoidExecutionContextDefaults.getDefaultCatalog();

    // Assert
    assertNull(actualDefaultCatalog);
    assertNull(actualVoidExecutionContextDefaults.getDefaultSchema());
  }
}
