package org.jkiss.dbeaver.tools.transfer.database;

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
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBIcon;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.data.DBDAttributeBindingCustom;
import org.jkiss.dbeaver.model.impl.data.AttributeMetaDataProxy;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSAttributeBase;
import org.jkiss.dbeaver.model.struct.DBSDataContainer;
import org.jkiss.dbeaver.model.struct.DBSEntityAttribute;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.jkiss.dbeaver.tools.transfer.stream.StreamDataImporterColumnInfo;
import org.jkiss.dbeaver.tools.transfer.stream.StreamEntityMapping;
import org.jkiss.dbeaver.tools.transfer.stream.model.StreamDataSource;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DatabaseMappingAttributeDiffblueTest {
  /**
   * Test {@link DatabaseMappingAttribute#DatabaseMappingAttribute(DatabaseMappingContainer,
   * DBSAttributeBase)}.
   *
   * <p>Method under test: {@link
   * DatabaseMappingAttribute#DatabaseMappingAttribute(DatabaseMappingContainer, DBSAttributeBase)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseMappingAttribute.<init>(DatabaseMappingContainer, DBSAttributeBase)"
  })
  public void testNewDatabaseMappingAttribute() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            new StreamEntityMapping(inputFile2),
            1,
            "Column Name",
            "Type Name",
            3,
            DBPDataKind.BOOLEAN);

    // Act
    DatabaseMappingAttribute actualDatabaseMappingAttribute =
        new DatabaseMappingAttribute(parent, source);

    // Assert
    assertTrue(actualDatabaseMappingAttribute.getIcon() instanceof DBIcon);
    DBSAttributeBase source2 = actualDatabaseMappingAttribute.getSource();
    assertTrue(source2 instanceof StreamDataImporterColumnInfo);
    assertEquals("?", actualDatabaseMappingAttribute.getTargetName());
    assertEquals("Type Name", actualDatabaseMappingAttribute.getSourceType());
    assertNull(actualDatabaseMappingAttribute.getTarget());
    assertNull(actualDatabaseMappingAttribute.getTransformer());
    assertEquals(DatabaseMappingType.unspecified, actualDatabaseMappingAttribute.getMappingType());
    assertTrue(actualDatabaseMappingAttribute.getTransformerProperties().isEmpty());
    assertSame(parent, actualDatabaseMappingAttribute.getParent());
    assertSame(source, source2);
  }

  /**
   * Test {@link DatabaseMappingAttribute#DatabaseMappingAttribute(DatabaseMappingAttribute,
   * DatabaseMappingContainer)}.
   *
   * <ul>
   *   <li>Then Icon return {@link DBIcon}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DatabaseMappingAttribute#DatabaseMappingAttribute(DatabaseMappingAttribute,
   * DatabaseMappingContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseMappingAttribute.<init>(DatabaseMappingAttribute, DatabaseMappingContainer)"
  })
  public void testNewDatabaseMappingAttribute_thenIconReturnDBIcon() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            new StreamEntityMapping(inputFile2),
            1,
            "Column Name",
            "Type Name",
            3,
            DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings2 = new DatabaseConsumerSettings();
    Path inputFile3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings2, new StreamEntityMapping(inputFile3));

    // Act
    DatabaseMappingAttribute actualDatabaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);

    // Assert
    assertTrue(actualDatabaseMappingAttribute.getIcon() instanceof DBIcon);
    DBSAttributeBase source2 = actualDatabaseMappingAttribute.getSource();
    assertTrue(source2 instanceof StreamDataImporterColumnInfo);
    assertEquals("?", actualDatabaseMappingAttribute.getTargetName());
    assertEquals("Type Name", actualDatabaseMappingAttribute.getSourceType());
    assertNull(actualDatabaseMappingAttribute.getTarget());
    assertNull(actualDatabaseMappingAttribute.getTransformer());
    assertEquals(DatabaseMappingType.unspecified, actualDatabaseMappingAttribute.getMappingType());
    assertTrue(actualDatabaseMappingAttribute.getTransformerProperties().isEmpty());
    assertSame(parent2, actualDatabaseMappingAttribute.getParent());
    assertSame(source, source2);
  }

  /**
   * Test {@link DatabaseMappingAttribute#getIcon()}.
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#getIcon()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DatabaseMappingAttribute.getIcon()"})
  public void testGetIcon() {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);

    // Act
    DBPImage actualIcon = databaseMappingAttribute.getIcon();
    String actualLocation = actualIcon.getLocation();

    // Assert
    assertTrue(actualIcon instanceof DBIcon);
    DBSAttributeBase source2 = databaseMappingAttribute.getSource();
    assertTrue(source2 instanceof StreamDataImporterColumnInfo);
    assertEquals("?", databaseMappingAttribute.getTargetName());
    assertEquals("Type Name", databaseMappingAttribute.getSourceType());
    assertEquals("boolean", ((DBIcon) actualIcon).getToken());
    assertEquals("types/boolean.svg", actualIcon.getLocation());
    assertEquals("types/boolean.svg", actualLocation);
    assertNull(databaseMappingAttribute.getTarget());
    assertNull(databaseMappingAttribute.getTransformer());
    assertEquals(DatabaseMappingType.unspecified, databaseMappingAttribute.getMappingType());
    assertTrue(databaseMappingAttribute.getTransformerProperties().isEmpty());
    assertSame(parent2, databaseMappingAttribute.getParent());
    assertSame(source, source2);
  }

  /**
   * Test {@link DatabaseMappingAttribute#getIcon()}.
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#getIcon()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DatabaseMappingAttribute.getIcon()"})
  public void testGetIcon2() {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    DBDAttributeBindingCustom parent2 = mock(DBDAttributeBindingCustom.class);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping dataContainer = new StreamEntityMapping(inputFile);
    StreamDataSource dataSource = new StreamDataSource("Input Name");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute vAttribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    DBDAttributeBindingCustom source =
        new DBDAttributeBindingCustom(parent2, dataContainer, dataSource, vAttribute, 1);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent3 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile2));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent3);

    // Act
    DBPImage actualIcon = databaseMappingAttribute.getIcon();
    String actualLocation = actualIcon.getLocation();

    // Assert
    assertTrue(actualIcon instanceof DBIcon);
    DBSAttributeBase source2 = databaseMappingAttribute.getSource();
    assertTrue(source2 instanceof DBDAttributeBindingCustom);
    assertEquals("?", databaseMappingAttribute.getTargetName());
    assertEquals("types/unknown.svg", actualIcon.getLocation());
    assertEquals("types/unknown.svg", actualLocation);
    assertEquals("unknown", ((DBIcon) actualIcon).getToken());
    assertNull(databaseMappingAttribute.getSourceType());
    assertNull(databaseMappingAttribute.getTarget());
    assertNull(databaseMappingAttribute.getTransformer());
    assertEquals(DatabaseMappingType.unspecified, databaseMappingAttribute.getMappingType());
    assertTrue(databaseMappingAttribute.getTransformerProperties().isEmpty());
    assertSame(source, source2);
    assertSame(parent3, databaseMappingAttribute.getParent());
  }

  /**
   * Test {@link DatabaseMappingAttribute#getIcon()}.
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#getIcon()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DatabaseMappingAttribute.getIcon()"})
  public void testGetIcon3() {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamDataImporterColumnInfo attribute =
        new StreamDataImporterColumnInfo(
            new StreamEntityMapping(inputFile),
            1,
            "Column Name",
            "Type Name",
            3,
            DBPDataKind.BOOLEAN);
    AttributeMetaDataProxy source = new AttributeMetaDataProxy(attribute);

    DatabaseMappingAttribute attribute2 = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile2));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute2, parent2);

    // Act
    DBPImage actualIcon = databaseMappingAttribute.getIcon();
    String actualLocation = actualIcon.getLocation();

    // Assert
    assertTrue(actualIcon instanceof DBIcon);
    DBSAttributeBase source2 = databaseMappingAttribute.getSource();
    assertTrue(source2 instanceof AttributeMetaDataProxy);
    assertEquals("?", databaseMappingAttribute.getTargetName());
    assertEquals("Type Name", databaseMappingAttribute.getSourceType());
    assertEquals("boolean", ((DBIcon) actualIcon).getToken());
    assertEquals("types/boolean.svg", actualIcon.getLocation());
    assertEquals("types/boolean.svg", actualLocation);
    assertNull(databaseMappingAttribute.getTarget());
    assertNull(databaseMappingAttribute.getTransformer());
    assertEquals(DatabaseMappingType.unspecified, databaseMappingAttribute.getMappingType());
    assertTrue(databaseMappingAttribute.getTransformerProperties().isEmpty());
    assertSame(source, source2);
    assertSame(parent2, databaseMappingAttribute.getParent());
  }

  /**
   * Test {@link DatabaseMappingAttribute#getIcon()}.
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#getIcon()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DatabaseMappingAttribute.getIcon()"})
  public void testGetIcon4() {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BINARY);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);

    // Act
    DBPImage actualIcon = databaseMappingAttribute.getIcon();

    // Assert
    DBSAttributeBase source2 = databaseMappingAttribute.getSource();
    assertTrue(source2 instanceof StreamDataImporterColumnInfo);
    assertEquals("?", databaseMappingAttribute.getTargetName());
    assertEquals("Type Name(3)", databaseMappingAttribute.getSourceType());
    assertNull(databaseMappingAttribute.getTarget());
    assertNull(databaseMappingAttribute.getTransformer());
    assertEquals(DatabaseMappingType.unspecified, databaseMappingAttribute.getMappingType());
    assertTrue(databaseMappingAttribute.getTransformerProperties().isEmpty());
    assertSame(parent2, databaseMappingAttribute.getParent());
    assertSame(source, source2);
    assertSame(((DBIcon) actualIcon).TYPE_BINARY, actualIcon);
  }

  /**
   * Test {@link DatabaseMappingAttribute#getIcon()}.
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#getIcon()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DatabaseMappingAttribute.getIcon()"})
  public void testGetIcon5() {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute source =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);

    // Act
    DBPImage actualIcon = databaseMappingAttribute.getIcon();

    // Assert
    DBSAttributeBase source2 = databaseMappingAttribute.getSource();
    assertTrue(source2 instanceof DBVEntityAttribute);
    assertEquals("?", databaseMappingAttribute.getTargetName());
    assertNull(databaseMappingAttribute.getSourceType());
    assertNull(databaseMappingAttribute.getTarget());
    assertNull(databaseMappingAttribute.getTransformer());
    assertEquals(DatabaseMappingType.unspecified, databaseMappingAttribute.getMappingType());
    assertTrue(databaseMappingAttribute.getTransformerProperties().isEmpty());
    assertSame(source, source2);
    assertSame(parent2, databaseMappingAttribute.getParent());
    assertSame(((DBIcon) actualIcon).TYPE_UNKNOWN, actualIcon);
  }

  /**
   * Test {@link DatabaseMappingAttribute#getIcon()}.
   *
   * <ul>
   *   <li>Then return Token is {@code datetime}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#getIcon()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DatabaseMappingAttribute.getIcon()"})
  public void testGetIcon_thenReturnTokenIsDatetime() {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class),
            1,
            "Column Name",
            "Type Name",
            3,
            DBPDataKind.DATETIME);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);

    // Act
    DBPImage actualIcon = databaseMappingAttribute.getIcon();
    String actualLocation = actualIcon.getLocation();

    // Assert
    assertTrue(actualIcon instanceof DBIcon);
    DBSAttributeBase source2 = databaseMappingAttribute.getSource();
    assertTrue(source2 instanceof StreamDataImporterColumnInfo);
    assertEquals("?", databaseMappingAttribute.getTargetName());
    assertEquals("Type Name", databaseMappingAttribute.getSourceType());
    assertEquals("datetime", ((DBIcon) actualIcon).getToken());
    assertEquals("types/datetime.svg", actualIcon.getLocation());
    assertEquals("types/datetime.svg", actualLocation);
    assertNull(databaseMappingAttribute.getTarget());
    assertNull(databaseMappingAttribute.getTransformer());
    assertEquals(DatabaseMappingType.unspecified, databaseMappingAttribute.getMappingType());
    assertTrue(databaseMappingAttribute.getTransformerProperties().isEmpty());
    assertSame(parent2, databaseMappingAttribute.getParent());
    assertSame(source, source2);
  }

  /**
   * Test {@link DatabaseMappingAttribute#getIcon()}.
   *
   * <ul>
   *   <li>Then return Token is {@code number}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#getIcon()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DatabaseMappingAttribute.getIcon()"})
  public void testGetIcon_thenReturnTokenIsNumber() {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.NUMERIC);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);

    // Act
    DBPImage actualIcon = databaseMappingAttribute.getIcon();
    String actualLocation = actualIcon.getLocation();

    // Assert
    assertTrue(actualIcon instanceof DBIcon);
    DBSAttributeBase source2 = databaseMappingAttribute.getSource();
    assertTrue(source2 instanceof StreamDataImporterColumnInfo);
    assertEquals("?", databaseMappingAttribute.getTargetName());
    assertEquals("Type Name", databaseMappingAttribute.getSourceType());
    assertEquals("number", ((DBIcon) actualIcon).getToken());
    assertEquals("types/number.svg", actualIcon.getLocation());
    assertEquals("types/number.svg", actualLocation);
    assertNull(databaseMappingAttribute.getTarget());
    assertNull(databaseMappingAttribute.getTransformer());
    assertEquals(DatabaseMappingType.unspecified, databaseMappingAttribute.getMappingType());
    assertTrue(databaseMappingAttribute.getTransformerProperties().isEmpty());
    assertSame(parent2, databaseMappingAttribute.getParent());
    assertSame(source, source2);
  }

  /**
   * Test {@link DatabaseMappingAttribute#getIcon()}.
   *
   * <ul>
   *   <li>Then return Token is {@code object}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#getIcon()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DatabaseMappingAttribute.getIcon()"})
  public void testGetIcon_thenReturnTokenIsObject() {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, null);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);

    // Act
    DBPImage actualIcon = databaseMappingAttribute.getIcon();
    String actualLocation = actualIcon.getLocation();

    // Assert
    assertTrue(actualIcon instanceof DBIcon);
    assertEquals("?", databaseMappingAttribute.getTargetName());
    assertEquals("object", ((DBIcon) actualIcon).getToken());
    assertEquals("types/object.svg", actualIcon.getLocation());
    assertEquals("types/object.svg", actualLocation);
    assertNull(databaseMappingAttribute.getSourceType());
    assertNull(databaseMappingAttribute.getSource());
    assertNull(databaseMappingAttribute.getTarget());
    assertNull(databaseMappingAttribute.getTransformer());
    assertEquals(DatabaseMappingType.unspecified, databaseMappingAttribute.getMappingType());
    assertTrue(databaseMappingAttribute.getTransformerProperties().isEmpty());
    assertSame(parent2, databaseMappingAttribute.getParent());
  }

  /**
   * Test {@link DatabaseMappingAttribute#getIcon()}.
   *
   * <ul>
   *   <li>Then return Token is {@code string}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#getIcon()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DatabaseMappingAttribute.getIcon()"})
  public void testGetIcon_thenReturnTokenIsString() {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.STRING);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);

    // Act
    DBPImage actualIcon = databaseMappingAttribute.getIcon();
    String actualLocation = actualIcon.getLocation();

    // Assert
    assertTrue(actualIcon instanceof DBIcon);
    DBSAttributeBase source2 = databaseMappingAttribute.getSource();
    assertTrue(source2 instanceof StreamDataImporterColumnInfo);
    assertEquals("?", databaseMappingAttribute.getTargetName());
    assertEquals("Type Name(3)", databaseMappingAttribute.getSourceType());
    assertEquals("string", ((DBIcon) actualIcon).getToken());
    assertEquals("types/string.svg", actualIcon.getLocation());
    assertEquals("types/string.svg", actualLocation);
    assertNull(databaseMappingAttribute.getTarget());
    assertNull(databaseMappingAttribute.getTransformer());
    assertEquals(DatabaseMappingType.unspecified, databaseMappingAttribute.getMappingType());
    assertTrue(databaseMappingAttribute.getTransformerProperties().isEmpty());
    assertSame(parent2, databaseMappingAttribute.getParent());
    assertSame(source, source2);
  }

  /**
   * Test {@link DatabaseMappingAttribute#getSource()}.
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#getSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSAttributeBase DatabaseMappingAttribute.getSource()"})
  public void testGetSource() {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);

    // Act and Assert
    assertSame(source, databaseMappingAttribute.getSource());
  }

  /**
   * Test {@link DatabaseMappingAttribute#getSourceType()}.
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#getSourceType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseMappingAttribute.getSourceType()"})
  public void testGetSourceType() {
    // Arrange
    StreamEntityMapping entity = mock(StreamEntityMapping.class);
    when(entity.getDataSource()).thenReturn(new StreamDataSource("Input Name"));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(entity, 1, "Column Name", null, 3, DBPDataKind.BOOLEAN);
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);

    // Act
    String actualSourceType = databaseMappingAttribute.getSourceType();

    // Assert
    verify(entity).getDataSource();
    assertNull(actualSourceType);
  }

  /**
   * Test {@link DatabaseMappingAttribute#getSourceType()}.
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#getSourceType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseMappingAttribute.getSourceType()"})
  public void testGetSourceType2() {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, null);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);

    // Act and Assert
    assertNull(databaseMappingAttribute.getSourceType());
  }

  /**
   * Test {@link DatabaseMappingAttribute#getSourceType()}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceContainer} {@link DBPDataSourceContainer#getId()} return {@code
   *       42}.
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#getSourceType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseMappingAttribute.getSourceType()"})
  public void testGetSourceType_givenDBPDataSourceContainerGetIdReturn42_thenCallsGetId() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);
    DBVEntityAttribute source =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);

    // Act
    String actualSourceType = databaseMappingAttribute.getSourceType();

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualSourceType);
  }

  /**
   * Test {@link DatabaseMappingAttribute#getSourceType()}.
   *
   * <ul>
   *   <li>Given {@link StreamDataSource#StreamDataSource(String)} with {@code Input Name}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#getSourceType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseMappingAttribute.getSourceType()"})
  public void testGetSourceType_givenStreamDataSourceWithInputName_thenReturnNull() {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    DBDAttributeBindingCustom parent2 = mock(DBDAttributeBindingCustom.class);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping dataContainer = new StreamEntityMapping(inputFile);
    StreamDataSource dataSource = new StreamDataSource("Input Name");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute vAttribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    DBDAttributeBindingCustom source =
        new DBDAttributeBindingCustom(parent2, dataContainer, dataSource, vAttribute, 1);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent3 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile2));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent3);

    // Act and Assert
    assertNull(databaseMappingAttribute.getSourceType());
  }

  /**
   * Test {@link DatabaseMappingAttribute#getSourceType()}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#getSourceType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseMappingAttribute.getSourceType()"})
  public void testGetSourceType_thenCallsGetDataSource() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(new StreamDataSource("Input Name"));
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute source =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent2, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent3 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent3);

    // Act
    String actualSourceType = databaseMappingAttribute.getSourceType();

    // Assert
    verify(parent).getDataSource();
    assertNull(actualSourceType);
  }

  /**
   * Test {@link DatabaseMappingAttribute#getSourceType()}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#getSourceType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseMappingAttribute.getSourceType()"})
  public void testGetSourceType_thenCallsGetId() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);
    DBVEntityAttribute source =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), null);

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);

    // Act
    String actualSourceType = databaseMappingAttribute.getSourceType();

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualSourceType);
  }

  /**
   * Test {@link DatabaseMappingAttribute#getSourceType()}.
   *
   * <ul>
   *   <li>Then return {@code Type Name}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#getSourceType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseMappingAttribute.getSourceType()"})
  public void testGetSourceType_thenReturnTypeName() {
    // Arrange
    StreamEntityMapping entity = mock(StreamEntityMapping.class);
    when(entity.getDataSource()).thenReturn(new StreamDataSource("Input Name"));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            entity, 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);

    // Act
    String actualSourceType = databaseMappingAttribute.getSourceType();

    // Assert
    verify(entity).getDataSource();
    assertEquals("Type Name", actualSourceType);
  }

  /**
   * Test {@link DatabaseMappingAttribute#getSourceType()}.
   *
   * <ul>
   *   <li>Then return {@code Type Name(3)}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#getSourceType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseMappingAttribute.getSourceType()"})
  public void testGetSourceType_thenReturnTypeName3() {
    // Arrange
    StreamEntityMapping entity = mock(StreamEntityMapping.class);
    when(entity.getDataSource()).thenReturn(new StreamDataSource("Input Name"));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            entity, 1, "Column Name", "Type Name", 3, DBPDataKind.STRING);
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);

    // Act
    String actualSourceType = databaseMappingAttribute.getSourceType();

    // Assert
    verify(entity).getDataSource();
    assertEquals("Type Name(3)", actualSourceType);
  }

  /**
   * Test {@link DatabaseMappingAttribute#getTargetName()}.
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#getTargetName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseMappingAttribute.getTargetName()"})
  public void testGetTargetName() {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);

    // Act and Assert
    assertEquals("?", databaseMappingAttribute.getTargetName());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DatabaseMappingAttribute#setTarget(DBSEntityAttribute)}
   *   <li>{@link DatabaseMappingAttribute#setTargetName(String)}
   *   <li>{@link DatabaseMappingAttribute#getMappingType()}
   *   <li>{@link DatabaseMappingAttribute#getParent()}
   *   <li>{@link DatabaseMappingAttribute#getTransformer()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DatabaseMappingType DatabaseMappingAttribute.getMappingType()",
    "DatabaseMappingContainer DatabaseMappingAttribute.getParent()",
    "org.jkiss.dbeaver.tools.transfer.registry.DataTransferAttributeTransformerDescriptor DatabaseMappingAttribute.getTransformer()",
    "void DatabaseMappingAttribute.setTarget(DBSEntityAttribute)",
    "void DatabaseMappingAttribute.setTargetName(String)",
    "void DatabaseMappingAttribute.setTransformer(org.jkiss.dbeaver.tools.transfer.registry.DataTransferAttributeTransformerDescriptor)"
  })
  public void testGettersAndSetters() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            new StreamEntityMapping(inputFile2),
            1,
            "Column Name",
            "Type Name",
            3,
            DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings2 = new DatabaseConsumerSettings();
    Path inputFile3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings2, new StreamEntityMapping(inputFile3));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);
    Path inputFile4 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamDataImporterColumnInfo target =
        new StreamDataImporterColumnInfo(
            new StreamEntityMapping(inputFile4),
            1,
            "Column Name",
            "Type Name",
            3,
            DBPDataKind.BOOLEAN);

    // Act
    databaseMappingAttribute.setTarget(target);
    databaseMappingAttribute.setTargetName("Target Name");
    DatabaseMappingType actualMappingType = databaseMappingAttribute.getMappingType();
    DatabaseMappingContainer actualParent = databaseMappingAttribute.getParent();

    // Assert
    assertNull(databaseMappingAttribute.getTransformer());
    assertEquals(DatabaseMappingType.unspecified, actualMappingType);
    assertSame(parent2, actualParent);
  }

  /**
   * Test {@link DatabaseMappingAttribute#setMappingType(DatabaseMappingType)}.
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#setMappingType(DatabaseMappingType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseMappingAttribute.setMappingType(DatabaseMappingType)"})
  public void testSetMappingType() {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);

    // Act
    databaseMappingAttribute.setMappingType(DatabaseMappingType.unspecified);

    // Assert that nothing has changed
    assertEquals("?", databaseMappingAttribute.getTargetName());
    assertEquals(DatabaseMappingType.unspecified, databaseMappingAttribute.getMappingType());
  }

  /**
   * Test {@link DatabaseMappingAttribute#setMappingType(DatabaseMappingType)}.
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#setMappingType(DatabaseMappingType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseMappingAttribute.setMappingType(DatabaseMappingType)"})
  public void testSetMappingType2() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    consumerSettings.setContainer(null);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent2, source);
    DatabaseConsumerSettings consumerSettings2 = new DatabaseConsumerSettings();
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent3 =
        new DatabaseMappingContainer(consumerSettings2, new StreamEntityMapping(inputFile2));

    DatabaseMappingAttribute attribute2 = new DatabaseMappingAttribute(attribute, parent3);

    new DatabaseMappingAttribute(attribute2, parent);
    DatabaseMappingContainer parent4 =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo source2 =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute3 = new DatabaseMappingAttribute(parent4, source2);
    DatabaseConsumerSettings consumerSettings3 = new DatabaseConsumerSettings();
    Path inputFile3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent5 =
        new DatabaseMappingContainer(consumerSettings3, new StreamEntityMapping(inputFile3));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute3, parent5);

    // Act
    databaseMappingAttribute.setMappingType(DatabaseMappingType.create);

    // Assert
    assertEquals("Column Name", databaseMappingAttribute.getTargetName());
    assertEquals(DatabaseMappingType.create, databaseMappingAttribute.getMappingType());
  }

  /**
   * Test {@link DatabaseMappingAttribute#setMappingType(DatabaseMappingType)}.
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#setMappingType(DatabaseMappingType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseMappingAttribute.setMappingType(DatabaseMappingType)"})
  public void testSetMappingType3() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    consumerSettings.setContainer(null);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent2, source);
    DatabaseConsumerSettings consumerSettings2 = new DatabaseConsumerSettings();
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent3 =
        new DatabaseMappingContainer(consumerSettings2, new StreamEntityMapping(inputFile2));

    DatabaseMappingAttribute attribute2 = new DatabaseMappingAttribute(attribute, parent3);

    new DatabaseMappingAttribute(attribute2, parent);
    DatabaseMappingContainer parent4 =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    DBDAttributeBindingCustom parent5 = mock(DBDAttributeBindingCustom.class);
    Path inputFile3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping dataContainer = new StreamEntityMapping(inputFile3);
    StreamDataSource dataSource = new StreamDataSource("Input Name");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute vAttribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    DBDAttributeBindingCustom source2 =
        new DBDAttributeBindingCustom(parent5, dataContainer, dataSource, vAttribute, 3);

    DatabaseMappingAttribute attribute3 = new DatabaseMappingAttribute(parent4, source2);
    DatabaseConsumerSettings consumerSettings3 = new DatabaseConsumerSettings();
    Path inputFile4 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent6 =
        new DatabaseMappingContainer(consumerSettings3, new StreamEntityMapping(inputFile4));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute3, parent6);

    // Act
    databaseMappingAttribute.setMappingType(DatabaseMappingType.create);

    // Assert
    assertEquals("Name", databaseMappingAttribute.getTargetName());
    assertEquals(DatabaseMappingType.create, databaseMappingAttribute.getMappingType());
  }

  /**
   * Test {@link DatabaseMappingAttribute#setMappingType(DatabaseMappingType)}.
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#setMappingType(DatabaseMappingType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseMappingAttribute.setMappingType(DatabaseMappingType)"})
  public void testSetMappingType4() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    consumerSettings.setContainer(null);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent2, source);
    DatabaseConsumerSettings consumerSettings2 = new DatabaseConsumerSettings();
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent3 =
        new DatabaseMappingContainer(consumerSettings2, new StreamEntityMapping(inputFile2));

    DatabaseMappingAttribute attribute2 = new DatabaseMappingAttribute(attribute, parent3);

    new DatabaseMappingAttribute(attribute2, parent);
    DatabaseMappingContainer parent4 =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    DBDAttributeBindingCustom parent5 = mock(DBDAttributeBindingCustom.class);
    Path inputFile3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping dataContainer = new StreamEntityMapping(inputFile3);
    StreamDataSource dataSource = new StreamDataSource("Input Name");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute vAttribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), (String) null);

    DBDAttributeBindingCustom source2 =
        new DBDAttributeBindingCustom(parent5, dataContainer, dataSource, vAttribute, 3);

    DatabaseMappingAttribute attribute3 = new DatabaseMappingAttribute(parent4, source2);
    DatabaseConsumerSettings consumerSettings3 = new DatabaseConsumerSettings();
    Path inputFile4 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent6 =
        new DatabaseMappingContainer(consumerSettings3, new StreamEntityMapping(inputFile4));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute3, parent6);

    // Act
    databaseMappingAttribute.setMappingType(DatabaseMappingType.create);

    // Assert
    assertNull(databaseMappingAttribute.getTargetName());
    assertEquals(DatabaseMappingType.create, databaseMappingAttribute.getMappingType());
  }

  /**
   * Test {@link DatabaseMappingAttribute#setMappingType(DatabaseMappingType)}.
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#setMappingType(DatabaseMappingType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseMappingAttribute.setMappingType(DatabaseMappingType)"})
  public void testSetMappingType5() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    consumerSettings.setContainer(null);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent2, source);
    DatabaseConsumerSettings consumerSettings2 = new DatabaseConsumerSettings();
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent3 =
        new DatabaseMappingContainer(consumerSettings2, new StreamEntityMapping(inputFile2));

    DatabaseMappingAttribute attribute2 = new DatabaseMappingAttribute(attribute, parent3);

    new DatabaseMappingAttribute(attribute2, parent);
    DatabaseMappingContainer parent4 =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    DBDAttributeBindingCustom parent5 = mock(DBDAttributeBindingCustom.class);
    Path inputFile3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping dataContainer = new StreamEntityMapping(inputFile3);
    StreamDataSource dataSource = new StreamDataSource("Input Name");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute vAttribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "");

    DBDAttributeBindingCustom source2 =
        new DBDAttributeBindingCustom(parent5, dataContainer, dataSource, vAttribute, 3);

    DatabaseMappingAttribute attribute3 = new DatabaseMappingAttribute(parent4, source2);
    DatabaseConsumerSettings consumerSettings3 = new DatabaseConsumerSettings();
    Path inputFile4 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent6 =
        new DatabaseMappingContainer(consumerSettings3, new StreamEntityMapping(inputFile4));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute3, parent6);

    // Act
    databaseMappingAttribute.setMappingType(DatabaseMappingType.create);

    // Assert
    assertEquals("", databaseMappingAttribute.getTargetName());
    assertEquals(DatabaseMappingType.create, databaseMappingAttribute.getMappingType());
  }

  /**
   * Test {@link DatabaseMappingAttribute#setMappingType(DatabaseMappingType)}.
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#setMappingType(DatabaseMappingType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseMappingAttribute.setMappingType(DatabaseMappingType)"})
  public void testSetMappingType6() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    consumerSettings.setContainer(null);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent2, source);
    DatabaseConsumerSettings consumerSettings2 = new DatabaseConsumerSettings();
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent3 =
        new DatabaseMappingContainer(consumerSettings2, new StreamEntityMapping(inputFile2));

    DatabaseMappingAttribute attribute2 = new DatabaseMappingAttribute(attribute, parent3);

    new DatabaseMappingAttribute(attribute2, parent);

    DBVContainer parent4 = mock(DBVContainer.class);
    when(parent4.getDataSource()).thenReturn(null);
    DBVContainer container = new DBVContainer(parent4, "org.jkiss.dbeaver.application");

    DatabaseConsumerSettings consumerSettings3 = new DatabaseConsumerSettings();
    consumerSettings3.setContainer(container);
    Path inputFile3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent5 =
        new DatabaseMappingContainer(consumerSettings3, new StreamEntityMapping(inputFile3));
    DatabaseMappingContainer parent6 =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute source2 =
        new DBVEntityAttribute(
            entity, mock(DBVEntityAttribute.class), "org.jkiss.dbeaver.application");

    DatabaseMappingAttribute attribute3 = new DatabaseMappingAttribute(parent6, source2);

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute3, parent5);

    // Act
    databaseMappingAttribute.setMappingType(DatabaseMappingType.create);

    // Assert
    verify(parent4).getDataSource();
    assertEquals("org.jkiss.dbeaver.application", databaseMappingAttribute.getTargetName());
    assertEquals(DatabaseMappingType.create, databaseMappingAttribute.getMappingType());
  }

  /**
   * Test {@link DatabaseMappingAttribute#updateMappingType(DBRProgressMonitor, boolean, boolean)}.
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#updateMappingType(DBRProgressMonitor,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseMappingAttribute.updateMappingType(DBRProgressMonitor, boolean, boolean)"
  })
  public void testUpdateMappingType() throws DBException {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);

    // Act
    databaseMappingAttribute.updateMappingType(new LoggingProgressMonitor(), true, true);

    // Assert
    assertEquals("Column Name", databaseMappingAttribute.getTargetName());
    assertEquals(DatabaseMappingType.create, databaseMappingAttribute.getMappingType());
  }

  /**
   * Test {@link DatabaseMappingAttribute#updateMappingType(DBRProgressMonitor, boolean, boolean)}.
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#updateMappingType(DBRProgressMonitor,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseMappingAttribute.updateMappingType(DBRProgressMonitor, boolean, boolean)"
  })
  public void testUpdateMappingType2() throws DBException {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, null, "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);

    // Act
    databaseMappingAttribute.updateMappingType(new LoggingProgressMonitor(), true, true);

    // Assert
    assertNull(databaseMappingAttribute.getTargetName());
    assertEquals(DatabaseMappingType.create, databaseMappingAttribute.getMappingType());
  }

  /**
   * Test {@link DatabaseMappingAttribute#updateMappingType(DBRProgressMonitor, boolean, boolean)}.
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#updateMappingType(DBRProgressMonitor,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseMappingAttribute.updateMappingType(DBRProgressMonitor, boolean, boolean)"
  })
  public void testUpdateMappingType3() throws DBException {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "", "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);

    // Act
    databaseMappingAttribute.updateMappingType(new LoggingProgressMonitor(), true, true);

    // Assert
    assertEquals("", databaseMappingAttribute.getTargetName());
    assertEquals(DatabaseMappingType.create, databaseMappingAttribute.getMappingType());
  }

  /**
   * Test {@link DatabaseMappingAttribute#updateMappingType(DBRProgressMonitor, boolean, boolean)}.
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#updateMappingType(DBRProgressMonitor,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseMappingAttribute.updateMappingType(DBRProgressMonitor, boolean, boolean)"
  })
  public void testUpdateMappingType4() throws DBException {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    DBDAttributeBindingCustom parent2 = mock(DBDAttributeBindingCustom.class);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping dataContainer = new StreamEntityMapping(inputFile);
    StreamDataSource dataSource = new StreamDataSource("Input Name");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute vAttribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    DBDAttributeBindingCustom source =
        new DBDAttributeBindingCustom(parent2, dataContainer, dataSource, vAttribute, 1);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent3 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile2));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent3);

    // Act
    databaseMappingAttribute.updateMappingType(new LoggingProgressMonitor(), true, true);

    // Assert
    assertEquals("Name", databaseMappingAttribute.getTargetName());
    assertEquals(DatabaseMappingType.create, databaseMappingAttribute.getMappingType());
  }

  /**
   * Test {@link DatabaseMappingAttribute#updateMappingType(DBRProgressMonitor, boolean, boolean)}.
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#updateMappingType(DBRProgressMonitor,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseMappingAttribute.updateMappingType(DBRProgressMonitor, boolean, boolean)"
  })
  public void testUpdateMappingType5() throws DBException {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    DBDAttributeBindingCustom parent2 = mock(DBDAttributeBindingCustom.class);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping dataContainer = new StreamEntityMapping(inputFile);
    StreamDataSource dataSource = new StreamDataSource("Input Name");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute vAttribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), (String) null);

    DBDAttributeBindingCustom source =
        new DBDAttributeBindingCustom(parent2, dataContainer, dataSource, vAttribute, 1);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent3 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile2));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent3);

    // Act
    databaseMappingAttribute.updateMappingType(new LoggingProgressMonitor(), true, true);

    // Assert
    assertNull(databaseMappingAttribute.getTargetName());
    assertEquals(DatabaseMappingType.create, databaseMappingAttribute.getMappingType());
  }

  /**
   * Test {@link DatabaseMappingAttribute#updateMappingType(DBRProgressMonitor, boolean, boolean)}.
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#updateMappingType(DBRProgressMonitor,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseMappingAttribute.updateMappingType(DBRProgressMonitor, boolean, boolean)"
  })
  public void testUpdateMappingType6() throws DBException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(null);
    DBVContainer container = new DBVContainer(parent, "org.jkiss.dbeaver.application");

    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    consumerSettings.setContainer(container);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    DatabaseMappingContainer parent3 =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute source =
        new DBVEntityAttribute(
            entity, mock(DBVEntityAttribute.class), "org.jkiss.dbeaver.application");

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent3, source);

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);

    // Act
    databaseMappingAttribute.updateMappingType(new LoggingProgressMonitor(), true, true);

    // Assert
    verify(parent, atLeast(1)).getDataSource();
    assertEquals("org.jkiss.dbeaver.application", databaseMappingAttribute.getTargetName());
    assertEquals(DatabaseMappingType.create, databaseMappingAttribute.getMappingType());
  }

  /**
   * Test {@link DatabaseMappingAttribute#updateMappingType(DBRProgressMonitor, boolean, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#updateMappingType(DBRProgressMonitor,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseMappingAttribute.updateMappingType(DBRProgressMonitor, boolean, boolean)"
  })
  public void testUpdateMappingType_whenFalse() throws DBException {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    new DatabaseMappingAttribute(attribute, parent2);
    DatabaseMappingContainer parent3 =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo source2 =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute2 = new DatabaseMappingAttribute(parent3, source2);
    DatabaseConsumerSettings consumerSettings2 = new DatabaseConsumerSettings();
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent4 =
        new DatabaseMappingContainer(consumerSettings2, new StreamEntityMapping(inputFile2));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute2, parent4);

    // Act
    databaseMappingAttribute.updateMappingType(new LoggingProgressMonitor(), false, false);

    // Assert
    assertEquals("Column Name", databaseMappingAttribute.getTargetName());
    assertEquals(DatabaseMappingType.create, databaseMappingAttribute.getMappingType());
  }

  /**
   * Test {@link DatabaseMappingAttribute#getSourceLabelOrName(DBSAttributeBase, boolean, boolean)}
   * with {@code source}, {@code addSpecialTransformation}, {@code updateAttributesNames}.
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#getSourceLabelOrName(DBSAttributeBase,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseMappingAttribute.getSourceLabelOrName(DBSAttributeBase, boolean, boolean)"
  })
  public void testGetSourceLabelOrNameWithSourceAddSpecialTransformationUpdateAttributesNames() {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamDataImporterColumnInfo source2 =
        new StreamDataImporterColumnInfo(
            new StreamEntityMapping(inputFile2),
            1,
            "Column Name",
            "Type Name",
            3,
            DBPDataKind.BOOLEAN);

    // Act and Assert
    assertEquals("Column Name", databaseMappingAttribute.getSourceLabelOrName(source2, true, true));
  }

  /**
   * Test {@link DatabaseMappingAttribute#getSourceLabelOrName(DBSAttributeBase, boolean, boolean)}
   * with {@code source}, {@code addSpecialTransformation}, {@code updateAttributesNames}.
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#getSourceLabelOrName(DBSAttributeBase,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseMappingAttribute.getSourceLabelOrName(DBSAttributeBase, boolean, boolean)"
  })
  public void testGetSourceLabelOrNameWithSourceAddSpecialTransformationUpdateAttributesNames2() {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile2);
    DBDAttributeBindingCustom parent3 = mock(DBDAttributeBindingCustom.class);
    Path inputFile3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping dataContainer = new StreamEntityMapping(inputFile3);
    StreamDataSource dataSource = new StreamDataSource("Input Name");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute vAttribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    DBDAttributeBindingCustom source2 =
        new DBDAttributeBindingCustom(parent3, dataContainer, dataSource, vAttribute, 1);

    // Act and Assert
    assertEquals("Name", databaseMappingAttribute.getSourceLabelOrName(source2, true, true));
  }

  /**
   * Test {@link DatabaseMappingAttribute#getSourceLabelOrName(DBSAttributeBase, boolean, boolean)}
   * with {@code source}, {@code addSpecialTransformation}, {@code updateAttributesNames}.
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#getSourceLabelOrName(DBSAttributeBase,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseMappingAttribute.getSourceLabelOrName(DBSAttributeBase, boolean, boolean)"
  })
  public void testGetSourceLabelOrNameWithSourceAddSpecialTransformationUpdateAttributesNames3() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(null);
    DBVContainer container = new DBVContainer(parent, "org.jkiss.dbeaver.application");

    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    consumerSettings.setContainer(container);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    DatabaseMappingContainer parent3 =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    DBVContainer container2 =
        new DBVContainer(mock(DBVContainer.class), "org.jkiss.dbeaver.application");
    DBVEntity entity =
        new DBVEntity(container2, "org.jkiss.dbeaver.application", "org.jkiss.dbeaver.application");
    DBVEntityAttribute source =
        new DBVEntityAttribute(
            entity, mock(DBVEntityAttribute.class), "org.jkiss.dbeaver.application");

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent3, source);

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamDataImporterColumnInfo source2 =
        new StreamDataImporterColumnInfo(
            new StreamEntityMapping(inputFile2),
            1,
            "Column Name",
            "Type Name",
            3,
            DBPDataKind.BOOLEAN);

    // Act
    String actualSourceLabelOrName =
        databaseMappingAttribute.getSourceLabelOrName(source2, true, true);

    // Assert
    verify(parent).getDataSource();
    assertEquals("Column Name", actualSourceLabelOrName);
  }

  /**
   * Test {@link DatabaseMappingAttribute#getTarget()}.
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#getTarget()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityAttribute DatabaseMappingAttribute.getTarget()"})
  public void testGetTarget() {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);

    // Act and Assert
    assertNull(databaseMappingAttribute.getTarget());
  }

  /**
   * Test {@link DatabaseMappingAttribute#getTransformerProperties()}.
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#getTransformerProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DatabaseMappingAttribute.getTransformerProperties()"})
  public void testGetTransformerProperties() {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);

    // Act and Assert
    assertTrue(databaseMappingAttribute.getTransformerProperties().isEmpty());
  }

  /**
   * Test {@link DatabaseMappingAttribute#saveSettings(Map)}.
   *
   * <ul>
   *   <li>Then {@link HashMap#HashMap()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#saveSettings(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseMappingAttribute.saveSettings(Map)"})
  public void testSaveSettings_thenHashMapSizeIsOne() {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);
    HashMap<String, Object> settings = new HashMap<>();

    // Act
    databaseMappingAttribute.saveSettings(settings);

    // Assert
    assertEquals(1, settings.size());
    assertEquals("unspecified", settings.get("mappingType"));
  }

  /**
   * Test {@link DatabaseMappingAttribute#saveSettings(Map)}.
   *
   * <ul>
   *   <li>Then {@link HashMap#HashMap()} size is two.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#saveSettings(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseMappingAttribute.saveSettings(Map)"})
  public void testSaveSettings_thenHashMapSizeIsTwo() {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);
    databaseMappingAttribute.setTargetName("mappingType");
    HashMap<String, Object> settings = new HashMap<>();

    // Act
    databaseMappingAttribute.saveSettings(settings);

    // Assert
    assertEquals(2, settings.size());
    assertEquals("mappingType", settings.get("targetName"));
    assertEquals("unspecified", settings.get("mappingType"));
  }

  /**
   * Test {@link DatabaseMappingAttribute#toString()}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer} and {@code Name}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseMappingAttribute.toString()"})
  public void testToString_givenDBVContainerWithParentIsDBVContainerAndName_thenReturnName() {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    DBDAttributeBindingCustom parent2 = mock(DBDAttributeBindingCustom.class);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping dataContainer = new StreamEntityMapping(inputFile);
    StreamDataSource dataSource = new StreamDataSource("Input Name");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute vAttribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    DBDAttributeBindingCustom source =
        new DBDAttributeBindingCustom(parent2, dataContainer, dataSource, vAttribute, 1);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent3 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile2));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent3);

    // Act and Assert
    assertEquals("Name", databaseMappingAttribute.toString());
  }

  /**
   * Test {@link DatabaseMappingAttribute#toString()}.
   *
   * <ul>
   *   <li>Then return {@code Column Name}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseMappingAttribute.toString()"})
  public void testToString_thenReturnColumnName() {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            mock(StreamEntityMapping.class), 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, source);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);

    // Act and Assert
    assertEquals("Column Name", databaseMappingAttribute.toString());
  }

  /**
   * Test {@link DatabaseMappingAttribute#toString()}.
   *
   * <ul>
   *   <li>Then return {@code ?}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseMappingAttribute#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseMappingAttribute.toString()"})
  public void testToString_thenReturnQuestionMark() {
    // Arrange
    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), mock(DBSDataContainer.class));
    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent, null);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    DatabaseMappingAttribute databaseMappingAttribute =
        new DatabaseMappingAttribute(attribute, parent2);

    // Act and Assert
    assertEquals("?", databaseMappingAttribute.toString());
  }
}
