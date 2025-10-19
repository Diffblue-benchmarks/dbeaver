package org.jkiss.dbeaver.model.logical;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBSLogicalCatalogDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DBSLogicalCatalog}
   *   <li>{@link DBSLogicalCatalog#setSchemas(List)}
   *   <li>{@link DBSLogicalCatalog#getSchemas()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBSLogicalCatalog.<init>()",
    "List DBSLogicalCatalog.getSchemas()",
    "void DBSLogicalCatalog.setSchemas(List)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBSLogicalCatalog actualDbsLogicalCatalog = new DBSLogicalCatalog();
    ArrayList<DBSLogicalSchema> schemas = new ArrayList<>();
    actualDbsLogicalCatalog.setSchemas(schemas);
    List<DBSLogicalSchema> actualSchemas = actualDbsLogicalCatalog.getSchemas();

    // Assert
    assertTrue(actualSchemas.isEmpty());
    assertSame(schemas, actualSchemas);
  }
}
