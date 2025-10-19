package org.jkiss.dbeaver.model.struct.rdb;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBSManipulationTypeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBSManipulationType#DBSManipulationType(String)}
   *   <li>{@link DBSManipulationType#toString()}
   *   <li>{@link DBSManipulationType#getName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBSManipulationType.<init>(String)",
    "String DBSManipulationType.getName()",
    "String DBSManipulationType.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBSManipulationType actualDbsManipulationType = new DBSManipulationType("Name");
    String actualToStringResult = actualDbsManipulationType.toString();

    // Assert
    assertEquals("Name", actualDbsManipulationType.getName());
    assertEquals("Name", actualToStringResult);
  }

  /**
   * Test {@link DBSManipulationType#getByName(String)}.
   *
   * <ul>
   *   <li>When {@code DELETE}.
   *   <li>Then return Name is {@code DELETE}.
   * </ul>
   *
   * <p>Method under test: {@link DBSManipulationType#getByName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSManipulationType DBSManipulationType.getByName(String)"})
  public void testGetByName_whenDelete_thenReturnNameIsDelete() {
    // Arrange, Act and Assert
    assertEquals("DELETE", DBSManipulationType.getByName("DELETE").getName());
  }

  /**
   * Test {@link DBSManipulationType#getByName(String)}.
   *
   * <ul>
   *   <li>When {@code INSERT}.
   *   <li>Then return Name is {@code INSERT}.
   * </ul>
   *
   * <p>Method under test: {@link DBSManipulationType#getByName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSManipulationType DBSManipulationType.getByName(String)"})
  public void testGetByName_whenInsert_thenReturnNameIsInsert() {
    // Arrange, Act and Assert
    assertEquals("INSERT", DBSManipulationType.getByName("INSERT").getName());
  }

  /**
   * Test {@link DBSManipulationType#getByName(String)}.
   *
   * <ul>
   *   <li>When {@code Name}.
   *   <li>Then return Name is {@code UNKNOWN}.
   * </ul>
   *
   * <p>Method under test: {@link DBSManipulationType#getByName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSManipulationType DBSManipulationType.getByName(String)"})
  public void testGetByName_whenName_thenReturnNameIsUnknown() {
    // Arrange, Act and Assert
    assertEquals("UNKNOWN", DBSManipulationType.getByName("Name").getName());
  }

  /**
   * Test {@link DBSManipulationType#getByName(String)}.
   *
   * <ul>
   *   <li>When {@code UPDATE}.
   *   <li>Then return Name is {@code UPDATE}.
   * </ul>
   *
   * <p>Method under test: {@link DBSManipulationType#getByName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSManipulationType DBSManipulationType.getByName(String)"})
  public void testGetByName_whenUpdate_thenReturnNameIsUpdate() {
    // Arrange, Act and Assert
    assertEquals("UPDATE", DBSManipulationType.getByName("UPDATE").getName());
  }
}
