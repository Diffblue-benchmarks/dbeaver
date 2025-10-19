package org.jkiss.dbeaver.tools.transfer.stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.model.data.DBDDataFormatterProfile;
import org.jkiss.dbeaver.model.data.DBDDisplayFormat;
import org.jkiss.dbeaver.model.runtime.DBRRunnableContext;
import org.jkiss.dbeaver.model.struct.DBSDataContainer;
import org.jkiss.dbeaver.tools.transfer.DataTransferSettings;
import org.jkiss.dbeaver.tools.transfer.registry.DataTransferEventProcessorDescriptor;
import org.jkiss.dbeaver.tools.transfer.stream.StreamConsumerSettings.BlobFileConflictBehavior;
import org.jkiss.dbeaver.tools.transfer.stream.StreamConsumerSettings.ConsumerRuntimeParameters;
import org.jkiss.dbeaver.tools.transfer.stream.StreamConsumerSettings.DataFileConflictBehavior;
import org.jkiss.dbeaver.tools.transfer.stream.StreamConsumerSettings.LobEncoding;
import org.jkiss.dbeaver.tools.transfer.stream.StreamConsumerSettings.LobExtractType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class StreamConsumerSettingsDiffblueTest {
  /**
   * Test ConsumerRuntimeParameters {@link
   * ConsumerRuntimeParameters#ConsumerRuntimeParameters(StreamConsumerSettings)}.
   *
   * <p>Method under test: {@link
   * ConsumerRuntimeParameters#ConsumerRuntimeParameters(StreamConsumerSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConsumerRuntimeParameters.<init>(StreamConsumerSettings)"})
  public void testConsumerRuntimeParametersNewConsumerRuntimeParameters() {
    // Arrange and Act
    ConsumerRuntimeParameters actualConsumerRuntimeParameters =
        new StreamConsumerSettings().new ConsumerRuntimeParameters();

    // Assert
    assertNull(actualConsumerRuntimeParameters.blobFileConflictPreviousChoice);
    assertNull(actualConsumerRuntimeParameters.dataFileConflictPreviousChoice);
    assertNull(actualConsumerRuntimeParameters.outputFileNameToReuse);
    assertEquals(
        BlobFileConflictBehavior.ASK, actualConsumerRuntimeParameters.blobFileConflictBehavior);
    assertEquals(
        DataFileConflictBehavior.ASK, actualConsumerRuntimeParameters.dataFileConflictBehavior);
    assertFalse(actualConsumerRuntimeParameters.dontDropBlobFileConflictBehavior);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link StreamConsumerSettings#setBlobFileConflictBehavior(BlobFileConflictBehavior)}
   *   <li>{@link StreamConsumerSettings#setCompressResults(boolean)}
   *   <li>{@link StreamConsumerSettings#setDataFileConflictBehavior(DataFileConflictBehavior)}
   *   <li>{@link StreamConsumerSettings#setLobEncoding(LobEncoding)}
   *   <li>{@link StreamConsumerSettings#setLobExtractType(LobExtractType)}
   *   <li>{@link StreamConsumerSettings#setMaxOutFileSize(long)}
   *   <li>{@link StreamConsumerSettings#setOutputClipboard(boolean)}
   *   <li>{@link StreamConsumerSettings#setOutputEncoding(String)}
   *   <li>{@link StreamConsumerSettings#setOutputEncodingBOM(boolean)}
   *   <li>{@link StreamConsumerSettings#setOutputFilePattern(String)}
   *   <li>{@link StreamConsumerSettings#setOutputFolder(String)}
   *   <li>{@link StreamConsumerSettings#setOutputTimestampPattern(String)}
   *   <li>{@link StreamConsumerSettings#setSplitOutFiles(boolean)}
   *   <li>{@link StreamConsumerSettings#setUseSingleFile(boolean)}
   *   <li>{@link StreamConsumerSettings#setValueFormat(DBDDisplayFormat)}
   *   <li>{@link StreamConsumerSettings#getBlobFileConflictBehavior()}
   *   <li>{@link StreamConsumerSettings#getDataFileConflictBehavior()}
   *   <li>{@link StreamConsumerSettings#getDataMappings()}
   *   <li>{@link StreamConsumerSettings#getEventProcessors()}
   *   <li>{@link StreamConsumerSettings#getFormatterProfile()}
   *   <li>{@link StreamConsumerSettings#getLobEncoding()}
   *   <li>{@link StreamConsumerSettings#getLobExtractType()}
   *   <li>{@link StreamConsumerSettings#getMaxOutFileSize()}
   *   <li>{@link StreamConsumerSettings#getOutputEncoding()}
   *   <li>{@link StreamConsumerSettings#getOutputFilePattern()}
   *   <li>{@link StreamConsumerSettings#getOutputFolder()}
   *   <li>{@link StreamConsumerSettings#getOutputTimestampPattern()}
   *   <li>{@link StreamConsumerSettings#getValueFormat()}
   *   <li>{@link StreamConsumerSettings#isCompressResults()}
   *   <li>{@link StreamConsumerSettings#isOutputClipboard()}
   *   <li>{@link StreamConsumerSettings#isOutputEncodingBOM()}
   *   <li>{@link StreamConsumerSettings#isSplitOutFiles()}
   *   <li>{@link StreamConsumerSettings#isUseSingleFile()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "BlobFileConflictBehavior StreamConsumerSettings.getBlobFileConflictBehavior()",
    "DataFileConflictBehavior StreamConsumerSettings.getDataFileConflictBehavior()",
    "Map StreamConsumerSettings.getDataMappings()",
    "Map StreamConsumerSettings.getEventProcessors()",
    "DBDDataFormatterProfile StreamConsumerSettings.getFormatterProfile()",
    "LobEncoding StreamConsumerSettings.getLobEncoding()",
    "LobExtractType StreamConsumerSettings.getLobExtractType()",
    "long StreamConsumerSettings.getMaxOutFileSize()",
    "String StreamConsumerSettings.getOutputEncoding()",
    "String StreamConsumerSettings.getOutputFilePattern()",
    "String StreamConsumerSettings.getOutputFolder()",
    "String StreamConsumerSettings.getOutputTimestampPattern()",
    "DBDDisplayFormat StreamConsumerSettings.getValueFormat()",
    "boolean StreamConsumerSettings.isCompressResults()",
    "boolean StreamConsumerSettings.isOutputClipboard()",
    "boolean StreamConsumerSettings.isOutputEncodingBOM()",
    "boolean StreamConsumerSettings.isSplitOutFiles()",
    "boolean StreamConsumerSettings.isUseSingleFile()",
    "void StreamConsumerSettings.setBlobFileConflictBehavior(BlobFileConflictBehavior)",
    "void StreamConsumerSettings.setCompressResults(boolean)",
    "void StreamConsumerSettings.setDataFileConflictBehavior(DataFileConflictBehavior)",
    "void StreamConsumerSettings.setFormatterProfile(DBDDataFormatterProfile)",
    "void StreamConsumerSettings.setLobEncoding(LobEncoding)",
    "void StreamConsumerSettings.setLobExtractType(LobExtractType)",
    "void StreamConsumerSettings.setMaxOutFileSize(long)",
    "void StreamConsumerSettings.setOutputClipboard(boolean)",
    "void StreamConsumerSettings.setOutputEncoding(String)",
    "void StreamConsumerSettings.setOutputEncodingBOM(boolean)",
    "void StreamConsumerSettings.setOutputFilePattern(String)",
    "void StreamConsumerSettings.setOutputFolder(String)",
    "void StreamConsumerSettings.setOutputTimestampPattern(String)",
    "void StreamConsumerSettings.setSplitOutFiles(boolean)",
    "void StreamConsumerSettings.setUseSingleFile(boolean)",
    "void StreamConsumerSettings.setValueFormat(DBDDisplayFormat)"
  })
  public void testGettersAndSetters() {
    // Arrange
    StreamConsumerSettings streamConsumerSettings = new StreamConsumerSettings();

    // Act
    streamConsumerSettings.setBlobFileConflictBehavior(BlobFileConflictBehavior.ASK);
    streamConsumerSettings.setCompressResults(true);
    streamConsumerSettings.setDataFileConflictBehavior(DataFileConflictBehavior.ASK);
    streamConsumerSettings.setLobEncoding(LobEncoding.BASE64);
    streamConsumerSettings.setLobExtractType(LobExtractType.SKIP);
    streamConsumerSettings.setMaxOutFileSize(3L);
    streamConsumerSettings.setOutputClipboard(true);
    streamConsumerSettings.setOutputEncoding("UTF-8");
    streamConsumerSettings.setOutputEncodingBOM(true);
    streamConsumerSettings.setOutputFilePattern("Output File Pattern");
    streamConsumerSettings.setOutputFolder("Output Folder");
    streamConsumerSettings.setOutputTimestampPattern("Output Timestamp Pattern");
    streamConsumerSettings.setSplitOutFiles(true);
    streamConsumerSettings.setUseSingleFile(true);
    streamConsumerSettings.setValueFormat(DBDDisplayFormat.UI);
    BlobFileConflictBehavior actualBlobFileConflictBehavior =
        streamConsumerSettings.getBlobFileConflictBehavior();
    DataFileConflictBehavior actualDataFileConflictBehavior =
        streamConsumerSettings.getDataFileConflictBehavior();
    Map<DBSDataContainer, StreamMappingContainer> actualDataMappings =
        streamConsumerSettings.getDataMappings();
    Map<String, Map<String, Object>> actualEventProcessors =
        streamConsumerSettings.getEventProcessors();
    DBDDataFormatterProfile actualFormatterProfile = streamConsumerSettings.getFormatterProfile();
    LobEncoding actualLobEncoding = streamConsumerSettings.getLobEncoding();
    LobExtractType actualLobExtractType = streamConsumerSettings.getLobExtractType();
    long actualMaxOutFileSize = streamConsumerSettings.getMaxOutFileSize();
    String actualOutputEncoding = streamConsumerSettings.getOutputEncoding();
    String actualOutputFilePattern = streamConsumerSettings.getOutputFilePattern();
    String actualOutputFolder = streamConsumerSettings.getOutputFolder();
    String actualOutputTimestampPattern = streamConsumerSettings.getOutputTimestampPattern();
    DBDDisplayFormat actualValueFormat = streamConsumerSettings.getValueFormat();
    boolean actualIsCompressResultsResult = streamConsumerSettings.isCompressResults();
    boolean actualIsOutputClipboardResult = streamConsumerSettings.isOutputClipboard();
    boolean actualIsOutputEncodingBOMResult = streamConsumerSettings.isOutputEncodingBOM();
    boolean actualIsSplitOutFilesResult = streamConsumerSettings.isSplitOutFiles();
    boolean actualIsUseSingleFileResult = streamConsumerSettings.isUseSingleFile();

    // Assert
    assertEquals("Output File Pattern", actualOutputFilePattern);
    assertEquals("Output Folder", actualOutputFolder);
    assertEquals("Output Timestamp Pattern", actualOutputTimestampPattern);
    assertEquals("UTF-8", actualOutputEncoding);
    assertNull(actualFormatterProfile);
    assertEquals(3L, actualMaxOutFileSize);
    assertEquals(DBDDisplayFormat.UI, actualValueFormat);
    assertEquals(BlobFileConflictBehavior.ASK, actualBlobFileConflictBehavior);
    assertEquals(DataFileConflictBehavior.ASK, actualDataFileConflictBehavior);
    assertEquals(LobEncoding.BASE64, actualLobEncoding);
    assertEquals(LobExtractType.SKIP, actualLobExtractType);
    assertTrue(actualDataMappings.isEmpty());
    assertTrue(actualEventProcessors.isEmpty());
    assertTrue(actualIsCompressResultsResult);
    assertTrue(actualIsOutputClipboardResult);
    assertTrue(actualIsOutputEncodingBOMResult);
    assertTrue(actualIsSplitOutFilesResult);
    assertTrue(actualIsUseSingleFileResult);
  }

  /**
   * Test {@link StreamConsumerSettings#prepareRuntimeParameters()}.
   *
   * <p>Method under test: {@link StreamConsumerSettings#prepareRuntimeParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ConsumerRuntimeParameters StreamConsumerSettings.prepareRuntimeParameters()"})
  public void testPrepareRuntimeParameters() {
    // Arrange and Act
    ConsumerRuntimeParameters actualPrepareRuntimeParametersResult =
        new StreamConsumerSettings().prepareRuntimeParameters();

    // Assert
    assertNull(actualPrepareRuntimeParametersResult.blobFileConflictPreviousChoice);
    assertNull(actualPrepareRuntimeParametersResult.dataFileConflictPreviousChoice);
    assertNull(actualPrepareRuntimeParametersResult.outputFileNameToReuse);
    assertEquals(
        BlobFileConflictBehavior.ASK,
        actualPrepareRuntimeParametersResult.blobFileConflictBehavior);
    assertEquals(
        DataFileConflictBehavior.ASK,
        actualPrepareRuntimeParametersResult.dataFileConflictBehavior);
    assertFalse(actualPrepareRuntimeParametersResult.dontDropBlobFileConflictBehavior);
  }

  /**
   * Test {@link StreamConsumerSettings#getDataMapping(DBSDataContainer)}.
   *
   * <p>Method under test: {@link StreamConsumerSettings#getDataMapping(DBSDataContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "StreamMappingContainer StreamConsumerSettings.getDataMapping(DBSDataContainer)"
  })
  public void testGetDataMapping() {
    // Arrange
    StreamConsumerSettings streamConsumerSettings = new StreamConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    StreamMappingContainer actualDataMapping =
        streamConsumerSettings.getDataMapping(new StreamEntityMapping(inputFile));

    // Assert
    assertNull(actualDataMapping);
  }

  /**
   * Test {@link StreamConsumerSettings#addDataMapping(StreamMappingContainer)}.
   *
   * <ul>
   *   <li>Then {@link StreamConsumerSettings} (default constructor) DataMappings size is one.
   * </ul>
   *
   * <p>Method under test: {@link StreamConsumerSettings#addDataMapping(StreamMappingContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamConsumerSettings.addDataMapping(StreamMappingContainer)"})
  public void testAddDataMapping_thenStreamConsumerSettingsDataMappingsSizeIsOne() {
    // Arrange
    StreamConsumerSettings streamConsumerSettings = new StreamConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    streamConsumerSettings.addDataMapping(
        new StreamMappingContainer(new StreamEntityMapping(inputFile)));

    // Assert
    assertEquals(1, streamConsumerSettings.getDataMappings().size());
  }

  /**
   * Test {@link StreamConsumerSettings#getEventProcessorSettings(String)}.
   *
   * <p>Method under test: {@link StreamConsumerSettings#getEventProcessorSettings(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map StreamConsumerSettings.getEventProcessorSettings(String)"})
  public void testGetEventProcessorSettings() {
    // Arrange
    StreamConsumerSettings streamConsumerSettings = new StreamConsumerSettings();

    // Act
    Map<String, Object> actualEventProcessorSettings =
        streamConsumerSettings.getEventProcessorSettings("42");

    // Assert
    Map<String, Map<String, Object>> eventProcessors = streamConsumerSettings.getEventProcessors();
    assertEquals(1, eventProcessors.size());
    assertTrue(eventProcessors.containsKey("42"));
    assertTrue(actualEventProcessorSettings.isEmpty());
  }

  /**
   * Test {@link StreamConsumerSettings#addEventProcessor(DataTransferEventProcessorDescriptor)}.
   *
   * <p>Method under test: {@link
   * StreamConsumerSettings#addEventProcessor(DataTransferEventProcessorDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamConsumerSettings.addEventProcessor(DataTransferEventProcessorDescriptor)"
  })
  public void testAddEventProcessor() {
    // Arrange
    StreamConsumerSettings streamConsumerSettings = new StreamConsumerSettings();

    DataTransferEventProcessorDescriptor descriptor =
        mock(DataTransferEventProcessorDescriptor.class);
    when(descriptor.getId()).thenReturn("42");

    // Act
    streamConsumerSettings.addEventProcessor(descriptor);

    // Assert
    verify(descriptor).getId();
    Map<String, Map<String, Object>> eventProcessors = streamConsumerSettings.getEventProcessors();
    assertEquals(1, eventProcessors.size());
    assertTrue(eventProcessors.get("42").isEmpty());
  }

  /**
   * Test {@link StreamConsumerSettings#removeEventProcessor(DataTransferEventProcessorDescriptor)}.
   *
   * <p>Method under test: {@link
   * StreamConsumerSettings#removeEventProcessor(DataTransferEventProcessorDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamConsumerSettings.removeEventProcessor(DataTransferEventProcessorDescriptor)"
  })
  public void testRemoveEventProcessor() {
    // Arrange
    StreamConsumerSettings streamConsumerSettings = new StreamConsumerSettings();

    DataTransferEventProcessorDescriptor descriptor =
        mock(DataTransferEventProcessorDescriptor.class);
    when(descriptor.getId()).thenReturn("42");

    // Act
    streamConsumerSettings.removeEventProcessor(descriptor);

    // Assert
    verify(descriptor).getId();
  }

  /**
   * Test {@link StreamConsumerSettings#hasEventProcessor(String)}.
   *
   * <p>Method under test: {@link StreamConsumerSettings#hasEventProcessor(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StreamConsumerSettings.hasEventProcessor(String)"})
  public void testHasEventProcessor() {
    // Arrange, Act and Assert
    assertFalse(new StreamConsumerSettings().hasEventProcessor("42"));
  }

  /**
   * Test {@link StreamConsumerSettings#loadSettings(DBRRunnableContext, DataTransferSettings,
   * Map)}.
   *
   * <p>Method under test: {@link StreamConsumerSettings#loadSettings(DBRRunnableContext,
   * DataTransferSettings, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamConsumerSettings.loadSettings(DBRRunnableContext, DataTransferSettings, Map)"
  })
  public void testLoadSettings() {
    // Arrange
    StreamConsumerSettings streamConsumerSettings = new StreamConsumerSettings();
    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);

    // Act
    streamConsumerSettings.loadSettings(runnableContext, null, new HashMap<>());

    // Assert
    Map<String, Map<String, Object>> eventProcessors = streamConsumerSettings.getEventProcessors();
    assertEquals(1, eventProcessors.size());
    assertEquals(
        BlobFileConflictBehavior.PATCHNAME, streamConsumerSettings.getBlobFileConflictBehavior());
    assertEquals(
        DataFileConflictBehavior.PATCHNAME, streamConsumerSettings.getDataFileConflictBehavior());
    assertFalse(streamConsumerSettings.isOutputEncodingBOM());
    assertTrue(eventProcessors.get("failedExportFileCleaner").isEmpty());
    String expectedSettingsSummary =
        String.join(
            "",
            "\tWrite to the single file: No\n\tDirectory: ",
            System.getProperty("user.home"),
            "\n"
                + "\tFile name pattern: ${table}_${timestamp}\n"
                + "\tOn object data file name conflict: Autofix name\n"
                + "\tOn blob value file name conflict: Autofix name\n"
                + "\tEncoding: UTF-8\n"
                + "\tTimestamp pattern: yyyyMMddHHmm\n"
                + "\tInsert BOM: No\n"
                + "\tCompress: No\n"
                + "\tBinaries: INLINE\n"
                + "\tEncoding: BINARY\n");
    assertEquals(expectedSettingsSummary, streamConsumerSettings.getSettingsSummary());
    assertEquals(System.getProperty("user.home"), streamConsumerSettings.getOutputFolder());
  }

  /**
   * Test {@link StreamConsumerSettings#loadSettings(DBRRunnableContext, DataTransferSettings,
   * Map)}.
   *
   * <p>Method under test: {@link StreamConsumerSettings#loadSettings(DBRRunnableContext,
   * DataTransferSettings, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamConsumerSettings.loadSettings(DBRRunnableContext, DataTransferSettings, Map)"
  })
  public void testLoadSettings2() {
    // Arrange
    StreamConsumerSettings streamConsumerSettings = new StreamConsumerSettings();
    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);

    HashMap<String, Object> settings = new HashMap<>();
    settings.put("lobEncoding", "42");

    // Act
    streamConsumerSettings.loadSettings(runnableContext, null, settings);

    // Assert
    Map<String, Map<String, Object>> eventProcessors = streamConsumerSettings.getEventProcessors();
    assertEquals(1, eventProcessors.size());
    assertEquals(
        BlobFileConflictBehavior.PATCHNAME, streamConsumerSettings.getBlobFileConflictBehavior());
    assertEquals(
        DataFileConflictBehavior.PATCHNAME, streamConsumerSettings.getDataFileConflictBehavior());
    assertFalse(streamConsumerSettings.isOutputEncodingBOM());
    assertTrue(eventProcessors.get("failedExportFileCleaner").isEmpty());
    String expectedSettingsSummary =
        String.join(
            "",
            "\tWrite to the single file: No\n\tDirectory: ",
            System.getProperty("user.home"),
            "\n"
                + "\tFile name pattern: ${table}_${timestamp}\n"
                + "\tOn object data file name conflict: Autofix name\n"
                + "\tOn blob value file name conflict: Autofix name\n"
                + "\tEncoding: UTF-8\n"
                + "\tTimestamp pattern: yyyyMMddHHmm\n"
                + "\tInsert BOM: No\n"
                + "\tCompress: No\n"
                + "\tBinaries: INLINE\n"
                + "\tEncoding: BINARY\n");
    assertEquals(expectedSettingsSummary, streamConsumerSettings.getSettingsSummary());
    assertEquals(System.getProperty("user.home"), streamConsumerSettings.getOutputFolder());
  }

  /**
   * Test {@link StreamConsumerSettings#loadSettings(DBRRunnableContext, DataTransferSettings,
   * Map)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>When {@link HashMap#HashMap()} {@code outputEncodingBOM} is empty string.
   * </ul>
   *
   * <p>Method under test: {@link StreamConsumerSettings#loadSettings(DBRRunnableContext,
   * DataTransferSettings, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamConsumerSettings.loadSettings(DBRRunnableContext, DataTransferSettings, Map)"
  })
  public void testLoadSettings_givenEmptyString_whenHashMapOutputEncodingBOMIsEmptyString() {
    // Arrange
    StreamConsumerSettings streamConsumerSettings = new StreamConsumerSettings();
    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);

    HashMap<String, Object> settings = new HashMap<>();
    settings.put("outputFolder", "42");
    settings.put("outputEncodingBOM", "");

    // Act
    streamConsumerSettings.loadSettings(runnableContext, null, settings);

    // Assert
    assertEquals("42", streamConsumerSettings.getOutputFolder());
    assertEquals(
        "\tWrite to the single file: No\n"
            + "\tDirectory: 42\n"
            + "\tFile name pattern: ${table}_${timestamp}\n"
            + "\tOn object data file name conflict: Autofix name\n"
            + "\tOn blob value file name conflict: Autofix name\n"
            + "\tEncoding: UTF-8\n"
            + "\tTimestamp pattern: yyyyMMddHHmm\n"
            + "\tInsert BOM: No\n"
            + "\tCompress: No\n"
            + "\tBinaries: INLINE\n"
            + "\tEncoding: BINARY\n",
        streamConsumerSettings.getSettingsSummary());
    Map<String, Map<String, Object>> eventProcessors = streamConsumerSettings.getEventProcessors();
    assertEquals(1, eventProcessors.size());
    assertEquals(
        BlobFileConflictBehavior.PATCHNAME, streamConsumerSettings.getBlobFileConflictBehavior());
    assertEquals(
        DataFileConflictBehavior.PATCHNAME, streamConsumerSettings.getDataFileConflictBehavior());
    assertFalse(streamConsumerSettings.isOutputEncodingBOM());
    assertTrue(eventProcessors.get("failedExportFileCleaner").isEmpty());
  }

  /**
   * Test {@link StreamConsumerSettings#loadSettings(DBRRunnableContext, DataTransferSettings,
   * Map)}.
   *
   * <ul>
   *   <li>Given {@code lobEncoding}.
   *   <li>When {@link HashMap#HashMap()} {@code lobEncoding} is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link StreamConsumerSettings#loadSettings(DBRRunnableContext,
   * DataTransferSettings, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamConsumerSettings.loadSettings(DBRRunnableContext, DataTransferSettings, Map)"
  })
  public void testLoadSettings_givenLobEncoding_whenHashMapLobEncodingIs42() {
    // Arrange
    StreamConsumerSettings streamConsumerSettings = new StreamConsumerSettings();
    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);

    HashMap<String, Object> settings = new HashMap<>();
    settings.put("outputFolder", "42");
    settings.put("lobEncoding", "42");

    // Act
    streamConsumerSettings.loadSettings(runnableContext, null, settings);

    // Assert
    assertEquals("42", streamConsumerSettings.getOutputFolder());
    assertEquals(
        "\tWrite to the single file: No\n"
            + "\tDirectory: 42\n"
            + "\tFile name pattern: ${table}_${timestamp}\n"
            + "\tOn object data file name conflict: Autofix name\n"
            + "\tOn blob value file name conflict: Autofix name\n"
            + "\tEncoding: UTF-8\n"
            + "\tTimestamp pattern: yyyyMMddHHmm\n"
            + "\tInsert BOM: No\n"
            + "\tCompress: No\n"
            + "\tBinaries: INLINE\n"
            + "\tEncoding: BINARY\n",
        streamConsumerSettings.getSettingsSummary());
    Map<String, Map<String, Object>> eventProcessors = streamConsumerSettings.getEventProcessors();
    assertEquals(1, eventProcessors.size());
    assertEquals(
        BlobFileConflictBehavior.PATCHNAME, streamConsumerSettings.getBlobFileConflictBehavior());
    assertEquals(
        DataFileConflictBehavior.PATCHNAME, streamConsumerSettings.getDataFileConflictBehavior());
    assertFalse(streamConsumerSettings.isOutputEncodingBOM());
    assertTrue(eventProcessors.get("failedExportFileCleaner").isEmpty());
  }

  /**
   * Test {@link StreamConsumerSettings#loadSettings(DBRRunnableContext, DataTransferSettings,
   * Map)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link HashMap#HashMap()} {@code lobEncoding} is one.
   * </ul>
   *
   * <p>Method under test: {@link StreamConsumerSettings#loadSettings(DBRRunnableContext,
   * DataTransferSettings, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamConsumerSettings.loadSettings(DBRRunnableContext, DataTransferSettings, Map)"
  })
  public void testLoadSettings_givenOne_whenHashMapLobEncodingIsOne() {
    // Arrange
    StreamConsumerSettings streamConsumerSettings = new StreamConsumerSettings();
    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);

    HashMap<String, Object> settings = new HashMap<>();
    settings.put("outputFolder", "42");
    settings.put("lobEncoding", 1);

    // Act
    streamConsumerSettings.loadSettings(runnableContext, null, settings);

    // Assert
    assertEquals("42", streamConsumerSettings.getOutputFolder());
    assertEquals(
        "\tWrite to the single file: No\n"
            + "\tDirectory: 42\n"
            + "\tFile name pattern: ${table}_${timestamp}\n"
            + "\tOn object data file name conflict: Autofix name\n"
            + "\tOn blob value file name conflict: Autofix name\n"
            + "\tEncoding: UTF-8\n"
            + "\tTimestamp pattern: yyyyMMddHHmm\n"
            + "\tInsert BOM: No\n"
            + "\tCompress: No\n"
            + "\tBinaries: INLINE\n"
            + "\tEncoding: BINARY\n",
        streamConsumerSettings.getSettingsSummary());
    Map<String, Map<String, Object>> eventProcessors = streamConsumerSettings.getEventProcessors();
    assertEquals(1, eventProcessors.size());
    assertEquals(
        BlobFileConflictBehavior.PATCHNAME, streamConsumerSettings.getBlobFileConflictBehavior());
    assertEquals(
        DataFileConflictBehavior.PATCHNAME, streamConsumerSettings.getDataFileConflictBehavior());
    assertFalse(streamConsumerSettings.isOutputEncodingBOM());
    assertTrue(eventProcessors.get("failedExportFileCleaner").isEmpty());
  }

  /**
   * Test {@link StreamConsumerSettings#loadSettings(DBRRunnableContext, DataTransferSettings,
   * Map)}.
   *
   * <ul>
   *   <li>Given {@code outputEncodingBOM}.
   *   <li>When {@link HashMap#HashMap()} {@code outputEncodingBOM} is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link StreamConsumerSettings#loadSettings(DBRRunnableContext,
   * DataTransferSettings, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamConsumerSettings.loadSettings(DBRRunnableContext, DataTransferSettings, Map)"
  })
  public void testLoadSettings_givenOutputEncodingBOM_whenHashMapOutputEncodingBOMIs42() {
    // Arrange
    StreamConsumerSettings streamConsumerSettings = new StreamConsumerSettings();
    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);

    HashMap<String, Object> settings = new HashMap<>();
    settings.put("outputFolder", "42");
    settings.put("outputEncodingBOM", "42");

    // Act
    streamConsumerSettings.loadSettings(runnableContext, null, settings);

    // Assert
    assertEquals("42", streamConsumerSettings.getOutputFolder());
    assertEquals(
        "\tWrite to the single file: No\n"
            + "\tDirectory: 42\n"
            + "\tFile name pattern: ${table}_${timestamp}\n"
            + "\tOn object data file name conflict: Autofix name\n"
            + "\tOn blob value file name conflict: Autofix name\n"
            + "\tEncoding: UTF-8\n"
            + "\tTimestamp pattern: yyyyMMddHHmm\n"
            + "\tInsert BOM: No\n"
            + "\tCompress: No\n"
            + "\tBinaries: INLINE\n"
            + "\tEncoding: BINARY\n",
        streamConsumerSettings.getSettingsSummary());
    Map<String, Map<String, Object>> eventProcessors = streamConsumerSettings.getEventProcessors();
    assertEquals(1, eventProcessors.size());
    assertEquals(
        BlobFileConflictBehavior.PATCHNAME, streamConsumerSettings.getBlobFileConflictBehavior());
    assertEquals(
        DataFileConflictBehavior.PATCHNAME, streamConsumerSettings.getDataFileConflictBehavior());
    assertFalse(streamConsumerSettings.isOutputEncodingBOM());
    assertTrue(eventProcessors.get("failedExportFileCleaner").isEmpty());
  }

  /**
   * Test {@link StreamConsumerSettings#loadSettings(DBRRunnableContext, DataTransferSettings,
   * Map)}.
   *
   * <ul>
   *   <li>Then {@link StreamConsumerSettings} (default constructor) OutputEncodingBOM.
   * </ul>
   *
   * <p>Method under test: {@link StreamConsumerSettings#loadSettings(DBRRunnableContext,
   * DataTransferSettings, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamConsumerSettings.loadSettings(DBRRunnableContext, DataTransferSettings, Map)"
  })
  public void testLoadSettings_thenStreamConsumerSettingsOutputEncodingBOM() {
    // Arrange
    StreamConsumerSettings streamConsumerSettings = new StreamConsumerSettings();
    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);

    HashMap<String, Object> settings = new HashMap<>();
    settings.put("outputFolder", "42");
    settings.put("outputEncodingBOM", true);

    // Act
    streamConsumerSettings.loadSettings(runnableContext, null, settings);

    // Assert
    assertEquals("42", streamConsumerSettings.getOutputFolder());
    assertEquals(
        "\tWrite to the single file: No\n"
            + "\tDirectory: 42\n"
            + "\tFile name pattern: ${table}_${timestamp}\n"
            + "\tOn object data file name conflict: Autofix name\n"
            + "\tOn blob value file name conflict: Autofix name\n"
            + "\tEncoding: UTF-8\n"
            + "\tTimestamp pattern: yyyyMMddHHmm\n"
            + "\tInsert BOM: Yes\n"
            + "\tCompress: No\n"
            + "\tBinaries: INLINE\n"
            + "\tEncoding: BINARY\n",
        streamConsumerSettings.getSettingsSummary());
    Map<String, Map<String, Object>> eventProcessors = streamConsumerSettings.getEventProcessors();
    assertEquals(1, eventProcessors.size());
    assertEquals(
        BlobFileConflictBehavior.PATCHNAME, streamConsumerSettings.getBlobFileConflictBehavior());
    assertEquals(
        DataFileConflictBehavior.PATCHNAME, streamConsumerSettings.getDataFileConflictBehavior());
    assertTrue(eventProcessors.get("failedExportFileCleaner").isEmpty());
    assertTrue(streamConsumerSettings.isOutputEncodingBOM());
  }

  /**
   * Test {@link StreamConsumerSettings#loadSettings(DBRRunnableContext, DataTransferSettings,
   * Map)}.
   *
   * <ul>
   *   <li>Then {@link StreamConsumerSettings} (default constructor) OutputFolder is {@link
   *       Boolean#TRUE} toString.
   * </ul>
   *
   * <p>Method under test: {@link StreamConsumerSettings#loadSettings(DBRRunnableContext,
   * DataTransferSettings, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamConsumerSettings.loadSettings(DBRRunnableContext, DataTransferSettings, Map)"
  })
  public void testLoadSettings_thenStreamConsumerSettingsOutputFolderIsTrueToString() {
    // Arrange
    StreamConsumerSettings streamConsumerSettings = new StreamConsumerSettings();
    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);

    HashMap<String, Object> settings = new HashMap<>();
    settings.put("outputFolder", true);
    settings.put("outputEncodingBOM", "42");

    // Act
    streamConsumerSettings.loadSettings(runnableContext, null, settings);

    // Assert
    assertEquals(
        "\tWrite to the single file: No\n"
            + "\tDirectory: true\n"
            + "\tFile name pattern: ${table}_${timestamp}\n"
            + "\tOn object data file name conflict: Autofix name\n"
            + "\tOn blob value file name conflict: Autofix name\n"
            + "\tEncoding: UTF-8\n"
            + "\tTimestamp pattern: yyyyMMddHHmm\n"
            + "\tInsert BOM: No\n"
            + "\tCompress: No\n"
            + "\tBinaries: INLINE\n"
            + "\tEncoding: BINARY\n",
        streamConsumerSettings.getSettingsSummary());
    Map<String, Map<String, Object>> eventProcessors = streamConsumerSettings.getEventProcessors();
    assertEquals(1, eventProcessors.size());
    assertEquals(
        BlobFileConflictBehavior.PATCHNAME, streamConsumerSettings.getBlobFileConflictBehavior());
    assertEquals(
        DataFileConflictBehavior.PATCHNAME, streamConsumerSettings.getDataFileConflictBehavior());
    assertFalse(streamConsumerSettings.isOutputEncodingBOM());
    assertTrue(eventProcessors.get("failedExportFileCleaner").isEmpty());
    assertEquals(Boolean.TRUE.toString(), streamConsumerSettings.getOutputFolder());
  }

  /**
   * Test {@link StreamConsumerSettings#saveSettings(Map)}.
   *
   * <ul>
   *   <li>Given {@link StreamConsumerSettings} (default constructor).
   *   <li>Then {@link HashMap#HashMap()} size is {@link Short#SIZE}.
   * </ul>
   *
   * <p>Method under test: {@link StreamConsumerSettings#saveSettings(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamConsumerSettings.saveSettings(Map)"})
  public void testSaveSettings_givenStreamConsumerSettings_thenHashMapSizeIsSize() {
    // Arrange
    StreamConsumerSettings streamConsumerSettings = new StreamConsumerSettings();
    HashMap<String, Object> settings = new HashMap<>();

    // Act
    streamConsumerSettings.saveSettings(settings);

    // Assert
    assertEquals(Short.SIZE, settings.size());
    assertEquals("", settings.get("formatterProfile"));
    assertEquals("${table}_${timestamp}", settings.get("outputFilePattern"));
    assertEquals("ASK", settings.get("dataFileConflictBehavior"));
    assertEquals("BINARY", settings.get("lobEncoding"));
    assertEquals("UI", settings.get("valueFormat"));
    assertEquals("yyyyMMddHHmm", settings.get("outputTimestampPattern"));
    assertEquals(10000000L, ((Long) settings.get("maxOutFileSize")).longValue());
    assertFalse((Boolean) settings.get("outputClipboard"));
    assertFalse((Boolean) settings.get("outputEncodingBOM"));
    assertFalse((Boolean) settings.get("splitOutFiles"));
    assertFalse((Boolean) settings.get("useSingleFile"));
  }

  /**
   * Test {@link StreamConsumerSettings#saveSettings(Map)}.
   *
   * <ul>
   *   <li>Then {@link HashMap#HashMap()} size is seventeen.
   * </ul>
   *
   * <p>Method under test: {@link StreamConsumerSettings#saveSettings(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamConsumerSettings.saveSettings(Map)"})
  public void testSaveSettings_thenHashMapSizeIsSeventeen() {
    // Arrange
    StreamConsumerSettings streamConsumerSettings = new StreamConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    streamConsumerSettings.addDataMapping(
        new StreamMappingContainer(new StreamEntityMapping(inputFile)));
    HashMap<String, Object> settings = new HashMap<>();

    // Act
    streamConsumerSettings.saveSettings(settings);

    // Assert
    assertEquals(17, settings.size());
    assertEquals("", settings.get("formatterProfile"));
    assertEquals("${table}_${timestamp}", settings.get("outputFilePattern"));
    assertEquals("ASK", settings.get("dataFileConflictBehavior"));
    assertEquals("BINARY", settings.get("lobEncoding"));
    assertEquals("UI", settings.get("valueFormat"));
    assertEquals("yyyyMMddHHmm", settings.get("outputTimestampPattern"));
    assertEquals(10000000L, ((Long) settings.get("maxOutFileSize")).longValue());
    assertFalse((Boolean) settings.get("outputClipboard"));
    assertFalse((Boolean) settings.get("outputEncodingBOM"));
    assertFalse((Boolean) settings.get("splitOutFiles"));
    assertFalse((Boolean) settings.get("useSingleFile"));
  }

  /**
   * Test {@link StreamConsumerSettings#getSettingsSummary()}.
   *
   * <p>Method under test: {@link StreamConsumerSettings#getSettingsSummary()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StreamConsumerSettings.getSettingsSummary()"})
  public void testGetSettingsSummary() {
    // Arrange
    StreamConsumerSettings streamConsumerSettings = new StreamConsumerSettings();
    streamConsumerSettings.setOutputClipboard(true);

    // Act and Assert
    assertEquals(
        "\tCopy to clipboard: Yes\n\tCompress: No\n\tBinaries: INLINE\n\tEncoding: BINARY\n",
        streamConsumerSettings.getSettingsSummary());
  }

  /**
   * Test {@link StreamConsumerSettings#getSettingsSummary()}.
   *
   * <ul>
   *   <li>Given {@link StreamConsumerSettings} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link StreamConsumerSettings#getSettingsSummary()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StreamConsumerSettings.getSettingsSummary()"})
  public void testGetSettingsSummary_givenStreamConsumerSettings() {
    // Arrange and Act
    String actualSettingsSummary = new StreamConsumerSettings().getSettingsSummary();

    // Assert
    String expectedSettingsSummary =
        String.join(
            "",
            "\tWrite to the single file: No\n\tDirectory: ",
            System.getProperty("user.home"),
            "\n"
                + "\tFile name pattern: ${table}_${timestamp}\n"
                + "\tOn object data file name conflict: Ask\n"
                + "\tOn blob value file name conflict: Ask\n"
                + "\tEncoding: UTF-8\n"
                + "\tTimestamp pattern: yyyyMMddHHmm\n"
                + "\tInsert BOM: No\n"
                + "\tCompress: No\n"
                + "\tBinaries: INLINE\n"
                + "\tEncoding: BINARY\n");
    assertEquals(expectedSettingsSummary, actualSettingsSummary);
  }

  /**
   * Test {@link StreamConsumerSettings#getSettingsSummary()}.
   *
   * <ul>
   *   <li>Given {@link StreamConsumerSettings} (default constructor) OutputEncodingBOM is {@code
   *       true}.
   * </ul>
   *
   * <p>Method under test: {@link StreamConsumerSettings#getSettingsSummary()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StreamConsumerSettings.getSettingsSummary()"})
  public void testGetSettingsSummary_givenStreamConsumerSettingsOutputEncodingBOMIsTrue() {
    // Arrange
    StreamConsumerSettings streamConsumerSettings = new StreamConsumerSettings();
    streamConsumerSettings.setOutputEncodingBOM(true);

    // Act
    String actualSettingsSummary = streamConsumerSettings.getSettingsSummary();

    // Assert
    String expectedSettingsSummary =
        String.join(
            "",
            "\tWrite to the single file: No\n\tDirectory: ",
            System.getProperty("user.home"),
            "\n"
                + "\tFile name pattern: ${table}_${timestamp}\n"
                + "\tOn object data file name conflict: Ask\n"
                + "\tOn blob value file name conflict: Ask\n"
                + "\tEncoding: UTF-8\n"
                + "\tTimestamp pattern: yyyyMMddHHmm\n"
                + "\tInsert BOM: Yes\n"
                + "\tCompress: No\n"
                + "\tBinaries: INLINE\n"
                + "\tEncoding: BINARY\n");
    assertEquals(expectedSettingsSummary, actualSettingsSummary);
  }

  /**
   * Test new {@link StreamConsumerSettings} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link StreamConsumerSettings}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamConsumerSettings.<init>()"})
  public void testNewStreamConsumerSettings() {
    // Arrange and Act
    StreamConsumerSettings actualStreamConsumerSettings = new StreamConsumerSettings();

    // Assert
    assertEquals("${table}_${timestamp}", actualStreamConsumerSettings.getOutputFilePattern());
    assertEquals("UTF-8", actualStreamConsumerSettings.getOutputEncoding());
    assertEquals("yyyyMMddHHmm", actualStreamConsumerSettings.getOutputTimestampPattern());
    assertNull(actualStreamConsumerSettings.getFormatterProfile());
    assertEquals(10000000L, actualStreamConsumerSettings.getMaxOutFileSize());
    assertEquals(DBDDisplayFormat.UI, actualStreamConsumerSettings.getValueFormat());
    assertEquals(
        BlobFileConflictBehavior.ASK, actualStreamConsumerSettings.getBlobFileConflictBehavior());
    assertEquals(
        DataFileConflictBehavior.ASK, actualStreamConsumerSettings.getDataFileConflictBehavior());
    assertEquals(LobEncoding.BINARY, actualStreamConsumerSettings.getLobEncoding());
    assertEquals(LobExtractType.INLINE, actualStreamConsumerSettings.getLobExtractType());
    assertFalse(actualStreamConsumerSettings.isCompressResults());
    assertFalse(actualStreamConsumerSettings.isOutputClipboard());
    assertFalse(actualStreamConsumerSettings.isOutputEncodingBOM());
    assertFalse(actualStreamConsumerSettings.isSplitOutFiles());
    assertFalse(actualStreamConsumerSettings.isUseSingleFile());
    assertTrue(actualStreamConsumerSettings.getDataMappings().isEmpty());
    assertTrue(actualStreamConsumerSettings.getEventProcessors().isEmpty());
    String expectedSettingsSummary =
        String.join(
            "",
            "\tWrite to the single file: No\n\tDirectory: ",
            System.getProperty("user.home"),
            "\n"
                + "\tFile name pattern: ${table}_${timestamp}\n"
                + "\tOn object data file name conflict: Ask\n"
                + "\tOn blob value file name conflict: Ask\n"
                + "\tEncoding: UTF-8\n"
                + "\tTimestamp pattern: yyyyMMddHHmm\n"
                + "\tInsert BOM: No\n"
                + "\tCompress: No\n"
                + "\tBinaries: INLINE\n"
                + "\tEncoding: BINARY\n");
    assertEquals(expectedSettingsSummary, actualStreamConsumerSettings.getSettingsSummary());
    assertEquals(System.getProperty("user.home"), actualStreamConsumerSettings.getOutputFolder());
  }
}
