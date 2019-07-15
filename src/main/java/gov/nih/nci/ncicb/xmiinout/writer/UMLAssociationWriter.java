/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/xmihandler/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.xmiinout.writer;

import gov.nih.nci.ncicb.xmiinout.domain.UMLAssociation;
import gov.nih.nci.ncicb.xmiinout.domain.UMLTaggedValue;


public interface UMLAssociationWriter {

  UMLTaggedValue writeTaggedValue(UMLAssociation assoc, UMLTaggedValue taggedValue);

  void removeTaggedValue(UMLAssociation assoc, UMLTaggedValue taggedValue);

}
