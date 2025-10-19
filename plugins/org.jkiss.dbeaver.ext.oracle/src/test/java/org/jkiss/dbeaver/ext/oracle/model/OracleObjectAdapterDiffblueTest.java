package org.jkiss.dbeaver.ext.oracle.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ext.oracle.model.source.OracleSourceObject;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.DBPScriptObjectExt;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OracleObjectAdapterDiffblueTest {
  /**
   * Test new {@link OracleObjectAdapter} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link OracleObjectAdapter}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OracleObjectAdapter.<init>()"})
  public void testNewOracleObjectAdapter() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new Class[] {
          OracleSourceObject.class,
          OracleProcedurePackaged.class,
          DBPScriptObjectExt.class,
          OracleSchedulerJob.class
        },
        new OracleObjectAdapter().getAdapterList());
  }

  /**
   * Test {@link OracleObjectAdapter#getAdapter(Object, Class)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OracleObjectAdapter#getAdapter(Object, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OracleObjectAdapter.getAdapter(Object, Class)"})
  public void testGetAdapter_whenJavaLangObject_thenReturnNull() {
    // Arrange
    OracleObjectAdapter oracleObjectAdapter = new OracleObjectAdapter();
    Class<Object> adapterType = Object.class;

    // Act and Assert
    assertNull(oracleObjectAdapter.getAdapter(DBPEvent.RENAME, adapterType));
  }

  /**
   * Test {@link OracleObjectAdapter#getAdapter(Object, Class)}.
   *
   * <ul>
   *   <li>When {@code DBSObject}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OracleObjectAdapter#getAdapter(Object, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OracleObjectAdapter.getAdapter(Object, Class)"})
  public void testGetAdapter_whenOrgJkissDbeaverModelStructDBSObject_thenReturnNull() {
    // Arrange
    OracleObjectAdapter oracleObjectAdapter = new OracleObjectAdapter();
    Class<DBSObject> adapterType = DBSObject.class;

    // Act and Assert
    assertNull(oracleObjectAdapter.getAdapter(DBPEvent.RENAME, adapterType));
  }

  /**
   * Test {@link OracleObjectAdapter#getAdapterList()}.
   *
   * <p>Method under test: {@link OracleObjectAdapter#getAdapterList()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class[] OracleObjectAdapter.getAdapterList()"})
  public void testGetAdapterList() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new Class[] {
          OracleSourceObject.class,
          OracleProcedurePackaged.class,
          DBPScriptObjectExt.class,
          OracleSchedulerJob.class
        },
        new OracleObjectAdapter().getAdapterList());
  }
}
