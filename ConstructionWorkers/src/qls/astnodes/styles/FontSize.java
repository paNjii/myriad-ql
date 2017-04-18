/*
 * Software Construction - University of Amsterdam
 *
 * ./src/qls/astnodes/styles/FontSize.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package qls.astnodes.styles;

import ql.astnodes.LineNumber;
import qls.visitorinterfaces.StyleAndWidgetVisitor;

public class FontSize extends StyleType {

    private static final String NAME = "fontsize";

    public FontSize(Integer value, LineNumber lineNumber) {
        super(NAME, Integer.toString(value), lineNumber);
    }

    public <T> T accept(StyleAndWidgetVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
