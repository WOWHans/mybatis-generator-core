/**
 *    Copyright ${license.git.copyrightYears} the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.generator.codegen.mybatis3.javamapper.elements;

import java.util.Set;
import java.util.TreeSet;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;

public class BatchInsertWithoutIdMethodGenerator extends AbstractJavaMapperMethodGenerator {

    public BatchInsertWithoutIdMethodGenerator() {
        super();
    }

    @Override
    public void addInterfaceElements(Interface interfaze) {
        Method method = new Method();

        method.setReturnType(FullyQualifiedJavaType.getBooleanPrimitiveInstance());
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName(introspectedTable.getBatchInsertWithoutIdStatementId());

        FullyQualifiedJavaType parameterType  = introspectedTable.getRules().calculateAllFieldsClass();

        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        importedTypes.add(parameterType);
        FullyQualifiedJavaType javaType = FullyQualifiedJavaType.getNewListInstance();
        javaType.addTypeArgument(parameterType);
        method.addParameter(new Parameter(javaType, "list"));

        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        if (context.getPlugins().clientBatchInsertMethodGenerated(method, interfaze, introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }
}
