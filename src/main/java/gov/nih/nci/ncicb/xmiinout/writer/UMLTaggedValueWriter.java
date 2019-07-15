/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/xmihandler/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.xmiinout.writer;

import gov.nih.nci.ncicb.xmiinout.domain.UMLTaggedValue;
import org.jdom.Element;


public interface UMLTaggedValueWriter {

  void writeTaggedValue(UMLTaggedValue taggedValue);

  UMLTaggedValue addTaggedValue(Element elt, UMLTaggedValue tv);

  void removeTaggedValue(Element elt, UMLTaggedValue tv);

}
