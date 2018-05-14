/*
 * Copyright (c) 2018 CA. All rights reserved.
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.l7tech.external.custom.evaluatemathexpression.console;

import com.l7tech.external.custom.evaluatemathexpression.EvaluateMathExpressionAssertion;
import com.l7tech.policy.assertion.ext.AssertionEditor;
import com.l7tech.policy.assertion.ext.AssertionEditorSupport;
import com.l7tech.policy.assertion.ext.EditListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 *
 */
public class EvaluateMathExpressionAssertionPropertiesPanel extends JDialog implements AssertionEditor {
    private JTextField expressionField;
    private JTextField outputVariableField;
    private JSpinner precisionField;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel mainPanel;

    private EvaluateMathExpressionAssertion assertion;
    private AssertionEditorSupport editorSupport;
    private Map consoleContext;

    public EvaluateMathExpressionAssertionPropertiesPanel(EvaluateMathExpressionAssertion assertion, Map consoleContext) {
        super(Frame.getFrames().length > 0 ? Frame.getFrames()[0] : null, true);
        this.setTitle("Evaluate Math Assertion Properties");
        this.assertion = assertion;
        this.editorSupport = new AssertionEditorSupport(this);
        this.consoleContext = consoleContext;
        this.init();
        enableDisableOkButton();
        modelToView(this.assertion);
        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        this.pack();
    }

    @Override
    public void edit() {
        this.setVisible(true);
    }

    @Override
    public void addEditListener(EditListener editListener) {
        this.editorSupport.addListener(editListener);
    }

    @Override
    public void removeEditListener(EditListener editListener) {
        this.editorSupport.removeListener(editListener);
    }

    private void viewToModel(EvaluateMathExpressionAssertion assertion) {
        assertion.setExpression(expressionField.getText().trim());
        assertion.setOutputVariable(outputVariableField.getText().trim());
        assertion.setPrecision(((Integer)precisionField.getValue()).intValue());
    }

    private void modelToView(EvaluateMathExpressionAssertion assertion) {
        expressionField.setText(assertion.getExpression() == null ? "" : assertion.getExpression());
        outputVariableField.setText(assertion.getOutputVariable() == null ? "" : assertion.getOutputVariable());
        precisionField.setValue(assertion.getPrecision());
    }

    private void init() {

        expressionField.getDocument().addDocumentListener(getDocumentListener());
        outputVariableField.getDocument().addDocumentListener(getDocumentListener());
        precisionField.setModel(new SpinnerNumberModel(0, 0, 16, 1));

        okButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                viewToModel(assertion);
                editorSupport.fireEditAccepted(assertion);
                dispose();
            }
        });
        cancelButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                editorSupport.fireCancelled(assertion);
                dispose();
            }
        });
    }

    private void enableDisableOkButton() {
        boolean expressionEnabled = !expressionField.getText().trim().isEmpty();
        boolean outputVariableEnabled = !outputVariableField.getText().trim().isEmpty();
        okButton.setEnabled(expressionEnabled && outputVariableEnabled);

    }

    private DocumentListener getDocumentListener() {
        return new DocumentListener(){
            public void removeUpdate (DocumentEvent e){
                enableDisableOkButton();
            }

            public void insertUpdate(DocumentEvent e){
                enableDisableOkButton();
            }

            public void changedUpdate(DocumentEvent e){
                //Plain text components do not fire these events
            }
        };
    }
}
