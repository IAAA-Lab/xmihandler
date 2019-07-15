/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/xmihandler/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.xmiinout.domain;

/**
 * Generalization or inheritance between 2 classes or 2 interfaces.
 */
public interface UMLGeneralization {

  /**
   * @return the sub type, or child class
   */
  UMLGeneralizable getSubtype();

  /**
   * @return the super type, or parent class
   */
  UMLGeneralizable getSupertype();

}
