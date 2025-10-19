package org.jkiss.dbeaver.tools.transfer.processor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.task.DBTTask;
import org.jkiss.dbeaver.tools.transfer.stream.StreamConsumerSettings;
import org.jkiss.dbeaver.tools.transfer.stream.StreamTransferConsumer;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class FailedExportFileCleanerProcessorDiffblueTest {
  /**
   * Test {@link FailedExportFileCleanerProcessor#processError(DBRProgressMonitor, Throwable,
   * StreamTransferConsumer, DBTTask, Map)} with {@code DBRProgressMonitor}, {@code Throwable},
   * {@code StreamTransferConsumer}, {@code DBTTask}, {@code Map}.
   *
   * <p>Method under test: {@link FailedExportFileCleanerProcessor#processError(DBRProgressMonitor,
   * Throwable, StreamTransferConsumer, DBTTask, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void FailedExportFileCleanerProcessor.processError(DBRProgressMonitor, Throwable, StreamTransferConsumer, DBTTask, Map)"
  })
  public void testProcessErrorWithDBRProgressMonitorThrowableStreamTransferConsumerDBTTaskMap()
      throws IOException {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
      mockFiles.when(() -> Files.deleteIfExists(Mockito.<Path>any())).thenReturn(true);
      FailedExportFileCleanerProcessor failedExportFileCleanerProcessor =
          new FailedExportFileCleanerProcessor();
      LoggingProgressMonitor monitor = new LoggingProgressMonitor();
      Throwable error = new Throwable();

      StreamConsumerSettings streamConsumerSettings = new StreamConsumerSettings();
      streamConsumerSettings.setUseSingleFile(false);

      ArrayList<Path> pathList = new ArrayList<>();
      pathList.add(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      StreamTransferConsumer consumer = mock(StreamTransferConsumer.class);
      when(consumer.getOutputFiles()).thenReturn(pathList);
      when(consumer.getSettings()).thenReturn(streamConsumerSettings);
      DBTTask task = mock(DBTTask.class);

      // Act
      failedExportFileCleanerProcessor.processError(
          monitor, error, consumer, task, new HashMap<>());

      // Assert
      mockFiles.verify(() -> Files.deleteIfExists(Mockito.<Path>any()));
      verify(consumer).getOutputFiles();
      verify(consumer).getSettings();
    }
  }

  /**
   * Test {@link FailedExportFileCleanerProcessor#processError(DBRProgressMonitor, Throwable,
   * StreamTransferConsumer, DBTTask, Map)} with {@code DBRProgressMonitor}, {@code Throwable},
   * {@code StreamTransferConsumer}, {@code DBTTask}, {@code Map}.
   *
   * <p>Method under test: {@link FailedExportFileCleanerProcessor#processError(DBRProgressMonitor,
   * Throwable, StreamTransferConsumer, DBTTask, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void FailedExportFileCleanerProcessor.processError(DBRProgressMonitor, Throwable, StreamTransferConsumer, DBTTask, Map)"
  })
  public void testProcessErrorWithDBRProgressMonitorThrowableStreamTransferConsumerDBTTaskMap2()
      throws IOException {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
      mockFiles.when(() -> Files.deleteIfExists(Mockito.<Path>any())).thenThrow(new IOException());
      FailedExportFileCleanerProcessor failedExportFileCleanerProcessor =
          new FailedExportFileCleanerProcessor();
      LoggingProgressMonitor monitor = new LoggingProgressMonitor();
      Throwable error = new Throwable();

      StreamConsumerSettings streamConsumerSettings = new StreamConsumerSettings();
      streamConsumerSettings.setUseSingleFile(false);

      ArrayList<Path> pathList = new ArrayList<>();
      pathList.add(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      StreamTransferConsumer consumer = mock(StreamTransferConsumer.class);
      when(consumer.getOutputFiles()).thenReturn(pathList);
      when(consumer.getSettings()).thenReturn(streamConsumerSettings);
      DBTTask task = mock(DBTTask.class);

      // Act
      failedExportFileCleanerProcessor.processError(
          monitor, error, consumer, task, new HashMap<>());

      // Assert
      mockFiles.verify(() -> Files.deleteIfExists(Mockito.<Path>any()));
      verify(consumer).getOutputFiles();
      verify(consumer).getSettings();
    }
  }

  /**
   * Test {@link FailedExportFileCleanerProcessor#processError(DBRProgressMonitor, Throwable,
   * StreamTransferConsumer, DBTTask, Map)} with {@code DBRProgressMonitor}, {@code Throwable},
   * {@code StreamTransferConsumer}, {@code DBTTask}, {@code Map}.
   *
   * <p>Method under test: {@link FailedExportFileCleanerProcessor#processError(DBRProgressMonitor,
   * Throwable, StreamTransferConsumer, DBTTask, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void FailedExportFileCleanerProcessor.processError(DBRProgressMonitor, Throwable, StreamTransferConsumer, DBTTask, Map)"
  })
  public void testProcessErrorWithDBRProgressMonitorThrowableStreamTransferConsumerDBTTaskMap3()
      throws IOException {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
      mockFiles.when(() -> Files.deleteIfExists(Mockito.<Path>any())).thenReturn(true);
      FailedExportFileCleanerProcessor failedExportFileCleanerProcessor =
          new FailedExportFileCleanerProcessor();
      LoggingProgressMonitor monitor = new LoggingProgressMonitor();
      Throwable error = new Throwable();

      StreamConsumerSettings streamConsumerSettings = new StreamConsumerSettings();
      streamConsumerSettings.setUseSingleFile(true);

      StreamTransferConsumer consumer = mock(StreamTransferConsumer.class);
      when(consumer.getSettings()).thenReturn(streamConsumerSettings);

      ArrayList<Path> pathList = new ArrayList<>();
      pathList.add(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
      DBTTask task = mock(DBTTask.class);

      // Act
      failedExportFileCleanerProcessor.processError(
          monitor, error, consumer, task, new HashMap<>());

      // Assert
      verify(consumer).getSettings();
    }
  }
}
