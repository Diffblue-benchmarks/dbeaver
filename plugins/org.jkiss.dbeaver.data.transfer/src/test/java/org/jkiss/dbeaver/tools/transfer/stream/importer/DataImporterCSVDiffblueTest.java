package org.jkiss.dbeaver.tools.transfer.stream.importer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.struct.DBSInstance;
import org.jkiss.dbeaver.tools.transfer.stream.IStreamDataImporterSite;
import org.jkiss.dbeaver.tools.transfer.stream.StreamDataImporterColumnInfo;
import org.jkiss.dbeaver.tools.transfer.stream.StreamEntityMapping;
import org.jkiss.dbeaver.tools.transfer.stream.model.StreamDataSource;
import org.jkiss.dbeaver.tools.transfer.stream.model.StreamExecutionContext;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DataImporterCSVDiffblueTest {
  /**
   * Test new {@link DataImporterCSV} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link DataImporterCSV}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataImporterCSV.<init>()"})
  public void testNewDataImporterCSV() {
    // Arrange, Act and Assert
    assertNull(new DataImporterCSV().getSite());
  }

  /**
   * Test {@link DataImporterCSV#readColumnsInfo(StreamEntityMapping, InputStream)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code columnTypeIsByteLength} is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DataImporterCSV#readColumnsInfo(StreamEntityMapping, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DataImporterCSV.readColumnsInfo(StreamEntityMapping, InputStream)"})
  public void testReadColumnsInfo_givenHashMapColumnTypeIsByteLengthIs42()
      throws UnsupportedEncodingException, DBException {
    // Arrange
    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("columnTypeSamplesCount", "42");
    stringObjectMap.put("columnTypeIsByteLength", "42");
    stringObjectMap.put("header", "42");

    IStreamDataImporterSite site = mock(IStreamDataImporterSite.class);
    when(site.getProcessorProperties()).thenReturn(stringObjectMap);

    DataImporterCSV dataImporterCSV = new DataImporterCSV();
    dataImporterCSV.init(site);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping entityMapping = new StreamEntityMapping(inputFile);

    // Act
    List<StreamDataImporterColumnInfo> actualReadColumnsInfoResult =
        dataImporterCSV.readColumnsInfo(
            entityMapping, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    // Assert
    verify(site).getProcessorProperties();
    assertEquals(1, actualReadColumnsInfoResult.size());
    StreamDataImporterColumnInfo getResult = actualReadColumnsInfoResult.get(0);
    DBPDataSource dataSource = getResult.getDataSource();
    Collection<? extends DBSInstance> availableInstances = dataSource.getAvailableInstances();
    assertEquals(1, availableInstances.size());
    assertTrue(availableInstances instanceof List);
    assertTrue(dataSource instanceof StreamDataSource);
    assertEquals("AXAXAXAX", getResult.getName());
    assertSame(dataSource, ((List<? extends DBSInstance>) availableInstances).get(0));
    StreamExecutionContext defaultContext = ((StreamDataSource) dataSource).getDefaultContext();
    assertSame(dataSource, defaultContext.getDataSource());
    StreamEntityMapping parentObject = getResult.getParentObject();
    assertSame(dataSource, parentObject.getDataSource());
    assertSame(dataSource, parentObject.getParentObject());
    assertSame(dataSource, defaultContext.getOwnerInstance());
  }

  /**
   * Test {@link DataImporterCSV#readColumnsInfo(StreamEntityMapping, InputStream)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code header} is {@code 42}.
   *   <li>Then return first Name is {@code AXAXAXAX}.
   * </ul>
   *
   * <p>Method under test: {@link DataImporterCSV#readColumnsInfo(StreamEntityMapping, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DataImporterCSV.readColumnsInfo(StreamEntityMapping, InputStream)"})
  public void testReadColumnsInfo_givenHashMapHeaderIs42_thenReturnFirstNameIsAxaxaxax()
      throws UnsupportedEncodingException, DBException {
    // Arrange
    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("header", "42");

    IStreamDataImporterSite site = mock(IStreamDataImporterSite.class);
    when(site.getProcessorProperties()).thenReturn(stringObjectMap);

    DataImporterCSV dataImporterCSV = new DataImporterCSV();
    dataImporterCSV.init(site);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping entityMapping = new StreamEntityMapping(inputFile);

    // Act
    List<StreamDataImporterColumnInfo> actualReadColumnsInfoResult =
        dataImporterCSV.readColumnsInfo(
            entityMapping, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    // Assert
    verify(site).getProcessorProperties();
    assertEquals(1, actualReadColumnsInfoResult.size());
    StreamDataImporterColumnInfo getResult = actualReadColumnsInfoResult.get(0);
    DBPDataSource dataSource = getResult.getDataSource();
    Collection<? extends DBSInstance> availableInstances = dataSource.getAvailableInstances();
    assertEquals(1, availableInstances.size());
    assertTrue(availableInstances instanceof List);
    assertTrue(dataSource instanceof StreamDataSource);
    assertEquals("AXAXAXAX", getResult.getName());
    assertSame(dataSource, ((List<? extends DBSInstance>) availableInstances).get(0));
    StreamExecutionContext defaultContext = ((StreamDataSource) dataSource).getDefaultContext();
    assertSame(dataSource, defaultContext.getDataSource());
    StreamEntityMapping parentObject = getResult.getParentObject();
    assertSame(dataSource, parentObject.getDataSource());
    assertSame(dataSource, parentObject.getParentObject());
    assertSame(dataSource, defaultContext.getOwnerInstance());
  }

  /**
   * Test {@link DataImporterCSV#readColumnsInfo(StreamEntityMapping, InputStream)}.
   *
   * <ul>
   *   <li>Then return first Name is A null XAXAX.
   * </ul>
   *
   * <p>Method under test: {@link DataImporterCSV#readColumnsInfo(StreamEntityMapping, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DataImporterCSV.readColumnsInfo(StreamEntityMapping, InputStream)"})
  public void testReadColumnsInfo_thenReturnFirstNameIsANullXaxax() throws DBException {
    // Arrange
    IStreamDataImporterSite site = mock(IStreamDataImporterSite.class);
    when(site.getProcessorProperties()).thenReturn(new HashMap<>());

    DataImporterCSV dataImporterCSV = new DataImporterCSV();
    dataImporterCSV.init(site);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping entityMapping = new StreamEntityMapping(inputFile);
    ByteArrayInputStream inputStream =
        new ByteArrayInputStream(new byte[] {'A', 0, 0, 'X', 'A', 'X', 'A', 'X'});

    // Act
    List<StreamDataImporterColumnInfo> actualReadColumnsInfoResult =
        dataImporterCSV.readColumnsInfo(entityMapping, inputStream);

    // Assert
    verify(site).getProcessorProperties();
    assertEquals(1, actualReadColumnsInfoResult.size());
    StreamDataImporterColumnInfo getResult = actualReadColumnsInfoResult.get(0);
    DBPDataSource dataSource = getResult.getDataSource();
    Collection<? extends DBSInstance> availableInstances = dataSource.getAvailableInstances();
    assertEquals(1, availableInstances.size());
    assertTrue(availableInstances instanceof List);
    assertTrue(dataSource instanceof StreamDataSource);
    assertEquals("A\u0000XAXAX", getResult.getName());
    assertSame(dataSource, ((List<? extends DBSInstance>) availableInstances).get(0));
    StreamExecutionContext defaultContext = ((StreamDataSource) dataSource).getDefaultContext();
    assertSame(dataSource, defaultContext.getDataSource());
    StreamEntityMapping parentObject = getResult.getParentObject();
    assertSame(dataSource, parentObject.getDataSource());
    assertSame(dataSource, parentObject.getParentObject());
    assertSame(dataSource, defaultContext.getOwnerInstance());
  }

  /**
   * Test {@link DataImporterCSV#readColumnsInfo(StreamEntityMapping, InputStream)}.
   *
   * <ul>
   *   <li>Then return first Name is {@code AAXAXAX}.
   * </ul>
   *
   * <p>Method under test: {@link DataImporterCSV#readColumnsInfo(StreamEntityMapping, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DataImporterCSV.readColumnsInfo(StreamEntityMapping, InputStream)"})
  public void testReadColumnsInfo_thenReturnFirstNameIsAaxaxax() throws DBException {
    // Arrange
    IStreamDataImporterSite site = mock(IStreamDataImporterSite.class);
    when(site.getProcessorProperties()).thenReturn(new HashMap<>());

    DataImporterCSV dataImporterCSV = new DataImporterCSV();
    dataImporterCSV.init(site);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping entityMapping = new StreamEntityMapping(inputFile);
    ByteArrayInputStream inputStream =
        new ByteArrayInputStream(new byte[] {'A', 0, 'A', 'X', 'A', 'X', 'A', 'X'});

    // Act
    List<StreamDataImporterColumnInfo> actualReadColumnsInfoResult =
        dataImporterCSV.readColumnsInfo(entityMapping, inputStream);

    // Assert
    verify(site).getProcessorProperties();
    assertEquals(1, actualReadColumnsInfoResult.size());
    StreamDataImporterColumnInfo getResult = actualReadColumnsInfoResult.get(0);
    DBPDataSource dataSource = getResult.getDataSource();
    Collection<? extends DBSInstance> availableInstances = dataSource.getAvailableInstances();
    assertEquals(1, availableInstances.size());
    assertTrue(availableInstances instanceof List);
    assertTrue(dataSource instanceof StreamDataSource);
    assertEquals("AAXAXAX", getResult.getName());
    assertSame(dataSource, ((List<? extends DBSInstance>) availableInstances).get(0));
    StreamExecutionContext defaultContext = ((StreamDataSource) dataSource).getDefaultContext();
    assertSame(dataSource, defaultContext.getDataSource());
    StreamEntityMapping parentObject = getResult.getParentObject();
    assertSame(dataSource, parentObject.getDataSource());
    assertSame(dataSource, parentObject.getParentObject());
    assertSame(dataSource, defaultContext.getOwnerInstance());
  }

  /**
   * Test {@link DataImporterCSV#readColumnsInfo(StreamEntityMapping, InputStream)}.
   *
   * <ul>
   *   <li>Then return first Name is {@code AXAXAXA}.
   * </ul>
   *
   * <p>Method under test: {@link DataImporterCSV#readColumnsInfo(StreamEntityMapping, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DataImporterCSV.readColumnsInfo(StreamEntityMapping, InputStream)"})
  public void testReadColumnsInfo_thenReturnFirstNameIsAxaxaxa() throws DBException {
    // Arrange
    IStreamDataImporterSite site = mock(IStreamDataImporterSite.class);
    when(site.getProcessorProperties()).thenReturn(new HashMap<>());

    DataImporterCSV dataImporterCSV = new DataImporterCSV();
    dataImporterCSV.init(site);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping entityMapping = new StreamEntityMapping(inputFile);
    ByteArrayInputStream inputStream =
        new ByteArrayInputStream(new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 0});

    // Act
    List<StreamDataImporterColumnInfo> actualReadColumnsInfoResult =
        dataImporterCSV.readColumnsInfo(entityMapping, inputStream);

    // Assert
    verify(site).getProcessorProperties();
    assertEquals(1, actualReadColumnsInfoResult.size());
    StreamDataImporterColumnInfo getResult = actualReadColumnsInfoResult.get(0);
    DBPDataSource dataSource = getResult.getDataSource();
    Collection<? extends DBSInstance> availableInstances = dataSource.getAvailableInstances();
    assertEquals(1, availableInstances.size());
    assertTrue(availableInstances instanceof List);
    assertTrue(dataSource instanceof StreamDataSource);
    assertEquals("AXAXAXA", getResult.getName());
    assertSame(dataSource, ((List<? extends DBSInstance>) availableInstances).get(0));
    StreamExecutionContext defaultContext = ((StreamDataSource) dataSource).getDefaultContext();
    assertSame(dataSource, defaultContext.getDataSource());
    StreamEntityMapping parentObject = getResult.getParentObject();
    assertSame(dataSource, parentObject.getDataSource());
    assertSame(dataSource, parentObject.getParentObject());
    assertSame(dataSource, defaultContext.getOwnerInstance());
  }

  /**
   * Test {@link DataImporterCSV#readColumnsInfo(StreamEntityMapping, InputStream)}.
   *
   * <ul>
   *   <li>Then return first Name is {@code AXAXAXAX}.
   * </ul>
   *
   * <p>Method under test: {@link DataImporterCSV#readColumnsInfo(StreamEntityMapping, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DataImporterCSV.readColumnsInfo(StreamEntityMapping, InputStream)"})
  public void testReadColumnsInfo_thenReturnFirstNameIsAxaxaxax()
      throws UnsupportedEncodingException, DBException {
    // Arrange
    IStreamDataImporterSite site = mock(IStreamDataImporterSite.class);
    when(site.getProcessorProperties()).thenReturn(new HashMap<>());

    DataImporterCSV dataImporterCSV = new DataImporterCSV();
    dataImporterCSV.init(site);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping entityMapping = new StreamEntityMapping(inputFile);

    // Act
    List<StreamDataImporterColumnInfo> actualReadColumnsInfoResult =
        dataImporterCSV.readColumnsInfo(
            entityMapping, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    // Assert
    verify(site).getProcessorProperties();
    assertEquals(1, actualReadColumnsInfoResult.size());
    StreamDataImporterColumnInfo getResult = actualReadColumnsInfoResult.get(0);
    DBPDataSource dataSource = getResult.getDataSource();
    Collection<? extends DBSInstance> availableInstances = dataSource.getAvailableInstances();
    assertEquals(1, availableInstances.size());
    assertTrue(availableInstances instanceof List);
    assertTrue(dataSource instanceof StreamDataSource);
    assertEquals("AXAXAXAX", getResult.getName());
    assertSame(dataSource, ((List<? extends DBSInstance>) availableInstances).get(0));
    StreamExecutionContext defaultContext = ((StreamDataSource) dataSource).getDefaultContext();
    assertSame(dataSource, defaultContext.getDataSource());
    StreamEntityMapping parentObject = getResult.getParentObject();
    assertSame(dataSource, parentObject.getDataSource());
    assertSame(dataSource, parentObject.getParentObject());
    assertSame(dataSource, defaultContext.getOwnerInstance());
  }

  /**
   * Test {@link DataImporterCSV#readColumnsInfo(StreamEntityMapping, InputStream)}.
   *
   * <ul>
   *   <li>Then return first Name is {@code XAXAXAX}.
   * </ul>
   *
   * <p>Method under test: {@link DataImporterCSV#readColumnsInfo(StreamEntityMapping, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DataImporterCSV.readColumnsInfo(StreamEntityMapping, InputStream)"})
  public void testReadColumnsInfo_thenReturnFirstNameIsXaxaxax() throws DBException {
    // Arrange
    IStreamDataImporterSite site = mock(IStreamDataImporterSite.class);
    when(site.getProcessorProperties()).thenReturn(new HashMap<>());

    DataImporterCSV dataImporterCSV = new DataImporterCSV();
    dataImporterCSV.init(site);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping entityMapping = new StreamEntityMapping(inputFile);
    ByteArrayInputStream inputStream =
        new ByteArrayInputStream(new byte[] {0, 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Act
    List<StreamDataImporterColumnInfo> actualReadColumnsInfoResult =
        dataImporterCSV.readColumnsInfo(entityMapping, inputStream);

    // Assert
    verify(site).getProcessorProperties();
    assertEquals(1, actualReadColumnsInfoResult.size());
    StreamDataImporterColumnInfo getResult = actualReadColumnsInfoResult.get(0);
    DBPDataSource dataSource = getResult.getDataSource();
    Collection<? extends DBSInstance> availableInstances = dataSource.getAvailableInstances();
    assertEquals(1, availableInstances.size());
    assertTrue(availableInstances instanceof List);
    assertTrue(dataSource instanceof StreamDataSource);
    assertEquals("XAXAXAX", getResult.getName());
    assertSame(dataSource, ((List<? extends DBSInstance>) availableInstances).get(0));
    StreamExecutionContext defaultContext = ((StreamDataSource) dataSource).getDefaultContext();
    assertSame(dataSource, defaultContext.getDataSource());
    StreamEntityMapping parentObject = getResult.getParentObject();
    assertSame(dataSource, parentObject.getDataSource());
    assertSame(dataSource, parentObject.getParentObject());
    assertSame(dataSource, defaultContext.getOwnerInstance());
  }

  /**
   * Test {@link DataImporterCSV#readColumnsInfo(StreamEntityMapping, InputStream)}.
   *
   * <ul>
   *   <li>Then return first Name is {@code XAXAXAX}.
   * </ul>
   *
   * <p>Method under test: {@link DataImporterCSV#readColumnsInfo(StreamEntityMapping, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DataImporterCSV.readColumnsInfo(StreamEntityMapping, InputStream)"})
  public void testReadColumnsInfo_thenReturnFirstNameIsXaxaxax2() throws DBException {
    // Arrange
    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("columnTypeSamplesCount", "42");

    IStreamDataImporterSite site = mock(IStreamDataImporterSite.class);
    when(site.getProcessorProperties()).thenReturn(stringObjectMap);

    DataImporterCSV dataImporterCSV = new DataImporterCSV();
    dataImporterCSV.init(site);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping entityMapping = new StreamEntityMapping(inputFile);
    ByteArrayInputStream inputStream =
        new ByteArrayInputStream(new byte[] {0, 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Act
    List<StreamDataImporterColumnInfo> actualReadColumnsInfoResult =
        dataImporterCSV.readColumnsInfo(entityMapping, inputStream);

    // Assert
    verify(site).getProcessorProperties();
    assertEquals(1, actualReadColumnsInfoResult.size());
    StreamDataImporterColumnInfo getResult = actualReadColumnsInfoResult.get(0);
    DBPDataSource dataSource = getResult.getDataSource();
    Collection<? extends DBSInstance> availableInstances = dataSource.getAvailableInstances();
    assertEquals(1, availableInstances.size());
    assertTrue(availableInstances instanceof List);
    assertTrue(dataSource instanceof StreamDataSource);
    assertEquals("XAXAXAX", getResult.getName());
    assertSame(dataSource, ((List<? extends DBSInstance>) availableInstances).get(0));
    StreamExecutionContext defaultContext = ((StreamDataSource) dataSource).getDefaultContext();
    assertSame(dataSource, defaultContext.getDataSource());
    StreamEntityMapping parentObject = getResult.getParentObject();
    assertSame(dataSource, parentObject.getDataSource());
    assertSame(dataSource, parentObject.getParentObject());
    assertSame(dataSource, defaultContext.getOwnerInstance());
  }

  /**
   * Test {@link DataImporterCSV#readColumnsInfo(StreamEntityMapping, InputStream)}.
   *
   * <ul>
   *   <li>When {@link ByteArrayInputStream#ByteArrayInputStream(byte[])} with empty array of {@code
   *       byte}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DataImporterCSV#readColumnsInfo(StreamEntityMapping, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DataImporterCSV.readColumnsInfo(StreamEntityMapping, InputStream)"})
  public void testReadColumnsInfo_whenByteArrayInputStreamWithEmptyArrayOfByte_thenReturnEmpty()
      throws DBException {
    // Arrange
    IStreamDataImporterSite site = mock(IStreamDataImporterSite.class);
    when(site.getProcessorProperties()).thenReturn(new HashMap<>());

    DataImporterCSV dataImporterCSV = new DataImporterCSV();
    dataImporterCSV.init(site);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping entityMapping = new StreamEntityMapping(inputFile);
    ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[] {});

    // Act
    List<StreamDataImporterColumnInfo> actualReadColumnsInfoResult =
        dataImporterCSV.readColumnsInfo(entityMapping, inputStream);

    // Assert
    verify(site).getProcessorProperties();
    assertTrue(actualReadColumnsInfoResult.isEmpty());
  }

  /**
   * Test {@link DataImporterCSV#readColumnsInfo(StreamEntityMapping, InputStream)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link DataImporterCSV#readColumnsInfo(StreamEntityMapping, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DataImporterCSV.readColumnsInfo(StreamEntityMapping, InputStream)"})
  public void testReadColumnsInfo_whenNull_thenThrowDBException() throws DBException {
    // Arrange
    IStreamDataImporterSite site = mock(IStreamDataImporterSite.class);
    when(site.getProcessorProperties()).thenReturn(new HashMap<>());

    DataImporterCSV dataImporterCSV = new DataImporterCSV();
    dataImporterCSV.init(site);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> dataImporterCSV.readColumnsInfo(new StreamEntityMapping(inputFile), null));
    verify(site).getProcessorProperties();
  }
}
