package ql.gui.components.widgets;

import ql.gui.formenvironment.values.MoneyValue;
import ql.gui.formenvironment.values.Value;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.EventListener;

/**
 * Created by LGGX on 24-Feb-17.
 */
public class MoneyTextField extends AbstractWidget{

    private static int COLUMNS = 7;
    private static int MIN_NUM = -1000000;
    private static int MAX_NUM = 1000000;

    private JFormattedTextField input;
    private DefaultFormatter defaultFormatter;

    public MoneyTextField(String _label) {
        JLabel label = new JLabel(_label);

        defaultFormatter = new NumberFormatter(new DecimalFormat("#####0.00"));
        defaultFormatter.setValueClass(BigDecimal.class);
        defaultFormatter.setAllowsInvalid(false);
        //defaultFormatter.setMinimum(MIN_NUM);
        //defaultFormatter.setMaximum(MAX_NUM);
        //defaultFormatter.setOverwriteMode(true);
        DefaultFormatterFactory fmtFactory = new DefaultFormatterFactory(defaultFormatter, defaultFormatter, defaultFormatter);

        this.input = new JFormattedTextField(new BigDecimal("0.00"));
        this.input.setFormatterFactory(fmtFactory);

        this.input.setColumns(COLUMNS);

        this.component.add(label);
        this.component.add(input);
    }

    @Override
    public void addListener(EventListener listener) {
        input.addKeyListener((KeyListener) listener);
    }

    @Override
    public MoneyValue getValue() {
        String value = this.input.getText().replaceAll(",","");
        BigDecimal bd = new BigDecimal(value);
        return new MoneyValue(bd);
    }

    @Override
    public void setValue(Value value) {
        MoneyValue nvalue = (MoneyValue) value;
        this.input.setText(nvalue.getValue().toString());
    }

    @Override
    public void setReadOnly(boolean _isReadonly) {
        this.input.setEditable(false);
        defaultFormatter.setAllowsInvalid(true);
    }
}
