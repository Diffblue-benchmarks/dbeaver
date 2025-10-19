package org.jkiss.dbeaver.model.preferences;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.preferences.DBPPreferenceListener.PreferenceChangeEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBPPreferenceListenerDiffblueTest {
  /**
   * Test PreferenceChangeEvent getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PreferenceChangeEvent#PreferenceChangeEvent(Object, String, Object, Object)}
   *   <li>{@link PreferenceChangeEvent#getNewValue()}
   *   <li>{@link PreferenceChangeEvent#getOldValue()}
   *   <li>{@link PreferenceChangeEvent#getProperty()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PreferenceChangeEvent.<init>(Object, String, Object, Object)",
    "Object PreferenceChangeEvent.getNewValue()",
    "Object PreferenceChangeEvent.getOldValue()",
    "String PreferenceChangeEvent.getProperty()"
  })
  public void testPreferenceChangeEventGettersAndSetters() {
    // Arrange
    Object object = DBPEvent.RENAME;

    // Act
    PreferenceChangeEvent actualPreferenceChangeEvent =
        new PreferenceChangeEvent(DBPEvent.RENAME, "Property", DBPEvent.RENAME, object);
    Object actualNewValue = actualPreferenceChangeEvent.getNewValue();
    Object actualOldValue = actualPreferenceChangeEvent.getOldValue();

    // Assert
    assertEquals("Property", actualPreferenceChangeEvent.getProperty());
    assertSame(object, actualPreferenceChangeEvent.getSource());
    assertSame(object, actualNewValue);
    assertSame(object, actualOldValue);
  }
}
