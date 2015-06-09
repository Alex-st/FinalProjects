<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:math="http://www.w3.org/2005/xpath-functions/math"
    xmlns:params="http://www.yazatebe.org.ua"
    exclude-result-prefixes="xs"
    version="2.0">
    
    <xsl:template match="/">
        <html>
            <body>
                <h2>Planes</h2>
                <table border="1">
                    <tr>
                        <th>id</th>
                        <th>model</th>
                        <th>origin</th>
                        <th>type</th>
                        <th>length</th>
                        <th>width</th>
                        <th>height</th>
                        <th>price</th>
                    </tr>
                    <xsl:for-each select="root/plane">
                        <tr>
                            
                            <td>
                                <xsl:value-of select="@id"/>
                            </td>
                            
                            <td><xsl:value-of select="model"/></td>
                            <td>
                                <xsl:value-of select="origin"/>
                            </td>
                            <td>
                                <xsl:value-of select="type"/>
                            </td>
                            <td>
                                <xsl:value-of select="parameters/params:Length"/>
                            </td>
                            <td>
                                <xsl:value-of select="parameters/params:Width"/>
                            </td>
                            <td>
                                <xsl:value-of select="parameters/params:Height"/>
                            </td>
                            <td>
                                <xsl:value-of select="price"/>
                            </td>
                        </tr>
                        
                                               
                    </xsl:for-each>
                    
                </table>
                
            </body>
            
        </html>
    </xsl:template>
    
</xsl:stylesheet>