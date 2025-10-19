package org.jkiss.dbeaver.model.struct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPImage;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBSEntityTypeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBSEntityType#DBSEntityType(String, String, DBPImage, boolean)}
   *   <li>{@link DBSEntityType#toString()}
   *   <li>{@link DBSEntityType#getIcon()}
   *   <li>{@link DBSEntityType#getId()}
   *   <li>{@link DBSEntityType#getName()}
   *   <li>{@link DBSEntityType#isPhysical()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBSEntityType.<init>(String, String, DBPImage, boolean)",
    "DBPImage DBSEntityType.getIcon()",
    "String DBSEntityType.getId()",
    "String DBSEntityType.getName()",
    "boolean DBSEntityType.isPhysical()",
    "String DBSEntityType.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBPImage icon = mock(DBPImage.class);

    // Act
    DBSEntityType actualDbsEntityType = new DBSEntityType("42", "Name", icon, true);
    String actualToStringResult = actualDbsEntityType.toString();
    DBPImage actualIcon = actualDbsEntityType.getIcon();
    String actualId = actualDbsEntityType.getId();
    String actualName = actualDbsEntityType.getName();

    // Assert
    assertEquals("42", actualId);
    assertEquals("Name", actualName);
    assertEquals("Name", actualToStringResult);
    assertTrue(actualDbsEntityType.isPhysical());
    assertSame(icon, actualIcon);
  }
}
