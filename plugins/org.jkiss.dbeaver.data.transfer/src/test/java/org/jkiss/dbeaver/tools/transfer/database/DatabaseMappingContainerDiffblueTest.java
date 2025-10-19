package org.jkiss.dbeaver.tools.transfer.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBIcon;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.preferences.DBPPropertyDescriptor;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.DBRRunnableContext;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSDataContainer;
import org.jkiss.dbeaver.model.struct.DBSDataManipulator;
import org.jkiss.dbeaver.tools.transfer.stream.StreamEntityMapping;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DatabaseMappingContainerDiffblueTest {
  /**
   * Test {@link DatabaseMappingContainer#DatabaseMappingContainer(DatabaseConsumerSettings,
   * DBSDataContainer)}.
   *
   * <p>Method under test: {@link
   * DatabaseMappingContainer#DatabaseMappingContainer(DatabaseConsumerSettings, DBSDataContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseMappingContainer.<init>(DatabaseConsumerSettings, DBSDataContainer)"
  })
  public void testNewDatabaseMappingContainer() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping source = new StreamEntityMapping(inputFile);

    // Act
    DatabaseMappingContainer actualDatabaseMappingContainer =
        new DatabaseMappingContainer(consumerSettings, source);

    // Assert
    Collection<DatabaseMappingAttribute> attributeMappings =
        actualDatabaseMappingContainer.getAttributeMappings();
    assertTrue(attributeMappings instanceof List);
    assertTrue(actualDatabaseMappingContainer.getIcon() instanceof DBIcon);
    DBSDataContainer source2 = actualDatabaseMappingContainer.getSource();
    assertTrue(source2 instanceof StreamEntityMapping);
    assertNull(actualDatabaseMappingContainer.getTargetFullName());
    assertNull(actualDatabaseMappingContainer.getRawChangedPropertiesMap());
    assertNull(actualDatabaseMappingContainer.getChangedPropertiesMap());
    assertNull(actualDatabaseMappingContainer.getTarget());
    assertEquals(DatabaseMappingType.unspecified, actualDatabaseMappingContainer.getMappingType());
    assertFalse(actualDatabaseMappingContainer.hasNewTargetObject());
    assertTrue(attributeMappings.isEmpty());
    assertTrue(actualDatabaseMappingContainer.isCompleted());
    assertSame(consumerSettings, actualDatabaseMappingContainer.getSettings());
    assertSame(source, source2);
  }

  /**
   * Test {@link DatabaseMappingContainer#DatabaseMappingContainer(DBRProgressMonitor,
   * DatabaseConsumerSettings, DBSDataContainer, DBSDataManipulator)}.
   *
   * <ul>
   *   <li>Then AttributeMappings return {@link List}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DatabaseMappingContainer#DatabaseMappingContainer(DBRProgressMonitor, DatabaseConsumerSettings,
   * DBSDataContainer, DBSDataManipulator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseMappingContainer.<init>(DBRProgressMonitor, DatabaseConsumerSettings, DBSDataContainer, DBSDataManipulator)"
  })
  public void testNewDatabaseMappingContainer_thenAttributeMappingsReturnList() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping sourceObject = new StreamEntityMapping(inputFile);
    DBSDataManipulator targetObject = mock(DBSDataManipulator.class);

    // Act
    DatabaseMappingContainer actualDatabaseMappingContainer =
        new DatabaseMappingContainer(monitor, consumerSettings, sourceObject, targetObject);

    // Assert
    Collection<DatabaseMappingAttribute> attributeMappings =
        actualDatabaseMappingContainer.getAttributeMappings();
    assertTrue(attributeMappings instanceof List);
    assertTrue(actualDatabaseMappingContainer.getIcon() instanceof DBIcon);
    DBSDataContainer source = actualDatabaseMappingContainer.getSource();
    assertTrue(source instanceof StreamEntityMapping);
    assertNull(actualDatabaseMappingContainer.getTargetFullName());
    assertNull(actualDatabaseMappingContainer.getTargetName());
    assertNull(actualDatabaseMappingContainer.getRawChangedPropertiesMap());
    assertNull(actualDatabaseMappingContainer.getChangedPropertiesMap());
    assertEquals(DatabaseMappingType.existing, actualDatabaseMappingContainer.getMappingType());
    assertFalse(actualDatabaseMappingContainer.hasNewTargetObject());
    assertTrue(attributeMappings.isEmpty());
    assertTrue(actualDatabaseMappingContainer.isCompleted());
    assertSame(consumerSettings, actualDatabaseMappingContainer.getSettings());
    assertSame(sourceObject, source);
    assertSame(targetObject, actualDatabaseMappingContainer.getTarget());
  }

  /**
   * Test {@link DatabaseMappingContainer#DatabaseMappingContainer(DatabaseMappingContainer,
   * DBSDataContainer)}.
   *
   * <ul>
   *   <li>Then AttributeMappings return {@link List}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DatabaseMappingContainer#DatabaseMappingContainer(DatabaseMappingContainer, DBSDataContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseMappingContainer.<init>(DatabaseMappingContainer, DBSDataContainer)"
  })
  public void testNewDatabaseMappingContainer_thenAttributeMappingsReturnList2() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping source = new StreamEntityMapping(inputFile);

    DatabaseMappingContainer container = new DatabaseMappingContainer(consumerSettings, source);
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping sourceObject = new StreamEntityMapping(inputFile2);

    // Act
    DatabaseMappingContainer actualDatabaseMappingContainer =
        new DatabaseMappingContainer(container, sourceObject);

    // Assert
    Collection<DatabaseMappingAttribute> attributeMappings =
        actualDatabaseMappingContainer.getAttributeMappings();
    assertTrue(attributeMappings instanceof List);
    assertTrue(actualDatabaseMappingContainer.getIcon() instanceof DBIcon);
    DBSDataContainer source2 = actualDatabaseMappingContainer.getSource();
    assertTrue(source2 instanceof StreamEntityMapping);
    assertNull(actualDatabaseMappingContainer.getTargetFullName());
    assertNull(actualDatabaseMappingContainer.getRawChangedPropertiesMap());
    assertNull(actualDatabaseMappingContainer.getChangedPropertiesMap());
    assertNull(actualDatabaseMappingContainer.getTarget());
    assertEquals(DatabaseMappingType.unspecified, actualDatabaseMappingContainer.getMappingType());
    assertFalse(actualDatabaseMappingContainer.hasNewTargetObject());
    assertTrue(attributeMappings.isEmpty());
    assertTrue(actualDatabaseMappingContainer.isCompleted());
    assertEquals(source, source2);
    assertSame(consumerSettings, actualDatabaseMappingContainer.getSettings());
    assertSame(sourceObject, source2);
  }

  /**
   * Test {@link DatabaseMappingContainer#getTarget()}.
   *
   * <p>Method under test: {@link DatabaseMappingContainer#getTarget()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSDataManipulator DatabaseMappingContainer.getTarget()"})
  public void testGetTarget() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer databaseMappingContainer =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    // Act and Assert
    assertNull(databaseMappingContainer.getTarget());
  }

  /**
   * Test {@link DatabaseMappingContainer#setTarget(DBSDataManipulator)}.
   *
   * <p>Method under test: {@link DatabaseMappingContainer#setTarget(DBSDataManipulator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseMappingContainer.setTarget(DBSDataManipulator)"})
  public void testSetTarget() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer databaseMappingContainer =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    DBSDataManipulator target = mock(DBSDataManipulator.class);

    // Act
    databaseMappingContainer.setTarget(target);

    // Assert
    assertNull(databaseMappingContainer.getTargetName());
    assertSame(target, databaseMappingContainer.getTarget());
  }

  /**
   * Test {@link DatabaseMappingContainer#isCompleted()}.
   *
   * <p>Method under test: {@link DatabaseMappingContainer#isCompleted()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseMappingContainer.isCompleted()"})
  public void testIsCompleted() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer databaseMappingContainer =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    // Act and Assert
    assertTrue(databaseMappingContainer.isCompleted());
  }

  /**
   * Test {@link DatabaseMappingContainer#isCompleted()}.
   *
   * <p>Method under test: {@link DatabaseMappingContainer#isCompleted()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseMappingContainer.isCompleted()"})
  public void testIsCompleted2() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DatabaseMappingContainer databaseMappingContainer =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    databaseMappingContainer.setMappingType(DatabaseMappingType.skip);

    // Act and Assert
    assertTrue(databaseMappingContainer.isCompleted());
  }

  /**
   * Test {@link DatabaseMappingContainer#getSource()}.
   *
   * <p>Method under test: {@link DatabaseMappingContainer#getSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSDataContainer DatabaseMappingContainer.getSource()"})
  public void testGetSource() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping source = new StreamEntityMapping(inputFile);

    DatabaseMappingContainer databaseMappingContainer =
        new DatabaseMappingContainer(consumerSettings, source);

    // Act and Assert
    assertSame(source, databaseMappingContainer.getSource());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DatabaseMappingContainer#setChangedPropertiesMap(Map)}
   *   <li>{@link DatabaseMappingContainer#setMappingType(DatabaseMappingType)}
   *   <li>{@link DatabaseMappingContainer#setTargetName(String)}
   *   <li>{@link DatabaseMappingContainer#getChangedPropertiesMap()}
   *   <li>{@link DatabaseMappingContainer#getIcon()}
   *   <li>{@link DatabaseMappingContainer#getMappingType()}
   *   <li>{@link DatabaseMappingContainer#getRawChangedPropertiesMap()}
   *   <li>{@link DatabaseMappingContainer#getSettings()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map DatabaseMappingContainer.getChangedPropertiesMap()",
    "DBPImage DatabaseMappingContainer.getIcon()",
    "DatabaseMappingType DatabaseMappingContainer.getMappingType()",
    "Map DatabaseMappingContainer.getRawChangedPropertiesMap()",
    "DatabaseConsumerSettings DatabaseMappingContainer.getSettings()",
    "void DatabaseMappingContainer.setChangedPropertiesMap(Map)",
    "void DatabaseMappingContainer.setMappingType(DatabaseMappingType)",
    "void DatabaseMappingContainer.setTargetName(String)"
  })
  public void testGettersAndSetters() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer databaseMappingContainer =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    HashMap<DBPPropertyDescriptor, Object> changedPropertiesMap = new HashMap<>();

    // Act
    databaseMappingContainer.setChangedPropertiesMap(changedPropertiesMap);
    databaseMappingContainer.setMappingType(DatabaseMappingType.unspecified);
    databaseMappingContainer.setTargetName("Target Name");
    Map<DBPPropertyDescriptor, Object> actualChangedPropertiesMap =
        databaseMappingContainer.getChangedPropertiesMap();
    DBPImage actualIcon = databaseMappingContainer.getIcon();
    DatabaseMappingType actualMappingType = databaseMappingContainer.getMappingType();
    Map<String, Object> actualRawChangedPropertiesMap =
        databaseMappingContainer.getRawChangedPropertiesMap();
    DatabaseConsumerSettings actualSettings = databaseMappingContainer.getSettings();

    // Assert
    assertNull(actualRawChangedPropertiesMap);
    assertEquals(DatabaseMappingType.unspecified, actualMappingType);
    assertTrue(actualChangedPropertiesMap.isEmpty());
    assertSame(changedPropertiesMap, actualChangedPropertiesMap);
    assertSame(consumerSettings, actualSettings);
    assertSame(((DBIcon) actualIcon).TREE_TABLE, actualIcon);
  }

  /**
   * Test {@link DatabaseMappingContainer#getAttributeMappings(DBRProgressMonitor)} with {@code
   * DBRProgressMonitor}.
   *
   * <ul>
   *   <li>Then return {@link List}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseMappingContainer#getAttributeMappings(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Collection DatabaseMappingContainer.getAttributeMappings(DBRProgressMonitor)"
  })
  public void testGetAttributeMappingsWithDBRProgressMonitor_thenReturnList() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer databaseMappingContainer =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    // Act
    Collection<DatabaseMappingAttribute> actualAttributeMappings =
        databaseMappingContainer.getAttributeMappings(new LoggingProgressMonitor());

    // Assert
    assertTrue(actualAttributeMappings instanceof List);
    assertTrue(actualAttributeMappings.isEmpty());
  }

  /**
   * Test {@link DatabaseMappingContainer#getAttributeMappings()}.
   *
   * <ul>
   *   <li>Then return {@link List}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseMappingContainer#getAttributeMappings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection DatabaseMappingContainer.getAttributeMappings()"})
  public void testGetAttributeMappings_thenReturnList() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer databaseMappingContainer =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    // Act
    Collection<DatabaseMappingAttribute> actualAttributeMappings =
        databaseMappingContainer.getAttributeMappings();

    // Assert
    assertTrue(actualAttributeMappings instanceof List);
    assertTrue(actualAttributeMappings.isEmpty());
  }

  /**
   * Test {@link DatabaseMappingContainer#saveSettings(Map)}.
   *
   * <p>Method under test: {@link DatabaseMappingContainer#saveSettings(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseMappingContainer.saveSettings(Map)"})
  public void testSaveSettings() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer databaseMappingContainer =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    HashMap<String, Object> settings = new HashMap<>();

    // Act
    databaseMappingContainer.saveSettings(settings);

    // Assert
    assertEquals(1, settings.size());
    assertEquals("unspecified", settings.get("mappingType"));
  }

  /**
   * Test {@link DatabaseMappingContainer#loadSettings(DBRRunnableContext, Map)}.
   *
   * <p>Method under test: {@link DatabaseMappingContainer#loadSettings(DBRRunnableContext, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseMappingContainer.loadSettings(DBRRunnableContext, Map)"})
  public void testLoadSettings() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer databaseMappingContainer =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    DBRRunnableContext context = mock(DBRRunnableContext.class);

    // Act
    databaseMappingContainer.loadSettings(context, new HashMap<>());

    // Assert
    assertNull(databaseMappingContainer.getTargetFullName());
    assertTrue(databaseMappingContainer.getRawChangedPropertiesMap().isEmpty());
  }

  /**
   * Test {@link DatabaseMappingContainer#loadSettings(DBRRunnableContext, Map)}.
   *
   * <p>Method under test: {@link DatabaseMappingContainer#loadSettings(DBRRunnableContext, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseMappingContainer.loadSettings(DBRRunnableContext, Map)"})
  public void testLoadSettings2() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer databaseMappingContainer =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    DBRRunnableContext context = mock(DBRRunnableContext.class);

    HashMap<String, Object> settings = new HashMap<>();
    settings.put("targetName", "42");

    // Act
    databaseMappingContainer.loadSettings(context, settings);

    // Assert
    assertEquals("42", databaseMappingContainer.getTargetFullName());
    assertEquals("42", databaseMappingContainer.getTargetName());
    assertTrue(databaseMappingContainer.getRawChangedPropertiesMap().isEmpty());
  }

  /**
   * Test {@link DatabaseMappingContainer#loadSettings(DBRRunnableContext, Map)}.
   *
   * <ul>
   *   <li>Given {@code mappingType}.
   *   <li>When {@link HashMap#HashMap()} {@code mappingType} is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseMappingContainer#loadSettings(DBRRunnableContext, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseMappingContainer.loadSettings(DBRRunnableContext, Map)"})
  public void testLoadSettings_givenMappingType_whenHashMapMappingTypeIs42() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer databaseMappingContainer =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    DBRRunnableContext context = mock(DBRRunnableContext.class);

    HashMap<String, Object> settings = new HashMap<>();
    settings.put("mappingType", "42");
    settings.put("targetName", "42");

    // Act
    databaseMappingContainer.loadSettings(context, settings);

    // Assert
    assertEquals("42", databaseMappingContainer.getTargetFullName());
    assertEquals("42", databaseMappingContainer.getTargetName());
    assertTrue(databaseMappingContainer.getRawChangedPropertiesMap().isEmpty());
  }

  /**
   * Test {@link DatabaseMappingContainer#isSameMapping(DatabaseMappingContainer)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseMappingContainer#isSameMapping(DatabaseMappingContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseMappingContainer.isSameMapping(DatabaseMappingContainer)"})
  public void testIsSameMapping_thenReturnFalse() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer databaseMappingContainer =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), null);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer mapping =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    // Act
    boolean actualIsSameMappingResult = databaseMappingContainer.isSameMapping(mapping);

    // Assert
    assertFalse(actualIsSameMappingResult);
  }

  /**
   * Test {@link DatabaseMappingContainer#isSameMapping(DatabaseMappingContainer)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseMappingContainer#isSameMapping(DatabaseMappingContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseMappingContainer.isSameMapping(DatabaseMappingContainer)"})
  public void testIsSameMapping_thenReturnFalse2() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer databaseMappingContainer =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer mapping =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), null);

    // Act
    boolean actualIsSameMappingResult = databaseMappingContainer.isSameMapping(mapping);

    // Assert
    assertFalse(actualIsSameMappingResult);
  }

  /**
   * Test {@link DatabaseMappingContainer#isSameMapping(DatabaseMappingContainer)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseMappingContainer#isSameMapping(DatabaseMappingContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseMappingContainer.isSameMapping(DatabaseMappingContainer)"})
  public void testIsSameMapping_thenReturnTrue() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer databaseMappingContainer =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    DatabaseConsumerSettings consumerSettings2 = new DatabaseConsumerSettings();
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer mapping =
        new DatabaseMappingContainer(consumerSettings2, new StreamEntityMapping(inputFile2));

    // Act
    boolean actualIsSameMappingResult = databaseMappingContainer.isSameMapping(mapping);

    // Assert
    assertTrue(actualIsSameMappingResult);
  }

  /**
   * Test {@link DatabaseMappingContainer#isSameMapping(DatabaseMappingContainer)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseMappingContainer#isSameMapping(DatabaseMappingContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseMappingContainer.isSameMapping(DatabaseMappingContainer)"})
  public void testIsSameMapping_thenReturnTrue2() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer databaseMappingContainer =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), null);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer mapping =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), null);

    // Act
    boolean actualIsSameMappingResult = databaseMappingContainer.isSameMapping(mapping);

    // Assert
    assertTrue(actualIsSameMappingResult);
  }

  /**
   * Test {@link DatabaseMappingContainer#hasNewTargetObject()}.
   *
   * <p>Method under test: {@link DatabaseMappingContainer#hasNewTargetObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseMappingContainer.hasNewTargetObject()"})
  public void testHasNewTargetObject() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DatabaseMappingContainer databaseMappingContainer =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    databaseMappingContainer.setMappingType(DatabaseMappingType.create);

    // Act and Assert
    assertTrue(databaseMappingContainer.hasNewTargetObject());
  }

  /**
   * Test {@link DatabaseMappingContainer#hasNewTargetObject()}.
   *
   * <p>Method under test: {@link DatabaseMappingContainer#hasNewTargetObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseMappingContainer.hasNewTargetObject()"})
  public void testHasNewTargetObject2() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DatabaseMappingContainer databaseMappingContainer =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    databaseMappingContainer.setMappingType(DatabaseMappingType.recreate);

    // Act and Assert
    assertTrue(databaseMappingContainer.hasNewTargetObject());
  }

  /**
   * Test {@link DatabaseMappingContainer#hasNewTargetObject()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseMappingContainer#hasNewTargetObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseMappingContainer.hasNewTargetObject()"})
  public void testHasNewTargetObject_thenReturnFalse() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer databaseMappingContainer =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    // Act and Assert
    assertFalse(databaseMappingContainer.hasNewTargetObject());
  }

  /**
   * Test {@link DatabaseMappingContainer#getTargetFullName()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseMappingContainer#getTargetFullName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseMappingContainer.getTargetFullName()"})
  public void testGetTargetFullName_thenReturnNull() {
    // Arrange
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer databaseMappingContainer =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    // Act and Assert
    assertNull(databaseMappingContainer.getTargetFullName());
  }
}
