package org.jkiss.dbeaver.model.runtime.features;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBRFeatureRegistryDiffblueTest {
  /**
   * Test {@link DBRFeatureRegistry#getInstance()}.
   *
   * <p>Method under test: {@link DBRFeatureRegistry#getInstance()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBRFeatureRegistry DBRFeatureRegistry.getInstance()"})
  public void testGetInstance() {
    // Arrange, Act and Assert
    assertTrue(DBRFeatureRegistry.getInstance().getAllFeatures().isEmpty());
  }

  /**
   * Test {@link DBRFeatureRegistry#getAllFeatures()}.
   *
   * <p>Method under test: {@link DBRFeatureRegistry#getAllFeatures()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.List DBRFeatureRegistry.getAllFeatures()"})
  public void testGetAllFeatures() {
    // Arrange, Act and Assert
    assertTrue(DBRFeatureRegistry.getInstance().getAllFeatures().isEmpty());
  }

  /**
   * Test {@link DBRFeatureRegistry#findCommandFeature(String)}.
   *
   * <p>Method under test: {@link DBRFeatureRegistry#findCommandFeature(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.runtime.features.DBRFeature DBRFeatureRegistry.findCommandFeature(String)"
  })
  public void testFindCommandFeature() {
    // Arrange, Act and Assert
    assertNull(DBRFeatureRegistry.getInstance().findCommandFeature("42"));
  }
}
