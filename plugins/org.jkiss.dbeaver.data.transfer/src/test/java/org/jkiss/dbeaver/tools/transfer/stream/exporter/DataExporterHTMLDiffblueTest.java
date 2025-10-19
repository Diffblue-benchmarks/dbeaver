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

public class DataExporterHTMLDiffblueTest {
  /**
   * Test {@link DataExporterHTML#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code tableHeader} is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterHTML#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterHTML.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapTableHeaderIsEmptyString() throws DBException {
    // Arrange
    DataExporterHTML dataExporterHTML = new DataExporterHTML();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("tableHeader", "");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterHTML.init(site);

    // Assert
    verify(site).getProperties();
    assertNull(dataExporterHTML.getOutputStream());
    assertNull(dataExporterHTML.getWriter());
    assertSame(site, dataExporterHTML.getSite());
  }

  /**
   * Test {@link DataExporterHTML#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code tableHeader} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterHTML#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterHTML.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapTableHeaderIsNull() throws DBException {
    // Arrange
    DataExporterHTML dataExporterHTML = new DataExporterHTML();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("tableHeader", null);

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterHTML.init(site);

    // Assert
    verify(site).getProperties();
    assertNull(dataExporterHTML.getOutputStream());
    assertNull(dataExporterHTML.getWriter());
    assertSame(site, dataExporterHTML.getSite());
  }

  /**
   * Test {@link DataExporterHTML#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code tableHeader} is {@code Properties}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterHTML#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterHTML.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapTableHeaderIsProperties() throws DBException {
    // Arrange
    DataExporterHTML dataExporterHTML = new DataExporterHTML();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("tableHeader", "Properties");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterHTML.init(site);

    // Assert
    verify(site).getProperties();
    assertNull(dataExporterHTML.getOutputStream());
    assertNull(dataExporterHTML.getWriter());
    assertSame(site, dataExporterHTML.getSite());
  }

  /**
   * Test {@link DataExporterHTML#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code tableHeader} is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterHTML#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterHTML.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapTableHeaderIsTrue() throws DBException {
    // Arrange
    DataExporterHTML dataExporterHTML = new DataExporterHTML();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("tableHeader", true);

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterHTML.init(site);

    // Assert
    verify(site).getProperties();
    assertNull(dataExporterHTML.getOutputStream());
    assertNull(dataExporterHTML.getWriter());
    assertSame(site, dataExporterHTML.getSite());
  }

  /**
   * Test {@link DataExporterHTML#exportHeader(DBCSession)}.
   *
   * <p>Method under test: {@link DataExporterHTML#exportHeader(DBCSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterHTML.exportHeader(DBCSession)"})
  public void testExportHeader() throws IOException, DBException {
    // Arrange
    DBPNamedObject dbpNamedObject = mock(DBPNamedObject.class);
    when(dbpNamedObject.getName()).thenReturn("Name");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getOutputEncoding()).thenReturn("UTF-8");
    when(site.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping dataContainer = new StreamEntityMapping(inputFile);
    StreamDataSource dataSource = new StreamDataSource("Input Name");
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntityAttribute vAttribute = new DBVEntityAttribute(entity, null, "Name");

    DBDAttributeBindingCustom dbdAttributeBindingCustom =
        new DBDAttributeBindingCustom(null, dataContainer, dataSource, vAttribute, 1);
    when(site.getAttributes()).thenReturn(new DBDAttributeBinding[] {dbdAttributeBindingCustom});
    when(site.getProperties()).thenReturn(new HashMap<>());
    when(site.getSource()).thenReturn(dbpNamedObject);

    DataExporterHTML dataExporterHTML = new DataExporterHTML();
    dataExporterHTML.init(site);

    // Act
    dataExporterHTML.exportHeader(null);

    // Assert
    verify(dbpNamedObject).getName();
    verify(site).getAttributes();
    verify(site).getOutputEncoding();
    verify(site).getProperties();
    verify(site).getSource();
    verify(site, atLeast(1)).getWriter();
  }

  /**
   * Test {@link DataExporterHTML#exportHeader(DBCSession)}.
   *
   * <p>Method under test: {@link DataExporterHTML#exportHeader(DBCSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterHTML.exportHeader(DBCSession)"})
  public void testExportHeader2() throws IOException, DBException {
    // Arrange
    DBPNamedObject dbpNamedObject = mock(DBPNamedObject.class);
    when(dbpNamedObject.getName()).thenReturn("Name");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getOutputEncoding()).thenReturn("UTF-8");
    when(site.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping dataContainer = new StreamEntityMapping(inputFile);
    StreamDataSource dataSource = new StreamDataSource("Input Name");
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntityAttribute vAttribute = new DBVEntityAttribute(entity, null, "");

    DBDAttributeBindingCustom dbdAttributeBindingCustom =
        new DBDAttributeBindingCustom(null, dataContainer, dataSource, vAttribute, 1);
    when(site.getAttributes()).thenReturn(new DBDAttributeBinding[] {dbdAttributeBindingCustom});
    when(site.getProperties()).thenReturn(new HashMap<>());
    when(site.getSource()).thenReturn(dbpNamedObject);

    DataExporterHTML dataExporterHTML = new DataExporterHTML();
    dataExporterHTML.init(site);

    // Act
    dataExporterHTML.exportHeader(null);

    // Assert
    verify(dbpNamedObject).getName();
    verify(site).getAttributes();
    verify(site).getOutputEncoding();
    verify(site).getProperties();
    verify(site).getSource();
    verify(site, atLeast(1)).getWriter();
  }

  /**
   * Test {@link DataExporterHTML#exportHeader(DBCSession)}.
   *
   * <ul>
   *   <li>Then calls {@link DBDAttributeBindingCustom#getLabel()}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterHTML#exportHeader(DBCSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterHTML.exportHeader(DBCSession)"})
  public void testExportHeader_thenCallsGetLabel() throws IOException, DBException {
    // Arrange
    DBPNamedObject dbpNamedObject = mock(DBPNamedObject.class);
    when(dbpNamedObject.getName()).thenReturn("Name");

    DBDAttributeBindingCustom dbdAttributeBindingCustom = mock(DBDAttributeBindingCustom.class);
    when(dbdAttributeBindingCustom.getLabel()).thenReturn("Label");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getOutputEncoding()).thenReturn("UTF-8");
    when(site.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
    when(site.getAttributes()).thenReturn(new DBDAttributeBinding[] {dbdAttributeBindingCustom});
    when(site.getProperties()).thenReturn(new HashMap<>());
    when(site.getSource()).thenReturn(dbpNamedObject);

    DataExporterHTML dataExporterHTML = new DataExporterHTML();
    dataExporterHTML.init(site);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile);

    // Act
    dataExporterHTML.exportHeader(null);

    // Assert
    verify(dbpNamedObject).getName();
    verify(dbdAttributeBindingCustom).getLabel();
    verify(site).getAttributes();
    verify(site).getOutputEncoding();
    verify(site).getProperties();
    verify(site).getSource();
    verify(site, atLeast(1)).getWriter();
  }

  /**
   * Test {@link DataExporterHTML#exportHeader(DBCSession)}.
   *
   * <ul>
   *   <li>Then calls {@link DBDAttributeBindingCustom#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterHTML#exportHeader(DBCSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterHTML.exportHeader(DBCSession)"})
  public void testExportHeader_thenCallsGetName() throws IOException, DBException {
    // Arrange
    DBPNamedObject dbpNamedObject = mock(DBPNamedObject.class);
    when(dbpNamedObject.getName()).thenReturn("Name");

    DBDAttributeBindingCustom dbdAttributeBindingCustom = mock(DBDAttributeBindingCustom.class);
    when(dbdAttributeBindingCustom.getLabel()).thenReturn("");
    when(dbdAttributeBindingCustom.getName()).thenReturn("Name");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getOutputEncoding()).thenReturn("UTF-8");
    when(site.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
    when(site.getAttributes()).thenReturn(new DBDAttributeBinding[] {dbdAttributeBindingCustom});
    when(site.getProperties()).thenReturn(new HashMap<>());
    when(site.getSource()).thenReturn(dbpNamedObject);

    DataExporterHTML dataExporterHTML = new DataExporterHTML();
    dataExporterHTML.init(site);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile);

    // Act
    dataExporterHTML.exportHeader(null);

    // Assert
    verify(dbpNamedObject).getName();
    verify(dbdAttributeBindingCustom).getLabel();
    verify(dbdAttributeBindingCustom).getName();
    verify(site).getAttributes();
    verify(site).getOutputEncoding();
    verify(site).getProperties();
    verify(site).getSource();
    verify(site, atLeast(1)).getWriter();
  }

  /**
   * Test {@link DataExporterHTML#exportRow(DBCSession, DBCResultSet, Object[])}.
   *
   * <ul>
   *   <li>When empty array of {@link Object}.
   *   <li>Then calls {@link IStreamDataExporterSite#getProperties()}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterHTML#exportRow(DBCSession, DBCResultSet, Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterHTML.exportRow(DBCSession, DBCResultSet, Object[])"})
  public void testExportRow_whenEmptyArrayOfObject_thenCallsGetProperties()
      throws IOException, DBException {
    // Arrange
    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
    when(site.getProperties()).thenReturn(new HashMap<>());

    DataExporterHTML dataExporterHTML = new DataExporterHTML();
    dataExporterHTML.init(site);
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferResultSet resultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile));

    // Act
    dataExporterHTML.exportRow(null, resultSet, new Object[] {});

    // Assert
    verify(site).getProperties();
    verify(site).getWriter();
  }

  /**
   * Test {@link DataExporterHTML#exportFooter(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then calls {@link IStreamDataExporterSite#getProperties()}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterHTML#exportFooter(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterHTML.exportFooter(DBRProgressMonitor)"})
  public void testExportFooter_thenCallsGetProperties() throws DBException {
    // Arrange
    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
    when(site.getProperties()).thenReturn(new HashMap<>());

    DataExporterHTML dataExporterHTML = new DataExporterHTML();
    dataExporterHTML.init(site);

    // Act
    dataExporterHTML.exportFooter(new LoggingProgressMonitor());

    // Assert
    verify(site).getProperties();
    verify(site).getWriter();
  }

  /**
   * Test new {@link DataExporterHTML} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link DataExporterHTML}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterHTML.<init>()"})
  public void testNewDataExporterHTML() {
    // Arrange, Act and Assert
    assertNull(new DataExporterHTML().getSite());
  }
}
