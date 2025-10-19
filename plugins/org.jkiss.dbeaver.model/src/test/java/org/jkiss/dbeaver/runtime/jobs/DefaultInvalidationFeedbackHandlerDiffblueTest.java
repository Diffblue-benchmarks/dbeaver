package org.jkiss.dbeaver.runtime.jobs;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.runtime.jobs.InvalidateJob.ContextInvalidateResult;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DefaultInvalidationFeedbackHandlerDiffblueTest {
  /**
   * Test {@link DefaultInvalidationFeedbackHandler#confirmInvalidate(Set)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceContainer}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultInvalidationFeedbackHandler#confirmInvalidate(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DefaultInvalidationFeedbackHandler.confirmInvalidate(Set)"})
  public void testConfirmInvalidate_givenDBPDataSourceContainer() {
    // Arrange
    DefaultInvalidationFeedbackHandler defaultInvalidationFeedbackHandler =
        new DefaultInvalidationFeedbackHandler();

    HashSet<DBPDataSourceContainer> containersToInvalidate = new HashSet<>();
    containersToInvalidate.add(mock(DBPDataSourceContainer.class));

    // Act and Assert
    assertTrue(defaultInvalidationFeedbackHandler.confirmInvalidate(containersToInvalidate));
  }

  /**
   * Test {@link DefaultInvalidationFeedbackHandler#confirmInvalidate(Set)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceContainer}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultInvalidationFeedbackHandler#confirmInvalidate(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DefaultInvalidationFeedbackHandler.confirmInvalidate(Set)"})
  public void testConfirmInvalidate_givenDBPDataSourceContainer2() {
    // Arrange
    DefaultInvalidationFeedbackHandler defaultInvalidationFeedbackHandler =
        new DefaultInvalidationFeedbackHandler();

    HashSet<DBPDataSourceContainer> containersToInvalidate = new HashSet<>();
    containersToInvalidate.add(mock(DBPDataSourceContainer.class));
    containersToInvalidate.add(mock(DBPDataSourceContainer.class));

    // Act and Assert
    assertTrue(defaultInvalidationFeedbackHandler.confirmInvalidate(containersToInvalidate));
  }

  /**
   * Test {@link DefaultInvalidationFeedbackHandler#confirmInvalidate(Set)}.
   *
   * <ul>
   *   <li>When {@link HashSet#HashSet()}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultInvalidationFeedbackHandler#confirmInvalidate(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DefaultInvalidationFeedbackHandler.confirmInvalidate(Set)"})
  public void testConfirmInvalidate_whenHashSet() {
    // Arrange
    DefaultInvalidationFeedbackHandler defaultInvalidationFeedbackHandler =
        new DefaultInvalidationFeedbackHandler();

    // Act and Assert
    assertTrue(defaultInvalidationFeedbackHandler.confirmInvalidate(new HashSet<>()));
  }

  /**
   * Test {@link DefaultInvalidationFeedbackHandler#onInvalidateSuccess(DBPDataSourceContainer,
   * Collection)}.
   *
   * <ul>
   *   <li>Given newError {@link DBPDataSource} and {@link Exception#Exception()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DefaultInvalidationFeedbackHandler#onInvalidateSuccess(DBPDataSourceContainer, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DefaultInvalidationFeedbackHandler.onInvalidateSuccess(DBPDataSourceContainer, Collection)"
  })
  public void testOnInvalidateSuccess_givenNewErrorDBPDataSourceAndException() {
    // Arrange
    DefaultInvalidationFeedbackHandler defaultInvalidationFeedbackHandler =
        new DefaultInvalidationFeedbackHandler();

    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    when(container.getDataSource()).thenReturn(mock(DBPDataSource.class));

    ArrayList<ContextInvalidateResult> results = new ArrayList<>();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    results.add(ContextInvalidateResult.newError(dataSource, new Exception()));

    // Act
    defaultInvalidationFeedbackHandler.onInvalidateSuccess(container, results);

    // Assert
    verify(container).getDataSource();
  }

  /**
   * Test {@link DefaultInvalidationFeedbackHandler#onInvalidateSuccess(DBPDataSourceContainer,
   * Collection)}.
   *
   * <ul>
   *   <li>Given newSuccess {@link DBPDataSource}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DefaultInvalidationFeedbackHandler#onInvalidateSuccess(DBPDataSourceContainer, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DefaultInvalidationFeedbackHandler.onInvalidateSuccess(DBPDataSourceContainer, Collection)"
  })
  public void testOnInvalidateSuccess_givenNewSuccessDBPDataSource() {
    // Arrange
    DefaultInvalidationFeedbackHandler defaultInvalidationFeedbackHandler =
        new DefaultInvalidationFeedbackHandler();

    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    when(container.getDataSource()).thenReturn(mock(DBPDataSource.class));

    ArrayList<ContextInvalidateResult> results = new ArrayList<>();
    results.add(ContextInvalidateResult.newSuccess(mock(DBPDataSource.class)));

    // Act
    defaultInvalidationFeedbackHandler.onInvalidateSuccess(container, results);

    // Assert
    verify(container).getDataSource();
  }

  /**
   * Test {@link DefaultInvalidationFeedbackHandler#onInvalidateSuccess(DBPDataSourceContainer,
   * Collection)}.
   *
   * <ul>
   *   <li>Given newSuccess {@link DBPDataSource}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DefaultInvalidationFeedbackHandler#onInvalidateSuccess(DBPDataSourceContainer, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DefaultInvalidationFeedbackHandler.onInvalidateSuccess(DBPDataSourceContainer, Collection)"
  })
  public void testOnInvalidateSuccess_givenNewSuccessDBPDataSource2() {
    // Arrange
    DefaultInvalidationFeedbackHandler defaultInvalidationFeedbackHandler =
        new DefaultInvalidationFeedbackHandler();

    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    when(container.getDataSource()).thenReturn(mock(DBPDataSource.class));

    ArrayList<ContextInvalidateResult> results = new ArrayList<>();
    results.add(ContextInvalidateResult.newSuccess(mock(DBPDataSource.class)));
    results.add(ContextInvalidateResult.newSuccess(mock(DBPDataSource.class)));

    // Act
    defaultInvalidationFeedbackHandler.onInvalidateSuccess(container, results);

    // Assert
    verify(container).getDataSource();
  }

  /**
   * Test {@link DefaultInvalidationFeedbackHandler#onInvalidateSuccess(DBPDataSourceContainer,
   * Collection)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then calls {@link DBPDataSourceContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DefaultInvalidationFeedbackHandler#onInvalidateSuccess(DBPDataSourceContainer, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DefaultInvalidationFeedbackHandler.onInvalidateSuccess(DBPDataSourceContainer, Collection)"
  })
  public void testOnInvalidateSuccess_whenArrayList_thenCallsGetDataSource() {
    // Arrange
    DefaultInvalidationFeedbackHandler defaultInvalidationFeedbackHandler =
        new DefaultInvalidationFeedbackHandler();

    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    when(container.getDataSource()).thenReturn(mock(DBPDataSource.class));

    // Act
    defaultInvalidationFeedbackHandler.onInvalidateSuccess(container, new ArrayList<>());

    // Assert
    verify(container).getDataSource();
  }

  /**
   * Test {@link DefaultInvalidationFeedbackHandler#onInvalidateFailure(DBPDataSourceContainer,
   * Collection)}.
   *
   * <ul>
   *   <li>Given newSuccess {@link DBPDataSource}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DefaultInvalidationFeedbackHandler#onInvalidateFailure(DBPDataSourceContainer, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DefaultInvalidationFeedbackHandler.onInvalidateFailure(DBPDataSourceContainer, Collection)"
  })
  public void testOnInvalidateFailure_givenNewSuccessDBPDataSource() {
    // Arrange
    DefaultInvalidationFeedbackHandler defaultInvalidationFeedbackHandler =
        new DefaultInvalidationFeedbackHandler();

    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    when(container.getDataSource()).thenReturn(mock(DBPDataSource.class));

    ArrayList<ContextInvalidateResult> results = new ArrayList<>();
    results.add(ContextInvalidateResult.newSuccess(mock(DBPDataSource.class)));

    // Act
    defaultInvalidationFeedbackHandler.onInvalidateFailure(container, results);

    // Assert
    verify(container).getDataSource();
  }

  /**
   * Test {@link DefaultInvalidationFeedbackHandler#onInvalidateFailure(DBPDataSourceContainer,
   * Collection)}.
   *
   * <ul>
   *   <li>Given newSuccess {@link DBPDataSource}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DefaultInvalidationFeedbackHandler#onInvalidateFailure(DBPDataSourceContainer, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DefaultInvalidationFeedbackHandler.onInvalidateFailure(DBPDataSourceContainer, Collection)"
  })
  public void testOnInvalidateFailure_givenNewSuccessDBPDataSource2() {
    // Arrange
    DefaultInvalidationFeedbackHandler defaultInvalidationFeedbackHandler =
        new DefaultInvalidationFeedbackHandler();

    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    when(container.getDataSource()).thenReturn(mock(DBPDataSource.class));

    ArrayList<ContextInvalidateResult> results = new ArrayList<>();
    results.add(ContextInvalidateResult.newSuccess(mock(DBPDataSource.class)));
    results.add(ContextInvalidateResult.newSuccess(mock(DBPDataSource.class)));

    // Act
    defaultInvalidationFeedbackHandler.onInvalidateFailure(container, results);

    // Assert
    verify(container).getDataSource();
  }

  /**
   * Test {@link DefaultInvalidationFeedbackHandler#onInvalidateFailure(DBPDataSourceContainer,
   * Collection)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DefaultInvalidationFeedbackHandler#onInvalidateFailure(DBPDataSourceContainer, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DefaultInvalidationFeedbackHandler.onInvalidateFailure(DBPDataSourceContainer, Collection)"
  })
  public void testOnInvalidateFailure_whenArrayList() {
    // Arrange
    DefaultInvalidationFeedbackHandler defaultInvalidationFeedbackHandler =
        new DefaultInvalidationFeedbackHandler();

    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    when(container.getDataSource()).thenReturn(mock(DBPDataSource.class));

    // Act
    defaultInvalidationFeedbackHandler.onInvalidateFailure(container, new ArrayList<>());

    // Assert
    verify(container).getDataSource();
  }
}
