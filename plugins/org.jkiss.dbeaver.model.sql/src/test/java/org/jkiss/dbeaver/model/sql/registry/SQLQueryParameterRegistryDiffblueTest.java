package org.jkiss.dbeaver.model.sql.registry;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.registry.SQLQueryParameterRegistry.ParameterInfo;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLQueryParameterRegistryDiffblueTest {
  /**
   * Test ParameterInfo {@link ParameterInfo#ParameterInfo(String, String)}.
   *
   * <p>Method under test: {@link ParameterInfo#ParameterInfo(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ParameterInfo.<init>(String, String)"})
  public void testParameterInfoNewParameterInfo() {
    // Arrange and Act
    ParameterInfo actualParameterInfo = new ParameterInfo("Name", "42");

    // Assert
    assertEquals("42", actualParameterInfo.value);
    assertEquals("Name", actualParameterInfo.name);
  }
}
