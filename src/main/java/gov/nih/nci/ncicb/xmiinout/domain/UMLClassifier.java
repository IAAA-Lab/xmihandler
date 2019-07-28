package gov.nih.nci.ncicb.xmiinout.domain;

import java.util.List;
import java.util.Set;

/**
 * A representation of the model object 'Classifier'. A classifier is a classification of instances - it describes a
 * set of instances that have features in common. A classifier can specify a generalization hierarchy by referencing
 * its general classifiers.
 */

public interface UMLClassifier extends UMLElement, UMLTaggableElement {

  /**
   * @return a List of all the UML Attributes belonging to this class
   */
  List<UMLAttribute> getAttributes();

  /**
   * @return UML Operation
   */
  UMLOperation getOperation(String name);


  /**
   * @return a List of all the UML Operations belonging to this class
   */
  List<UMLOperation> getOperations();

  /**
   * Convenient method to retrieve one Attribute by it's name.
   *
   * @param name the name of the attribute to find
   * @return The Attribute with requested name or null if none exists
   */
  UMLAttribute getAttribute(String name);

  /**
   * @return the class name
   */
  String getName();

  /**
   * @return a Set of all associations where at least one end points to this class.
   */
  Set<UMLAssociation> getAssociations();

  /**
   * @return a List of all Generalizations where one end points to this class.
   */
  List<UMLGeneralization> getGeneralizations();

}


