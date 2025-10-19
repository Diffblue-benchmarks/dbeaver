package org.jkiss.dbeaver.model.virtual;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.data.DBDAttributeTransformerDescriptor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBVTransformSettingsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBVTransformSettings#DBVTransformSettings()}
   *   <li>{@link DBVTransformSettings#setCustomTransformer(String)}
   *   <li>{@link DBVTransformSettings#setTransformOptions(Map)}
   *   <li>{@link DBVTransformSettings#getCustomTransformer()}
   *   <li>{@link DBVTransformSettings#getExcludedTransformers()}
   *   <li>{@link DBVTransformSettings#getIncludedTransformers()}
   *   <li>{@link DBVTransformSettings#getTransformOptions()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBVTransformSettings.<init>()",
    "String DBVTransformSettings.getCustomTransformer()",
    "Set DBVTransformSettings.getExcludedTransformers()",
    "Set DBVTransformSettings.getIncludedTransformers()",
    "Map DBVTransformSettings.getTransformOptions()",
    "void DBVTransformSettings.setCustomTransformer(String)",
    "void DBVTransformSettings.setTransformOptions(Map)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBVTransformSettings actualDbvTransformSettings = new DBVTransformSettings();
    actualDbvTransformSettings.setCustomTransformer("Custom Transformer");
    HashMap<String, Object> transformOptions = new HashMap<>();
    actualDbvTransformSettings.setTransformOptions(transformOptions);
    String actualCustomTransformer = actualDbvTransformSettings.getCustomTransformer();
    Set<String> actualExcludedTransformers = actualDbvTransformSettings.getExcludedTransformers();
    Set<String> actualIncludedTransformers = actualDbvTransformSettings.getIncludedTransformers();
    Map<String, Object> actualTransformOptions = actualDbvTransformSettings.getTransformOptions();

    // Assert
    assertEquals("Custom Transformer", actualCustomTransformer);
    assertNull(actualExcludedTransformers);
    assertNull(actualIncludedTransformers);
    assertTrue(actualTransformOptions.isEmpty());
    assertSame(transformOptions, actualTransformOptions);
  }

  /**
   * Test {@link DBVTransformSettings#DBVTransformSettings(DBVTransformSettings)}.
   *
   * <ul>
   *   <li>Given {@code Name}.
   *   <li>Then return TransformOptions size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBVTransformSettings#DBVTransformSettings(DBVTransformSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVTransformSettings.<init>(DBVTransformSettings)"})
  public void testNewDBVTransformSettings_givenName_thenReturnTransformOptionsSizeIsOne() {
    // Arrange
    DBVTransformSettings source = new DBVTransformSettings();
    source.setTransformOption("Name", DBPEvent.RENAME);

    // Act
    DBVTransformSettings actualDbvTransformSettings = new DBVTransformSettings(source);

    // Assert
    assertNull(actualDbvTransformSettings.getCustomTransformer());
    assertNull(actualDbvTransformSettings.getExcludedTransformers());
    assertNull(actualDbvTransformSettings.getIncludedTransformers());
    Map<String, Object> transformOptions = actualDbvTransformSettings.getTransformOptions();
    assertEquals(1, transformOptions.size());
    assertFalse(actualDbvTransformSettings.hasValuableData());
    assertTrue(transformOptions.containsKey("Name"));
    assertTrue(actualDbvTransformSettings.hasTransformOptions());
  }

  /**
   * Test {@link DBVTransformSettings#DBVTransformSettings(DBVTransformSettings)}.
   *
   * <ul>
   *   <li>Then return TransformOptions is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBVTransformSettings#DBVTransformSettings(DBVTransformSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVTransformSettings.<init>(DBVTransformSettings)"})
  public void testNewDBVTransformSettings_thenReturnTransformOptionsIsNull() {
    // Arrange and Act
    DBVTransformSettings actualDbvTransformSettings =
        new DBVTransformSettings(new DBVTransformSettings());

    // Assert
    assertNull(actualDbvTransformSettings.getCustomTransformer());
    assertNull(actualDbvTransformSettings.getTransformOptions());
    assertNull(actualDbvTransformSettings.getExcludedTransformers());
    assertNull(actualDbvTransformSettings.getIncludedTransformers());
    assertFalse(actualDbvTransformSettings.hasTransformOptions());
    assertFalse(actualDbvTransformSettings.hasValuableData());
  }

  /**
   * Test {@link DBVTransformSettings#isExcluded(String)}.
   *
   * <p>Method under test: {@link DBVTransformSettings#isExcluded(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVTransformSettings.isExcluded(String)"})
  public void testIsExcluded() {
    // Arrange, Act and Assert
    assertFalse(new DBVTransformSettings().isExcluded("42"));
  }

  /**
   * Test {@link DBVTransformSettings#isIncluded(String)}.
   *
   * <p>Method under test: {@link DBVTransformSettings#isIncluded(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVTransformSettings.isIncluded(String)"})
  public void testIsIncluded() {
    // Arrange, Act and Assert
    assertFalse(new DBVTransformSettings().isIncluded("42"));
  }

  /**
   * Test {@link DBVTransformSettings#enableTransformer(DBDAttributeTransformerDescriptor,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>Then not {@link DBVTransformSettings#DBVTransformSettings()} hasValuableData.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBVTransformSettings#enableTransformer(DBDAttributeTransformerDescriptor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBVTransformSettings.enableTransformer(DBDAttributeTransformerDescriptor, boolean)"
  })
  public void testEnableTransformer_givenFalse_thenNotDBVTransformSettingsHasValuableData() {
    // Arrange
    DBVTransformSettings dbvTransformSettings = new DBVTransformSettings();

    DBDAttributeTransformerDescriptor transformer = mock(DBDAttributeTransformerDescriptor.class);
    when(transformer.isApplicableByDefault()).thenReturn(false);
    when(transformer.getId()).thenReturn("42");

    // Act
    dbvTransformSettings.enableTransformer(transformer, false);

    // Assert
    verify(transformer).getId();
    verify(transformer).isApplicableByDefault();
    assertFalse(dbvTransformSettings.hasValuableData());
    assertTrue(dbvTransformSettings.getExcludedTransformers().isEmpty());
    assertTrue(dbvTransformSettings.getIncludedTransformers().isEmpty());
  }

  /**
   * Test {@link DBVTransformSettings#enableTransformer(DBDAttributeTransformerDescriptor,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>Then not {@link DBVTransformSettings#DBVTransformSettings()} hasValuableData.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBVTransformSettings#enableTransformer(DBDAttributeTransformerDescriptor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBVTransformSettings.enableTransformer(DBDAttributeTransformerDescriptor, boolean)"
  })
  public void testEnableTransformer_givenTrue_thenNotDBVTransformSettingsHasValuableData() {
    // Arrange
    DBVTransformSettings dbvTransformSettings = new DBVTransformSettings();

    DBDAttributeTransformerDescriptor transformer = mock(DBDAttributeTransformerDescriptor.class);
    when(transformer.isApplicableByDefault()).thenReturn(true);
    when(transformer.getId()).thenReturn("42");

    // Act
    dbvTransformSettings.enableTransformer(transformer, true);

    // Assert
    verify(transformer).getId();
    verify(transformer).isApplicableByDefault();
    assertFalse(dbvTransformSettings.hasValuableData());
    assertTrue(dbvTransformSettings.getExcludedTransformers().isEmpty());
    assertTrue(dbvTransformSettings.getIncludedTransformers().isEmpty());
  }

  /**
   * Test {@link DBVTransformSettings#enableTransformer(DBDAttributeTransformerDescriptor,
   * boolean)}.
   *
   * <ul>
   *   <li>Then {@link DBVTransformSettings#DBVTransformSettings()} ExcludedTransformers size is
   *       one.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBVTransformSettings#enableTransformer(DBDAttributeTransformerDescriptor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBVTransformSettings.enableTransformer(DBDAttributeTransformerDescriptor, boolean)"
  })
  public void testEnableTransformer_thenDBVTransformSettingsExcludedTransformersSizeIsOne() {
    // Arrange
    DBVTransformSettings dbvTransformSettings = new DBVTransformSettings();

    DBDAttributeTransformerDescriptor transformer = mock(DBDAttributeTransformerDescriptor.class);
    when(transformer.isApplicableByDefault()).thenReturn(true);
    when(transformer.getId()).thenReturn("42");

    // Act
    dbvTransformSettings.enableTransformer(transformer, false);

    // Assert
    verify(transformer).getId();
    verify(transformer).isApplicableByDefault();
    Set<String> excludedTransformers = dbvTransformSettings.getExcludedTransformers();
    assertEquals(1, excludedTransformers.size());
    assertTrue(excludedTransformers.contains("42"));
    assertTrue(dbvTransformSettings.getIncludedTransformers().isEmpty());
    assertTrue(dbvTransformSettings.hasValuableData());
  }

  /**
   * Test {@link DBVTransformSettings#enableTransformer(DBDAttributeTransformerDescriptor,
   * boolean)}.
   *
   * <ul>
   *   <li>Then {@link DBVTransformSettings#DBVTransformSettings()} IncludedTransformers size is
   *       one.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBVTransformSettings#enableTransformer(DBDAttributeTransformerDescriptor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBVTransformSettings.enableTransformer(DBDAttributeTransformerDescriptor, boolean)"
  })
  public void testEnableTransformer_thenDBVTransformSettingsIncludedTransformersSizeIsOne() {
    // Arrange
    DBVTransformSettings dbvTransformSettings = new DBVTransformSettings();

    DBDAttributeTransformerDescriptor transformer = mock(DBDAttributeTransformerDescriptor.class);
    when(transformer.isApplicableByDefault()).thenReturn(false);
    when(transformer.getId()).thenReturn("42");

    // Act
    dbvTransformSettings.enableTransformer(transformer, true);

    // Assert
    verify(transformer).getId();
    verify(transformer).isApplicableByDefault();
    Set<String> includedTransformers = dbvTransformSettings.getIncludedTransformers();
    assertEquals(1, includedTransformers.size());
    assertTrue(includedTransformers.contains("42"));
    assertTrue(dbvTransformSettings.getExcludedTransformers().isEmpty());
    assertTrue(dbvTransformSettings.hasValuableData());
  }

  /**
   * Test {@link DBVTransformSettings#setTransformOption(String, Object)}.
   *
   * <p>Method under test: {@link DBVTransformSettings#setTransformOption(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVTransformSettings.setTransformOption(String, Object)"})
  public void testSetTransformOption() {
    // Arrange
    DBVTransformSettings dbvTransformSettings =
        new DBVTransformSettings(new DBVTransformSettings());
    HashMap<String, Object> transformOptions = new HashMap<>();
    dbvTransformSettings.setTransformOptions(transformOptions);
    Object object = DBPEvent.RENAME;

    // Act
    dbvTransformSettings.setTransformOption("Name", object);

    // Assert
    Map<String, Object> transformOptions2 = dbvTransformSettings.getTransformOptions();
    assertEquals(1, transformOptions2.size());
    assertTrue(dbvTransformSettings.hasTransformOptions());
    assertSame(transformOptions, transformOptions2);
    assertSame(object, transformOptions2.get("Name"));
  }

  /**
   * Test {@link DBVTransformSettings#setTransformOption(String, Object)}.
   *
   * <ul>
   *   <li>Then {@link DBVTransformSettings#DBVTransformSettings()} TransformOptions size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBVTransformSettings#setTransformOption(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVTransformSettings.setTransformOption(String, Object)"})
  public void testSetTransformOption_thenDBVTransformSettingsTransformOptionsSizeIsOne() {
    // Arrange
    DBVTransformSettings dbvTransformSettings = new DBVTransformSettings();
    Object object = DBPEvent.RENAME;

    // Act
    dbvTransformSettings.setTransformOption("Name", object);

    // Assert
    Map<String, Object> transformOptions = dbvTransformSettings.getTransformOptions();
    assertEquals(1, transformOptions.size());
    assertTrue(dbvTransformSettings.hasTransformOptions());
    assertSame(object, transformOptions.get("Name"));
  }

  /**
   * Test {@link DBVTransformSettings#hasTransformOptions()}.
   *
   * <p>Method under test: {@link DBVTransformSettings#hasTransformOptions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVTransformSettings.hasTransformOptions()"})
  public void testHasTransformOptions() {
    // Arrange
    DBVTransformSettings dbvTransformSettings =
        new DBVTransformSettings(new DBVTransformSettings());
    dbvTransformSettings.setTransformOptions(new HashMap<>());

    // Act and Assert
    assertFalse(dbvTransformSettings.hasTransformOptions());
  }

  /**
   * Test {@link DBVTransformSettings#hasTransformOptions()}.
   *
   * <ul>
   *   <li>Given {@link DBVTransformSettings#DBVTransformSettings()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBVTransformSettings#hasTransformOptions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVTransformSettings.hasTransformOptions()"})
  public void testHasTransformOptions_givenDBVTransformSettings_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new DBVTransformSettings().hasTransformOptions());
  }

  /**
   * Test {@link DBVTransformSettings#hasTransformOptions()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBVTransformSettings#hasTransformOptions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVTransformSettings.hasTransformOptions()"})
  public void testHasTransformOptions_thenReturnTrue() {
    // Arrange
    DBVTransformSettings dbvTransformSettings = new DBVTransformSettings();
    dbvTransformSettings.setTransformOption("Name", DBPEvent.RENAME);

    // Act and Assert
    assertTrue(dbvTransformSettings.hasTransformOptions());
  }

  /**
   * Test {@link DBVTransformSettings#hasValuableData()}.
   *
   * <ul>
   *   <li>Given {@link DBVTransformSettings#DBVTransformSettings()} CustomTransformer is empty
   *       string.
   * </ul>
   *
   * <p>Method under test: {@link DBVTransformSettings#hasValuableData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVTransformSettings.hasValuableData()"})
  public void testHasValuableData_givenDBVTransformSettingsCustomTransformerIsEmptyString() {
    // Arrange
    DBVTransformSettings dbvTransformSettings = new DBVTransformSettings();
    dbvTransformSettings.setCustomTransformer("");

    // Act and Assert
    assertFalse(dbvTransformSettings.hasValuableData());
  }

  /**
   * Test {@link DBVTransformSettings#hasValuableData()}.
   *
   * <ul>
   *   <li>Given {@link DBVTransformSettings#DBVTransformSettings()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBVTransformSettings#hasValuableData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVTransformSettings.hasValuableData()"})
  public void testHasValuableData_givenDBVTransformSettings_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new DBVTransformSettings().hasValuableData());
  }

  /**
   * Test {@link DBVTransformSettings#hasValuableData()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBVTransformSettings#hasValuableData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVTransformSettings.hasValuableData()"})
  public void testHasValuableData_thenReturnTrue() {
    // Arrange
    DBVTransformSettings dbvTransformSettings = new DBVTransformSettings();
    dbvTransformSettings.setCustomTransformer("Custom Transformer");

    // Act and Assert
    assertTrue(dbvTransformSettings.hasValuableData());
  }

  /**
   * Test {@link DBVTransformSettings#filterTransformers(List)}.
   *
   * <p>Method under test: {@link DBVTransformSettings#filterTransformers(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVTransformSettings.filterTransformers(List)"})
  public void testFilterTransformers() {
    // Arrange
    DBVTransformSettings dbvTransformSettings = new DBVTransformSettings();
    dbvTransformSettings.setCustomTransformer("Custom Transformer");
    ArrayList<DBDAttributeTransformerDescriptor> descriptors = new ArrayList<>();

    // Act
    boolean actualFilterTransformersResult = dbvTransformSettings.filterTransformers(descriptors);

    // Assert
    assertTrue(descriptors.isEmpty());
    assertTrue(actualFilterTransformersResult);
  }

  /**
   * Test {@link DBVTransformSettings#filterTransformers(List)}.
   *
   * <ul>
   *   <li>Given {@link DBVTransformSettings#DBVTransformSettings()} CustomTransformer is empty
   *       string.
   * </ul>
   *
   * <p>Method under test: {@link DBVTransformSettings#filterTransformers(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVTransformSettings.filterTransformers(List)"})
  public void testFilterTransformers_givenDBVTransformSettingsCustomTransformerIsEmptyString() {
    // Arrange
    DBVTransformSettings dbvTransformSettings = new DBVTransformSettings();
    dbvTransformSettings.setCustomTransformer("");
    ArrayList<DBDAttributeTransformerDescriptor> descriptors = new ArrayList<>();

    // Act and Assert
    assertFalse(dbvTransformSettings.filterTransformers(descriptors));
    assertTrue(descriptors.isEmpty());
  }

  /**
   * Test {@link DBVTransformSettings#filterTransformers(List)}.
   *
   * <ul>
   *   <li>Given {@link DBVTransformSettings#DBVTransformSettings()}.
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBVTransformSettings#filterTransformers(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVTransformSettings.filterTransformers(List)"})
  public void testFilterTransformers_givenDBVTransformSettings_whenArrayList_thenReturnFalse() {
    // Arrange
    DBVTransformSettings dbvTransformSettings = new DBVTransformSettings();
    ArrayList<DBDAttributeTransformerDescriptor> descriptors = new ArrayList<>();

    // Act and Assert
    assertFalse(dbvTransformSettings.filterTransformers(descriptors));
    assertTrue(descriptors.isEmpty());
  }

  /**
   * Test {@link DBVTransformSettings#filterTransformers(List)}.
   *
   * <ul>
   *   <li>Then {@link ArrayList#ArrayList()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBVTransformSettings#filterTransformers(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVTransformSettings.filterTransformers(List)"})
  public void testFilterTransformers_thenArrayListSizeIsOne() {
    // Arrange
    DBVTransformSettings dbvTransformSettings = new DBVTransformSettings();
    dbvTransformSettings.setCustomTransformer("42");

    DBDAttributeTransformerDescriptor dbdAttributeTransformerDescriptor =
        mock(DBDAttributeTransformerDescriptor.class);
    when(dbdAttributeTransformerDescriptor.getId()).thenReturn("42");
    when(dbdAttributeTransformerDescriptor.isCustom()).thenReturn(true);

    ArrayList<DBDAttributeTransformerDescriptor> descriptors = new ArrayList<>();
    descriptors.add(dbdAttributeTransformerDescriptor);

    // Act
    boolean actualFilterTransformersResult = dbvTransformSettings.filterTransformers(descriptors);

    // Assert
    verify(dbdAttributeTransformerDescriptor).getId();
    verify(dbdAttributeTransformerDescriptor).isCustom();
    assertEquals(1, descriptors.size());
    assertTrue(actualFilterTransformersResult);
  }

  /**
   * Test {@link DBVTransformSettings#filterTransformers(List)}.
   *
   * <ul>
   *   <li>Then calls {@link DBDAttributeTransformerDescriptor#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVTransformSettings#filterTransformers(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVTransformSettings.filterTransformers(List)"})
  public void testFilterTransformers_thenCallsGetId() {
    // Arrange
    DBVTransformSettings dbvTransformSettings = new DBVTransformSettings();
    dbvTransformSettings.setCustomTransformer("Custom Transformer");

    DBDAttributeTransformerDescriptor dbdAttributeTransformerDescriptor =
        mock(DBDAttributeTransformerDescriptor.class);
    when(dbdAttributeTransformerDescriptor.getId()).thenReturn("42");
    when(dbdAttributeTransformerDescriptor.isCustom()).thenReturn(true);

    ArrayList<DBDAttributeTransformerDescriptor> descriptors = new ArrayList<>();
    descriptors.add(dbdAttributeTransformerDescriptor);

    // Act
    boolean actualFilterTransformersResult = dbvTransformSettings.filterTransformers(descriptors);

    // Assert
    verify(dbdAttributeTransformerDescriptor).getId();
    verify(dbdAttributeTransformerDescriptor).isCustom();
    assertTrue(descriptors.isEmpty());
    assertTrue(actualFilterTransformersResult);
  }
}
