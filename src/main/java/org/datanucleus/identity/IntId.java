/**********************************************************************
Copyright (c) 2014 Andy Jefferson and others. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Contributors:
    ...
**********************************************************************/
package org.datanucleus.identity;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * This class is for identity with a single int field.
 */
public class IntId extends SingleFieldId<Integer>
{
    private int key;

    public IntId(Class pcClass, int key)
    {
        super(pcClass);
        this.key = key;
        this.hashCode = targetClassName.hashCode() ^ key;
    }

    public IntId(Class pcClass, Integer key)
    {
        this(pcClass, key != null ? key.intValue() : -1);
        assertKeyNotNull(key);
    }

    public IntId(Class pcClass, String str)
    {
        this(pcClass, Integer.parseInt(str));
        assertKeyNotNull(str);
    }

    public IntId()
    {
    }

    public int getKey()
    {
        return key;
    }

    public Integer getKeyAsObject()
    {
        return Integer.valueOf(key);
    }

    /**
     * Return the String form of the key.
     * @return the String form of the key
     */
    public String toString()
    {
        return Integer.toString(key);
    }

    /* (non-Javadoc)
     * @see org.datanucleus.identity.SingleFieldId#keyEquals(org.datanucleus.identity.SingleFieldId)
     */
    @Override
    protected boolean keyEquals(SingleFieldId obj)
    {
        if (obj instanceof IntId)
        {
            return key == ((IntId)obj).key;
        }
        return false;
    }

    public int compareTo(Object o)
    {
        if (o instanceof IntId)
        {
            IntId other = (IntId) o;
            int result = super.compare(other);
            if (result == 0)
            {
                return key - other.key;
            }
            return result;
        }
        else if (o == null)
        {
            throw new ClassCastException("object is null");
        }
        throw new ClassCastException(this.getClass().getName() + " != " + o.getClass().getName());
    }

    /**
     * Write this object. Write the superclass first.
     * @param out the output
     */
    public void writeExternal(ObjectOutput out) throws IOException
    {
        super.writeExternal(out);
        out.writeInt(key);
    }

    /**
     * Read this object. Read the superclass first.
     * @param in the input
     */
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        super.readExternal(in);
        key = in.readInt();
    }
}