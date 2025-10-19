package org.jkiss.dbeaver.ext.db2.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ext.db2.model.DB2Alias;
import org.jkiss.dbeaver.ext.db2.model.DB2Schema;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DB2AbstractDropOnlyManagerDiffblueTest {
  /**
   * Test {@link DB2AbstractDropOnlyManager#getMakerOptions(DBPDataSource)}.
   *
   * <p>Method under test: {@link DB2AbstractDropOnlyManager#getMakerOptions(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long DB2AbstractDropOnlyManager.getMakerOptions(DBPDataSource)"})
  public void testGetMakerOptions() {
    // Arrange, Act and Assert
    assertEquals(0L, new DB2AliasManager().getMakerOptions(mock(DBPDataSource.class)));
  }

  /**
   * Test {@link DB2AbstractDropOnlyManager#canCreateObject(Object)}.
   *
   * <ul>
   *   <li>Given {@link DB2AliasManager} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DB2AbstractDropOnlyManager#canCreateObject(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DB2AbstractDropOnlyManager.canCreateObject(Object)"})
  public void testCanCreateObject_givenDB2AliasManager_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new DB2AliasManager().canCreateObject("Container"));
  }

  /**
   * Test {@link DB2AbstractDropOnlyManager#canCreateObject(Object)}.
   *
   * <ul>
   *   <li>Then calls {@link DB2AbstractDropOnlyManager#canCreateObject(Object)}.
   * </ul>
   *
   * <p>Method under test: {@link DB2AbstractDropOnlyManager#canCreateObject(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DB2AbstractDropOnlyManager.canCreateObject(Object)"})
  public void testCanCreateObject_thenCallsCanCreateObject() {
    // Arrange
    DB2AbstractDropOnlyManager<DB2Alias, DB2Schema> db2AbstractDropOnlyManager =
        mock(DB2AbstractDropOnlyManager.class);
    when(db2AbstractDropOnlyManager.canCreateObject(Mockito.<Object>any())).thenReturn(true);

    // Act
    db2AbstractDropOnlyManager.canCreateObject("Container");

    // Assert
    verify(db2AbstractDropOnlyManager).canCreateObject(isA(Object.class));
  }

  /**
   * Test {@link DB2AbstractDropOnlyManager#canEditObject(DBSObject)} with {@code DB2Alias}.
   *
   * <ul>
   *   <li>Given {@link DB2AliasManager} (default constructor).
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DB2AbstractDropOnlyManager#canEditObject(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DB2AbstractDropOnlyManager.canEditObject(DBSObject)"})
  public void testCanEditObjectWithDB2Alias_givenDB2AliasManager_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new DB2AliasManager().canEditObject(null));
  }
}
