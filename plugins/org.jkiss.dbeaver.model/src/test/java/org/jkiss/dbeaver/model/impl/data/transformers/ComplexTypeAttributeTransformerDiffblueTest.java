package org.jkiss.dbeaver.model.impl.data.transformers;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.data.DBDAttributeBinding;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSBindableDataType;
import org.jkiss.dbeaver.model.struct.DBSContextBoundAttribute;
import org.jkiss.dbeaver.model.struct.DBSDataType;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ComplexTypeAttributeTransformerDiffblueTest {
  /**
   * Test {@link ComplexTypeAttributeTransformer#createNestedTypeBindings(DBCSession,
   * DBDAttributeBinding, List, DBSDataType)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ComplexTypeAttributeTransformer#createNestedTypeBindings(DBCSession, DBDAttributeBinding, List,
   * DBSDataType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ComplexTypeAttributeTransformer.createNestedTypeBindings(DBCSession, DBDAttributeBinding, List, DBSDataType)"
  })
  public void testCreateNestedTypeBindings_givenArrayList() throws DBException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    ArrayList<Object[]> rows = new ArrayList<>();

    DBSBindableDataType dataType = mock(DBSBindableDataType.class);
    Mockito.<List<? extends DBSContextBoundAttribute>>when(
            dataType.bindAttributesToContext(
                Mockito.<DBRProgressMonitor>any(), Mockito.<DBDAttributeBinding>any()))
        .thenReturn(new ArrayList<>());

    // Act
    ComplexTypeAttributeTransformer.createNestedTypeBindings(session, null, rows, dataType);

    // Assert
    verify(session).getProgressMonitor();
    verify(dataType).bindAttributesToContext(isA(DBRProgressMonitor.class), isNull());
  }

  /**
   * Test {@link ComplexTypeAttributeTransformer#createNestedTypeBindings(DBCSession,
   * DBDAttributeBinding, List, DBSDataType)}.
   *
   * <ul>
   *   <li>Given array of {@link Object} with {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ComplexTypeAttributeTransformer#createNestedTypeBindings(DBCSession, DBDAttributeBinding, List,
   * DBSDataType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ComplexTypeAttributeTransformer.createNestedTypeBindings(DBCSession, DBDAttributeBinding, List, DBSDataType)"
  })
  public void testCreateNestedTypeBindings_givenArrayOfObjectWithRename() throws DBException {
    // Arrange
    DBCSession session = mock(DBCSession.class);

    ArrayList<Object[]> rows = new ArrayList<>();
    rows.add(new Object[] {DBPEvent.RENAME});

    // Act and Assert
    ComplexTypeAttributeTransformer.createNestedTypeBindings(
        session, null, rows, mock(DBSDataType.class));
  }

  /**
   * Test {@link ComplexTypeAttributeTransformer#createNestedTypeBindings(DBCSession,
   * DBDAttributeBinding, List, DBSDataType)}.
   *
   * <ul>
   *   <li>Given array of {@link Object} with {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ComplexTypeAttributeTransformer#createNestedTypeBindings(DBCSession, DBDAttributeBinding, List,
   * DBSDataType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ComplexTypeAttributeTransformer.createNestedTypeBindings(DBCSession, DBDAttributeBinding, List, DBSDataType)"
  })
  public void testCreateNestedTypeBindings_givenArrayOfObjectWithRename2() throws DBException {
    // Arrange
    DBCSession session = mock(DBCSession.class);

    ArrayList<Object[]> rows = new ArrayList<>();
    rows.add(new Object[] {DBPEvent.RENAME});
    rows.add(new Object[] {DBPEvent.RENAME});

    // Act and Assert
    ComplexTypeAttributeTransformer.createNestedTypeBindings(
        session, null, rows, mock(DBSDataType.class));
  }

  /**
   * Test {@link ComplexTypeAttributeTransformer#createNestedTypeBindings(DBCSession,
   * DBDAttributeBinding, List, DBSDataType)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ComplexTypeAttributeTransformer#createNestedTypeBindings(DBCSession, DBDAttributeBinding, List,
   * DBSDataType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ComplexTypeAttributeTransformer.createNestedTypeBindings(DBCSession, DBDAttributeBinding, List, DBSDataType)"
  })
  public void testCreateNestedTypeBindings_givenNull() throws DBException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    ArrayList<Object[]> rows = new ArrayList<>();

    DBSBindableDataType dataType = mock(DBSBindableDataType.class);
    Mockito.<List<? extends DBSContextBoundAttribute>>when(
            dataType.bindAttributesToContext(
                Mockito.<DBRProgressMonitor>any(), Mockito.<DBDAttributeBinding>any()))
        .thenReturn(null);

    // Act
    ComplexTypeAttributeTransformer.createNestedTypeBindings(session, null, rows, dataType);

    // Assert
    verify(session).getProgressMonitor();
    verify(dataType).bindAttributesToContext(isA(DBRProgressMonitor.class), isNull());
  }

  /**
   * Test {@link ComplexTypeAttributeTransformer#createNestedTypeBindings(DBCSession,
   * DBDAttributeBinding, List, DBSDataType)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ComplexTypeAttributeTransformer#createNestedTypeBindings(DBCSession, DBDAttributeBinding, List,
   * DBSDataType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ComplexTypeAttributeTransformer.createNestedTypeBindings(DBCSession, DBDAttributeBinding, List, DBSDataType)"
  })
  public void testCreateNestedTypeBindings_thenThrowDBException() throws DBException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    ArrayList<Object[]> rows = new ArrayList<>();

    DBSBindableDataType dataType = mock(DBSBindableDataType.class);
    Mockito.<List<? extends DBSContextBoundAttribute>>when(
            dataType.bindAttributesToContext(
                Mockito.<DBRProgressMonitor>any(), Mockito.<DBDAttributeBinding>any()))
        .thenThrow(new DBException("An error occurred"));

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            ComplexTypeAttributeTransformer.createNestedTypeBindings(
                session, null, rows, dataType));
    verify(session).getProgressMonitor();
    verify(dataType).bindAttributesToContext(isA(DBRProgressMonitor.class), isNull());
  }

  /**
   * Test {@link ComplexTypeAttributeTransformer#createNestedTypeBindings(DBCSession,
   * DBDAttributeBinding, List, DBSDataType)}.
   *
   * <ul>
   *   <li>When {@link DBCSession}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link
   * ComplexTypeAttributeTransformer#createNestedTypeBindings(DBCSession, DBDAttributeBinding, List,
   * DBSDataType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ComplexTypeAttributeTransformer.createNestedTypeBindings(DBCSession, DBDAttributeBinding, List, DBSDataType)"
  })
  public void testCreateNestedTypeBindings_whenDBCSession_thenDoesNotThrow() throws DBException {
    // Arrange
    DBCSession session = mock(DBCSession.class);

    // Act and Assert
    ComplexTypeAttributeTransformer.createNestedTypeBindings(
        session, null, new ArrayList<>(), mock(DBSDataType.class));
  }
}
