package org.jkiss.dbeaver.model.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBTTaskContextDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DBTTaskContext}
   *   <li>{@link DBTTaskContext#setDefaultCatalog(String)}
   *   <li>{@link DBTTaskContext#setDefaultSchema(String)}
   *   <li>{@link DBTTaskContext#setTransactionIsolation(int)}
   *   <li>{@link DBTTaskContext#setAutoCommit(boolean)}
   *   <li>{@link DBTTaskContext#getDefaultCatalog()}
   *   <li>{@link DBTTaskContext#getDefaultSchema()}
   *   <li>{@link DBTTaskContext#getTransactionIsolation()}
   *   <li>{@link DBTTaskContext#isAutoCommit()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBTTaskContext.<init>()",
    "String DBTTaskContext.getDefaultCatalog()",
    "String DBTTaskContext.getDefaultSchema()",
    "int DBTTaskContext.getTransactionIsolation()",
    "boolean DBTTaskContext.isAutoCommit()",
    "void DBTTaskContext.setAutoCommit(boolean)",
    "void DBTTaskContext.setDefaultCatalog(String)",
    "void DBTTaskContext.setDefaultSchema(String)",
    "void DBTTaskContext.setTransactionIsolation(int)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBTTaskContext actualDbtTaskContext = new DBTTaskContext();
    actualDbtTaskContext.setDefaultCatalog("Default Catalog");
    actualDbtTaskContext.setDefaultSchema("Default Schema");
    actualDbtTaskContext.setTransactionIsolation(1);
    actualDbtTaskContext.setAutoCommit(true);
    String actualDefaultCatalog = actualDbtTaskContext.getDefaultCatalog();
    String actualDefaultSchema = actualDbtTaskContext.getDefaultSchema();
    int actualTransactionIsolation = actualDbtTaskContext.getTransactionIsolation();

    // Assert
    assertEquals("Default Catalog", actualDefaultCatalog);
    assertEquals("Default Schema", actualDefaultSchema);
    assertEquals(1, actualTransactionIsolation);
    assertTrue(actualDbtTaskContext.isAutoCommit());
  }
}
