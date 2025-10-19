package org.jkiss.dbeaver.tools.transfer.stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.Log;
import org.jkiss.dbeaver.model.DBIcon;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.runtime.SubTaskProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSAttributeBase;
import org.jkiss.dbeaver.model.struct.DBSDataContainer;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class StreamMappingContainerDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link StreamMappingContainer#StreamMappingContainer(DBSDataContainer)}
   *   <li>{@link StreamMappingContainer#getObjectImage()}
   *   <li>{@link StreamMappingContainer#getSource()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamMappingContainer.<init>(DBSDataContainer)",
    "DBPImage StreamMappingContainer.getObjectImage()",
    "DBSDataContainer StreamMappingContainer.getSource()"
  })
  public void testGettersAndSetters() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping source = new StreamEntityMapping(inputFile);

    // Act
    StreamMappingContainer actualStreamMappingContainer = new StreamMappingContainer(source);
    DBPImage actualObjectImage = actualStreamMappingContainer.getObjectImage();

    // Assert
    assertSame(source, actualStreamMappingContainer.getSource());
    assertSame(((DBIcon) actualObjectImage).TREE_TABLE, actualObjectImage);
  }

  /**
   * Test {@link StreamMappingContainer#StreamMappingContainer(StreamMappingContainer)}.
   *
   * <ul>
   *   <li>Then ObjectImage return {@link DBIcon}.
   * </ul>
   *
   * <p>Method under test: {@link
   * StreamMappingContainer#StreamMappingContainer(StreamMappingContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamMappingContainer.<init>(StreamMappingContainer)"})
  public void testNewStreamMappingContainer_thenObjectImageReturnDBIcon() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping source = new StreamEntityMapping(inputFile);
    StreamMappingContainer other = new StreamMappingContainer(source);

    // Act
    StreamMappingContainer actualStreamMappingContainer = new StreamMappingContainer(other);

    // Assert
    DBPImage objectImage = actualStreamMappingContainer.getObjectImage();
    assertTrue(objectImage instanceof DBIcon);
    assertEquals("test.txt", actualStreamMappingContainer.getName());
    assertEquals("tree/table.svg", objectImage.getLocation());
    assertNull(actualStreamMappingContainer.getMappingType());
    assertFalse(actualStreamMappingContainer.isComplete());
    assertEquals(StreamTransferConsumer.VARIABLE_TABLE, ((DBIcon) objectImage).getToken());
    assertSame(source, actualStreamMappingContainer.getSource());
  }

  /**
   * Test {@link StreamMappingContainer#isComplete()}.
   *
   * <p>Method under test: {@link StreamMappingContainer#isComplete()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StreamMappingContainer.isComplete()"})
  public void testIsComplete() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertFalse(new StreamMappingContainer(new StreamEntityMapping(inputFile)).isComplete());
  }

  /**
   * Test {@link StreamMappingContainer#getName()}.
   *
   * <ul>
   *   <li>Then return {@code test.txt}.
   * </ul>
   *
   * <p>Method under test: {@link StreamMappingContainer#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StreamMappingContainer.getName()"})
  public void testGetName_thenReturnTestTxt() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertEquals(
        "test.txt", new StreamMappingContainer(new StreamEntityMapping(inputFile)).getName());
  }

  /**
   * Test {@link StreamMappingContainer#getAttribute(DBSAttributeBase)}.
   *
   * <p>Method under test: {@link StreamMappingContainer#getAttribute(DBSAttributeBase)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "StreamMappingAttribute StreamMappingContainer.getAttribute(DBSAttributeBase)"
  })
  public void testGetAttribute() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamMappingContainer streamMappingContainer =
        new StreamMappingContainer(new StreamEntityMapping(inputFile));
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamDataImporterColumnInfo sourceAttribute =
        new StreamDataImporterColumnInfo(
            new StreamEntityMapping(inputFile2),
            1,
            "Column Name",
            "Type Name",
            3,
            DBPDataKind.BOOLEAN);

    // Act
    StreamMappingAttribute actualAttribute = streamMappingContainer.getAttribute(sourceAttribute);

    // Assert
    assertNull(actualAttribute);
  }

  /**
   * Test {@link StreamMappingContainer#getAttributes(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link StreamMappingContainer#getAttributes(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.List StreamMappingContainer.getAttributes(DBRProgressMonitor)"})
  public void testGetAttributes() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping source = new StreamEntityMapping(inputFile, "Entity Name", true);
    StreamMappingContainer streamMappingContainer = new StreamMappingContainer(source);

    // Act and Assert
    assertTrue(streamMappingContainer.getAttributes(new LoggingProgressMonitor()).isEmpty());
  }

  /**
   * Test {@link StreamMappingContainer#getAttributes(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link StreamMappingContainer#getAttributes(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.List StreamMappingContainer.getAttributes(DBRProgressMonitor)"})
  public void testGetAttributes_whenJavaLangObject_thenReturnEmpty() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamMappingContainer streamMappingContainer =
        new StreamMappingContainer(new StreamEntityMapping(inputFile));
    Class<Object> forClass = Object.class;

    // Act and Assert
    assertTrue(
        streamMappingContainer
            .getAttributes(new LoggingProgressMonitor(Log.getLog(forClass)))
            .isEmpty());
  }

  /**
   * Test {@link StreamMappingContainer#getAttributes(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>When {@link LoggingProgressMonitor#LoggingProgressMonitor()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link StreamMappingContainer#getAttributes(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.List StreamMappingContainer.getAttributes(DBRProgressMonitor)"})
  public void testGetAttributes_whenLoggingProgressMonitor_thenReturnEmpty() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamMappingContainer streamMappingContainer =
        new StreamMappingContainer(new StreamEntityMapping(inputFile));

    // Act and Assert
    assertTrue(streamMappingContainer.getAttributes(new LoggingProgressMonitor()).isEmpty());
  }

  /**
   * Test {@link StreamMappingContainer#getAttributes(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>When {@link SubTaskProgressMonitor#SubTaskProgressMonitor(DBRProgressMonitor)} with
   *       original is {@link LoggingProgressMonitor#LoggingProgressMonitor()}.
   * </ul>
   *
   * <p>Method under test: {@link StreamMappingContainer#getAttributes(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.List StreamMappingContainer.getAttributes(DBRProgressMonitor)"})
  public void testGetAttributes_whenSubTaskProgressMonitorWithOriginalIsLoggingProgressMonitor() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile);
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamMappingContainer other = new StreamMappingContainer(new StreamEntityMapping(inputFile2));
    StreamMappingContainer streamMappingContainer = new StreamMappingContainer(other);

    // Act and Assert
    assertTrue(
        streamMappingContainer
            .getAttributes(new SubTaskProgressMonitor(new LoggingProgressMonitor()))
            .isEmpty());
  }

  /**
   * Test {@link StreamMappingContainer#getAttributes(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>When {@link SubTaskProgressMonitor#SubTaskProgressMonitor(DBRProgressMonitor)} with
   *       original is {@link LoggingProgressMonitor#LoggingProgressMonitor(Log)}.
   * </ul>
   *
   * <p>Method under test: {@link StreamMappingContainer#getAttributes(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.List StreamMappingContainer.getAttributes(DBRProgressMonitor)"})
  public void testGetAttributes_whenSubTaskProgressMonitorWithOriginalIsLoggingProgressMonitor2() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile);
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamMappingContainer other = new StreamMappingContainer(new StreamEntityMapping(inputFile2));
    StreamMappingContainer streamMappingContainer = new StreamMappingContainer(other);
    Class<Object> forClass = Object.class;
    LoggingProgressMonitor original = new LoggingProgressMonitor(Log.getLog(forClass));

    // Act and Assert
    assertTrue(
        streamMappingContainer.getAttributes(new SubTaskProgressMonitor(original)).isEmpty());
  }

  /**
   * Test {@link StreamMappingContainer#getMappingType()}.
   *
   * <p>Method under test: {@link StreamMappingContainer#getMappingType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.tools.transfer.stream.StreamMappingType StreamMappingContainer.getMappingType()"
  })
  public void testGetMappingType() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertNull(new StreamMappingContainer(new StreamEntityMapping(inputFile)).getMappingType());
  }

  /**
   * Test {@link StreamMappingContainer#saveSettings(Map)}.
   *
   * <p>Method under test: {@link StreamMappingContainer#saveSettings(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamMappingContainer.saveSettings(Map)"})
  public void testSaveSettings() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamMappingContainer streamMappingContainer =
        new StreamMappingContainer(new StreamEntityMapping(inputFile));
    HashMap<String, Object> containerSettings = new HashMap<>();

    // Act
    streamMappingContainer.saveSettings(containerSettings);

    // Assert
    assertEquals(1, containerSettings.size());
    Object getResult = containerSettings.get("attributes");
    assertTrue(getResult instanceof Map);
    assertTrue(((Map<Object, Object>) getResult).isEmpty());
  }
}
