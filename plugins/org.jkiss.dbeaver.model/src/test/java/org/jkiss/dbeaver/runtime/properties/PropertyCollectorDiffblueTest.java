package org.jkiss.dbeaver.runtime.properties;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PropertyCollectorDiffblueTest {
  /**
   * Test {@link PropertyCollector#PropertyCollector(Object, Object, boolean)}.
   *
   * <p>Method under test: {@link PropertyCollector#PropertyCollector(Object, Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PropertyCollector.<init>(Object, Object, boolean)",
    "void PropertyCollector.<init>(Object, boolean)"
  })
  public void testNewPropertyCollector() {
    // Arrange
    Object object = DBPEvent.RENAME;

    // Act
    PropertyCollector actualPropertyCollector =
        new PropertyCollector(DBPEvent.RENAME, object, true);

    // Assert
    assertTrue(actualPropertyCollector.getChangedPropertiesValues().isEmpty());
    assertTrue(actualPropertyCollector.getEnableFilters());
    assertTrue(actualPropertyCollector.isEnableFilters());
    assertSame(object, actualPropertyCollector.getEditableValue());
    assertSame(object, actualPropertyCollector.getSourceObject());
  }

  /**
   * Test {@link PropertyCollector#PropertyCollector(Object, boolean)}.
   *
   * <p>Method under test: {@link PropertyCollector#PropertyCollector(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PropertyCollector.<init>(Object, Object, boolean)",
    "void PropertyCollector.<init>(Object, boolean)"
  })
  public void testNewPropertyCollector2() {
    // Arrange
    Object object = DBPEvent.RENAME;

    // Act
    PropertyCollector actualPropertyCollector = new PropertyCollector(object, true);

    // Assert
    assertTrue(actualPropertyCollector.getChangedPropertiesValues().isEmpty());
    assertTrue(actualPropertyCollector.getEnableFilters());
    assertTrue(actualPropertyCollector.isEnableFilters());
    assertSame(object, actualPropertyCollector.getEditableValue());
    assertSame(object, actualPropertyCollector.getSourceObject());
  }
}
