/*
 * Copyright (c) 2018 CA. All rights reserved.
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.l7tech.external.custom.evaluatemathexpression;

import com.l7tech.external.custom.evaluatemathexpression.console.EvaluateMathExpressionAssertionPropertiesPanel;
import com.l7tech.policy.assertion.ext.AssertionEditor;
import com.l7tech.policy.assertion.ext.CustomAssertion;
import com.l7tech.policy.assertion.ext.CustomAssertionUI;
import com.l7tech.policy.assertion.ext.cei.UsesConsoleContext;

import javax.swing.*;
import java.io.Serializable;
import java.util.Map;

/**
 *
 */
public class EvaluateMathExpressionUI implements CustomAssertionUI, UsesConsoleContext, Serializable {
    private Map consoleContext;

    @Override
    public AssertionEditor getEditor(CustomAssertion customAssertion) {
        if(!(customAssertion instanceof EvaluateMathExpressionAssertion)) {
            throw new IllegalArgumentException(EvaluateMathExpressionAssertion.class +" type is required");
        }
        return new EvaluateMathExpressionAssertionPropertiesPanel((EvaluateMathExpressionAssertion) customAssertion, consoleContext);
    }

    @Override
    public ImageIcon getSmallIcon() {
        return new ImageIcon(getClass().getClassLoader().getResource("Properties16.gif"));
    }

    @Override
    public ImageIcon getLargeIcon() {
        return new ImageIcon(getClass().getClassLoader().getResource("Properties16.gif"));
    }

    @Override
    public void setConsoleContextUsed(Map map) {
        consoleContext = map;
    }
}
