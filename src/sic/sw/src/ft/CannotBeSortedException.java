/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2015-2025 Universitat Politècnica de València
 *  Dependable and Secure Computer-based Systems
 *  David de Andrés and Juan Carlos Ruiz
 *  mailto: ddandres AT disca DOT upv DOT es / jcruizg AT disca DOT upv DOT es
 *
 */

package sic.sw.src.ft;

/**
 * Signals that an array could not be sorted, generally because some fault
 * occurred during the sorting operation.
 */
public class CannotBeSortedException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * Constructs an CannotBeSortedException with the specified detail message.
   *
   * @param message The detail message
   */
  public CannotBeSortedException(final String message) {
    super(message);
  }

}
