package org.jkiss.dbeaver.model.sql.semantics.model.dml;

import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.semantics.model.dml.SQLQuerySelectIntoModel.RowsetSelectionTarget;
import org.jkiss.dbeaver.model.sql.semantics.model.dml.SQLQuerySelectIntoModel.SelectionTargetVisitor;
import org.jkiss.dbeaver.model.sql.semantics.model.dml.SQLQuerySelectIntoModel.ValueSelectionTarget;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLQuerySelectIntoModelDiffblueTest {
  /**
   * Test RowsetSelectionTarget {@link RowsetSelectionTarget#apply(SelectionTargetVisitor)}.
   *
   * <p>Method under test: {@link RowsetSelectionTarget#apply(SelectionTargetVisitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RowsetSelectionTarget.apply(SelectionTargetVisitor)"})
  public void testRowsetSelectionTargetApply() {
    // Arrange
    RowsetSelectionTarget rowsetSelectionTarget = new RowsetSelectionTarget(null);

    SelectionTargetVisitor visitor = mock(SelectionTargetVisitor.class);
    doNothing().when(visitor).visitRowsetTarget(Mockito.<RowsetSelectionTarget>any());

    // Act
    rowsetSelectionTarget.apply(visitor);

    // Assert
    verify(visitor).visitRowsetTarget(isA(RowsetSelectionTarget.class));
  }

  /**
   * Test RowsetSelectionTarget {@link RowsetSelectionTarget#getNode()}.
   *
   * <p>Method under test: {@link RowsetSelectionTarget#getNode()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.sql.semantics.model.SQLQueryNodeModel RowsetSelectionTarget.getNode()"
  })
  public void testRowsetSelectionTargetGetNode() {
    // Arrange, Act and Assert
    assertNull(new RowsetSelectionTarget(null).getNode());
  }

  /**
   * Test ValueSelectionTarget {@link ValueSelectionTarget#apply(SelectionTargetVisitor)}.
   *
   * <p>Method under test: {@link ValueSelectionTarget#apply(SelectionTargetVisitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ValueSelectionTarget.apply(SelectionTargetVisitor)"})
  public void testValueSelectionTargetApply() {
    // Arrange
    ValueSelectionTarget valueSelectionTarget = new ValueSelectionTarget(null);

    SelectionTargetVisitor visitor = mock(SelectionTargetVisitor.class);
    doNothing().when(visitor).visitExpressionTarget(Mockito.<ValueSelectionTarget>any());

    // Act
    valueSelectionTarget.apply(visitor);

    // Assert
    verify(visitor).visitExpressionTarget(isA(ValueSelectionTarget.class));
  }

  /**
   * Test ValueSelectionTarget {@link ValueSelectionTarget#getNode()}.
   *
   * <p>Method under test: {@link ValueSelectionTarget#getNode()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.sql.semantics.model.SQLQueryNodeModel ValueSelectionTarget.getNode()"
  })
  public void testValueSelectionTargetGetNode() {
    // Arrange, Act and Assert
    assertNull(new ValueSelectionTarget(null).getNode());
  }
}
