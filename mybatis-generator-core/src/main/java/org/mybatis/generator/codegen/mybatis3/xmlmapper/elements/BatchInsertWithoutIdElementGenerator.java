/**
 *    Copyright 2006-2019 the original author or authors.
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
package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import java.util.List;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.ListUtilities;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

public class BatchInsertWithoutIdElementGenerator extends AbstractXmlElementGenerator {

    public BatchInsertWithoutIdElementGenerator() {super();}

    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("insert");

        answer.addAttribute(new Attribute(
            "id", introspectedTable.getBatchInsertWithoutIdStatementId()));

        FullyQualifiedJavaType parameterType = introspectedTable.getRules()
            .calculateAllFieldsClass();

        answer.addAttribute(new Attribute("parameterType",
            parameterType.getFullyQualifiedName()));

        context.getCommentGenerator().addComment(answer);

        XmlElement forEachElement = new XmlElement("foreach");
        forEachElement.addAttribute(new Attribute("collection","list"));
        forEachElement.addAttribute(new Attribute("item", "record"));
        forEachElement.addAttribute(new Attribute("separator", ","));

        StringBuilder insertClause = new StringBuilder();
        insertClause.append("insert into ");
        insertClause.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        insertClause.append(" (");

        StringBuilder valuesClause = new StringBuilder();
        valuesClause.append("(");

        List<IntrospectedColumn> columns = ListUtilities
            .removeIdentityAndGeneratedAlwaysColumns(introspectedTable.getAllColumns());
        for (int i = 0; i < columns.size(); i++) {
            IntrospectedColumn introspectedColumn = columns.get(i);
            insertClause.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
            valuesClause.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn,"record."));
            if (i + 1 < columns.size()) {
                insertClause.append(", ");
                valuesClause.append(", ");
            }
        }
        insertClause.append(") values ");
        valuesClause.append(")");
        forEachElement.addElement(new TextElement(valuesClause.toString()));
        answer.addElement(new TextElement(insertClause.toString()));
        answer.addElement(forEachElement);

        if (context.getPlugins().sqlMapInsertSelectiveElementGenerated(
            answer, introspectedTable)) {
            parentElement.addElement(answer);
        }
    }
}
