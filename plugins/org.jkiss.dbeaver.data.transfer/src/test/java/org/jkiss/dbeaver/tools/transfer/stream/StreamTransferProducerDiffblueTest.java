package org.jkiss.dbeaver.tools.transfer.stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
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
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBIcon;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.fs.DBFFileSystemManager;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.DBRRunnableContext;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.task.DBTTask;
import org.jkiss.dbeaver.tools.transfer.IDataTransferConsumer;
import org.jkiss.dbeaver.tools.transfer.IDataTransferProcessor;
import org.jkiss.dbeaver.tools.transfer.database.DatabaseTransferConsumer;
import org.jkiss.dbeaver.tools.transfer.registry.DataTransferProcessorDescriptor;
import org.jkiss.dbeaver.tools.transfer.serialize.SerializerContext;
import org.jkiss.dbeaver.tools.transfer.stream.StreamTransferProducer.ObjectSerializer;
import org.jkiss.dbeaver.tools.transfer.stream.model.StreamDataSourceContainer;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class StreamTransferProducerDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link StreamTransferProducer#StreamTransferProducer(StreamEntityMapping)}
   *   <li>{@link StreamTransferProducer#getEntityMapping()}
   *   <li>{@link StreamTransferProducer#getObjectContainerIcon()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamTransferProducer.<init>()",
    "void StreamTransferProducer.<init>(StreamEntityMapping)",
    "void StreamTransferProducer.<init>(StreamEntityMapping, DataTransferProcessorDescriptor)",
    "StreamEntityMapping StreamTransferProducer.getEntityMapping()",
    "DBPImage StreamTransferProducer.getObjectContainerIcon()"
  })
  public void testGettersAndSetters() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping entityMapping = new StreamEntityMapping(inputFile);

    // Act
    StreamTransferProducer actualStreamTransferProducer = new StreamTransferProducer(entityMapping);
    StreamEntityMapping actualEntityMapping = actualStreamTransferProducer.getEntityMapping();
    DBPImage actualObjectContainerIcon = actualStreamTransferProducer.getObjectContainerIcon();

    // Assert
    assertSame(entityMapping, actualStreamTransferProducer.getDatabaseObject());
    assertSame(entityMapping, actualEntityMapping);
    assertSame(((DBIcon) actualObjectContainerIcon).TREE_FOLDER, actualObjectContainerIcon);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return DatabaseObject is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link StreamTransferProducer#StreamTransferProducer()}
   *   <li>{@link StreamTransferProducer#getEntityMapping()}
   *   <li>{@link StreamTransferProducer#getObjectContainerIcon()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamTransferProducer.<init>()",
    "void StreamTransferProducer.<init>(StreamEntityMapping)",
    "void StreamTransferProducer.<init>(StreamEntityMapping, DataTransferProcessorDescriptor)",
    "StreamEntityMapping StreamTransferProducer.getEntityMapping()",
    "DBPImage StreamTransferProducer.getObjectContainerIcon()"
  })
  public void testGettersAndSetters_thenReturnDatabaseObjectIsNull() {
    // Arrange and Act
    StreamTransferProducer actualStreamTransferProducer = new StreamTransferProducer();
    StreamEntityMapping actualEntityMapping = actualStreamTransferProducer.getEntityMapping();
    DBPImage actualObjectContainerIcon = actualStreamTransferProducer.getObjectContainerIcon();

    // Assert
    assertNull(actualStreamTransferProducer.getDatabaseObject());
    assertNull(actualEntityMapping);
    assertSame(((DBIcon) actualObjectContainerIcon).TREE_FOLDER, actualObjectContainerIcon);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link StreamTransferProducer#StreamTransferProducer(StreamEntityMapping,
   *       DataTransferProcessorDescriptor)}
   *   <li>{@link StreamTransferProducer#getEntityMapping()}
   *   <li>{@link StreamTransferProducer#getObjectContainerIcon()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamTransferProducer.<init>()",
    "void StreamTransferProducer.<init>(StreamEntityMapping)",
    "void StreamTransferProducer.<init>(StreamEntityMapping, DataTransferProcessorDescriptor)",
    "StreamEntityMapping StreamTransferProducer.getEntityMapping()",
    "DBPImage StreamTransferProducer.getObjectContainerIcon()"
  })
  public void testGettersAndSetters_whenNull() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping entityMapping = new StreamEntityMapping(inputFile);

    // Act
    StreamTransferProducer actualStreamTransferProducer =
        new StreamTransferProducer(entityMapping, null);
    StreamEntityMapping actualEntityMapping = actualStreamTransferProducer.getEntityMapping();
    DBPImage actualObjectContainerIcon = actualStreamTransferProducer.getObjectContainerIcon();

    // Assert
    assertSame(entityMapping, actualStreamTransferProducer.getDatabaseObject());
    assertSame(entityMapping, actualEntityMapping);
    assertSame(((DBIcon) actualObjectContainerIcon).TREE_FOLDER, actualObjectContainerIcon);
  }

  /**
   * Test {@link StreamTransferProducer#getDatabaseObject()}.
   *
   * <p>Method under test: {@link StreamTransferProducer#getDatabaseObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"StreamEntityMapping StreamTransferProducer.getDatabaseObject()"})
  public void testGetDatabaseObject() {
    // Arrange, Act and Assert
    assertNull(new StreamTransferProducer().getDatabaseObject());
  }

  /**
   * Test {@link StreamTransferProducer#getProject()}.
   *
   * <p>Method under test: {@link StreamTransferProducer#getProject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPProject StreamTransferProducer.getProject()"})
  public void testGetProject() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertNull(new StreamTransferProducer(new StreamEntityMapping(inputFile)).getProject());
  }

  /**
   * Test {@link StreamTransferProducer#getProject()}.
   *
   * <ul>
   *   <li>Given {@link StreamTransferProducer#StreamTransferProducer()}.
   * </ul>
   *
   * <p>Method under test: {@link StreamTransferProducer#getProject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPProject StreamTransferProducer.getProject()"})
  public void testGetProject_givenStreamTransferProducer() {
    // Arrange, Act and Assert
    assertNull(new StreamTransferProducer().getProject());
  }

  /**
   * Test {@link StreamTransferProducer#getDataSourceContainer()}.
   *
   * <ul>
   *   <li>Given {@link StreamTransferProducer#StreamTransferProducer()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link StreamTransferProducer#getDataSourceContainer()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.DBPDataSourceContainer StreamTransferProducer.getDataSourceContainer()"
  })
  public void testGetDataSourceContainer_givenStreamTransferProducer_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new StreamTransferProducer().getDataSourceContainer());
  }

  /**
   * Test {@link StreamTransferProducer#getObjectName()}.
   *
   * <ul>
   *   <li>Given {@link StreamTransferProducer#StreamTransferProducer()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link StreamTransferProducer#getObjectName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StreamTransferProducer.getObjectName()"})
  public void testGetObjectName_givenStreamTransferProducer_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new StreamTransferProducer().getObjectName());
  }

  /**
   * Test {@link StreamTransferProducer#getObjectName()}.
   *
   * <ul>
   *   <li>Then return {@code test.txt}.
   * </ul>
   *
   * <p>Method under test: {@link StreamTransferProducer#getObjectName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StreamTransferProducer.getObjectName()"})
  public void testGetObjectName_thenReturnTestTxt() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertEquals(
        "test.txt", new StreamTransferProducer(new StreamEntityMapping(inputFile)).getObjectName());
  }

  /**
   * Test {@link StreamTransferProducer#getObjectFullName(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link StreamTransferProducer#getObjectFullName(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StreamTransferProducer.getObjectFullName(DBRProgressMonitor)"})
  public void testGetObjectFullName() throws IOException {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferProducer streamTransferProducer =
        new StreamTransferProducer(new StreamEntityMapping(inputFile));

    // Act and Assert
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString(),
        streamTransferProducer.getObjectFullName(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link StreamTransferProducer#getObjectFullName(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link StreamTransferProducer#StreamTransferProducer()}.
   *   <li>Then return {@code N/A}.
   * </ul>
   *
   * <p>Method under test: {@link StreamTransferProducer#getObjectFullName(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StreamTransferProducer.getObjectFullName(DBRProgressMonitor)"})
  public void testGetObjectFullName_givenStreamTransferProducer_thenReturnNA() throws IOException {
    // Arrange
    StreamTransferProducer streamTransferProducer = new StreamTransferProducer();

    // Act and Assert
    assertEquals("N/A", streamTransferProducer.getObjectFullName(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link StreamTransferProducer#getObjectIcon()}.
   *
   * <ul>
   *   <li>Given {@link StreamTransferProducer#StreamTransferProducer()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link StreamTransferProducer#getObjectIcon()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage StreamTransferProducer.getObjectIcon()"})
  public void testGetObjectIcon_givenStreamTransferProducer_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new StreamTransferProducer().getObjectIcon());
  }

  /**
   * Test {@link StreamTransferProducer#getObjectIcon()}.
   *
   * <ul>
   *   <li>Then calls {@link DataTransferProcessorDescriptor#getIcon()}.
   * </ul>
   *
   * <p>Method under test: {@link StreamTransferProducer#getObjectIcon()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage StreamTransferProducer.getObjectIcon()"})
  public void testGetObjectIcon_thenCallsGetIcon() {
    // Arrange
    DataTransferProcessorDescriptor defaultProcessor = mock(DataTransferProcessorDescriptor.class);
    when(defaultProcessor.getIcon()).thenReturn(mock(DBPImage.class));
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferProducer streamTransferProducer =
        new StreamTransferProducer(new StreamEntityMapping(inputFile), defaultProcessor);

    // Act
    streamTransferProducer.getObjectIcon();

    // Assert
    verify(defaultProcessor).getIcon();
  }

  /**
   * Test {@link StreamTransferProducer#getObjectContainerName()}.
   *
   * <ul>
   *   <li>Given {@link StreamTransferProducer#StreamTransferProducer()}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link StreamTransferProducer#getObjectContainerName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StreamTransferProducer.getObjectContainerName()"})
  public void testGetObjectContainerName_givenStreamTransferProducer_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", new StreamTransferProducer().getObjectContainerName());
  }

  /**
   * Test {@link StreamTransferProducer#getObjectContainerName()}.
   *
   * <ul>
   *   <li>Then return Property is {@code user.dir}.
   * </ul>
   *
   * <p>Method under test: {@link StreamTransferProducer#getObjectContainerName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StreamTransferProducer.getObjectContainerName()"})
  public void testGetObjectContainerName_thenReturnPropertyIsUserDir() throws DBException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPProject project = mock(DBPProject.class);
    when(project.getFileSystemManager())
        .thenReturn(new DBFFileSystemManager(mock(DBPProject.class)));
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    StreamEntityMapping entityMapping = new StreamEntityMapping(monitor, project, new HashMap<>());

    // Act
    String actualObjectContainerName =
        new StreamTransferProducer(entityMapping).getObjectContainerName();

    // Assert
    verify(project).getFileSystemManager();
    assertEquals(System.getProperty("user.dir"), actualObjectContainerName);
  }

  /**
   * Test {@link StreamTransferProducer#isConfigurationComplete()}.
   *
   * <ul>
   *   <li>Given {@link StreamTransferProducer#StreamTransferProducer()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link StreamTransferProducer#isConfigurationComplete()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StreamTransferProducer.isConfigurationComplete()"})
  public void testIsConfigurationComplete_givenStreamTransferProducer_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new StreamTransferProducer().isConfigurationComplete());
  }

  /**
   * Test {@link StreamTransferProducer#isConfigurationComplete()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link StreamTransferProducer#isConfigurationComplete()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StreamTransferProducer.isConfigurationComplete()"})
  public void testIsConfigurationComplete_thenReturnTrue() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertTrue(
        new StreamTransferProducer(new StreamEntityMapping(inputFile)).isConfigurationComplete());
  }

  /**
   * Test {@link StreamTransferProducer#getInputFile()}.
   *
   * <ul>
   *   <li>Given {@link StreamTransferProducer#StreamTransferProducer()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link StreamTransferProducer#getInputFile()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Path StreamTransferProducer.getInputFile()"})
  public void testGetInputFile_givenStreamTransferProducer_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new StreamTransferProducer().getInputFile());
  }

  /**
   * Test {@link StreamTransferProducer#getInputFile()}.
   *
   * <ul>
   *   <li>Then return Property is {@code java.io.tmpdir} is {@code test.txt}.
   * </ul>
   *
   * <p>Method under test: {@link StreamTransferProducer#getInputFile()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Path StreamTransferProducer.getInputFile()"})
  public void testGetInputFile_thenReturnPropertyIsJavaIoTmpdirIsTestTxt() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertSame(
        inputFile, new StreamTransferProducer(new StreamEntityMapping(inputFile)).getInputFile());
  }

  /**
   * Test ObjectSerializer {@link ObjectSerializer#deserializeObject(DBRRunnableContext,
   * SerializerContext, DBTTask, Map)} with {@code DBRRunnableContext}, {@code SerializerContext},
   * {@code DBTTask}, {@code Map}.
   *
   * <p>Method under test: {@link ObjectSerializer#deserializeObject(DBRRunnableContext,
   * SerializerContext, DBTTask, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "StreamTransferProducer ObjectSerializer.deserializeObject(DBRRunnableContext, SerializerContext, DBTTask, Map)"
  })
  public void
      testObjectSerializerDeserializeObjectWithDBRRunnableContextSerializerContextDBTTaskMap()
          throws DBException {
    // Arrange
    ObjectSerializer objectSerializer = new ObjectSerializer();
    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);
    SerializerContext serializeContext = new SerializerContext();

    DBTTask objectContext = mock(DBTTask.class);
    when(objectContext.getProject()).thenReturn(null);

    // Act
    StreamTransferProducer actualDeserializeObjectResult =
        objectSerializer.deserializeObject(
            runnableContext, serializeContext, objectContext, new HashMap<>());

    // Assert
    verify(objectContext).getProject();
    assertTrue(actualDeserializeObjectResult.getObjectContainerIcon() instanceof DBIcon);
    assertTrue(
        actualDeserializeObjectResult.getDataSourceContainer()
            instanceof StreamDataSourceContainer);
    assertEquals("", actualDeserializeObjectResult.getObjectName());
    assertNull(actualDeserializeObjectResult.getObjectIcon());
    assertNull(actualDeserializeObjectResult.getProject());
    assertTrue(actualDeserializeObjectResult.isConfigurationComplete());
    assertEquals(
        System.getProperty("user.dir"), actualDeserializeObjectResult.getObjectContainerName());
  }

  /**
   * Test ObjectSerializer {@link ObjectSerializer#deserializeObject(DBRRunnableContext,
   * SerializerContext, DBTTask, Map)} with {@code DBRRunnableContext}, {@code SerializerContext},
   * {@code DBTTask}, {@code Map}.
   *
   * <p>Method under test: {@link ObjectSerializer#deserializeObject(DBRRunnableContext,
   * SerializerContext, DBTTask, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "StreamTransferProducer ObjectSerializer.deserializeObject(DBRRunnableContext, SerializerContext, DBTTask, Map)"
  })
  public void
      testObjectSerializerDeserializeObjectWithDBRRunnableContextSerializerContextDBTTaskMap2()
          throws DBException {
    // Arrange
    ObjectSerializer objectSerializer = new ObjectSerializer();
    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);
    SerializerContext serializeContext = new SerializerContext();

    DBTTask objectContext = mock(DBTTask.class);
    when(objectContext.getProject()).thenReturn(null);

    HashMap<String, Object> state = new HashMap<>();
    state.put("node", "42");

    // Act
    StreamTransferProducer actualDeserializeObjectResult =
        objectSerializer.deserializeObject(runnableContext, serializeContext, objectContext, state);

    // Assert
    verify(objectContext).getProject();
    assertTrue(actualDeserializeObjectResult.getObjectContainerIcon() instanceof DBIcon);
    assertTrue(
        actualDeserializeObjectResult.getDataSourceContainer()
            instanceof StreamDataSourceContainer);
    assertEquals("", actualDeserializeObjectResult.getObjectName());
    assertNull(actualDeserializeObjectResult.getObjectIcon());
    assertNull(actualDeserializeObjectResult.getProject());
    assertTrue(actualDeserializeObjectResult.isConfigurationComplete());
    assertEquals(
        System.getProperty("user.dir"), actualDeserializeObjectResult.getObjectContainerName());
  }

  /**
   * Test ObjectSerializer {@link ObjectSerializer#deserializeObject(DBRRunnableContext,
   * SerializerContext, DBTTask, Map)} with {@code DBRRunnableContext}, {@code SerializerContext},
   * {@code DBTTask}, {@code Map}.
   *
   * <p>Method under test: {@link ObjectSerializer#deserializeObject(DBRRunnableContext,
   * SerializerContext, DBTTask, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "StreamTransferProducer ObjectSerializer.deserializeObject(DBRRunnableContext, SerializerContext, DBTTask, Map)"
  })
  public void
      testObjectSerializerDeserializeObjectWithDBRRunnableContextSerializerContextDBTTaskMap3()
          throws DBException {
    // Arrange
    ObjectSerializer objectSerializer = new ObjectSerializer();
    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);
    SerializerContext serializeContext = new SerializerContext();

    DBTTask objectContext = mock(DBTTask.class);
    when(objectContext.getProject()).thenReturn(null);

    HashMap<String, Object> state = new HashMap<>();
    state.put("node", 42);
    state.put("processor", "");
    state.put("file", null);
    state.put("name", null);
    state.put("child", null);

    // Act
    StreamTransferProducer actualDeserializeObjectResult =
        objectSerializer.deserializeObject(runnableContext, serializeContext, objectContext, state);

    // Assert
    verify(objectContext).getProject();
    assertTrue(actualDeserializeObjectResult.getObjectContainerIcon() instanceof DBIcon);
    assertTrue(
        actualDeserializeObjectResult.getDataSourceContainer()
            instanceof StreamDataSourceContainer);
    assertEquals("", actualDeserializeObjectResult.getObjectName());
    assertNull(actualDeserializeObjectResult.getObjectIcon());
    assertNull(actualDeserializeObjectResult.getProject());
    assertTrue(actualDeserializeObjectResult.isConfigurationComplete());
    assertEquals(
        System.getProperty("user.dir"), actualDeserializeObjectResult.getObjectContainerName());
  }

  /**
   * Test ObjectSerializer {@link ObjectSerializer#deserializeObject(DBRRunnableContext,
   * SerializerContext, DBTTask, Map)} with {@code DBRRunnableContext}, {@code SerializerContext},
   * {@code DBTTask}, {@code Map}.
   *
   * <p>Method under test: {@link ObjectSerializer#deserializeObject(DBRRunnableContext,
   * SerializerContext, DBTTask, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "StreamTransferProducer ObjectSerializer.deserializeObject(DBRRunnableContext, SerializerContext, DBTTask, Map)"
  })
  public void
      testObjectSerializerDeserializeObjectWithDBRRunnableContextSerializerContextDBTTaskMap4()
          throws DBException {
    // Arrange
    ObjectSerializer objectSerializer = new ObjectSerializer();
    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);
    SerializerContext serializeContext = new SerializerContext();

    DBTTask objectContext = mock(DBTTask.class);
    when(objectContext.getProject()).thenReturn(null);

    HashMap<String, Object> state = new HashMap<>();
    state.put("node", "");
    state.put("processor", "");
    state.put("file", null);
    state.put("name", null);
    state.put("child", "42");

    // Act
    StreamTransferProducer actualDeserializeObjectResult =
        objectSerializer.deserializeObject(runnableContext, serializeContext, objectContext, state);

    // Assert
    verify(objectContext).getProject();
    assertTrue(actualDeserializeObjectResult.getObjectContainerIcon() instanceof DBIcon);
    assertTrue(
        actualDeserializeObjectResult.getDataSourceContainer()
            instanceof StreamDataSourceContainer);
    assertEquals("", actualDeserializeObjectResult.getObjectName());
    assertNull(actualDeserializeObjectResult.getObjectIcon());
    assertNull(actualDeserializeObjectResult.getProject());
    assertTrue(actualDeserializeObjectResult.isConfigurationComplete());
    assertEquals(
        System.getProperty("user.dir"), actualDeserializeObjectResult.getObjectContainerName());
  }

  /**
   * Test ObjectSerializer {@link ObjectSerializer#deserializeObject(DBRRunnableContext,
   * SerializerContext, DBTTask, Map)} with {@code DBRRunnableContext}, {@code SerializerContext},
   * {@code DBTTask}, {@code Map}.
   *
   * <p>Method under test: {@link ObjectSerializer#deserializeObject(DBRRunnableContext,
   * SerializerContext, DBTTask, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "StreamTransferProducer ObjectSerializer.deserializeObject(DBRRunnableContext, SerializerContext, DBTTask, Map)"
  })
  public void
      testObjectSerializerDeserializeObjectWithDBRRunnableContextSerializerContextDBTTaskMap5()
          throws DBException {
    // Arrange
    ObjectSerializer objectSerializer = new ObjectSerializer();
    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);
    SerializerContext serializeContext = new SerializerContext();

    DBTTask objectContext = mock(DBTTask.class);
    when(objectContext.getProject()).thenReturn(null);

    HashMap<String, Object> state = new HashMap<>();
    state.put("node", "");
    state.put("processor", "");
    state.put("file", "file:");
    state.put("name", null);
    state.put("child", null);

    // Act
    StreamTransferProducer actualDeserializeObjectResult =
        objectSerializer.deserializeObject(runnableContext, serializeContext, objectContext, state);

    // Assert
    verify(objectContext).getProject();
    assertTrue(actualDeserializeObjectResult.getObjectContainerIcon() instanceof DBIcon);
    assertTrue(
        actualDeserializeObjectResult.getDataSourceContainer()
            instanceof StreamDataSourceContainer);
    assertEquals("", actualDeserializeObjectResult.getObjectName());
    assertNull(actualDeserializeObjectResult.getObjectIcon());
    assertNull(actualDeserializeObjectResult.getProject());
    assertTrue(actualDeserializeObjectResult.isConfigurationComplete());
    String expectedObjectContainerName =
        String.join("", Paths.get(System.getProperty("user.dir"), "file").toString(), ":");
    assertEquals(
        expectedObjectContainerName, actualDeserializeObjectResult.getObjectContainerName());
  }

  /**
   * Test ObjectSerializer {@link ObjectSerializer#serializeObject(DBRRunnableContext, DBTTask,
   * StreamTransferProducer, Map)} with {@code DBRRunnableContext}, {@code DBTTask}, {@code
   * StreamTransferProducer}, {@code Map}.
   *
   * <p>Method under test: {@link ObjectSerializer#serializeObject(DBRRunnableContext, DBTTask,
   * StreamTransferProducer, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ObjectSerializer.serializeObject(DBRRunnableContext, DBTTask, StreamTransferProducer, Map)"
  })
  public void
      testObjectSerializerSerializeObjectWithDBRRunnableContextDBTTaskStreamTransferProducerMap()
          throws DBException {
    // Arrange
    ObjectSerializer objectSerializer = new ObjectSerializer();
    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);
    DBTTask context = mock(DBTTask.class);
    StreamTransferProducer object = new StreamTransferProducer();

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> objectSerializer.serializeObject(runnableContext, context, object, new HashMap<>()));
  }

  /**
   * Test {@link StreamTransferProducer#transferData(DBRProgressMonitor, IDataTransferConsumer,
   * IDataTransferProcessor, StreamProducerSettings, DBTTask)} with {@code DBRProgressMonitor},
   * {@code IDataTransferConsumer}, {@code IDataTransferProcessor}, {@code StreamProducerSettings},
   * {@code DBTTask}.
   *
   * <p>Method under test: {@link StreamTransferProducer#transferData(DBRProgressMonitor,
   * IDataTransferConsumer, IDataTransferProcessor, StreamProducerSettings, DBTTask)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamTransferProducer.transferData(DBRProgressMonitor, IDataTransferConsumer, IDataTransferProcessor, StreamProducerSettings, DBTTask)"
  })
  public void
      testTransferDataWithDBRProgressMonitorIDataTransferConsumerIDataTransferProcessorStreamProducerSettingsDBTTask()
          throws DBException {
    // Arrange
    StreamTransferProducer streamTransferProducer = new StreamTransferProducer();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DatabaseTransferConsumer consumer = new DatabaseTransferConsumer();

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            streamTransferProducer.transferData(
                monitor, consumer, null, new StreamProducerSettings(), mock(DBTTask.class)));
  }

  /**
   * Test {@link StreamTransferProducer#equals(Object)}, and {@link
   * StreamTransferProducer#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Method under test: {@link StreamTransferProducer#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StreamTransferProducer.equals(Object)"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    StreamTransferProducer streamTransferProducer = new StreamTransferProducer();
    StreamTransferProducer streamTransferProducer2 = new StreamTransferProducer();

    // Act and Assert
    assertEquals(streamTransferProducer, streamTransferProducer2);
    assertNotEquals(streamTransferProducer.hashCode(), streamTransferProducer2.hashCode());
  }

  /**
   * Test {@link StreamTransferProducer#equals(Object)}, and {@link
   * StreamTransferProducer#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Method under test: {@link StreamTransferProducer#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StreamTransferProducer.equals(Object)"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    StreamTransferProducer streamTransferProducer = new StreamTransferProducer();

    // Act and Assert
    assertEquals(streamTransferProducer, streamTransferProducer);
    int expectedHashCodeResult = streamTransferProducer.hashCode();
    assertEquals(expectedHashCodeResult, streamTransferProducer.hashCode());
  }

  /**
   * Test {@link StreamTransferProducer#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link StreamTransferProducer#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StreamTransferProducer.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferProducer streamTransferProducer =
        new StreamTransferProducer(new StreamEntityMapping(inputFile));

    // Act and Assert
    assertNotEquals(streamTransferProducer, new StreamTransferProducer());
  }

  /**
   * Test {@link StreamTransferProducer#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link StreamTransferProducer#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StreamTransferProducer.equals(Object)"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new StreamTransferProducer(), null);
  }

  /**
   * Test {@link StreamTransferProducer#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link StreamTransferProducer#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StreamTransferProducer.equals(Object)"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new StreamTransferProducer(), "Different type to StreamTransferProducer");
  }
}
