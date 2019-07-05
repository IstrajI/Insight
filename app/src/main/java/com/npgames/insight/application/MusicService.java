/*
package com.npgames.insight.application;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

public class MusicService implements MediaPlayer.OnCompletionListener,
        AudioManager.OnAudioFocusChangeListener {
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    private int musicToPlay;
    private int resumePosition;

    public void destroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        removeAudioFocus();
    }

    private void initMediaPlayer(final Context context) {
        mediaPlayer = MediaPlayer.create(context, musicToPlay);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setLooping(true);
    }

    @Override
    public void onAudioFocusChange(final int focusState) {
        switch (focusState) {
            case AudioManager.AUDIOFOCUS_GAIN:
                if (!mediaPlayer.isPlaying()) mediaPlayer.start();
                mediaPlayer.setVolume(1.0f, 1.0f);
                break;
            case AudioManager.AUDIOFOCUS_LOSS:
                Log.d("TestPish", "fucus loss");
                // Lost focus for an unbounded amount of time: stopMusic playback and release media player
                if (mediaPlayer.isPlaying()) mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                Log.d("TestPish", "fucus loss transient");
                // Lost focus for a short time, but we have to stopMusic
                // playback. We don't release the media player because playback
                // is likely to resume
                if (mediaPlayer.isPlaying()) mediaPlayer.pause();
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                Log.d("TestPish", "fucus duck");
                // Lost focus for a short time, but it's ok to keep playing
                // at an attenuated level
                if (mediaPlayer.isPlaying()) mediaPlayer.setVolume(0.1f, 0.1f);
                break;
        }
    }

    public void pauseMusic() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            resumePosition = mediaPlayer.getCurrentPosition();
        }
    }

    public void resumeMusic() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(resumePosition);
            mediaPlayer.start();
        }
    }

    public void setMusic(final Context context, final int musicResource) {
        if (requestAudioFocus(context) && musicResource == musicToPlay) {
            resumeMusic();
        } else if (requestAudioFocus(context)) {
            musicToPlay = musicResource;
            if(mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
            initMediaPlayer(context);
            mediaPlayer.start();
        }
    }

    private boolean requestAudioFocus(final Context context) {
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int result = audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        return result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
    }

    private boolean removeAudioFocus() {
        return AudioManager.AUDIOFOCUS_REQUEST_GRANTED == audioManager.abandonAudioFocus(this);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        //TODO: playMusic next
        Log.d("TestPish", "onCompletetion");

    }
}
*/
