package org.jkiss.dbeaver.tools.transfer.stream;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class StreamDataImporterSiteDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link StreamDataImporterSite#StreamDataImporterSite(StreamProducerSettings,
   *       StreamEntityMapping, Map)}
   *   <li>{@link StreamDataImporterSite#getProcessorProperties()}
   *   <li>{@link StreamDataImporterSite#getSettings()}
   *   <li>{@link StreamDataImporterSite#getSourceObject()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamDataImporterSite.<init>(StreamProducerSettings, StreamEntityMapping, Map)",
    "Map StreamDataImporterSite.getProcessorProperties()",
    "StreamProducerSettings StreamDataImporterSite.getSettings()",
    "StreamEntityMapping StreamDataImporterSite.getSourceObject()"
  })
  public void testGettersAndSetters() {
    // Arrange
    StreamProducerSettings settings = new StreamProducerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping entityMapping = new StreamEntityMapping(inputFile);
    HashMap<String, Object> processorProperties = new HashMap<>();

    // Act
    StreamDataImporterSite actualStreamDataImporterSite =
        new StreamDataImporterSite(settings, entityMapping, processorProperties);
    Map<String, Object> actualProcessorProperties =
        actualStreamDataImporterSite.getProcessorProperties();
    StreamProducerSettings actualSettings = actualStreamDataImporterSite.getSettings();
    StreamEntityMapping actualSourceObject = actualStreamDataImporterSite.getSourceObject();

    // Assert
    assertTrue(actualProcessorProperties.isEmpty());
    assertSame(processorProperties, actualProcessorProperties);
    assertSame(entityMapping, actualSourceObject);
    assertSame(settings, actualSettings);
  }
}
