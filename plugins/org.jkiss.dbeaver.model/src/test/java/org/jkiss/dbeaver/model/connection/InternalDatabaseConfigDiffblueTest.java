package org.jkiss.dbeaver.model.connection;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.connection.InternalDatabaseConfig.Pool;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class InternalDatabaseConfigDiffblueTest {
  /**
   * Test Pool getters and setters.
   *
   * <ul>
   *   <li>Then return ValidationQuery is {@code SELECT 1}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Pool#Pool()}
   *   <li>{@link Pool#getMaxConnections()}
   *   <li>{@link Pool#getMaxIdleConnections()}
   *   <li>{@link Pool#getMinIdleConnections()}
   *   <li>{@link Pool#getValidationQuery()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Pool.<init>()",
    "void Pool.<init>(int, int, int, String)",
    "int Pool.getMaxConnections()",
    "int Pool.getMaxIdleConnections()",
    "int Pool.getMinIdleConnections()",
    "String Pool.getValidationQuery()"
  })
  public void testPoolGettersAndSetters_thenReturnValidationQueryIsSelect1() {
    // Arrange and Act
    Pool actualPool = new Pool();
    int actualMaxConnections = actualPool.getMaxConnections();
    int actualMaxIdleConnections = actualPool.getMaxIdleConnections();
    int actualMinIdleConnections = actualPool.getMinIdleConnections();

    // Assert
    assertEquals("SELECT 1", actualPool.getValidationQuery());
    assertEquals(10, actualMaxIdleConnections);
    assertEquals(100, actualMaxConnections);
    assertEquals(4, actualMinIdleConnections);
  }

  /**
   * Test Pool getters and setters.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code Validation Query}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Pool#Pool(int, int, int, String)}
   *   <li>{@link Pool#getMaxConnections()}
   *   <li>{@link Pool#getMaxIdleConnections()}
   *   <li>{@link Pool#getMinIdleConnections()}
   *   <li>{@link Pool#getValidationQuery()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Pool.<init>()",
    "void Pool.<init>(int, int, int, String)",
    "int Pool.getMaxConnections()",
    "int Pool.getMaxIdleConnections()",
    "int Pool.getMinIdleConnections()",
    "String Pool.getValidationQuery()"
  })
  public void testPoolGettersAndSetters_whenOne_thenReturnValidationQuery() {
    // Arrange and Act
    Pool actualPool = new Pool(1, 1, 3, "Validation Query");
    int actualMaxConnections = actualPool.getMaxConnections();
    int actualMaxIdleConnections = actualPool.getMaxIdleConnections();
    int actualMinIdleConnections = actualPool.getMinIdleConnections();

    // Assert
    assertEquals("Validation Query", actualPool.getValidationQuery());
    assertEquals(1, actualMaxIdleConnections);
    assertEquals(1, actualMinIdleConnections);
    assertEquals(3, actualMaxConnections);
  }
}
