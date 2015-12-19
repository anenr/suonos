/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package suonos.lucene;

import com.github.am0e.commons.json.InstanceCreator;
import com.github.am0e.commons.json.JsonObjectReader;

import suonos.app.SuonosLib;
import suonos.models.media.MediaTagValue;
import suonos.models.media.MediaTagValues;
import suonos.models.media.MediaTags;

public class JsonDeserializer extends JsonObjectReader {

    private final MediaTags tags = SuonosLib.lib().instanceOf(MediaTags.class);

    public JsonDeserializer() {
        super.registerAdaptor(MediaTagValues.class, new InstanceCreator<MediaTagValues>() {

            @Override
            public MediaTagValues createInstance(Class<MediaTagValues> type) {
                return new MediaTagValues();
            }

            @Override
            public MediaTagValues deserialized(MediaTagValues obj) {
                return obj;
            }
        });

        super.registerAdaptor(MediaTagValue.class, new InstanceCreator<MediaTagValue>() {

            @Override
            public MediaTagValue createInstance(Class<MediaTagValue> type) {
                return new MediaTagValue();
            }

            @Override
            public MediaTagValue deserialized(MediaTagValue obj) {
                // Recreate the correct object with all the fields filled in.
                //
                obj = tags.createTagValue(tags.getMediaTag(obj.getTagDefId()), obj.getValue());
                return obj;
            }
        });
    }
}
