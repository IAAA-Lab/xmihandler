/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/xmihandler/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.xmiinout.domain;


/**
 * Represents a dependency between 2 UML Elements.
 * Usually represented by a dashed line in UML.
 * <br> A Dependency has 2 ends, called client and supplier
 */
public interface UMLDependency extends UMLTaggableElement {

  /**
   * @return the class name
   */
  String getName();

  /**
   * @return the visibility (scope)
   */
  UMLVisibility getVisibility();

  /**
   * @return the client end
   */
  UMLDependencyEnd getClient();

  /**
   * @return the supplier end
   */
  UMLDependencyEnd getSupplier();

  /**
   * @return the dependency stereotype name, or null if there isn't any
   */
  String getStereotype();

  /**
   * @param stereotype
   */
  void addStereotype(String stereotype);

  /**
   * @param stereotype
   */
  void removeStereotype(String stereotype);
}

