package org.jkiss.dbeaver.model.ai;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.TreeMap;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.ai.AIContextSettings.PersistentSettings;
import org.jkiss.dbeaver.model.preferences.DBPPreferenceStore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class AICompletionSettingsDiffblueTest {
  /**
   * Test {@link AICompletionSettings#AICompletionSettings(DBPPreferenceStore,
   * DBPDataSourceContainer)}.
   *
   * <ul>
   *   <li>Given {@code Extension}.
   * </ul>
   *
   * <p>Method under test: {@link AICompletionSettings#AICompletionSettings(DBPPreferenceStore,
   * DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AICompletionSettings.<init>(DBPPreferenceStore, DBPDataSourceContainer)"
  })
  public void testNewAICompletionSettings_givenExtension() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getExtension(Mockito.<String>any())).thenReturn("Extension");

    // Act
    AICompletionSettings actualAiCompletionSettings =
        new AICompletionSettings(null, dataSourceContainer);

    // Assert
    verify(dataSourceContainer).getExtension("ai.assistant");
    assertNull(actualAiCompletionSettings.getCustomObjectIds());
    PersistentSettings persistentSettings = actualAiCompletionSettings.settings;
    assertNull(persistentSettings.objects);
    assertNull(actualAiCompletionSettings.getScope());
    assertNull(persistentSettings.scope);
    assertNull(actualAiCompletionSettings.preferenceStore);
    assertFalse(actualAiCompletionSettings.isMetaTransferConfirmed());
    assertFalse(persistentSettings.confirmed);
    assertSame(dataSourceContainer, actualAiCompletionSettings.getDataSourceContainer());
  }

  /**
   * Test {@link AICompletionSettings#AICompletionSettings(DBPPreferenceStore,
   * DBPDataSourceContainer)}.
   *
   * <ul>
   *   <li>Given {@code Extension}.
   * </ul>
   *
   * <p>Method under test: {@link AICompletionSettings#AICompletionSettings(DBPPreferenceStore,
   * DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AICompletionSettings.<init>(DBPPreferenceStore, DBPDataSourceContainer)"
  })
  public void testNewAICompletionSettings_givenExtension2() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getExtension(Mockito.<String>any())).thenReturn("Extension");

    // Act
    AICompletionSettings actualAiCompletionSettings =
        new AICompletionSettings(null, dataSourceContainer);

    // Assert
    verify(dataSourceContainer).getExtension("ai.assistant");
    assertNull(actualAiCompletionSettings.getCustomObjectIds());
    PersistentSettings persistentSettings = actualAiCompletionSettings.settings;
    assertNull(persistentSettings.objects);
    assertNull(actualAiCompletionSettings.getScope());
    assertNull(persistentSettings.scope);
    assertNull(actualAiCompletionSettings.preferenceStore);
    assertFalse(actualAiCompletionSettings.isMetaTransferConfirmed());
    assertFalse(persistentSettings.confirmed);
    assertSame(dataSourceContainer, actualAiCompletionSettings.getDataSourceContainer());
  }

  /**
   * Test {@link AICompletionSettings#AICompletionSettings(DBPPreferenceStore,
   * DBPDataSourceContainer)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link AICompletionSettings#AICompletionSettings(DBPPreferenceStore,
   * DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AICompletionSettings.<init>(DBPPreferenceStore, DBPDataSourceContainer)"
  })
  public void testNewAICompletionSettings_givenHashMap() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getExtension(Mockito.<String>any())).thenReturn(new HashMap<>());

    // Act
    AICompletionSettings actualAiCompletionSettings =
        new AICompletionSettings(null, dataSourceContainer);

    // Assert
    verify(dataSourceContainer).getExtension("ai.assistant");
    assertNull(actualAiCompletionSettings.getCustomObjectIds());
    PersistentSettings persistentSettings = actualAiCompletionSettings.settings;
    assertNull(persistentSettings.objects);
    assertNull(actualAiCompletionSettings.getScope());
    assertNull(persistentSettings.scope);
    assertNull(actualAiCompletionSettings.preferenceStore);
    assertFalse(actualAiCompletionSettings.isMetaTransferConfirmed());
    assertFalse(persistentSettings.confirmed);
    assertSame(dataSourceContainer, actualAiCompletionSettings.getDataSourceContainer());
  }

  /**
   * Test {@link AICompletionSettings#AICompletionSettings(DBPPreferenceStore,
   * DBPDataSourceContainer)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code 42} is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link AICompletionSettings#AICompletionSettings(DBPPreferenceStore,
   * DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AICompletionSettings.<init>(DBPPreferenceStore, DBPDataSourceContainer)"
  })
  public void testNewAICompletionSettings_givenHashMap42Is42() {
    // Arrange
    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.put("42", "42");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getExtension(Mockito.<String>any())).thenReturn(objectObjectMap);

    // Act
    AICompletionSettings actualAiCompletionSettings =
        new AICompletionSettings(null, dataSourceContainer);

    // Assert
    verify(dataSourceContainer).getExtension("ai.assistant");
    assertNull(actualAiCompletionSettings.getCustomObjectIds());
    PersistentSettings persistentSettings = actualAiCompletionSettings.settings;
    assertNull(persistentSettings.objects);
    assertNull(actualAiCompletionSettings.getScope());
    assertNull(persistentSettings.scope);
    assertNull(actualAiCompletionSettings.preferenceStore);
    assertFalse(actualAiCompletionSettings.isMetaTransferConfirmed());
    assertFalse(persistentSettings.confirmed);
    assertSame(dataSourceContainer, actualAiCompletionSettings.getDataSourceContainer());
  }

  /**
   * Test {@link AICompletionSettings#AICompletionSettings(DBPPreferenceStore,
   * DBPDataSourceContainer)}.
   *
   * <ul>
   *   <li>Given {@link TreeMap#TreeMap()}.
   * </ul>
   *
   * <p>Method under test: {@link AICompletionSettings#AICompletionSettings(DBPPreferenceStore,
   * DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AICompletionSettings.<init>(DBPPreferenceStore, DBPDataSourceContainer)"
  })
  public void testNewAICompletionSettings_givenTreeMap() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getExtension(Mockito.<String>any())).thenReturn(new TreeMap<>());

    // Act
    AICompletionSettings actualAiCompletionSettings =
        new AICompletionSettings(null, dataSourceContainer);

    // Assert
    verify(dataSourceContainer).getExtension("ai.assistant");
    assertNull(actualAiCompletionSettings.getCustomObjectIds());
    PersistentSettings persistentSettings = actualAiCompletionSettings.settings;
    assertNull(persistentSettings.objects);
    assertNull(actualAiCompletionSettings.getScope());
    assertNull(persistentSettings.scope);
    assertNull(actualAiCompletionSettings.preferenceStore);
    assertFalse(actualAiCompletionSettings.isMetaTransferConfirmed());
    assertFalse(persistentSettings.confirmed);
    assertSame(dataSourceContainer, actualAiCompletionSettings.getDataSourceContainer());
  }
}
