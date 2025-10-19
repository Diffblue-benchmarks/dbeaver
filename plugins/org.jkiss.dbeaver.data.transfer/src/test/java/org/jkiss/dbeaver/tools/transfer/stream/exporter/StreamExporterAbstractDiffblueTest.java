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
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.jkiss.dbeaver.tools.transfer.stream.IStreamDataExporterSite;
import org.jkiss.dbeaver.tools.transfer.stream.StreamEntityMapping;
import org.jkiss.dbeaver.tools.transfer.stream.model.StreamDataSource;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class StreamExporterAbstractDiffblueTest {
  /**
   * Test {@link StreamExporterAbstract#getSite()}.
   *
   * <p>Method under test: {@link StreamExporterAbstract#getSite()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStreamDataExporterSite StreamExporterAbstract.getSite()"})
  public void testGetSite() {
    // Arrange, Act and Assert
    assertNull(new DataExporterCSV().getSite());
  }

  /**
   * Test {@link StreamExporterAbstract#getWriter()}.
   *
   * <ul>
   *   <li>Then return {@link PrintWriter#PrintWriter(Writer)} with {@link
   *       StringWriter#StringWriter()}.
   * </ul>
   *
   * <p>Method under test: {@link StreamExporterAbstract#getWriter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PrintWriter StreamExporterAbstract.getWriter()"})
  public void testGetWriter_thenReturnPrintWriterWithStringWriter() throws DBException {
    // Arrange
    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    PrintWriter printWriter = new PrintWriter(new StringWriter());
    when(site.getWriter()).thenReturn(printWriter);
    when(site.getProperties()).thenReturn(new HashMap<>());

    DataExporterCSV dataExporterCSV = new DataExporterCSV();
    dataExporterCSV.init(site);

    // Act
    PrintWriter actualWriter = dataExporterCSV.getWriter();

    // Assert
    verify(site, atLeast(1)).getProperties();
    verify(site).getWriter();
    assertSame(printWriter, actualWriter);
  }

  /**
   * Test {@link StreamExporterAbstract#init(IStreamDataExporterSite)}.
   *
   * <p>Method under test: {@link StreamExporterAbstract#init(IStreamDataExporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamExporterAbstract.init(IStreamDataExporterSite)"})
  public void testInit() throws DBException {
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
   * Test {@link StreamExporterAbstract#getValueDisplayString(DBDAttributeBinding, Object)}.
   *
   * <ul>
   *   <li>Given {@code NUMERIC}.
   * </ul>
   *
   * <p>Method under test: {@link StreamExporterAbstract#getValueDisplayString(DBDAttributeBinding,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String StreamExporterAbstract.getValueDisplayString(DBDAttributeBinding, Object)"
  })
  public void testGetValueDisplayString_givenNumeric() throws DBException {
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
    String actualValueDisplayString = dataExporterCSV.getValueDisplayString(column, "Value");

    // Assert
    verify(site, atLeast(1)).getProperties();
    assertEquals("Value", actualValueDisplayString);
  }

  /**
   * Test {@link StreamExporterAbstract#getValueDisplayString(DBDAttributeBinding, Object)}.
   *
   * <ul>
   *   <li>Then calls {@link IStreamDataExporterSite#getExportFormat()}.
   * </ul>
   *
   * <p>Method under test: {@link StreamExporterAbstract#getValueDisplayString(DBDAttributeBinding,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String StreamExporterAbstract.getValueDisplayString(DBDAttributeBinding, Object)"
  })
  public void testGetValueDisplayString_thenCallsGetExportFormat() throws DBException {
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
    String actualValueDisplayString = dataExporterCSV.getValueDisplayString(column, "Value");

    // Assert
    verify(site).getExportFormat();
    verify(site, atLeast(1)).getProperties();
    assertEquals("Value", actualValueDisplayString);
  }

  /**
   * Test {@link StreamExporterAbstract#getValueExportFormat(DBDAttributeBinding)}.
   *
   * <ul>
   *   <li>Then return {@code UI}.
   * </ul>
   *
   * <p>Method under test: {@link StreamExporterAbstract#getValueExportFormat(DBDAttributeBinding)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBDDisplayFormat StreamExporterAbstract.getValueExportFormat(DBDAttributeBinding)"
  })
  public void testGetValueExportFormat_thenReturnUi() throws DBException {
    // Arrange
    IStreamDataExporterSite site = mock(IStreamDataExporterSite.class);
    when(site.getProperties()).thenReturn(new HashMap<>());
    when(site.getExportFormat()).thenReturn(DBDDisplayFormat.UI);

    DataExporterDbUnit dataExporterDbUnit = new DataExporterDbUnit();
    dataExporterDbUnit.init(site);
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
    DBDDisplayFormat actualValueExportFormat = dataExporterDbUnit.getValueExportFormat(column);

    // Assert
    verify(site).getExportFormat();
    verify(site, atLeast(1)).getProperties();
    assertEquals(DBDDisplayFormat.UI, actualValueExportFormat);
  }
}
