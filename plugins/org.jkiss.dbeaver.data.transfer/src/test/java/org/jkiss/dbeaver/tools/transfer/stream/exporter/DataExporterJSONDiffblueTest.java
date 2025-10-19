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
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.jkiss.dbeaver.tools.transfer.stream.IStreamDataExporterSite;
import org.jkiss.dbeaver.tools.transfer.stream.StreamEntityMapping;
import org.jkiss.dbeaver.tools.transfer.stream.model.StreamDataSource;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DataExporterJSONDiffblueTest {
  /**
   * Test {@link DataExporterJSON#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@link DataExporterJSON#PROP_EXPORT_JSON_VALUES} is
   *       {@code Properties}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterJSON#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterJSON.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapProp_export_json_valuesIsProperties() throws DBException {
    // Arrange
    DataExporterJSON dataExporterJSON = new DataExporterJSON();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put(DataExporterJSON.PROP_EXPORT_JSON_VALUES, "Properties");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterJSON.init(site);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertNull(dataExporterJSON.getOutputStream());
    assertNull(dataExporterJSON.getWriter());
    assertSame(site, dataExporterJSON.getSite());
  }

  /**
   * Test {@link DataExporterJSON#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@link DataExporterJSON#PROP_FORMAT_DATE_ISO} is empty
   *       string.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterJSON#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterJSON.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapProp_format_date_isoIsEmptyString() throws DBException {
    // Arrange
    DataExporterJSON dataExporterJSON = new DataExporterJSON();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put(DataExporterJSON.PROP_FORMAT_DATE_ISO, "");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterJSON.init(site);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertNull(dataExporterJSON.getOutputStream());
    assertNull(dataExporterJSON.getWriter());
    assertSame(site, dataExporterJSON.getSite());
  }

  /**
   * Test {@link DataExporterJSON#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@link DataExporterJSON#PROP_FORMAT_DATE_ISO} is {@code
   *       Properties}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterJSON#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterJSON.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapProp_format_date_isoIsProperties() throws DBException {
    // Arrange
    DataExporterJSON dataExporterJSON = new DataExporterJSON();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put(DataExporterJSON.PROP_FORMAT_DATE_ISO, "Properties");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterJSON.init(site);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertNull(dataExporterJSON.getOutputStream());
    assertNull(dataExporterJSON.getWriter());
    assertSame(site, dataExporterJSON.getSite());
  }

  /**
   * Test {@link DataExporterJSON#init(IStreamDataExporterSite)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@link DataExporterJSON#PROP_FORMAT_DATE_ISO} is {@code
   *       true}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterJSON#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterJSON.init(IStreamDataExporterSite)"})
  public void testInit_givenHashMapProp_format_date_isoIsTrue() throws DBException {
    // Arrange
    DataExporterJSON dataExporterJSON = new DataExporterJSON();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put(DataExporterJSON.PROP_FORMAT_DATE_ISO, true);

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(stringObjectMap);

    // Act
    dataExporterJSON.init(site);

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertNull(dataExporterJSON.getOutputStream());
    assertNull(dataExporterJSON.getWriter());
    assertSame(site, dataExporterJSON.getSite());
  }

  /**
   * Test {@link DataExporterJSON#exportHeader(DBCSession)}.
   * <ul>
   *   <li>Given {@link DBPNamedObject} {@link DBPNamedObject#getName()} return {@code {}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataExporterJSON#exportHeader(DBCSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterJSON.exportHeader(DBCSession)"})
  public void testExportHeader_givenDBPNamedObjectGetNameReturnLeftCurlyBracket()
      throws IOException, DBException {
    // Arrange
    DBPNamedObject dbpNamedObject = mock(DBPNamedObject.class);
    when(dbpNamedObject.getName()).thenReturn("{\n");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
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

    DataExporterJSON dataExporterJSON = new DataExporterJSON();
    dataExporterJSON.init(site);

    // Act
    dataExporterJSON.exportHeader(null);

    // Assert
    verify(dbpNamedObject).getName();
    verify(site).getAttributes();
    verify(site, atLeast(1)).getProperties();
    verify(site).getSource();
    verify(site).getWriter();
  }

  /**
   * Test {@link DataExporterJSON#exportHeader(DBCSession)}.
   *
   * <ul>
   *   <li>Given {@link DBPNamedObject} {@link DBPNamedObject#getName()} return {@code Name}.
   *   <li>Then calls {@link DBPNamedObject#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterJSON#exportHeader(DBCSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterJSON.exportHeader(DBCSession)"})
  public void testExportHeader_givenDBPNamedObjectGetNameReturnName_thenCallsGetName()
      throws IOException, DBException {
    // Arrange
    DBPNamedObject dbpNamedObject = mock(DBPNamedObject.class);
    when(dbpNamedObject.getName()).thenReturn("Name");

    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
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

    DataExporterJSON dataExporterJSON = new DataExporterJSON();
    dataExporterJSON.init(site);

    // Act
    dataExporterJSON.exportHeader(null);

    // Assert
    verify(dbpNamedObject).getName();
    verify(site).getAttributes();
    verify(site, atLeast(1)).getProperties();
    verify(site).getSource();
    verify(site).getWriter();
  }

  /**
   * Test {@link DataExporterJSON#exportFooter(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then calls {@link IStreamDataExporterSite#getProperties()}.
   * </ul>
   *
   * <p>Method under test: {@link DataExporterJSON#exportFooter(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterJSON.exportFooter(DBRProgressMonitor)"})
  public void testExportFooter_thenCallsGetProperties() throws IOException, DBException {
    // Arrange
    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
    when(site.getProperties()).thenReturn(new HashMap<>());

    DataExporterJSON dataExporterJSON = new DataExporterJSON();
    dataExporterJSON.init(site);

    // Act
    dataExporterJSON.exportFooter(new LoggingProgressMonitor());

    // Assert
    verify(site, atLeast(1)).getProperties();
    verify(site).getWriter();
  }

  /**
   * Test new {@link DataExporterJSON} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link DataExporterJSON}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterJSON.<init>()"})
  public void testNewDataExporterJSON() {
    // Arrange, Act and Assert
    assertNull(new DataExporterJSON().getSite());
  }
}
