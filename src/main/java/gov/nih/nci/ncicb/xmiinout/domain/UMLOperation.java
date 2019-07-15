/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/xmihandler/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.xmiinout.domain;

import java.util.List;

public interface UMLOperation extends UMLTaggableElement {
  /**
   * @return The name of the operation
   */
  String getName();

  /**
   * @return the visibility (scope)
   */
  UMLVisibility getVisibility();

  /**
   * @return the stereo type
   */
  String getStereoType();


  /**
   * @return list of parameters
   */
  List<UMLOperationParameter> getParameters();

  /**
   * @return the isAbstract modifier
   */
  UMLAbstractModifier getAbstractModifier();

  /**
   * @return the isFinal modifier
   */
  UMLFinalModifier getFinalModifier();

  /**
   * @return the isSynchronized modifier
   */
  UMLSynchronizedModifier getSynchronizedModifier();

  /**
   * @return the isStatic modifier
   */
  UMLStaticModifier getStaticModifier();

  /**
   * @return a List of all the UML Attributes belonging to this class
   */
  List<UMLAttribute> getAttributes();

  String getSpecification();

}
