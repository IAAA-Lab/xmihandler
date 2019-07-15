/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/xmihandler/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.xmiinout.writer;

public interface UMLWriter {

  UMLClassWriter getUMLClassWriter();

  UMLInterfaceWriter getUMLInterfaceWriter();

  UMLAttributeWriter getUMLAttributeWriter();

  UMLAssociationWriter getUMLAssociationWriter();

  UMLAssociationEndWriter getUMLAssociationEndWriter();

  UMLTaggedValueWriter getUMLTaggedValueWriter();

  UMLModelWriter getUMLModelWriter();

  UMLPackageWriter getUMLPackageWriter();

  UMLDependencyWriter getUMLDependencyWriter();

  UMLStereotypeWriter getUMLStereotypeWriter();

  UMLOperationWriter getUMLOperationWriter();

  UMLOperationParameterWriter getUMLOperationParameterWriter();
}
