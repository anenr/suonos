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
package suonos.services.player;

import suonos.services.player.events.PlaybackStarted;

/**
 * A media item has started playing.
 * 
 * @author anthony
 */
public class PlaybackStartedCmd implements Cmd {

    private PlaybackInfo playbackItem;

    public PlaybackStartedCmd(PlaybackInfo item) {
        this.playbackItem = item;
        if (item.item == null) {
            this.getClass();
        }
    }

    @Override
    public void execute(MediaPlayer mp) throws Exception {
        mp.itemPlaying = playbackItem.item;

        MediaPlayer.log.debug("playStarted {}", mp.itemPlaying.mediaPath);

        // Calc percentage position.
        //
        MediaPlayer.log.debug("setLength {}", playbackItem.lengthInSecs);

        mp.itemPlaying.lenInSec = (int) playbackItem.lengthInSecs;

        mp.events.post(new PlaybackStarted(mp.itemPlaying));
    }

    @Override
    public void queued(MediaPlayer mp) {
    }
}
