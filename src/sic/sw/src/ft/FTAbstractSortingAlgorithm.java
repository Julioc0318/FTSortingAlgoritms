package sic.sw.src.ft;

import sic.sw.src.basic.AbstractSortingAlgorithm;

public abstract class FTAbstractSortingAlgorithm extends AbstractSortingAlgorithm {

  private AbstractSortingAlgorithm[] versions = null;

  public void setVersions(AbstractSortingAlgorithm[] algos) {
    versions = algos.clone();
  }

  public AbstractSortingAlgorithm[] getVersions() {
    return versions;
  }

  FTAbstractSortingAlgorithm(AbstractSortingAlgorithm[] algos) {
    super();
    versions = null;
    setVersions(algos);
  }

  FTAbstractSortingAlgorithm() {
    super();
    versions = null;
    setVersions(Factory.newAlgorithmSet());
  }

  public void setFaultyBehavior(boolean[] behaviors) {
    if ((versions == null) || (behaviors == null)) {
      return;
    }
    if (behaviors.length == versions.length) {
      for (int i = 0; i < versions.length; i++) {
        versions[i].setFaultyBehavior(behaviors[i]);
      }
    }
  }

  public int numberOfVersions() {
    if (versions != null) {
      return versions.length;
    } else {
      return 0;
    }
  }

  public void setFaultyBehavior(int i, boolean f) {
    if (versions == null) {
      return;
    }
    if ((i >= 0) && (i < versions.length)) {
      versions[i].setFaultyBehavior(f);
    }
  }

  /**
   * Makes a copy of the integer array to be sorted.
   * 
   * @param array The array to be copied. Null arrays are treated as being empty.
   */
  @Override
  public void setData(final int[] array) {
    super.setData(array);
    if (versions != null) {
      for (int i = 0; i < versions.length; i++)
        versions[i].setData(array);
    }
  }

}
