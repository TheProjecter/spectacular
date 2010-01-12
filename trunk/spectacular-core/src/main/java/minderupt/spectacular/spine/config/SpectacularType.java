/*
 * XML Type:  spectacularType
 * Namespace: 
 * Java type: minderupt.spectacular.spine.config.SpectacularType
 *
 * Automatically generated - do not modify.
 */
package minderupt.spectacular.spine.config;


/**
 * An XML spectacularType(@).
 *
 * This is a complex type.
 */
public interface SpectacularType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(SpectacularType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s93CAFC5FADEA884A9CDF9B356A957356").resolveHandle("spectaculartype05d4type");
    
    /**
     * Gets array of all "for-spec" elements
     */
    minderupt.spectacular.spine.config.ForSpecType[] getForSpecArray();
    
    /**
     * Gets ith "for-spec" element
     */
    minderupt.spectacular.spine.config.ForSpecType getForSpecArray(int i);
    
    /**
     * Returns number of "for-spec" element
     */
    int sizeOfForSpecArray();
    
    /**
     * Sets array of all "for-spec" element
     */
    void setForSpecArray(minderupt.spectacular.spine.config.ForSpecType[] forSpecArray);
    
    /**
     * Sets ith "for-spec" element
     */
    void setForSpecArray(int i, minderupt.spectacular.spine.config.ForSpecType forSpec);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "for-spec" element
     */
    minderupt.spectacular.spine.config.ForSpecType insertNewForSpec(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "for-spec" element
     */
    minderupt.spectacular.spine.config.ForSpecType addNewForSpec();
    
    /**
     * Removes the ith "for-spec" element
     */
    void removeForSpec(int i);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static minderupt.spectacular.spine.config.SpectacularType newInstance() {
          return (minderupt.spectacular.spine.config.SpectacularType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static minderupt.spectacular.spine.config.SpectacularType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (minderupt.spectacular.spine.config.SpectacularType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static minderupt.spectacular.spine.config.SpectacularType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (minderupt.spectacular.spine.config.SpectacularType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static minderupt.spectacular.spine.config.SpectacularType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (minderupt.spectacular.spine.config.SpectacularType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static minderupt.spectacular.spine.config.SpectacularType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (minderupt.spectacular.spine.config.SpectacularType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static minderupt.spectacular.spine.config.SpectacularType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (minderupt.spectacular.spine.config.SpectacularType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static minderupt.spectacular.spine.config.SpectacularType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (minderupt.spectacular.spine.config.SpectacularType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static minderupt.spectacular.spine.config.SpectacularType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (minderupt.spectacular.spine.config.SpectacularType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static minderupt.spectacular.spine.config.SpectacularType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (minderupt.spectacular.spine.config.SpectacularType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static minderupt.spectacular.spine.config.SpectacularType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (minderupt.spectacular.spine.config.SpectacularType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static minderupt.spectacular.spine.config.SpectacularType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (minderupt.spectacular.spine.config.SpectacularType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static minderupt.spectacular.spine.config.SpectacularType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (minderupt.spectacular.spine.config.SpectacularType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static minderupt.spectacular.spine.config.SpectacularType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (minderupt.spectacular.spine.config.SpectacularType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static minderupt.spectacular.spine.config.SpectacularType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (minderupt.spectacular.spine.config.SpectacularType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static minderupt.spectacular.spine.config.SpectacularType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (minderupt.spectacular.spine.config.SpectacularType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static minderupt.spectacular.spine.config.SpectacularType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (minderupt.spectacular.spine.config.SpectacularType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static minderupt.spectacular.spine.config.SpectacularType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (minderupt.spectacular.spine.config.SpectacularType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static minderupt.spectacular.spine.config.SpectacularType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (minderupt.spectacular.spine.config.SpectacularType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
