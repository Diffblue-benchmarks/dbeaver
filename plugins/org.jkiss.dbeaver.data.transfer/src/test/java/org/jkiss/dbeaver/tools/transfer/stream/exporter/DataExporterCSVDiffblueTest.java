package org.jkiss.dbeaver.tools.transfer.stream.exporter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.data.DBDAttributeBinding;
import org.jkiss.dbeaver.model.data.DBDAttributeBindingCustom;
import org.jkiss.dbeaver.model.data.DBDDisplayFormat;
import org.jkiss.dbeaver.model.exec.DBCResultSet;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.impl.local.LocalStatement;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.jkiss.dbeaver.tools.transfer.stream.IStreamDataExporterSite;
import org.jkiss.dbeaver.tools.transfer.stream.StreamEntityMapping;
import org.jkiss.dbeaver.tools.transfer.stream.StreamTransferResultSet;
import org.jkiss.dbeaver.tools.transfer.stream.model.StreamDataSource;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DataExporterCSVDiffblueTest {
  /**
   * Test {@link DataExporterCSV#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link DataExporterCSV} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link DataExporterCSV#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterCSV.init(IStreamDataExporterSite)"})
  public void testInit_givenDataExporterCSV() throws DBException {
    // Arrange
    DataExporterCSV dataExporterCSV = new DataExporterCSV();

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(new HashMap<>());

    // Act
    dataExporterCSV.init(site);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertNull(dataExporterCSV.getOutputStream());
    assertNull(dataExporterCSV.getWriter());
    assertSame(site, dataExporterCSV.getSite());
  }

  /**
   * Test {@link DataExporterCSV#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code delimiter} is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterCSV#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterCSV.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapDelimiterIs42() throws DBException {
    // Arrange
    DataExporterCSV dataExporterCSV = new DataExporterCSV();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("delimiter", "42");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterCSV.init(site);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertNull(dataExporterCSV.getOutputStream());
    assertNull(dataExporterCSV.getWriter());
    assertSame(site, dataExporterCSV.getSite());
  }

  /**
   * Test {@link DataExporterCSV#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Then calls {@link IStreamDataExporterSite#getOutputFile()}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterCSV#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterCSV.init(IStreamDataExporterSite)"})
  public void testInit_thenCallsGetOutputFile() throws DBException {
    // Arrange
    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getOutputFile())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

    DataExporterCSV dataExporterCSV = new DataExporterCSV();
    dataExporterCSV.importData(site);

    IStreamDataExporterSite site2 = mock(IStreamDataExporterSite.class);
    when(site2.getProperties()).thenReturn(new HashMap<>());

    // Act
    dataExporterCSV.init(site2);

    // Assert
    verify(site).getOutputFile();
    verify(site2, atLeast(1)).getProperties();
    assertNull(dataExporterCSV.getOutputStream());
    assertNull(dataExporterCSV.getWriter());
    assertSame(site2, dataExporterCSV.getSite());
  }

  /**
   * Test {@link DataExporterCSV#getValueExportFormat(DBDAttributeBinding)}.
   *
   * <ul>
   *   <li>Given {@code NUMERIC}.
   *   <li>Then return {@code NATIVE}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterCSV#getValueExportFormat(DBDAttributeBinding)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDDisplayFormat DataExporterCSV.getValueExportFormat(DBDAttributeBinding)"})
  public void testGetValueExportFormat_givenNumeric_thenReturnNative() throws DBException {
    // Arrange
    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(new HashMap<>());

    DataExporterCSV dataExporterCSV = new DataExporterCSV();
    dataExporterCSV.init(site);
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityAttribute vAttribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    vAttribute.setDataKind(DBPDataKind.NUMERIC);
    DBDAttributeBindingCustom parent = mock(DBDAttributeBindingCustom.class);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping dataContainer = new StreamEntityMapping(inputFile);

    DBDAttributeBindingCustom column =
        new DBDAttributeBindingCustom(
            parent, dataContainer, new StreamDataSource("Input Name"), vAttribute, 1);

    // Act
    DBDDisplayFormat actualValueExportFormat = dataExporterCSV.getValueExportFormat(column);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertEquals(DBDDisplayFormat.NATIVE, actualValueExportFormat);
  }

  /**
   * Test {@link DataExporterCSV#getValueExportFormat(DBDAttributeBinding)}.
   *
   * <ul>
   *   <li>Then return {@code UI}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterCSV#getValueExportFormat(DBDAttributeBinding)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDDisplayFormat DataExporterCSV.getValueExportFormat(DBDAttributeBinding)"})
  public void testGetValueExportFormat_thenReturnUi() throws DBException {
    // Arrange
    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(new HashMap<>());
    when(site.getExportFormat()).thenReturn(DBDDisplayFormat.UI);

    DataExporterCSV dataExporterCSV = new DataExporterCSV();
    dataExporterCSV.init(site);
    DBDAttributeBindingCustom parent = mock(DBDAttributeBindingCustom.class);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping dataContainer = new StreamEntityMapping(inputFile);
    StreamDataSource dataSource = new StreamDataSource("Input Name");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute vAttribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    DBDAttributeBindingCustom column =
        new DBDAttributeBindingCustom(parent, dataContainer, dataSource, vAttribute, 1);

    // Act
    DBDDisplayFormat actualValueExportFormat = dataExporterCSV.getValueExportFormat(column);

    // Assert
    verify(site).getExportFormat();
    verify(site, atLeast(1)).getProperties();
    assertEquals(DBDDisplayFormat.UI, actualValueExportFormat);
  }

  /**
   * Test {@link DataExporterCSV#exportHeader(DBCSession)}.
   *
   * <p>Method under test: {@link DataExporterCSV#exportHeader(DBCSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterCSV.exportHeader(DBCSession)"})
  public void testExportHeader() throws IOException, DBException {
    // Arrange
    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
    when(site.getProperties()).thenReturn(new HashMap<>());
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping dataContainer = new StreamEntityMapping(inputFile);
    StreamDataSource dataSource = new StreamDataSource("Input Name");
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntityAttribute vAttribute = new DBVEntityAttribute(entity, null, "Name");

    DBDAttributeBindingCustom parent =
        new DBDAttributeBindingCustom(null, dataContainer, dataSource, vAttribute, 1);
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping dataContainer2 = new StreamEntityMapping(inputFile2);
    StreamDataSource dataSource2 = new StreamDataSource("Input Name");
    DBVEntity entity2 =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntityAttribute vAttribute2 = new DBVEntityAttribute(entity2, null, "Name");

    DBDAttributeBindingCustom dbdAttributeBindingCustom =
        new DBDAttributeBindingCustom(parent, dataContainer2, dataSource2, vAttribute2, 1);
    when(site.getAttributes()).thenReturn(new DBDAttributeBinding[] {dbdAttributeBindingCustom});

    DataExporterCSV dataExporterCSV = new DataExporterCSV();
    dataExporterCSV.init(site);

    // Act
    dataExporterCSV.exportHeader(null);

    // Assert
    verify(site).getAttributes();
    verify(site, atLeast(1)).getProperties();
    verify(site, atLeast(1)).getWriter();
  }

  /**
   * Test {@link DataExporterCSV#exportHeader(DBCSession)}.
   *
   * <p>Method under test: {@link DataExporterCSV#exportHeader(DBCSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterCSV.exportHeader(DBCSession)"})
  public void testExportHeader2() throws IOException, DBException {
    // Arrange
    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
    when(site.getProperties()).thenReturn(new HashMap<>());
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping dataContainer = new StreamEntityMapping(inputFile);
    StreamDataSource dataSource = new StreamDataSource("Input Name");
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntityAttribute vAttribute = new DBVEntityAttribute(entity, null, "");

    DBDAttributeBindingCustom dbdAttributeBindingCustom =
        new DBDAttributeBindingCustom(null, dataContainer, dataSource, vAttribute, 1);
    when(site.getAttributes()).thenReturn(new DBDAttributeBinding[] {dbdAttributeBindingCustom});

    DataExporterCSV dataExporterCSV = new DataExporterCSV();
    dataExporterCSV.init(site);

    // Act
    dataExporterCSV.exportHeader(null);

    // Assert
    verify(site).getAttributes();
    verify(site, atLeast(1)).getProperties();
    verify(site, atLeast(1)).getWriter();
  }

  /**
   * Test {@link DataExporterCSV#exportHeader(DBCSession)}.
   *
   * <p>Method under test: {@link DataExporterCSV#exportHeader(DBCSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterCSV.exportHeader(DBCSession)"})
  public void testExportHeader3() throws IOException, DBException {
    // Arrange
    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(new HashMap<>());
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping dataContainer = new StreamEntityMapping(inputFile);
    StreamDataSource dataSource = new StreamDataSource("Input Name");
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntityAttribute vAttribute = new DBVEntityAttribute(entity, null, "Name");

    DBDAttributeBindingCustom dbdAttributeBindingCustom =
        new DBDAttributeBindingCustom(null, dataContainer, dataSource, vAttribute, 1);
    when(site.getAttributes()).thenReturn(new DBDAttributeBinding[] {dbdAttributeBindingCustom});

    IStreamDataExporterSite site2 = mock(IStreamDataExporterSite.class);
    when(site2.getOutputFile())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

    DataExporterCSV dataExporterCSV = new DataExporterCSV();
    dataExporterCSV.importData(site2);
    dataExporterCSV.init(site);

    // Act
    dataExporterCSV.exportHeader(null);

    // Assert
    verify(site).getAttributes();
    verify(site2).getOutputFile();
    verify(site, atLeast(1)).getProperties();
  }

  /**
   * Test {@link DataExporterCSV#exportHeader(DBCSession)}.
   *
   * <ul>
   *   <li>Then calls {@link IStreamDataExporterSite#getWriter()}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterCSV#exportHeader(DBCSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterCSV.exportHeader(DBCSession)"})
  public void testExportHeader_thenCallsGetWriter() throws IOException, DBException {
    // Arrange
    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
    when(site.getProperties()).thenReturn(new HashMap<>());
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping dataContainer = new StreamEntityMapping(inputFile);
    StreamDataSource dataSource = new StreamDataSource("Input Name");
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntityAttribute vAttribute = new DBVEntityAttribute(entity, null, "Name");

    DBDAttributeBindingCustom dbdAttributeBindingCustom =
        new DBDAttributeBindingCustom(null, dataContainer, dataSource, vAttribute, 1);
    when(site.getAttributes()).thenReturn(new DBDAttributeBinding[] {dbdAttributeBindingCustom});

    DataExporterCSV dataExporterCSV = new DataExporterCSV();
    dataExporterCSV.init(site);

    // Act
    dataExporterCSV.exportHeader(null);

    // Assert
    verify(site).getAttributes();
    verify(site, atLeast(1)).getProperties();
    verify(site, atLeast(1)).getWriter();
  }

  /**
   * Test {@link DataExporterCSV#exportHeader(DBCSession)}.
   *
   * <ul>
   *   <li>Then calls {@link DBDAttributeBindingCustom#isPseudoAttribute()}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterCSV#exportHeader(DBCSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterCSV.exportHeader(DBCSession)"})
  public void testExportHeader_thenCallsIsPseudoAttribute() throws IOException, DBException {
    // Arrange
    DBDAttributeBindingCustom parent = mock(DBDAttributeBindingCustom.class);
    when(parent.isPseudoAttribute()).thenReturn(true);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping dataContainer = new StreamEntityMapping(inputFile);
    StreamDataSource dataSource = new StreamDataSource("Input Name");
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntityAttribute vAttribute = new DBVEntityAttribute(entity, null, "Name");

    DBDAttributeBindingCustom dbdAttributeBindingCustom =
        new DBDAttributeBindingCustom(null, dataContainer, dataSource, vAttribute, 1);
    when(parent.getParentObject()).thenReturn(dbdAttributeBindingCustom);
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping dataContainer2 = new StreamEntityMapping(inputFile2);
    StreamDataSource dataSource2 = new StreamDataSource("Input Name");
    DBVEntity entity2 =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntityAttribute vAttribute2 = new DBVEntityAttribute(entity2, null, "Name");

    DBDAttributeBindingCustom dbdAttributeBindingCustom2 =
        new DBDAttributeBindingCustom(parent, dataContainer2, dataSource2, vAttribute2, 1);

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
    when(site.getProperties()).thenReturn(new HashMap<>());
    when(site.getAttributes()).thenReturn(new DBDAttributeBinding[] {dbdAttributeBindingCustom2});

    IStreamDataExporterSite site2 = mock(IStreamDataExporterSite.class);
    when(site2.getOutputFile()).thenReturn(null);

    DataExporterCSV dataExporterCSV = new DataExporterCSV();
    dataExporterCSV.importData(site2);
    dataExporterCSV.init(site);
    System.getProperty("java.io.tmpdir");

    // Act
    dataExporterCSV.exportHeader(null);

    // Assert
    verify(parent).isPseudoAttribute();
    verify(parent).getParentObject();
    verify(site).getAttributes();
    verify(site2).getOutputFile();
    verify(site, atLeast(1)).getProperties();
    verify(site, atLeast(1)).getWriter();
  }

  /**
   * Test {@link DataExporterCSV#exportRow(DBCSession, DBCResultSet, Object[])}.
   *
   * <ul>
   *   <li>Then calls {@link IStreamDataExporterSite#getProperties()}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterCSV#exportRow(DBCSession, DBCResultSet, Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterCSV.exportRow(DBCSession, DBCResultSet, Object[])"})
  public void testExportRow_thenCallsGetProperties() throws IOException, DBException {
    // Arrange
    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
    when(site.getProperties()).thenReturn(new HashMap<>());

    DataExporterCSV dataExporterCSV = new DataExporterCSV();
    dataExporterCSV.init(site);
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferResultSet resultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile));

    // Act
    dataExporterCSV.exportRow(null, resultSet, new Object[] {});

    // Assert
    verify(site, atLeast(1)).getProperties();
    verify(site).getWriter();
  }

  /**
   * Test {@link DataExporterCSV#importData(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link IStreamDataExporterSite} {@link IStreamDataExporterSite#getOutputFile()}
   *       return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterCSV#importData(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterCSV.importData(IStreamDataExporterSite)"})
  public void testImportData_givenNull_whenIStreamDataExporterSiteGetOutputFileReturnNull() {
    // Arrange
    DataExporterCSV dataExporterCSV = new DataExporterCSV();

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getOutputFile()).thenReturn(null);

    // Act
    dataExporterCSV.importData(site);

    // Assert
    verify(site).getOutputFile();
  }

  /**
   * Test {@link DataExporterCSV#importData(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given Property is {@code java.io.tmpdir} is array of {@link String} with {@code
   *       test.txt}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterCSV#importData(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterCSV.importData(IStreamDataExporterSite)"})
  public void testImportData_givenPropertyIsJavaIoTmpdirIsArrayOfStringWithTestTxt() {
    // Arrange
    DataExporterCSV dataExporterCSV = new DataExporterCSV();

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getOutputFile())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

    // Act
    dataExporterCSV.importData(site);

    // Assert
    verify(site).getOutputFile();
  }

  /**
   * Test {@link DataExporterCSV#shouldTruncateOutputFileBeforeExport()}.
   *
   * <p>Method under test: {@link DataExporterCSV#shouldTruncateOutputFileBeforeExport()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DataExporterCSV.shouldTruncateOutputFileBeforeExport()"})
  public void testShouldTruncateOutputFileBeforeExport() {
    // Arrange, Act and Assert
    assertFalse(new DataExporterCSV().shouldTruncateOutputFileBeforeExport());
  }

  /**
   * Test new {@link DataExporterCSV} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link DataExporterCSV}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterCSV.<init>()"})
  public void testNewDataExporterCSV() {
    // Arrange, Act and Assert
    assertNull(new DataExporterCSV().getSite());
  }
}
