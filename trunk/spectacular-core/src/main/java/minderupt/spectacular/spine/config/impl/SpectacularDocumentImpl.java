/*
 * An XML document type.
 * Localname: spectacular
 * Namespace: 
 * Java type: minderupt.spectacular.spine.config.SpectacularDocument
 *
 * Automatically generated - do not modify.
 */
package minderupt.spectacular.spine.config.impl;
/**
 * A document containing one spectacular(@) element.
 *
 * This is a complex type.
 */
public class SpectacularDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements minderupt.spectacular.spine.config.SpectacularDocument
{
    private static final long serialVersionUID = 1L;
    
    public SpectacularDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SPECTACULAR$0 = 
        new javax.xml.namespace.QName("", "spectacular");
    
    
    /**
     * Gets the "spectacular" element
     */
    public minderupt.spectacular.spine.config.SpectacularType getSpectacular()
    {
        synchronized (monitor())
        {
            check_orphaned();
            minderupt.spectacular.spine.config.SpectacularType target = null;
            target = (minderupt.spectacular.spine.config.SpectacularType)get_store().find_element_user(SPECTACULAR$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "spectacular" element
     */
    public void setSpectacular(minderupt.spectacular.spine.config.SpectacularType spectacular)
    {
        synchronized (monitor())
        {
            check_orphaned();
            minderupt.spectacular.spine.config.SpectacularType target = null;
            target = (minderupt.spectacular.spine.config.SpectacularType)get_store().find_element_user(SPECTACULAR$0, 0);
            if (target == null)
            {
                target = (minderupt.spectacular.spine.config.SpectacularType)get_store().add_element_user(SPECTACULAR$0);
            }
            target.set(spectacular);
        }
    }
    
    /**
     * Appends and returns a new empty "spectacular" element
     */
    public minderupt.spectacular.spine.config.SpectacularType addNewSpectacular()
    {
        synchronized (monitor())
        {
            check_orphaned();
            minderupt.spectacular.spine.config.SpectacularType target = null;
            target = (minderupt.spectacular.spine.config.SpectacularType)get_store().add_element_user(SPECTACULAR$0);
            return target;
        }
    }
}
