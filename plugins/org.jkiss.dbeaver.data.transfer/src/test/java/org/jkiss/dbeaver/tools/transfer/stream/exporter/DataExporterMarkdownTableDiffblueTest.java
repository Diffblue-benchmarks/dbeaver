package org.jkiss.dbeaver.tools.transfer.stream.exporter;

import static org.junit.Assert.assertEquals;
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

public class DataExporterMarkdownTableDiffblueTest {
  /**
   * Test {@link DataExporterMarkdownTable#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code nullString} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterMarkdownTable#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterMarkdownTable.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapNullStringIsNull() throws DBException {
    // Arrange
    DataExporterMarkdownTable dataExporterMarkdownTable = new DataExporterMarkdownTable();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("nullString", null);

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterMarkdownTable.init(site);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertNull(dataExporterMarkdownTable.getOutputStream());
    assertNull(dataExporterMarkdownTable.getWriter());
    assertSame(site, dataExporterMarkdownTable.getSite());
  }

  /**
   * Test {@link DataExporterMarkdownTable#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code nullString} is {@code Properties}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterMarkdownTable#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterMarkdownTable.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapNullStringIsProperties() throws DBException {
    // Arrange
    DataExporterMarkdownTable dataExporterMarkdownTable = new DataExporterMarkdownTable();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("nullString", "Properties");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterMarkdownTable.init(site);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertNull(dataExporterMarkdownTable.getOutputStream());
    assertNull(dataExporterMarkdownTable.getWriter());
    assertSame(site, dataExporterMarkdownTable.getSite());
  }

  /**
   * Test {@link DataExporterMarkdownTable#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code showHeaderSeparator} is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterMarkdownTable#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterMarkdownTable.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapShowHeaderSeparatorIsEmptyString() throws DBException {
    // Arrange
    DataExporterMarkdownTable dataExporterMarkdownTable = new DataExporterMarkdownTable();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("showHeaderSeparator", "");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterMarkdownTable.init(site);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertNull(dataExporterMarkdownTable.getOutputStream());
    assertNull(dataExporterMarkdownTable.getWriter());
    assertSame(site, dataExporterMarkdownTable.getSite());
  }

  /**
   * Test {@link DataExporterMarkdownTable#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code showHeaderSeparator} is {@code Properties}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterMarkdownTable#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterMarkdownTable.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapShowHeaderSeparatorIsProperties() throws DBException {
    // Arrange
    DataExporterMarkdownTable dataExporterMarkdownTable = new DataExporterMarkdownTable();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("showHeaderSeparator", "Properties");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterMarkdownTable.init(site);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertNull(dataExporterMarkdownTable.getOutputStream());
    assertNull(dataExporterMarkdownTable.getWriter());
    assertSame(site, dataExporterMarkdownTable.getSite());
  }

  /**
   * Test {@link DataExporterMarkdownTable#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code showHeaderSeparator} is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterMarkdownTable#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterMarkdownTable.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapShowHeaderSeparatorIsTrue() throws DBException {
    // Arrange
    DataExporterMarkdownTable dataExporterMarkdownTable = new DataExporterMarkdownTable();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("showHeaderSeparator", true);

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterMarkdownTable.init(site);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertNull(dataExporterMarkdownTable.getOutputStream());
    assertNull(dataExporterMarkdownTable.getWriter());
    assertSame(site, dataExporterMarkdownTable.getSite());
  }

  /**
   * Test {@link DataExporterMarkdownTable#getValueExportFormat(DBDAttributeBinding)}.
   *
   * <ul>
   *   <li>Given {@code NUMERIC}.
   *   <li>Then return {@code NATIVE}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DataExporterMarkdownTable#getValueExportFormat(DBDAttributeBinding)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBDDisplayFormat DataExporterMarkdownTable.getValueExportFormat(DBDAttributeBinding)"
  })
  public void testGetValueExportFormat_givenNumeric_thenReturnNative() throws DBException {
    // Arrange
    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(new HashMap<>());

    DataExporterMarkdownTable dataExporterMarkdownTable = new DataExporterMarkdownTable();
    dataExporterMarkdownTable.init(site);
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
    DBDDisplayFormat actualValueExportFormat =
        dataExporterMarkdownTable.getValueExportFormat(column);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertEquals(DBDDisplayFormat.NATIVE, actualValueExportFormat);
  }

  /**
   * Test {@link DataExporterMarkdownTable#getValueExportFormat(DBDAttributeBinding)}.
   *
   * <ul>
   *   <li>Then return {@code UI}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DataExporterMarkdownTable#getValueExportFormat(DBDAttributeBinding)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBDDisplayFormat DataExporterMarkdownTable.getValueExportFormat(DBDAttributeBinding)"
  })
  public void testGetValueExportFormat_thenReturnUi() throws DBException {
    // Arrange
    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(new HashMap<>());
    when(site.getExportFormat()).thenReturn(DBDDisplayFormat.UI);

    DataExporterMarkdownTable dataExporterMarkdownTable = new DataExporterMarkdownTable();
    dataExporterMarkdownTable.init(site);
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
    DBDDisplayFormat actualValueExportFormat =
        dataExporterMarkdownTable.getValueExportFormat(column);

    // Assert
    verify(site).getExportFormat();
    verify(site, atLeast(1)).getProperties();
    assertEquals(DBDDisplayFormat.UI, actualValueExportFormat);
  }

  /**
   * Test {@link DataExporterMarkdownTable#exportHeader(DBCSession)}.
   *
   * <p>Method under test: {@link DataExporterMarkdownTable#exportHeader(DBCSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterMarkdownTable.exportHeader(DBCSession)"})
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

    DBDAttributeBindingCustom dbdAttributeBindingCustom =
        new DBDAttributeBindingCustom(null, dataContainer, dataSource, vAttribute, 1);
    when(site.getAttributes()).thenReturn(new DBDAttributeBinding[] {dbdAttributeBindingCustom});

    DataExporterMarkdownTable dataExporterMarkdownTable = new DataExporterMarkdownTable();
    dataExporterMarkdownTable.init(site);

    // Act
    dataExporterMarkdownTable.exportHeader(null);

    // Assert
    verify(site).getAttributes();
    verify(site, atLeast(1)).getProperties();
    verify(site, atLeast(1)).getWriter();
  }

  /**
   * Test {@link DataExporterMarkdownTable#exportHeader(DBCSession)}.
   *
   * <ul>
   *   <li>Then calls {@link DBDAttributeBindingCustom#getLabel()}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterMarkdownTable#exportHeader(DBCSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterMarkdownTable.exportHeader(DBCSession)"})
  public void testExportHeader_thenCallsGetLabel() throws IOException, DBException {
    // Arrange
    DBDAttributeBindingCustom dbdAttributeBindingCustom = mock(DBDAttributeBindingCustom.class);
    when(dbdAttributeBindingCustom.getLabel()).thenReturn("Label");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
    when(site.getProperties()).thenReturn(new HashMap<>());
    when(site.getAttributes()).thenReturn(new DBDAttributeBinding[] {dbdAttributeBindingCustom});

    DataExporterMarkdownTable dataExporterMarkdownTable = new DataExporterMarkdownTable();
    dataExporterMarkdownTable.init(site);

    // Act
    dataExporterMarkdownTable.exportHeader(null);

    // Assert
    verify(dbdAttributeBindingCustom, atLeast(1)).getLabel();
    verify(site).getAttributes();
    verify(site, atLeast(1)).getProperties();
    verify(site, atLeast(1)).getWriter();
  }

  /**
   * Test {@link DataExporterMarkdownTable#exportHeader(DBCSession)}.
   *
   * <ul>
   *   <li>Then calls {@link DBDAttributeBindingCustom#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterMarkdownTable#exportHeader(DBCSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterMarkdownTable.exportHeader(DBCSession)"})
  public void testExportHeader_thenCallsGetName() throws IOException, DBException {
    // Arrange
    DBDAttributeBindingCustom dbdAttributeBindingCustom = mock(DBDAttributeBindingCustom.class);
    when(dbdAttributeBindingCustom.getLabel()).thenReturn("");
    when(dbdAttributeBindingCustom.getName()).thenReturn("Name");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
    when(site.getProperties()).thenReturn(new HashMap<>());
    when(site.getAttributes()).thenReturn(new DBDAttributeBinding[] {dbdAttributeBindingCustom});

    DataExporterMarkdownTable dataExporterMarkdownTable = new DataExporterMarkdownTable();
    dataExporterMarkdownTable.init(site);

    // Act
    dataExporterMarkdownTable.exportHeader(null);

    // Assert
    verify(dbdAttributeBindingCustom, atLeast(1)).getLabel();
    verify(dbdAttributeBindingCustom, atLeast(1)).getName();
    verify(site).getAttributes();
    verify(site, atLeast(1)).getProperties();
    verify(site, atLeast(1)).getWriter();
  }

  /**
   * Test {@link DataExporterMarkdownTable#exportRow(DBCSession, DBCResultSet, Object[])}.
   *
   * <ul>
   *   <li>When empty array of {@link Object}.
   *   <li>Then calls {@link IStreamDataExporterSite#getProperties()}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterMarkdownTable#exportRow(DBCSession, DBCResultSet,
   * Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataExporterMarkdownTable.exportRow(DBCSession, DBCResultSet, Object[])"
  })
  public void testExportRow_whenEmptyArrayOfObject_thenCallsGetProperties()
      throws IOException, DBException {
    // Arrange
    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
    when(site.getProperties()).thenReturn(new HashMap<>());

    DataExporterMarkdownTable dataExporterMarkdownTable = new DataExporterMarkdownTable();
    dataExporterMarkdownTable.init(site);
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferResultSet resultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile));

    // Act
    dataExporterMarkdownTable.exportRow(null, resultSet, new Object[] {});

    // Assert
    verify(site, atLeast(1)).getProperties();
    verify(site, atLeast(1)).getWriter();
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DataExporterMarkdownTable}
   *   <li>{@link DataExporterMarkdownTable#exportFooter(DBRProgressMonitor)}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataExporterMarkdownTable.<init>()",
    "void DataExporterMarkdownTable.exportFooter(DBRProgressMonitor)"
  })
  public void testGettersAndSetters() throws IOException, DBException {
    // Arrange and Act
    DataExporterMarkdownTable actualDataExporterMarkdownTable = new DataExporterMarkdownTable();
    actualDataExporterMarkdownTable.exportFooter(new LoggingProgressMonitor());

    // Assert
    assertNull(actualDataExporterMarkdownTable.getSite());
  }
}
