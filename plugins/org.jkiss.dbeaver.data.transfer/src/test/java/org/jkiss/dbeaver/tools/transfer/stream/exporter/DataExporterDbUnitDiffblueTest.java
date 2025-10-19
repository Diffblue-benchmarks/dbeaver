package org.jkiss.dbeaver.tools.transfer.stream.exporter;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPNamedObject;
import org.jkiss.dbeaver.model.data.DBDAttributeBinding;
import org.jkiss.dbeaver.model.data.DBDAttributeBindingCustom;
import org.jkiss.dbeaver.model.exec.DBCResultSet;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.impl.local.LocalStatement;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.jkiss.dbeaver.tools.transfer.stream.IStreamDataExporterSite;
import org.jkiss.dbeaver.tools.transfer.stream.StreamEntityMapping;
import org.jkiss.dbeaver.tools.transfer.stream.StreamTransferResultSet;
import org.jkiss.dbeaver.tools.transfer.stream.model.StreamDataSource;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DataExporterDbUnitDiffblueTest {
  /**
   * Test {@link DataExporterDbUnit#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code upperCaseTableName} is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterDbUnit#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterDbUnit.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapUpperCaseTableNameIsEmptyString() throws DBException {
    // Arrange
    DataExporterDbUnit dataExporterDbUnit = new DataExporterDbUnit();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("upperCaseTableName", "");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterDbUnit.init(site);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertNull(dataExporterDbUnit.getOutputStream());
    assertNull(dataExporterDbUnit.getWriter());
    assertSame(site, dataExporterDbUnit.getSite());
  }

  /**
   * Test {@link DataExporterDbUnit#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code upperCaseTableName} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterDbUnit#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterDbUnit.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapUpperCaseTableNameIsNull() throws DBException {
    // Arrange
    DataExporterDbUnit dataExporterDbUnit = new DataExporterDbUnit();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("upperCaseTableName", null);

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterDbUnit.init(site);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertNull(dataExporterDbUnit.getOutputStream());
    assertNull(dataExporterDbUnit.getWriter());
    assertSame(site, dataExporterDbUnit.getSite());
  }

  /**
   * Test {@link DataExporterDbUnit#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code upperCaseTableName} is {@code Properties}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterDbUnit#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterDbUnit.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapUpperCaseTableNameIsProperties() throws DBException {
    // Arrange
    DataExporterDbUnit dataExporterDbUnit = new DataExporterDbUnit();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("upperCaseTableName", "Properties");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterDbUnit.init(site);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertNull(dataExporterDbUnit.getOutputStream());
    assertNull(dataExporterDbUnit.getWriter());
    assertSame(site, dataExporterDbUnit.getSite());
  }

  /**
   * Test {@link DataExporterDbUnit#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code upperCaseTableName} is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterDbUnit#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterDbUnit.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapUpperCaseTableNameIsTrue() throws DBException {
    // Arrange
    DataExporterDbUnit dataExporterDbUnit = new DataExporterDbUnit();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("upperCaseTableName", true);

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterDbUnit.init(site);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertNull(dataExporterDbUnit.getOutputStream());
    assertNull(dataExporterDbUnit.getWriter());
    assertSame(site, dataExporterDbUnit.getSite());
  }

  /**
   * Test {@link DataExporterDbUnit#exportHeader(DBCSession)}.
   *
   * <ul>
   *   <li>Given {@link DBPNamedObject} {@link DBPNamedObject#getName()} return {@code Name}.
   *   <li>Then calls {@link DBPNamedObject#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterDbUnit#exportHeader(DBCSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterDbUnit.exportHeader(DBCSession)"})
  public void testExportHeader_givenDBPNamedObjectGetNameReturnName_thenCallsGetName()
      throws IOException, DBException {
    // Arrange
    DBPNamedObject dbpNamedObject = mock(DBPNamedObject.class);
    when(dbpNamedObject.getName()).thenReturn("Name");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getOutputEncoding()).thenReturn("UTF-8");
    when(site.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
    when(site.getProperties()).thenReturn(new HashMap<>());
    when(site.getSource()).thenReturn(dbpNamedObject);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping dataContainer = new StreamEntityMapping(inputFile);
    StreamDataSource dataSource = new StreamDataSource("Input Name");
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntityAttribute vAttribute = new DBVEntityAttribute(entity, null, "Name");

    DBDAttributeBindingCustom dbdAttributeBindingCustom =
        new DBDAttributeBindingCustom(null, dataContainer, dataSource, vAttribute, 1);
    when(site.getAttributes()).thenReturn(new DBDAttributeBinding[] {dbdAttributeBindingCustom});

    DataExporterDbUnit dataExporterDbUnit = new DataExporterDbUnit();
    dataExporterDbUnit.init(site);

    // Act
    dataExporterDbUnit.exportHeader(null);

    // Assert
    verify(dbpNamedObject).getName();
    verify(site).getAttributes();
    verify(site).getOutputEncoding();
    verify(site, atLeast(1)).getProperties();
    verify(site).getSource();
    verify(site).getWriter();
  }

  /**
   * Test {@link DataExporterDbUnit#exportHeader(DBCSession)}.
   *
   * <ul>
   *   <li>Given {@link DBPNamedObject} {@link DBPNamedObject#getName()} return {@code <?xml
   *       version="1.0" encoding="}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterDbUnit#exportHeader(DBCSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterDbUnit.exportHeader(DBCSession)"})
  public void testExportHeader_givenDBPNamedObjectGetNameReturnXmlVersion10Encoding()
      throws IOException, DBException {
    // Arrange
    DBPNamedObject dbpNamedObject = mock(DBPNamedObject.class);
    when(dbpNamedObject.getName()).thenReturn("<?xml version=\"1.0\" encoding=\"");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getOutputEncoding()).thenReturn("UTF-8");
    when(site.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
    when(site.getProperties()).thenReturn(new HashMap<>());
    when(site.getSource()).thenReturn(dbpNamedObject);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping dataContainer = new StreamEntityMapping(inputFile);
    StreamDataSource dataSource = new StreamDataSource("Input Name");
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntityAttribute vAttribute = new DBVEntityAttribute(entity, null, "Name");

    DBDAttributeBindingCustom dbdAttributeBindingCustom =
        new DBDAttributeBindingCustom(null, dataContainer, dataSource, vAttribute, 1);
    when(site.getAttributes()).thenReturn(new DBDAttributeBinding[] {dbdAttributeBindingCustom});

    DataExporterDbUnit dataExporterDbUnit = new DataExporterDbUnit();
    dataExporterDbUnit.init(site);

    // Act
    dataExporterDbUnit.exportHeader(null);

    // Assert
    verify(dbpNamedObject).getName();
    verify(site).getAttributes();
    verify(site).getOutputEncoding();
    verify(site, atLeast(1)).getProperties();
    verify(site).getSource();
    verify(site).getWriter();
  }

  /**
   * Test {@link DataExporterDbUnit#exportRow(DBCSession, DBCResultSet, Object[])}.
   *
   * <ul>
   *   <li>When empty array of {@link Object}.
   *   <li>Then calls {@link IStreamDataExporterSite#getProperties()}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterDbUnit#exportRow(DBCSession, DBCResultSet, Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterDbUnit.exportRow(DBCSession, DBCResultSet, Object[])"})
  public void testExportRow_whenEmptyArrayOfObject_thenCallsGetProperties()
      throws IOException, DBException {
    // Arrange
    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
    when(site.getProperties()).thenReturn(new HashMap<>());

    DataExporterDbUnit dataExporterDbUnit = new DataExporterDbUnit();
    dataExporterDbUnit.init(site);
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferResultSet resultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile));

    // Act
    dataExporterDbUnit.exportRow(null, resultSet, new Object[] {});

    // Assert
    verify(site, atLeast(1)).getProperties();
    verify(site).getWriter();
  }

  /**
   * Test {@link DataExporterDbUnit#exportFooter(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then calls {@link IStreamDataExporterSite#getProperties()}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterDbUnit#exportFooter(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterDbUnit.exportFooter(DBRProgressMonitor)"})
  public void testExportFooter_thenCallsGetProperties() throws DBException {
    // Arrange
    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
    when(site.getProperties()).thenReturn(new HashMap<>());

    DataExporterDbUnit dataExporterDbUnit = new DataExporterDbUnit();
    dataExporterDbUnit.init(site);

    // Act
    dataExporterDbUnit.exportFooter(new LoggingProgressMonitor());

    // Assert
    verify(site, atLeast(1)).getProperties();
    verify(site).getWriter();
  }

  /**
   * Test new {@link DataExporterDbUnit} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link DataExporterDbUnit}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterDbUnit.<init>()"})
  public void testNewDataExporterDbUnit() {
    // Arrange, Act and Assert
    assertNull(new DataExporterDbUnit().getSite());
  }
}
