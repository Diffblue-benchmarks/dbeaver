package org.jkiss.dbeaver.ext.db2.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DB2PlanConfigDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DB2PlanConfig}
   *   <li>{@link DB2PlanConfig#setSessionUserSchema(String)}
   *   <li>{@link DB2PlanConfig#setTablespace(String)}
   *   <li>{@link DB2PlanConfig#getSessionUserSchema()}
   *   <li>{@link DB2PlanConfig#getTablespace()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DB2PlanConfig.<init>()",
    "String DB2PlanConfig.getSessionUserSchema()",
    "String DB2PlanConfig.getTablespace()",
    "void DB2PlanConfig.setSessionUserSchema(String)",
    "void DB2PlanConfig.setTablespace(String)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DB2PlanConfig actualDb2PlanConfig = new DB2PlanConfig();
    actualDb2PlanConfig.setSessionUserSchema("Session User Schema");
    actualDb2PlanConfig.setTablespace("Tablespace");
    String actualSessionUserSchema = actualDb2PlanConfig.getSessionUserSchema();

    // Assert
    assertEquals("Session User Schema", actualSessionUserSchema);
    assertEquals("Tablespace", actualDb2PlanConfig.getTablespace());
  }
}
