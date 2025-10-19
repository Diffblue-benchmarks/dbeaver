package org.jkiss.dbeaver.model.impl;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.exec.DBCScriptContext;
import org.jkiss.dbeaver.model.struct.DBSDataContainer;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AbstractExecutionSourceDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return SourceDescriptor is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AbstractExecutionSource#AbstractExecutionSource(DBSDataContainer,
   *       DBCExecutionContext, Object)}
   *   <li>{@link AbstractExecutionSource#setScriptContext(DBCScriptContext)}
   *   <li>{@link AbstractExecutionSource#getDataContainer()}
   *   <li>{@link AbstractExecutionSource#getExecutionController()}
   *   <li>{@link AbstractExecutionSource#getScriptContext()}
   *   <li>{@link AbstractExecutionSource#getSourceDescriptor()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AbstractExecutionSource.<init>(DBSDataContainer, DBCExecutionContext, Object)",
    "void AbstractExecutionSource.<init>(DBSDataContainer, DBCExecutionContext, Object, Object)",
    "DBSDataContainer AbstractExecutionSource.getDataContainer()",
    "Object AbstractExecutionSource.getExecutionController()",
    "DBCScriptContext AbstractExecutionSource.getScriptContext()",
    "Object AbstractExecutionSource.getSourceDescriptor()",
    "void AbstractExecutionSource.setScriptContext(DBCScriptContext)"
  })
  public void testGettersAndSetters_thenReturnSourceDescriptorIsNull() {
    // Arrange
    DBSDataContainer dataContainer = mock(DBSDataContainer.class);
    Object object = DBPEvent.RENAME;

    // Act
    AbstractExecutionSource actualAbstractExecutionSource =
        new AbstractExecutionSource(dataContainer, mock(DBCExecutionContext.class), object);
    DBCScriptContext scriptContext = mock(DBCScriptContext.class);
    actualAbstractExecutionSource.setScriptContext(scriptContext);
    DBSDataContainer actualDataContainer = actualAbstractExecutionSource.getDataContainer();
    Object actualExecutionController = actualAbstractExecutionSource.getExecutionController();
    DBCScriptContext actualScriptContext = actualAbstractExecutionSource.getScriptContext();

    // Assert
    assertNull(actualAbstractExecutionSource.getSourceDescriptor());
    assertSame(object, actualExecutionController);
    assertSame(scriptContext, actualScriptContext);
    assertSame(dataContainer, actualDataContainer);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return SourceDescriptor is {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AbstractExecutionSource#AbstractExecutionSource(DBSDataContainer,
   *       DBCExecutionContext, Object, Object)}
   *   <li>{@link AbstractExecutionSource#setScriptContext(DBCScriptContext)}
   *   <li>{@link AbstractExecutionSource#getDataContainer()}
   *   <li>{@link AbstractExecutionSource#getExecutionController()}
   *   <li>{@link AbstractExecutionSource#getScriptContext()}
   *   <li>{@link AbstractExecutionSource#getSourceDescriptor()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AbstractExecutionSource.<init>(DBSDataContainer, DBCExecutionContext, Object)",
    "void AbstractExecutionSource.<init>(DBSDataContainer, DBCExecutionContext, Object, Object)",
    "DBSDataContainer AbstractExecutionSource.getDataContainer()",
    "Object AbstractExecutionSource.getExecutionController()",
    "DBCScriptContext AbstractExecutionSource.getScriptContext()",
    "Object AbstractExecutionSource.getSourceDescriptor()",
    "void AbstractExecutionSource.setScriptContext(DBCScriptContext)"
  })
  public void testGettersAndSetters_thenReturnSourceDescriptorIsRename() {
    // Arrange
    DBSDataContainer dataContainer = mock(DBSDataContainer.class);
    Object object = DBPEvent.RENAME;

    // Act
    AbstractExecutionSource actualAbstractExecutionSource =
        new AbstractExecutionSource(
            dataContainer, mock(DBCExecutionContext.class), DBPEvent.RENAME, object);
    DBCScriptContext scriptContext = mock(DBCScriptContext.class);
    actualAbstractExecutionSource.setScriptContext(scriptContext);
    DBSDataContainer actualDataContainer = actualAbstractExecutionSource.getDataContainer();
    Object actualExecutionController = actualAbstractExecutionSource.getExecutionController();
    DBCScriptContext actualScriptContext = actualAbstractExecutionSource.getScriptContext();

    // Assert
    assertSame(object, actualExecutionController);
    assertSame(object, actualAbstractExecutionSource.getSourceDescriptor());
    assertSame(scriptContext, actualScriptContext);
    assertSame(dataContainer, actualDataContainer);
  }
}
