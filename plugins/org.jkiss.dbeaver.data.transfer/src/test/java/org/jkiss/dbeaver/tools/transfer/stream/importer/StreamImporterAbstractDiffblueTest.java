package org.jkiss.dbeaver.tools.transfer.stream.importer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.impl.local.LocalStatement;
import org.jkiss.dbeaver.model.struct.DBSDataManipulator;
import org.jkiss.dbeaver.tools.transfer.IDataTransferConsumer;
import org.jkiss.dbeaver.tools.transfer.database.DatabaseTransferConsumer;
import org.jkiss.dbeaver.tools.transfer.database.DatabaseTransferConsumer.ColumnMapping;
import org.jkiss.dbeaver.tools.transfer.stream.IStreamDataImporterSite;
import org.jkiss.dbeaver.tools.transfer.stream.StreamEntityMapping;
import org.jkiss.dbeaver.tools.transfer.stream.StreamTransferResultSet;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class StreamImporterAbstractDiffblueTest {
  /**
   * Test {@link StreamImporterAbstract#getSite()}.
   *
   * <p>Method under test: {@link StreamImporterAbstract#getSite()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStreamDataImporterSite StreamImporterAbstract.getSite()"})
  public void testGetSite() {
    // Arrange, Act and Assert
    assertNull(new DataImporterCSV().getSite());
  }

  /**
   * Test {@link StreamImporterAbstract#init(IStreamDataImporterSite)}.
   *
   * <p>Method under test: {@link StreamImporterAbstract#init(IStreamDataImporterSite)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamImporterAbstract.init(IStreamDataImporterSite)"})
  public void testInit() throws DBException {
    // Arrange
    DataImporterCSV dataImporterCSV = new DataImporterCSV();
    IStreamDataImporterSite site = mock(IStreamDataImporterSite.class);

    // Act
    dataImporterCSV.init(site);

    // Assert
    assertSame(site, dataImporterCSV.getSite());
  }

  /**
   * Test {@link StreamImporterAbstract#getTimeStampFormat(Map, String)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link HashMap#HashMap()} empty string is {@code 42}.
   *   <li>Then return Zone is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link StreamImporterAbstract#getTimeStampFormat(Map, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateTimeFormatter StreamImporterAbstract.getTimeStampFormat(Map, String)"})
  public void testGetTimeStampFormat_given42_whenHashMapEmptyStringIs42_thenReturnZoneIsNull() {
    // Arrange
    DataImporterCSV dataImporterCSV = new DataImporterCSV();

    HashMap<String, Object> properties = new HashMap<>();
    properties.put("", "42");

    // Act
    DateTimeFormatter actualTimeStampFormat = dataImporterCSV.getTimeStampFormat(properties, "");

    // Assert
    assertNull(actualTimeStampFormat.getZone());
    assertNull(actualTimeStampFormat.getChronology());
    assertNull(actualTimeStampFormat.getResolverFields());
    assertEquals(ResolverStyle.SMART, actualTimeStampFormat.getResolverStyle());
  }

  /**
   * Test {@link StreamImporterAbstract#getTimeStampFormat(Map, String)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>When {@link HashMap#HashMap()} empty string is {@code foo}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link StreamImporterAbstract#getTimeStampFormat(Map, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateTimeFormatter StreamImporterAbstract.getTimeStampFormat(Map, String)"})
  public void testGetTimeStampFormat_givenFoo_whenHashMapEmptyStringIsFoo_thenReturnNull() {
    // Arrange
    DataImporterCSV dataImporterCSV = new DataImporterCSV();

    HashMap<String, Object> properties = new HashMap<>();
    properties.put("", "foo");

    // Act and Assert
    assertNull(dataImporterCSV.getTimeStampFormat(properties, ""));
  }

  /**
   * Test {@link StreamImporterAbstract#getTimeStampFormat(Map, String)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link HashMap#HashMap()} empty string is one.
   *   <li>Then return Zone is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link StreamImporterAbstract#getTimeStampFormat(Map, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateTimeFormatter StreamImporterAbstract.getTimeStampFormat(Map, String)"})
  public void testGetTimeStampFormat_givenOne_whenHashMapEmptyStringIsOne_thenReturnZoneIsNull() {
    // Arrange
    DataImporterCSV dataImporterCSV = new DataImporterCSV();

    HashMap<String, Object> properties = new HashMap<>();
    properties.put("", 1);

    // Act
    DateTimeFormatter actualTimeStampFormat = dataImporterCSV.getTimeStampFormat(properties, "");

    // Assert
    assertNull(actualTimeStampFormat.getZone());
    assertNull(actualTimeStampFormat.getChronology());
    assertNull(actualTimeStampFormat.getResolverFields());
    assertEquals(ResolverStyle.SMART, actualTimeStampFormat.getResolverStyle());
  }

  /**
   * Test {@link StreamImporterAbstract#getTimeStampFormat(Map, String)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link StreamImporterAbstract#getTimeStampFormat(Map, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DateTimeFormatter StreamImporterAbstract.getTimeStampFormat(Map, String)"})
  public void testGetTimeStampFormat_whenHashMap_thenReturnNull() {
    // Arrange
    DataImporterCSV dataImporterCSV = new DataImporterCSV();

    // Act and Assert
    assertNull(dataImporterCSV.getTimeStampFormat(new HashMap<>(), "Format Prop Name"));
  }

  /**
   * Test {@link StreamImporterAbstract#applyTransformHints(StreamTransferResultSet,
   * IDataTransferConsumer, Map, String, String)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   *   <li>Then calls {@link StreamTransferResultSet#getAttributeMappings()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * StreamImporterAbstract#applyTransformHints(StreamTransferResultSet, IDataTransferConsumer, Map,
   * String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamImporterAbstract.applyTransformHints(StreamTransferResultSet, IDataTransferConsumer, Map, String, String)"
  })
  public void testApplyTransformHints_givenArrayList_thenCallsGetAttributeMappings()
      throws DBException {
    // Arrange
    DataImporterCSV dataImporterCSV = new DataImporterCSV();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile);

    StreamTransferResultSet resultSet = mock(StreamTransferResultSet.class);
    when(resultSet.getAttributeMappings()).thenReturn(new ArrayList<>());

    DatabaseTransferConsumer consumer = mock(DatabaseTransferConsumer.class);
    when(consumer.getTargetObject()).thenReturn(mock(DBSDataManipulator.class));
    when(consumer.getColumnMappings()).thenReturn(new ColumnMapping[] {mock(ColumnMapping.class)});

    // Act
    dataImporterCSV.applyTransformHints(
        resultSet, consumer, new HashMap<>(), "Format Prop Name", "UTC");

    // Assert
    verify(consumer).getColumnMappings();
    verify(consumer).getTargetObject();
    verify(resultSet).getAttributeMappings();
  }

  /**
   * Test {@link StreamImporterAbstract#applyTransformHints(StreamTransferResultSet,
   * IDataTransferConsumer, Map, String, String)}.
   *
   * <ul>
   *   <li>Given array of {@link ColumnMapping} with {@link ColumnMapping}.
   * </ul>
   *
   * <p>Method under test: {@link
   * StreamImporterAbstract#applyTransformHints(StreamTransferResultSet, IDataTransferConsumer, Map,
   * String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamImporterAbstract.applyTransformHints(StreamTransferResultSet, IDataTransferConsumer, Map, String, String)"
  })
  public void testApplyTransformHints_givenArrayOfColumnMappingWithColumnMapping()
      throws DBException {
    // Arrange
    DataImporterCSV dataImporterCSV = new DataImporterCSV();
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferResultSet resultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile));

    DatabaseTransferConsumer consumer = mock(DatabaseTransferConsumer.class);
    when(consumer.getTargetObject()).thenReturn(mock(DBSDataManipulator.class));
    when(consumer.getColumnMappings()).thenReturn(new ColumnMapping[] {mock(ColumnMapping.class)});

    // Act
    dataImporterCSV.applyTransformHints(
        resultSet, consumer, new HashMap<>(), "Format Prop Name", "UTC");

    // Assert
    verify(consumer).getColumnMappings();
    verify(consumer).getTargetObject();
  }

  /**
   * Test {@link StreamImporterAbstract#applyTransformHints(StreamTransferResultSet,
   * IDataTransferConsumer, Map, String, String)}.
   *
   * <ul>
   *   <li>Given array of {@link ColumnMapping} with {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * StreamImporterAbstract#applyTransformHints(StreamTransferResultSet, IDataTransferConsumer, Map,
   * String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamImporterAbstract.applyTransformHints(StreamTransferResultSet, IDataTransferConsumer, Map, String, String)"
  })
  public void testApplyTransformHints_givenArrayOfColumnMappingWithNull() throws DBException {
    // Arrange
    DataImporterCSV dataImporterCSV = new DataImporterCSV();
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferResultSet resultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile));

    DatabaseTransferConsumer consumer = mock(DatabaseTransferConsumer.class);
    when(consumer.getTargetObject()).thenReturn(mock(DBSDataManipulator.class));
    when(consumer.getColumnMappings()).thenReturn(new ColumnMapping[] {null});

    // Act
    dataImporterCSV.applyTransformHints(
        resultSet, consumer, new HashMap<>(), "Format Prop Name", "UTC");

    // Assert
    verify(consumer).getColumnMappings();
    verify(consumer).getTargetObject();
  }

  /**
   * Test {@link StreamImporterAbstract#applyTransformHints(StreamTransferResultSet,
   * IDataTransferConsumer, Map, String, String)}.
   *
   * <ul>
   *   <li>Given array of {@link ColumnMapping} with {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * StreamImporterAbstract#applyTransformHints(StreamTransferResultSet, IDataTransferConsumer, Map,
   * String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamImporterAbstract.applyTransformHints(StreamTransferResultSet, IDataTransferConsumer, Map, String, String)"
  })
  public void testApplyTransformHints_givenArrayOfColumnMappingWithNull2() throws DBException {
    // Arrange
    DataImporterCSV dataImporterCSV = new DataImporterCSV();
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferResultSet resultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile));

    DatabaseTransferConsumer consumer = mock(DatabaseTransferConsumer.class);
    when(consumer.getTargetObject()).thenReturn(mock(DBSDataManipulator.class));
    when(consumer.getColumnMappings()).thenReturn(new ColumnMapping[] {null});

    // Act
    dataImporterCSV.applyTransformHints(resultSet, consumer, new HashMap<>(), null, "UTC");

    // Assert
    verify(consumer).getColumnMappings();
    verify(consumer).getTargetObject();
  }

  /**
   * Test {@link StreamImporterAbstract#applyTransformHints(StreamTransferResultSet,
   * IDataTransferConsumer, Map, String, String)}.
   *
   * <ul>
   *   <li>Given array of {@link ColumnMapping} with {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * StreamImporterAbstract#applyTransformHints(StreamTransferResultSet, IDataTransferConsumer, Map,
   * String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamImporterAbstract.applyTransformHints(StreamTransferResultSet, IDataTransferConsumer, Map, String, String)"
  })
  public void testApplyTransformHints_givenArrayOfColumnMappingWithNull3() throws DBException {
    // Arrange
    DataImporterCSV dataImporterCSV = new DataImporterCSV();
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferResultSet resultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile));

    DatabaseTransferConsumer consumer = mock(DatabaseTransferConsumer.class);
    when(consumer.getTargetObject()).thenReturn(mock(DBSDataManipulator.class));
    when(consumer.getColumnMappings()).thenReturn(new ColumnMapping[] {null});

    // Act
    dataImporterCSV.applyTransformHints(
        resultSet, consumer, new HashMap<>(), "Format Prop Name", null);

    // Assert
    verify(consumer).getColumnMappings();
    verify(consumer).getTargetObject();
  }
}
