package org.jkiss.dbeaver.registry.data.hints;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ValueHintProviderConfigurationDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ValueHintProviderConfiguration#ValueHintProviderConfiguration(String)}
   *   <li>{@link ValueHintProviderConfiguration#setEnabled(boolean)}
   *   <li>{@link ValueHintProviderConfiguration#setParameters(Map)}
   *   <li>{@link ValueHintProviderConfiguration#getHintProviderDescriptor()}
   *   <li>{@link ValueHintProviderConfiguration#getParameters()}
   *   <li>{@link ValueHintProviderConfiguration#isEnabled()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ValueHintProviderConfiguration.<init>(String)",
    "String ValueHintProviderConfiguration.getHintProviderDescriptor()",
    "Map ValueHintProviderConfiguration.getParameters()",
    "boolean ValueHintProviderConfiguration.isEnabled()",
    "void ValueHintProviderConfiguration.setEnabled(boolean)",
    "void ValueHintProviderConfiguration.setParameters(Map)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    ValueHintProviderConfiguration actualValueHintProviderConfiguration =
        new ValueHintProviderConfiguration("Hint Provider Descriptor");
    actualValueHintProviderConfiguration.setEnabled(true);
    HashMap<String, Object> parameters = new HashMap<>();
    actualValueHintProviderConfiguration.setParameters(parameters);
    String actualHintProviderDescriptor =
        actualValueHintProviderConfiguration.getHintProviderDescriptor();
    Map<String, Object> actualParameters = actualValueHintProviderConfiguration.getParameters();
    boolean actualIsEnabledResult = actualValueHintProviderConfiguration.isEnabled();

    // Assert
    assertEquals("Hint Provider Descriptor", actualHintProviderDescriptor);
    assertTrue(actualParameters.isEmpty());
    assertTrue(actualIsEnabledResult);
    assertSame(parameters, actualParameters);
  }
}
