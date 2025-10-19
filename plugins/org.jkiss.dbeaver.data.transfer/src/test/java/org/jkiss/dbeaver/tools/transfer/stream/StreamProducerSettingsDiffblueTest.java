package org.jkiss.dbeaver.tools.transfer.stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.Log;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.DBRRunnableContext;
import org.jkiss.dbeaver.model.runtime.DBRRunnableWithProgress;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.runtime.SubTaskProgressMonitor;
import org.jkiss.dbeaver.tools.transfer.DataTransferPipe;
import org.jkiss.dbeaver.tools.transfer.DataTransferSettings;
import org.jkiss.dbeaver.tools.transfer.DataTransferState;
import org.jkiss.dbeaver.tools.transfer.IDataTransferProcessor;
import org.jkiss.dbeaver.tools.transfer.database.DatabaseTransferConsumer;
import org.jkiss.dbeaver.tools.transfer.registry.DataTransferProcessorDescriptor;
import org.jkiss.dbeaver.tools.transfer.stream.exporter.DataExporterCSV;
import org.jkiss.dbeaver.tools.transfer.stream.importer.DataImporterCSV;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class StreamProducerSettingsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link StreamProducerSettings}
   *   <li>{@link StreamProducerSettings#setMaxRows(int)}
   *   <li>{@link StreamProducerSettings#setProcessorProperties(Map)}
   *   <li>{@link StreamProducerSettings#getMaxRows()}
   *   <li>{@link StreamProducerSettings#getProcessorProperties()}
   *   <li>{@link StreamProducerSettings#getSettingsSummary()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.<init>()",
    "int StreamProducerSettings.getMaxRows()",
    "Map StreamProducerSettings.getProcessorProperties()",
    "String StreamProducerSettings.getSettingsSummary()",
    "void StreamProducerSettings.setMaxRows(int)",
    "void StreamProducerSettings.setProcessorProperties(Map)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    StreamProducerSettings actualStreamProducerSettings = new StreamProducerSettings();
    actualStreamProducerSettings.setMaxRows(3);
    HashMap<String, Object> processorProperties = new HashMap<>();
    actualStreamProducerSettings.setProcessorProperties(processorProperties);
    int actualMaxRows = actualStreamProducerSettings.getMaxRows();
    Map<String, Object> actualProcessorProperties =
        actualStreamProducerSettings.getProcessorProperties();

    // Assert
    assertEquals("", actualStreamProducerSettings.getSettingsSummary());
    assertEquals(3, actualMaxRows);
    assertTrue(actualProcessorProperties.isEmpty());
    assertSame(processorProperties, actualProcessorProperties);
  }

  /**
   * Test {@link StreamProducerSettings#loadSettings(DBRRunnableContext, DataTransferSettings,
   * Map)}.
   *
   * <p>Method under test: {@link StreamProducerSettings#loadSettings(DBRRunnableContext,
   * DataTransferSettings, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.loadSettings(DBRRunnableContext, DataTransferSettings, Map)"
  })
  public void testLoadSettings() throws InterruptedException, InvocationTargetException {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();

    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);
    Throwable throwable = new Throwable("Error loading stream producer settings", new Throwable());
    doThrow(new InvocationTargetException(throwable, "foo"))
        .when(runnableContext)
        .run(anyBoolean(), anyBoolean(), Mockito.<DBRRunnableWithProgress>any());

    DataTransferSettings dataTransferSettings = mock(DataTransferSettings.class);
    when(dataTransferSettings.getProcessorProperties()).thenReturn(new HashMap<>());

    // Act
    streamProducerSettings.loadSettings(runnableContext, dataTransferSettings, new HashMap<>());

    // Assert
    verify(runnableContext).run(eq(true), eq(true), isA(DBRRunnableWithProgress.class));
    verify(dataTransferSettings).getProcessorProperties();
    assertTrue(streamProducerSettings.getProcessorProperties().isEmpty());
  }

  /**
   * Test {@link StreamProducerSettings#loadSettings(DBRRunnableContext, DataTransferSettings,
   * Map)}.
   *
   * <p>Method under test: {@link StreamProducerSettings#loadSettings(DBRRunnableContext,
   * DataTransferSettings, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.loadSettings(DBRRunnableContext, DataTransferSettings, Map)"
  })
  public void testLoadSettings2() throws InterruptedException, InvocationTargetException {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();

    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);
    doThrow(
            new InvocationTargetException(
                new Throwable(), "Error loading stream producer settings"))
        .when(runnableContext)
        .run(anyBoolean(), anyBoolean(), Mockito.<DBRRunnableWithProgress>any());

    DataTransferSettings dataTransferSettings = mock(DataTransferSettings.class);
    when(dataTransferSettings.getProcessorProperties()).thenReturn(new HashMap<>());

    // Act
    streamProducerSettings.loadSettings(runnableContext, dataTransferSettings, new HashMap<>());

    // Assert
    verify(runnableContext).run(eq(true), eq(true), isA(DBRRunnableWithProgress.class));
    verify(dataTransferSettings).getProcessorProperties();
    assertTrue(streamProducerSettings.getProcessorProperties().isEmpty());
  }

  /**
   * Test {@link StreamProducerSettings#loadSettings(DBRRunnableContext, DataTransferSettings,
   * Map)}.
   *
   * <p>Method under test: {@link StreamProducerSettings#loadSettings(DBRRunnableContext,
   * DataTransferSettings, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.loadSettings(DBRRunnableContext, DataTransferSettings, Map)"
  })
  public void testLoadSettings3() throws InterruptedException, InvocationTargetException {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();

    Throwable throwable = new Throwable();
    Throwable throwable2 = new Throwable("Error loading stream producer settings", new Throwable());
    throwable.initCause(throwable2);

    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);
    doThrow(new InvocationTargetException(throwable, "foo"))
        .when(runnableContext)
        .run(anyBoolean(), anyBoolean(), Mockito.<DBRRunnableWithProgress>any());

    DataTransferSettings dataTransferSettings = mock(DataTransferSettings.class);
    when(dataTransferSettings.getProcessorProperties()).thenReturn(new HashMap<>());

    // Act
    streamProducerSettings.loadSettings(runnableContext, dataTransferSettings, new HashMap<>());

    // Assert
    verify(runnableContext).run(eq(true), eq(true), isA(DBRRunnableWithProgress.class));
    verify(dataTransferSettings).getProcessorProperties();
    assertTrue(streamProducerSettings.getProcessorProperties().isEmpty());
  }

  /**
   * Test {@link StreamProducerSettings#loadSettings(DBRRunnableContext, DataTransferSettings,
   * Map)}.
   *
   * <ul>
   *   <li>Given {@link InterruptedException#InterruptedException()}.
   * </ul>
   *
   * <p>Method under test: {@link StreamProducerSettings#loadSettings(DBRRunnableContext,
   * DataTransferSettings, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.loadSettings(DBRRunnableContext, DataTransferSettings, Map)"
  })
  public void testLoadSettings_givenInterruptedException()
      throws InterruptedException, InvocationTargetException {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();

    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);
    doThrow(new InterruptedException())
        .when(runnableContext)
        .run(anyBoolean(), anyBoolean(), Mockito.<DBRRunnableWithProgress>any());

    DataTransferSettings dataTransferSettings = mock(DataTransferSettings.class);
    when(dataTransferSettings.getProcessorProperties()).thenReturn(new HashMap<>());

    // Act
    streamProducerSettings.loadSettings(runnableContext, dataTransferSettings, new HashMap<>());

    // Assert
    verify(runnableContext).run(eq(true), eq(true), isA(DBRRunnableWithProgress.class));
    verify(dataTransferSettings).getProcessorProperties();
    assertTrue(streamProducerSettings.getProcessorProperties().isEmpty());
  }

  /**
   * Test {@link StreamProducerSettings#loadSettings(DBRRunnableContext, DataTransferSettings,
   * Map)}.
   *
   * <ul>
   *   <li>Given {@link InvocationTargetException#InvocationTargetException(Throwable, String)} with
   *       {@link Throwable#Throwable()} and {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link StreamProducerSettings#loadSettings(DBRRunnableContext,
   * DataTransferSettings, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.loadSettings(DBRRunnableContext, DataTransferSettings, Map)"
  })
  public void testLoadSettings_givenInvocationTargetExceptionWithThrowableAndFoo()
      throws InterruptedException, InvocationTargetException {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();

    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);
    doThrow(new InvocationTargetException(new Throwable(), "foo"))
        .when(runnableContext)
        .run(anyBoolean(), anyBoolean(), Mockito.<DBRRunnableWithProgress>any());

    DataTransferSettings dataTransferSettings = mock(DataTransferSettings.class);
    when(dataTransferSettings.getProcessorProperties()).thenReturn(new HashMap<>());

    // Act
    streamProducerSettings.loadSettings(runnableContext, dataTransferSettings, new HashMap<>());

    // Assert
    verify(runnableContext).run(eq(true), eq(true), isA(DBRRunnableWithProgress.class));
    verify(dataTransferSettings).getProcessorProperties();
    assertTrue(streamProducerSettings.getProcessorProperties().isEmpty());
  }

  /**
   * Test {@link StreamProducerSettings#loadSettings(DBRRunnableContext, DataTransferSettings,
   * Map)}.
   *
   * <ul>
   *   <li>Given {@link Throwable#Throwable()} initCause {@link Throwable#Throwable()}.
   * </ul>
   *
   * <p>Method under test: {@link StreamProducerSettings#loadSettings(DBRRunnableContext,
   * DataTransferSettings, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.loadSettings(DBRRunnableContext, DataTransferSettings, Map)"
  })
  public void testLoadSettings_givenThrowableInitCauseThrowable()
      throws InterruptedException, InvocationTargetException {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();

    Throwable throwable = new Throwable();
    throwable.initCause(new Throwable());

    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);
    doThrow(new InvocationTargetException(throwable, "foo"))
        .when(runnableContext)
        .run(anyBoolean(), anyBoolean(), Mockito.<DBRRunnableWithProgress>any());

    DataTransferSettings dataTransferSettings = mock(DataTransferSettings.class);
    when(dataTransferSettings.getProcessorProperties()).thenReturn(new HashMap<>());

    // Act
    streamProducerSettings.loadSettings(runnableContext, dataTransferSettings, new HashMap<>());

    // Assert
    verify(runnableContext).run(eq(true), eq(true), isA(DBRRunnableWithProgress.class));
    verify(dataTransferSettings).getProcessorProperties();
    assertTrue(streamProducerSettings.getProcessorProperties().isEmpty());
  }

  /**
   * Test {@link StreamProducerSettings#loadSettings(DBRRunnableContext, DataTransferSettings,
   * Map)}.
   *
   * <ul>
   *   <li>Given {@link Throwable#Throwable(String)} with {@code Error loading stream producer
   *       settings}.
   * </ul>
   *
   * <p>Method under test: {@link StreamProducerSettings#loadSettings(DBRRunnableContext,
   * DataTransferSettings, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.loadSettings(DBRRunnableContext, DataTransferSettings, Map)"
  })
  public void testLoadSettings_givenThrowableWithErrorLoadingStreamProducerSettings()
      throws InterruptedException, InvocationTargetException {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();

    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);
    Throwable throwable =
        new Throwable(
            "Error loading stream producer settings",
            new Throwable("Error loading stream producer settings"));
    doThrow(new InvocationTargetException(throwable, "foo"))
        .when(runnableContext)
        .run(anyBoolean(), anyBoolean(), Mockito.<DBRRunnableWithProgress>any());

    DataTransferSettings dataTransferSettings = mock(DataTransferSettings.class);
    when(dataTransferSettings.getProcessorProperties()).thenReturn(new HashMap<>());

    // Act
    streamProducerSettings.loadSettings(runnableContext, dataTransferSettings, new HashMap<>());

    // Assert
    verify(runnableContext).run(eq(true), eq(true), isA(DBRRunnableWithProgress.class));
    verify(dataTransferSettings).getProcessorProperties();
    assertTrue(streamProducerSettings.getProcessorProperties().isEmpty());
  }

  /**
   * Test {@link StreamProducerSettings#loadSettings(DBRRunnableContext, DataTransferSettings,
   * Map)}.
   *
   * <ul>
   *   <li>When {@link DBRRunnableContext} {@link DBRRunnableContext#run(boolean, boolean,
   *       DBRRunnableWithProgress)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link StreamProducerSettings#loadSettings(DBRRunnableContext,
   * DataTransferSettings, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.loadSettings(DBRRunnableContext, DataTransferSettings, Map)"
  })
  public void testLoadSettings_whenDBRRunnableContextRunDoesNothing()
      throws InterruptedException, InvocationTargetException {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();

    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);
    doNothing()
        .when(runnableContext)
        .run(anyBoolean(), anyBoolean(), Mockito.<DBRRunnableWithProgress>any());

    DataTransferSettings dataTransferSettings = mock(DataTransferSettings.class);
    when(dataTransferSettings.getProcessorProperties()).thenReturn(new HashMap<>());

    // Act
    streamProducerSettings.loadSettings(runnableContext, dataTransferSettings, new HashMap<>());

    // Assert
    verify(runnableContext).run(eq(true), eq(true), isA(DBRRunnableWithProgress.class));
    verify(dataTransferSettings).getProcessorProperties();
    assertTrue(streamProducerSettings.getProcessorProperties().isEmpty());
  }

  /**
   * Test {@link StreamProducerSettings#updateMappingsFromStream(DBRProgressMonitor,
   * DataTransferSettings)}.
   *
   * <p>Method under test: {@link
   * StreamProducerSettings#updateMappingsFromStream(DBRProgressMonitor, DataTransferSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.updateMappingsFromStream(DBRProgressMonitor, DataTransferSettings)"
  })
  public void testUpdateMappingsFromStream() {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ArrayList<DataTransferPipe> dataTransferPipeList = new ArrayList<>();
    StreamTransferProducer producer = new StreamTransferProducer();
    DataTransferPipe dataTransferPipe =
        new DataTransferPipe(producer, new DatabaseTransferConsumer());
    dataTransferPipeList.add(dataTransferPipe);

    DataTransferSettings dataTransferSettings = mock(DataTransferSettings.class);
    when(dataTransferSettings.getDataPipes()).thenReturn(dataTransferPipeList);

    // Act
    streamProducerSettings.updateMappingsFromStream(monitor, dataTransferSettings);

    // Assert
    verify(dataTransferSettings).getDataPipes();
  }

  /**
   * Test {@link StreamProducerSettings#updateMappingsFromStream(DBRProgressMonitor,
   * DataTransferSettings)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   *   <li>Then calls {@link DataTransferSettings#getDataPipes()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * StreamProducerSettings#updateMappingsFromStream(DBRProgressMonitor, DataTransferSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.updateMappingsFromStream(DBRProgressMonitor, DataTransferSettings)"
  })
  public void testUpdateMappingsFromStream_givenArrayList_thenCallsGetDataPipes() {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DataTransferSettings dataTransferSettings = mock(DataTransferSettings.class);
    when(dataTransferSettings.getDataPipes()).thenReturn(new ArrayList<>());

    // Act
    streamProducerSettings.updateMappingsFromStream(monitor, dataTransferSettings);

    // Assert
    verify(dataTransferSettings).getDataPipes();
  }

  /**
   * Test {@link StreamProducerSettings#updateMappingsFromStream(DBRProgressMonitor,
   * DataTransferSettings)}.
   *
   * <ul>
   *   <li>Then calls {@link DataTransferSettings#getProcessor()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * StreamProducerSettings#updateMappingsFromStream(DBRProgressMonitor, DataTransferSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.updateMappingsFromStream(DBRProgressMonitor, DataTransferSettings)"
  })
  public void testUpdateMappingsFromStream_thenCallsGetProcessor() {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ArrayList<DataTransferPipe> dataTransferPipeList = new ArrayList<>();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferProducer producer =
        new StreamTransferProducer(new StreamEntityMapping(inputFile));
    DataTransferPipe dataTransferPipe =
        new DataTransferPipe(producer, new DatabaseTransferConsumer());
    dataTransferPipeList.add(dataTransferPipe);

    DataTransferSettings dataTransferSettings = mock(DataTransferSettings.class);
    when(dataTransferSettings.getState()).thenReturn(new DataTransferState());
    when(dataTransferSettings.getProcessor()).thenReturn(null);
    when(dataTransferSettings.getDataPipes()).thenReturn(dataTransferPipeList);

    // Act
    streamProducerSettings.updateMappingsFromStream(monitor, dataTransferSettings);

    // Assert
    verify(dataTransferSettings).getDataPipes();
    verify(dataTransferSettings).getProcessor();
    verify(dataTransferSettings).getState();
  }

  /**
   * Test {@link StreamProducerSettings#extractExtraEntities(DBRProgressMonitor,
   * StreamEntityMapping, DataTransferSettings, Collection)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link StreamProducerSettings#extractExtraEntities(DBRProgressMonitor,
   * StreamEntityMapping, DataTransferSettings, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean StreamProducerSettings.extractExtraEntities(DBRProgressMonitor, StreamEntityMapping, DataTransferSettings, Collection)"
  })
  public void testExtractExtraEntities_thenReturnFalse() {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping entityMapping = new StreamEntityMapping(inputFile, "Entity Name", true);

    // Act and Assert
    assertFalse(
        streamProducerSettings.extractExtraEntities(
            monitor, entityMapping, null, new ArrayList<>()));
  }

  /**
   * Test {@link StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, DataTransferSettings)} with {@code monitor}, {@code producer}, {@code
   * dataTransferSettings}.
   *
   * <p>Method under test: {@link
   * StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, DataTransferSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.updateProducerSettingsFromStream(DBRProgressMonitor, StreamTransferProducer, DataTransferSettings)"
  })
  public void testUpdateProducerSettingsFromStreamWithMonitorProducerDataTransferSettings() {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    StreamTransferProducer producer = new StreamTransferProducer();

    DataTransferSettings dataTransferSettings = mock(DataTransferSettings.class);
    when(dataTransferSettings.getState()).thenReturn(new DataTransferState());
    when(dataTransferSettings.getProcessor()).thenReturn(null);

    // Act
    streamProducerSettings.updateProducerSettingsFromStream(
        monitor, producer, dataTransferSettings);

    // Assert
    verify(dataTransferSettings).getProcessor();
    verify(dataTransferSettings).getState();
  }

  /**
   * Test {@link StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, DataTransferSettings)} with {@code monitor}, {@code producer}, {@code
   * dataTransferSettings}.
   *
   * <p>Method under test: {@link
   * StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, DataTransferSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.updateProducerSettingsFromStream(DBRProgressMonitor, StreamTransferProducer, DataTransferSettings)"
  })
  public void testUpdateProducerSettingsFromStreamWithMonitorProducerDataTransferSettings2() {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    StreamTransferProducer producer = new StreamTransferProducer();

    DataTransferProcessorDescriptor dataTransferProcessorDescriptor =
        mock(DataTransferProcessorDescriptor.class);
    when(dataTransferProcessorDescriptor.getInstance()).thenReturn(new DataExporterCSV());

    DataTransferSettings dataTransferSettings = mock(DataTransferSettings.class);
    when(dataTransferSettings.getProcessorProperties()).thenReturn(new HashMap<>());
    when(dataTransferSettings.getProcessor()).thenReturn(dataTransferProcessorDescriptor);

    // Act
    streamProducerSettings.updateProducerSettingsFromStream(
        monitor, producer, dataTransferSettings);

    // Assert
    verify(dataTransferSettings).getProcessor();
    verify(dataTransferSettings).getProcessorProperties();
    verify(dataTransferProcessorDescriptor).getInstance();
  }

  /**
   * Test {@link StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, DataTransferSettings)} with {@code monitor}, {@code producer}, {@code
   * dataTransferSettings}.
   *
   * <p>Method under test: {@link
   * StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, DataTransferSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.updateProducerSettingsFromStream(DBRProgressMonitor, StreamTransferProducer, DataTransferSettings)"
  })
  public void testUpdateProducerSettingsFromStreamWithMonitorProducerDataTransferSettings3() {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();
    Class<Object> forClass = Object.class;
    LoggingProgressMonitor monitor = new LoggingProgressMonitor(Log.getLog(forClass));
    StreamTransferProducer producer = new StreamTransferProducer();

    DataTransferProcessorDescriptor dataTransferProcessorDescriptor =
        mock(DataTransferProcessorDescriptor.class);
    when(dataTransferProcessorDescriptor.getInstance()).thenReturn(new DataExporterCSV());

    DataTransferSettings dataTransferSettings = mock(DataTransferSettings.class);
    when(dataTransferSettings.getProcessorProperties()).thenReturn(new HashMap<>());
    when(dataTransferSettings.getProcessor()).thenReturn(dataTransferProcessorDescriptor);

    // Act
    streamProducerSettings.updateProducerSettingsFromStream(
        monitor, producer, dataTransferSettings);

    // Assert
    verify(dataTransferSettings).getProcessor();
    verify(dataTransferSettings).getProcessorProperties();
    verify(dataTransferProcessorDescriptor).getInstance();
  }

  /**
   * Test {@link StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, DataTransferSettings)} with {@code monitor}, {@code producer}, {@code
   * dataTransferSettings}.
   *
   * <p>Method under test: {@link
   * StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, DataTransferSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.updateProducerSettingsFromStream(DBRProgressMonitor, StreamTransferProducer, DataTransferSettings)"
  })
  public void testUpdateProducerSettingsFromStreamWithMonitorProducerDataTransferSettings4() {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();
    SubTaskProgressMonitor monitor = new SubTaskProgressMonitor(new LoggingProgressMonitor());
    StreamTransferProducer producer = new StreamTransferProducer();

    DataTransferProcessorDescriptor dataTransferProcessorDescriptor =
        mock(DataTransferProcessorDescriptor.class);
    when(dataTransferProcessorDescriptor.getInstance()).thenReturn(new DataExporterCSV());

    DataTransferSettings dataTransferSettings = mock(DataTransferSettings.class);
    when(dataTransferSettings.getProcessorProperties()).thenReturn(new HashMap<>());
    when(dataTransferSettings.getProcessor()).thenReturn(dataTransferProcessorDescriptor);

    // Act
    streamProducerSettings.updateProducerSettingsFromStream(
        monitor, producer, dataTransferSettings);

    // Assert
    verify(dataTransferSettings).getProcessor();
    verify(dataTransferSettings).getProcessorProperties();
    verify(dataTransferProcessorDescriptor).getInstance();
  }

  /**
   * Test {@link StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, DataTransferSettings)} with {@code monitor}, {@code producer}, {@code
   * dataTransferSettings}.
   *
   * <p>Method under test: {@link
   * StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, DataTransferSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.updateProducerSettingsFromStream(DBRProgressMonitor, StreamTransferProducer, DataTransferSettings)"
  })
  public void testUpdateProducerSettingsFromStreamWithMonitorProducerDataTransferSettings5() {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferProducer producer =
        new StreamTransferProducer(new StreamEntityMapping(inputFile));

    DataTransferProcessorDescriptor dataTransferProcessorDescriptor =
        mock(DataTransferProcessorDescriptor.class);
    when(dataTransferProcessorDescriptor.getInstance()).thenReturn(new DataExporterCSV());

    DataTransferSettings dataTransferSettings = mock(DataTransferSettings.class);
    when(dataTransferSettings.getProcessorProperties()).thenReturn(new HashMap<>());
    when(dataTransferSettings.getProcessor()).thenReturn(dataTransferProcessorDescriptor);

    // Act
    streamProducerSettings.updateProducerSettingsFromStream(
        monitor, producer, dataTransferSettings);

    // Assert that nothing has changed
    verify(dataTransferSettings).getProcessor();
    verify(dataTransferSettings).getProcessorProperties();
    verify(dataTransferProcessorDescriptor).getInstance();
    assertTrue(producer.getDatabaseObject().getStreamColumns().isEmpty());
  }

  /**
   * Test {@link StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, DataTransferSettings)} with {@code monitor}, {@code producer}, {@code
   * dataTransferSettings}.
   *
   * <p>Method under test: {@link
   * StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, DataTransferSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.updateProducerSettingsFromStream(DBRProgressMonitor, StreamTransferProducer, DataTransferSettings)"
  })
  public void testUpdateProducerSettingsFromStreamWithMonitorProducerDataTransferSettings6() {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();
    Class<Object> forClass = Object.class;
    LoggingProgressMonitor original = new LoggingProgressMonitor(Log.getLog(forClass));
    SubTaskProgressMonitor monitor = new SubTaskProgressMonitor(original);
    StreamTransferProducer producer = new StreamTransferProducer();

    DataTransferProcessorDescriptor dataTransferProcessorDescriptor =
        mock(DataTransferProcessorDescriptor.class);
    when(dataTransferProcessorDescriptor.getInstance()).thenReturn(new DataExporterCSV());

    DataTransferSettings dataTransferSettings = mock(DataTransferSettings.class);
    when(dataTransferSettings.getProcessorProperties()).thenReturn(new HashMap<>());
    when(dataTransferSettings.getProcessor()).thenReturn(dataTransferProcessorDescriptor);

    // Act
    streamProducerSettings.updateProducerSettingsFromStream(
        monitor, producer, dataTransferSettings);

    // Assert
    verify(dataTransferSettings).getProcessor();
    verify(dataTransferSettings).getProcessorProperties();
    verify(dataTransferProcessorDescriptor).getInstance();
  }

  /**
   * Test {@link StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, DataTransferSettings)} with {@code monitor}, {@code producer}, {@code
   * dataTransferSettings}.
   *
   * <p>Method under test: {@link
   * StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, DataTransferSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.updateProducerSettingsFromStream(DBRProgressMonitor, StreamTransferProducer, DataTransferSettings)"
  })
  public void testUpdateProducerSettingsFromStreamWithMonitorProducerDataTransferSettings7() {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferProducer producer =
        new StreamTransferProducer(new StreamEntityMapping(inputFile));

    DataTransferProcessorDescriptor dataTransferProcessorDescriptor =
        mock(DataTransferProcessorDescriptor.class);
    when(dataTransferProcessorDescriptor.getInstance()).thenReturn(new DataImporterCSV());

    DataTransferSettings dataTransferSettings = mock(DataTransferSettings.class);
    when(dataTransferSettings.getProcessorProperties()).thenReturn(new HashMap<>());
    when(dataTransferSettings.getState()).thenReturn(new DataTransferState());
    when(dataTransferSettings.getProcessor()).thenReturn(dataTransferProcessorDescriptor);

    // Act
    streamProducerSettings.updateProducerSettingsFromStream(
        monitor, producer, dataTransferSettings);

    // Assert that nothing has changed
    verify(dataTransferSettings).getProcessor();
    verify(dataTransferSettings).getProcessorProperties();
    verify(dataTransferSettings).getState();
    verify(dataTransferProcessorDescriptor).getInstance();
    assertTrue(producer.getDatabaseObject().getStreamColumns().isEmpty());
  }

  /**
   * Test {@link StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, DataTransferSettings)} with {@code monitor}, {@code producer}, {@code
   * dataTransferSettings}.
   *
   * <p>Method under test: {@link
   * StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, DataTransferSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.updateProducerSettingsFromStream(DBRProgressMonitor, StreamTransferProducer, DataTransferSettings)"
  })
  public void testUpdateProducerSettingsFromStreamWithMonitorProducerDataTransferSettings8()
      throws DBException {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferProducer producer =
        new StreamTransferProducer(new StreamEntityMapping(inputFile));

    DataImporterCSV dataImporterCSV = mock(DataImporterCSV.class);
    when(dataImporterCSV.readColumnsInfo(
            Mockito.<StreamEntityMapping>any(), Mockito.<InputStream>any()))
        .thenReturn(new ArrayList<>());
    doNothing().when(dataImporterCSV).dispose();
    doNothing().when(dataImporterCSV).init(Mockito.<IStreamDataImporterSite>any());

    DataTransferProcessorDescriptor dataTransferProcessorDescriptor =
        mock(DataTransferProcessorDescriptor.class);
    when(dataTransferProcessorDescriptor.getInstance()).thenReturn(dataImporterCSV);

    DataTransferSettings dataTransferSettings = mock(DataTransferSettings.class);
    when(dataTransferSettings.getProcessorProperties()).thenReturn(new HashMap<>());
    when(dataTransferSettings.getProcessor()).thenReturn(dataTransferProcessorDescriptor);

    // Act
    streamProducerSettings.updateProducerSettingsFromStream(
        monitor, producer, dataTransferSettings);

    // Assert that nothing has changed
    verify(dataTransferSettings).getProcessor();
    verify(dataTransferSettings).getProcessorProperties();
    verify(dataTransferProcessorDescriptor).getInstance();
    verify(dataImporterCSV).readColumnsInfo(isA(StreamEntityMapping.class), isA(InputStream.class));
    verify(dataImporterCSV).dispose();
    verify(dataImporterCSV).init(isA(IStreamDataImporterSite.class));
    assertTrue(producer.getDatabaseObject().getStreamColumns().isEmpty());
  }

  /**
   * Test {@link StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, DataTransferSettings)} with {@code monitor}, {@code producer}, {@code
   * dataTransferSettings}.
   *
   * <p>Method under test: {@link
   * StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, DataTransferSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.updateProducerSettingsFromStream(DBRProgressMonitor, StreamTransferProducer, DataTransferSettings)"
  })
  public void testUpdateProducerSettingsFromStreamWithMonitorProducerDataTransferSettings9()
      throws DBException {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferProducer producer =
        new StreamTransferProducer(new StreamEntityMapping(inputFile));

    DataImporterCSV dataImporterCSV = mock(DataImporterCSV.class);
    doThrow(new DBException("An error occurred"))
        .when(dataImporterCSV)
        .init(Mockito.<IStreamDataImporterSite>any());

    DataTransferProcessorDescriptor dataTransferProcessorDescriptor =
        mock(DataTransferProcessorDescriptor.class);
    when(dataTransferProcessorDescriptor.getInstance()).thenReturn(dataImporterCSV);

    DataTransferSettings dataTransferSettings = mock(DataTransferSettings.class);
    when(dataTransferSettings.getProcessorProperties()).thenReturn(new HashMap<>());
    when(dataTransferSettings.getState()).thenReturn(new DataTransferState());
    when(dataTransferSettings.getProcessor()).thenReturn(dataTransferProcessorDescriptor);

    // Act
    streamProducerSettings.updateProducerSettingsFromStream(
        monitor, producer, dataTransferSettings);

    // Assert that nothing has changed
    verify(dataTransferSettings).getProcessor();
    verify(dataTransferSettings).getProcessorProperties();
    verify(dataTransferSettings).getState();
    verify(dataTransferProcessorDescriptor).getInstance();
    verify(dataImporterCSV).init(isA(IStreamDataImporterSite.class));
    assertTrue(producer.getDatabaseObject().getStreamColumns().isEmpty());
  }

  /**
   * Test {@link StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, DataTransferSettings)} with {@code monitor}, {@code producer}, {@code
   * dataTransferSettings}.
   *
   * <p>Method under test: {@link
   * StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, DataTransferSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.updateProducerSettingsFromStream(DBRProgressMonitor, StreamTransferProducer, DataTransferSettings)"
  })
  public void testUpdateProducerSettingsFromStreamWithMonitorProducerDataTransferSettings10()
      throws DBException {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferProducer producer =
        new StreamTransferProducer(new StreamEntityMapping(inputFile));

    ArrayList<StreamDataImporterColumnInfo> streamDataImporterColumnInfoList = new ArrayList<>();
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamDataImporterColumnInfo streamDataImporterColumnInfo =
        new StreamDataImporterColumnInfo(
            new StreamEntityMapping(inputFile2),
            1,
            "Update data produces settings from import stream",
            "Update data produces settings from import stream",
            3,
            DBPDataKind.BOOLEAN);
    streamDataImporterColumnInfoList.add(streamDataImporterColumnInfo);

    DataImporterCSV dataImporterCSV = mock(DataImporterCSV.class);
    when(dataImporterCSV.readColumnsInfo(
            Mockito.<StreamEntityMapping>any(), Mockito.<InputStream>any()))
        .thenReturn(streamDataImporterColumnInfoList);
    doNothing().when(dataImporterCSV).dispose();
    doNothing().when(dataImporterCSV).init(Mockito.<IStreamDataImporterSite>any());

    DataTransferProcessorDescriptor dataTransferProcessorDescriptor =
        mock(DataTransferProcessorDescriptor.class);
    when(dataTransferProcessorDescriptor.getInstance()).thenReturn(dataImporterCSV);

    DataTransferSettings dataTransferSettings = mock(DataTransferSettings.class);
    when(dataTransferSettings.getProcessorProperties()).thenReturn(new HashMap<>());
    when(dataTransferSettings.getProcessor()).thenReturn(dataTransferProcessorDescriptor);

    // Act
    streamProducerSettings.updateProducerSettingsFromStream(
        monitor, producer, dataTransferSettings);

    // Assert
    verify(dataTransferSettings).getProcessor();
    verify(dataTransferSettings).getProcessorProperties();
    verify(dataTransferProcessorDescriptor).getInstance();
    verify(dataImporterCSV).readColumnsInfo(isA(StreamEntityMapping.class), isA(InputStream.class));
    verify(dataImporterCSV).dispose();
    verify(dataImporterCSV).init(isA(IStreamDataImporterSite.class));
    assertEquals(streamDataImporterColumnInfoList, producer.getDatabaseObject().getStreamColumns());
  }

  /**
   * Test {@link StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, DataTransferSettings)} with {@code monitor}, {@code producer}, {@code
   * dataTransferSettings}.
   *
   * <p>Method under test: {@link
   * StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, DataTransferSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.updateProducerSettingsFromStream(DBRProgressMonitor, StreamTransferProducer, DataTransferSettings)"
  })
  public void testUpdateProducerSettingsFromStreamWithMonitorProducerDataTransferSettings11()
      throws DBException {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferProducer producer =
        new StreamTransferProducer(new StreamEntityMapping(inputFile));

    ArrayList<StreamDataImporterColumnInfo> streamDataImporterColumnInfoList = new ArrayList<>();
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamDataImporterColumnInfo streamDataImporterColumnInfo =
        new StreamDataImporterColumnInfo(
            new StreamEntityMapping(inputFile2),
            1,
            "Update data produces settings from import stream",
            "Update data produces settings from import stream",
            3,
            DBPDataKind.BOOLEAN);
    streamDataImporterColumnInfoList.add(streamDataImporterColumnInfo);
    Path inputFile3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamDataImporterColumnInfo streamDataImporterColumnInfo2 =
        new StreamDataImporterColumnInfo(
            new StreamEntityMapping(inputFile3),
            1,
            "Update data produces settings from import stream",
            "Update data produces settings from import stream",
            3,
            DBPDataKind.BOOLEAN);
    streamDataImporterColumnInfoList.add(streamDataImporterColumnInfo2);

    DataImporterCSV dataImporterCSV = mock(DataImporterCSV.class);
    when(dataImporterCSV.readColumnsInfo(
            Mockito.<StreamEntityMapping>any(), Mockito.<InputStream>any()))
        .thenReturn(streamDataImporterColumnInfoList);
    doNothing().when(dataImporterCSV).dispose();
    doNothing().when(dataImporterCSV).init(Mockito.<IStreamDataImporterSite>any());

    DataTransferProcessorDescriptor dataTransferProcessorDescriptor =
        mock(DataTransferProcessorDescriptor.class);
    when(dataTransferProcessorDescriptor.getInstance()).thenReturn(dataImporterCSV);

    DataTransferSettings dataTransferSettings = mock(DataTransferSettings.class);
    when(dataTransferSettings.getProcessorProperties()).thenReturn(new HashMap<>());
    when(dataTransferSettings.getProcessor()).thenReturn(dataTransferProcessorDescriptor);

    // Act
    streamProducerSettings.updateProducerSettingsFromStream(
        monitor, producer, dataTransferSettings);

    // Assert
    verify(dataTransferSettings).getProcessor();
    verify(dataTransferSettings).getProcessorProperties();
    verify(dataTransferProcessorDescriptor).getInstance();
    verify(dataImporterCSV).readColumnsInfo(isA(StreamEntityMapping.class), isA(InputStream.class));
    verify(dataImporterCSV).dispose();
    verify(dataImporterCSV).init(isA(IStreamDataImporterSite.class));
    List<StreamDataImporterColumnInfo> streamColumns =
        producer.getDatabaseObject().getStreamColumns();
    assertEquals(2, streamColumns.size());
    assertSame(streamDataImporterColumnInfo2, streamColumns.get(1));
  }

  /**
   * Test {@link StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, DataTransferSettings)} with {@code monitor}, {@code producer}, {@code
   * dataTransferSettings}.
   *
   * <p>Method under test: {@link
   * StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, DataTransferSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.updateProducerSettingsFromStream(DBRProgressMonitor, StreamTransferProducer, DataTransferSettings)"
  })
  public void testUpdateProducerSettingsFromStreamWithMonitorProducerDataTransferSettings12()
      throws DBException {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    StreamEntityMapping entityMapping = mock(StreamEntityMapping.class);
    doNothing()
        .when(entityMapping)
        .setStreamColumns(Mockito.<List<StreamDataImporterColumnInfo>>any());
    when(entityMapping.getInputFile())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    StreamTransferProducer producer = new StreamTransferProducer(entityMapping);

    DataImporterCSV dataImporterCSV = mock(DataImporterCSV.class);
    when(dataImporterCSV.readColumnsInfo(
            Mockito.<StreamEntityMapping>any(), Mockito.<InputStream>any()))
        .thenReturn(new ArrayList<>());
    doNothing().when(dataImporterCSV).dispose();
    doNothing().when(dataImporterCSV).init(Mockito.<IStreamDataImporterSite>any());

    DataTransferProcessorDescriptor dataTransferProcessorDescriptor =
        mock(DataTransferProcessorDescriptor.class);
    when(dataTransferProcessorDescriptor.getInstance()).thenReturn(dataImporterCSV);

    DataTransferSettings dataTransferSettings = mock(DataTransferSettings.class);
    when(dataTransferSettings.getProcessorProperties()).thenReturn(new HashMap<>());
    when(dataTransferSettings.getProcessor()).thenReturn(dataTransferProcessorDescriptor);

    // Act
    streamProducerSettings.updateProducerSettingsFromStream(
        monitor, producer, dataTransferSettings);

    // Assert
    verify(dataTransferSettings).getProcessor();
    verify(dataTransferSettings).getProcessorProperties();
    verify(dataTransferProcessorDescriptor).getInstance();
    verify(entityMapping).getInputFile();
    verify(entityMapping).setStreamColumns(isA(List.class));
    verify(dataImporterCSV).readColumnsInfo(isA(StreamEntityMapping.class), isA(InputStream.class));
    verify(dataImporterCSV).dispose();
    verify(dataImporterCSV).init(isA(IStreamDataImporterSite.class));
  }

  /**
   * Test {@link StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, DataTransferSettings)} with {@code monitor}, {@code producer}, {@code
   * dataTransferSettings}.
   *
   * <p>Method under test: {@link
   * StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, DataTransferSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.updateProducerSettingsFromStream(DBRProgressMonitor, StreamTransferProducer, DataTransferSettings)"
  })
  public void testUpdateProducerSettingsFromStreamWithMonitorProducerDataTransferSettings13() {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    StreamEntityMapping entityMapping = mock(StreamEntityMapping.class);
    when(entityMapping.getInputFile())
        .thenReturn(
            Paths.get(
                System.getProperty("java.io.tmpdir"),
                "Update data produces settings from import stream"));
    StreamTransferProducer producer = new StreamTransferProducer(entityMapping);

    DataTransferProcessorDescriptor dataTransferProcessorDescriptor =
        mock(DataTransferProcessorDescriptor.class);
    when(dataTransferProcessorDescriptor.getInstance()).thenReturn(mock(DataImporterCSV.class));

    DataTransferSettings dataTransferSettings = mock(DataTransferSettings.class);
    when(dataTransferSettings.getProcessorProperties()).thenReturn(new HashMap<>());
    when(dataTransferSettings.getState()).thenReturn(new DataTransferState());
    when(dataTransferSettings.getProcessor()).thenReturn(dataTransferProcessorDescriptor);

    // Act
    streamProducerSettings.updateProducerSettingsFromStream(
        monitor, producer, dataTransferSettings);

    // Assert
    verify(dataTransferSettings).getProcessor();
    verify(dataTransferSettings).getProcessorProperties();
    verify(dataTransferSettings).getState();
    verify(dataTransferProcessorDescriptor).getInstance();
    verify(entityMapping).getInputFile();
  }

  /**
   * Test {@link StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, IDataTransferProcessor, Map)} with {@code monitor}, {@code producer},
   * {@code processor}, {@code processorProperties}.
   *
   * <p>Method under test: {@link
   * StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, IDataTransferProcessor, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.updateProducerSettingsFromStream(DBRProgressMonitor, StreamTransferProducer, IDataTransferProcessor, Map)"
  })
  public void testUpdateProducerSettingsFromStreamWithMonitorProducerProcessorProcessorProperties()
      throws DBException {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    StreamTransferProducer producer = new StreamTransferProducer();
    DataExporterCSV processor = new DataExporterCSV();

    // Act and Assert
    streamProducerSettings.updateProducerSettingsFromStream(
        monitor, producer, processor, new HashMap<>());
  }

  /**
   * Test {@link StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, IDataTransferProcessor, Map)} with {@code monitor}, {@code producer},
   * {@code processor}, {@code processorProperties}.
   *
   * <p>Method under test: {@link
   * StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, IDataTransferProcessor, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.updateProducerSettingsFromStream(DBRProgressMonitor, StreamTransferProducer, IDataTransferProcessor, Map)"
  })
  public void testUpdateProducerSettingsFromStreamWithMonitorProducerProcessorProcessorProperties2()
      throws DBException {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferProducer producer =
        new StreamTransferProducer(new StreamEntityMapping(inputFile));
    DataImporterCSV processor = new DataImporterCSV();

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            streamProducerSettings.updateProducerSettingsFromStream(
                monitor, producer, processor, new HashMap<>()));
  }

  /**
   * Test {@link StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, IDataTransferProcessor, Map)} with {@code monitor}, {@code producer},
   * {@code processor}, {@code processorProperties}.
   *
   * <p>Method under test: {@link
   * StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, IDataTransferProcessor, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.updateProducerSettingsFromStream(DBRProgressMonitor, StreamTransferProducer, IDataTransferProcessor, Map)"
  })
  public void testUpdateProducerSettingsFromStreamWithMonitorProducerProcessorProcessorProperties3()
      throws DBException {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();
    Class<Object> forClass = Object.class;
    LoggingProgressMonitor monitor = new LoggingProgressMonitor(Log.getLog(forClass));
    StreamTransferProducer producer = new StreamTransferProducer();
    DataExporterCSV processor = new DataExporterCSV();

    // Act and Assert
    streamProducerSettings.updateProducerSettingsFromStream(
        monitor, producer, processor, new HashMap<>());
  }

  /**
   * Test {@link StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, IDataTransferProcessor, Map)} with {@code monitor}, {@code producer},
   * {@code processor}, {@code processorProperties}.
   *
   * <p>Method under test: {@link
   * StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, IDataTransferProcessor, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.updateProducerSettingsFromStream(DBRProgressMonitor, StreamTransferProducer, IDataTransferProcessor, Map)"
  })
  public void testUpdateProducerSettingsFromStreamWithMonitorProducerProcessorProcessorProperties4()
      throws DBException {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();
    SubTaskProgressMonitor monitor = new SubTaskProgressMonitor(new LoggingProgressMonitor());
    StreamTransferProducer producer = new StreamTransferProducer();
    DataExporterCSV processor = new DataExporterCSV();

    // Act and Assert
    streamProducerSettings.updateProducerSettingsFromStream(
        monitor, producer, processor, new HashMap<>());
  }

  /**
   * Test {@link StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, IDataTransferProcessor, Map)} with {@code monitor}, {@code producer},
   * {@code processor}, {@code processorProperties}.
   *
   * <p>Method under test: {@link
   * StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, IDataTransferProcessor, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.updateProducerSettingsFromStream(DBRProgressMonitor, StreamTransferProducer, IDataTransferProcessor, Map)"
  })
  public void testUpdateProducerSettingsFromStreamWithMonitorProducerProcessorProcessorProperties5()
      throws DBException {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferProducer producer =
        new StreamTransferProducer(new StreamEntityMapping(inputFile));
    DataExporterCSV processor = new DataExporterCSV();

    // Act and Assert
    streamProducerSettings.updateProducerSettingsFromStream(
        monitor, producer, processor, new HashMap<>());
  }

  /**
   * Test {@link StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, IDataTransferProcessor, Map)} with {@code monitor}, {@code producer},
   * {@code processor}, {@code processorProperties}.
   *
   * <p>Method under test: {@link
   * StreamProducerSettings#updateProducerSettingsFromStream(DBRProgressMonitor,
   * StreamTransferProducer, IDataTransferProcessor, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamProducerSettings.updateProducerSettingsFromStream(DBRProgressMonitor, StreamTransferProducer, IDataTransferProcessor, Map)"
  })
  public void testUpdateProducerSettingsFromStreamWithMonitorProducerProcessorProcessorProperties6()
      throws DBException {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();
    Class<Object> forClass = Object.class;
    LoggingProgressMonitor original = new LoggingProgressMonitor(Log.getLog(forClass));
    SubTaskProgressMonitor monitor = new SubTaskProgressMonitor(original);
    StreamTransferProducer producer = new StreamTransferProducer();
    DataExporterCSV processor = new DataExporterCSV();

    // Act and Assert
    streamProducerSettings.updateProducerSettingsFromStream(
        monitor, producer, processor, new HashMap<>());
  }

  /**
   * Test {@link StreamProducerSettings#saveSettings(Map)}.
   *
   * <p>Method under test: {@link StreamProducerSettings#saveSettings(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamProducerSettings.saveSettings(Map)"})
  public void testSaveSettings() {
    // Arrange
    StreamProducerSettings streamProducerSettings = new StreamProducerSettings();
    HashMap<String, Object> settings = new HashMap<>();

    // Act
    streamProducerSettings.saveSettings(settings);

    // Assert
    assertEquals(1, settings.size());
    Object getResult = settings.get("mappings");
    assertTrue(getResult instanceof List);
    assertTrue(((List<Object>) getResult).isEmpty());
  }
}
