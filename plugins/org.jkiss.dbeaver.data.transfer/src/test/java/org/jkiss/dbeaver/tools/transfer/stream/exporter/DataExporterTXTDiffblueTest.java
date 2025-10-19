package org.jkiss.dbeaver.tools.transfer.stream.exporter;

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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.data.DBDAttributeBinding;
import org.jkiss.dbeaver.model.data.DBDAttributeBindingCustom;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.jkiss.dbeaver.tools.transfer.stream.IStreamDataExporterSite;
import org.jkiss.dbeaver.tools.transfer.stream.StreamEntityMapping;
import org.jkiss.dbeaver.tools.transfer.stream.model.StreamDataSource;
import org.jkiss.dbeaver.tools.transfer.stream.model.StreamTransferSession;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DataExporterTXTDiffblueTest {
  /**
   * Test {@link DataExporterTXT#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code batchSize} is {@code 1}.
   *   <li>Then {@link DataExporterTXT} (default constructor) OutputStream is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterTXT#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterTXT.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapBatchSizeIs1_thenDataExporterTXTOutputStreamIsNull()
      throws DBException {
    // Arrange
    DataExporterTXT dataExporterTXT = new DataExporterTXT();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("batchSize", "1");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterTXT.init(site);

    // Assert
    verify(site).getProperties();
    assertNull(dataExporterTXT.getOutputStream());
    assertNull(dataExporterTXT.getWriter());
    assertSame(site, dataExporterTXT.getSite());
  }

  /**
   * Test {@link DataExporterTXT#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code batchSize} is {@code Properties}.
   *   <li>Then {@link DataExporterTXT} (default constructor) OutputStream is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterTXT#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterTXT.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapBatchSizeIsProperties_thenDataExporterTXTOutputStreamIsNull()
      throws DBException {
    // Arrange
    DataExporterTXT dataExporterTXT = new DataExporterTXT();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("batchSize", "Properties");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterTXT.init(site);

    // Assert
    verify(site).getProperties();
    assertNull(dataExporterTXT.getOutputStream());
    assertNull(dataExporterTXT.getWriter());
    assertSame(site, dataExporterTXT.getSite());
  }

  /**
   * Test {@link DataExporterTXT#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code batchSize} is space.
   *   <li>Then {@link DataExporterTXT} (default constructor) OutputStream is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterTXT#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterTXT.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapBatchSizeIsSpace_thenDataExporterTXTOutputStreamIsNull()
      throws DBException {
    // Arrange
    DataExporterTXT dataExporterTXT = new DataExporterTXT();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("batchSize", " ");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterTXT.init(site);

    // Assert
    verify(site).getProperties();
    assertNull(dataExporterTXT.getOutputStream());
    assertNull(dataExporterTXT.getWriter());
    assertSame(site, dataExporterTXT.getSite());
  }

  /**
   * Test {@link DataExporterTXT#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code batchSize} is {@code true}.
   *   <li>Then {@link DataExporterTXT} (default constructor) OutputStream is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterTXT#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterTXT.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapBatchSizeIsTrue_thenDataExporterTXTOutputStreamIsNull()
      throws DBException {
    // Arrange
    DataExporterTXT dataExporterTXT = new DataExporterTXT();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("batchSize", true);

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterTXT.init(site);

    // Assert
    verify(site).getProperties();
    assertNull(dataExporterTXT.getOutputStream());
    assertNull(dataExporterTXT.getWriter());
    assertSame(site, dataExporterTXT.getSite());
  }

  /**
   * Test {@link DataExporterTXT#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code batchSize} is valueOf one.
   *   <li>Then {@link DataExporterTXT} (default constructor) OutputStream is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterTXT#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterTXT.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapBatchSizeIsValueOfOne_thenDataExporterTXTOutputStreamIsNull()
      throws DBException {
    // Arrange
    DataExporterTXT dataExporterTXT = new DataExporterTXT();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("batchSize", Integer.valueOf(1));

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterTXT.init(site);

    // Assert
    verify(site).getProperties();
    assertNull(dataExporterTXT.getOutputStream());
    assertNull(dataExporterTXT.getWriter());
    assertSame(site, dataExporterTXT.getSite());
  }

  /**
   * Test {@link DataExporterTXT#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code delimBetween} is {@link Boolean#FALSE} toString.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterTXT#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterTXT.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapDelimBetweenIsFalseToString() throws DBException {
    // Arrange
    DataExporterTXT dataExporterTXT = new DataExporterTXT();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("delimBetween", Boolean.FALSE.toString());

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterTXT.init(site);

    // Assert
    verify(site).getProperties();
    assertNull(dataExporterTXT.getOutputStream());
    assertNull(dataExporterTXT.getWriter());
    assertSame(site, dataExporterTXT.getSite());
  }

  /**
   * Test {@link DataExporterTXT#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code delimBetween} is {@code Properties}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterTXT#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterTXT.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapDelimBetweenIsProperties() throws DBException {
    // Arrange
    DataExporterTXT dataExporterTXT = new DataExporterTXT();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("delimBetween", "Properties");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterTXT.init(site);

    // Assert
    verify(site).getProperties();
    assertNull(dataExporterTXT.getOutputStream());
    assertNull(dataExporterTXT.getWriter());
    assertSame(site, dataExporterTXT.getSite());
  }

  /**
   * Test {@link DataExporterTXT#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code maxColumnLength} is {@code 42}.
   *   <li>Then {@link DataExporterTXT} (default constructor) OutputStream is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterTXT#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterTXT.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapMaxColumnLengthIs42_thenDataExporterTXTOutputStreamIsNull()
      throws DBException {
    // Arrange
    DataExporterTXT dataExporterTXT = new DataExporterTXT();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("maxColumnLength", "42");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterTXT.init(site);

    // Assert
    verify(site).getProperties();
    assertNull(dataExporterTXT.getOutputStream());
    assertNull(dataExporterTXT.getWriter());
    assertSame(site, dataExporterTXT.getSite());
  }

  /**
   * Test {@link DataExporterTXT#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code maxColumnLength} is valueOf one.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterTXT#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterTXT.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapMaxColumnLengthIsValueOfOne() throws DBException {
    // Arrange
    DataExporterTXT dataExporterTXT = new DataExporterTXT();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("maxColumnLength", Integer.valueOf(1));

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterTXT.init(site);

    // Assert
    verify(site).getProperties();
    assertNull(dataExporterTXT.getOutputStream());
    assertNull(dataExporterTXT.getWriter());
    assertSame(site, dataExporterTXT.getSite());
  }

  /**
   * Test {@link DataExporterTXT#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code showNulls} is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterTXT#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterTXT.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapShowNullsIsEmptyString() throws DBException {
    // Arrange
    DataExporterTXT dataExporterTXT = new DataExporterTXT();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("showNulls", "");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterTXT.init(site);

    // Assert
    verify(site).getProperties();
    assertNull(dataExporterTXT.getOutputStream());
    assertNull(dataExporterTXT.getWriter());
    assertSame(site, dataExporterTXT.getSite());
  }

  /**
   * Test {@link DataExporterTXT#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code showNulls} is {@code null}.
   *   <li>Then {@link DataExporterTXT} (default constructor) OutputStream is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterTXT#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterTXT.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapShowNullsIsNull_thenDataExporterTXTOutputStreamIsNull()
      throws DBException {
    // Arrange
    DataExporterTXT dataExporterTXT = new DataExporterTXT();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("showNulls", null);

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterTXT.init(site);

    // Assert
    verify(site).getProperties();
    assertNull(dataExporterTXT.getOutputStream());
    assertNull(dataExporterTXT.getWriter());
    assertSame(site, dataExporterTXT.getSite());
  }

  /**
   * Test {@link DataExporterTXT#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code showNulls} is {@code Properties}.
   *   <li>Then {@link DataExporterTXT} (default constructor) OutputStream is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterTXT#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterTXT.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapShowNullsIsProperties_thenDataExporterTXTOutputStreamIsNull()
      throws DBException {
    // Arrange
    DataExporterTXT dataExporterTXT = new DataExporterTXT();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("showNulls", "Properties");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterTXT.init(site);

    // Assert
    verify(site).getProperties();
    assertNull(dataExporterTXT.getOutputStream());
    assertNull(dataExporterTXT.getWriter());
    assertSame(site, dataExporterTXT.getSite());
  }

  /**
   * Test {@link DataExporterTXT#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code showNulls} is {@code true}.
   *   <li>Then {@link DataExporterTXT} (default constructor) OutputStream is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterTXT#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterTXT.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapShowNullsIsTrue_thenDataExporterTXTOutputStreamIsNull()
      throws DBException {
    // Arrange
    DataExporterTXT dataExporterTXT = new DataExporterTXT();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("showNulls", true);

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterTXT.init(site);

    // Assert
    verify(site).getProperties();
    assertNull(dataExporterTXT.getOutputStream());
    assertNull(dataExporterTXT.getWriter());
    assertSame(site, dataExporterTXT.getSite());
  }

  /**
   * Test {@link DataExporterTXT#exportHeader(DBCSession)}.
   *
   * <p>Method under test: {@link DataExporterTXT#exportHeader(DBCSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterTXT.exportHeader(DBCSession)"})
  public void testExportHeader() throws IOException, DBException {
    // Arrange
    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(new HashMap<>());
    when(site.getAttributes()).thenReturn(new DBDAttributeBinding[] {});

    DataExporterTXT dataExporterTXT = new DataExporterTXT();
    dataExporterTXT.init(site);

    StreamTransferSession session = mock(StreamTransferSession.class);
    when(session.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());

    // Act
    dataExporterTXT.exportHeader(session);

    // Assert
    verify(session).getProgressMonitor();
    verify(site).getAttributes();
    verify(site).getProperties();
  }

  /**
   * Test {@link DataExporterTXT#exportHeader(DBCSession)}.
   *
   * <p>Method under test: {@link DataExporterTXT#exportHeader(DBCSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterTXT.exportHeader(DBCSession)"})
  public void testExportHeader2() throws IOException, DBException {
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

    DataExporterTXT dataExporterTXT = new DataExporterTXT();
    dataExporterTXT.init(site);

    StreamTransferSession session = mock(StreamTransferSession.class);
    when(session.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());

    // Act
    dataExporterTXT.exportHeader(session);

    // Assert
    verify(session).getProgressMonitor();
    verify(site).getAttributes();
    verify(site).getProperties();
  }

  /**
   * Test {@link DataExporterTXT#exportHeader(DBCSession)}.
   *
   * <p>Method under test: {@link DataExporterTXT#exportHeader(DBCSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterTXT.exportHeader(DBCSession)"})
  public void testExportHeader3() throws IOException, DBException {
    // Arrange
    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
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

    DataExporterTXT dataExporterTXT = new DataExporterTXT();
    dataExporterTXT.init(site);

    StreamTransferSession session = mock(StreamTransferSession.class);
    when(session.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());

    // Act
    dataExporterTXT.exportHeader(session);

    // Assert
    verify(session).getProgressMonitor();
    verify(site).getAttributes();
    verify(site).getProperties();
  }

  /**
   * Test {@link DataExporterTXT#exportHeader(DBCSession)}.
   *
   * <ul>
   *   <li>Then calls {@link DBDAttributeBindingCustom#getLabel()}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterTXT#exportHeader(DBCSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterTXT.exportHeader(DBCSession)"})
  public void testExportHeader_thenCallsGetLabel() throws IOException, DBException {
    // Arrange
    DBDAttributeBindingCustom dbdAttributeBindingCustom = mock(DBDAttributeBindingCustom.class);
    when(dbdAttributeBindingCustom.getLabel()).thenReturn("Label");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(new HashMap<>());
    when(site.getAttributes()).thenReturn(new DBDAttributeBinding[] {dbdAttributeBindingCustom});

    DataExporterTXT dataExporterTXT = new DataExporterTXT();
    dataExporterTXT.init(site);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile);

    StreamTransferSession session = mock(StreamTransferSession.class);
    when(session.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());

    // Act
    dataExporterTXT.exportHeader(session);

    // Assert
    verify(dbdAttributeBindingCustom, atLeast(1)).getLabel();
    verify(session).getProgressMonitor();
    verify(site).getAttributes();
    verify(site).getProperties();
  }

  /**
   * Test {@link DataExporterTXT#exportHeader(DBCSession)}.
   *
   * <ul>
   *   <li>Then calls {@link DBDAttributeBindingCustom#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterTXT#exportHeader(DBCSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterTXT.exportHeader(DBCSession)"})
  public void testExportHeader_thenCallsGetName() throws IOException, DBException {
    // Arrange
    DBDAttributeBindingCustom dbdAttributeBindingCustom = mock(DBDAttributeBindingCustom.class);
    when(dbdAttributeBindingCustom.getLabel()).thenReturn("");
    when(dbdAttributeBindingCustom.getName()).thenReturn("Name");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(new HashMap<>());
    when(site.getAttributes()).thenReturn(new DBDAttributeBinding[] {dbdAttributeBindingCustom});

    DataExporterTXT dataExporterTXT = new DataExporterTXT();
    dataExporterTXT.init(site);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile);

    StreamTransferSession session = mock(StreamTransferSession.class);
    when(session.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());

    // Act
    dataExporterTXT.exportHeader(session);

    // Assert
    verify(dbdAttributeBindingCustom).getLabel();
    verify(dbdAttributeBindingCustom).getName();
    verify(session).getProgressMonitor();
    verify(site).getAttributes();
    verify(site).getProperties();
  }

  /**
   * Test {@link DataExporterTXT#exportFooter(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then calls {@link IStreamDataExporterSite#getProperties()}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterTXT#exportFooter(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterTXT.exportFooter(DBRProgressMonitor)"})
  public void testExportFooter_thenCallsGetProperties() throws IOException, DBException {
    // Arrange
    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(new HashMap<>());

    DataExporterTXT dataExporterTXT = new DataExporterTXT();
    dataExporterTXT.init(site);

    // Act
    dataExporterTXT.exportFooter(new LoggingProgressMonitor());

    // Assert
    verify(site).getProperties();
  }

  /**
   * Test {@link DataExporterTXT#shouldTruncateOutputFileBeforeExport()}.
   *
   * <p>Method under test: {@link DataExporterTXT#shouldTruncateOutputFileBeforeExport()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DataExporterTXT.shouldTruncateOutputFileBeforeExport()"})
  public void testShouldTruncateOutputFileBeforeExport() {
    // Arrange, Act and Assert
    assertFalse(new DataExporterTXT().shouldTruncateOutputFileBeforeExport());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DataExporterTXT}
   *   <li>{@link DataExporterTXT#importData(IStreamDataExporterSite)}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataExporterTXT.<init>()",
    "void DataExporterTXT.importData(IStreamDataExporterSite)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DataExporterTXT actualDataExporterTXT = new DataExporterTXT();
    actualDataExporterTXT.importData(mock(IStreamDataExporterSite.class));

    // Assert
    assertNull(actualDataExporterTXT.getSite());
  }
}
