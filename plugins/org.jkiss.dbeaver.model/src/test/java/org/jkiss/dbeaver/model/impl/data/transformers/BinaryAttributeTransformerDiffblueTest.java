package org.jkiss.dbeaver.model.impl.data.transformers;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.data.DBDAttributeBinding;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BinaryAttributeTransformerDiffblueTest {
  /**
   * Test {@link BinaryAttributeTransformer#transformAttribute(DBCSession, DBDAttributeBinding,
   * List, Map)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource} {@link DBPDataSource#getSQLDialect()} throw {@link
   *       RuntimeException#RuntimeException()}.
   * </ul>
   *
   * <p>Method under test: {@link BinaryAttributeTransformer#transformAttribute(DBCSession,
   * DBDAttributeBinding, List, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BinaryAttributeTransformer.transformAttribute(DBCSession, DBDAttributeBinding, List, Map)"
  })
  public void testTransformAttribute_givenDBPDataSourceGetSQLDialectThrowRuntimeException()
      throws DBException {
    // Arrange
    BinaryAttributeTransformer binaryAttributeTransformer = new BinaryAttributeTransformer();

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenThrow(new RuntimeException());

    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(dbpDataSource);
    ArrayList<Object[]> rows = new ArrayList<>();

    HashMap<String, Object> options = new HashMap<>();
    options.put("format", "native");

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> binaryAttributeTransformer.transformAttribute(session, null, rows, options));
    verify(dbpDataSource).getSQLDialect();
    verify(session).getDataSource();
  }

  /**
   * Test {@link BinaryAttributeTransformer#transformAttribute(DBCSession, DBDAttributeBinding,
   * List, Map)}.
   *
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException()}.
   * </ul>
   *
   * <p>Method under test: {@link BinaryAttributeTransformer#transformAttribute(DBCSession,
   * DBDAttributeBinding, List, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BinaryAttributeTransformer.transformAttribute(DBCSession, DBDAttributeBinding, List, Map)"
  })
  public void testTransformAttribute_givenRuntimeException() throws DBException {
    // Arrange
    BinaryAttributeTransformer binaryAttributeTransformer = new BinaryAttributeTransformer();

    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenThrow(new RuntimeException());
    ArrayList<Object[]> rows = new ArrayList<>();

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> binaryAttributeTransformer.transformAttribute(session, null, rows, new HashMap<>()));
    verify(session).getDataSource();
  }

  /**
   * Test {@link BinaryAttributeTransformer#transformAttribute(DBCSession, DBDAttributeBinding,
   * List, Map)}.
   *
   * <ul>
   *   <li>Then calls {@link SQLDialect#getNativeBinaryFormatter()}.
   * </ul>
   *
   * <p>Method under test: {@link BinaryAttributeTransformer#transformAttribute(DBCSession,
   * DBDAttributeBinding, List, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BinaryAttributeTransformer.transformAttribute(DBCSession, DBDAttributeBinding, List, Map)"
  })
  public void testTransformAttribute_thenCallsGetNativeBinaryFormatter() throws DBException {
    // Arrange
    BinaryAttributeTransformer binaryAttributeTransformer = new BinaryAttributeTransformer();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getNativeBinaryFormatter()).thenThrow(new RuntimeException());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(dbpDataSource);
    ArrayList<Object[]> rows = new ArrayList<>();

    HashMap<String, Object> options = new HashMap<>();
    options.put("format", "native");

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> binaryAttributeTransformer.transformAttribute(session, null, rows, options));
    verify(dbpDataSource).getSQLDialect();
    verify(session).getDataSource();
    verify(sqlDialect).getNativeBinaryFormatter();
  }
}
