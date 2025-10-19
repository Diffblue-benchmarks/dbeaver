package org.jkiss.dbeaver.tools.compare.simple;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.model.navigator.DBNDatabaseNode;
import org.jkiss.dbeaver.model.preferences.DBPSettingsSection;
import org.jkiss.dbeaver.tools.compare.simple.CompareObjectsSettings.OutputType;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class CompareObjectsSettingsDiffblueTest {
  /**
   * Test {@link CompareObjectsSettings#CompareObjectsSettings(List)}.
   *
   * <p>Method under test: {@link CompareObjectsSettings#CompareObjectsSettings(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompareObjectsSettings.<init>(List)"})
  public void testNewCompareObjectsSettings() {
    // Arrange and Act
    CompareObjectsSettings actualCompareObjectsSettings =
        new CompareObjectsSettings(new ArrayList<>());

    // Assert
    assertNull(actualCompareObjectsSettings.getOutputFolder());
    assertEquals(OutputType.BROWSER, actualCompareObjectsSettings.getOutputType());
    assertFalse(actualCompareObjectsSettings.isCompareLazyProperties());
    assertFalse(actualCompareObjectsSettings.isCompareOnlyStructure());
    assertFalse(actualCompareObjectsSettings.isCompareScripts());
    assertFalse(actualCompareObjectsSettings.isShowOnlyDifferences());
    assertTrue(actualCompareObjectsSettings.getNodes().isEmpty());
    assertTrue(actualCompareObjectsSettings.isSkipSystemObjects());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CompareObjectsSettings#setCompareLazyProperties(boolean)}
   *   <li>{@link CompareObjectsSettings#setCompareOnlyStructure(boolean)}
   *   <li>{@link CompareObjectsSettings#setCompareScripts(boolean)}
   *   <li>{@link CompareObjectsSettings#setOutputFolder(String)}
   *   <li>{@link CompareObjectsSettings#setOutputType(OutputType)}
   *   <li>{@link CompareObjectsSettings#setShowOnlyDifferences(boolean)}
   *   <li>{@link CompareObjectsSettings#setSkipSystemObjects(boolean)}
   *   <li>{@link CompareObjectsSettings#getNodes()}
   *   <li>{@link CompareObjectsSettings#getOutputFolder()}
   *   <li>{@link CompareObjectsSettings#getOutputType()}
   *   <li>{@link CompareObjectsSettings#isCompareLazyProperties()}
   *   <li>{@link CompareObjectsSettings#isCompareOnlyStructure()}
   *   <li>{@link CompareObjectsSettings#isCompareScripts()}
   *   <li>{@link CompareObjectsSettings#isShowOnlyDifferences()}
   *   <li>{@link CompareObjectsSettings#isSkipSystemObjects()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List CompareObjectsSettings.getNodes()",
    "String CompareObjectsSettings.getOutputFolder()",
    "OutputType CompareObjectsSettings.getOutputType()",
    "boolean CompareObjectsSettings.isCompareLazyProperties()",
    "boolean CompareObjectsSettings.isCompareOnlyStructure()",
    "boolean CompareObjectsSettings.isCompareScripts()",
    "boolean CompareObjectsSettings.isShowOnlyDifferences()",
    "boolean CompareObjectsSettings.isSkipSystemObjects()",
    "void CompareObjectsSettings.setCompareLazyProperties(boolean)",
    "void CompareObjectsSettings.setCompareOnlyStructure(boolean)",
    "void CompareObjectsSettings.setCompareScripts(boolean)",
    "void CompareObjectsSettings.setOutputFolder(String)",
    "void CompareObjectsSettings.setOutputType(OutputType)",
    "void CompareObjectsSettings.setShowOnlyDifferences(boolean)",
    "void CompareObjectsSettings.setSkipSystemObjects(boolean)"
  })
  public void testGettersAndSetters() {
    // Arrange
    ArrayList<DBNDatabaseNode> nodes = new ArrayList<>();
    CompareObjectsSettings compareObjectsSettings = new CompareObjectsSettings(nodes);

    // Act
    compareObjectsSettings.setCompareLazyProperties(true);
    compareObjectsSettings.setCompareOnlyStructure(true);
    compareObjectsSettings.setCompareScripts(true);
    compareObjectsSettings.setOutputFolder("Output Folder");
    compareObjectsSettings.setOutputType(OutputType.BROWSER);
    compareObjectsSettings.setShowOnlyDifferences(true);
    compareObjectsSettings.setSkipSystemObjects(true);
    List<DBNDatabaseNode> actualNodes = compareObjectsSettings.getNodes();
    String actualOutputFolder = compareObjectsSettings.getOutputFolder();
    OutputType actualOutputType = compareObjectsSettings.getOutputType();
    boolean actualIsCompareLazyPropertiesResult = compareObjectsSettings.isCompareLazyProperties();
    boolean actualIsCompareOnlyStructureResult = compareObjectsSettings.isCompareOnlyStructure();
    boolean actualIsCompareScriptsResult = compareObjectsSettings.isCompareScripts();
    boolean actualIsShowOnlyDifferencesResult = compareObjectsSettings.isShowOnlyDifferences();
    boolean actualIsSkipSystemObjectsResult = compareObjectsSettings.isSkipSystemObjects();

    // Assert
    assertEquals("Output Folder", actualOutputFolder);
    assertEquals(OutputType.BROWSER, actualOutputType);
    assertTrue(actualNodes.isEmpty());
    assertTrue(actualIsCompareLazyPropertiesResult);
    assertTrue(actualIsCompareOnlyStructureResult);
    assertTrue(actualIsCompareScriptsResult);
    assertTrue(actualIsShowOnlyDifferencesResult);
    assertTrue(actualIsSkipSystemObjectsResult);
    assertSame(nodes, actualNodes);
  }

  /**
   * Test {@link CompareObjectsSettings#loadFrom(DBPSettingsSection)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link DBPSettingsSection} {@link DBPSettingsSection#get(String)} return {@code
   *       null}.
   *   <li>Then calls {@link DBPSettingsSection#get(String)}.
   * </ul>
   *
   * <p>Method under test: {@link CompareObjectsSettings#loadFrom(DBPSettingsSection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompareObjectsSettings.loadFrom(DBPSettingsSection)"})
  public void testLoadFrom_givenNull_whenDBPSettingsSectionGetReturnNull_thenCallsGet() {
    // Arrange
    CompareObjectsSettings compareObjectsSettings = new CompareObjectsSettings(new ArrayList<>());

    DBPSettingsSection dialogSettings = mock(DBPSettingsSection.class);
    when(dialogSettings.get(Mockito.<String>any())).thenReturn(null);

    // Act
    compareObjectsSettings.loadFrom(dialogSettings);

    // Assert
    verify(dialogSettings, atLeast(1)).get(Mockito.<String>any());
  }

  /**
   * Test OutputType {@link OutputType#getTitle()}.
   *
   * <p>Method under test: {@link OutputType#getTitle()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OutputType.getTitle()"})
  public void testOutputTypeGetTitle() {
    // Arrange, Act and Assert
    assertEquals("Open in browser", OutputType.valueOf("BROWSER").getTitle());
  }

  /**
   * Test {@link CompareObjectsSettings#saveTo(DBPSettingsSection)}.
   *
   * <p>Method under test: {@link CompareObjectsSettings#saveTo(DBPSettingsSection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompareObjectsSettings.saveTo(DBPSettingsSection)"})
  public void testSaveTo() {
    // Arrange
    CompareObjectsSettings compareObjectsSettings = new CompareObjectsSettings(new ArrayList<>());

    DBPSettingsSection dialogSettings = mock(DBPSettingsSection.class);
    doNothing().when(dialogSettings).put(Mockito.<String>any(), anyBoolean());
    doNothing().when(dialogSettings).put(Mockito.<String>any(), Mockito.<String>any());

    // Act
    compareObjectsSettings.saveTo(dialogSettings);

    // Assert
    verify(dialogSettings, atLeast(1)).put(Mockito.<String>any(), anyBoolean());
    verify(dialogSettings, atLeast(1)).put(Mockito.<String>any(), Mockito.<String>any());
  }
}
