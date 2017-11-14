/*
 *  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.wso2.siddhi.plugins.idea.psi;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import org.antlr.jetbrains.adaptor.psi.IdentifierDefSubtree;
import org.jetbrains.annotations.Nullable;
import org.wso2.siddhi.plugins.idea.SiddhiIcons;
import org.wso2.siddhi.plugins.idea.SiddhiTypes;
import org.wso2.siddhi.plugins.idea.psi.impl.SiddhiItemPresentation;

import javax.annotation.Nonnull;
import javax.swing.Icon;

/**
 * ANTLRPsiNode which represents attribute_name rule in parser.
 */
public class AttributeNameNode extends IdentifierDefSubtree {

    public AttributeNameNode(@Nonnull ASTNode node) {
        super(node, SiddhiTypes.IDENTIFIER);
    }

    @Override
    public ItemPresentation getPresentation() {
        return new SiddhiItemPresentation(getNameIdentifier()) {

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return SiddhiIcons.PARAMETER;
            }
        };
    }
}
