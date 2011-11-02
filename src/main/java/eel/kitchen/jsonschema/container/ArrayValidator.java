/*
 * Copyright (c) 2011, Francis Galiegue <fgaliegue@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package eel.kitchen.jsonschema.container;

import eel.kitchen.jsonschema.context.ValidationContext;
import eel.kitchen.jsonschema.keyword.KeywordValidatorFactory;
import eel.kitchen.jsonschema.base.Validator;
import eel.kitchen.util.NodeType;
import org.codehaus.jackson.JsonNode;

public final class ArrayValidator
    extends ContainerValidator
{
    public ArrayValidator(final Validator validator,
        final ValidationContext context, final JsonNode instance)
    {
        super(validator, context, instance);
    }

    @Override
    protected void buildQueue()
    {
        final PathProvider provider = PathProviderFactory.getPathProvider(
            schema, NodeType.ARRAY);
        final KeywordValidatorFactory factory = context.getKeywordFactory();

        int i = 0;
        JsonNode schemaNode;
        Validator v;

        for (final JsonNode element: instance) {
            schemaNode = provider.getSchema(Integer.toString(i++));
            v = factory.getValidator(context, element);
            queue.add(v);
        }
    }
}
