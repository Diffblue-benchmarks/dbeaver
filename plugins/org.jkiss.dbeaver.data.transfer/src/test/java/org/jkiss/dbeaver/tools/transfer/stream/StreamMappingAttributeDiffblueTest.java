package org.jkiss.dbeaver.tools.transfer.stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
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
import org.jkiss.dbeaver.model.DBIcon;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.data.DBDAttributeBindingCustom;
import org.jkiss.dbeaver.model.impl.data.AttributeMetaDataProxy;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSAttributeBase;
import org.jkiss.dbeaver.model.struct.DBSDataContainer;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.jkiss.dbeaver.tools.transfer.stream.model.StreamDataSource;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class StreamMappingAttributeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link StreamMappingAttribute#StreamMappingAttribute(StreamMappingContainer,
   *       DBSAttributeBase, StreamMappingType)}
   *   <li>{@link StreamMappingAttribute#setMappingType(StreamMappingType)}
   *   <li>{@link StreamMappingAttribute#getAttribute()}
   *   <li>{@link StreamMappingAttribute#getContainer()}
   *   <li>{@link StreamMappingAttribute#getMappingType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamMappingAttribute.<init>(StreamMappingContainer, DBSAttributeBase, StreamMappingType)",
    "DBSAttributeBase StreamMappingAttribute.getAttribute()",
    "StreamMappingContainer StreamMappingAttribute.getContainer()",
    "StreamMappingType StreamMappingAttribute.getMappingType()",
    "void StreamMappingAttribute.setMappingType(StreamMappingType)"
  })
  public void testGettersAndSetters() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamMappingContainer container =
        new StreamMappingContainer(new StreamEntityMapping(inputFile));
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamDataImporterColumnInfo attribute =
        new StreamDataImporterColumnInfo(
            new StreamEntityMapping(inputFile2),
            1,
            "Column Name",
            "Type Name",
            3,
            DBPDataKind.BOOLEAN);

    // Act
    StreamMappingAttribute actualStreamMappingAttribute =
        new StreamMappingAttribute(container, attribute, StreamMappingType.export);
    actualStreamMappingAttribute.setMappingType(StreamMappingType.export);
    DBSAttributeBase actualAttribute = actualStreamMappingAttribute.getAttribute();
    StreamMappingContainer actualContainer = actualStreamMappingAttribute.getContainer();

    // Assert
    assertEquals(StreamMappingType.export, actualStreamMappingAttribute.getMappingType());
    assertSame(attribute, actualAttribute);
    assertSame(container, actualContainer);
  }

  /**
   * Test {@link StreamMappingAttribute#StreamMappingAttribute(StreamMappingContainer,
   * StreamMappingAttribute)}.
   *
   * <ul>
   *   <li>Then ObjectImage return {@link DBIcon}.
   * </ul>
   *
   * <p>Method under test: {@link
   * StreamMappingAttribute#StreamMappingAttribute(StreamMappingContainer, StreamMappingAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamMappingAttribute.<init>(StreamMappingContainer, StreamMappingAttribute)"
  })
  public void testNewStreamMappingAttribute_thenObjectImageReturnDBIcon() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamMappingContainer container =
        new StreamMappingContainer(new StreamEntityMapping(inputFile));
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamMappingContainer container2 =
        new StreamMappingContainer(new StreamEntityMapping(inputFile2));
    Path inputFile3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamDataImporterColumnInfo attribute =
        new StreamDataImporterColumnInfo(
            new StreamEntityMapping(inputFile3),
            1,
            "Column Name",
            "Type Name",
            3,
            DBPDataKind.BOOLEAN);

    StreamMappingAttribute other =
        new StreamMappingAttribute(container2, attribute, StreamMappingType.export);

    // Act
    StreamMappingAttribute actualStreamMappingAttribute =
        new StreamMappingAttribute(container, other);

    // Assert
    assertTrue(actualStreamMappingAttribute.getObjectImage() instanceof DBIcon);
    DBSAttributeBase attribute2 = actualStreamMappingAttribute.getAttribute();
    assertTrue(attribute2 instanceof StreamDataImporterColumnInfo);
    assertEquals("\"Column Name\"", actualStreamMappingAttribute.getName());
    assertEquals(StreamMappingType.export, actualStreamMappingAttribute.getMappingType());
    assertSame(attribute, attribute2);
    assertSame(container, actualStreamMappingAttribute.getContainer());
  }

  /**
   * Test {@link StreamMappingAttribute#getName()}.
   *
   * <ul>
   *   <li>Given {@link StreamEntityMapping} {@link StreamEntityMapping#getDataSource()} return
   *       {@code null}.
   *   <li>Then return {@code Column Name}.
   * </ul>
   *
   * <p>Method under test: {@link StreamMappingAttribute#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StreamMappingAttribute.getName()"})
  public void testGetName_givenStreamEntityMappingGetDataSourceReturnNull_thenReturnColumnName() {
    // Arrange
    StreamEntityMapping entity = mock(StreamEntityMapping.class);
    when(entity.getDataSource()).thenReturn(null);
    StreamDataImporterColumnInfo attribute =
        new StreamDataImporterColumnInfo(
            entity, 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);
    StreamMappingContainer container = new StreamMappingContainer(mock(DBSDataContainer.class));

    StreamMappingAttribute other =
        new StreamMappingAttribute(container, attribute, StreamMappingType.export);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamMappingContainer container2 =
        new StreamMappingContainer(new StreamEntityMapping(inputFile));

    StreamMappingAttribute streamMappingAttribute = new StreamMappingAttribute(container2, other);

    // Act
    String actualName = streamMappingAttribute.getName();

    // Assert
    verify(entity).getDataSource();
    assertEquals("Column Name", actualName);
  }

  /**
   * Test {@link StreamMappingAttribute#getName()}.
   *
   * <ul>
   *   <li>Then return {@code "Column Name"}.
   * </ul>
   *
   * <p>Method under test: {@link StreamMappingAttribute#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StreamMappingAttribute.getName()"})
  public void testGetName_thenReturnColumnName() {
    // Arrange
    StreamEntityMapping entity = mock(StreamEntityMapping.class);
    when(entity.getDataSource()).thenReturn(new StreamDataSource("Input Name"));
    StreamDataImporterColumnInfo attribute =
        new StreamDataImporterColumnInfo(
            entity, 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);
    StreamMappingContainer container = new StreamMappingContainer(mock(DBSDataContainer.class));

    StreamMappingAttribute other =
        new StreamMappingAttribute(container, attribute, StreamMappingType.export);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamMappingContainer container2 =
        new StreamMappingContainer(new StreamEntityMapping(inputFile));

    StreamMappingAttribute streamMappingAttribute = new StreamMappingAttribute(container2, other);

    // Act
    String actualName = streamMappingAttribute.getName();

    // Assert
    verify(entity, atLeast(1)).getDataSource();
    assertEquals("\"Column Name\"", actualName);
  }

  /**
   * Test {@link StreamMappingAttribute#getObjectImage()}.
   *
   * <p>Method under test: {@link StreamMappingAttribute#getObjectImage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage StreamMappingAttribute.getObjectImage()"})
  public void testGetObjectImage() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamMappingContainer container =
        new StreamMappingContainer(new StreamEntityMapping(inputFile));
    StreamMappingContainer container2 = new StreamMappingContainer(mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo attribute =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    StreamMappingAttribute other =
        new StreamMappingAttribute(container2, attribute, StreamMappingType.export);

    StreamMappingAttribute streamMappingAttribute = new StreamMappingAttribute(container, other);

    // Act
    DBPImage actualObjectImage = streamMappingAttribute.getObjectImage();
    String actualLocation = actualObjectImage.getLocation();

    // Assert
    assertTrue(actualObjectImage instanceof DBIcon);
    DBSAttributeBase attribute2 = streamMappingAttribute.getAttribute();
    assertTrue(attribute2 instanceof StreamDataImporterColumnInfo);
    assertEquals("Column Name", streamMappingAttribute.getName());
    assertEquals("boolean", ((DBIcon) actualObjectImage).getToken());
    assertEquals("types/boolean.svg", actualObjectImage.getLocation());
    assertEquals("types/boolean.svg", actualLocation);
    assertEquals(StreamMappingType.export, streamMappingAttribute.getMappingType());
    assertSame(attribute, attribute2);
    assertSame(container, streamMappingAttribute.getContainer());
  }

  /**
   * Test {@link StreamMappingAttribute#getObjectImage()}.
   *
   * <p>Method under test: {@link StreamMappingAttribute#getObjectImage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage StreamMappingAttribute.getObjectImage()"})
  public void testGetObjectImage2() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamMappingContainer container =
        new StreamMappingContainer(new StreamEntityMapping(inputFile));
    StreamMappingContainer container2 = new StreamMappingContainer(mock(DBSDataContainer.class));
    DBDAttributeBindingCustom parent = mock(DBDAttributeBindingCustom.class);
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping dataContainer = new StreamEntityMapping(inputFile2);
    StreamDataSource dataSource = new StreamDataSource("Input Name");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute vAttribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    DBDAttributeBindingCustom attribute =
        new DBDAttributeBindingCustom(parent, dataContainer, dataSource, vAttribute, 1);

    StreamMappingAttribute other =
        new StreamMappingAttribute(container2, attribute, StreamMappingType.export);

    StreamMappingAttribute streamMappingAttribute = new StreamMappingAttribute(container, other);

    // Act
    DBPImage actualObjectImage = streamMappingAttribute.getObjectImage();
    String actualLocation = actualObjectImage.getLocation();

    // Assert
    assertTrue(actualObjectImage instanceof DBIcon);
    DBSAttributeBase attribute2 = streamMappingAttribute.getAttribute();
    assertTrue(attribute2 instanceof DBDAttributeBindingCustom);
    assertEquals("types/unknown.svg", actualObjectImage.getLocation());
    assertEquals("types/unknown.svg", actualLocation);
    assertEquals("unknown", ((DBIcon) actualObjectImage).getToken());
    assertEquals(StreamMappingType.export, streamMappingAttribute.getMappingType());
    assertSame(attribute, attribute2);
    assertSame(container, streamMappingAttribute.getContainer());
  }

  /**
   * Test {@link StreamMappingAttribute#getObjectImage()}.
   *
   * <p>Method under test: {@link StreamMappingAttribute#getObjectImage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage StreamMappingAttribute.getObjectImage()"})
  public void testGetObjectImage3() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamMappingContainer container =
        new StreamMappingContainer(new StreamEntityMapping(inputFile));
    StreamMappingContainer container2 = new StreamMappingContainer(mock(DBSDataContainer.class));
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamDataImporterColumnInfo attribute =
        new StreamDataImporterColumnInfo(
            new StreamEntityMapping(inputFile2),
            1,
            "Column Name",
            "Type Name",
            3,
            DBPDataKind.BOOLEAN);
    AttributeMetaDataProxy attribute2 = new AttributeMetaDataProxy(attribute);

    StreamMappingAttribute other =
        new StreamMappingAttribute(container2, attribute2, StreamMappingType.export);

    StreamMappingAttribute streamMappingAttribute = new StreamMappingAttribute(container, other);

    // Act
    DBPImage actualObjectImage = streamMappingAttribute.getObjectImage();
    String actualLocation = actualObjectImage.getLocation();

    // Assert
    assertTrue(actualObjectImage instanceof DBIcon);
    DBSAttributeBase attribute3 = streamMappingAttribute.getAttribute();
    assertTrue(attribute3 instanceof AttributeMetaDataProxy);
    assertEquals("Column Name", streamMappingAttribute.getName());
    assertEquals("boolean", ((DBIcon) actualObjectImage).getToken());
    assertEquals("types/boolean.svg", actualObjectImage.getLocation());
    assertEquals("types/boolean.svg", actualLocation);
    assertEquals(StreamMappingType.export, streamMappingAttribute.getMappingType());
    assertSame(attribute2, attribute3);
    assertSame(container, streamMappingAttribute.getContainer());
  }

  /**
   * Test {@link StreamMappingAttribute#getObjectImage()}.
   *
   * <p>Method under test: {@link StreamMappingAttribute#getObjectImage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage StreamMappingAttribute.getObjectImage()"})
  public void testGetObjectImage4() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamMappingContainer container =
        new StreamMappingContainer(new StreamEntityMapping(inputFile));
    StreamMappingContainer container2 = new StreamMappingContainer(mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo attribute =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BINARY);

    StreamMappingAttribute other =
        new StreamMappingAttribute(container2, attribute, StreamMappingType.export);

    StreamMappingAttribute streamMappingAttribute = new StreamMappingAttribute(container, other);

    // Act
    DBPImage actualObjectImage = streamMappingAttribute.getObjectImage();

    // Assert
    DBSAttributeBase attribute2 = streamMappingAttribute.getAttribute();
    assertTrue(attribute2 instanceof StreamDataImporterColumnInfo);
    assertEquals("Column Name", streamMappingAttribute.getName());
    assertEquals(StreamMappingType.export, streamMappingAttribute.getMappingType());
    assertSame(attribute, attribute2);
    assertSame(container, streamMappingAttribute.getContainer());
    assertSame(((DBIcon) actualObjectImage).TYPE_BINARY, actualObjectImage);
  }

  /**
   * Test {@link StreamMappingAttribute#getObjectImage()}.
   *
   * <p>Method under test: {@link StreamMappingAttribute#getObjectImage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage StreamMappingAttribute.getObjectImage()"})
  public void testGetObjectImage5() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamMappingContainer container =
        new StreamMappingContainer(new StreamEntityMapping(inputFile));
    StreamMappingContainer container2 = new StreamMappingContainer(mock(DBSDataContainer.class));
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    StreamMappingAttribute other =
        new StreamMappingAttribute(container2, attribute, StreamMappingType.export);

    StreamMappingAttribute streamMappingAttribute = new StreamMappingAttribute(container, other);

    // Act
    DBPImage actualObjectImage = streamMappingAttribute.getObjectImage();

    // Assert
    DBSAttributeBase attribute2 = streamMappingAttribute.getAttribute();
    assertTrue(attribute2 instanceof DBVEntityAttribute);
    assertEquals("Name", streamMappingAttribute.getName());
    assertEquals(StreamMappingType.export, streamMappingAttribute.getMappingType());
    assertSame(attribute, attribute2);
    assertSame(container, streamMappingAttribute.getContainer());
    assertSame(((DBIcon) actualObjectImage).TYPE_UNKNOWN, actualObjectImage);
  }

  /**
   * Test {@link StreamMappingAttribute#getObjectImage()}.
   *
   * <ul>
   *   <li>Then return Token is {@code datetime}.
   * </ul>
   *
   * <p>Method under test: {@link StreamMappingAttribute#getObjectImage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage StreamMappingAttribute.getObjectImage()"})
  public void testGetObjectImage_thenReturnTokenIsDatetime() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamMappingContainer container =
        new StreamMappingContainer(new StreamEntityMapping(inputFile));
    StreamMappingContainer container2 = new StreamMappingContainer(mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo attribute =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class),
            1,
            "Column Name",
            "Type Name",
            3,
            DBPDataKind.DATETIME);

    StreamMappingAttribute other =
        new StreamMappingAttribute(container2, attribute, StreamMappingType.export);

    StreamMappingAttribute streamMappingAttribute = new StreamMappingAttribute(container, other);

    // Act
    DBPImage actualObjectImage = streamMappingAttribute.getObjectImage();
    String actualLocation = actualObjectImage.getLocation();

    // Assert
    assertTrue(actualObjectImage instanceof DBIcon);
    DBSAttributeBase attribute2 = streamMappingAttribute.getAttribute();
    assertTrue(attribute2 instanceof StreamDataImporterColumnInfo);
    assertEquals("Column Name", streamMappingAttribute.getName());
    assertEquals("datetime", ((DBIcon) actualObjectImage).getToken());
    assertEquals("types/datetime.svg", actualObjectImage.getLocation());
    assertEquals("types/datetime.svg", actualLocation);
    assertEquals(StreamMappingType.export, streamMappingAttribute.getMappingType());
    assertSame(attribute, attribute2);
    assertSame(container, streamMappingAttribute.getContainer());
  }

  /**
   * Test {@link StreamMappingAttribute#getObjectImage()}.
   *
   * <ul>
   *   <li>Then return Token is {@code number}.
   * </ul>
   *
   * <p>Method under test: {@link StreamMappingAttribute#getObjectImage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage StreamMappingAttribute.getObjectImage()"})
  public void testGetObjectImage_thenReturnTokenIsNumber() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamMappingContainer container =
        new StreamMappingContainer(new StreamEntityMapping(inputFile));
    StreamMappingContainer container2 = new StreamMappingContainer(mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo attribute =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.NUMERIC);

    StreamMappingAttribute other =
        new StreamMappingAttribute(container2, attribute, StreamMappingType.export);

    StreamMappingAttribute streamMappingAttribute = new StreamMappingAttribute(container, other);

    // Act
    DBPImage actualObjectImage = streamMappingAttribute.getObjectImage();
    String actualLocation = actualObjectImage.getLocation();

    // Assert
    assertTrue(actualObjectImage instanceof DBIcon);
    DBSAttributeBase attribute2 = streamMappingAttribute.getAttribute();
    assertTrue(attribute2 instanceof StreamDataImporterColumnInfo);
    assertEquals("Column Name", streamMappingAttribute.getName());
    assertEquals("number", ((DBIcon) actualObjectImage).getToken());
    assertEquals("types/number.svg", actualObjectImage.getLocation());
    assertEquals("types/number.svg", actualLocation);
    assertEquals(StreamMappingType.export, streamMappingAttribute.getMappingType());
    assertSame(attribute, attribute2);
    assertSame(container, streamMappingAttribute.getContainer());
  }

  /**
   * Test {@link StreamMappingAttribute#getObjectImage()}.
   *
   * <ul>
   *   <li>Then return Token is {@code object}.
   * </ul>
   *
   * <p>Method under test: {@link StreamMappingAttribute#getObjectImage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage StreamMappingAttribute.getObjectImage()"})
  public void testGetObjectImage_thenReturnTokenIsObject() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamMappingContainer container =
        new StreamMappingContainer(new StreamEntityMapping(inputFile));
    StreamMappingContainer container2 = new StreamMappingContainer(mock(DBSDataContainer.class));
    StreamMappingAttribute other =
        new StreamMappingAttribute(container2, null, StreamMappingType.export);

    StreamMappingAttribute streamMappingAttribute = new StreamMappingAttribute(container, other);

    // Act
    DBPImage actualObjectImage = streamMappingAttribute.getObjectImage();
    String actualLocation = actualObjectImage.getLocation();

    // Assert
    assertTrue(actualObjectImage instanceof DBIcon);
    assertEquals("object", ((DBIcon) actualObjectImage).getToken());
    assertEquals("types/object.svg", actualObjectImage.getLocation());
    assertEquals("types/object.svg", actualLocation);
    assertNull(streamMappingAttribute.getAttribute());
    assertEquals(StreamMappingType.export, streamMappingAttribute.getMappingType());
    assertSame(container, streamMappingAttribute.getContainer());
  }

  /**
   * Test {@link StreamMappingAttribute#getObjectImage()}.
   *
   * <ul>
   *   <li>Then return Token is {@code string}.
   * </ul>
   *
   * <p>Method under test: {@link StreamMappingAttribute#getObjectImage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage StreamMappingAttribute.getObjectImage()"})
  public void testGetObjectImage_thenReturnTokenIsString() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamMappingContainer container =
        new StreamMappingContainer(new StreamEntityMapping(inputFile));
    StreamMappingContainer container2 = new StreamMappingContainer(mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo attribute =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.STRING);

    StreamMappingAttribute other =
        new StreamMappingAttribute(container2, attribute, StreamMappingType.export);

    StreamMappingAttribute streamMappingAttribute = new StreamMappingAttribute(container, other);

    // Act
    DBPImage actualObjectImage = streamMappingAttribute.getObjectImage();
    String actualLocation = actualObjectImage.getLocation();

    // Assert
    assertTrue(actualObjectImage instanceof DBIcon);
    DBSAttributeBase attribute2 = streamMappingAttribute.getAttribute();
    assertTrue(attribute2 instanceof StreamDataImporterColumnInfo);
    assertEquals("Column Name", streamMappingAttribute.getName());
    assertEquals("string", ((DBIcon) actualObjectImage).getToken());
    assertEquals("types/string.svg", actualObjectImage.getLocation());
    assertEquals("types/string.svg", actualLocation);
    assertEquals(StreamMappingType.export, streamMappingAttribute.getMappingType());
    assertSame(attribute, attribute2);
    assertSame(container, streamMappingAttribute.getContainer());
  }

  /**
   * Test {@link StreamMappingAttribute#loadSettings(DBRProgressMonitor, Map)}.
   *
   * <p>Method under test: {@link StreamMappingAttribute#loadSettings(DBRProgressMonitor, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamMappingAttribute.loadSettings(DBRProgressMonitor, Map)"})
  public void testLoadSettings() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamMappingContainer container =
        new StreamMappingContainer(new StreamEntityMapping(inputFile));
    StreamMappingContainer container2 = new StreamMappingContainer(mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo attribute =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    StreamMappingAttribute other =
        new StreamMappingAttribute(container2, attribute, StreamMappingType.export);

    StreamMappingAttribute streamMappingAttribute = new StreamMappingAttribute(container, other);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    HashMap<String, Object> attributeSettings = new HashMap<>();
    attributeSettings.put("mappingType", "Attribute Settings");

    // Act
    streamMappingAttribute.loadSettings(monitor, attributeSettings);

    // Assert
    assertEquals(StreamMappingType.unspecified, streamMappingAttribute.getMappingType());
  }

  /**
   * Test {@link StreamMappingAttribute#loadSettings(DBRProgressMonitor, Map)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>When {@link HashMap#HashMap()} {@code mappingType} is empty string.
   * </ul>
   *
   * <p>Method under test: {@link StreamMappingAttribute#loadSettings(DBRProgressMonitor, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamMappingAttribute.loadSettings(DBRProgressMonitor, Map)"})
  public void testLoadSettings_givenEmptyString_whenHashMapMappingTypeIsEmptyString() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamMappingContainer container =
        new StreamMappingContainer(new StreamEntityMapping(inputFile));
    StreamMappingContainer container2 = new StreamMappingContainer(mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo attribute =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    StreamMappingAttribute other =
        new StreamMappingAttribute(container2, attribute, StreamMappingType.export);

    StreamMappingAttribute streamMappingAttribute = new StreamMappingAttribute(container, other);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    HashMap<String, Object> attributeSettings = new HashMap<>();
    attributeSettings.put("mappingType", "");

    // Act
    streamMappingAttribute.loadSettings(monitor, attributeSettings);

    // Assert that nothing has changed
    assertEquals(StreamMappingType.export, streamMappingAttribute.getMappingType());
  }

  /**
   * Test {@link StreamMappingAttribute#loadSettings(DBRProgressMonitor, Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link StreamMappingAttribute#loadSettings(DBRProgressMonitor, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamMappingAttribute.loadSettings(DBRProgressMonitor, Map)"})
  public void testLoadSettings_whenHashMap() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamMappingContainer container =
        new StreamMappingContainer(new StreamEntityMapping(inputFile));
    StreamMappingContainer container2 = new StreamMappingContainer(mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo attribute =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    StreamMappingAttribute other =
        new StreamMappingAttribute(container2, attribute, StreamMappingType.export);

    StreamMappingAttribute streamMappingAttribute = new StreamMappingAttribute(container, other);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    streamMappingAttribute.loadSettings(monitor, new HashMap<>());

    // Assert that nothing has changed
    assertEquals(StreamMappingType.export, streamMappingAttribute.getMappingType());
  }

  /**
   * Test {@link StreamMappingAttribute#saveSettings(Map)}.
   *
   * <p>Method under test: {@link StreamMappingAttribute#saveSettings(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamMappingAttribute.saveSettings(Map)"})
  public void testSaveSettings() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamMappingContainer container =
        new StreamMappingContainer(new StreamEntityMapping(inputFile));
    StreamMappingContainer container2 = new StreamMappingContainer(mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo attribute =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    StreamMappingAttribute other =
        new StreamMappingAttribute(container2, attribute, StreamMappingType.export);

    StreamMappingAttribute streamMappingAttribute = new StreamMappingAttribute(container, other);
    HashMap<String, Object> attributeSettings = new HashMap<>();

    // Act
    streamMappingAttribute.saveSettings(attributeSettings);

    // Assert
    assertEquals(1, attributeSettings.size());
    assertEquals("export", attributeSettings.get("mappingType"));
  }
}
