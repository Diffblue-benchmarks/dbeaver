package org.jkiss.dbeaver.model.erd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.impl.preferences.BundlePreferenceStore;
import org.jkiss.dbeaver.model.preferences.DBPPreferenceStore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ERDAttributeVisibilityDiffblueTest {
  /**
   * Test {@link ERDAttributeVisibility#getTitle()}.
   *
   * <p>Method under test: {@link ERDAttributeVisibility#getTitle()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ERDAttributeVisibility.getTitle()"})
  public void testGetTitle() {
    // Arrange, Act and Assert
    assertEquals("All", ERDAttributeVisibility.valueOf("ALL").getTitle());
  }

  /**
   * Test {@link ERDAttributeVisibility#getDefaultVisibility(DBPPreferenceStore)}.
   *
   * <ul>
   *   <li>Given empty string.
   * </ul>
   *
   * <p>Method under test: {@link ERDAttributeVisibility#getDefaultVisibility(DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ERDAttributeVisibility ERDAttributeVisibility.getDefaultVisibility(DBPPreferenceStore)"
  })
  public void testGetDefaultVisibility_givenEmptyString() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("");

    // Act
    ERDAttributeVisibility actualDefaultVisibility =
        ERDAttributeVisibility.getDefaultVisibility(store);

    // Assert
    verify(store).getString("erd.attr.visibility");
    assertEquals(ERDAttributeVisibility.ALL, actualDefaultVisibility);
  }

  /**
   * Test {@link ERDAttributeVisibility#getDefaultVisibility(DBPPreferenceStore)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>Then calls {@link DBPPreferenceStore#getString(String)}.
   * </ul>
   *
   * <p>Method under test: {@link ERDAttributeVisibility#getDefaultVisibility(DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ERDAttributeVisibility ERDAttributeVisibility.getDefaultVisibility(DBPPreferenceStore)"
  })
  public void testGetDefaultVisibility_givenNull_thenCallsGetString() {
    // Arrange
    DBPPreferenceStore store = mock(DBPPreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn(null);

    // Act
    ERDAttributeVisibility actualDefaultVisibility =
        ERDAttributeVisibility.getDefaultVisibility(store);

    // Assert
    verify(store).getString("erd.attr.visibility");
    assertEquals(ERDAttributeVisibility.ALL, actualDefaultVisibility);
  }

  /**
   * Test {@link ERDAttributeVisibility#getDefaultVisibility(DBPPreferenceStore)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link ERDAttributeVisibility#getDefaultVisibility(DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ERDAttributeVisibility ERDAttributeVisibility.getDefaultVisibility(DBPPreferenceStore)"
  })
  public void testGetDefaultVisibility_givenString() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("String");

    // Act
    ERDAttributeVisibility actualDefaultVisibility =
        ERDAttributeVisibility.getDefaultVisibility(store);

    // Assert
    verify(store).getString("erd.attr.visibility");
    assertEquals(ERDAttributeVisibility.ALL, actualDefaultVisibility);
  }

  /**
   * Test {@link ERDAttributeVisibility#isHideAttributeAssociations(DBPPreferenceStore)}.
   *
   * <ul>
   *   <li>Given empty string.
   * </ul>
   *
   * <p>Method under test: {@link
   * ERDAttributeVisibility#isHideAttributeAssociations(DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ERDAttributeVisibility.isHideAttributeAssociations(DBPPreferenceStore)"
  })
  public void testIsHideAttributeAssociations_givenEmptyString() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("");

    // Act
    boolean actualIsHideAttributeAssociationsResult =
        ERDAttributeVisibility.isHideAttributeAssociations(store);

    // Assert
    verify(store).getString("erd.attr.visibility");
    assertFalse(actualIsHideAttributeAssociationsResult);
  }

  /**
   * Test {@link ERDAttributeVisibility#isHideAttributeAssociations(DBPPreferenceStore)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>Then calls {@link DBPPreferenceStore#getString(String)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ERDAttributeVisibility#isHideAttributeAssociations(DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ERDAttributeVisibility.isHideAttributeAssociations(DBPPreferenceStore)"
  })
  public void testIsHideAttributeAssociations_givenNull_thenCallsGetString() {
    // Arrange
    DBPPreferenceStore store = mock(DBPPreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn(null);

    // Act
    boolean actualIsHideAttributeAssociationsResult =
        ERDAttributeVisibility.isHideAttributeAssociations(store);

    // Assert
    verify(store).getString("erd.attr.visibility");
    assertFalse(actualIsHideAttributeAssociationsResult);
  }

  /**
   * Test {@link ERDAttributeVisibility#isHideAttributeAssociations(DBPPreferenceStore)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ERDAttributeVisibility#isHideAttributeAssociations(DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ERDAttributeVisibility.isHideAttributeAssociations(DBPPreferenceStore)"
  })
  public void testIsHideAttributeAssociations_givenString() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("String");

    // Act
    boolean actualIsHideAttributeAssociationsResult =
        ERDAttributeVisibility.isHideAttributeAssociations(store);

    // Assert
    verify(store).getString("erd.attr.visibility");
    assertFalse(actualIsHideAttributeAssociationsResult);
  }

  /**
   * Test {@link ERDAttributeVisibility#setDefaultVisibility(DBPPreferenceStore,
   * ERDAttributeVisibility)}.
   *
   * <ul>
   *   <li>Then calls {@link BundlePreferenceStore#setValue(String, String)}.
   * </ul>
   *
   * <p>Method under test: {@link ERDAttributeVisibility#setDefaultVisibility(DBPPreferenceStore,
   * ERDAttributeVisibility)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDAttributeVisibility.setDefaultVisibility(DBPPreferenceStore, ERDAttributeVisibility)"
  })
  public void testSetDefaultVisibility_thenCallsSetValue() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    doNothing().when(store).setValue(Mockito.<String>any(), Mockito.<String>any());

    // Act
    ERDAttributeVisibility.setDefaultVisibility(store, ERDAttributeVisibility.ALL);

    // Assert
    verify(store).setValue("erd.attr.visibility", "ALL");
  }
}
