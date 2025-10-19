package org.jkiss.dbeaver.tools.transfer.stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCResultSet;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.impl.local.LocalStatement;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.DBRRunnableContext;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.task.DBTTask;
import org.jkiss.dbeaver.tools.transfer.serialize.SerializerContext;
import org.jkiss.dbeaver.tools.transfer.stream.StreamTransferConsumer.ObjectSerializer;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class StreamTransferConsumerDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link StreamTransferConsumer}
   *   <li>{@link StreamTransferConsumer#fetchEnd(DBCSession, DBCResultSet)}
   *   <li>{@link StreamTransferConsumer#startTransfer(DBRProgressMonitor)}
   *   <li>{@link StreamTransferConsumer#close()}
   *   <li>{@link StreamTransferConsumer#getDataSourceContainer()}
   *   <li>{@link StreamTransferConsumer#getDatabaseObject()}
   *   <li>{@link StreamTransferConsumer#getObjectIcon()}
   *   <li>{@link StreamTransferConsumer#getOutputFiles()}
   *   <li>{@link StreamTransferConsumer#getProject()}
   *   <li>{@link StreamTransferConsumer#getSettings()}
   *   <li>{@link StreamTransferConsumer#getTargetObject()}
   *   <li>{@link StreamTransferConsumer#getTargetObjectContainer()}
   *   <li>{@link StreamTransferConsumer#isBeforeFirstRow()}
   *   <li>{@link StreamTransferConsumer#isConfigurationComplete()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamTransferConsumer.<init>()",
    "void StreamTransferConsumer.close()",
    "void StreamTransferConsumer.fetchEnd(DBCSession, DBCResultSet)",
    "DBPDataSourceContainer StreamTransferConsumer.getDataSourceContainer()",
    "DBSObject StreamTransferConsumer.getDatabaseObject()",
    "DBPImage StreamTransferConsumer.getObjectIcon()",
    "List StreamTransferConsumer.getOutputFiles()",
    "DBPProject StreamTransferConsumer.getProject()",
    "StreamConsumerSettings StreamTransferConsumer.getSettings()",
    "Object StreamTransferConsumer.getTargetObject()",
    "Object StreamTransferConsumer.getTargetObjectContainer()",
    "boolean StreamTransferConsumer.isBeforeFirstRow()",
    "boolean StreamTransferConsumer.isConfigurationComplete()",
    "void StreamTransferConsumer.startTransfer(DBRProgressMonitor)"
  })
  public void testGettersAndSetters() throws DBCException {
    // Arrange and Act
    StreamTransferConsumer actualStreamTransferConsumer = new StreamTransferConsumer();
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferResultSet resultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile));
    actualStreamTransferConsumer.fetchEnd(null, resultSet);
    actualStreamTransferConsumer.startTransfer(new LoggingProgressMonitor());
    actualStreamTransferConsumer.close();
    DBPDataSourceContainer actualDataSourceContainer =
        actualStreamTransferConsumer.getDataSourceContainer();
    DBSObject actualDatabaseObject = actualStreamTransferConsumer.getDatabaseObject();
    DBPImage actualObjectIcon = actualStreamTransferConsumer.getObjectIcon();
    List<Path> actualOutputFiles = actualStreamTransferConsumer.getOutputFiles();
    DBPProject actualProject = actualStreamTransferConsumer.getProject();
    StreamConsumerSettings actualSettings = actualStreamTransferConsumer.getSettings();
    Object actualTargetObject = actualStreamTransferConsumer.getTargetObject();
    Object actualTargetObjectContainer = actualStreamTransferConsumer.getTargetObjectContainer();
    boolean actualIsBeforeFirstRowResult = actualStreamTransferConsumer.isBeforeFirstRow();
    boolean actualIsConfigurationCompleteResult =
        actualStreamTransferConsumer.isConfigurationComplete();

    // Assert
    assertNull(actualTargetObject);
    assertNull(actualTargetObjectContainer);
    assertNull(actualDataSourceContainer);
    assertNull(actualObjectIcon);
    assertNull(actualProject);
    assertNull(actualDatabaseObject);
    assertNull(actualSettings);
    assertTrue(actualOutputFiles.isEmpty());
    assertTrue(actualIsBeforeFirstRowResult);
    assertTrue(actualIsConfigurationCompleteResult);
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
    "StreamTransferConsumer ObjectSerializer.deserializeObject(DBRRunnableContext, SerializerContext, DBTTask, Map)"
  })
  public void
      testObjectSerializerDeserializeObjectWithDBRRunnableContextSerializerContextDBTTaskMap()
          throws DBException {
    // Arrange
    ObjectSerializer objectSerializer = new ObjectSerializer();
    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);
    SerializerContext serializeContext = new SerializerContext();
    DBTTask objectContext = mock(DBTTask.class);

    // Act
    StreamTransferConsumer actualDeserializeObjectResult =
        objectSerializer.deserializeObject(
            runnableContext, serializeContext, objectContext, new HashMap<>());

    // Assert
    assertNull(actualDeserializeObjectResult.getTargetObject());
    assertNull(actualDeserializeObjectResult.getTargetObjectContainer());
    assertNull(actualDeserializeObjectResult.getDataSourceContainer());
    assertNull(actualDeserializeObjectResult.getObjectIcon());
    assertNull(actualDeserializeObjectResult.getProject());
    assertNull(actualDeserializeObjectResult.getDatabaseObject());
    assertNull(actualDeserializeObjectResult.getSettings());
    assertEquals(0L, actualDeserializeObjectResult.getBytesWritten());
    assertTrue(actualDeserializeObjectResult.getOutputFiles().isEmpty());
    assertTrue(actualDeserializeObjectResult.isBeforeFirstRow());
    assertTrue(actualDeserializeObjectResult.isConfigurationComplete());
  }
}
