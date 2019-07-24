/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/xmihandler/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.xmiinout.domain;

/**
 * UMLClass represents a Class in a UML Model
 */
public interface UMLInterface extends UMLClassifier, UMLTaggableElement, UMLDependencyEnd, UMLAssociable, UMLGeneralizable, UMLDatatype {

  /**
   * @return the UMLPackage to which this class belongs
   */
  UMLPackage getPackage();

  /**
   * @return the class stereotype, or null if there isn't any
   */
  String getStereotype();
}
