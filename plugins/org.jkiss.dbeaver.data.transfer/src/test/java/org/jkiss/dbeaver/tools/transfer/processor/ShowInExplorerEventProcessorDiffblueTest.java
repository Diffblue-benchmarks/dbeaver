package org.jkiss.dbeaver.tools.transfer.processor;

import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.task.DBTTask;
import org.jkiss.dbeaver.tools.transfer.IDataTransferEventProcessor;
import org.jkiss.dbeaver.tools.transfer.IDataTransferEventProcessor.Event;
import org.jkiss.dbeaver.tools.transfer.stream.StreamConsumerSettings;
import org.jkiss.dbeaver.tools.transfer.stream.StreamTransferConsumer;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ShowInExplorerEventProcessorDiffblueTest {
  /**
   * Test {@link ShowInExplorerEventProcessor#processEvent(DBRProgressMonitor, Event,
   * StreamTransferConsumer, DBTTask, Map)} with {@code DBRProgressMonitor}, {@code Event}, {@code
   * StreamTransferConsumer}, {@code DBTTask}, {@code Map}.
   *
   * <p>Method under test: {@link ShowInExplorerEventProcessor#processEvent(DBRProgressMonitor,
   * IDataTransferEventProcessor.Event, StreamTransferConsumer, DBTTask, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ShowInExplorerEventProcessor.processEvent(DBRProgressMonitor, IDataTransferEventProcessor.Event, StreamTransferConsumer, DBTTask, Map)"
  })
  public void testProcessEventWithDBRProgressMonitorEventStreamTransferConsumerDBTTaskMap()
      throws DBException {
    // Arrange
    ShowInExplorerEventProcessor showInExplorerEventProcessor = new ShowInExplorerEventProcessor();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    StreamConsumerSettings streamConsumerSettings = mock(StreamConsumerSettings.class);
    when(streamConsumerSettings.isOutputClipboard()).thenReturn(true);
    doNothing().when(streamConsumerSettings).setOutputClipboard(anyBoolean());
    doNothing().when(streamConsumerSettings).setUseSingleFile(anyBoolean());
    streamConsumerSettings.setOutputClipboard(false);
    streamConsumerSettings.setUseSingleFile(false);

    StreamTransferConsumer consumer = mock(StreamTransferConsumer.class);
    when(consumer.getSettings()).thenReturn(streamConsumerSettings);
    DBTTask task = mock(DBTTask.class);

    // Act
    showInExplorerEventProcessor.processEvent(
        monitor, Event.START, consumer, task, new HashMap<>());

    // Assert
    verify(streamConsumerSettings).isOutputClipboard();
    verify(streamConsumerSettings).setOutputClipboard(false);
    verify(streamConsumerSettings).setUseSingleFile(false);
    verify(consumer).getSettings();
  }
}
