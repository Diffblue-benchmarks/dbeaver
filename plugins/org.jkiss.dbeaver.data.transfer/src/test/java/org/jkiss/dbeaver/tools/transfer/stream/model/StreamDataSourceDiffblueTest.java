package org.jkiss.dbeaver.tools.transfer.stream.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPDataSourceInfo;
import org.jkiss.dbeaver.model.data.DBDFormatSettings;
import org.jkiss.dbeaver.model.data.DBDValueHandler;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.impl.SimpleExclusiveLock;
import org.jkiss.dbeaver.model.impl.data.DefaultValueHandler;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSInstance;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.struct.DBSTypedObject;
import org.jkiss.dbeaver.tools.transfer.stream.StreamDataImporterColumnInfo;
import org.jkiss.dbeaver.tools.transfer.stream.StreamEntityMapping;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class StreamDataSourceDiffblueTest {
  /**
   * Test {@link StreamDataSource#StreamDataSource(String)}.
   *
   * <p>Method under test: {@link StreamDataSource#StreamDataSource(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamDataSource.<init>(String)"})
  public void testNewStreamDataSource() {
    // Arrange and Act
    StreamDataSource actualStreamDataSource = new StreamDataSource("Input Name");

    // Assert
    Collection<? extends DBSInstance> availableInstances =
        actualStreamDataSource.getAvailableInstances();
    assertEquals(1, availableInstances.size());
    assertTrue(availableInstances instanceof List);
    assertTrue(actualStreamDataSource.getExclusiveLock() instanceof SimpleExclusiveLock);
    DBPDataSourceContainer container = actualStreamDataSource.getContainer();
    assertTrue(container instanceof StreamDataSourceContainer);
    assertTrue(actualStreamDataSource.getSQLDialect() instanceof StreamDataSourceDialect);
    assertTrue(actualStreamDataSource.getInfo() instanceof StreamDataSourceInfo);
    assertEquals("Input Name", actualStreamDataSource.getName());
    assertNull(actualStreamDataSource.getDescription());
    assertEquals(0, actualStreamDataSource.getAllContexts().length);
    assertFalse(actualStreamDataSource.isConnectionRefreshing());
    assertTrue(actualStreamDataSource.getContextAttributes().isEmpty());
    assertTrue(actualStreamDataSource.isPersisted());
    assertSame(container, actualStreamDataSource.getParentObject());
  }

  /**
   * Test {@link StreamDataSource#StreamDataSource(StreamDataSourceContainer)}.
   *
   * <ul>
   *   <li>Then return AvailableInstances size is one.
   * </ul>
   *
   * <p>Method under test: {@link StreamDataSource#StreamDataSource(StreamDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamDataSource.<init>(StreamDataSourceContainer)"})
  public void testNewStreamDataSource_thenReturnAvailableInstancesSizeIsOne() {
    // Arrange
    StreamDataSourceContainer container = new StreamDataSourceContainer("Name");

    // Act
    StreamDataSource actualStreamDataSource = new StreamDataSource(container);

    // Assert
    Collection<? extends DBSInstance> availableInstances =
        actualStreamDataSource.getAvailableInstances();
    assertEquals(1, availableInstances.size());
    assertTrue(availableInstances instanceof List);
    assertTrue(actualStreamDataSource.getExclusiveLock() instanceof SimpleExclusiveLock);
    DBPDataSourceContainer container2 = actualStreamDataSource.getContainer();
    assertTrue(container2 instanceof StreamDataSourceContainer);
    assertTrue(actualStreamDataSource.getSQLDialect() instanceof StreamDataSourceDialect);
    assertTrue(actualStreamDataSource.getInfo() instanceof StreamDataSourceInfo);
    assertEquals("Name", actualStreamDataSource.getName());
    assertNull(actualStreamDataSource.getDescription());
    assertEquals(0, actualStreamDataSource.getAllContexts().length);
    assertFalse(actualStreamDataSource.isConnectionRefreshing());
    assertTrue(actualStreamDataSource.getContextAttributes().isEmpty());
    assertTrue(actualStreamDataSource.isPersisted());
    assertSame(container, container2);
    assertSame(container, actualStreamDataSource.getParentObject());
  }

  /**
   * Test {@link StreamDataSource#getInfo()}.
   *
   * <p>Method under test: {@link StreamDataSource#getInfo()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSourceInfo StreamDataSource.getInfo()"})
  public void testGetInfo() {
    // Arrange and Act
    DBPDataSourceInfo actualInfo = new StreamDataSource("Input Name").getInfo();

    // Assert
    assertTrue(actualInfo instanceof StreamDataSourceInfo);
    assertEquals("1.0", actualInfo.getDatabaseProductVersion());
    assertEquals("1.0", actualInfo.getDriverVersion());
    assertEquals("stream", actualInfo.getDatabaseProductName());
    assertEquals("stream", actualInfo.getDriverName());
    assertNull(actualInfo.getCatalogTerm());
    assertNull(actualInfo.getProcedureTerm());
    assertNull(actualInfo.getSchemaTerm());
    assertNull(actualInfo.getSupportedTransactionsIsolation());
    assertNull(actualInfo.getDatabaseProductDetails());
    assertEquals(0, actualInfo.getSupportedObjectTypes().length);
    assertFalse(actualInfo.isDynamicMetadata());
    assertFalse(actualInfo.isMultipleResultsFailsOnMaxRows());
    assertFalse(actualInfo.isMultipleResultsFetchBroken());
    assertFalse(actualInfo.isReadOnlyData());
    assertFalse(actualInfo.isReadOnlyMetaData());
  }

  /**
   * Test {@link StreamDataSource#openIsolatedContext(DBRProgressMonitor, String,
   * DBCExecutionContext)}.
   *
   * <p>Method under test: {@link StreamDataSource#openIsolatedContext(DBRProgressMonitor, String,
   * DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "StreamExecutionContext StreamDataSource.openIsolatedContext(DBRProgressMonitor, String, DBCExecutionContext)"
  })
  public void testOpenIsolatedContext() throws DBException {
    // Arrange
    StreamDataSource streamDataSource = new StreamDataSource("Input Name");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    StreamExecutionContext actualOpenIsolatedContextResult =
        streamDataSource.openIsolatedContext(
            monitor,
            "Purpose",
            new StreamExecutionContext(new StreamDataSource("Input Name"), "Purpose"));

    // Assert
    assertEquals("Purpose", actualOpenIsolatedContextResult.getContextName());
    assertNull(actualOpenIsolatedContextResult.getContextDefaults());
    assertTrue(actualOpenIsolatedContextResult.getContextAttributes().isEmpty());
    assertTrue(actualOpenIsolatedContextResult.isConnected());
    assertSame(streamDataSource, actualOpenIsolatedContextResult.getDataSource());
    assertSame(streamDataSource, actualOpenIsolatedContextResult.getOwnerInstance());
  }

  /**
   * Test {@link StreamDataSource#getValueHandler(DBPDataSource, DBDFormatSettings,
   * DBSTypedObject)}.
   *
   * <p>Method under test: {@link StreamDataSource#getValueHandler(DBPDataSource, DBDFormatSettings,
   * DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBDValueHandler StreamDataSource.getValueHandler(DBPDataSource, DBDFormatSettings, DBSTypedObject)"
  })
  public void testGetValueHandler() {
    // Arrange
    StreamDataSource streamDataSource = new StreamDataSource("Input Name");
    StreamDataSource dataSource = new StreamDataSource("Input Name");
    StreamDataSourceContainer preferences = new StreamDataSourceContainer("Name");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamDataImporterColumnInfo typedObject =
        new StreamDataImporterColumnInfo(
            new StreamEntityMapping(inputFile),
            1,
            "Column Name",
            "Type Name",
            3,
            DBPDataKind.BOOLEAN);

    // Act
    DBDValueHandler actualValueHandler =
        streamDataSource.getValueHandler(dataSource, preferences, typedObject);

    // Assert
    assertTrue(actualValueHandler instanceof DefaultValueHandler);
    DBPDataSource dataSource2 = typedObject.getDataSource();
    assertTrue(dataSource2 instanceof StreamDataSource);
    DBPDataSourceContainer container = dataSource2.getContainer();
    assertTrue(container instanceof StreamDataSourceContainer);
    DBPDataSourceContainer container2 = dataSource.getContainer();
    assertTrue(container2 instanceof StreamDataSourceContainer);
    assertNull(actualValueHandler.getComparator());
    DefaultValueHandler defaultValueHandler = ((DefaultValueHandler) actualValueHandler).INSTANCE;
    assertSame(defaultValueHandler, container.getDefaultValueHandler());
    assertSame(defaultValueHandler, container2.getDefaultValueHandler());
    assertSame(defaultValueHandler, actualValueHandler);
    assertSame(defaultValueHandler, preferences.getDefaultValueHandler());
  }

  /**
   * Test {@link StreamDataSource#getChildren(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link StreamDataSource#getChildren(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection StreamDataSource.getChildren(DBRProgressMonitor)"})
  public void testGetChildren() throws DBException {
    // Arrange
    StreamDataSource streamDataSource = new StreamDataSource("Input Name");

    // Act and Assert
    assertNull(streamDataSource.getChildren(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link StreamDataSource#getChild(DBRProgressMonitor, String)}.
   *
   * <p>Method under test: {@link StreamDataSource#getChild(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject StreamDataSource.getChild(DBRProgressMonitor, String)"})
  public void testGetChild() throws DBException {
    // Arrange
    StreamDataSource streamDataSource = new StreamDataSource("Input Name");

    // Act and Assert
    assertNull(streamDataSource.getChild(new LoggingProgressMonitor(), "Child Name"));
  }

  /**
   * Test {@link StreamDataSource#getPrimaryChildType(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link StreamDataSource#getPrimaryChildType(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class StreamDataSource.getPrimaryChildType(DBRProgressMonitor)"})
  public void testGetPrimaryChildType() throws DBException {
    // Arrange
    StreamDataSource streamDataSource = new StreamDataSource("Input Name");

    // Act
    Class<? extends DBSObject> actualPrimaryChildType =
        streamDataSource.getPrimaryChildType(new LoggingProgressMonitor());

    // Assert
    Class<DBSObject> expectedPrimaryChildType = DBSObject.class;
    assertEquals(expectedPrimaryChildType, actualPrimaryChildType);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link StreamDataSource#cacheStructure(DBRProgressMonitor, int)}
   *   <li>{@link StreamDataSource#initialize(DBRProgressMonitor)}
   *   <li>{@link StreamDataSource#getSQLDialect()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamDataSource.cacheStructure(DBRProgressMonitor, int)",
    "org.jkiss.dbeaver.model.sql.SQLDialect StreamDataSource.getSQLDialect()",
    "void StreamDataSource.initialize(DBRProgressMonitor)"
  })
  public void testGettersAndSetters() throws DBException {
    // Arrange
    StreamDataSource streamDataSource = new StreamDataSource("Input Name");

    // Act
    streamDataSource.cacheStructure(new LoggingProgressMonitor(), 1);
    streamDataSource.initialize(new LoggingProgressMonitor());

    // Assert
    assertTrue(streamDataSource.getSQLDialect() instanceof StreamDataSourceDialect);
  }
}
